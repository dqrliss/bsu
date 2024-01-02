#include <windows.h>
#include <iostream>
#include <string>
#include "header.h"
#include <fstream>

std::ofstream os;
std::ifstream is;
std::fstream ios;
char name_of_binary_file[15];
int quantity_of_processes, quantity_of_employees;
HANDLE* hSemaphores;

std::string giving_note(int emp_id, HANDLE& pipa) {

	char in_msg[40], out_msg[40];
	DWORD dwBytesWrite, dwBytesRead;
	employee emp;
	std::string str;
	is.open(name_of_binary_file, std::ios::binary | std::ios::in);
	is.seekg(emp_id * sizeof(employee));
	is.read((char*)&emp, sizeof(employee));
	is.close();
	strcpy_s(out_msg, ("id: " + std::to_string(emp.id) + ", name: " + emp.name
		+ ", hours: " + std::to_string(emp.hours)).c_str());
	WriteFile(pipa, out_msg, sizeof(out_msg),
		&dwBytesWrite, (LPOVERLAPPED)NULL);
	ReadFile(pipa, in_msg, sizeof(in_msg),
		&dwBytesRead, (LPOVERLAPPED)NULL);
	return in_msg;

}

void modifying_note(std::string str, int emp_id) {

	employee emp;
	emp.id = emp_id;
	str = str.substr(str.find(' ') + 1);
	strcpy_s(emp.name, (str.substr(0, str.find(' '))).c_str());
	emp.hours = stod(str.substr(str.find(' ') + 1));
	ios.open(name_of_binary_file, std::ios::binary | std::ios::out | std::ios::in);
	ios.seekp(emp.id * sizeof(employee));
	ios.write((char*)&emp, sizeof(employee));
	ios.close();

}

int thread(HANDLE pipa) {
	DWORD dwBytesRead;
	char in_msg[40];
	std::string str;
	int emp_id;

	if (!ConnectNamedPipe(pipa, (LPOVERLAPPED)NULL))
		return GetLastError();

	while (true) {
		if (!ReadFile(pipa, in_msg, sizeof(in_msg),
			&dwBytesRead, (LPOVERLAPPED)NULL))
			return GetLastError();

		if (in_msg[0] == 'q') return 0;

		str = in_msg;
		emp_id = stoi(str.substr(str.find(' ') + 1));
		if (in_msg[0] == 'm') {
			for (int i = 0; i < quantity_of_processes; i++)
				WaitForSingleObject(hSemaphores[emp_id], INFINITE);

			strcpy_s(in_msg, giving_note(emp_id, pipa).c_str());
			str = in_msg;
			modifying_note(str, emp_id);

			ReleaseSemaphore(hSemaphores[emp_id], quantity_of_processes, NULL);
		}
		else {
			WaitForSingleObject(hSemaphores[emp_id], INFINITE);

			giving_note(emp_id, pipa);

			ReleaseSemaphore(hSemaphores[emp_id], 1, NULL);
		}
	}
}

