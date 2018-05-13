package creator.test.logintest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CuestionarioMenuActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cuestionario_menu);
        btn1 = findViewById(R.id.btnIngresar2);
        btn2 = findViewById(R.id.btnEliminar2);
        btn3 = findViewById(R.id.btnConsultar2);
        btn4 = findViewById(R.id.btnActualizar);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        Intent i = getIntent();
        user = i.getStringExtra("Usuario");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnIngresar2:
                Intent intento = new Intent(this,CuestionarioInsertarActivity.class);
                intento.putExtra("Usuario",user);
                startActivity(intento);
                break;
            case R.id.btnEliminar2:
                Intent intento2 = new Intent(this,CuestionarioEliminarActivity.class);
                intento2.putExtra("Usuario",user);
                startActivity(intento2);
                break;
            case R.id.btnConsultar2:
                Intent intento3 = new Intent(this,CuestionarioConsultarActivity.class);
                intento3.putExtra("Usuario",user);
                startActivity(intento3);
                break;
            case R.id.btnActualizar:
                Intent intento4 = new Intent(this,CuestionarioActualizarActivity.class);
                intento4.putExtra("Usuario",user);
                startActivity(intento4);
                break;
        }
    }
}
