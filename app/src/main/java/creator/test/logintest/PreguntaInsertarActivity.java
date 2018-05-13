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

public class PreguntaInsertarActivity extends AppCompatActivity {
    Spinner comboArea;
    Spinner comboTipoPregunta;
    ControlBDHelper helper;
    ArrayList<String> listaArea;
    ArrayList<AreaEvaluacion> AreasList;
    String id;
    String [] tipoPreguntaLista={"1.Opcion Multiple", "2.Verdadero o Falso"};
    ArrayList<Integer> listatipo= new ArrayList<Integer>();
    int tipoPregunta;
    EditText ponderacion;
    EditText descrippreg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        listatipo.add(1);
        listatipo.add(2);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_pregunta_insertar);
        helper = new ControlBDHelper(this);
        comboArea = (Spinner) findViewById(R.id.spinnerAreaEvaluacion);
        comboTipoPregunta = (Spinner) findViewById(R.id.spinnerTipoPregunta);
        consultarListaArea();
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,listaArea);
        comboArea.setAdapter(adapter);

        comboArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {
                if(position!=0) {
                    id = String.valueOf(AreasList.get(position-1).getIdarea());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        ArrayAdapter<CharSequence> adapatado1 = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,tipoPreguntaLista);
        comboTipoPregunta.setAdapter(adapatado1);



        comboTipoPregunta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        tipoPregunta = listatipo.get(i);
                        System.out.println(tipoPregunta);
                        break;
                    case 1:
                        tipoPregunta = listatipo.get(i);
                        System.out.println(tipoPregunta);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ponderacion = (EditText) findViewById(R.id.editPonde);
        descrippreg =(EditText) findViewById(R.id.editDec);
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

    public void insertarPregunta(View v) {

        int pondera = Integer.parseInt(ponderacion.getText().toString());
        String descripcion = descrippreg.getText().toString();
        int idArea= Integer.parseInt(id);
        int tipo = tipoPregunta;
        String regInsertados;
        Pregunta pr1 = new Pregunta();
        pr1.setIdarea(idArea);
        pr1.setPonderapregunta(pondera);
        pr1.setDescrippreg(descripcion);
        pr1.setTipo(tipo);
        helper.abrir();
        regInsertados = helper.insertar(pr1);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        ponderacion.setText("");
        descrippreg.setText("");


    }




}



