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


        Tablero tablero=new Tablero(rutaFichero);


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

            String distanciaEuclideaRey;
            int distanciaManhattanRey;

            String distanciaEuclideaHueco;
            int distanciaManhattanHueco;


            for (i = 0; i < values.length-1; i++) {
                //SACO LA DEL PADRE
//Fuente: https://es.stackoverflow.com/questions/29408/como-limitar-la-cantidad-de-decimales-de-un-double
                distanciaEuclideaRey=String.format("%.2f",tablero.getDistanciaEuclideaRey());
                distanciaManhattanRey=tablero.getDistanciaManhattanRey();

                distanciaEuclideaHueco =String.format("%.2f",tablero.getDistanciaEuclideaHueco());;
                distanciaManhattanHueco=tablero.getDistanciaManhattanHueco();

                System.out.println("Padre (Distancia del Rey)(E: "+distanciaEuclideaRey+" , M: "+distanciaManhattanRey+")"+"(Distancia del Hueco)(E: "+distanciaEuclideaHueco+" , M: "+distanciaManhattanHueco+")");


                //CALCULO LOS HIJOS
                System.out.println("Hijos:");
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

            distanciaEuclideaRey=String.format("%.2f",tablero.getDistanciaEuclideaRey());
            distanciaManhattanRey=tablero.getDistanciaManhattanRey();
            System.out.println("Padre (Distancia del Rey)(Euclidea: "+distanciaEuclideaRey+" , Manhattan: "+distanciaManhattanRey+")");
            // La última pieza en mover es el rey al objetivo y el hueco desaparece
            System.out.println("MOVIMIENTO FINAL");

//            letra = values[i].charAt(0);// Obtengo la letra
//            numero = Character.getNumericValue(values[i].charAt(1));
//            Posicion pos = new Posicion(numero-1,letraANumero(letra));

            tablero.ponerReyObjetivo();
            tablero.mostrar();

            distanciaEuclideaRey=String.format("%.2f",tablero.getDistanciaEuclideaRey());
            distanciaManhattanRey=tablero.getDistanciaManhattanRey();
            System.out.println("Padre (Distancia del Rey)(Euclidea: "+distanciaEuclideaRey+" , Manhattan: "+distanciaManhattanRey+")");


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


            //Inecesario buscar, ya lo obtengo cuando completo la matriz


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


            //ALGORITMO

            // Que piezas estan al lado del hueco que pueda mover
            // Que piezas se pueden mover segun la figura y sus posibles movimientos

            // muevo las piezas
            // Si El rey esta mas cerca, continuo

            // Hay un muro??

            //Entiendo que solo se puede mover las piezas que estan al lado del hueco. CORRECTO


            // POSICIONES ALREDEDOR DEL HUECO
//            //Posiciones que existen alrededor del hueco
//            // Me quedo con una matriz de 3x3
//            int pos_x=0;
//            int pos_y=0;
//            if(pos_x_hueco >1){
//                pos_x=pos_x_hueco-1;
//            }
//            if(pos_y_hueco >1){
//                pos_y=pos_y_hueco-1;
//            }
//
//            System.out.println("La posición de la matriz sería: ("+pos_x+", "+pos_y+")");
//
//            int limite_filas=filas;
//            int limite_columnas=columnas;
//            if(pos_x_hueco <3){
//                limite_filas=pos_x_hueco+2;
//            }
//            if(pos_y_hueco <3){
//                limite_columnas=pos_y_hueco+2;
//            }
//
//            System.out.println("La posición limite sería: ("+limite_filas+", "+limite_columnas+")");

//            char figura;
            // Necesitamos una segunda matriz para calcular el movimiento y ver cual de los caminos es mejor
//            char[][] matriz2= tablero.getMatriz();

            //Comprobar que no se sale y que los movimientos que dan son correctos

//            for (int i = pos_x; i<limite_filas; i++) {
//                for (int j = pos_y; j < limite_columnas; j++) {
//                    figura=matriz[i][j];
//                    if(figura!='H'&&figura!='M'){
//                        //Tendriamos los posibles movimientos
//
//                        //Piezas que se pueden mover
//                        //REY
//                        //Siempre se puede mover
//
//                        // en todos los casos hay que calcular la nueva distanciaEuclidea al objetivo
//                        switch (figura){
//                            case 'T':
//                                // La torre se puede mover(cuando sea un movimiento en horizontal o en vertical
//                               if (distanciaEuclidea(i,j,pos_x_hueco,pos_y_hueco)<=1){
//                                   //Calcular la nueva distanciaEuclidea
//                                   //guardar en una lista
//
//
//                                   System.out.println(figura+" - "+"("+i+"," +j+"), y la nueva distanciaEuclidea al objetivo es de: ");
//                               }
//                                break;
//                            case 'A':
//                                // El alfil se puede mover(cuando sea un movimiento en diagonal)
//                                if (distanciaEuclidea(i,j,pos_x_hueco,pos_y_hueco)>1){
//                                    //Calcular la nueva distanciaEuclidea
//                                    //guardar en una lista
//
//                                    System.out.println(figura+" - "+"("+i+"," +j+"), y la nueva distanciaEuclidea al objetivo es de: ");
//                                }
//                                break;
//                            case 'R':
//                                // El rey siempre se puede mover
//                                //Calcular la nueva distanciaEuclidea
//                                //guardar en una lista
//
//
//
//                                System.out.println(figura+" - "+"("+i+"," +j+"), y la nueva distanciaEuclidea al objetivo es de: ");
//                                break;
//                        }
//
//
//
//
//
//                        //Intercambia piezas
//
//                        //Calcula la distanciaEuclidea
//
//                    }
//                }
//            }



//            moverRey();



//            moverTorre();


//            moverAlfil();


            //Acuerdate de dar la solución

            //Acuerdate de dar el tiempo final




    }
}