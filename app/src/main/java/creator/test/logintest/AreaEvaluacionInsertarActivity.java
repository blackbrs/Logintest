package creator.test.logintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AreaEvaluacionInsertarActivity extends AppCompatActivity {

    ControlBDHelper helper;
    EditText editarea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_area_evaluacion_insertar);
        helper = new ControlBDHelper(this);
        editarea= (EditText) findViewById(R.id.editAreaEliminar);
    }


    public void insertarArea(View v) {

        String area = editarea.getText().toString();
        String regInsertados;

        AreaEvaluacion est1 = new AreaEvaluacion();
        est1.setArea(area);
        helper.abrir();
        regInsertados = helper.insertar(est1);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editarea.setText("");

    }
}
