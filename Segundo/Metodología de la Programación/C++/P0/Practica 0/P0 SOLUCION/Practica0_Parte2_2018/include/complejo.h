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
        complejo(int vr, int vi=0);  //el 2º parametro es opcional, si no se indica vale 0
        int getr() const { return this->real; }
        int geti() const { return this->imaginario; }
        void set(int vr, int vi) { this->real = vr; this->imaginario = vi;}
        void set();
        void ver() const;
        complejo operator+(complejo c) const; //c1.operator+(c2) --> c1+c2
        complejo operator+(int i) const;      //c1.operator+(i)  --> c1+i

        complejo operator-() const; //c1.operator-() --> -c1

        complejo operator++(); //++c
        complejo operator++(int i); //c++

        bool operator==(complejo c) const; //c1.operator==(c2) --> c1==c2
        bool operator==(int i) const; //c1.operator==(8) --> c1==8

        operator int() const;
};

complejo operator+(int i, complejo c);  //operator+(i,c) --> i+c
bool operator==(int i, complejo c);  //operator==(8,c) --> 8==c
ostream & operator<<(ostream &s, const complejo &c);

#endif // COMPLEJO_H
