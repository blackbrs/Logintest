package creator.test.logintest;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EstudianteInsertarActivity extends AppCompatActivity {

    ControlBDHelper helper;
    EditText editNombreestu;
    EditText editApellido;
    EditText editCorreo;
    EditText editDireccion;
    EditText editUser;
    EditText editClave;
    boolean isDocente;
    boolean isAdmin;
    boolean isEstudiante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_docente_insertar);
        helper = new ControlBDHelper(this);
        editNombreestu = (EditText) findViewById(R.id.nombre);
        editApellido = (EditText) findViewById(R.id.apellido);
        editCorreo = (EditText) findViewById(R.id.correo);
        editDireccion = (EditText) findViewById(R.id.direccion);
        editUser = (EditText) findViewById(R.id.carnet);
        editClave = (EditText) findViewById(R.id.editClave);
    }

    public void insertarEstudiante(View v) {

        String nombre = editNombreestu.getText().toString();
        String apellido = editApellido.getText().toString();
        String user = editUser.getText().toString();
        String mail = editCorreo.getText().toString();
        String dir = editDireccion.getText().toString();
        String clave = editClave.getText().toString();
        String regInsertados;
        isDocente = false;
        isEstudiante = true;
        isAdmin = false;

        Estudiante est1 = new Estudiante();
        est1.setNomusuario(user);
        est1.setNombreestu(nombre);
        est1.setApellidoestu(apellido);
        est1.setDireccionestu(dir);
        est1.setCorreoestu(mail);

        Usuario usuario1 = new Usuario();
        usuario1.setNomusuario(user);
        usuario1.setClave(clave);
        usuario1.setAdmin(isAdmin);
        usuario1.setDocente(isDocente);
        usuario1.setEstudiante((isEstudiante));
        helper.abrir();
        regInsertados = helper.insertar(est1);
        regInsertados = helper.insertar(usuario1);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editCorreo.setText("");
        editNombreestu.setText("");
        editApellido.setText("");
        editUser.setText("");
        editDireccion.setText("");
    }

}