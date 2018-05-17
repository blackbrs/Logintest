package creator.test.logintest;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EvaluacionActivity extends AppCompatActivity {
    private  int mQuestionNumber=0;
    private  int mQuestionWeight=0;
    String user;
    String answer;
    int id;
    ControlBDHelper helper;
    ArrayList<DetallePregunta> detallesList;
    ArrayList<String > listaPreguntas;
    ArrayList<String> listaRespuestas;
    ArrayList<Integer> listaTipoPreguntas;
    ArrayList<Integer> listaPonderacion;
    private TextView mQuestionView;
    private int answerNumber=0;
    private int ponderacionTotal=0;
    private int ponderacionParcial=0;
    private Button btnRespuesta1;
    private Button btnRespuesta2;
    private Button btnRespuesta3;
    private Button btnRespuesta4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion);
        helper= new ControlBDHelper(this);
        listaPreguntas = new ArrayList<String>();
        listaRespuestas = new ArrayList<String>();
        listaTipoPreguntas = new ArrayList<Integer>();
        listaPonderacion= new ArrayList<Integer>();
        Intent i = getIntent();
        id=Integer.parseInt(i.getStringExtra("idcuestionario"));
        user = i.getStringExtra("Usuario");
        cargarCuestionario();
        for (int x=0; x<detallesList.size();x++){
            Cursor pregunta= helper.db.rawQuery("SELECT descrippreg,tipopreg,ponderapregunt FROM pregunta WHERE idpregunta= "+detallesList.get(x).getIdpregunta(),null);
            while(pregunta.moveToNext()){
                listaPreguntas.add(pregunta.getString(0));
                listaTipoPreguntas.add(pregunta.getInt(1));
                listaPonderacion.add(pregunta.getInt(2));
                ponderacionTotal+=pregunta.getInt(2);
                Cursor respuesta= helper.db.rawQuery("SELECT descripopc FROM opcion WHERE idpregunta= "+detallesList.get(x).getIdpregunta(),null);
                while(respuesta.moveToNext()){
                    listaRespuestas.add(respuesta.getString(0));
                }
            }
        }

        mQuestionView = (TextView) findViewById(R.id.question);
        btnRespuesta1 = (Button) findViewById(R.id.choice1);
        btnRespuesta2 = (Button) findViewById(R.id.choice2);
        btnRespuesta3 = (Button) findViewById(R.id.choice3);
        btnRespuesta4 = (Button) findViewById(R.id.choice4);
        updateQuestion();
    }
    public void updateQuestion(){
        if(mQuestionNumber < listaPreguntas.size()){
            mQuestionView.setText(listaPreguntas.get(mQuestionNumber));
            if(listaTipoPreguntas.get(mQuestionNumber)==1){
                for (int i=1; i <= 4; i++){

                    switch (i){
                        case 1:
                            Cursor cursorCorrecta = helper.db.rawQuery("SELECT esCorrecta FROM opcion WHERE idpregunta="+detallesList.get(mQuestionNumber).getIdpregunta()+ " AND descripopc='"+listaRespuestas.get(answerNumber)+"'",null);
                            while(cursorCorrecta.moveToNext()){
                                if(cursorCorrecta.getInt(0)==1){
                                    btnRespuesta1.setText(listaRespuestas.get(answerNumber));
                                    answer=listaRespuestas.get(answerNumber);
                                    answerNumber++;
                                }else{
                                    btnRespuesta1.setText(listaRespuestas.get(answerNumber));
                                    answerNumber++;

                                }
                            }

                            break;
                        case 2:
                            Cursor cursorCorrecta2 = helper.db.rawQuery("SELECT esCorrecta FROM opcion WHERE idpregunta="+detallesList.get(mQuestionNumber).getIdpregunta() + " AND descripopc='"+listaRespuestas.get(answerNumber)+"'",null);
                            while(cursorCorrecta2.moveToNext()){
                                if(cursorCorrecta2.getInt(0)==1){
                                    btnRespuesta2.setText(listaRespuestas.get(answerNumber));
                                    answer=listaRespuestas.get(answerNumber);
                                    answerNumber++;
                                }else{
                                    btnRespuesta2.setText(listaRespuestas.get(answerNumber));
                                    answerNumber++;
                                }
                            }
                            break;
                        case 3:
                            Cursor cursorCorrecta3 = helper.db.rawQuery("SELECT esCorrecta FROM opcion WHERE idpregunta="+detallesList.get(mQuestionNumber).getIdpregunta() + " AND descripopc='"+listaRespuestas.get(answerNumber)+"'",null);
                            while(cursorCorrecta3.moveToNext()){
                                if(cursorCorrecta3.getInt(0)==1){
                                    btnRespuesta3.setText(listaRespuestas.get(answerNumber));
                                    answer=listaRespuestas.get(answerNumber);
                                    answerNumber++;
                                }else{
                                    btnRespuesta3.setText(listaRespuestas.get(answerNumber));
                                    answerNumber++;
                                }
                            }
                        case 4:
                            Cursor cursorCorrecta4 = helper.db.rawQuery("SELECT esCorrecta FROM opcion WHERE idpregunta="+detallesList.get(mQuestionNumber).getIdpregunta() + " AND descripopc='"+listaRespuestas.get(answerNumber)+"'",null);
                            while(cursorCorrecta4.moveToNext()){
                                if(cursorCorrecta4.getInt(0)==1){
                                    btnRespuesta4.setText(listaRespuestas.get(answerNumber));
                                    answer=listaRespuestas.get(answerNumber);
                                    answerNumber++;
                                }else{
                                    btnRespuesta4.setText(listaRespuestas.get(answerNumber));
                                    answerNumber++;
                                }
                            }
                    }
                }
            }else{
                btnRespuesta3.setVisibility(View.INVISIBLE);
                btnRespuesta4.setVisibility(View.INVISIBLE);
                for (int i=1; i <= 2; i++){
                    switch (i){
                        case 1:
                            System.out.println("ESTA EN CASO 1 OPCION VERDADERO");
                            Cursor cursorCorrecta = helper.db.rawQuery("SELECT esCorrecta FROM opcion WHERE idpregunta="+detallesList.get(mQuestionNumber).getIdpregunta() + " AND descripopc='"+listaRespuestas.get(answerNumber)+"'",null);
                            while(cursorCorrecta.moveToNext()){
                                if(cursorCorrecta.getInt(0)==1){
                                    btnRespuesta1.setText(listaRespuestas.get(answerNumber));
                                    answer=listaRespuestas.get(answerNumber);
                                    answerNumber++;
                                }else{
                                    System.out.println("ESTA EN CASO 2 OPCION FALSO");
                                    btnRespuesta1.setText(listaRespuestas.get(answerNumber));
                                    answerNumber++;
                                }
                            }

                            break;
                        case 2:
                            Cursor cursorCorrecta2 = helper.db.rawQuery("SELECT esCorrecta FROM opcion WHERE idpregunta="+detallesList.get(mQuestionNumber).getIdpregunta() + " AND descripopc='"+listaRespuestas.get(answerNumber)+"'",null);
                            while(cursorCorrecta2.moveToNext()){
                                if(cursorCorrecta2.getInt(0)==1){
                                    btnRespuesta2.setText(listaRespuestas.get(answerNumber));
                                    answer=listaRespuestas.get(answerNumber);
                                    answerNumber++;
                                }else{
                                    btnRespuesta2.setText(listaRespuestas.get(answerNumber));
                                    answerNumber++;
                                }
                            }
                            break;
                    }
                }
            }
            mQuestionNumber++;
        }else{
            Toast.makeText(this,"Nota del Examen:"+String.valueOf((ponderacionParcial/ponderacionTotal)*10),Toast.LENGTH_LONG).show();
            Intent i = new Intent(this,RealizarExamenActivity.class);
            startActivity(i);
        }
    }

    public void onClick(View view){
        Button answer1 = (Button)  view;
        if (answer1.getText().equals(answer)){
                ponderacionParcial=listaPonderacion.get(mQuestionWeight);
                mQuestionWeight++;
        }
        updateQuestion();
    }



    public void cargarCuestionario(){
        DetallePregunta detalle;
        detallesList = new ArrayList<DetallePregunta>();
        helper.abrir();
        helper.cerrar();
        helper.abrir();
        Cursor cursorPreguntas = helper.db.rawQuery("SELECT idpregunta FROM detallePregunta WHERE idcuestionario = "+id,null);
        while(cursorPreguntas.moveToNext()){
            detalle = new DetallePregunta();
            detalle.setIdcuestionario(id);
            detalle.setIdpregunta(cursorPreguntas.getInt(0));
            detallesList.add(detalle);
        }
    }


}
