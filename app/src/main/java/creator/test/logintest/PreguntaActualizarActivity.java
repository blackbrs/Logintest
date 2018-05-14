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

public class PreguntaActualizarActivity extends AppCompatActivity {
    Spinner listaPreguntas;
    Spinner comboArea;
    ControlBDHelper helper;
    ArrayList<String> listaArea;
    ArrayList<AreaEvaluacion> AreasList;
    ArrayList<String> listaPregunta;
    ArrayList<Pregunta> PreguntasList;
    EditText descripcion;
    EditText pond;
    Button btn;
    int id;
    String auxDescrip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = new ControlBDHelper(this);
        setContentView(R.layout.activity_pregunta_actualizar);
        comboArea = (Spinner) findViewById(R.id.spinnerArea2);
        btn = (Button) findViewById(R.id.botonActualizar);
        btn.setEnabled(false);
        pond= (EditText) findViewById(R.id.ponderaciontxt);
        pond.setEnabled(false);
        listaPreguntas = (Spinner) findViewById(R.id.spinnerPregunta);
        listaPreguntas.setEnabled(false);
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

    public void consultarListaPreguntas(View v){
        consultarPreguntas();
        listaPreguntas.setEnabled(true);
        pond.setEnabled(true);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaPregunta);
        listaPreguntas.setAdapter(adapter);
        btn.setEnabled(true);
        listaPreguntas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int postition, long l) {
                if(postition != 0){
                auxDescrip=PreguntasList.get(postition-1).getDescrippreg();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public  void finalizarEdicion(View v){
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

    public void actualizarMateria(View v) {
        Pregunta est1 = new Pregunta();
        est1.setDescrippreg(auxDescrip);
        System.out.println(auxDescrip);
        est1.setPonderapregunta(Integer.parseInt(pond.getText().toString()));
        est1.setIdarea(id);
        helper.abrir();
        String estado = helper.actualizarPregunta(est1);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();

    }

}

