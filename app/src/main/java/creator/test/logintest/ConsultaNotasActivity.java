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

import java.util.ArrayList;

public class ConsultaNotasActivity extends AppCompatActivity implements ListView.OnItemClickListener {
    int id;
    int idcuestionario;
    int idoferta;
    String user;
    ArrayList<String> listaMateria;
    ArrayList<Materia> MateriasList;
    ArrayList<String> ofertaLista;
    ArrayList<OfertaAcademica> lista;
    ArrayList<String> listaCuestionario;
    ArrayList<Cuestionario> CuestionariosList;
    Spinner comboMateria;
    Spinner comboOfertas;
    ListView comboCuestionario;
    Button btn1;
    Button btn2;
    Button btn3;
    ControlBDHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_consulta_notas);
        helper = new ControlBDHelper(this);
        Intent i = getIntent();
        user = i.getStringExtra("Usuario");
        comboMateria = (Spinner) findViewById(R.id.materiaConsultar);
        comboOfertas = (Spinner) findViewById(R.id.ofertaConsultar);
        comboCuestionario = (ListView) findViewById(R.id.cuestionarioConsultar);
        comboMateria.setEnabled(true);
        comboOfertas.setEnabled(false);
        comboCuestionario.setEnabled(false);

        btn1 = (Button) findViewById(R.id.btnVerOferta);
        btn2 = (Button) findViewById(R.id.btnAsignarPreguntas);

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

    public void consultarListaCuestionarios(){
        Cuestionario cuestionario = null;
        CuestionariosList = new ArrayList<Cuestionario>();
        helper.abrir();
        helper.cerrar();
        helper.abrir();
        Cursor cursorCuestionario = helper.consultarListaCuestionario(idoferta);
        while(cursorCuestionario.moveToNext()){
            System.out.println(cursorCuestionario.getInt(0));
            System.out.println(cursorCuestionario.getString(1));
            System.out.println(cursorCuestionario.getDouble(2));
            cuestionario = new Cuestionario();
            cuestionario.setIdoferta(idoferta);
            cuestionario.setIdcuestionario(cursorCuestionario.getInt(0));
            cuestionario.setDescricuestinario(cursorCuestionario.getString(1));
            cuestionario.setPonderacion(Double.parseDouble(cursorCuestionario.getString(2)));
            CuestionariosList.add(cuestionario);
        }
        obtenerListaCuestionario();
    }

    private void obtenerListaCuestionario() {
        listaCuestionario = new ArrayList<String>();
        for (int i=0; i< CuestionariosList.size();i++){
            listaCuestionario.add(CuestionariosList.get(i).getDescricuestinario());
        }
    }

    public void asignarPreguntas(View v) {
        comboCuestionario.setEnabled(true);
        consultarListaCuestionarios();
        ArrayAdapter<CharSequence> adapter3 = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,listaCuestionario);
        comboCuestionario.setAdapter(adapter3);
        comboCuestionario.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        System.out.println("ID CUESTIONARIO");
        idcuestionario=CuestionariosList.get(i).getIdcuestionario();
        Intent nuevoForm = new Intent(this,NotasActivity.class);
        nuevoForm.putExtra("idcuestionario",String.valueOf(idcuestionario));
        nuevoForm.putExtra("materia",id);
        nuevoForm.putExtra("idoferta",idoferta);
        nuevoForm.putExtra("Usuario",user);
        startActivity(nuevoForm);
    }
}
