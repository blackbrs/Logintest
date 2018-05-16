package creator.test.logintest;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class EstudianteInscribirActivity extends AppCompatActivity {
    int id;
    int idcuestionario;
    int idoferta;
    ArrayList<String> listaMateria;
    ArrayList<Materia> MateriasList;
    ArrayList<String> ofertaLista;
    ArrayList<OfertaAcademica> lista;
    Spinner comboMateria;
    Spinner comboOfertas;
    Button btn1;
    Button btn2;
    Button btn3;
    ControlBDHelper helper;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_inscribir);

        helper = new ControlBDHelper(this);

        comboMateria = (Spinner) findViewById(R.id.materiaConsultar);
        comboOfertas = (Spinner) findViewById(R.id.ofertaConsultar);
        comboMateria.setEnabled(true);
        comboOfertas.setEnabled(false);

        btn1 = (Button) findViewById(R.id.btnVerOferta);
        btn2 = (Button) findViewById(R.id.btnInscribirMateria);

        btn1.setEnabled(true);
        btn2.setEnabled(false);
        consultarListaMateria();
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,listaMateria);
        comboMateria.setAdapter(adapter);
        comboMateria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!=0) {
                    id = MateriasList.get(i - 1).getIdmateria();
                    btn1.setVisibility(View.VISIBLE);
                }else{
                    btn1.setVisibility(View.INVISIBLE);
                    btn2.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        Intent i = getIntent();
        user = i.getStringExtra("Usuario");
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
            materia.setIdmateria(cursorMateria.getInt(0));
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


    public void consultarListaOfertaX() {
        OfertaAcademica ofertaacademica;
        lista = new ArrayList<OfertaAcademica>();
        helper.abrir();
        helper.cerrar();
        helper.abrir();
        Cursor cursor = helper.consultarListaOfertaMateria(id);
        while(cursor.moveToNext()) {
            helper.abrir();
            ofertaacademica = new OfertaAcademica();
            ofertaacademica.setIdoferta(cursor.getInt(0));
            ofertaacademica.setDescripcion(cursor.getString(1));
            lista.add(ofertaacademica);
        }

        obtenerListaOferta();
    }

    private void obtenerListaOferta() {
        ofertaLista = new  ArrayList<String>();
        ofertaLista.add("Seleccione");
        for (int i=0 ; i <lista.size();i++){
            System.out.println("#########################");
            System.out.println("#########################");
            System.out.println("#########################");
            System.out.println("#########################");
            System.out.println(lista.get(i).getIdoferta());
            System.out.println(lista.get(i).getDescripcion());
            System.out.println("#########################");
            System.out.println("#########################");
            System.out.println("#########################");
            System.out.println("#########################");
            ofertaLista.add(lista.get(i).getIdoferta() + " " +lista.get(i).getDescripcion());
        }
    }

    public  void verOferta(View v){
        btn2.setEnabled(true);
        comboOfertas.setEnabled(true);
        consultarListaOfertaX();
        ArrayAdapter<CharSequence> adapter1 = new ArrayAdapter(this,android.R.layout.simple_list_item_1,ofertaLista);
        comboOfertas.setAdapter(adapter1);
        comboOfertas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    idoferta = lista.get(i-1).getIdoferta();
                    System.out.println("ESTE ES ID DE OFERTA:");
                    System.out.println(idoferta);
                    btn2.setVisibility(View.VISIBLE);
                }else{
                    btn2.setVisibility(View.INVISIBLE);

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    public void asignarPreguntas(View v) {

        String regInsertados;
        DetalleEstudiante detalle = new DetalleEstudiante();
        detalle.setIdoferta(idoferta);
        detalle.setCarnet(user);
        helper.abrir();
        regInsertados = helper.insertar(detalle);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

    }
}
