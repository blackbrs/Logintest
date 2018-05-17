package creator.test.logintest;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class NotasActivity extends AppCompatActivity {
    int id;
    ArrayList<String> listaRespuestas;
    ArrayList<Respuesta> RespuestasList;
    ControlBDHelper helper;
    ListView listaNotas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_notas);
        helper = new ControlBDHelper(this);
        Intent i = getIntent();
        listaNotas = (ListView) findViewById(R.id.listadoNotas);
        id=Integer.parseInt(i.getStringExtra("idcuestionario"));
        consultarListaRespuesta();
        ArrayAdapter<CharSequence> adapter3 = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,listaRespuestas);
        listaNotas.setAdapter(adapter3);
    }

    public void consultarListaRespuesta() {
        Respuesta respuesta =null;
        RespuestasList = new ArrayList<Respuesta>();
        helper.abrir();
        helper.cerrar();
        helper.abrir();
        Cursor cursorRespuesta = helper.consultarListaRespuesta(id);
        while(cursorRespuesta.moveToNext()){
            System.out.println("CARNET"+cursorRespuesta.getInt(0));
            respuesta = new Respuesta();
            respuesta.setIdcuestionario(id);
            respuesta.setCarnet(cursorRespuesta.getString(0));
            respuesta.setNota(cursorRespuesta.getDouble(1));
            RespuestasList.add(respuesta);
        }
        obtenerListaRespuesta();
    }

    public void obtenerListaRespuesta(){
        listaRespuestas = new ArrayList<>();
        for (int i=0; i<RespuestasList.size();i++){
            listaRespuestas.add(RespuestasList.get(i).getCarnet()+"       "+RespuestasList.get(i).getNota());
        }
    }
}
