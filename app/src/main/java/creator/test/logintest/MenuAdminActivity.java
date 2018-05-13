package creator.test.logintest;

import android.support.v7.app.AppCompatActivity;
import android.app.ListActivity;
//import android.content.Intent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View;
import android.widget.ListView;

public class MenuAdminActivity extends ListActivity {

    String[] menu={"Gestionar Docente","Gestionar Alumno","Oferta Academica","Gestionar Ciclo","Gestionar Materia"};
    String[] activities={"DocenteMenuActivity", "EstudianteMenuActivity","OfertaAcademicaInsertarActivity","CicloMenuActivity","MateriaMenuActivity"};
    ControlBDHelper BDHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menu));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l,v,position,id);

        String nombreValue = activities[position];
        try{
            Class<?> clase = Class.forName("creator.test.logintest." + nombreValue);
            Intent inte = new Intent(this,clase);
            startActivity(inte);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
