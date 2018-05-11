package creator.test.logintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EstudianteEliminarActivity extends AppCompatActivity {
    EditText editCarnet;
    ControlBDHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_estudiante_eliminar);
        editCarnet = (EditText) findViewById(R.id.editCarne);
        helper = new ControlBDHelper(this);
    }


    public void eliminarEstudiante(View v){
        String regAfectados;
        String user=editCarnet.getText().toString();
        Estudiante est1=new Estudiante();
        est1.setCarnet(user);
        helper.abrir();
        regAfectados=helper.eliminar(est1);
        helper.cerrar();
        Toast.makeText(this, regAfectados, Toast.LENGTH_SHORT).show();
    }
}