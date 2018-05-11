package creator.test.logintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DocenteActualizarActivity extends AppCompatActivity {
    ControlBDHelper helper;
    EditText editusuario;
    EditText editnombre;
    EditText editapellido;
    EditText editcorreo;
    EditText editdireccion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_actualizar);
        helper = new ControlBDHelper(this);
        editusuario = (EditText) findViewById(R.id.editarUsuario);
        editnombre = (EditText) findViewById(R.id.editarNombre);
        editapellido = (EditText) findViewById(R.id.editarApellido);
        editcorreo = (EditText) findViewById(R.id.editarCorreo);
        editdireccion = (EditText) findViewById(R.id.editarDireccion);
    }
    public void actualizarDocente(View v) {
        Docente profe = new Docente();
        profe.setNomusuario(editusuario.getText().toString());
        profe.setNombdocente(editnombre.getText().toString());
        profe.setApelldocente(editapellido.getText().toString());
        profe.setCorreo(editcorreo.getText().toString());
        profe.setDireccion(editdireccion.getText().toString());
        System.out.println(profe.getNomusuario());
        helper.abrir();
        String estado = helper.actualizar(profe);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editusuario.setText("");
        editnombre.setText("");
        editapellido.setText("");
        editcorreo.setText("");
        editdireccion.setText("");
    }
}
