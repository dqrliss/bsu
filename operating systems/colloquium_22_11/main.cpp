#include <windows.h>
#include <fstream>
#include <string>
#include <vector>

std::string directory;
double global_sum = 0;

double calculate(int option, std::vector<double> v) {
	if (option == 1) {
		double result = 0;
		for (int i = 0; i < v.size(); i++)
			result += v[i];

		return result;
	}

	if (option == 2) {
		double result = 1;
		for (int i = 0; i < v.size(); i++)
			result *= v[i];

		return result;
	}

	if (option == 3) {
		double result = 0;
		for (int i = 0; i < v.size(); i++)
			result += v[i] * v[i];

		return result;
	}

	throw("something went wrong");
}

void thread(int i) {
	std::ifstream is(directory + "\\in_" + std::to_string(i + 1) + ".dat");

	int option;
	is >> option;

	std::vector <double> v;

	double num;
	while (is >> num)
		v.push_back(num);

	is.close();

	global_sum += calculate(option, v);
}

int main(int argc, char* argv[]) {

	directory = argv[1];
	int quantity_of_threads = atoi(argv[2]);

	HANDLE* hThread = new HANDLE[quantity_of_threads];
	DWORD* IDThread = new DWORD[quantity_of_threads];

	for (int i = 0; i < quantity_of_threads; i++)
		hThread[i] = CreateThread(nullptr, 0, (LPTHREAD_START_ROUTINE)thread, (LPVOID)i, 0, &IDThread[i]);

	WaitForMultipleObjects(quantity_of_threads, hThread, TRUE, INFINITE);

	std::ofstream os(directory + "\\out.dat");
	os << global_sum;
	os.close();

	for (int i = quantity_of_threads - 1; i > 0; i--)
		CloseHandle(hThread[i]);

	delete[] IDThread;
	delete[] hThread;

	return 0;
}