#include <boost/thread.hpp>
#include <boost/chrono.hpp>
#include <iostream>


void min_max(int* arr) {
	int max = INT_MIN, min = INT_MAX;
	int ind_max = 6, ind_min = 6;
	for (int i = 0; i < arr[0]; i++) {
		if (max < arr[i + 6]) {
			max = arr[i + 6];
			ind_max = i + 6;
		}
		boost::this_thread::sleep_for(boost::chrono::milliseconds(7));
		if (min > arr[i + 6]) {
			min = arr[i + 6];
			ind_min = i + 6;
		}
		boost::this_thread::sleep_for(boost::chrono::milliseconds(7));
	}
	arr[1] = min, arr[2] = max, arr[3] = ind_min, arr[4] = ind_max;

	std::cout << "max value: " << max << "\nmin value: " << min << '\n';
}

void average(int* arr) {
	int avg = 0;
	for (int i = 0; i < arr[0]; i++) {
		avg += arr[i + 6];
		boost::this_thread::sleep_for(boost::chrono::milliseconds(12));
	}
	arr[5] = avg / arr[0];

	std::cout << "\naverage value: " << arr[5] << '\n';
}

int main() {
	try {
		int n, max, min, ind_max, ind_min, avg;

		std::cout << "array size: ";
		std::cin >> n;

		if (n == 0) throw std::exception("division by zero in average()");

		int* arr = new int[n + 6];
		arr[0] = n;

		std::cout << "array elements: ";
		for (int i = 0; i < n; i++)
			std::cin >> arr[i + 6];

		boost::thread thread1(min_max, boost::ref(arr));
		boost::thread thread2(average, boost::ref(arr));

		thread1.join();
		thread2.join();

		avg = arr[5];
		ind_max = arr[4];
		ind_min = arr[3];
		arr[ind_max] = avg;
		arr[ind_min] = avg;

		for (int i = 0; i < n; i++)
			std::cout << arr[i + 6] << " ";

		delete[] arr;

	}
	catch (std::exception& e) {
		std::cout << e.what();
	}

	return 0;
}