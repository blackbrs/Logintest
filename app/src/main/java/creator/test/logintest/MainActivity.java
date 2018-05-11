package creator.test.logintest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

 public class MainActivity extends AppCompatActivity implements   View.OnClickListener{

private Button btncusuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        btncusuario = (Button) findViewById(R.id.registrar);
    }

    public void onClick(View v){

        int id = v.getId();
        if(id == btncusuario.getId()){
            Intent intent = new Intent(this, CrearUsuario.class);
            startActivity(intent);
        }

}

}
