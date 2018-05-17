package creator.test.logintest;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import android.widget.ListView;

public class AreaEvaluacionMenuActivity extends ListActivity {
    String[] menu = {"Ingresar area de evaluacion", "Eliminar area de evaluacion"};
    String[] activities = {"AreaEvaluacionInsertarActivity" , "AreaEvaluacionEliminarActivity"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menu);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String nomValue = activities[position];
        try {
            Class<?> clase = Class.forName("creator.test.logintest." + nomValue);
            Intent inte = new Intent(this,clase);
            startActivity(inte);
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
