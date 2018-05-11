package creator.test.logintest;

/**
 * Created by Mego on 5/10/2018.
 */

public class Estudiante {


    private String carnet;
    private String nombreestu ;
    private String apellidoestu;
    private String correoestu;
    private String direccionestu;
    private String nomusuario;

    public Estudiante(String carnet, String nombreestu, String apellidoestu, String correoestu, String direccionestu, String nomusuario) {
        this.carnet = carnet;
        this.nombreestu = nombreestu;
        this.apellidoestu = apellidoestu;
        this.correoestu = correoestu;
        this.direccionestu = direccionestu;
        this.nomusuario = nomusuario;
    }
    public Estudiante(){

    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getNombreestu() {
        return nombreestu;
    }

    public void setNombreestu(String nombreestu) {
        this.nombreestu = nombreestu;
    }

    public String getApellidoestu() {
        return apellidoestu;
    }

    public void setApellidoestu(String apellidoestu) {
        this.apellidoestu = apellidoestu;
    }

    public String getCorreoestu() {
        return correoestu;
    }

    public void setCorreoestu(String correoestu) {
        this.correoestu = correoestu;
    }

    public String getDireccionestu() {
        return direccionestu;
    }

    public void setDireccionestu(String direccionestu) {
        this.direccionestu = direccionestu;
    }

    public String getNomusuario() {
        return nomusuario;
    }

    public void setNomusuario(String nomusuario) {
        this.nomusuario = nomusuario;
    }
}
