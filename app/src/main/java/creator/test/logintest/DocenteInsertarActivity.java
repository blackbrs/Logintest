package creator.test.logintest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DocenteInsertarActivity extends Activity {

    ControlBDHelper helper;
    EditText editNombre;
    EditText editApellido;
    EditText editCorreo;
    EditText editDireccion;
    EditText editUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_insertar);
        helper = new ControlBDHelper(this);
        editNombre = (EditText) findViewById(R.id.editNombre);
        editApellido = (EditText) findViewById(R.id.editApellido);
        editCorreo = (EditText) findViewById(R.id.editCorreo);
        editDireccion = (EditText) findViewById(R.id.editDireccion);
        editUser = (EditText) findViewById(R.id.editUser);

    }

    public void insertarDocente(View v) {
        String nombre=editNombre.getText().toString();
        String apellido=editApellido.getText().toString();
        String user=editUser.getText().toString();
        String mail = editCorreo.getText().toString();
        String dir = editDireccion.getText().toString();
        String regInsertados;
        Docente profesor=new Docente();
        profesor.setNomusuario(user);
        profesor.setNombdocente(nombre);
        profesor.setApelldocente(apellido);
        profesor.setDireccion(dir);
        profesor.setCorreo(mail);
        helper.abrir();
        //regInsertados=helper.insertar(alumno);
        helper.cerrar();
       // Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }


    public void limpiarTexto(View v) {
        editCorreo.setText("");
        editNombre.setText("");
        editApellido.setText("");
        editUser.setText("");
        editDireccion.setText("");
    }

}
