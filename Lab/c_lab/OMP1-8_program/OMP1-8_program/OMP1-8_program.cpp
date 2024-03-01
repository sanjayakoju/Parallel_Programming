// OMP1-8_program.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <time.h>
#include <omp.h>
#define n 2000000

int i;
clock_t t;
int x[n];
int y[n];

int main()
{
	t = clock();
	omp_set_num_threads(4);
	#pragma omp parallel for schedule(static,1)
		for (i = 0;i < n;i++) {
			x[i] = i;
		}
	printf("Clock : %d\n", clock() - t);
	t = clock();
	omp_set_num_threads(4);
	#pragma omp parallel for
		for (i = 0; i < n; i++) {
			y[i] = n - i;
		}
	printf("Clock : %d\n", clock() - t);
}

