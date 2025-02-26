package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static java.lang.Math.abs;

public class Tablero {
    private char[][] matriz;//Una matriz de Posiciones y piezas
    private int filas = 5;
    private int columnas = 5;

    private Posicion posRey;
    private Posicion posHueco;
    private Posicion posObjetivo;

    private double distanciaEuclideaRey;
    private int distanciaManhattanRey;

    private double distanciaEuclideaHueco;
    private int distanciaManhattanHueco;


    // Constructor por defecto
    public Tablero(){
        posRey=new Posicion();
        posHueco=new Posicion();
        posObjetivo=new Posicion();
        //Ejemplo de tablero
//        matriz = new char[][]{
//                {'T', 'T', 'T', 'A', 'T'},
//                {'R', 'H', 'T', 'A', 'T'},
//                {'A', 'A', 'M', 'M', 'O'},
//                {'M', 'M', 'M', 'M', 'M'},
//                {'T', 'T', 'T', 'T', 'T'}};
    }

    // Constructor parametrizado
    // Dado un fichero, crea el tablero y completa las posiciones
    public Tablero(String fichero){
        matriz = new char[filas][columnas];
        char letra;
        posRey=new Posicion();
        posHueco=new Posicion();
        posObjetivo=new Posicion();

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            int fila = 0;
            while ((linea = br.readLine()) != null && fila < filas) {
                String[] values = linea.split(", ");
                for (int col = 0; col < values.length; col++) {
                    letra=values[col].charAt(0);// Obtengo la letra
                    matriz[col][fila] = letra; //La guardo en la matriz

                    // Dependiendo de la letra guardo su posición

                    //Coordenadas cartesianas (suponiendo que hacia abajo es positivo y a la derecha es positivo)
                    //Abcisas: X
                    //Ordenadas: Y
                    //Fuente: https://www.youtube.com/watch?v=Q1-KWwcbLzg

                    switch (letra){
                        case 'R':
                            posRey.setXY(col,fila);
                            break;

                        case 'H':
                            posHueco.setXY(col,fila);
                            break;

                        case 'O':
                            posObjetivo.setXY(col,fila);
                            break;

                    }

                }
                fila++;
            }
            //Cuando ha terminado de rellenar la matriz guardo las distancias euclideas y manhattan
            calcularDistanciaEuclideaRey();
            calcularDistanciaManhattanRey();

            calcularDistanciaEuclideaHueco();
            calcularDistanciaManhattanHueco();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Trasladar constructor parametrizado aquí
//    public void importar(String fichero){
//
//
//    }

    //Útiles
// Distancia Euclidea: dadas dos posiciones, calcula la distancia en línea recta
    //Fuente: https://manolohidalgo.com/ejercicio-12-distancia-entre-dos-puntos/
    //distanciaEuclidea
    //por el teorema de pitagoras
    public void calcularDistanciaEuclideaRey(){
        distanciaEuclideaRey= Math.sqrt (Math.pow ((posObjetivo.getX()- posRey.getX()),2) + Math.pow ((posObjetivo.getY()- posRey.getY()),2));
    }

    public void calcularDistanciaEuclideaHueco(){
        distanciaEuclideaHueco= Math.sqrt (Math.pow ((posObjetivo.getX()- posHueco.getX()),2) + Math.pow ((posObjetivo.getY()- posHueco.getY()),2));
    }


//Distancia Manhattan: dadas dos posiciones, calcula la distancia realizando movimientos
    // horizontales y verticales
    // Fuente: https://m.riunet.upv.es/bitstream/handle/10251/118069/10808-43941-1-PB.pdf?sequence=4&isAllowed=y#:~:text=En%20contrapartida%2C%20la%20distancia%20m%C4%B1nima,de%20Minkowski%20o%20del%20taxi.

    public void calcularDistanciaManhattanRey(){
        distanciaManhattanRey= (abs(posObjetivo.getX()- posRey.getX())+abs(posObjetivo.getY()- posRey.getY()));
    }

    public void calcularDistanciaManhattanHueco(){
        distanciaManhattanHueco= (abs(posObjetivo.getX()- posHueco.getX())+abs(posObjetivo.getY()- posHueco.getY()));
    }
















    //Intercambia la pieza por el hueco
    public void intercambiarPieza(Posicion pos){
        char pieza=matriz[pos.getX()][pos.getY()];

        System.out.println("La pieza de la posicion "+pos+" es "+pieza);

        //Si la pieza es rey actualizo sus posiciones
        if (pieza=='R'){
            posRey.setXY(posHueco.getX(),posHueco.getY());
        }
        matriz[posHueco.getX()][posHueco.getY()]=pieza; // en el hueco iria la pieza

        posHueco.setXY(pos.getX(),pos.getY());
        matriz[posHueco.getX()][posHueco.getY()]='H'; // y en la pieza el hueco

        //Calculo las nuevas distancias
        calcularDistanciaEuclideaRey();
        calcularDistanciaManhattanRey();

        calcularDistanciaEuclideaHueco();
        calcularDistanciaManhattanHueco();

    }


    //Pone el Rey en el objetivo, ese lugar quedaría vacío (ponemos una X)
    // El Objetivo ya no existe
    public void ponerReyObjetivo(){
        matriz[posRey.getX()][posRey.getY()]='X';
        //Ponemos el Rey
        posRey.setXY(posObjetivo.getX(),posObjetivo.getY());
        matriz[posRey.getX()][posRey.getY()]='R'; // en el hueco iria la pieza
        //Calculamos las distancias
        calcularDistanciaEuclideaRey();
        calcularDistanciaManhattanRey();

        calcularDistanciaEuclideaHueco();
        calcularDistanciaManhattanHueco();

    }







    public void mostrar(){

        //ANTIGUO
//        for (char[] fila : matriz) {
////            System.out.println("+--------------+");
//            for (char c : fila) {
//                System.out.print(c+", ");
//            }
//            System.out.println();
//        }

        //OTRO

        int i = 0;
        int j = 0;

        for (i = 0; i < filas; i++) {
            for (j = 0; j < columnas-1; j++) {
                System.out.print(matriz[j][i]+", ");
            }
            System.out.print(matriz[j][i]);
            System.out.println();
        }

//        System.out.println(Arrays.deepToString(matriz));

        System.out.println("El rey esta en la posición: "+posRey);
        System.out.println("El hueco esta en la posición: "+posHueco);





        //NUEVO
//        for (char[] fila : matriz) {
////            System.out.println("+--------------+");
//            for (char c : fila) {
//                System.out.print("|"+c);
//            }
//            System.out.println("|");
//
//        }
    }

    public void mostrarObjetivo() {
        System.out.println("El objetivo esta en la posición: " + posObjetivo);
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

    public double getDistanciaEuclideaRey() {
        return distanciaEuclideaRey;
    }

    public void setDistanciaEuclideaRey(double distanciaEuclideaRey) {
        this.distanciaEuclideaRey = distanciaEuclideaRey;
    }

    public int getDistanciaManhattanRey() {
        return distanciaManhattanRey;
    }

    public void setDistanciaManhattanRey(int distanciaManhattanRey) {
        this.distanciaManhattanRey = distanciaManhattanRey;
    }

    public int getDistanciaManhattanHueco() {
        return distanciaManhattanHueco;
    }

    public void setDistanciaManhattanHueco(int distanciaManhattanHueco) {
        this.distanciaManhattanHueco = distanciaManhattanHueco;
    }

    public double getDistanciaEuclideaHueco() {
        return distanciaEuclideaHueco;
    }

    public void setDistanciaEuclideaHueco(double distanciaEuclideaHueco) {
        this.distanciaEuclideaHueco = distanciaEuclideaHueco;
    }

    // Exporta la matriz en un fichero
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






}
