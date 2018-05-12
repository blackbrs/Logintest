package creator.test.logintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MateriaEliminarActivity extends AppCompatActivity {
    ControlBDHelper helper;
    EditText editCod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_materia_eliminar);
        helper = new ControlBDHelper(this);
        editCod = (EditText) findViewById(R.id.editCodMateria);
    }

    public void eliminarMateria(View v){
        String regAfectados;
        String user=editCod.getText().toString();
        Materia est1=new Materia();
        est1.setCodmateria(user);
        helper.abrir();
        regAfectados=helper.eliminar(est1);
        helper.cerrar();
        Toast.makeText(this, regAfectados, Toast.LENGTH_SHORT).show();
    }

}
