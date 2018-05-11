package creator.test.logintest;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DocenteInsertarActivity extends AppCompatActivity {

    ControlBDHelper helper;
    EditText editNombre;
    EditText editApellido;
    EditText editCorreo;
    EditText editDireccion;
    EditText editUser;
    EditText editClave;
    String isDocente;
    String isAdmin;
    String isEstudiante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_docente_insertar);
        helper = new ControlBDHelper(this);
        editNombre = (EditText) findViewById(R.id.editNombre);
        editApellido = (EditText) findViewById(R.id.editApellido);
        editCorreo = (EditText) findViewById(R.id.editCorreo);
        editDireccion = (EditText) findViewById(R.id.editDireccion);
        editUser = (EditText) findViewById(R.id.editUser);
        editClave = (EditText) findViewById(R.id.editClave);
    }

    public void insertarDocente(View v) {
        String nombre=editNombre.getText().toString();
        String apellido=editApellido.getText().toString();
        String user=editUser.getText().toString();
        String mail = editCorreo.getText().toString();
        String dir = editDireccion.getText().toString();
        String clave = editClave.getText().toString();
        String regInsertados;
        if (findViewById(R.id.isDocente).isSelected()){
            isDocente = "1";
            isEstudiante = "0";
            isAdmin = "0";
            Docente profesor=new Docente();
            profesor.setNomusuario(user);
            profesor.setNombdocente(nombre);
            profesor.setApelldocente(apellido);
            profesor.setDireccion(dir);
            profesor.setCorreo(mail);

            Usuario usuario1 = new Usuario();
            usuario1.setNomusuario(user);
            usuario1.setClave(clave);
            usuario1.setAdmin(Boolean.valueOf(isAdmin));
            usuario1.setDocente(Boolean.valueOf(isDocente));
            usuario1.setEstudiante(Boolean.valueOf(isEstudiante));
            helper.abrir();
            regInsertados=helper.insertar(profesor);
            regInsertados=helper.insertar(usuario1);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        }
    }


    public void limpiarTexto(View v) {
        editCorreo.setText("");
        editNombre.setText("");
        editApellido.setText("");
        editUser.setText("");
        editDireccion.setText("");
    }

}
