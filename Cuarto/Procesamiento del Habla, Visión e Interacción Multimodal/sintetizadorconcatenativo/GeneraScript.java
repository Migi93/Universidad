/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sintetizadorconcatenativo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguel Sánchez
 */
public class GeneraScript {

    private final String pathUno;
    private final String pathDos;

    public GeneraScript() {
        this.pathUno = "./CreaAudio.praat";
        this.pathDos = "./pitch.PitchTier";
    }

    public void generaScriptVoz(String ruta, ArrayList<String> difonos) {
        try {
            File antiguo = new File(ruta);
            if (antiguo.exists()) {
                antiguo.delete();
            }

            File file = new File(pathUno);
            BufferedWriter buffer;
            buffer = new BufferedWriter(new FileWriter(file));

            for (int i = 0; i < difonos.size(); i++) {
                buffer.write("Read from file: \".\\DifonosGrabados\\" + difonos.get(i).trim() + ".wav\" \n"); //.trim() lo que hace es quitar los espacios por delante y por detras
            }

            buffer.write("select Sound " + difonos.get(0).trim() + "\n");

            for (int i = 1; i < difonos.size(); i++) {
                buffer.write("plus Sound " + difonos.get(i).trim() + "\n");
            }

            buffer.write("Concatenate with overlap... 0.001 \n");
            buffer.write("Write to WAV file... " + ruta + " \n");
            buffer.close();

        } catch (IOException e) {
            System.out.println("Error con el fichero.");
        }
        try {
            Process p = Runtime.getRuntime().exec("Praat.exe --run " + pathUno); //el --run no es necesario //Necesario para que cree el audio ejecutando praat.exe
            p.waitFor();
        } catch (Exception e) {
            System.out.println("Error al ejecutar praat.exe y crear el audio deseado." + e.getMessage());
        }
    }

    /*public Path dondeEstaElFilename() {
        String filename = "meketrefe.wav";
        Path pathToFile = Paths.get(filename);
        return pathToFile.toAbsolutePath();
    }*/
    public void generarEntonacion(String ruta) throws InterruptedException {
        try {
            File antiguo = new File(pathDos);
            if (antiguo.exists()) {
                antiguo.delete();
                //System.out.println("Borrado");
            }
            //Tal y como pone en el README.txt facilitado por el profesor en clases extraemos el pitch track
            Process p = Runtime.getRuntime().exec("Praat.exe --run ./extraer-pitch-track.praat " + ruta + " pitch.PitchTier" + " 50 300");
            p.waitFor();
        } catch (IOException e) {
            System.out.println("Error con la extracion del pitch track. " + e.getMessage());
        }

        try {
            List<String> lineas = Files.readAllLines(Paths.get(pathDos), Charset.defaultCharset()); //Introducimos en la lista de String todo el fichero linea por linea 

            int inicioUno = 0;
            int inicioDos = 0;
            int finUno = 0;
            int finDos = 0;
            int cont = 0;

            for (int i = 0; i < lineas.size(); i++) {
                if (lineas.contains("size = ")) {
                    int numeroMuestras = Integer.parseInt(lineas.get(i).split("=")[1].trim());
                    inicioDos = Math.round(numeroMuestras * 0.75f);
                    finUno = (int) (numeroMuestras * 0.15f);
                    finDos = numeroMuestras;
                }
                if (lineas.contains("value = ")) {
                    String sValue = lineas.get(i).split("=")[1].trim();
                    double value = Double.parseDouble(sValue);
                    if (i >= inicioUno && i <= finUno) {
                        value -= 4 * (i + 10);
                    } else if (i >= inicioDos && i <= finDos) {
                        value += 6 * (cont + 1);
                        cont++;
                    }
                    lineas.set(i, lineas.get(i).replace(sValue, Double.toString(value)));
                }
            }
            File antiguo = new File(pathDos);
            if (antiguo.exists()) {
                antiguo.delete();
            }
            Files.write(Paths.get("mod.PitchTier"), lineas, Charset.defaultCharset());
        } catch (IOException ex) {
            System.out.println("Error al leer el archivo o al escribirlo" + ex.getMessage());
        }

        try {
            Process pp = Runtime.getRuntime().exec("Praat.exe --run ./reemplazar-pitch-track.praat " + ruta + " ./mod.PitchTier" + " " + ruta + " 50 300");
            pp.waitFor();
        } catch (IOException e) {
            System.out.println("Error al reemplazar el pitch track" + e.getMessage());
        }
    }
}
