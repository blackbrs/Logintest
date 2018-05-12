package creator.test.logintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CicloEliminarActivity extends AppCompatActivity {

    ControlBDHelper helper;
    EditText editCod;
    EditText editAnio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ciclo_eliminar);
        helper = new ControlBDHelper(this);
        editCod = (EditText) findViewById(R.id.editNumeroCiclo);
        editAnio = (EditText) findViewById(R.id.editAnioCiclo);

    }

    public void eliminarCiclo(View v){
        String regAfectados;
        int numciclo=Integer.parseInt(editCod.getText().toString());
        int aniociclo=Integer.parseInt(editAnio.getText().toString());
        Ciclo ciclo = new Ciclo();
        ciclo.setNumCiclo(numciclo);
        ciclo.setAnioCiclo(aniociclo);
        helper.abrir();
        regAfectados=helper.eliminar(ciclo);
        helper.cerrar();
        Toast.makeText(this, regAfectados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTextoCiclo(View v) {
        editCod.setText("");
        editAnio .setText("");
    }
}
