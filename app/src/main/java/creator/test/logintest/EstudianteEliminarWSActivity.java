package creator.test.logintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class EstudianteEliminarWSActivity extends AppCompatActivity {
    EditText editCarnet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_estudiante_eliminar_ws);
        editCarnet = (EditText) findViewById(R.id.editCarne);
    }
}
