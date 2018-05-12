package creator.test.logintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CicloInsertarActivity extends AppCompatActivity {

    ControlBDHelper helper;
    EditText editCod;
    EditText editAnio;
    EditText editFechaIni;
    EditText editFechaFin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ciclo_insertar);
        helper = new ControlBDHelper(this);
        editCod = (EditText) findViewById(R.id.editnumerociclo);
        editAnio = (EditText) findViewById(R.id.editaniociclo);
        editFechaIni = (EditText) findViewById(R.id.editFechaIni);
        editFechaFin = (EditText) findViewById(R.id.editFechaFin);
    }

    public void insertarCiclo(View v) {

        int numciclo = Integer.parseInt(editCod.getText().toString());
        int aniociclo = Integer.parseInt(editAnio.getText().toString());
        String fecha1 = editFechaIni.getText().toString();
        String fecha2 = editFechaFin.getText().toString();
        String regInsertados;

        Ciclo ciclo = new Ciclo();
        ciclo.setNumCiclo(numciclo);
        ciclo.setAnioCiclo(aniociclo);
        ciclo.setFechaIni(fecha1);
        ciclo.setFechaFin(fecha2);
        helper.abrir();
        regInsertados = helper.insertar(ciclo);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {

        editCod.setText("");
        editAnio .setText("");
        editFechaIni .setText("");
        editFechaFin .setText("");
    }
}
