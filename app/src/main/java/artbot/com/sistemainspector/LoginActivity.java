package artbot.com.sistemainspector;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import artbot.com.sistemainspector.model.LoginRequest;

public class LoginActivity extends AppCompatActivity {

    private int idResult;
    private String userResult = "";
    private String emailResult = "";
    private String passResult = "";
    private String telResult = "";
    private String dirResult = "";
    private String nivelResult = "";
    private String imgResult = "";

    private String email;
    private String pass;

    private RadioButton RBsesion;
    private boolean isActivateRadioButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (obtenerEstadoyDatos()){
            SharedPreferences preferences = getSharedPreferences("USER", Context.MODE_PRIVATE);


            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);

            intent.putExtra("id",preferences.getInt("id",0));
            intent.putExtra("user",preferences.getString("user","hola"));
            intent.putExtra("email",preferences.getString("email",""));
            intent.putExtra("pass",preferences.getString("pass",""));
            intent.putExtra("tel",preferences.getString("tel",""));
            intent.putExtra("dir",preferences.getString("dir",""));
            intent.putExtra("nivel",preferences.getString("nivel",""));
            intent.putExtra("img",preferences.getString("img",""));
            //guardarEstadoyDatos();
            startActivity(intent);
            finish();
        }

        TextInputEditText editTextEmail = (TextInputEditText) findViewById(R.id.userEmail);
        TextInputEditText editTextPass = (TextInputEditText) findViewById(R.id.userPass);
        email = editTextEmail.getText().toString();
        pass = editTextPass.getText().toString();

        RBsesion = (RadioButton) findViewById(R.id.RBsesion);
        isActivateRadioButton = RBsesion.isChecked();
        RBsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isActivateRadioButton){
                    RBsesion.setChecked(false);
                }
                isActivateRadioButton = RBsesion.isChecked();
            }
        });
    }

    public static void CerrarSesion(Context c, boolean b){
        SharedPreferences preferences = c.getSharedPreferences("USER", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("sesion",false);
        editor.putInt("id",0);
        editor.putString("user","");
        editor.putString("email","");
        editor.putString("pass","");
        editor.putString("tel","");
        editor.putString("dir","");
        editor.putString("nivel","");
        editor.putString("img","");
        editor.commit();
    }

    public void guardarEstadoyDatos(){

        SharedPreferences preferences = getSharedPreferences("USER", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("sesion",RBsesion.isChecked());
        editor.putInt("id",idResult);
        editor.putString("user",userResult);
        editor.putString("email",email);
        editor.putString("pass",passResult);
        editor.putString("tel",telResult);
        editor.putString("dir",dirResult);
        editor.putString("nivel",nivelResult);
        editor.putString("img",imgResult);
        editor.commit();

    }

    public boolean obtenerEstadoyDatos(){
        SharedPreferences preferences = getSharedPreferences("USER", Context.MODE_PRIVATE);
        return preferences.getBoolean("sesion",false);
    }

    public void goLoginAccount(View view){
        TextInputEditText editTextEmail = (TextInputEditText) findViewById(R.id.userEmail);
        TextInputEditText editTextPass = (TextInputEditText) findViewById(R.id.userPass);
        email = editTextEmail.getText().toString();
        pass = editTextPass.getText().toString();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success){
                        idResult = jsonResponse.getInt("id");
                        userResult = jsonResponse.getString("user");
                        emailResult = jsonResponse.getString("email");
                        passResult = jsonResponse.getString("pass");
                        telResult = jsonResponse.getString("telefono");
                        dirResult = jsonResponse.getString("direccion");
                        nivelResult = jsonResponse.getString("nivel");
                        imgResult = jsonResponse.getString("img");

                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);

                        intent.putExtra("id",idResult);
                        intent.putExtra("user",userResult);
                        intent.putExtra("email",emailResult);
                        intent.putExtra("pass",passResult);
                        intent.putExtra("tel",telResult);
                        intent.putExtra("dir",dirResult);
                        intent.putExtra("nivel",nivelResult);
                        intent.putExtra("img",imgResult);
                        guardarEstadoyDatos();
                        startActivity(intent);
                        finish();
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setMessage("Correo o contraseña incorrecto")
                                .setNegativeButton("Entendido",null)
                                .create().show();
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Ingrese un correo valido",Toast.LENGTH_SHORT).show();
                }
            }
        };

        LoginRequest loginRequest = new LoginRequest(email,pass,responseListener);
        RequestQueue queue =Volley.newRequestQueue(LoginActivity.this);
        queue.add(loginRequest);

    }

}
