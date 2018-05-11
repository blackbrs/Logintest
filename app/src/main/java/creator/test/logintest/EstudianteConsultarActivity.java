package creator.test.logintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EstudianteConsultarActivity extends AppCompatActivity {
    ControlBDHelper helper;
    EditText editCarnetestu;
    EditText editNombreEstu;
    EditText editApellidoEstu;
    EditText editCorreoEstu;
    EditText editDireccionEstu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_consultar);
        helper = new ControlBDHelper(this);
        editCarnetestu = (EditText) findViewById(R.id.editUsuaCarnet);
        editNombreEstu= (EditText) findViewById(R.id.editNombreEstu);
        editApellidoEstu= (EditText) findViewById(R.id.editApellidoEstu);
        editCorreoEstu = (EditText) findViewById(R.id.editCorreoEstu);
        editDireccionEstu= (EditText) findViewById(R.id.editDireccionEstu);
    }

    public void consultarEstudiante(View v) {
        helper.abrir();
        Estudiante est1 = helper.consultarEstudiante(editCarnetestu.getText().toString());
        helper.cerrar();
        if( est1 == null)
            Toast.makeText(this, "Estudiante con usuario: " + editCarnetestu.getText().toString() + " no encontrado", Toast.LENGTH_LONG).show();
        else{
            editCarnetestu.setText(est1.getCarnet());
            editNombreEstu.setText(est1.getNombreestu());
            editApellidoEstu.setText(est1.getApellidoestu());
            editCorreoEstu.setText(est1.getCorreoestu());
            editDireccionEstu.setText(est1.getDireccionestu());
        }
    }
    public void limpiarTexto(View v){
        editCarnetestu.setText("");
        editNombreEstu.setText("");
        editApellidoEstu.setText("");
        editCorreoEstu.setText("");
        editDireccionEstu.setText("");
    }
}
