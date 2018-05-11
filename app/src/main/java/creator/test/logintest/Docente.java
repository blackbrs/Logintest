package creator.test.logintest;

/**
 * Created by Mego on 5/10/2018.
 */

public class Docente {


    private String nombdocente;
    private String apelldocente;
    private String correo;
    private String direccion;
    private String nomusuario;


    public Docente(String nombdocente, String apelldocente, String correo, String direccion, String nomusuario) {
        this.nombdocente = nombdocente;
        this.apelldocente = apelldocente;
        this.correo = correo;
        this.direccion = direccion;
        this.nomusuario = nomusuario;
    }

    public String getNombdocente() {
        return nombdocente;
    }

    public void setNombdocente(String nombdocente) {
        this.nombdocente = nombdocente;
    }

    public String getApelldocente() {
        return apelldocente;
    }

    public void setApelldocente(String apelldocente) {
        this.apelldocente = apelldocente;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNomusuario() {
        return nomusuario;
    }

    public void setNomusuario(String nomusuario) {
        this.nomusuario = nomusuario;
    }
}
