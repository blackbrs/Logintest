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
    ArrayList<OfertaAcademica> listaOfertas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oferta_academica_consulta);
        helper = new ControlBDHelper(this);
        Intent i = getIntent();
        user = i.getStringExtra("Usuario");
        ListaOferta = (ListView) findViewById(R.id.listViewOferta);
        consultarListaOferta();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,ofertaLista);
        ListaOferta.setAdapter(adapter);


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
            listaOfertas.add(ofer1);
        }
        obtenerListaOferta();
    }

    private void obtenerListaOferta() {
        ofertaLista = new  ArrayList<String>();
        for (int i=0 ; i < listaOfertas.size();i++){
            ofertaLista.add(listaOfertas.get(i).getMater().getCodmateria()+"-"
                    + listaOfertas.get(i).getDoc().getNomusuario()+"-"
                    +listaOfertas.get(i).getCic().getNumCiclo()+" "
                    +listaOfertas.get(i).getCic().getAnioCiclo()+"-"
                    +listaOfertas.get(i).getDescripcion()
                     );
        }
    }
}
