package creator.test.logintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DocenteEliminarActivity extends AppCompatActivity {

    EditText editUser;
    ControlBDHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_docente_eliminar);
        editUser=(EditText) findViewById(R.id.editUsuario);
    }


    public void eliminarDocente(View v){
        String regEliminados;
        String nomusuario=editUser.getText().toString();
        Docente docente = new Docente();
        docente.setNomusuario(nomusuario);
        helper.abrir();
        regEliminados=helper.eliminar(docente);
        helper.cerrar();
        Toast.makeText(this,regEliminados,Toast.LENGTH_SHORT).show();
    }
}
