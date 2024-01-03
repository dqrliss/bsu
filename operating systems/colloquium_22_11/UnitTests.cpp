#include "pch.h"
#include "CppUnitTest.h"
#include "vector"
#include "fstream"
#include <Windows.h>
#include <ctime>
#include <string.h>
#pragma warning(disable: 4996)

double calculate(int option, std::vector<double> v);

void thread(int i);

int main(int argc, char* argv[]);

using namespace Microsoft::VisualStudio::CppUnitTestFramework;

namespace Tests
{
	TEST_CLASS(calculateTest)
	{
	public:

		TEST_METHOD(Test1)
		{
			char c = '1';
			std::vector <double> v = { 0, 3.4, 1, 2.2, 4, 1.2, 2.8, 2.8, 3.6, 0, 1.8, 3.4, 0.4, 2.2, 0, 2.2, 2.6, 0.4 };
			Assert::IsTrue(abs(calculate(c, v) - 34.) < 1e-6);;
		}
		TEST_METHOD(Test2)
		{
			char c = '2';
			std::vector <double> v = { 0, 3.4, 1, 2.2, 4, 1.2, 2.8, 2.8, 3.6, 0, 1.8, 3.4, 0.4, 2.2, 0, 2.2, 2.6, 0.4 };
			Assert::IsTrue(abs(calculate(c, v) - 0) < 1e-6);
		}
		TEST_METHOD(Test3)
		{
			char c = '3';
			std::vector <double> v = { 0, 3.4, 1, 2.2, 4, 1.2, 2.8, 2.8, 3.6, 0, 1.8, 3.4, 0.4, 2.2, 0, 2.2, 2.6, 0.4 };
			Assert::IsTrue(abs(calculate(c, v) - 95.04) < 1e-6);
		}
	};
}