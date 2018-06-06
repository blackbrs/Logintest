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

import org.json.JSONObject;

public class EstudianteEliminarWSActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    EditText editCarnet;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_estudiante_eliminar_ws);
        editCarnet = (EditText) findViewById(R.id.editCarne);

        request= Volley.newRequestQueue(getApplicationContext());
    }

    public void eliminarEstu(View v){
        String url="https://pa15045pdm.000webhostapp.com/ws_estudiante_eliminar.php?carnet="+editCarnet.getText().toString();
        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url,null,this,this);
        request.add(jsonObjectRequest);

    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getApplicationContext(),"Se ha eliminado exitosamente",Toast.LENGTH_LONG).show();
        editCarnet.setText(" ");

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(),"No se puedo eliminar: "+error.toString(), Toast.LENGTH_LONG).show();

    }


}
