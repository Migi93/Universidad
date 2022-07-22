
pwrL = 0;
pwrR = 0;
switch pov(joy) %Pov nos da el angulo hacia donde apunta el jostyck %joy es la psp
    case 0
        pwrL = 1;
        pwrR = 1;
    case 90
        pwrL = 1;
        pwrR = 0.5;
    case 270
        pwrL = 0.5;
        pwrR = 1;
    case 45
        pwrL = 1;
        pwrR = 0.75;
    case 315
        pwrL = 0.75;
        pwrR = 1;
end

if button(joy, 1) %Boton de la psp X (acelerar)
    pwrL = pwrL * 2;
    pwrR = pwrR * 2;
elseif button(joy, 2) %Circulo (ir mas lento)
    pwrL = pwrL * 0.5;
    pwrR = pwrR * 0.5;
end

new_triangle = button(joy, 4); %con la funcion button detectar que se ha pulsado una vez, indepentiendemente de que se deje pulsado
triangle = new_triangle && ~(new_triangle == last_triangle);
last_triangle = new_triangle;

new_square = button(joy, 3); %Lo mismo con el cuadrado
square = new_square && ~(new_square == last_square);
last_square = new_square;
