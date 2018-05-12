package creator.test.logintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CicloConsultarActivity extends AppCompatActivity {
    ControlBDHelper helper;
    EditText editCod;
    EditText editAnio;
    EditText editFechaIni;
    EditText editFechaFin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ciclo_consultar);
        helper = new ControlBDHelper(this);
        editCod = (EditText) findViewById(R.id.editnumerociclo);
        editAnio = (EditText) findViewById(R.id.editaniociclo);
        editFechaIni = (EditText) findViewById(R.id.editfechainicio);
        editFechaFin = (EditText) findViewById(R.id.editfechafinal);
    }
    public void consultarCiclo(View v) {
        helper.abrir();
        Ciclo ciclo = helper.consultarCiclo( editCod.getText().toString(), editAnio.getText().toString());
        helper.cerrar();
        if( ciclo == null)
            Toast.makeText(this, "Ciclo con numero de ciclo: " + editCod.getText().toString()+"y Anio Ciclo: " +editAnio.getText().toString() + " no encontrado", Toast.LENGTH_LONG).show();
        else{
            editCod.setText(String.valueOf(ciclo.getNumCiclo()));
            editAnio.setText(String.valueOf(ciclo.getAnioCiclo()));
            editFechaIni.setText(ciclo.getFechaIni());
            editFechaFin.setText(ciclo.getFechaFin());
        }
    }

    public void limpiarTexto(View v) {

        editCod.setText("");
        editAnio .setText("");
        editFechaIni .setText("");
        editFechaFin .setText("");
    }
}
