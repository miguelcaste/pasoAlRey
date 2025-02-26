package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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

    //Hijos y matriz de movimientos
    private Posicion posInicialMatrizMovimientos;
    private Posicion posFinalMatrizMovimientos;

    //Figura
    private char ultimaFiguraMovida;


    private ArrayList<Tablero> hijos;


    // Constructor por defecto
    public Tablero(){
        //Posiciones
        posRey=new Posicion();
        posHueco=new Posicion();
        posObjetivo=new Posicion();
        posInicialMatrizMovimientos=new Posicion();
        posFinalMatrizMovimientos=new Posicion();

        hijos=new ArrayList<>();
        //Ejemplo de tablero
//        matriz = new char[][]{
//                {'T', 'T', 'T', 'A', 'T'},
//                {'R', 'H', 'T', 'A', 'T'},
//                {'A', 'A', 'M', 'M', 'O'},
//                {'M', 'M', 'M', 'M', 'M'},
//                {'T', 'T', 'T', 'T', 'T'}};
    }

    public Tablero(Tablero tablero) {
        //Se copiaran matrices?

        this.matriz = new char[filas][columnas];

        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas() ; j++) {
                this.matriz[i][j]=tablero.getMatriz()[i][j];
            }
        }



        this.filas = tablero.getFilas();
        this.columnas = tablero.getColumnas();

        //Se copiaran posiciones?
        this.posRey=new Posicion(tablero.getPosRey().getX(),tablero.getPosRey().getY());
        this.posHueco = new Posicion(tablero.getPosHueco().getX(),tablero.getPosHueco().getY());
        this.posObjetivo = new Posicion(tablero.getPosObjetivo().getX(),tablero.getPosObjetivo().getY());

        this.distanciaEuclideaRey = tablero.getDistanciaEuclideaRey();
        this.distanciaManhattanRey = tablero.getDistanciaManhattanRey();
        this.distanciaEuclideaHueco = tablero.getDistanciaEuclideaHueco();
        this.distanciaManhattanHueco = tablero.getDistanciaManhattanHueco();
        this.posInicialMatrizMovimientos = tablero.getPosInicialMatrizMovimientos();
        this.posFinalMatrizMovimientos = tablero.getPosFinalMatrizMovimientos();

        //Los hijos no se copian, se dejan vacios
        this.hijos=new ArrayList<>();

    }

    // Constructor parametrizado
    // Dado un fichero, crea el tablero y completa las posiciones
    public Tablero(String fichero){
        matriz = new char[filas][columnas];
        char letra;
        //Posiciones
        posRey=new Posicion();
        posHueco=new Posicion();
        posObjetivo=new Posicion();
        posInicialMatrizMovimientos=new Posicion();
        posFinalMatrizMovimientos=new Posicion();

        hijos=new ArrayList<>();


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
    //sin parametros del hueco al objetivo
    public void calcularDistanciaEuclideaHueco(){
        distanciaEuclideaHueco= Math.sqrt (Math.pow ((posObjetivo.getX()- posHueco.getX()),2) + Math.pow ((posObjetivo.getY()- posHueco.getY()),2));
    }

//    public void calcularDistanciaEuclideaHueco(Posicion pos){
//        distanciaEuclideaHueco= Math.sqrt (Math.pow ((pos.getX()- posHueco.getX()),2) + Math.pow ((pos.getY()- posHueco.getY()),2));
//    }


//Distancia Manhattan: dadas dos posiciones, calcula la distancia realizando movimientos
    // horizontales y verticales
    // Fuente: https://m.riunet.upv.es/bitstream/handle/10251/118069/10808-43941-1-PB.pdf?sequence=4&isAllowed=y#:~:text=En%20contrapartida%2C%20la%20distancia%20m%C4%B1nima,de%20Minkowski%20o%20del%20taxi.

    public void calcularDistanciaManhattanRey(){
        distanciaManhattanRey= (abs(posObjetivo.getX()- posRey.getX())+abs(posObjetivo.getY()- posRey.getY()));
    }

    public void calcularDistanciaManhattanHueco(){
        distanciaManhattanHueco= (abs(posObjetivo.getX()- posHueco.getX())+abs(posObjetivo.getY()- posHueco.getY()));
    }
    //con parametros del hueco a la posición dada
    public int calcularDistanciaManhattanHueco(Posicion pos){
        return (abs(pos.getX()- posHueco.getX())+abs(pos.getY()- posHueco.getY()));
    }

    public void calcularPosicionesMatrizMovimientos(){
        //     POSICIONES ALREDEDOR DEL HUECO
        //Posiciones que existen alrededor del hueco
        // Me quedo con una matriz de 3x3
        //PROBAR ESTE ALGORITMO
        //1º PARTE
        //LIMITES INFERIORES
        posInicialMatrizMovimientos.setXY(0,0);
        if(posHueco.getX() >0){
            posInicialMatrizMovimientos.setX(posHueco.getX()-1);
        }
        if(posHueco.getY() >0){
            posInicialMatrizMovimientos.setY(posHueco.getY()-1);
        }

        System.out.println("La posición de la matriz sería: "+posInicialMatrizMovimientos);

        //LIMITES SUPERIORES
        posFinalMatrizMovimientos.setXY(4,4);
        if(posHueco.getX() <4){
            posFinalMatrizMovimientos.setX(posHueco.getX()+1);
        }
        if(posHueco.getY() <4){
            posFinalMatrizMovimientos.setY(posHueco.getY()+1);
        }

        System.out.println("La posición limite sería: ("+posFinalMatrizMovimientos);
    }


    //ALGORITMO PRINCIPAL

    // Qué piezas están al lado del hueco que pueda mover
    // Que piezas se pueden mover según la figura y sus posibles movimientos

    // muevo las piezas
    // Si El rey esta mas cerca, continuo

    // Hay un muro??

    //Entiendo que solo se puede mover las piezas que están al lado del hueco. CORRECTO
    public void calcularHijos(){
        calcularPosicionesMatrizMovimientos();
            char figura;
            Posicion p=new Posicion();
            String disEuclideaRey;
            String disEuclideaHueco;
            int contador=0;

//                // Necesitamos una segunda matriz para calcular el movimiento y ver cual de los caminos es mejor
////            char[][] matriz2= tablero.getMatriz();
//
//                //Comprobar que no se sale y que los movimientos que dan son correctos
//            //2º PARTE
            for (int i = posInicialMatrizMovimientos.getX(); i<=posFinalMatrizMovimientos.getX(); i++) {
                for (int j = posInicialMatrizMovimientos.getY(); j <=posFinalMatrizMovimientos.getY(); j++) {
                    figura=matriz[i][j];
                    p.setXY(i,j);
                    //Distinto de Hueco y de Muro
                    if(figura!='H'&&figura!='M'){
                        //Tendriamos los posibles movimientos

                        //Piezas que se pueden mover
                        //REY
                        //Siempre se puede mover

                        // en todos los casos hay que calcular la nueva distanciaEuclidea al objetivo
                        switch (figura){
                            case 'T':
                                // La torre se puede mover(cuando sea un movimiento en horizontal o en vertical
                               if (calcularDistanciaManhattanHueco(p) == 1){
                                   //Calcular la nueva distanciaEuclidea
                                   //guardar en una lista

                                   //1º OPCIÓN CORRECTA
                                   //Generaría un tablero por cada figura correcta y lo guardaría en la lista
                                   //Necesito tambien el movimiento

                                   //2º OPCION SENCILLA
                                   //Otra opción
                                   //Aplicar el movimiento
                                   //imprimirlo y volver hacia atrás

                                   // Recorrer padre
                                   // Tranferir a hijo

                                   System.out.print(figura+" - "+p);
                                   hijos.add(contador,new Tablero(this));
                                   hijos.get(contador).intercambiarPieza(p);
                                   hijos.get(contador).mostrardistancias();

                                   contador++;
//                                   Tablero t=new Tablero(this);
//                                   t.intercambiarPieza(p);
//                                   t.mostrar();
//                                   System.out.print(t.getUltimaFiguraMovida()+" - "+p);
//                                    t.mostrardistancias();
//                                   hijos.add(t);




                                   //VUELVO HACIA ATRAS
                                   //Falta el volver hacia atras


                               }
                                break;
                            case 'A':
                                // El alfil se puede mover(cuando sea un movimiento en diagonal)
                                if (calcularDistanciaManhattanHueco(p) > 1){
                                    //Calcular la nueva distanciaEuclidea
                                    //guardar en una lista

                                    System.out.print(figura+" - "+p);
                                    hijos.add(contador,new Tablero(this));
                                    hijos.get(contador).intercambiarPieza(p);

                                    hijos.get(contador).mostrardistancias();

                                    contador++;

//                                    Tablero t=new Tablero(this);
//                                    t.intercambiarPieza(p);
//                                    t.mostrar();
//                                    System.out.print(t.getUltimaFiguraMovida()+" - "+p);
//                                    t.mostrardistancias();
//                                    hijos.add(t);


                                }
                                break;
                            case 'R':
                                // El rey siempre se puede mover
                                //Calcular la nueva distanciaEuclidea
                                //guardar en una lista

                                System.out.print(figura+" - "+p);
                                hijos.add(contador,new Tablero(this));
                                hijos.get(contador).intercambiarPieza(p);

                                hijos.get(contador).mostrardistancias();

                                contador++;


//                                Tablero t=new Tablero(this);
//                                t.intercambiarPieza(p);
//                                t.mostrar();
//                                System.out.print(t.getUltimaFiguraMovida()+" - "+p);
//                                t.mostrardistancias();
//                                hijos.add(t);

                                break;
                        }





                        //Intercambia piezas

                        //Calcula la distanciaEuclidea

                    }
                }
            }




    }

    public void borrarHijos(){
        hijos.clear();
    }











    //Intercambia la pieza por el hueco
    public void intercambiarPieza(Posicion pos){
        char pieza=matriz[pos.getX()][pos.getY()];

//        System.out.println("La pieza de la posicion "+pos+" es "+pieza);

        //Si la pieza es rey actualizo sus posiciones
        if (pieza=='R'){
            posRey.setXY(posHueco.getX(),posHueco.getY());
        }
        matriz[posHueco.getX()][posHueco.getY()]=pieza; // en el hueco iria la pieza

        //Guardo la última figura movida
        ultimaFiguraMovida=pieza;

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


    public void mostrardistancias() {
        String disEuclideaRey=String.format("%.2f",distanciaEuclideaRey);
        String disEuclideaHueco=String.format("%.2f",distanciaEuclideaHueco);

        System.out.println("(Distancia del Rey)(E: "+disEuclideaRey+" , M: "+distanciaManhattanRey+")"+"(Distancia del Hueco)(E: "+disEuclideaHueco+" , M: "+distanciaManhattanHueco+")");
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

    public Posicion getPosRey() {
        return posRey;
    }

    public void setPosRey(Posicion posRey) {
        this.posRey = posRey;
    }

    public Posicion getPosHueco() {
        return posHueco;
    }

    public void setPosHueco(Posicion posHueco) {
        this.posHueco = posHueco;
    }

    public Posicion getPosObjetivo() {
        return posObjetivo;
    }

    public void setPosObjetivo(Posicion posObjetivo) {
        this.posObjetivo = posObjetivo;
    }

    public Posicion getPosInicialMatrizMovimientos() {
        return posInicialMatrizMovimientos;
    }

    public void setPosInicialMatrizMovimientos(Posicion posInicialMatrizMovimientos) {
        this.posInicialMatrizMovimientos = posInicialMatrizMovimientos;
    }

    public Posicion getPosFinalMatrizMovimientos() {
        return posFinalMatrizMovimientos;
    }

    public void setPosFinalMatrizMovimientos(Posicion posFinalMatrizMovimientos) {
        this.posFinalMatrizMovimientos = posFinalMatrizMovimientos;
    }

    public ArrayList<Tablero> getHijos() {
        return hijos;
    }

    public void setHijos(ArrayList<Tablero> hijos) {
        this.hijos = hijos;
    }

    public char getUltimaFiguraMovida() {
        return ultimaFiguraMovida;
    }

    public void setUltimaFiguraMovida(char ultimaFiguraMovida) {
        this.ultimaFiguraMovida = ultimaFiguraMovida;
    }

    //FUNCIONES APARTE
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
