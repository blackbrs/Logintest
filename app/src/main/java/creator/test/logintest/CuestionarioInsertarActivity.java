package creator.test.logintest;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class CuestionarioInsertarActivity extends AppCompatActivity {
    Spinner ListaOferta;
    String user;
    int idoferta;
    ControlBDHelper helper;
    ArrayList<String> ofertaLista;
    ArrayList<OfertaAcademica> lista;
    EditText descricuestinario;
    EditText fechacuestionario;
    EditText ponderacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuestionario_insertar);
        getSupportActionBar().hide();
        helper = new ControlBDHelper(this);
        ListaOferta = (Spinner) findViewById(R.id.spinnerOferta);
        descricuestinario = (EditText) findViewById(R.id.editDescripcionExamen);
        ponderacion = (EditText) findViewById(R.id.editPonderacionExamen);
        fechacuestionario = (EditText) findViewById(R.id.editFechaExamen);
        Intent i = getIntent();
        user = i.getStringExtra("Usuario");
        consultarListaOfertaX();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,ofertaLista);
        ListaOferta.setAdapter(adapter);

        ListaOferta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!= 0){
                idoferta = lista.get(i-1).getIdoferta();
                    System.out.println("ESTE ES EL ID OFERTA = "+ idoferta);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void consultarListaOfertaX() {
        String auxiliar=null;
        String auxiliarciclo1=null;
        String auxiliarciclo2=null;
        OfertaAcademica ofertaacademica;
        Materia materia1;
        Ciclo ciclo1;
        Docente docente1;
        lista = new ArrayList<OfertaAcademica>();
        helper.abrir();
        helper.cerrar();
        helper.abrir();
        Cursor cursor = helper.consultarListaOferta(user);
        while(cursor.moveToNext()) {
            helper.abrir();
            materia1 = new Materia();
            ciclo1 = new Ciclo();
            docente1 = new Docente();
            ofertaacademica = new OfertaAcademica();
            Cursor cursorCiclo = helper.db.rawQuery("SELECT numciclo,aniociclo FROM ciclo WHERE idciclo="+cursor.getInt(3),null);
            if(cursorCiclo.moveToFirst()) {
                auxiliarciclo1=cursor.getString(0);
                auxiliarciclo2=cursor.getString(1);
            }

            Cursor cursorMateriaX=helper.db.rawQuery("SELECT codmateria FROM materia WHERE idmateria = "+cursor.getInt(1),null);
            if (cursorMateriaX.moveToFirst()) {
                auxiliar=cursorMateriaX.getString(0);
            }

            materia1.setCodmateria(auxiliar);
            ciclo1.setNumCiclo(Integer.parseInt(auxiliarciclo1));
            ciclo1.setAnioCiclo(Integer.parseInt(auxiliarciclo2));
            docente1.setNomusuario(user);
            ofertaacademica.setMater(materia1);
            ofertaacademica.setCic(ciclo1);
            ofertaacademica.setDoc(docente1);
            ofertaacademica.setDescripcion(cursor.getString(4));
            ofertaacademica.setIdoferta(cursor.getInt(0));
            lista.add(ofertaacademica);
        }

        obtenerListaOferta();
    }

    private void obtenerListaOferta() {
        ofertaLista = new  ArrayList<String>();
        ofertaLista.add("Seleccione");
        for (int i=0 ; i <lista.size();i++){
            System.out.println("#########################");
            System.out.println("#########################");
            System.out.println("#########################");
            System.out.println("#########################");
            System.out.println(lista.get(i).getDoc().getNomusuario());
            System.out.println(lista.get(i).getMater().getCodmateria());
            System.out.println(lista.get(i).getCic().getNumCiclo());
            System.out.println(lista.get(i).getCic().getAnioCiclo());
            System.out.println(lista.get(i).getDescripcion());
            System.out.println("#########################");
            System.out.println("#########################");
            System.out.println("#########################");
            System.out.println("#########################");
            ofertaLista.add(lista.get(i).getMater().getCodmateria()+"-"
                    + lista.get(i).getDoc().getNomusuario()+"-"
                    +lista.get(i).getCic().getNumCiclo()+" "
                    +lista.get(i).getCic().getAnioCiclo()+"-"
                    +lista.get(i).getDescripcion());
        }
    }

    public void insertarCuestionario(View v) {

        String  descrip= descricuestinario.getText().toString();
        double pond= Double.parseDouble(ponderacion.getText().toString());
        String fechaE = fechacuestionario.getText().toString();
        String regInsertados;

        Cuestionario cuest1 = new Cuestionario();
        cuest1.setIdoferta(idoferta);
        System.out.println(idoferta);
        cuest1.setDescricuestinario(descrip);
        cuest1.setPonderacion(pond);
        cuest1.setFechacuestionario(fechaE);
        helper.abrir();
        regInsertados = helper.insertar(cuest1);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        descricuestinario.setText("");
        ponderacion.setText("");
        fechacuestionario.setText("");
    }
}