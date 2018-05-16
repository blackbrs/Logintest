package creator.test.logintest;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuEstudianteActivity extends ListActivity {
    String[] menu={"Inscribir Cursos","Realizar  cuestionario"};
    String[] activities={"EstudianteInscribirActivity", "RealizarExamenActivity"};
    ControlBDHelper BDHelper;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menu));
        Intent intento = getIntent();
        user = intento.getStringExtra("Usuario");
        System.out.println(user);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l,v,position,id);

        String nombreValue = activities[position];
        try{
            Class<?> clase = Class.forName("creator.test.logintest." + nombreValue);
            Intent inte = new Intent(this,clase);
            inte.putExtra("Usuario",user);
            startActivity(inte);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
