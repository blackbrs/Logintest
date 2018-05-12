package creator.test.logintest;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class OfertaAcademicaInsertarActivity extends AppCompatActivity {
    ControlBDHelper helper;
    Spinner comboMaterias;
    Spinner comboCiclo;
    Spinner comboDocente;
    ArrayList<String> listaMateria;
    ArrayList<Materia> MateriasList;
    ArrayList<String> listaCiclo;
    ArrayList<Ciclo> CiclosList;
    ArrayList<String> listaDocente;
    ArrayList<Docente> DocentesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oferta_academica_insertar);
        helper = new ControlBDHelper(this);
        comboMaterias = (Spinner) findViewById(R.id.comboMaterias);
        comboCiclo= (Spinner) findViewById(R.id.comboCiclo);
        comboDocente= (Spinner) findViewById(R.id.comboDocente);
        consultarListaMateria();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,listaMateria);
        comboMaterias.setAdapter(adaptador);

        consultarListaCiclo();
        ArrayAdapter<CharSequence> adaptador1 = new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,listaCiclo);
        comboCiclo.setAdapter(adaptador1);

        consultarListaDocentes();
        ArrayAdapter<CharSequence> adaptador2 = new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,listaDocente);
        comboDocente.setAdapter(adaptador2);
    }

    public void consultarListaMateria() {
        Materia materia =null;
        int idciclo=0;
        MateriasList = new ArrayList<Materia>();
        helper.abrir();
        helper.cerrar();
        helper.abrir();
        Cursor cursorMateria = helper.consultarListaMaterias();
        while(cursorMateria.moveToNext()){
            materia = new Materia();
            idciclo=cursorMateria.getInt(0);
            materia.setCodmateria(cursorMateria.getString(1));
            materia.setUnidadval(cursorMateria.getInt(2));
            materia.setNombremat(cursorMateria.getString(3));
            System.out.println(materia.getCodmateria());
            System.out.println(materia.getNombremat());
            MateriasList.add(materia);
        }
        obtenerListaMateria(idciclo);
    }

    public void obtenerListaMateria(int id){
        listaMateria = new ArrayList<>();
        listaMateria.add("Seleccione");
        for (int i=0; i<MateriasList.size();i++){
            listaMateria.add(String.valueOf(id)+" "+MateriasList.get(i).getCodmateria());
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
