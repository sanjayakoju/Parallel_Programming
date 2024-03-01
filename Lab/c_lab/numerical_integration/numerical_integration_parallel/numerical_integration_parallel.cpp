// numerical_integration_parallel.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <omp.h>
#define n  100000000

double sum, a, b;
double step;
int i, threadCount = 4, threadId, chunck;
clock_t t;

double f(double t) {
    return sqrt(4 - t * t);
}

int main()
{
    std::cout << "Hello World!\n";
    a = 0.0;
    b = 2.0;
    step = (b - a) / n;
    sum = 0.0;
    t = clock();

    chunck = n / threadCount;

    int lastChunck = chunck + n % threadCount;

    omp_set_num_threads(threadCount);
    #pragma omp parallel private(i)
        {
        int chunckSize = chunck;
        int threadId = omp_get_thread_num();

        if (threadId == threadCount - 1) {
            chunckSize = lastChunck;
        }

        double localSum = 0.0;
            for (i = (threadId * chunck) +1;i <= threadId  * chunck + chunckSize;i++) {
                localSum += f(a + i * step);
            }

            #pragma omp critical
            {
                sum += localSum;
            }
        }

    sum = sum + (f(a) + f(b)) / 2;
    printf("Sum : %f\n", sum * step);
    printf("Elapsed Time : %d\n", clock() - t);
}
