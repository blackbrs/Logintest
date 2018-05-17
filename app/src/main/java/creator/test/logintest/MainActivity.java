package creator.test.logintest;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements   View.OnClickListener{

    private Button btncusuario;
    public EditText et1, et2;
    private Cursor fila;
    public ControlBDHelper BDHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        btncusuario = (Button) findViewById(R.id.registrar);
        et1 = findViewById(R.id.usuario);
        et2 = findViewById(R.id.contra);
    }

    public void ingresar(View v) {

        BDHelper = new ControlBDHelper(this);
        BDHelper.abrir();
        String usuario = et1.getText().toString();
        String password = et2.getText().toString();
        fila = BDHelper.ConsultarUsuPas(usuario, password);
        

        System.out.println(fila.moveToFirst() + "    " + fila.getCount());
        if (!(fila.moveToFirst() )) { //Si la combinacion Usuario - Contraseña no existe, mostrar el toast

            Toast.makeText(getApplicationContext(), "Credenciales incorrectas", Toast.LENGTH_LONG).show();
            return;
        }

        if (fila.getInt(2) == 1) { //Si es admin
            Intent i = new Intent(this, MenuAdminActivity.class);
            i.putExtra("Usuario", usuario);
            startActivity(i);
            et1.setText("");
            et2.setText("");
        }

        if (fila.getInt(3) == 1) { //Si es docente
                Intent i = new Intent(this, MenuDocenteActivity.class);
                i.putExtra("Usuario", usuario);
                startActivity(i);
                et1.setText("");
                et2.setText("");
        }

        if (fila.getInt(4) == 1) { //Si es estudiante
            Intent i = new Intent(this, MenuEstudianteActivity.class);
            i.putExtra("Usuario", usuario);
            startActivity(i);
            et1.setText("");
            et2.setText("");
        }
    }




    public void onClick(View v){

        int id = v.getId();
        if(id == btncusuario.getId()){
            Intent intent = new Intent(this, EstudianteInsertarActivity.class);
            startActivity(intent);
        }
}

}
