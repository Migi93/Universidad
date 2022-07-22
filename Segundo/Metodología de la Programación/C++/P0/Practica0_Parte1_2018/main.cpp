#include <iostream>
#include "complejo.h"
using namespace std;

int main()
{
    cout << "Hello world!" << endl;

    complejo c1(7,2), c2(2,5), c3(0,1), c4(0,0);

    cout << "####################\n";
    cout << "parte real: " << c1.getr() << endl;
    cout << "parte imaginaria: " << c1.geti() << endl;
    c1.set(5,4);
    c1.ver();
    c1.set();
    c1.ver();

    cout << "####################\n";
    c2.ver();
    c3.ver();
    c4=c2+c3;

    c4.ver();
    c4=c4+8;
    c4.ver();
    c4=-3+c3;
    c4.ver();

    c4=-c2;
    c4.ver();

    return 0;
}
