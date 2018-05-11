package creator.test.logintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DocenteConsultarActivity extends AppCompatActivity {
    ControlBDHelper helper;
    EditText editUserDoc;
    EditText editName;
    EditText editLast;
    EditText editMail;
    EditText editAdress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_consultar);
        helper = new ControlBDHelper(this);
        editUserDoc = (EditText) findViewById(R.id.editUsua);
        editName = (EditText) findViewById(R.id.editNombreEstu);
        editLast = (EditText) findViewById(R.id.editApellidoEstu);
        editMail = (EditText) findViewById(R.id.editMail);
        editAdress = (EditText) findViewById(R.id.editAdress);
    }
    public void consultarDocente(View v) {
        helper.abrir();
        Docente doc = helper.consultarDocente(editUserDoc.getText().toString());
        helper.cerrar();
        if( doc == null)
            Toast.makeText(this, "Docente con usuario: " + editUserDoc.getText().toString() + " no encontrado", Toast.LENGTH_LONG).show();
        else{
            editName.setText(doc.getNombdocente());
            editLast.setText(doc.getApelldocente());
            editMail.setText(doc.getCorreo());
            editAdress.setText(doc.getDireccion());
        }
    }
    public void limpiarTexto(View v){
        editUserDoc.setText("");
        editName.setText("");
        editLast.setText("");
        editMail.setText("");
        editAdress.setText("");
    }
}
