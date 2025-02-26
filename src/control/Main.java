package control;

import model.Posicion;
import model.Tablero;

import static java.lang.Math.abs;


public class Main {




    //En un futuro usar enumerados
    //Fuente: https://jarroba.com/enum-enumerados-en-java-con-ejemplos/

    public static int letraANumero(char letra){
            switch (letra){
                case 'a':
                    return 0;
                case 'b':
                    return 1;
                case 'c':
                    return 2;
                case 'd':
                    return 3;
                case 'e':
                    return 4;
            }
            return 0;
    }




        public static void main(String[] args) {
        // 1. Primero creamos la matriz
        // Fuente: https://www.w3schools.com/java/java_arrays_multi.asp

//        char[][] tablero=crearTablero();

        // 2. Exportamos el tablero
            // Fuente: https://www.w3schools.com/java/java_files_create.asp
//        guardarTablero(tablero);

        // 3. Importamos el tablero
        // Fuente: https://www.javacodegeeks.com/java-file-to-two-dimensional-array.html
        String rutaFichero="./files/PASOALREY1.txt";
//        String rutaFichero="./files/PASOALREY2.txt";
//        String rutaFichero="./files/PASOALREY3.txt";
//        String rutaFichero="./files/PASOALREY4.txt";
//        String rutaFichero="./files/PASOALREY5.txt";
//        String rutaFichero="./files/PASOALREY6.txt";
//        String rutaFichero="./files/PASOALREY7.txt";
//        String rutaFichero="./files/PASOALREY8.txt";
//        String rutaFichero="./files/PASOALREY9.txt";
//        String rutaFichero="./files/PASOALREY10.txt";

        //tablero original
        Tablero tablero=new Tablero(rutaFichero);
//            System.out.println("Mostrando tablero t1");
//            tablero.mostrar();
//            tablero.mostrardistancias();
//
//
//        //Prueba constructor copia tablero
//        Tablero t2=new Tablero(tablero);
//            System.out.println("Mostrando tablero t2");
//            t2.mostrar();
//            t2.mostrardistancias();
//
//        t2.intercambiarPieza(new Posicion(1,0));
//            System.out.println("Mostrando tablero t2");
//        t2.mostrar();
//        t2.mostrardistancias();


        // 4. La mostramos

            System.out.println("--------------TABLERO INICIAL----------------");
            tablero.mostrar();
            tablero.mostrarObjetivo();



        // Primera aproximación: Implementar el recorrido de una solución
            String solucion="b1, c2, b2, b3, c2, b2, a2, a3, b4, b3, b4";

// Transformando esta solución
            // esta formada por la pieza que va al hueco, necesitamos saber donde esta el hueco en todo momento
            // actualizando su posición
            String[] values = solucion.split(", ");

            char letra;
            int numero;
            int i;



            String distanciaEuclideaHueco;
            int distanciaManhattanHueco;

//            for (i = 0; i < 1; i++) {
            for (i = 0; i < values.length-1; i++) {
                //SACO LA DEL PADRE
//Fuente: https://es.stackoverflow.com/questions/29408/como-limitar-la-cantidad-de-decimales-de-un-double
                System.out.print("Padre ");
                tablero.mostrardistancias();

                //CALCULO LOS HIJOS
                System.out.println("Hijos:");
                tablero.calcularHijos();
                tablero.borrarHijos();

                //Cuando termine de calcular los hijos se borra la lista o se entra en una nueva


                //Hijo_1, Hijo_2, etc













                //SACO EL MOVIMIENTO
                letra = values[i].charAt(0);// Obtengo la letra
                numero = Character.getNumericValue(values[i].charAt(1));
                //System.out.println(letra+"-"+numero+" se transforma en "+(numero-1)+"-"+letraANumero(letra));
                //Realizamos los movimientos y actualizamos el tablero
                System.out.println("--------------MOVIMIENTO "+(i+1)+"--------------");

                //[T, T, T, A, T]
                //[R, H, T, A, T]
                // 0,1
                Posicion pos = new Posicion(numero-1,letraANumero(letra));


//                System.out.println("Pieza a intercambiar"+pos);
                tablero.intercambiarPieza(pos);
                tablero.mostrar();


            }

//            distanciaEuclideaRey=String.format("%.2f",tablero.getDistanciaEuclideaRey());
//            distanciaManhattanRey=tablero.getDistanciaManhattanRey();
//            System.out.println("Padre (Distancia del Rey)(Euclidea: "+distanciaEuclideaRey+" , Manhattan: "+distanciaManhattanRey+")");
            // La última pieza en mover es el rey al objetivo y el hueco desaparece
            System.out.println("MOVIMIENTO FINAL");

//            letra = values[i].charAt(0);// Obtengo la letra
//            numero = Character.getNumericValue(values[i].charAt(1));
//            Posicion pos = new Posicion(numero-1,letraANumero(letra));

            tablero.ponerReyObjetivo();
            tablero.mostrar();

//            distanciaEuclideaRey=String.format("%.2f",tablero.getDistanciaEuclideaRey());
//            distanciaManhattanRey=tablero.getDistanciaManhattanRey();
//            System.out.println("Padre (Distancia del Rey)(Euclidea: "+distanciaEuclideaRey+" , Manhattan: "+distanciaManhattanRey+")");


            // recorrerlo
            //sacar el primer carácter y el segundo con charAt
            // Transformar a coordenadas x, y






        // 5. Implementamos movimientos

            char[][] matriz= tablero.getMatriz();
            int filas= tablero.getFilas();
            int columnas= tablero.getColumnas();


            // Algoritmo de movimiento
//          1. Encontrar al Rey
//              Distancia al objetivo
//              Comprobar muros

            //filas x
            //columnas y


            //FUNCIONES DE BUSCAR
            //Innecesario buscar, ya lo obtengo cuando completo la matriz


//            int x_rey = 0;
//            int y_rey = 0;
//
//
//            char c;
//            for (int i = 0; i < filas; i++) {
//                for (int j = 0; j < columnas; j++) {
//                    c=matriz[i][j];
//                    if (c=='R'){
////                        System.out.println("La posición del rey es ("+i+" ,"+j+")");
//                        x_rey=i;
//                        y_rey=j;
//                    }
//                }
//            }
//
//            int pos_x_objetivo = 0;
//            int pos_y_objetivo = 0;
//
//            for (int i = 0; i < filas; i++) {
//                for (int j = 0; j < columnas; j++) {
//                    c=matriz[i][j];
//                    if (c=='O'){
////                        System.out.println("La posición del objetivo es ("+i+" ,"+j+")");
//                        pos_x_objetivo =i;
//                        pos_y_objetivo =j;
//                    }
//                }
//            }
//
//            int pos_x_hueco = 0;
//            int pos_y_hueco = 0;
//
//            for (int i = 0; i < filas; i++) {
//                for (int j = 0; j < columnas; j++) {
//                    c=matriz[i][j];
//                    if (c=='H'){
//                        System.out.println("La posición del hueco es ("+i+" ,"+j+")");
//                        pos_x_hueco =i;
//                        pos_y_hueco =j;
//                    }
//                }
//            }




//            System.out.println("La distanciaEuclidea es de: "+ distanciaEuclidea(x_rey,y_rey, pos_x_objetivo, pos_y_objetivo));






//            moverRey();
//            moverTorre();
//            moverAlfil();


            //Acuerdate de dar la solución

            //Acuerdate de dar el tiempo final




    }
}