#ifndef COMPLEJO_H
#define COMPLEJO_H

#include <iostream>

using namespace std;

class complejo
{
    private:
        int real;
        int imaginario;
    public:
        complejo(int vr, int vi);
        int getr() { return this->real; }
        int geti() { return this->imaginario; }
        void set(int vr, int vi) { this->real = vr; this->imaginario = vi;}
        void set();
        void ver();
        complejo operator+(complejo c); //c1.operator+(c2) --> c1+c2
        complejo operator+(int i);      //c1.operator+(i)  --> c1+i

        complejo operator-(); //c1.operator-() --> -c1

    friend complejo operator+(int i, complejo c);  //operator+(i,c) --> i+c
    friend ostream & operator<<(ostream &s, const complejo &c);
};

complejo operator+(int i, complejo c);  //operator+(i,c) --> i+c
ostream & operator<<(ostream &s, const complejo &c);
#endif // COMPLEJO_H
