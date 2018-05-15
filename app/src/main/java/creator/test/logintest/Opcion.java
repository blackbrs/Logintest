package creator.test.logintest;

import android.graphics.Path;

/**
 * Created by Mego on 5/12/2018.
 */

public class Opcion {
    private int idpregunta;
    private int idopcion;
    private String descripcion;
    private boolean isCorrect;

    public Opcion(){

    }

    public int getIdpregunta() {
        return idpregunta;
    }

    public void setIdpregunta(int idpregunta) {
        this.idpregunta = idpregunta;
    }

    public int getIdopcion() {
        return idopcion;
    }

    public void setIdopcion(int idopcion) {
        this.idopcion = idopcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
