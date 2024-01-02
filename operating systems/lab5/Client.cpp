#include <windows.h>
#include <iostream>
#include <string>
#include "../lab5/header.h"

int getting_note(int quantity_of_employees, std::string request, HANDLE& hNamedPipe) {

	char out_msg[40], in_msg[40];
	DWORD dwBytesWritten, dwBytesRead;
	employee emp;
	std::cout << "employee's id: ";
	std::cin >> emp.id;
	while (emp.id < 0 || emp.id >= quantity_of_employees) {
		std::cout << "non-existent id, try again: ";
		std::cin >> emp.id;
	}
	strcpy_s(out_msg, (request + " " + std::to_string(emp.id)).c_str());
	WriteFile(hNamedPipe, out_msg, sizeof(out_msg),
		&dwBytesWritten, (LPOVERLAPPED)NULL);
	ReadFile(hNamedPipe, in_msg, sizeof(in_msg),
		&dwBytesRead, (LPOVERLAPPED)NULL);
	std::cout << "\nnote from server\t" << in_msg << '\n';
	return emp.id;

}

std::string modifying_note(int id) {

	std::string out_msg;
	employee emp;
	std::cout << "\nnew name: ";
	std::cin >> emp.name;
	std::cout << "new hours: ";
	std::cin >> emp.hours;
	out_msg = std::to_string(id) + " " + emp.name + " "
		+ std::to_string(emp.hours);
	return out_msg;

}

void completing(HANDLE& hNamedPipe, char (&out_msg)[40]) {

	DWORD dwBytesWritten;
	std::string smth;
	std::cout << "\nsomething to confirm: ";
	std::cin >> smth;
	WriteFile(hNamedPipe, out_msg, sizeof(out_msg),
		&dwBytesWritten, (LPOVERLAPPED)NULL);

}

int main(int argc, char* argv[]) {
	DWORD dwBytesWritten, dwBytesRead;
	std::string request;
	char out_msg[40] = {};
	employee emp;
	int i = atoi(argv[1]);
	HANDLE hNamedPipe = CreateFile((L"\\\\.\\pipe\\some_pipe"
		+ std::to_wstring(i)).c_str(),
		GENERIC_READ | GENERIC_WRITE, FILE_SHARE_READ | FILE_SHARE_WRITE,
		(LPSECURITY_ATTRIBUTES)NULL, OPEN_EXISTING, 0, (HANDLE)NULL);
	if (hNamedPipe == INVALID_HANDLE_VALUE)
		return 1;

	while (true) {
		std::cout << "write modify, read or quit: ";
		std::cin >> request;

		if (request == "quit") {
			strcpy_s(out_msg, request.c_str());
			completing(hNamedPipe, out_msg);
			break;
		}

		if (request == "modify") {
			emp.id = getting_note(atoi(argv[2]), request, hNamedPipe);
			strcpy_s(out_msg, modifying_note(emp.id).c_str());
			completing(hNamedPipe, out_msg);
		}
		else if (request == "read") {
			getting_note(atoi(argv[2]), request, hNamedPipe);
			completing(hNamedPipe, out_msg);
		}
	}

	CloseHandle(hNamedPipe);
	ExitProcess(0);
}
