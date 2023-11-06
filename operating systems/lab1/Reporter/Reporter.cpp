#include <iostream>
#include <fstream>
#include "..\Creator\header.h"

int main(int argc, char* argv[])
{
    employee emp;
    std::ifstream is(argv[2], std::ios::binary | std::ios::in);
    std::ofstream os(argv[3]);

    os << "report on file " << argv[2] << ":\n";
    while (is.read((char*)&emp, sizeof(employee))) os << emp.num << "\t" << emp.name << "\t" << emp.hours << "\t" << atoi(argv[1]) * emp.hours << "\n";
    return 0;
}