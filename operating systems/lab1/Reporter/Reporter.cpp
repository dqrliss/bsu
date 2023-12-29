#include <iostream>
#include <fstream>
#include <algorithm>
#include <iomanip>
#include "..\Creator\header.h"

int main(int argc, char* argv[]) {
    std::ifstream is(argv[2], std::ios::binary | std::ios::in);
    is.seekg(0, std::ios::end);
    int quantity_of_employees = static_cast<int>(is.tellg() / sizeof(employee));
    employee* emp = new employee[quantity_of_employees];

    is.seekg(std::ios::beg);
    is.read(reinterpret_cast<char*>(emp), sizeof(employee) * quantity_of_employees);
    is.close();

    std::sort(emp, emp + quantity_of_employees, [](employee& emp_1, employee& emp_2) -> bool {
        return emp_1.num < emp_2.num; });

    std::ofstream os(argv[3], std::ios::out);
    os << "report on the file " << argv[2] << '\n';
    for (int i = 0; i < quantity_of_employees; i++)
        os << emp[i].num << std::setw(10) << emp[i].name << std::setw(10) << emp[i].hours
        << std::setw(10) << emp[i].hours * atoi(argv[1]) << '\n';
    os.close();
    delete[] emp;

    return 0;
}