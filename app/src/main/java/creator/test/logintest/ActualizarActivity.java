package creator.test.logintest;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.SQLOutput;

public class ActualizarActivity extends AppCompatActivity {

    String idPregunta;
    String descripcionOpcion;
    EditText actualizador;
    Spinner actualizadorVF;
    ControlBDHelper helper;
    String[] menu ={"Verdadero","Falso"};
    int x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);
        Intent i = getIntent();
        idPregunta=i.getStringExtra("EXTRA_MESSAGE_1");
        descripcionOpcion=i.getStringExtra("EXTRA_MESSAGE_2");
        actualizador = (EditText) findViewById(R.id.actualizarOp);
        actualizadorVF = (Spinner) findViewById(R.id.actualizadorVFSpinner);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,menu);
        actualizadorVF.setVisibility(View.INVISIBLE);
        helper = new ControlBDHelper(this);
        x=helper.consultarOpcion(idPregunta,descripcionOpcion);
        System.out.println("MENSAJE 1:"+ x);
        if(descripcionOpcion.equals("Verdadero") || descripcionOpcion.equals("Falso")){
            actualizador.setVisibility(View.INVISIBLE);
            actualizadorVF.setVisibility(View.VISIBLE);
            actualizadorVF.setAdapter(adapter);
        }else{
            actualizador.setVisibility(View.VISIBLE);
            actualizadorVF.setVisibility(View.INVISIBLE);

        }
    }


    public void actualizarOpcion(View v){
        if(descripcionOpcion.equals("Verdadero") || descripcionOpcion.equals("Falso")){

        }else{
            String mensaje = actualizador.getText().toString();
            String regActualizados=null;
            regActualizados=helper.actualizar(mensaje, x, idPregunta);
            Toast.makeText(getApplicationContext(),regActualizados,Toast.LENGTH_LONG).show();
        }

    }
}
