// matrix_vector_multiplication.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <stdio.h>
#include <time.h>
#include <omp.h>
#include <math.h>
#define n 4
double A[n], B[n][n], C[n];
int i, j;
int threadCount = 4;

int main() {

    for (i = 0;i < n;i++) {
        for (j = 0;j < n;j++) {
            B[i][j] = (i + 1.0) * (j + 1.0);
        }
        C[i] = i + 2.0;
    }

    omp_set_num_threads(threadCount);
        #pragma omp parallel for private(j)
        for (i = 0;i < n;i++) {
            A[i] = 0.0;
            for (j = 0;j < n;j++) {
                A[i] += B[i][j] * C[j];
            }
        }

    for (i = 0;i < n;i++) {
        printf("%f\n", A[i]);
    }
}



