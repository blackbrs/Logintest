package creator.test.logintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MateriaInsertarActivity extends AppCompatActivity {
    ControlBDHelper helper;
    EditText editCod;
    EditText editUnidadesVal;
    EditText editNombreMateria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_materia_insertar);
        helper = new ControlBDHelper(this);
        editCod = (EditText) findViewById(R.id.editCod);
        editUnidadesVal = (EditText) findViewById(R.id.editUnidadesVal);
        editNombreMateria = (EditText) findViewById(R.id.editNombreDocente);
    }

    public void insertarMateria(View v) {

        String codmateria = editCod.getText().toString();
        int unidadesval =Integer.parseInt(editUnidadesVal.getText().toString());
        String  nombreMateria = editNombreMateria.getText().toString();
        String regInsertados;

        Materia mat1 = new Materia();
        mat1.setCodmateria(codmateria);
        mat1.setUnidadval(unidadesval);
        mat1.setNombremat(nombreMateria);

        helper.abrir();
        regInsertados = helper.insertar(mat1);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editCod.setText("");
        editNombreMateria.setText("");
        editUnidadesVal.setText("");

    }

}