int main() {
	
	std::cout << "name of binary file: ";
	std::cin >> name_of_binary_file;

	std::cout << "quantity of employees: ";
	std::cin >> quantity_of_employees;

	employee emp;
	os.open(name_of_binary_file, std::ios::binary | std::ios::out | std::ios::trunc);
	for (int i = 0; i < quantity_of_employees; i++) {
		std::cout << "\nemployee's id: " << i;
		emp.id = i;
		std::cout << "\nemployee's name: ";
		std::cin >> emp.name;
		std::cout << "employee's hours: ";
		std::cin >> emp.hours;
		os.write((char*)&emp, sizeof(employee));
	}
	os.close();

	is.open(name_of_binary_file, std::ios::binary | std::ios::in);
	std::cout << "\nbinary file contents:\n";
	for (int i = 0; i < quantity_of_employees; i++) {
		is.read((char*)&emp, sizeof(employee));
		std::cout << emp.id << " " << emp.name << " " << emp.hours << '\n';
	}
	is.close();

	std::cout << "\nquantity of client processes: ";
	std::cin >> quantity_of_processes;

	HANDLE* hNamedPipes = new HANDLE[quantity_of_processes];
	for (int i = 0; i < quantity_of_processes; i++) {
		hNamedPipes[i] = CreateNamedPipe((L"\\\\.\\pipe\\some_pipe"
			+ std::to_wstring(i)).c_str(),
			PIPE_ACCESS_DUPLEX, PIPE_TYPE_MESSAGE | PIPE_WAIT,
			PIPE_UNLIMITED_INSTANCES, 0, 0, INFINITE, 0);
		if (hNamedPipes[i] == NULL)
			return GetLastError();
	}

	hSemaphores = new HANDLE[quantity_of_employees];
	for (int i = 0; i < quantity_of_employees; i++) {
		hSemaphores[i] = CreateSemaphore(NULL, quantity_of_processes,
			quantity_of_processes, std::to_wstring(i).c_str());
		if (hSemaphores[i] == NULL)
			return GetLastError();
	}

	STARTUPINFO* si = new STARTUPINFO[quantity_of_processes];
	PROCESS_INFORMATION* pi = new PROCESS_INFORMATION[quantity_of_processes];

	char сmd_line_args[100];
	for (int i = 0; i < quantity_of_processes; i++) {
		wsprintfA(сmd_line_args,
			"C:\\Users\\dq\\source\\repos\\lab5\\x64\\Debug\\lab5_continuation.exe %d %d",
			i, quantity_of_employees);

		ZeroMemory(&si[i], sizeof(STARTUPINFO));
		si[i].cb = sizeof(STARTUPINFO);
		ZeroMemory(&pi[i], sizeof(PROCESS_INFORMATION));
		if (!CreateProcessA(nullptr, сmd_line_args, NULL, NULL, 0,
			CREATE_NEW_CONSOLE, NULL, NULL, (LPSTARTUPINFOA)&si[i], &pi[i])) {
				std::cout << "process hasn't created" << '\n';
				return GetLastError();
		}
	}

	DWORD* IDThreads = new DWORD[quantity_of_processes];
	HANDLE* hThreads = new HANDLE[quantity_of_processes];
	for (int i = 0; i < quantity_of_processes; i++) {
		hThreads[i] = CreateThread(NULL, 0, (LPTHREAD_START_ROUTINE)thread,
			hNamedPipes[i], 0, &IDThreads[i]);
		if (hThreads[i] == NULL)
			return GetLastError();
	}

	WaitForMultipleObjects(quantity_of_processes, hThreads, TRUE, INFINITE);

	is.open(name_of_binary_file, std::ios::binary | std::ios::in);
	std::cout << "\nresulted binary file contents:\n";
	for (int i = 0; i < quantity_of_employees; i++) {
		is.read((char*)&emp, sizeof(employee));
		std::cout << emp.id << " " << emp.name << " " << emp.hours << '\n';
	}
	is.close();

	std::string smth;
	std::cout << "\nenter something to finish: ";
	std::cin >> smth;

	for (int i = quantity_of_processes - 1; i > -1; i--)
		CloseHandle(hThreads[i]);

	delete[] hThreads;
	delete[] IDThreads;

	for (int i = quantity_of_processes - 1; i > -1; i--) {
		CloseHandle(pi[i].hThread);
		CloseHandle(pi[i].hProcess);
	}
	delete[] pi;
	delete[] si;
	for (int i = quantity_of_employees - 1; i > -1; i--)
		CloseHandle(hSemaphores[i]);
	delete[] hSemaphores;

	for (int i = quantity_of_processes - 1; i > -1; i--)
		CloseHandle(hNamedPipes[i]);
	delete[] hNamedPipes;

	return 0;
}
