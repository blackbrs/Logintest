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
    ArrayList<OfertaAcademica> listaOfertas;
    EditText descricuestinario;
    EditText fechacuestionario;
    EditText ponderacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuestionario_insertar);
        helper = new ControlBDHelper(this);
        ListaOferta = (Spinner) findViewById(R.id.spinnerOferta);
        descricuestinario = (EditText) findViewById(R.id.editDescripcionExamen);
        ponderacion = (EditText) findViewById(R.id.editPonderacionExamen);
        fechacuestionario = (EditText) findViewById(R.id.editFechaExamen);
        Intent i = getIntent();
        user = i.getStringExtra("Usuario");
        consultarListaOferta();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,ofertaLista);
        ListaOferta.setAdapter(adapter);

        ListaOferta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!= 0){
                idoferta = listaOfertas.get(i-1).getIdoferta();
                System.out.println(idoferta);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void consultarListaOferta() {
        String auxid1=null;
        String auxid2=null;
        String auxid3=null;
        String auxid4=null;
        OfertaAcademica ofer1;
        Materia mat1 = new Materia();
        Ciclo cic1 = new Ciclo();
        Docente doc1 = new Docente();
        listaOfertas = new ArrayList<OfertaAcademica>();
        helper.abrir();
        Cursor cursorOferta = helper.consultarListaOferta(user);
        while(cursorOferta.moveToNext()){
            ofer1 = new OfertaAcademica();
            System.out.println("ID OFERTA:");
            Cursor cursorDocente= helper.db.rawQuery("SELECT nomusuario FROM docente WHERE  iddocente ="+ cursorOferta.getInt(2),null);
            if(cursorDocente.moveToFirst()){
                System.out.println("Docente AUXID1 POS 2");
                auxid1=cursorDocente.getString(0);
                System.out.println(auxid1);
            }

            Cursor cursorMateria = helper.db.rawQuery("SELECT numciclo,aniociclo FROM ciclo WHERE idciclo = "+ cursorOferta.getInt(3),null);
            if(cursorMateria.moveToFirst()){
                System.out.println("Ciclo AUXID2 y AUXID4 POS 3");
                auxid2=cursorMateria.getString(0);
                auxid4=cursorMateria.getString(1);
                System.out.println(auxid2);
                System.out.println(auxid4);
            }

            Cursor cursorId=helper.db.rawQuery("SELECT codmateria FROM materia WHERE  idmateria ="+cursorOferta.getInt(1),null);
            if(cursorId.moveToFirst()){

                auxid3=cursorId.getString(0);
                System.out.println("Materia AUXID3 POS 1");
                System.out.println(auxid3);
            }

            System.out.println(cursorOferta.getString(4));
            mat1.setCodmateria(auxid3);
            cic1.setNumCiclo(Integer.parseInt(auxid2));
            cic1.setAnioCiclo(Integer.parseInt(auxid4));
            doc1.setNomusuario(auxid1);
            ofer1.setDoc(doc1);
            ofer1.setMater(mat1);
            ofer1.setCic(cic1);
            ofer1.setDescripcion(cursorOferta.getString(4));
            ofer1.setIdoferta(cursorOferta.getInt(0));
            listaOfertas.add(ofer1);
        }
        obtenerListaOferta();
    }

    private void obtenerListaOferta() {
        ofertaLista = new  ArrayList<String>();
        ofertaLista.add("Seleccione");
        for (int i=0 ; i < listaOfertas.size();i++){
            ofertaLista.add(listaOfertas.get(i).getMater().getCodmateria()+"-"
                    +listaOfertas.get(i).getCic().getNumCiclo()+" "
                    +listaOfertas.get(i).getCic().getAnioCiclo()+"-"
                    +listaOfertas.get(i).getDescripcion()
            );
        }
    }

    public void insertarCuestionario(View v) {

        String  descrip= descricuestinario.getText().toString();
        double pond= Double.parseDouble(ponderacion.getText().toString());
        String fechaE = fechacuestionario.getText().toString();
        String regInsertados;

        Cuestionario cuest1 = new Cuestionario();
        cuest1.setIdoferta(idoferta);
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