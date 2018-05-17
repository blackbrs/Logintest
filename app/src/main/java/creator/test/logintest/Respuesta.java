package creator.test.logintest;

/**
 * Created by root on 05-17-18.
 */

public class Respuesta {
    private int idcuestionario;
    private String carnet;
    private double nota;


    public Respuesta(){

    }

    public int getIdcuestionario() {
        return idcuestionario;
    }

    public void setIdcuestionario(int idcuestionario) {
        this.idcuestionario = idcuestionario;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}
