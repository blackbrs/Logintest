package creator.test.logintest;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class OfertaAcademicaConsultaActivity extends AppCompatActivity {

    ListView ListaOferta;
    String user;
    ControlBDHelper helper;
    ArrayList<String> ofertaLista;
    ArrayList<OfertaAcademica> lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_oferta_academica_consulta);
        helper = new ControlBDHelper(this);
        Intent i = getIntent();
        user = i.getStringExtra("Usuario");
        ListaOferta = (ListView) findViewById(R.id.listViewOferta);
        consultarListaOfertaX();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,ofertaLista);
        ListaOferta.setAdapter(adapter);


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
            lista.add(ofertaacademica);
        }

        obtenerListaOferta();
    }

    private void obtenerListaOferta() {
        ofertaLista = new  ArrayList<String>();

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
}
