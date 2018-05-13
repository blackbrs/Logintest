package creator.test.logintest;

/**
 * Created by Mego on 5/12/2018.
 */

public class Pregunta {
    private int idarea;
    private int ponderapregunta;
    private String descrippreg;
    private int tipo;


    public Pregunta(){

    }

    public int getIdarea() {
        return idarea;
    }

    public void setIdarea(int idarea) {
        this.idarea = idarea;
    }

    public int getPonderapregunta() {
        return ponderapregunta;
    }

    public void setPonderapregunta(int ponderapregunta) {
        this.ponderapregunta = ponderapregunta;
    }

    public String getDescrippreg() {
        return descrippreg;
    }

    public void setDescrippreg(String descrippreg) {
        this.descrippreg = descrippreg;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
