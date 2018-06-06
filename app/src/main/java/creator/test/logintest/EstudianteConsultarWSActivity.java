package creator.test.logintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EstudianteConsultarWSActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    EditText editCarnetestu;
    EditText editNombreEstu;
    EditText editApellidoEstu;
    EditText editCorreoEstu;
    EditText editDireccionEstu;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_estudiante_consultar_ws);
        editCarnetestu = (EditText) findViewById(R.id.editUsuaCarnet);
        editNombreEstu= (EditText) findViewById(R.id.editNombreEstu);
        editApellidoEstu= (EditText) findViewById(R.id.editApellidoEstu);
        editCorreoEstu = (EditText) findViewById(R.id.editCorreoEstu);
        editDireccionEstu= (EditText) findViewById(R.id.editDireccionEstu);

        request= Volley.newRequestQueue(getApplicationContext());

    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getApplicationContext(),"Conexion y Consulta Exitosa",Toast.LENGTH_LONG).show();
        Estudiante e = new Estudiante();

        //JSONArray json=response.optJSONArray("carnet");
        JSONObject jsonObject= new JSONObject();

            jsonObject=response;
            e.setNombreestu(jsonObject.optString("nombreestu"));
            e.setApellidoestu(jsonObject.optString("apellidoestu"));
            e.setCorreoestu(jsonObject.optString("correoestu"));
            e.setDireccionestu(jsonObject.optString("direccionestu"));

        editNombreEstu.setText(e.getNombreestu());
        editApellidoEstu.setText(e.getApellidoestu());
        editCorreoEstu.setText(e.getCorreoestu());
        editDireccionEstu.setText(e.getDireccionestu());

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(),"No se pudo Consultar, no se encontro el registro",Toast.LENGTH_LONG).show();


    }

    public void consultarWebServices(View v){
        String url= "https://pa15045pdm.000webhostapp.com/ws_estudiante_consultar.php?carnet="+editCarnetestu.getText().toString();
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);

    }


}
