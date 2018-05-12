package creator.test.logintest;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class OfertaAcademicaInsertarActivity extends AppCompatActivity {
    ControlBDHelper helper;
    EditText materia;
    EditText numciclo;
    EditText aniociclo;
    EditText docente;
    EditText descrip;
    ArrayList<String> listaMateria;
    ArrayList<Materia> MateriasList;
    ArrayList<String> listaCiclo;
    ArrayList<Ciclo> CiclosList;
    ArrayList<String> listaDocente;
    ArrayList<Docente> DocentesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_oferta_academica_insertar);
        helper = new ControlBDHelper(this);

        materia = (EditText) findViewById(R.id.codmaterias);
        numciclo= (EditText) findViewById(R.id.numerocic);
        aniociclo=(EditText) findViewById(R.id.aniociclo);
        docente= (EditText) findViewById(R.id.usuariodoc);
        descrip = (EditText) findViewById(R.id.descripcion);
    }

    public void insertarOfertaAcademica(View v) {

        String codmateria = materia.getText().toString();
        int numerociclo =Integer.parseInt(numciclo.getText().toString());
        int aniocic = Integer.parseInt(aniociclo.getText().toString());
        String  nombreDocente = docente.getText().toString();
        String des = descrip.getText().toString();
        String regInsertados;
        Materia mat1 = new Materia();
        Ciclo ciclo1 = new Ciclo();
        Docente doc1 = new Docente();

        mat1.setCodmateria(codmateria);
        ciclo1.setNumCiclo(numerociclo);
        ciclo1.setAnioCiclo(aniocic);
        doc1.setNomusuario(nombreDocente);

        OfertaAcademica oferta = new OfertaAcademica();
        oferta.setMater(mat1);
        oferta.setCic(ciclo1);
        oferta.setDoc(doc1);
        oferta.setDescripcion(des);
        helper.abrir();
        regInsertados = helper.insertar(oferta);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        materia.setText("");
        numciclo.setText("");
        aniociclo.setText("");
        docente.setText("");

    }









    public void consultarListaMateria() {
        Materia materia =null;
        MateriasList = new ArrayList<Materia>();
        helper.abrir();
        helper.cerrar();
        helper.abrir();
        Cursor cursorMateria = helper.consultarListaMaterias();
        while(cursorMateria.moveToNext()){
            materia = new Materia();
            materia.setCodmateria(cursorMateria.getString(1));
            materia.setUnidadval(cursorMateria.getInt(2));
            materia.setNombremat(cursorMateria.getString(3));
            System.out.println(materia.getCodmateria());
            System.out.println(materia.getNombremat());
            MateriasList.add(materia);
        }
        obtenerListaMateria();
    }

    public void obtenerListaMateria(){
        listaMateria = new ArrayList<>();
        listaMateria.add("Seleccione");
        for (int i=0; i<MateriasList.size();i++){
            listaMateria.add(MateriasList.get(i).getCodmateria());
        }
        System.out.println(listaMateria);
    }

    public void consultarListaCiclo() {
        Ciclo ciclo =null;
        ArrayList<Integer> idciclo =   idciclo = new ArrayList<>();;
        CiclosList = new ArrayList<Ciclo>();
        helper.abrir();
        helper.cerrar();
        helper.abrir();
        Cursor cursorCiclo = helper.consultarListaCiclos();
        while(cursorCiclo.moveToNext()){
            ciclo = new Ciclo();
            idciclo.add(cursorCiclo.getInt(0));
            ciclo.setNumCiclo(cursorCiclo.getInt(1));
            ciclo.setAnioCiclo(cursorCiclo.getInt(2));
            ciclo.setFechaIni(cursorCiclo.getString(3));
            ciclo.setFechaFin(cursorCiclo.getString(4));
            CiclosList.add(ciclo);
        }
        obtenerListaCiclo(idciclo);
    }
    private void obtenerListaCiclo(ArrayList<Integer> id) {
        listaCiclo = new ArrayList<>();
        listaCiclo.add("Seleccione");
        for (int i=0; i<CiclosList.size();i++){
            listaCiclo.add(String.valueOf(id.get(i))+" "+CiclosList.get(i).getAnioCiclo()+" - "+CiclosList.get(i).getNumCiclo());
        }
        System.out.println();
    }

    public  void consultarListaDocentes() {

        Docente docente =null;
        DocentesList = new ArrayList<>();
        helper.abrir();
        Cursor cursorDocente = helper.consultarListaDocentes();

        while(cursorDocente.moveToNext()){
            docente = new Docente();
            docente.setNombdocente(cursorDocente.getString(1));
            docente.setApelldocente(cursorDocente.getString(2));
            docente.setCorreo(cursorDocente.getString(3));
            docente.setDireccion(cursorDocente.getString(4));
            docente.setNomusuario(cursorDocente.getString(5));
            DocentesList.add(docente);
        }
        obtenerListaDocente();
    }

    private void obtenerListaDocente() {
        listaDocente = new ArrayList<>();
        listaDocente.add("Seleccione");
        for (int i=0; i<DocentesList.size();i++){
            listaDocente.add(DocentesList.get(i).getNomusuario());
        }
    }

}
