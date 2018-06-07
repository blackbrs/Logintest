package creator.test.logintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

        request= Volley.newRequestQueue(getApplicationContext());


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
        Toast.makeText(getApplicationContext(),"Se ha registrado exitosamente",Toast.LENGTH_LONG).show();
        editCarnetestu.setText(" ");
        editDireccionEstu.setText(" ");
        editCorreoEstu.setText(" ");
        editApellidoEstu.setText(" ");
        editNombreEstu.setText(" ");

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(),"No se puedo actualizar, asegurate de llenar todos los campos y/o no introducir un usuario ya eliminado", Toast.LENGTH_LONG).show();

    }


}
