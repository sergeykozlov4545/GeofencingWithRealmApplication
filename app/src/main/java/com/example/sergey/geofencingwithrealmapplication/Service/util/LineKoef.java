package com.example.sergey.geofencingwithrealmapplication.Service.util;

public class LineKoef {
    private double a;
    private double b;
    private double c;

    public LineKoef(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }
}
