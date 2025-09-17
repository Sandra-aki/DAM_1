package SERIALIZAR;

import java.io.Serializable;

public class N implements Serializable {

    private boolean b;
    private char c;
    private int n;
    private double d;
    private String s;

    public N() {
        this.b = true;
        this.c = 'A';
        this.n = 10;
        this.d = 5.5;
        this.s = "Hola";
    }

    public N(boolean b, char c, int n, double d, String s) {
        this.b = b;
        this.c = c;
        this.n = n;
        this.d = d;
        this.s = s;
    }

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return "N{" + "b=" + b + ", c=" + c + ", n=" + n + ", d=" + d + ", s=" + s + '}';
    }

}
