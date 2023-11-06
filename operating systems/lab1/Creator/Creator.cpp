#include <fstream>
#include <iostream>
#include "header.h"

int main(int argc, char* argv[])
{
    std::ofstream os(argv[2], std::ios::binary | std::ios::out);
    employee* emp = new employee[atoi(argv[1])];
    std::cout << "num, name and hours: ";
    for (int i = 0; i < atoi(argv[1]); ++i) std::cin >> emp[i].num >> emp[i].name >> emp[i].hours;
    os.write(reinterpret_cast<const char*>(emp), sizeof(employee) * atoi(argv[1]));
    os.close();
    return 0;
}
