package creator.test.logintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MateriaActualizarActivity extends AppCompatActivity {
    ControlBDHelper helper;
    EditText editCod;
    EditText editUnidadesVal;
    EditText editNombreMateria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_materia_actualizar);
        helper = new ControlBDHelper(this);
        editCod = (EditText) findViewById(R.id.editCod);
        editUnidadesVal = (EditText) findViewById(R.id.editUnidadesValorativa);
        editNombreMateria = (EditText) findViewById(R.id.editNombremateria);
    }

    public void actualizarMateria(View v) {
        Materia est1 = new Materia();
        est1.setCodmateria(editCod.getText().toString());
        est1.setUnidadval(Integer.parseInt(editUnidadesVal.getText().toString()));
        est1.setNombremat(editNombreMateria.getText().toString());
        helper.abrir();
        String estado = helper.actualizar(est1);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editCod.setText("");
        editUnidadesVal.setText("");
        editNombreMateria.setText("");

    }

}
