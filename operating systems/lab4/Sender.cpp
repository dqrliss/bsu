#include <fstream>
#include <windows.h>
#include <iostream>
#include <string>
#include <conio.h>

int main(int argc, char* argv[]) {
    std::ofstream os;
    std::ifstream is;

    HANDLE hSemaphore1 = OpenSemaphore(SEMAPHORE_ALL_ACCESS, FALSE, L"sema1"),
        hSemaphore2 = OpenSemaphore(SEMAPHORE_ALL_ACCESS, FALSE, L"sema2");

    HANDLE hMutex = OpenMutex(SYNCHRONIZE, FALSE, L"mutex");
    if (hMutex == NULL) {
        std::cout << "mutex hasn't opened" << '\n';
        return GetLastError();
    }

    HANDLE hEvent = OpenEvent(EVENT_ALL_ACCESS, FALSE, std::to_wstring(atoi(argv[2])).c_str());
    SetEvent(hEvent);

    int number;
    char message[20];
    while (true) {
        std::cout << "enter '1' to send message or '2' to interrupt work: ";
        std::cin >> number;
        if (number == 1) {

            WaitForSingleObject(hSemaphore2, INFINITE);

            WaitForSingleObject(hMutex, INFINITE);
            ReleaseSemaphore(hSemaphore1, 1, NULL);
            std::cout << "enter message: ";
            std::cin >> message;
            os.open(argv[1], std::ios::binary | std::ios::out | std::ios::app);
            os.write(reinterpret_cast<const char*>(&message), sizeof(char[20]));
            os.close();
            ReleaseMutex(hMutex);

        }
        else if (number == 2) {
            CloseHandle(hEvent);
            CloseHandle(hMutex);
            CloseHandle(hSemaphore2);
            CloseHandle(hSemaphore1);
            ExitProcess(0);
        }
    }
}