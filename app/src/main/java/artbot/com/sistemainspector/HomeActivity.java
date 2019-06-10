package artbot.com.sistemainspector;

import android.Manifest;
import android.app.assist.AssistStructure;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.SparseArray;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import artbot.com.sistemainspector.model.SearchFolioDB;
import artbot.com.sistemainspector.view.ContainerActivity;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private int request_code = 1;
    private String tittle = "Inicio";

    private String NameUser, EmailUser, img;
    private int idUser;
    TextView TextViewNameUser, TextViewEmailUser;
    private ImageView imageViewUser;

    private int idResult;
    private String userResult = "";
    private String user2Result = "";
    private String curpResult = "";
    private String emailResult = "";
    private String telResult = "";
    private String celResult = "";
    private String generoResult = "";
    private String giroResult = "";
    private String tagsResult = "";
    private String tipoResult = "";
    private String tarjetonResult = "";
    private int horarioResult;
    private String descripcionResult = "";
    private String zonaResult = "";
    private String direccionResult = "";
    private String coordenadasResult = "";
    private int metrosResult;
    private String estructuraResult = "";
    private String comentarioResult = "";
    private String estadoResult = "";
    private int vigenciaResult;
    private String imgResult = "";
    private String pngResult = "";
    private String jpegResult = "";
    private String pdfResult = "";

    private String formato1 = "";
    private String formato2 = "";


    FloatingActionButton scaner;
    final private int REQUEST_CODE_ASK_PERMISSION=111;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);

        TextInputEditText editTextFolio = (TextInputEditText) findViewById(R.id.SearchFolio);

        Intent intent1 = getIntent();
        NameUser = intent1.getStringExtra("user");
        EmailUser = intent1.getStringExtra("email");
        img = intent1.getStringExtra("img");
        idUser = intent1.getIntExtra("id", 0);

        scaner= (FloatingActionButton) findViewById(R.id.scanner);

        scaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int permisoCamara = ActivityCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.CAMERA);

                if (permisoCamara != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_ASK_PERMISSION);
                    }

                }else {
                    Intent intent = new Intent(HomeActivity.this, ScanActivity.class);
                    startActivityForResult(intent, request_code);
                }

            }
        });





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        TextViewNameUser = (TextView) findViewById(R.id.navMenuEmail);
        TextViewEmailUser = (TextView) findViewById(R.id.navMenuName);
        imageViewUser = (ImageView) findViewById(R.id.imageViewUser);

        TextViewNameUser.setText(NameUser);
        TextViewEmailUser.setText(EmailUser);
        Picasso.get().load("http://www.carmen.gob.mx/sistema-ambulante/uploads/users/"+img).into(imageViewUser);
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       // if (id == R.id.action_settings) {
        //    return true;
       // }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //if (id == R.id.nav_camera) {
            // Handle the camera action
        //} else if (id == R.id.nav_gallery) {

        //} else if (id == R.id.nav_slideshow) {

        //} else if (id == R.id.nav_manage) {

        //} else if (id == R.id.nav_share) {}
        if (id == R.id.closeSession) {

            LoginActivity.CerrarSesion(HomeActivity.this,false);
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if ((requestCode == request_code) && (resultCode == RESULT_OK)){
            //Toast.makeText(getApplicationContext(),data.getDataString(),Toast.LENGTH_SHORT).show();
            TextInputEditText editTextFolio = (TextInputEditText) findViewById(R.id.SearchFolio);
            editTextFolio.setText(data.getDataString());
            String folio = editTextFolio.getText().toString();


            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try{
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        if (success){
                            idResult = jsonResponse.getInt("id");
                            userResult = jsonResponse.getString("nombre");

                            user2Result = jsonResponse.getString("nombre_segundo");
                            curpResult = jsonResponse.getString("curp");
                            emailResult = jsonResponse.getString("email");
                            telResult = jsonResponse.getString("tel");
                            celResult = jsonResponse.getString("cel");
                            generoResult = jsonResponse.getString("genero");
                            giroResult = jsonResponse.getString("giro");
                            tagsResult = jsonResponse.getString("tags");
                            tipoResult = jsonResponse.getString("tipo");
                            tarjetonResult = jsonResponse.getString("tarjetonsindicato");
                            horarioResult = jsonResponse.getInt("horario");
                            descripcionResult = jsonResponse.getString("descripcion");
                            zonaResult = jsonResponse.getString("zona");
                            direccionResult = jsonResponse.getString("direccion");
                            coordenadasResult = jsonResponse.getString("coordenadas");
                            metrosResult = jsonResponse.getInt("metros");
                            estructuraResult = jsonResponse.getString("estructura");
                            comentarioResult = jsonResponse.getString("comentario");
                            estadoResult = jsonResponse.getString("estado");

                            vigenciaResult = jsonResponse.getInt("vigencia");
                            imgResult = jsonResponse.getString("img");

                            pngResult = jsonResponse.getString("png");
                            jpegResult = jsonResponse.getString("jpeg");
                            pdfResult = jsonResponse.getString("pdf");

                            formato1 = jsonResponse.getString("formato1");
                            formato2 = jsonResponse.getString("formato2");



                            Intent intent = new Intent(HomeActivity.this, ContainerActivity.class);

                            intent.putExtra("idUser",idUser);
                            intent.putExtra("nameUser",NameUser);

                            intent.putExtra("id",idResult);
                            intent.putExtra("user",userResult);

                            intent.putExtra("nombre_segundo",user2Result);
                            intent.putExtra("curp",curpResult);
                            intent.putExtra("email",emailResult);
                            intent.putExtra("tel",telResult);
                            intent.putExtra("cel",celResult);
                            intent.putExtra("genero",generoResult);
                            intent.putExtra("giro",giroResult);
                            intent.putExtra("tags",tagsResult);
                            intent.putExtra("tipo",tipoResult);
                            intent.putExtra("tarjetonsindicato",tarjetonResult);
                            intent.putExtra("horario",horarioResult);
                            intent.putExtra("descripcion",descripcionResult);
                            intent.putExtra("zona",zonaResult);
                            intent.putExtra("direccion",direccionResult);
                            intent.putExtra("coordenadas",coordenadasResult);
                            intent.putExtra("metros",metrosResult);
                            intent.putExtra("estructura",estructuraResult);
                            intent.putExtra("comentario",comentarioResult);
                            intent.putExtra("estado",estadoResult);

                            intent.putExtra("vigencia",vigenciaResult);
                            intent.putExtra("img",imgResult);

                            intent.putExtra("png",pngResult);
                            intent.putExtra("jpeg",jpegResult);
                            intent.putExtra("pdf",pdfResult);

                            intent.putExtra("formato1",formato1);
                            intent.putExtra("formato2",formato2);

                            startActivity(intent);
                        }else{
                            android.support.v7.app.AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                            builder.setMessage("Folio no encontrado")
                                    .setNegativeButton("Ok",null)
                                    .create().show();
                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"Ingrese un correo valido",Toast.LENGTH_SHORT).show();
                    }
                }
            };

            SearchFolioDB searchFolioDB = new SearchFolioDB(folio,responseListener);
            RequestQueue queue = Volley.newRequestQueue(HomeActivity.this);
            queue.add(searchFolioDB);






           /* String valorfolio = "00001";
            if (folio.equals(valorfolio)){
                Toast.makeText(getApplicationContext(),"Valido",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ContainerActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(),"No valido",Toast.LENGTH_SHORT).show();
            }*/
        }
    }

    public void goSearchFolio(View view){
        TextInputEditText editTextFolio = (TextInputEditText) findViewById(R.id.SearchFolio);
        String folio = editTextFolio.getText().toString();




        //buscar en base de datos


        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success){
                        idResult = jsonResponse.getInt("id");
                        userResult = jsonResponse.getString("nombre");

                        user2Result = jsonResponse.getString("nombre_segundo");
                        curpResult = jsonResponse.getString("curp");
                        emailResult = jsonResponse.getString("email");
                        telResult = jsonResponse.getString("tel");
                        celResult = jsonResponse.getString("cel");
                        generoResult = jsonResponse.getString("genero");
                        giroResult = jsonResponse.getString("giro");
                        tagsResult = jsonResponse.getString("tags");
                        tipoResult = jsonResponse.getString("tipo");
                        tarjetonResult = jsonResponse.getString("tarjetonsindicato");
                        horarioResult = jsonResponse.getInt("horario");
                        descripcionResult = jsonResponse.getString("descripcion");
                        zonaResult = jsonResponse.getString("zona");
                        direccionResult = jsonResponse.getString("direccion");
                        coordenadasResult = jsonResponse.getString("coordenadas");
                        metrosResult = jsonResponse.getInt("metros");
                        estructuraResult = jsonResponse.getString("estructura");
                        comentarioResult = jsonResponse.getString("comentario");
                        estadoResult = jsonResponse.getString("estado");

                        vigenciaResult = jsonResponse.getInt("vigencia");
                        imgResult = jsonResponse.getString("img");

                        pngResult = jsonResponse.getString("png");
                        jpegResult = jsonResponse.getString("jpeg");
                        pdfResult = jsonResponse.getString("pdf");


                        formato1 = jsonResponse.getString("formato1");
                        formato2 = jsonResponse.getString("formato2");



                        Intent intent = new Intent(HomeActivity.this, ContainerActivity.class);

                        intent.putExtra("idUser",idUser);
                        intent.putExtra("nameUser",NameUser);

                        intent.putExtra("id",idResult);
                        intent.putExtra("user",userResult);

                        intent.putExtra("nombre_segundo",user2Result);
                        intent.putExtra("curp",curpResult);
                        intent.putExtra("email",emailResult);
                        intent.putExtra("tel",telResult);
                        intent.putExtra("cel",celResult);
                        intent.putExtra("genero",generoResult);
                        intent.putExtra("giro",giroResult);
                        intent.putExtra("tags",tagsResult);
                        intent.putExtra("tipo",tipoResult);
                        intent.putExtra("tarjetonsindicato",tarjetonResult);
                        intent.putExtra("horario",horarioResult);
                        intent.putExtra("descripcion",descripcionResult);
                        intent.putExtra("zona",zonaResult);
                        intent.putExtra("direccion",direccionResult);
                        intent.putExtra("coordenadas",coordenadasResult);
                        intent.putExtra("metros",metrosResult);
                        intent.putExtra("estructura",estructuraResult);
                        intent.putExtra("comentario",comentarioResult);
                        intent.putExtra("estado",estadoResult);

                        intent.putExtra("vigencia",vigenciaResult);
                        intent.putExtra("img",imgResult);

                        intent.putExtra("png",pngResult);
                        intent.putExtra("jpeg",jpegResult);
                        intent.putExtra("pdf",pdfResult);

                        intent.putExtra("formato1",formato1);
                        intent.putExtra("formato2",formato2);

                        startActivity(intent);
                    }else{
                        android.support.v7.app.AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                        builder.setMessage("Folio no encontrado")
                                .setNegativeButton("Ok",null)
                                .create().show();
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Ingrese un correo valido"+ e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        };

        SearchFolioDB searchFolioDB = new SearchFolioDB(folio,responseListener);
        RequestQueue queue = Volley.newRequestQueue(HomeActivity.this);
        queue.add(searchFolioDB);


        /*String valorfolio = "00001";
        if (folio.equals(valorfolio)){
            Toast.makeText(getApplicationContext(),"Valido",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ContainerActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"No valido",Toast.LENGTH_SHORT).show();
        }*/
    }


}
