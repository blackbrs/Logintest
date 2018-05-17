package creator.test.logintest;

import android.content.Intent;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DocenteMenuActivity extends ListActivity {
    String[] menu ={"Insertar Registro","Eliminar Registro","Consultar Registro","Actualizar Registro"};
    String[] activities ={"DocenteInsertarActivity", "DocenteEliminarActivity", "DocenteConsultarActivity", "DocenteActualizarActivity"};
    String user;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu);
        setListAdapter(adapter);
        user = getIntent().getStringExtra("Usuario");
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l,v,position,id);

        String nombreValue= activities[position];
        try {
            Class<?> clase=Class.forName("creator.test.logintest." + nombreValue);
            Intent inte= new Intent(this, clase);
            inte.putExtra("Usuario", user);
            this.startActivity(inte);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
