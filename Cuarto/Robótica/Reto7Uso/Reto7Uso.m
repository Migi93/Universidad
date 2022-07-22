%Clear para evitar sorpresas
clear all;
clc;
cla;

% Inicializar el mando
joy = vrjoystick(1); %obtenemos el mando, funcion de matlab
%inicializamos triangulo y cuadrado
last_triangle = false;
last_square = false;

%Constantes
PUNTO_PARTIDA = [150 30];
PUNTO_FINAL = [570 21];
global l
global radio_rueda
global camino
global pose
global punto
global MAPA
l=3.5; %distancia entre el centro(eje) y la rueda, lo usamos para el calculo de la odometria
radio_rueda=1;
camino = PUNTO_PARTIDA;
pose = [PUNTO_PARTIDA pi/2]'; %donde esta el robot
punto = PUNTO_PARTIDA; %* Rojo
h = 0.1; 
t0 = 0; %instante inicial
tf = 720; %tiempo de simulacion
t=0:h:tf;
ultimo_lh = 0;
incrementos_seguidos_lh = 0; %Controlamos si el coche se acerca o se aleja del *

%Constantes afinables para el algoritmo de seguimiento
KP = 0.5; 
MAX_V = 20;
A_DS = 25; %cuadrados de cuadrilla
K_DS = 5;

%Variables de estado
pilotoAutomatico = false;
k = 1; 
pose(:, k+1) = pose(:, k);
t(k+1) = 0;

%El mapa
MAPA = imread('cuadro6.bmp');
MAPA(1:end,:,:) = MAPA(end:-1:1,:,:); %Invertimos la imagen, porque la estaba leyende del reves

%Bucle
while k < length(t)
    k=k+1;

    leer_mando;
    
    %PARA GENERARA UN NUEVO CAMINO
    if triangle %Cuando pulsemos el triangulo entonces se pone automatico o manual si esta en automatica, se alterna
        pilotoAutomatico = ~pilotoAutomatico; %ponemos pilotoautomatico a true
        if pilotoAutomatico %Si esta piloto automatico activado, es decir, si es true, entra
            caminoOptimo = A_estrella_manual(MAPA, A_DS, pose(:, k)', PUNTO_FINAL); %calculamos el camino optimo
            camino = funcion_spline_cubica_varios_puntos(caminoOptimo(:, 1)', caminoOptimo(:, 2)', K_DS)'; %para suavizar el camino usamos el spline
        end
    end

    if square && ~pilotoAutomatico
        xlabel('Selecciona un punto para colocar el obstaculo','Color','black');
        but=0; %Para saber que hemos pulsado el click del raton a la hora de poner un obstaculo
        while (but ~= 1) %mientras no se pulse el click
            [px,py,but]=ginput(1); %seguimos leyendo el raton
        end
        %posicion del raton, quitando los decimales
        px = round(px);
        py = round(py);
        %Pintamos el cuadrado donde hagamos click -12 para la izquierda, 12
        %para la derecha, por tanto es de 25 x 25 porque el 0 contario
        for sx = -12:12
            for sy = -12:12
                MAPA(py+sy, px+sx, 1) = 0;
                MAPA(py+sy, px+sx, 2) = 0;
                MAPA(py+sy, px+sx, 3) = 0;
            end
        end
    end

    %Control del robot PARA SEGUIR EL NUEVO CAMINO
    if pilotoAutomatico
        if ~isempty(camino) %Por si el algoritmo no encuentra camino, para que no termine inesperadamente
            p = minima_distancia(camino, pose(:, k)'); %p es el punto donde se esta encontrando el vehiculo
            %Para saber la distancia entre el robot y el punto apuntado pot p
            while sqrt(power(pose(1, k)-camino(p, 1), 2) + power(pose(2, k)-camino(p, 2), 2)) < 5 && p < length(camino)
                p = p + 1; %sumamos porque si no no llegaria el bucle a su fin
                if p > length(camino) %Para no salirnos, lenght nos da la longitud del camino
                    p = length(camino);
                end
            end
    
            punto = camino(p, :);
            %Con delta y lh calculamos R
            delta = ((pose(1, k)-punto(1))*sin(pose(3, k))) - ((pose(2, k)-punto(2))*cos(pose(3, k))); %cateto opuesto
            lh = sqrt(power(pose(1, k)-punto(1), 2) + power(pose(2, k)-punto(2), 2)); %hipotenusa
        
            %Curvatura que nos conecta con el destino
            rho = (2*delta)/power(lh, 2);
        
            distancia_al_final = sqrt(power(pose(1, k)-camino(length(camino), 1), 2) + power(pose(2, k)-camino(length(camino), 2), 2));
            %calculamos la velocidad hacia el punto final de manera proporcional
            V = min(distancia_al_final*KP, MAX_V);
            %W es lo que hace darle mas potencia a una rueda u otra, es la velocidad angular
            W = rho*V;
        
            velocidad_derecha = (1/radio_rueda)*(V+W*l);
            velocidad_izquierda = (1/radio_rueda)*(V-W*l);
        else %Si no hay camino el coche no se mueve
            velocidad_derecha = 0;
            velocidad_izquierda = 0;
        end
    else %si no esta el piloto automatico, es que estamos manejando con el jostyck
        velocidad_derecha = pwrR*5; %pwrR es la potencia * 5
        velocidad_izquierda = pwrL*5;
        camino(:,end+1) = camino(:,end); %Actualizamos el camino conforme nos dirijimos con el jostick para que se pinte detras del robot
    end
    
    conduccion=[velocidad_derecha velocidad_izquierda];

    pose(:,k+1) = kuta_diferencial_mapa(t(k), pose(:,k), h, conduccion, MAPA);
end
