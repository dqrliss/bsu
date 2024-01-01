#include <iostream>
#include <windows.h>
#include <fstream>
#include <conio.h>
#include <string>

int main() {
    char name_of_binary_file[15];
    std::cout << "name of binary file: ";
    std::cin >> name_of_binary_file;

    int number_of_notes;
    std::cout << "number of notes: ";
    std::cin >> number_of_notes;
    
    std::ifstream is;
    std::ofstream os(name_of_binary_file, std::ios::binary | std::ios::out | std::ios::trunc);
    os.close();

    int number_of_processes;
    std::cout << "number of processes: ";
    std::cin >> number_of_processes;

    HANDLE hSemaphore1 = CreateSemaphore(NULL, 0, number_of_notes, L"sema1");
    //if
    HANDLE hSemaphore2 = CreateSemaphore(NULL, number_of_notes, number_of_notes, L"sema2");

    HANDLE hMutex = CreateMutex(NULL, FALSE, L"mutex");
    if (hMutex == NULL) {
        std::cout << "mutex hasn't created" << '\n';
        return GetLastError();
    }

    HANDLE* hEvent = new HANDLE[number_of_processes];

    STARTUPINFO* si = new STARTUPINFO[number_of_processes];
    PROCESS_INFORMATION* pi = new PROCESS_INFORMATION[number_of_processes];

    char cmd_line_args[100];
    for (int i = 0; i < number_of_processes; i++) {
        wsprintfA(cmd_line_args,
            "C:\\Users\\dq\\source\\repos\\lab4\\Receiver\\x64\\Debug\\Sender.exe %s %d %d",
            name_of_binary_file, i, number_of_notes);

        hEvent[i] = CreateEvent(NULL, FALSE, FALSE, std::to_wstring(i).c_str());

        ZeroMemory(&si[i], sizeof(STARTUPINFO));
        si[i].cb = sizeof(STARTUPINFO);
        ZeroMemory(&pi[i], sizeof(PROCESS_INFORMATION));

        if (!CreateProcessA(nullptr, cmd_line_args, NULL, NULL, FALSE,
            CREATE_NEW_CONSOLE, NULL, NULL, (LPSTARTUPINFOA)&si[i], &pi[i])) {
                std::cout << "process hasn't created" << '\n';
                return GetLastError();
        }
    }

    WaitForMultipleObjects(number_of_processes, hEvent, TRUE, INFINITE);

    int number, size;
    char message[20];
    char* str;
    while (true) {
        std::cout << "enter '1' to read message or '2' to interrupt work: ";
        std::cin >> number;
        if (number == 1) {

            WaitForSingleObject(hSemaphore1, INFINITE);

            WaitForSingleObject(hMutex, INFINITE);
            ReleaseSemaphore(hSemaphore2, 1, NULL);
            is.open(name_of_binary_file, std::ios::binary | std::ios::in);
            is.read(reinterpret_cast<char*>(&message), sizeof(char[20]));
            std::cout << message << '\n';
            is.seekg(0, std::ios::end);
            size = is.tellg();
            if (size == 20) {
                is.close();

                os.open(name_of_binary_file, std::ios::binary | std::ios::out | std::ios::trunc);
                os.close();
            }
            else {
                is.seekg(20);
                str = new char[size - 20];
                is.read(str, size - 20);
                is.close();

                os.open(name_of_binary_file, std::ios::binary | std::ios::out); //trunc?
                os.write(str, size - 20);
                os.close();
                delete[] str;
            }
            ReleaseMutex(hMutex);

        }
        else if (number == 2) {
            for (int i = number_of_processes - 1; i > -1; i--) {
                CloseHandle(pi[i].hThread);
                CloseHandle(pi[i].hProcess);
                CloseHandle(hEvent[i]);
            }
            delete[] pi;
            delete[] si;
            delete[] hEvent;
            CloseHandle(hMutex);
            CloseHandle(hSemaphore2);
            CloseHandle(hSemaphore1);
            return 0;
        }
    }
}