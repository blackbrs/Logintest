package creator.test.logintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MateriaConsultarActivity extends AppCompatActivity {
    ControlBDHelper helper;
    EditText editCod;
    EditText editUnidadesVal;
    EditText editNombreMateria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_materia_consultar);
        helper = new ControlBDHelper(this);
        editCod = (EditText) findViewById(R.id.editCod);
        editUnidadesVal = (EditText) findViewById(R.id.editUnidadesVal);
        editNombreMateria = (EditText) findViewById(R.id.editNombreDocente);
    }

    public void consultarMateria(View v) {
        helper.abrir();
        Materia mat1 = helper.consultarMateria(editCod.getText().toString());
        helper.cerrar();
        if( mat1 == null)
            Toast.makeText(this, "Materia con Codigo: " + editCod.getText().toString() + " no encontrado", Toast.LENGTH_LONG).show();
        else{
            editCod.setText(mat1.getCodmateria());
            editUnidadesVal.setText(String.valueOf(mat1.getUnidadval()));
            editNombreMateria.setText(mat1.getNombremat());
        }
    }
    public void limpiarTexto(View v){
        editCod.setText("");
        editUnidadesVal.setText("");
        editNombreMateria.setText("");

    }

}
