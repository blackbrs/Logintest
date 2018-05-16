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
    TextView tvRegistro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        btncusuario = (Button) findViewById(R.id.registrar);
        et1 = findViewById(R.id.usuario);
        et2 = findViewById(R.id.contra);
    }

    public void ingresar(View v){
        BDHelper = new ControlBDHelper(this);
        BDHelper.abrir();
        int isadmin=0;
        String  usuario = et1.getText().toString();
        String  password= et2.getText().toString();
        fila = BDHelper.ConsultarUsuPasAdmin(usuario,password);
        Cursor admin = BDHelper.db.rawQuery("SELECT isadmin FROM usuario WHERE nomusuario='"+usuario+"'"+"AND clave='"+password+"'",null);

        if (admin.moveToFirst()){
            isadmin=admin.getInt(0);

        }

        if(isadmin==1) {

            if (fila.getCount() > 0) {
                Intent i = new Intent(this,MenuAdminActivity.class);
                i.putExtra("Usuario",usuario);
                startActivity(i);
                et1.setText("");
                et2.setText("");

            } else {
                Toast.makeText(getApplicationContext(), "Usuario Incorrecto", Toast.LENGTH_LONG).show();
            }
        }

        int isdocente=0;
        fila = BDHelper.ConsultarUsuPasDocente(usuario,password);
        Cursor docente = BDHelper.db.rawQuery("SELECT isdocente FROM usuario WHERE nomusuario='"+usuario+"'"+"AND clave='"+password+"'",null);

        if (docente.moveToFirst()){
            isdocente=docente.getInt(0);
        }

        if (isdocente==1){
            if (fila.getCount() > 0){
            Intent i = new Intent(this, MenuDocenteActivity.class);
            i.putExtra("Usuario",usuario);
            startActivity(i);
            et1.setText("");
            et2.setText("");
            }else{
                Toast.makeText(getApplicationContext(), "Usuario Incorrecto", Toast.LENGTH_LONG).show();
            }
        }


        int isestudiante=0;
        fila = BDHelper.ConsultarUsuPasEstudiante(usuario,password);
        Cursor estudiante = BDHelper.db.rawQuery("SELECT isestudiante FROM usuario WHERE nomusuario='"+usuario+"'"+"AND clave='"+password+"'",null);

        if (estudiante.moveToFirst()){
            isestudiante=estudiante.getInt(0);
        }

        if (isestudiante==1){
            if (fila.getCount() > 0){
                Intent i = new Intent(this,MenuEstudianteActivity.class);
                i.putExtra("Usuario",usuario);
                startActivity(i);
                et1.setText("");
                et2.setText("");
            }else{
                Toast.makeText(getApplicationContext(), "Usuario Incorrecto", Toast.LENGTH_LONG).show();
            }
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
