package creator.test.logintest;

/**
 * Created by Mego on 5/11/2018.
 */

public class Materia {

    private String codmateria;
    private int unidadval;
    private String nombremat;
    private  int idmateria;

    public Materia(String codmateria, int unidadval, String nombremat) {
        this.codmateria = codmateria;
        this.unidadval = unidadval;
        this.nombremat = nombremat;
    }

    public Materia() {
    }

    public int getIdmateria() {
        return idmateria;
    }

    public void setIdmateria(int idmateria) {
        this.idmateria = idmateria;
    }

    public String getCodmateria() {
        return codmateria;
    }

    public void setCodmateria(String codmateria) {
        this.codmateria = codmateria;
    }

    public int getUnidadval() {
        return unidadval;
    }

    public void setUnidadval(int unidadval) {
        this.unidadval = unidadval;
    }

    public String getNombremat() {
        return nombremat;
    }

    public void setNombremat(String nombremat) {
        this.nombremat = nombremat;
    }
}
