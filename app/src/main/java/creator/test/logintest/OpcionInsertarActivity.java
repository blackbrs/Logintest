package creator.test.logintest;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class OpcionInsertarActivity extends AppCompatActivity {
    int id;
    int idpregunta;
    ControlBDHelper helper;
    ArrayList<String> listaArea;
    ArrayList<AreaEvaluacion> AreasList;
    ArrayList<String> listaPregunta;
    ArrayList<Pregunta> PreguntasList;
    EditText descripcion;
    Spinner comboPreguntas;
    Spinner comboArea;
    Button btn1;
    Button btn2;
    RadioButton radio1;
    RadioButton radio2;
    RadioGroup grupo;
    int id2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        helper = new ControlBDHelper(this);
        setContentView(R.layout.activity_opcion_insertar);
        comboArea = (Spinner) findViewById(R.id.SpinnerAreaOpcion);
        comboPreguntas = (Spinner) findViewById(R.id.SpinnerPregOpcion);
        comboPreguntas.setEnabled(false);
        btn1 = (Button) findViewById(R.id.btnFiltroPreguntaOpinion);
        btn2 = (Button) findViewById(R.id.Agregar);
        descripcion = (EditText) findViewById(R.id.editRespuesta);
        grupo = (RadioGroup) findViewById(R.id.grupoCorrecta);



        consultarListaArea();
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,listaArea);
        comboArea.setAdapter(adapter);

        comboArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int postition, long l) {
                if(postition!=0) {
                    id = AreasList.get(postition - 1).getIdarea();
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

    public void consultarListaPreguntas(View v){
        consultarPreguntas();
        comboPreguntas.setEnabled(true);
        btn2.setEnabled(true);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaPregunta);
        comboPreguntas.setAdapter(adapter);
        comboPreguntas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int postition, long l) {
                if(postition != 0){
                    idpregunta=PreguntasList.get(postition-1).getIdpregunta();
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

    public void AsignarRespuesta(View v){
        String regInsertados;
        Opcion opcion = new Opcion();
        opcion.setIdpregunta(idpregunta);
        opcion.setDescripcion(descripcion.getText().toString());
        id2 = grupo.getCheckedRadioButtonId();
        if(id2==-1){

        }else{
            radio1= findViewById(id2);
        }
        if(radio1.getText().toString()=="Correcta"){
            opcion.setIsCorrect(1);
        }else{
            opcion.setIsCorrect(0);
        }

        helper.abrir();
        regInsertados = helper.insertar(opcion);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
}
