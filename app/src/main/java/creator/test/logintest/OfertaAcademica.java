package creator.test.logintest;

/**
 * Created by Mego on 5/12/2018.
 */

class OfertaAcademica {

    String descripcion;
    Materia mater;
    Ciclo cic;
    Docente doc;
    private int idoferta;


    public OfertaAcademica() {
    }

    public int getIdoferta() {
        return idoferta;
    }

    public void setIdoferta(int idoferta) {
        this.idoferta = idoferta;
    }

    public Materia getMater() {
        return mater;
    }

    public void setMater(Materia mater) {
        this.mater = mater;
    }

    public Ciclo getCic() {
        return cic;
    }

    public void setCic(Ciclo cic) {
        this.cic = cic;
    }

    public Docente getDoc() {
        return doc;
    }

    public void setDoc(Docente doc) {
        this.doc = doc;
    }



    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
