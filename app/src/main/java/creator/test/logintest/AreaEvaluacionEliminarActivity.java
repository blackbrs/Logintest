package creator.test.logintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AreaEvaluacionEliminarActivity extends AppCompatActivity {
    EditText edittipoarea;
    ControlBDHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_area_evaluacion_eliminar);
        edittipoarea = (EditText) findViewById(R.id.editAreaEliminar);
        helper = new ControlBDHelper(this);
    }


    public void eliminarArea(View v){
        String regAfectados;
        String area=edittipoarea.getText().toString();
        AreaEvaluacion est1=new AreaEvaluacion();
        est1.setArea(area);
        helper.abrir();
        regAfectados=helper.eliminar(est1);
        helper.cerrar();
        Toast.makeText(this, regAfectados, Toast.LENGTH_SHORT).show();
    }
}
