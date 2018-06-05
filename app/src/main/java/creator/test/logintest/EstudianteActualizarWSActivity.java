package creator.test.logintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class EstudianteActualizarWSActivity extends AppCompatActivity {


    EditText editCarnetestu;
    EditText editNombreEstu;
    EditText editApellidoEstu;
    EditText editCorreoEstu;
    EditText editDireccionEstu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_estudiante_actualizar_ws);
        editCarnetestu = (EditText) findViewById(R.id.editarUsuario);
        editNombreEstu= (EditText) findViewById(R.id.editarNombre);
        editApellidoEstu= (EditText) findViewById(R.id.editarApellido);
        editCorreoEstu = (EditText) findViewById(R.id.editarCorreo);
        editDireccionEstu= (EditText) findViewById(R.id.editarDireccion);

    }
}
