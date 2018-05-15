package creator.test.logintest;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class CuestionarioActualizarActivity extends AppCompatActivity {

    int id;
    int idcuestionario;
    int idoferta;
    EditText ponderacionActualizar;
    EditText fechaActualizar;
    ArrayList<String> listaMateria;
    ArrayList<Materia> MateriasList;
    ArrayList<String> ofertaLista;
    ArrayList<OfertaAcademica> lista;
    ArrayList<String> listaCuestionario;
    ArrayList<Cuestionario> CuestionariosList;
    Spinner comboMateria;
    Spinner comboOfertas;
    Spinner comboCuestionario;
    Button btn1;
    Button btn2;
    Button btn3;
    ControlBDHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuestionario_actualizar);
        helper = new ControlBDHelper(this);

        comboMateria = (Spinner) findViewById(R.id.materiaActualizar);
        comboOfertas = (Spinner) findViewById(R.id.ofertaActualizar);
        comboCuestionario = (Spinner) findViewById(R.id.cuestionarioActualizar);
        comboMateria.setEnabled(true);
        comboOfertas.setEnabled(false);
        comboCuestionario.setEnabled(false);

        btn1 = (Button) findViewById(R.id.btnVerOferta);
        btn2 = (Button) findViewById(R.id.btnAsignarPreguntas);
        btn3 = (Button) findViewById(R.id.btnActualizarRegistro);
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
                    btn3.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        ponderacionActualizar = (EditText) findViewById(R.id.editPonderacionAct);
        fechaActualizar = (EditText) findViewById(R.id.editFechaActualizar);
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
                    btn3.setVisibility(View.INVISIBLE);

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
    public void actualizarCuestioinario(View v) {
        Cuestionario cuest1 = new Cuestionario();
        cuest1.setPonderacion(Double.parseDouble(ponderacionActualizar.getText().toString()));
        cuest1.setFechacuestionario(fechaActualizar.getText().toString());
        cuest1.setIdcuestionario(idcuestionario);
        helper.abrir();
        String estado = helper.actualizarCuestionarios(cuest1);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void asignarPreguntas(View v) {

        comboCuestionario.setEnabled(true);
        consultarListaCuestionarios();
        ArrayAdapter<CharSequence> adapter3 = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,listaCuestionario);
        comboCuestionario.setAdapter(adapter3);

        comboCuestionario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i!=0){
                    idcuestionario = CuestionariosList.get(i - 1).getIdcuestionario();
                    btn3.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}