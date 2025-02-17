package control;

import model.Tablero;












public class Main {


        //Útiles

        public static double distancia(int x1, int y1, int x2, int y2){
            return Math.sqrt (Math.pow ((x2-x1),2) + Math.pow ((y2-y1),2));
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
        tablero.mostrar();


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

            int x_rey = 0;
            int y_rey = 0;


            char c;
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    c=matriz[i][j];
                    if (c=='R'){
//                        System.out.println("La posición del rey es ("+i+" ,"+j+")");
                        x_rey=i;
                        y_rey=j;
                    }
                }
            }

            int pos_x_objetivo = 0;
            int pos_y_objetivo = 0;

            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    c=matriz[i][j];
                    if (c=='O'){
//                        System.out.println("La posición del objetivo es ("+i+" ,"+j+")");
                        pos_x_objetivo =i;
                        pos_y_objetivo =j;
                    }
                }
            }

            int pos_x_hueco = 0;
            int pos_y_hueco = 0;

            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    c=matriz[i][j];
                    if (c=='H'){
                        System.out.println("La posición del hueco es ("+i+" ,"+j+")");
                        pos_x_hueco =i;
                        pos_y_hueco =j;
                    }
                }
            }







            //https://manolohidalgo.com/ejercicio-12-distancia-entre-dos-puntos/
            //distancia
            //por el teorema de pitagoras





            System.out.println("La distancia es de: "+distancia(x_rey,y_rey, pos_x_objetivo, pos_y_objetivo));


            //ALGORITMO

            // Que piezas estan al lado del hueco que pueda mover
            // Que piezas se pueden mover segun la figura y sus posibles movimientos

            // muevo las piezas
            // Si El rey esta mas cerca, continuo

            // Hay un muro??

            //Entiendo que solo se puede mover las piezas que estan al lado del hueco


            //Posiciones que existen alrededor del hueco
            // Me quedo con una matriz de 3x3
            int pos_x=0;
            int pos_y=0;
            if(pos_x_hueco >1){
                pos_x=pos_x_hueco-1;
            }
            if(pos_y_hueco >1){
                pos_y=pos_y_hueco-1;
            }

            System.out.println("La posición de la matriz sería: ("+pos_x+", "+pos_y+")");

            int limite_filas=filas;
            int limite_columnas=columnas;
            if(pos_x_hueco <3){
                limite_filas=pos_x_hueco+2;
            }
            if(pos_y_hueco <3){
                limite_columnas=pos_y_hueco+2;
            }

            System.out.println("La posición limite sería: ("+limite_filas+", "+limite_columnas+")");

            char figura;
            // Necesitamos una segunda matriz para calcular el movimiento y ver cual de los caminos es mejor
            char[][] matriz2= tablero.getMatriz();

            for (int i = pos_x; i<limite_filas; i++) {
                for (int j = pos_y; j < limite_columnas; j++) {
                    figura=matriz[i][j];
                    if(figura!='H'&&figura!='M'){
                        System.out.println(figura+" - "+"("+i+"," +j+")");
                        //Tendriamos los posibles movimientos

                        //Vamos a pensar que todas las piezas se pueden mover

                        //Intercambia piezas

                        //Calcula la distancia

                    }
                }
            }



//            moverRey();



//            moverTorre();


//            moverAlfil();


    }
}