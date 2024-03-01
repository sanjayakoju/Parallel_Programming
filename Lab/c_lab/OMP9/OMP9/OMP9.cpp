// OMP9.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <omp.h>
#define size 100000000

int x[size], i;
clock_t t;
int threadCount = 2;
int threadId;

int main()
{
    std::cout << "Hello World!\n";
    
    for (i = 0;i < size;i++) {
        x[i] = abs(rand() % 100);
    }
    t = clock();

    #pragma omp parallel for private(i)
    for (i = 0;i < size;i++) {
        threadId = omp_get_thread_num();
        if (threadId == 0) {
            x[0]++;
        }
        if (threadId == 1) {
            x[1]++;
        }
    }
    printf("Thread %d Elapsed Time : %d while increment x[0] and x[1]\n\n", threadCount, clock() - t);
    t = clock();

    #pragma omp parallel for private (i)
    for (i = 0;i < size;i++) {
        threadId = omp_get_thread_num();
        if (threadId == 0) {
            x[100]++;
        }
    }
    printf("Thread %d Elapsed Time : %d while increment x[100]\n", threadCount, clock() - t);
    
}
