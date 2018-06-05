package creator.test.logintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class EstudianteConsultarWSActivity extends AppCompatActivity {
    EditText editCarnetestu;
    EditText editNombreEstu;
    EditText editApellidoEstu;
    EditText editCorreoEstu;
    EditText editDireccionEstu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_estudiante_consultar_ws);
        editCarnetestu = (EditText) findViewById(R.id.editUsuaCarnet);
        editNombreEstu= (EditText) findViewById(R.id.editNombreEstu);
        editApellidoEstu= (EditText) findViewById(R.id.editApellidoEstu);
        editCorreoEstu = (EditText) findViewById(R.id.editCorreoEstu);
        editDireccionEstu= (EditText) findViewById(R.id.editDireccionEstu);
    }
}
