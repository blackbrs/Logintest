package creator.test.logintest;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
//import android.content.Intent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MenuAdminActivity extends AppCompatActivity implements ListView.OnItemClickListener{
    Button botonWS;
    ListView comboCue;
    ControlBDHelper helper;
    String[] menu={"Gestionar Docente","Gestionar Alumno","Gestionar Ciclo","Gestionar Materia","Oferta Academica"};
    String[] activities={"DocenteMenuActivity", "EstudianteMenuActivity","CicloMenuActivity","MateriaMenuActivity","OfertaAcademicaInsertarActivity"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_menu_admin);
        helper = new ControlBDHelper(this);
        comboCue = (ListView) findViewById(R.id.listiD);
        ArrayAdapter<CharSequence> adapter3 = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,menu);
        comboCue.setAdapter(adapter3);
        comboCue.setOnItemClickListener(this);
        botonWS = (Button) findViewById(R.id.btnWS);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String nombreValue = activities[i];
        try{
            Class<?> clase = Class.forName("creator.test.logintest." + nombreValue);
            Intent inte = new Intent(this,clase);
            startActivity(inte);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void crudWS(View view){
        Intent a = new Intent (this,estudianteWS.class);
        startActivity(a);
    }

}
