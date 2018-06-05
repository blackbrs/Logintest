package creator.test.logintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class EstudianteInsertarWSActivity extends AppCompatActivity {
    EditText editNombreestu;
    EditText editApellido;
    EditText editCorreo;
    EditText editDireccion;
    EditText editUser;
    EditText editClave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_estudiante_insertar_ws);
        editNombreestu = (EditText) findViewById(R.id.nombre);
        editApellido = (EditText) findViewById(R.id.apellido);
        editCorreo = (EditText) findViewById(R.id.correo);
        editDireccion = (EditText) findViewById(R.id.direccion);
        editUser = (EditText) findViewById(R.id.carnet);
        editClave = (EditText) findViewById(R.id.editClave);
    }
}
