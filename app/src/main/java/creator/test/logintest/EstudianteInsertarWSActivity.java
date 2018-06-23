package creator.test.logintest;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;


public class EstudianteInsertarWSActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    EditText editNombreestu;
    EditText editApellido;
    EditText editCorreo;
    EditText editDireccion;
    EditText editUser;
    EditText editClave;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_estudiante_insertar_ws);
        editNombreestu = (EditText) findViewById(R.id.nombre);
        editApellido = (EditText) findViewById(R.id.apellido);
        editCorreo = (EditText) findViewById(R.id.correo);
        editDireccion = (EditText) findViewById(R.id.direccion);
        editUser = (EditText) findViewById(R.id.carnet);
        editClave = (EditText) findViewById(R.id.editClave);

        request= Volley.newRequestQueue(getApplicationContext());

    }

    public void cargarWebServices(View v){

        if (editNombreestu.getText().toString().isEmpty() || editUser.getText().toString().isEmpty() || editDireccion.getText().toString().isEmpty() ||editCorreo.getText().toString().isEmpty() || editApellido.getText().toString().isEmpty()||editClave.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Debes llenar todos los campos ",Toast.LENGTH_LONG).show();
        }
        else{
            //Por si se quiere realizar local
            //String url="http://192.168.43.18/ws_estudiante_insertar.php?carnet="+editUser.getText().toString()+"&nombre="+editNombreestu.getText().toString()+"&apellido="+editApellido.getText().toString()+"&correo="+editCorreo.getText().toString()+"&direccion="+editDireccion.getText().toString()+"&user="+editUser.getText().toString()+"&psw="+editClave.getText().toString();


            String url="http://pa15045pdm.000webhostapp.com/ws_estudiante_insertar.php?carnet="+editUser.getText().toString()+"&nombre="+editNombreestu.getText().toString()+"&apellido="+editApellido.getText().toString()+"&correo="+editCorreo.getText().toString()+"&direccion="+editDireccion.getText().toString()+"&user="+editUser.getText().toString()+"&psw="+editClave.getText().toString();

            url=url.replace(" ","%20");
            jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url,null,this,this);
            request.add(jsonObjectRequest);
        }
    }

    @Override
    public void onResponse(JSONObject response) {

        Toast.makeText(getApplicationContext(),"Se ha registrado exitosamente",Toast.LENGTH_LONG).show();
        editApellido.setText("");
        editClave.setText("");
        editCorreo.setText("");
        editDireccion.setText("");
        editNombreestu.setText("");
        editUser.setText("");

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(),"No se puedo registrar, asegurate de llenar todos los campos y/o no introducir un usuario ya registrado", Toast.LENGTH_LONG).show();

    }






}
