#include "pch.h"
#include "CppUnitTest.h"
#include "vector"
#include "fstream"
#include <windows.h>
#include <ctime>
#include <string.h>
#pragma warning(disable: 4996)

double calculate(int option, std::vector<double> v);

void thread(int i);

int main(int argc, char* argv[]);

using namespace Microsoft::VisualStudio::CppUnitTestFramework;

namespace Tests {
	TEST_CLASS(calculateTest) {
	public:

		TEST_METHOD(Test1) {
			int option = 1;
			std::vector <double> v = { 1.4, 2.8, 4.5, 6.1, 0.2, 0.9, 9.1, 0, 2.2, 5, 3.3, 5.3, 7.1, 0.9, 2.5, 1.4, 9.0 };
			Assert::IsTrue(abs(calculate(option, v) - 61.7) < 1e-6);;
		}
		TEST_METHOD(Test2) {
			int option = 2;
			std::vector <double> v = { 3.5, 1, 8.2, 0, 0.3, 2.7, 3.8, 7.3, 3.3, 0, 3.9, 6.4, 4.2, 8.2 };
			Assert::IsTrue(abs(calculate(option, v) - 0) < 1e-6);
		}
		TEST_METHOD(Test3) {
			int option = 3;
			std::vector <double> v = { 2.2, 0.4, 1.8, 0, 2.6, 1.2, 0.4, 3.6, 0, 1, 3.4, 3.4, 2.2, 0, 2.2, 4, 2.8 };
			Assert::IsTrue(abs(calculate(option, v) - 87.2) < 1e-6);
		}
	};
}
