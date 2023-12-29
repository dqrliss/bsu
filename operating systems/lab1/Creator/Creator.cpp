#include <fstream>
#include <iostream>
#include "header.h"

int main(int argc, char* argv[]) {
    employee emp;
    std::ofstream os(argv[2], std::ios::binary | std::ios::out | std::ios::trunc);
    for (int i = 0; i < atoi(argv[1]); i++) {
        std::cout << "employee's num: ";
        std::cin >> emp.num;
        std::cout << "employee's name: ";
        std::cin >> emp.name;
        std::cout << "employee's hours: ";
        std::cin >> emp.hours;
        std::cout << '\n';
        os.write((char*)&emp, sizeof(employee));
    }
    os.close();

    return 0;
}