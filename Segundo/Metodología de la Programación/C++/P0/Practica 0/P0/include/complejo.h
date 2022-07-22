#ifndef COMPLEJO_H
#define COMPLEJO_H


class complejo
{
    public:
        complejo(int r, int i);

        int getr() { return real; }
        void setr(int val) { real = val; }
        int geti() { return imaginario; }
        void seti(int val) { imaginario = val; }
        void set(int r, int i);
        void set();
        void ver();
        complejo operator +(complejo a, complejo b);
        complejo operator -(complejo c, complejo d);

    private:
        int real;
        int imaginario;
};

#endif // COMPLEJO_H
