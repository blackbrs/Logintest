package creator.test.logintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CicloActualizarActivity extends AppCompatActivity {

    ControlBDHelper helper;
    EditText editCod;
    EditText editAnio;
    EditText editFechaIni;
    EditText editFechaFin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ciclo_actualizar);
        helper = new ControlBDHelper(this);
        editCod = (EditText) findViewById(R.id.editnumeroCiclo);
        editAnio = (EditText) findViewById(R.id.editaniociclO);
        editFechaIni = (EditText) findViewById(R.id.editfechainiciO);
        editFechaFin = (EditText) findViewById(R.id.editfechafinaL);
    }
    public void actualizarCiclo(View v) {
        Ciclo ciclo = new Ciclo();
        ciclo.setNumCiclo(Integer.parseInt(editCod.getText().toString()));
        ciclo.setAnioCiclo(Integer.parseInt(editAnio.getText().toString()));
        ciclo.setFechaIni(editFechaIni.getText().toString());
        ciclo.setFechaFin(editFechaFin.getText().toString());
        helper.abrir();
        String estado = helper.actualizar(ciclo);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editCod.setText("");
        editAnio.setText("");
        editFechaIni.setText("");
        editFechaFin.setText("");
    }
}
