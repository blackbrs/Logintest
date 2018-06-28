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

public class OpcionConsultarActivity extends AppCompatActivity {
    int id;
    int idpregunta;
    String descripopc;
    ArrayList<String> listaArea;
    ArrayList<AreaEvaluacion> AreasList;
    ArrayList<String> listaPregunta;
    ArrayList<Pregunta> PreguntasList;
    ArrayList<String> listaOpcion;
    ArrayList<Opcion> OpcionessList;
    ListView comboOpcion;
    Spinner comboArea;
    Spinner comboPreguntas;
    Button btn1;
    Button btn2;
    Button btn3;
    ControlBDHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_opcion_consultar);
        helper = new ControlBDHelper(this);
        comboArea = (Spinner) findViewById(R.id.spinnerArea);
        comboPreguntas = (Spinner) findViewById(R.id.spinnerPregunta);
        comboOpcion = (ListView)  findViewById(R.id.listViewOpcion);
        getSupportActionBar().hide();
        consultarListaArea();
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,listaArea);
        comboArea.setAdapter(adapter);

        comboArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int postition, long l) {
                if(postition!=0) {
                    id = AreasList.get(postition - 1).getIdarea();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void consultarListaArea() {
        AreaEvaluacion area1;
        ArrayList<Integer> ids= new ArrayList<>();
        AreasList = new ArrayList<AreaEvaluacion>();
        helper.abrir();
        helper.cerrar();
        helper.abrir();
        Cursor cursorArea = helper.consultarListaAreas();
        while(cursorArea.moveToNext()){
            area1 = new AreaEvaluacion();
            ids.add(cursorArea.getInt(0));
            area1.setIdarea(cursorArea.getInt(0));
            area1.setArea(cursorArea.getString(1));
            AreasList.add(area1);
        }
        obtenerListaAreas(ids);
    }

    private void obtenerListaAreas(ArrayList<Integer> idarea) {
        listaArea = new ArrayList<String>();
        listaArea.add("Seleccione");
        for (int i=0; i< AreasList.size();i++){
            listaArea.add(AreasList.get(i).getIdarea() + AreasList.get(i).getArea());
        }
    }


    public void consultarListaPreguntas(View v){
        consultarPreguntas();
        comboPreguntas.setEnabled(true);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaPregunta);
        comboPreguntas.setAdapter(adapter);
        comboPreguntas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int postition, long l) {
                if(postition != 0){
                    idpregunta=PreguntasList.get(postition-1).getIdpregunta();

                }else{

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }



    private void consultarPreguntas(){

        Pregunta preg1l;
        PreguntasList = new ArrayList<Pregunta>();
        helper.abrir();
        helper.cerrar();
        helper.abrir();
        Cursor cursorPregunta= helper.consultarListaPregunta(id);
        while(cursorPregunta.moveToNext()){
            preg1l = new Pregunta();
            preg1l.setDescrippreg(cursorPregunta.getString(0));
            preg1l.setIdpregunta(cursorPregunta.getInt(1));
            PreguntasList.add(preg1l);
        }
        obtenerListaPreguntas();
    }

    private void obtenerListaPreguntas() {
        listaPregunta = new ArrayList<String>();
        listaPregunta.add("Seleccione");
        for (int i=0; i < PreguntasList.size(); i++){
            listaPregunta.add(PreguntasList.get(i).getDescrippreg());
        }
    }

    public void filtrarRespuestas(View v){

        consultarListaRespuestas();
        ArrayAdapter<CharSequence> adapter8 = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaOpcion);
        comboOpcion.setAdapter(adapter8);
        comboOpcion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!=0) {
                    descripopc = OpcionessList.get(i - 1).getDescripcion();

                }
                else{

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    private void consultarListaRespuestas() {
        Opcion op1;
        OpcionessList = new ArrayList<Opcion>();
        helper.abrir();
        helper.cerrar();
        helper.abrir();
        Cursor cursorArea = helper.consultarListaOpciones(idpregunta);
        while(cursorArea.moveToNext()){
            op1 = new Opcion();
            op1.setIdopcion(cursorArea.getInt(0));
            op1.setDescripcion(cursorArea.getString(1));
            op1.setIdpregunta(cursorArea.getInt(2));
            OpcionessList.add(op1);
        }
        obtenerListaOpcion();
    }

    private void obtenerListaOpcion() {
        listaOpcion = new ArrayList<String>();
        listaOpcion.add("Seleccione");
        for (int i=0; i< OpcionessList.size();i++){
            listaOpcion.add(OpcionessList.get(i).getDescripcion());
        }
    }

    public void eliminarRespuesta(View v){

        String regInsertados;
        Opcion detalle = new Opcion();
        detalle.setIdpregunta(idpregunta);
        detalle.setDescripcion(descripopc);
        helper.abrir();
        regInsertados = helper.eliminar(detalle);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
}