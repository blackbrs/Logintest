package creator.test.logintest;

/**
 * Created by Mego on 5/12/2018.
 */

public class Cuestionario {
    private int idoferta;
    private String descricuestinario;
    private String fechacuestionario;
    private double ponderacion;

    public Cuestionario(){

    }

    public int getIdoferta() {
        return idoferta;
    }

    public void setIdoferta(int idoferta) {
        this.idoferta = idoferta;
    }

    public String getDescricuestinario() {
        return descricuestinario;
    }

    public void setDescricuestinario(String descricuestinario) {
        this.descricuestinario = descricuestinario;
    }

    public String getFechacuestionario() {
        return fechacuestionario;
    }

    public void setFechacuestionario(String fechacuestionario) {
        this.fechacuestionario = fechacuestionario;
    }

    public double getPonderacion() {
        return ponderacion;
    }

    public void setPonderacion(double ponderacion) {
        this.ponderacion = ponderacion;
    }
}
