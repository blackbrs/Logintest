package creator.test.logintest;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AsignarPreguntasCuestionarioActivity extends AppCompatActivity {
    int id;
    int idcuestionario;
    int idoferta;
    int idpregunta;
    ArrayList<String> listaMateria;
    ArrayList<Materia> MateriasList;
    ArrayList<String> ofertaLista;
    ArrayList<OfertaAcademica> lista;
    ArrayList<String> listaCuestionario;
    ArrayList<Cuestionario> CuestionariosList;
    ArrayList<String> listaArea;
    ArrayList<AreaEvaluacion> AreasList;
    ArrayList<String> listaPregunta;
    ArrayList<Pregunta> PreguntasList;
    Spinner comboMateria;
    Spinner comboOfertas;
    Spinner comboCuestionario;
    Spinner comboArea;
    Spinner comboPreguntas;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;

    ControlBDHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignar_preguntas_cuestionario);
        helper = new ControlBDHelper(this);

        comboMateria = (Spinner) findViewById(R.id.materia);
        comboOfertas = (Spinner) findViewById(R.id.oferta);
        comboCuestionario = (Spinner) findViewById(R.id.cuestionario);
        comboArea = (Spinner) findViewById(R.id.area);
        comboPreguntas = (Spinner) findViewById(R.id.preguntas);
        comboMateria.setEnabled(true);
        comboOfertas.setEnabled(false);
        comboCuestionario.setEnabled(false);
        comboArea.setEnabled(false);
        comboPreguntas.setEnabled(false);
        btn1 = (Button) findViewById(R.id.btnVerOferta);
        btn2 = (Button) findViewById(R.id.btnAsignarPreguntas);
        btn3 = (Button) findViewById(R.id.btnFiltrarPreguntas);
        btn4 = (Button) findViewById(R.id.btnCrearCuestionario);
        btn1.setEnabled(true);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);
        consultarListaMateria();
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,listaMateria);
        comboMateria.setAdapter(adapter);
        comboMateria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!=0) {
                    id = MateriasList.get(i - 1).getIdmateria();
                    btn1.setVisibility(View.VISIBLE);
                }else{
                    btn1.setVisibility(View.INVISIBLE);
                    btn2.setVisibility(View.INVISIBLE);
                    btn3.setVisibility(View.INVISIBLE);
                    btn4.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
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
            materia.setIdmateria(cursorMateria.getInt(0));
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


    public void consultarListaOfertaX() {
        OfertaAcademica ofertaacademica;
        lista = new ArrayList<OfertaAcademica>();
        helper.abrir();
        helper.cerrar();
        helper.abrir();
        Cursor cursor = helper.consultarListaOfertaMateria(id);
        while(cursor.moveToNext()) {
            helper.abrir();
            ofertaacademica = new OfertaAcademica();
            ofertaacademica.setIdoferta(cursor.getInt(0));
            ofertaacademica.setDescripcion(cursor.getString(1));
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
            System.out.println(lista.get(i).getIdoferta());
            System.out.println(lista.get(i).getDescripcion());
            System.out.println("#########################");
            System.out.println("#########################");
            System.out.println("#########################");
            System.out.println("#########################");
            ofertaLista.add(lista.get(i).getIdoferta() + " " +lista.get(i).getDescripcion());
        }
    }

    public  void verOferta(View v){
        btn2.setEnabled(true);
        comboOfertas.setEnabled(true);
        consultarListaOfertaX();
        ArrayAdapter<CharSequence> adapter1 = new ArrayAdapter(this,android.R.layout.simple_list_item_1,ofertaLista);
        comboOfertas.setAdapter(adapter1);
        comboOfertas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    idoferta = lista.get(i-1).getIdoferta();
                    System.out.println("ESTE ES ID DE OFERTA:");
                    System.out.println(idoferta);
                    btn2.setVisibility(View.VISIBLE);
                }else{
                    btn2.setVisibility(View.INVISIBLE);
                    btn3.setVisibility(View.INVISIBLE);
                    btn4.setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void consultarListaCuestionarios(){
        Cuestionario cuestionario = null;
        CuestionariosList = new ArrayList<Cuestionario>();
        helper.abrir();
        helper.cerrar();
        helper.abrir();
        Cursor cursorCuestionario = helper.consultarListaCuestionario(idoferta);
        while(cursorCuestionario.moveToNext()){
            System.out.println(cursorCuestionario.getInt(0));
            System.out.println(cursorCuestionario.getString(1));
            System.out.println(cursorCuestionario.getDouble(2));
            cuestionario = new Cuestionario();
            cuestionario.setIdoferta(idoferta);
            cuestionario.setIdcuestionario(cursorCuestionario.getInt(0));
            cuestionario.setDescricuestinario(cursorCuestionario.getString(1));
            cuestionario.setPonderacion(Double.parseDouble(cursorCuestionario.getString(2)));
            CuestionariosList.add(cuestionario);
        }
        obtenerListaCuestionario();
    }

    private void obtenerListaCuestionario() {
        listaCuestionario = new ArrayList<String>();
        listaCuestionario.add("Seleccion");
        for (int i=0; i< CuestionariosList.size();i++){
            listaCuestionario.add(CuestionariosList.get(i).getDescricuestinario());
        }
    }

    private void consultarListaArea() {
        AreaEvaluacion area1;
        ArrayList<Integer> ids= new ArrayList<>();
        AreasList = new ArrayList<AreaEvaluacion>();
        helper.abrir();
        helper.cerrar();
        helper.abrir();
        Cursor cursorArea = helper.consultarListaAreas();
        while(cursorArea.moveToNext()){
            area1 = new AreaEvaluacion();
            ids.add(cursorArea.getInt(0));
            area1.setIdarea(cursorArea.getInt(0));
            area1.setArea(cursorArea.getString(1));
            AreasList.add(area1);
        }
        obtenerListaAreas(ids);
    }

    private void obtenerListaAreas(ArrayList<Integer> idarea) {
        listaArea = new ArrayList<String>();
        listaArea.add("Seleccione");
        for (int i=0; i< AreasList.size();i++){
            listaArea.add(AreasList.get(i).getIdarea() + AreasList.get(i).getArea());
        }
    }

    public void asignarPreguntas(View v) {
        btn3.setEnabled(true);
        comboCuestionario.setEnabled(true);
        comboArea.setEnabled(true);
        consultarListaCuestionarios();
        ArrayAdapter<CharSequence> adapter3 = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,listaCuestionario);
        comboCuestionario.setAdapter(adapter3);

        comboCuestionario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    idcuestionario = CuestionariosList.get(i - 1).getIdcuestionario();

                } else {
                    btn3.setVisibility(View.INVISIBLE);
                    btn4.setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        consultarListaArea();
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,listaArea);
        comboArea.setAdapter(adapter);

        comboArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int postition, long l) {
                if(postition!=0) {
                    id = AreasList.get(postition - 1).getIdarea();
                    btn3.setVisibility(View.VISIBLE);
                }else{
                    btn3.setVisibility(View.INVISIBLE);
                    btn4.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void consultarPreguntas(){

        Pregunta preg1l;
        PreguntasList = new ArrayList<Pregunta>();
        helper.abrir();
        helper.cerrar();
        helper.abrir();
        Cursor cursorPregunta= helper.consultarListaPregunta(id);
        while(cursorPregunta.moveToNext()){
            preg1l = new Pregunta();
            preg1l.setDescrippreg(cursorPregunta.getString(0));
            preg1l.setIdpregunta(cursorPregunta.getInt(1));
            PreguntasList.add(preg1l);
        }
        obtenerListaPreguntas();
    }

    private void obtenerListaPreguntas() {
        listaPregunta = new ArrayList<String>();
        listaPregunta.add("Seleccione");
        for (int i=0; i < PreguntasList.size(); i++){
            listaPregunta.add(PreguntasList.get(i).getDescrippreg());
        }
    }

    public void consultarListaPreguntas(View v){
        consultarPreguntas();
        comboPreguntas.setEnabled(true);
        btn4.setEnabled(true);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaPregunta);
        comboPreguntas.setAdapter(adapter);
        comboPreguntas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int postition, long l) {
                if(postition != 0){
                    idpregunta=PreguntasList.get(postition-1).getIdpregunta();
                    btn4.setVisibility(View.VISIBLE);
                }else{
                    btn4.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void insertarDetalleCuestionario(View v) {
        String regInsertados;
        DetallePregunta detalle = new DetallePregunta();
        detalle.setIdpregunta(idpregunta);
        detalle.setIdcuestionario(idcuestionario);
        helper.abrir();
        regInsertados = helper.insertar(detalle);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }


}
