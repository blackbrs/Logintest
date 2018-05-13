package creator.test.logintest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AreaEvaluacionMenuActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_area_evaluacion_menu);
        btn1 = findViewById(R.id.btnIngresar);
        btn2 = findViewById(R.id.btnEliminar);
        btn3 = findViewById(R.id.btnConsultar);
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
            case R.id.btnIngresar:
                Intent intento = new Intent(this,AreaEvaluacionInsertarActivity.class);
                intento.putExtra("Usuario",user);
                startActivity(intento);
                break;
            case R.id.btnEliminar:
                Intent intento2 = new Intent(this,AreaEvaluacionEliminarActivity.class);
                intento2.putExtra("Usuario",user);
                startActivity(intento2);
                break;
            case R.id.btnConsultar:
                Intent intento3 = new Intent(this,AreaEvaluacionConsultarActivity.class);
                intento3.putExtra("Usuario",user);
                startActivity(intento3);
                break;
            case R.id.btnActualizar:
                Intent intento4 = new Intent(this,AreaEvaluacionActualizarActivity.class);
                intento4.putExtra("Usuario",user);
                startActivity(intento4);
                break;
        }
    }
}
