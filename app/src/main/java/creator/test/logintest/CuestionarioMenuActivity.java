package creator.test.logintest;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CuestionarioMenuActivity extends ListActivity {
    String[] menu = {"Crear Cuestionario", "Eliminar Cuestionario", "Consultar Cuestionario", "Actualizar cuestionario", "Añadir preguntas"};
    String[] acciones = {"CuestionarioInsertarActivity", "CuestionarioEliminarActivity", "CuestionarioConsultarActivity" ,"CuestionarioActualizarActivity","AsignarPreguntasCuestionarioActivity"};
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, menu);
        setListAdapter(adapter);
        user = getIntent().getStringExtra("Usuario");
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String nomValue = acciones[position];
        try {
            Class<?> clase = Class.forName("creator.test.logintest." + nomValue);
            Intent inte = new Intent(this,clase);
            inte.putExtra("Usuario",user);
            startActivity(inte);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
