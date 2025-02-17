package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Tablero {
    private char[][] matriz;//Una matriz de Posiciones y piezas
    private int filas = 5;
    private int columnas = 5;

    public Tablero(){
        //Ejemplo de tablero
//        matriz = new char[][]{
//                {'T', 'T', 'T', 'A', 'T'},
//                {'R', 'H', 'T', 'A', 'T'},
//                {'A', 'A', 'M', 'M', 'O'},
//                {'M', 'M', 'M', 'M', 'M'},
//                {'T', 'T', 'T', 'T', 'T'}};
    }

    // Dado un fichero, crea el tablero
    public Tablero(String fichero){
        matriz = new char[filas][columnas];

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            int fila = 0;
            while ((linea = br.readLine()) != null && fila < filas) {
                String[] values = linea.split(", ");
                for (int col = 0; col < values.length; col++) {
                    matriz[fila][col] = values[col].charAt(0);
                }
                fila++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Trasladar constructor parametrizado aquí
    public void importar(String fichero){


    }

    public void exportar(String rutaFichero){
        try {
            FileWriter fileWriter = new FileWriter(rutaFichero);
            for (char[] fila : matriz) {
                for (char c : fila) {
                    fileWriter.write(c+", ");
                }
                fileWriter.append('\n');
            }

            fileWriter.close();
            System.out.println("El fichero se ha escrito correctamente.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public void mostrar(){
        for (char[] fila : matriz) {
            for (char c : fila) {
                System.out.print(c+", ");
            }
            System.out.println();
        }
    }


    //Getters and Setters


    public char[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(char[][] matriz) {
        this.matriz = matriz;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }
}
