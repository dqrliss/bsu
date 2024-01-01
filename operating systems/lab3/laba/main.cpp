#include <windows.h> 
#include <iostream>

int n, quantity_of_threads;
int* arr;
CRITICAL_SECTION cs;

struct struct_to_thread {
    int number;
    bool marked;
    HANDLE hEventFrom;
    HANDLE hEventTo;
};

void thread(struct_to_thread obj_of_stt) {
    WaitForSingleObject(obj_of_stt.hEventTo, INFINITE);
    srand(obj_of_stt.number);
    int quantity_of_marked = 0, random_number;
    while (true) {
        random_number = rand() % n;

        EnterCriticalSection(&cs);
        if (arr[random_number] == 0) {
            Sleep(5);
            arr[random_number] = obj_of_stt.number;
            Sleep(5);
            quantity_of_marked++;
            LeaveCriticalSection(&cs);
        }

        else {
            std::cout << "\nnumber: " << obj_of_stt.number
                << ", quantity of marked elements: " << quantity_of_marked
                << ", index of array element which cannot be marked: " << random_number
                << '\n';
            LeaveCriticalSection(&cs);

            SetEvent(obj_of_stt.hEventFrom);

            WaitForSingleObject(obj_of_stt.hEventTo, INFINITE);
            if (obj_of_stt.marked) {
                for (int i = 0; i < n; i++)
                    if (arr[i] == obj_of_stt.number)
                        arr[i] = 0;
                break;
            }
        }
    }
    SetEvent(obj_of_stt.hEventFrom);
}

int main() {
    std::cout << "array size: ";
    std::cin >> n;

    arr = new int[n];
    for (int i = 0; i < n; i++)
        arr[i] = 0;

    std::cout << "quantity of 'marker' threads: ";
    std::cin >> quantity_of_threads;

    DWORD* IDThread = new DWORD[quantity_of_threads];
    HANDLE* hThread = new HANDLE[quantity_of_threads];

    struct_to_thread* obj_of_stt = new struct_to_thread[quantity_of_threads];
    //HANDLE* local_hEventFrom = new HANDLE[quantity_of_threads];

    InitializeCriticalSection(&cs);

    for (int i = 0; i < quantity_of_threads; i++) {
        obj_of_stt[i].number = i + 1;
        obj_of_stt[i].marked = false;
        obj_of_stt[i].hEventTo = CreateEvent(NULL, FALSE, FALSE, NULL);
        obj_of_stt[i].hEventFrom = CreateEvent(NULL, TRUE, FALSE, NULL);
        //local_hEventFrom[i] = obj_of_stt[i].hEventFrom;

        hThread[i] = CreateThread(NULL, 0, (LPTHREAD_START_ROUTINE)thread, &obj_of_stt[i], 0, &IDThread[i]);
        if (hThread[i] = NULL)
            return GetLastError();
    }
    for (int i = 0; i < quantity_of_threads; i++)
        SetEvent(obj_of_stt[i].hEventTo);

    int marker_to_delete, quantity_of_closed = 0;
    while (true) {

        for (int i = 0; i < quantity_of_threads; i++)
            WaitForSingleObject(obj_of_stt[i].hEventFrom, INFINITE);

        /*
        
        2) using WaitForMultipleObjects (with the help of "local_hEventFrom[i] = obj_of_stt[i].hEventFrom");
        
        WaitForMultipleObjects(quantity_of_threads, local_hEventFrom, TRUE, INFINITE);


        3) with automatic reset of hEventFrom:
        
        for (int i = 0; i < quantity_of_threads; i++) {
            if (!obj_of_stt[i].marked)
                WaitForSingleObject(obj_of_stt[i].hEventFrom, INFINITE);

        */

        for (int i = 0; i < n; i++)
            std::cout << arr[i] << " ";

        std::cout << "\nmarker to delete: ";
        std::cin >> marker_to_delete;
        marker_to_delete--;

        while (marker_to_delete < 0 || marker_to_delete >= quantity_of_threads) {
            std::cout << "wrong marker, try again: ";
            std::cin >> marker_to_delete;
            marker_to_delete--;
        }
        while (obj_of_stt[marker_to_delete].marked) {
            std::cout << "marker has already been deleted, try again: ";
            std::cin >> marker_to_delete;
            marker_to_delete--;
        }

        obj_of_stt[marker_to_delete].marked = true;
        quantity_of_closed++;

        ResetEvent(obj_of_stt[marker_to_delete].hEventFrom);
        SetEvent(obj_of_stt[marker_to_delete].hEventTo);
        
        WaitForSingleObject(obj_of_stt[marker_to_delete].hEventFrom, INFINITE);

        for (int i = 0; i < n; i++)
            std::cout << arr[i] << " ";
        
        if (quantity_of_closed == quantity_of_threads) break;

        for (int i = 0; i < quantity_of_threads; i++)
            if (!obj_of_stt[i].marked) {
                ResetEvent(obj_of_stt[i].hEventFrom);
                SetEvent(obj_of_stt[i].hEventTo);
            }
    }

    for (int i = quantity_of_threads - 1; i > -1; i--) {
        CloseHandle(hThread[i]);
        CloseHandle(obj_of_stt[i].hEventFrom);
        CloseHandle(obj_of_stt[i].hEventTo);
    }

    DeleteCriticalSection(&cs);

    //delete[] local_hEventFrom;
    delete[] obj_of_stt;
    delete[] hThread;
    delete[] IDThread;
    delete[] arr;

    return 0;
}