#include <iostream>
#include <thread>
#include <chrono>
std::mutex m;

class semaphore {
    unsigned int count;
    std::mutex mutex;
    std::condition_variable condition;

public:
    semaphore(): count(0) {};
    explicit semaphore(unsigned int initial_count): count(initial_count) {};

    void signal() {
        std::unique_lock<std::mutex> lock(mutex);
        ++count;
        condition.notify_one();
    }
    void wait() {
        std::unique_lock<std::mutex> lock(mutex);
        while (count == 0) condition.wait(lock);
        --count;
    }
};

semaphore pause_sema(0);
semaphore* marker_sems;

void my_thread(int i, std::vector<int>& v, std::vector<bool>& marked) {
    marker_sems[i].wait();
    srand(i);

    int quantity_of_marked = 0;
    while (true) {
        int random_number = rand() % v.size();
        m.lock();
        if (v[random_number] == 0) {
            std::this_thread::sleep_for(std::chrono::milliseconds(5));
            v[random_number] = i;
            std::this_thread::sleep_for(std::chrono::milliseconds(5));
            quantity_of_marked++;
            m.unlock();
        }
        else {
            std::cout << "\nnumber: " << i
                << ", quantity of marked elements: " << quantity_of_marked
                << ", index of array element which cannot be marked: " << random_number
                << '\n';
            m.unlock();
            pause_sema.signal();
            marker_sems[i].wait();
            if (marked[i]) {
                m.lock();
                for (int &j: v)
                    if (j == i)
                        j = 0;
                m.unlock();
                marker_sems[i].signal();
                break;
            }
        }
    }
}

int main() {
    int n;
    std::cout << "array size: ";
    std::cin >> n;
    std::vector<int> v(n);

    int quantity_of_threads;
    std::cout << "quantity of 'marker' threads: ";
    std::cin >> quantity_of_threads;

    std::vector<bool> marked(quantity_of_threads + 1);
    marked[0] = true;
    marker_sems = new semaphore[quantity_of_threads + 1];

    std::thread** marker = new std::thread*[quantity_of_threads + 1];
    for (int i = 1; i < quantity_of_threads + 1; i++) {
        marker[i] = new std::thread(my_thread, i, std::ref(v), std::ref(marked));
        marker[i]->detach();
        marker_sems[i].signal();
    }

    int marker_to_delete, quantity_of_closed = 0;
    while (true) {
        for (int i = 1; i < (quantity_of_threads - quantity_of_closed) + 1; i++)
            pause_sema.wait();

        for (int i = 0; i < n; i++)
            std::cout << v[i] << " ";

        std::cout << "\nmarker to delete: ";
        std::cin >> marker_to_delete;
        while (marker_to_delete < 1 || marker_to_delete > quantity_of_threads) {
            std::cout << "wrong marker to delete, try again: ";
            std::cin >> marker_to_delete;
        }
        while (marked[marker_to_delete]) {
            std::cout << "marker has already deleted, try again: ";
            std::cin >> marker_to_delete;
        }

        marked[marker_to_delete] = true;
        quantity_of_closed++;
        marker_sems[marker_to_delete].signal();
        std::this_thread::sleep_for(std::chrono::milliseconds(1));

        marker_sems[marker_to_delete].wait();

        for (int i = 0; i < n; i++)
            std::cout << v[i] << " ";

        if (quantity_of_closed == quantity_of_threads) break;

        for (int i = 1; i < quantity_of_threads + 1; i++)
            if (!marked[i])
                marker_sems[i].signal();
    }

    for (int i = 1; i < quantity_of_threads + 1; i++) {
        marker[i] = nullptr;
        delete[] marker[i];
    }
    delete[] marker;

    delete[] marker_sems;

    return 0;
}
