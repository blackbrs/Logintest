package creator.test.logintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DocenteEliminarActivity extends AppCompatActivity {

    EditText editDocente;
    ControlBDHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_docente_eliminar);
        helper = new ControlBDHelper(this);
        editDocente=(EditText) findViewById(R.id.editUsuario);
    }


    public void eliminarDocente(View v){
        String regAfectados;
        String user=editDocente.getText().toString();
        Docente profesor=new Docente();
        profesor.setNomusuario(user);

        helper.abrir();
        regAfectados=helper.eliminar(profesor);
        helper.cerrar();
        Toast.makeText(this, regAfectados, Toast.LENGTH_SHORT).show();
    }
}
