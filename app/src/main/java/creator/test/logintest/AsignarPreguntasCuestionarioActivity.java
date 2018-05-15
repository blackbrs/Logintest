package creator.test.logintest;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import java.util.ArrayList;

public class AsignarPreguntasCuestionarioActivity extends AppCompatActivity {

    ArrayList<String> listaMateria;
    ArrayList<Materia> MateriasList;
    Spinner comboMateria;
    ControlBDHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignar_preguntas_cuestionario);
        helper = new ControlBDHelper(this);
    }

    public void consultarListaMateria() {
        Materia materia =null;
        MateriasList = new ArrayList<Materia>();
        helper.abrir();
        helper.cerrar();
        helper.abrir();
        Cursor cursorMateria = helper.consultarListaMaterias();
        while(cursorMateria.moveToNext()){
            materia = new Materia();
            materia.setCodmateria(cursorMateria.getString(1));
            materia.setUnidadval(cursorMateria.getInt(2));
            materia.setNombremat(cursorMateria.getString(3));
            System.out.println(materia.getCodmateria());
            System.out.println(materia.getNombremat());
            MateriasList.add(materia);
        }
        obtenerListaMateria();
    }

    public void obtenerListaMateria(){
        listaMateria = new ArrayList<>();
        listaMateria.add("Seleccione");
        for (int i=0; i<MateriasList.size();i++){
            listaMateria.add(MateriasList.get(i).getCodmateria());
        }
        System.out.println(listaMateria);
    }
}
