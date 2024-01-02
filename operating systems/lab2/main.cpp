#include <windows.h>
#include <iostream>


LPVOID WINAPI min_max(int* arr) {
	int max = INT_MIN, min = INT_MAX;
	int ind_max = 6, ind_min = 6;
	for (int i = 0; i < arr[0]; i++) {
		if (max < arr[i + 6]) {
			max = arr[i + 6];
			ind_max = i + 6;
		}
		Sleep(7);
		if (min > arr[i + 6]) {
			min = arr[i + 6];
			ind_min = i + 6;
		}
		Sleep(7);
	}
	arr[1] = min, arr[2] = max, arr[3] = ind_min, arr[4] = ind_max;

	std::cout << "max value: " << max << "\nmin value: " << min << '\n';

	return 0;
}

DWORD WINAPI average(int* arr) {
	int avg = 0;
	for (int i = 0; i < arr[0]; i++) {
		avg += arr[i + 6];
		Sleep(12);
	}
	arr[5] = avg / arr[0];

	std::cout << "\naverage value: " << arr[5] << '\n';

	return 0;
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

	HANDLE hThread, hThread_;
	DWORD IDThread, IDThread_;

	hThread = CreateThread(NULL, 0, (LPTHREAD_START_ROUTINE)min_max,
		(void*)arr, 0, &IDThread);
	if (hThread == NULL)
		return GetLastError();

	hThread_ = CreateThread(NULL, 0, (LPTHREAD_START_ROUTINE)average,
		(void*)arr, 0, &IDThread_);
	if (hThread_ == NULL)
		return GetLastError();

	WaitForSingleObject(hThread, INFINITE);
	WaitForSingleObject(hThread_, INFINITE);
	
	avg = arr[5];
	ind_max = arr[4];
	ind_min = arr[3];
	arr[ind_max] = avg;
	arr[ind_min] = avg;

	for (int i = 0; i < n; i++)
		std::cout << arr[i + 6] << " ";

	CloseHandle(hThread_);
	CloseHandle(hThread);

	delete[] arr;

	}
	catch (std::exception& e) {
		std::cout << e.what();
	}

	return 0;
}
