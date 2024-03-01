// numerical_integration.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <omp.h>
#define n  100000000

double sum, a, b, step;
int i;
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

    for (i = 1;i <= n;i++) {
        sum = sum + f(a + i * step);
    }
    sum = sum + (f(a) + f(b)) / 2;

    printf("Sum : %f\n", sum * step);
    printf("Elapsed Time : %d\n", clock() - t);
}

