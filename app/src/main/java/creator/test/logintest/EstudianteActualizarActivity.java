package creator.test.logintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EstudianteActualizarActivity extends AppCompatActivity {

    ControlBDHelper helper;
    EditText editCarnetestu;
    EditText editNombreEstu;
    EditText editApellidoEstu;
    EditText editCorreoEstu;
    EditText editDireccionEstu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_estudiante_actualizar);
        helper = new ControlBDHelper(this);
        helper = new ControlBDHelper(this);
        editCarnetestu = (EditText) findViewById(R.id.editarUsuario);
        editNombreEstu= (EditText) findViewById(R.id.editarNombre);
        editApellidoEstu= (EditText) findViewById(R.id.editarApellido);
        editCorreoEstu = (EditText) findViewById(R.id.editarCorreo);
        editDireccionEstu= (EditText) findViewById(R.id.editarDireccion);
    }
    public void actualizarEstudiante(View v) {
        Estudiante est1 = new Estudiante();
        est1.setCarnet(editCarnetestu.getText().toString());
        est1.setNombreestu(editNombreEstu.getText().toString());
        est1.setApellidoestu(editApellidoEstu.getText().toString());
        est1.setCorreoestu(editCorreoEstu.getText().toString());
        est1.setDireccionestu(editDireccionEstu.getText().toString());
        est1.setNomusuario(editCarnetestu.getText().toString());
        helper.abrir();
        String estado = helper.actualizar(est1);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editCarnetestu.setText("");
        editNombreEstu.setText("");
        editApellidoEstu.setText("");
        editCorreoEstu.setText("");
        editDireccionEstu.setText("");
    }
}
