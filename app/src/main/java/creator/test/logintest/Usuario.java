package creator.test.logintest;

/**
 * Created by Mego on 5/10/2018.
 */

public class Usuario {

    private String nomusuario;
    private String clave;
    private Boolean isAdmin;
    private Boolean isDocente;
    private Boolean isEstudiante;

    public Usuario(String nomusuario, String clave, Boolean isAdmin, Boolean isDocente, Boolean isEstudiante) {
        this.nomusuario = nomusuario;
        this.clave = clave;
        this.isAdmin = isAdmin;
        this.isDocente = isDocente;
        this.isEstudiante = isEstudiante;
    }

    public Usuario() {
    }

    public String getNomusuario() {
        return nomusuario;
    }

    public void setNomusuario(String nomusuario) {
        this.nomusuario = nomusuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Boolean getDocente() {
        return isDocente;
    }

    public void setDocente(Boolean docente) {
        isDocente = docente;
    }

    public Boolean getEstudiante() {
        return isEstudiante;
    }

    public void setEstudiante(Boolean estudiante) {
        isEstudiante = estudiante;
    }
}
