package model;

public class Posicion {
    private int x;
    private int y;

    public Posicion() {
        this.x = 0;
        this.y = 0;
    }

    public Posicion(Posicion posicion) {
        this.x = posicion.getX();
        this.y = posicion.getY();
    }

    public Posicion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }



    @Override
    public String toString() {
        return "(" +
                "x=" + x +
                ",y=" + y +
                ')';
    }
}
