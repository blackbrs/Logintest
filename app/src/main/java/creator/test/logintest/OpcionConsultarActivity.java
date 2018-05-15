package creator.test.logintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class OpcionConsultarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcion_consultar);
    }
}
