#include <iostream> // std::cout, std::fixed
#include <fstream> // para trabajar con ficheros
#include <iomanip> // std::setprecision
#include <cstdlib> // system
#include "complejo.h" // definicion de la clase complejo

using namespace std;

int main()
{
    /*
    cout << "Hello world!" << endl;

    complejo c1(7,2), c2(2,5), c3(0,1), c4(0,0);

    cout << "####################\n";
    cout << "parte real: " << c1.getr() << endl;
    cout << "parte imaginaria: " << c1.geti() << endl;
    c1.set(5,4);
    c1.ver();
    c1.set();
    c1.ver();

    cout << "...................\n";
    cout << "c1 vale " << c1 << "\n";
    ofstream salida("prueba.txt");
    if (!salida.fail()) {
      salida << "c1 vale " << c1 << "\n";
      salida << "c2 vale " << c2 << "\n";
      salida << "c3 vale " << c3 << "\n";
    }
    salida.close();
    cout << "...................\n";

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


    if (c1==c2)
      cout << "c1 y c2 son iguales \n";
    else
      cout << "c1 y c2 son distintos \n";
    if (c1==8)
      cout << c1 << " es igual a " << 8 << endl;
    else
      cout << c1 << " NO es igual a " << 8 << endl;
    return 0;
   */

  complejo a(1,2), b(3), c(a), e(6,2); //b debe ser  3+0i, c es 1+2i
  const complejo d(-1,-2);
  cout << fixed << setprecision(2); //mostrar 2 (setprecision) decimales (fixed)

  a.set(a.getr()+1,-1*a.geti());
             a.ver(); cout << endl; //a = 2-2i
  b=5+c+a;   b.ver(); cout << endl; //b = 8+0i
  c=5+c+a+2; c.ver(); cout << endl; //c = 10+0i
  c=-c;      c.ver(); cout << endl; //c =-10+0i
  c=d+1;     c.ver(); cout << endl; //c = 0-2i
  c=d+c;     c.ver(); cout << endl; //c =-1-4i


  ++a;       cout << a << endl;     //a = 3-2i
  a++;       cout << b << endl;     //a = 4-2i


  int r = (int)a;              //r = (int) a devuelve la parte real de a (4)

  e.set(8,0);                       //e = 8+0i
  if (e==b)
   cout << "e y b son iguales \n";
  else
   cout << "e y b son distintos \n";
  if (e==8)
   cout << e << " es igual a " << 8 << endl;

   b=++a;
  c=b++;
  cout << a << ", " << b << ", " << c << endl; //a=5-2i, b=6-2i, c=6-2i, c=5-2i

  system("PAUSE");  return EXIT_SUCCESS;

}
