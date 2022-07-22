#include <iostream>
#include "complejo.h"

using namespace std;

int main()
{
    complejo a(1,2), b(3,4) ,c(1,-3), d(-1,-2), e(6,2);
    a.set(a.getr()+1,-1*a.geti());
    a.ver(); cout << endl; //a = 2-2i
    return 0;
}
