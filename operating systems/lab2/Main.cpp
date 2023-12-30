#include <iostream>
#include <windows.h>

int n, avg = 0, ind_max = 0, ind_min = 0;
int* arr;

DWORD WINAPI min_max() {
    int max = INT_MIN, min = INT_MAX;
    for (int i = 0; i < n; i++) {
        if (arr[i] > max) {
            max = arr[i];
            ind_max = i;
        }
        Sleep(7);
        if (arr[i] < min) {
            min = arr[i];
            ind_min = i;
        }
        Sleep(7);
    }
    std::cout << "max value: " << max << "\nmin value: " << min << '\n';
    return 0;
}

DWORD WINAPI average() {
    for (int i = 0; i < n; i++) {
        avg += arr[i];
        Sleep(12);
    }
    avg /= n;
    std::cout << "\naverage value: " << avg << '\n';
    return 0;
}

int main() {
    try {
        std::cout << "array size: ";
        std::cin >> n;

        if (n == 0) throw std::exception("division by zero in average()");
        arr = new int[n];
        std::cout << "array elements: ";
        for (int i = 0; i < n; i++)
            std::cin >> arr[i];

        HANDLE hThread, hThread_;
        DWORD IDThread, IDThread_;

        hThread = CreateThread(NULL, 0, (LPTHREAD_START_ROUTINE)min_max,
            NULL, 0, &IDThread);
        if (hThread == NULL)
            return GetLastError();

        hThread_ = CreateThread(NULL, 0, (LPTHREAD_START_ROUTINE)average,
            NULL, 0, &IDThread_);
        if (hThread_ == NULL)
            return GetLastError();

        WaitForSingleObject(hThread, INFINITE);
        WaitForSingleObject(hThread_, INFINITE);

        CloseHandle(hThread_);
        CloseHandle(hThread);

        arr[ind_max] = avg;
        arr[ind_min] = avg;
        std::cout << "array element which was the maximum: " << arr[ind_max] << '\n'
            << "array element which was the minimum: " << arr[ind_min];
        delete[] arr;
    }
    catch (std::exception& e) {
        std::cout << e.what();
    }

    return 0;
}