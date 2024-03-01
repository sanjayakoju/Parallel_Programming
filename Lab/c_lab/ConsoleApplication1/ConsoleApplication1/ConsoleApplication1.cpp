// ConsoleApplication1.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <omp.h>
#include <math.h>
#define size 100000000
int i, a[size];
clock_t t;

int main()
{
    t = clock();
    omp_set_num_threads(10); 
    #pragma omp parallel for
        for (i = 0; i < size;i++) {
            a[i] = rand()%100;
            double b = log(a[i]);
            double sqr = sqrt(a[i]);
            double sqrts = sin(a[i]);
            a[i] = sin(sqrt(log(a[i])));
            //printf("Logorithm of %d is : %lf\n", a[i], b);
            //printf("Square of %d is : %lf\n", a[i], sqr);
           // printf("Sin of %d is : %lf\n", a[i], sqrts);

        
        }

    printf("Elapsed Time: %d\n", clock() - t);
    
return 0;
}
