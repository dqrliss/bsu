#include <iostream>
#include <windows.h>
#include <fstream>
#include <conio.h>
#include <string>
#include <iomanip>
#include "../Creator/header.h"

int main() {
    char name_of_binary_file[15];
    std::cout << "name of binary file: ";
    std::cin >> name_of_binary_file;

    int quantity_of_employees;
    std::cout << "quantity of employees: ";
    std::cin >> quantity_of_employees;

    char cmd_line_args_c[100]; //to creator
    wsprintfA(cmd_line_args_c, "C:\\Users\\dq\\source\\repos\\lab1\\Main\\x64\\Debug\\Creator.exe %d %s",
        quantity_of_employees, name_of_binary_file);
    
    STARTUPINFO si;
    PROCESS_INFORMATION piApp;
    ZeroMemory(&si, sizeof(STARTUPINFO));
    if (!CreateProcessA(nullptr, cmd_line_args_c, nullptr, nullptr, FALSE,
        CREATE_NEW_CONSOLE, nullptr, nullptr, (LPSTARTUPINFOA)&si, &piApp)) {
        _cputs("The new process is not created.\n");
        _cputs("Check a name of the process.\n");
        _cputs("Press any key to finish.\n");
        _getch();
        return 0;
    }

    WaitForSingleObject(piApp.hProcess, INFINITE);
    CloseHandle(piApp.hThread);
    CloseHandle(piApp.hProcess);

    employee emp;
    std::ifstream is;
    is.open(name_of_binary_file, std::ios::binary | std::ios::in);
    std::cout << "\nbinary file contents:\n";
    for (int i = 0; i < quantity_of_employees; i++) {
        is.read((char*)&emp, sizeof(employee));
        std::cout << emp.num << std::setw(10) << emp.name << std::setw(10) << emp.hours << "\n";
    }
    is.close();

    char name_of_report_file[15];
    std::cout << "\nname of report file: ";
    std::cin >> name_of_report_file;

    int hourly_wage;
    std::cout << "hourly wage: ";
    std::cin >> hourly_wage;
    std::cout << '\n';

    char cmd_line_args_r[100]; //to reporter
    wsprintfA(cmd_line_args_r, "C:\\Users\\dq\\source\\repos\\lab1\\Main\\x64\\Debug\\Reporter.exe %d %s %s",
        hourly_wage, name_of_binary_file, name_of_report_file);

    STARTUPINFO si_;
    PROCESS_INFORMATION piApp_;
    ZeroMemory(&si_, sizeof(STARTUPINFO));
    if (!CreateProcessA(nullptr, cmd_line_args_r, nullptr, nullptr, FALSE,
        CREATE_NEW_CONSOLE, nullptr, nullptr, (LPSTARTUPINFOA)&si_, &piApp_)) {
        _cputs("The new process is not created.\n");
        _cputs("Check a name of the process.\n");
        _cputs("Press any key to finish.\n");
        _getch();
        return 0;
    }

    WaitForSingleObject(piApp_.hProcess, INFINITE);
    CloseHandle(piApp_.hThread);
    CloseHandle(piApp_.hProcess);

    is.open(name_of_report_file, std::ios::in);
    std::string line;
    for (int i = 0; i < quantity_of_employees + 1; i++) {
        getline(is, line);
        std::cout << line << '\n';
    }
    is.close();

    return 0;
}