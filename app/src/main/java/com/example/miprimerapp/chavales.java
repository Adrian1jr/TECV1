package com.example.miprimerapp;

public class chavales {
    private String nombre;
    private int NoControl;
    private boolean presente;

    chavales(String nombre, int NoControl){
        this.nombre = nombre;
        this.NoControl = NoControl;
        this.presente = false;
    }

    public void setPresente(boolean presente) {
        this.presente = presente;
    }

    public boolean isPresente() {
        return presente;
    }
    @Override
    public String toString(){
        return this.nombre + " " + this.presente;
    }

    public int getNoControl() {
        return NoControl;
    }
}
