package creator.test.logintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MateriaMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_materia_menu);
    }
}
