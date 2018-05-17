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
    int x=0;
    int idpregunta;
    int tipodepregunta;
    String ver;
    boolean valida;
    String[] tipo={"Selecione","Verdadero","Falso"};
    String[] tipoVal={"Correcta","Incorrecta"};
    ControlBDHelper helper;
    ArrayList<String> listaArea;
    ArrayList<AreaEvaluacion> AreasList;
    ArrayList<String> listaPregunta;
    ArrayList<Pregunta> PreguntasList;
    EditText descripcion;
    Spinner comboPreguntas;
    Spinner comboArea;
    Spinner tipoPregunta;
    Spinner validador;
    Button btn1;
    Button btn2;
    int id2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        helper = new ControlBDHelper(this);
        setContentView(R.layout.activity_opcion_insertar);
        comboArea = (Spinner) findViewById(R.id.SpinnerAreaOpcion);
        comboPreguntas = (Spinner) findViewById(R.id.SpinnerPregOpcion);
        validador = (Spinner) findViewById(R.id.spinnerValidacion);
        ArrayAdapter<CharSequence> adapte6 = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,tipoVal);
        validador.setAdapter(adapte6);
        validador.setVisibility(View.INVISIBLE);
        tipoPregunta =(Spinner) findViewById(R.id.spinnerTipo);
        ArrayAdapter<CharSequence> adapter5 = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,tipo);
        tipoPregunta.setAdapter(adapter5);
        tipoPregunta.setVisibility(View.INVISIBLE);
        comboPreguntas.setEnabled(false);
        btn1 = (Button) findViewById(R.id.btnFiltroPreguntaOpinion);
        btn2 = (Button) findViewById(R.id.Agregar);
        descripcion = (EditText) findViewById(R.id.editRespuesta);
        descripcion.setVisibility(View.INVISIBLE);


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

        validador.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    valida = true;
                }else{
                    valida = false;
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
            preg1l.setTipo(cursorPregunta.getInt(2));
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
                    tipodepregunta=PreguntasList.get(postition-1).getTipo();
                    validador.setVisibility(View.VISIBLE);
                    x=0;
                    if(PreguntasList.get(postition-1).getTipo()==1) {
                        descripcion.setVisibility(View.VISIBLE);
                        btn2.setVisibility(View.VISIBLE);
                        tipoPregunta.setVisibility(View.INVISIBLE);
                    }else{
                        tipoPregunta.setVisibility(View.VISIBLE);
                        descripcion.setVisibility(View.INVISIBLE);
                        btn2.setVisibility(View.VISIBLE);
                    }
                }else{
                    btn2.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        tipoPregunta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        break;
                    case 1:
                        ver= "Verdadero";
                        break;
                    case 2:
                        ver="Falso";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public void AsignarRespuesta(View v){
        helper.abrir();
        System.out.println(ver);
     /*   Cursor cursorauxiliar = helper.db.rawQuery("SELECT esCorrecta FROM opcion WHERE idpregunta="+idpregunta,null);

        if(cursorauxiliar.moveToNext()){
            System.out.println(cursorauxiliar.getInt(0));
            if(cursorauxiliar.getInt(0)==1){
                x++;
            }
        }*/
        System.out.println("INCREMENTA LA X="+ x);
        if (tipodepregunta == 1) {

            if (x == 0 && valida == true) {
                String regInsertados;
                Opcion opcion = new Opcion();
                opcion.setIdpregunta(idpregunta);
                opcion.setDescripcion(descripcion.getText().toString());
                System.out.println(descripcion.getText().toString());
                opcion.setIsCorrect(valida);
                System.out.println("ESTA ES LA CORRECTA");
                helper.abrir();
                regInsertados = helper.insertar(opcion);
                helper.cerrar();
                Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
                x++;
            } else if (x!= 0 && valida == false) {
                String regInsertados;
                Opcion opcion = new Opcion();
                opcion.setIdpregunta(idpregunta);
                opcion.setDescripcion(descripcion.getText().toString());
                System.out.println(descripcion.getText().toString());
                opcion.setIsCorrect(valida);
                helper.abrir();
                regInsertados = helper.insertar(opcion);
                helper.cerrar();
                Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
            }else if(x==0 && valida==false){
                String regInsertados;
                Opcion opcion = new Opcion();
                opcion.setIdpregunta(idpregunta);
                opcion.setDescripcion(descripcion.getText().toString());
                System.out.println(descripcion.getText().toString());
                opcion.setIsCorrect(valida);
                helper.abrir();
                regInsertados = helper.insertar(opcion);
                helper.cerrar();
                Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
            }
        }else{
            if (x == 0 && valida == true) {
                String regInsertados;
                Opcion opcion = new Opcion();
                opcion.setIdpregunta(idpregunta);
                opcion.setDescripcion(ver);
                opcion.setIsCorrect(valida);
                helper.abrir();
                regInsertados = helper.insertar(opcion);
                helper.cerrar();
                Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
            } else if (x != 0 && valida == false) {
                String regInsertados;
                Opcion opcion = new Opcion();
                opcion.setIdpregunta(idpregunta);
                opcion.setDescripcion(ver);
                opcion.setIsCorrect(valida);
                helper.abrir();
                regInsertados = helper.insertar(opcion);
                helper.cerrar();
                Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
            }else if(x==0 && valida==false){
                String regInsertados;
                Opcion opcion = new Opcion();
                opcion.setIdpregunta(idpregunta);
                opcion.setDescripcion(ver);
                opcion.setIsCorrect(valida);
                helper.abrir();
                regInsertados = helper.insertar(opcion);
                helper.cerrar();
                Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
