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

public class OpcionActualizarActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    int id;
    int idpreguntaUP;
    String descripopcUP;
    ArrayList<String> listaAreaUP;
    ArrayList<AreaEvaluacion> AreasListUP;
    ArrayList<String> listaPreguntaUP;
    ArrayList<Pregunta> PreguntasListUP;
    ArrayList<String> listaOpcionUP;
    ArrayList<Opcion> OpcionessListUP;
    ListView comboOpcionUP;
    Spinner comboAreaUP;
    Spinner comboPreguntasUP;
    Button btn1UP;
    Button btn2UP;
    Button btn3UP;
    ControlBDHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_opcion_consultar);
        helper = new ControlBDHelper(this);
        comboAreaUP = (Spinner) findViewById(R.id.spinnerArea);
        comboPreguntasUP = (Spinner) findViewById(R.id.spinnerPregunta);
        comboOpcionUP = (ListView)  findViewById(R.id.listViewOpcion);
        getSupportActionBar().hide();
        consultarListaArea();
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,listaAreaUP);
        comboAreaUP.setAdapter(adapter);

        comboAreaUP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int postition, long l) {
                if(postition!=0) {
                    id = AreasListUP.get(postition - 1).getIdarea();
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
        AreasListUP = new ArrayList<AreaEvaluacion>();
        helper.abrir();
        helper.cerrar();
        helper.abrir();
        Cursor cursorArea = helper.consultarListaAreas();
        while(cursorArea.moveToNext()){
            area1 = new AreaEvaluacion();
            ids.add(cursorArea.getInt(0));
            area1.setIdarea(cursorArea.getInt(0));
            area1.setArea(cursorArea.getString(1));
            AreasListUP.add(area1);
        }
        obtenerListaAreas(ids);
    }


    private void obtenerListaAreas(ArrayList<Integer> idarea) {
        listaAreaUP = new ArrayList<String>();
        listaAreaUP.add("Seleccione");
        for (int i=0; i< AreasListUP.size();i++){
            listaAreaUP.add(AreasListUP.get(i).getIdarea() + AreasListUP.get(i).getArea());
        }
    }


    public void consultarListaPreguntas(View v){
        consultarPreguntas();
        comboPreguntasUP.setEnabled(true);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaPreguntaUP);
        comboPreguntasUP.setAdapter(adapter);
        comboPreguntasUP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int postition, long l) {
                if(postition != 0){
                    idpreguntaUP=PreguntasListUP.get(postition-1).getIdpregunta();

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
        PreguntasListUP = new ArrayList<Pregunta>();
        helper.abrir();
        helper.cerrar();
        helper.abrir();
        Cursor cursorPregunta= helper.consultarListaPregunta(id);
        while(cursorPregunta.moveToNext()){
            preg1l = new Pregunta();
            preg1l.setDescrippreg(cursorPregunta.getString(0));
            preg1l.setIdpregunta(cursorPregunta.getInt(1));
            PreguntasListUP.add(preg1l);
        }
        obtenerListaPreguntas();
    }

    private void obtenerListaPreguntas() {
        listaPreguntaUP = new ArrayList<String>();
        listaPreguntaUP.add("Seleccion");
        for (int i=0; i < PreguntasListUP.size(); i++){
            listaPreguntaUP.add(PreguntasListUP.get(i).getDescrippreg());
        }
    }

    public void filtrarRespuestas(View v){
        consultarListaRespuestas();
        ArrayAdapter<CharSequence> adapter8 = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaOpcionUP);
        comboOpcionUP.setAdapter(adapter8);
        comboOpcionUP.setOnItemClickListener(this);
    }
    private void consultarListaRespuestas() {
        Opcion op1;
        OpcionessListUP = new ArrayList<Opcion>();
        helper.abrir();
        helper.cerrar();
        helper.abrir();
        Cursor cursorArea = helper.consultarListaOpciones(idpreguntaUP);
        while(cursorArea.moveToNext()){
            op1 = new Opcion();
            op1.setIdopcion(cursorArea.getInt(0));
            op1.setDescripcion(cursorArea.getString(1));
            op1.setIdpregunta(cursorArea.getInt(2));
            OpcionessListUP.add(op1);
        }
        obtenerListaOpcion();
    }

    private void obtenerListaOpcion() {
        listaOpcionUP = new ArrayList<String>();
        listaOpcionUP.add("Seleccion");
        for (int i=0; i< OpcionessListUP.size();i++){
            listaOpcionUP.add(OpcionessListUP.get(i).getDescripcion());
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position != 0){
            descripopcUP = OpcionessListUP.get(position - 1).getDescripcion();
            Intent intentoActualizar = new Intent(getApplicationContext(), ActualizarActivity.class);
            intentoActualizar.putExtra("EXTRA_MESSAGE_1", String.valueOf(idpreguntaUP));
            intentoActualizar.putExtra("EXTRA_MESSAGE_2", descripopcUP);
            startActivity(intentoActualizar);
        }
    }
}
