package creator.test.logintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Map;

public class EstudianteActualizarWSActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {


    EditText editCarnetestu;
    EditText editNombreEstu;
    EditText editApellidoEstu;
    EditText editCorreoEstu;
    EditText editDireccionEstu;
    Button consu;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_estudiante_actualizar_ws);
        editCarnetestu = (EditText) findViewById(R.id.editarUsuario);
        editNombreEstu= (EditText) findViewById(R.id.editarNombre);
        editApellidoEstu= (EditText) findViewById(R.id.editarApellido);
        editCorreoEstu = (EditText) findViewById(R.id.editarCorreo);
        editDireccionEstu= (EditText) findViewById(R.id.editarDireccion);

        consu= (Button) findViewById(R.id.bt01);

        consu.setVisibility(View.INVISIBLE);


        request= Volley.newRequestQueue(getApplicationContext());

    }

    public void consultarWs(View v){
        consu.setVisibility(View.VISIBLE);
        if(editCarnetestu.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Debes llenar el campo de busqueda ",Toast.LENGTH_LONG).show();
        }
        else{
            String url = "https://pa15045pdm.000webhostapp.com/ws_estudiante_consultar.php?carnet=" + editCarnetestu.getText().toString();
            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
            System.out.println("RESPONEJSON"+jsonObjectRequest);
            request.add(jsonObjectRequest);

        }
    }

    public void actualizarEstu(View v){
        if (editNombreEstu.getText().toString().isEmpty() || editCarnetestu.getText().toString().isEmpty() || editApellidoEstu.getText().toString().isEmpty() || editCorreoEstu.getText().toString().isEmpty()||editDireccionEstu.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Debes llenar todos los campos ",Toast.LENGTH_LONG).show();
        }
        else {
            String url = "https://pa15045pdm.000webhostapp.com/ws_estudiante_actualizar.php?carnet=" + editCarnetestu.getText().toString() + "&nombre=" + editNombreEstu.getText().toString() + "&apellido=" + editApellidoEstu.getText().toString() + "&correo=" + editCorreoEstu.getText().toString() + "&direccion=" + editDireccionEstu.getText().toString();
            url = url.replace(" ", "%20");
            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
            request.add(jsonObjectRequest);
        }

    }

    @Override
    public void onResponse(JSONObject response) {

        JSONObject reu=response;
        Estudiante e = new Estudiante();

        if(response.optString("resultado")=="1"){

            editCarnetestu.setText("");
            editDireccionEstu.setText("");
            editCorreoEstu.setText("");
            editApellidoEstu.setText("");
            editNombreEstu.setText("");
            Toast.makeText(getApplicationContext(),"Se ha actualizado exitosamente",Toast.LENGTH_LONG).show();
        }
        else{

            if(response.optString("resultado")=="0"){
                Toast.makeText(getApplicationContext(),"No se actualizo, posiblemente no exista el estudiante o ha sido eliminado ",Toast.LENGTH_LONG).show();
            }
            else{
                e.setNombreestu(reu.optString("nombreestu"));
                e.setApellidoestu(reu.optString("apellidoestu"));
                e.setCorreoestu(reu.optString("correoestu"));
                e.setDireccionestu(reu.optString("direccionestu"));

                editNombreEstu.setText(e.getNombreestu());
                editApellidoEstu.setText(e.getApellidoestu());
                editCorreoEstu.setText(e.getCorreoestu());
                editDireccionEstu.setText(e.getDireccionestu());
            }
        }


    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(),"No se puedo realizar, asegurate de llenar todos los campos y/o no introducir un usuario ya eliminado", Toast.LENGTH_LONG).show();

    }


}
