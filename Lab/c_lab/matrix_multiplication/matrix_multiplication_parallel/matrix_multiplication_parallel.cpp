// matrix_multiplication_parallel.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <omp.h>
#include <math.h>
#define n 1000

int a[n][n];
int b[n][n];
int c[n][n];
int threadCount = 4;

int i, j, k;
clock_t t;

int main()
{
    std::cout << "Sanjaya Koju\n";
    t = clock();
    printf("After Parallalized\n\n");
    printf("Before Inverted\n");

    for (i = 0;i < n;i++) {
        for (j = 0;j < n;j++) {
            a[i][j] = abs(rand() % 100);
            b[i][j] = abs(rand() % 100);
            c[i][j] = 0;
        }
    }

    omp_set_num_threads(threadCount);
    #pragma omp parallel for private(j,k)
    for (i = 0;i < n;i++) {
        for (j = 0;j < n;j++) {
            for (k = 0; k < n; k++) {
                c[i][j] += a[i][k] * b[k][j];
            }
        }
    }

    printf("Elapsed Time : %d\n", clock() - t);
    printf("After Inverted j,k loop Parallel\n");
    t = clock();

    omp_set_num_threads(threadCount);
    #pragma omp parallel for private(j,k)
    for (i = 0;i < n;i++) {
        for (k = 0;k < n;k++) {
            for (j = 0; j < n; j++) {
                c[i][j] += a[i][k] * b[k][j];
            }
        }
    }
    printf("Elapsed Time : %d\n", clock() - t);
}
