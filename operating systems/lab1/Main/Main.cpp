#include <iostream>
#include <windows.h>
#include <fstream>
#include <conio.h>
#include <string>

int main()
{
    char bin[10]; int size;
    std::cout << "name of binary file and number of notes: "; std::cin >> bin >> size;

    char for_creator[100];
    wsprintfA(for_creator, "C:\\Users\\dq\\source\\repos\\lab1\\Main\\x64\\Debug\\Creator.exe %d %s", size, bin);

    STARTUPINFO si;
    PROCESS_INFORMATION piApp;
    ZeroMemory(&si, sizeof(STARTUPINFO));
    if (!CreateProcessA(nullptr, for_creator, nullptr, nullptr, FALSE,
        CREATE_NEW_CONSOLE, nullptr, nullptr, (LPSTARTUPINFOA)&si, &piApp)) {
        _cputs("The new process is not created.\n");
        _cputs("Check a name of the process.\n");
        _cputs("Press any key to finish.\n");
        _getch();
        return 0;
    }
    _cputs("The Creator process is created.\n");
    WaitForSingleObject(piApp.hProcess, INFINITE);
    CloseHandle(piApp.hThread);
    CloseHandle(piApp.hProcess);

    char txt[10]; int cost;
    std::cout << "name of report file and payment by hour: ";
    std::cin >> txt >> cost;

    char for_reporter[100];
    wsprintfA(for_reporter, "C:\\Users\\dq\\source\\repos\\lab1\\Main\\x64\\Debug\\Reporter.exe %d %s %s", cost, bin, txt);

    ZeroMemory(&si, sizeof(STARTUPINFO));
    if (!CreateProcessA(nullptr, for_reporter, nullptr, nullptr, FALSE,
        CREATE_NEW_CONSOLE, nullptr, nullptr, (LPSTARTUPINFOA)&si, &piApp)) {
        _cputs("The new process is not created.\n");
        _cputs("Check a name of the process.\n");
        _cputs("Press any key to finish.\n");
        _getch();
        return 0;
    }
    _cputs("The Reporter process is created.\n");
    WaitForSingleObject(piApp.hProcess, INFINITE);
    CloseHandle(piApp.hThread);
    CloseHandle(piApp.hProcess);

    std::ifstream is(txt);
    std::string line;
    while (!is.eof())
    {
        getline(is, line);
        std::cout << line << '\n';
    }
    return 0;
}
