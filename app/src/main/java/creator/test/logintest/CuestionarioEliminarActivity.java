package creator.test.logintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CuestionarioEliminarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuestionario_eliminar);
    }
}
