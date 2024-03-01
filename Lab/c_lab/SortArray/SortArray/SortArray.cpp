// SortArray.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <omp.h>
#include <math.h>
#define n 40000000
int i,x[n], y[n],z[2*n];
clock_t t;
int main()
{
    printf("Hello World!!");
    t = clock();
    int c[sizeof(n) + sizeof(n)];
    omp_set_num_threads(2);
#pragma omp parallel for
    for (i = 0;i < n;i++) {
        x[i] = i * 2;
        y[i] = 2 * i + 1;
    }
    for (i = 0;i < n;i++) {
        z[i] = x[i];
       // printf("Z index %d is : %d\n ", i, z[i]);
    }
    for (i = n;i < n*2;i++) {
        z[i] = y[i];
       // printf("Z index %d is : %d\n ", i, z[i]);
    }

    printf("\nElapsed Time: %d\n", clock() - t);
}

