package artbot.com.sistemainspector.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import artbot.com.sistemainspector.HomeActivity;
import artbot.com.sistemainspector.R;
import artbot.com.sistemainspector.ScanActivity;
import artbot.com.sistemainspector.view.fragment.ComercianteFragment;
import artbot.com.sistemainspector.view.fragment.MapFragment;
import artbot.com.sistemainspector.view.fragment.ReportFragment;

public class ContainerActivity extends AppCompatActivity {

    private boolean viewIsAtHome;

    private  String NameUser;
    private int idUser;

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

    final private int REQUEST_CODE_ASK_PERMISSION=111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        final ComercianteFragment comercianteFragment = new ComercianteFragment();
        final MapFragment mapFragment  = new MapFragment();
        final ReportFragment reportFragment = new ReportFragment();

        Intent intentfrag = getIntent();

        idUser = intentfrag.getExtras().getInt("idUser");
        NameUser = intentfrag.getStringExtra("nameUser");

        idResult = intentfrag.getExtras().getInt("id");
        userResult = intentfrag.getStringExtra("user");

        user2Result = intentfrag.getStringExtra("nombre_segundo");
        curpResult = intentfrag.getStringExtra("curp");
        emailResult = intentfrag.getStringExtra("email");
        telResult = intentfrag.getStringExtra("tel");
        celResult = intentfrag.getStringExtra("cel");
        generoResult = intentfrag.getStringExtra("genero");
        giroResult = intentfrag.getStringExtra("giro");
        tagsResult = intentfrag.getStringExtra("tags");
        tipoResult = intentfrag.getStringExtra("tipo");
        tarjetonResult = intentfrag.getStringExtra("tarjetonsindicato");

        horarioResult = intentfrag.getExtras().getInt("horario");

        descripcionResult = intentfrag.getStringExtra("descripcion");
        zonaResult = intentfrag.getStringExtra("zona");
        direccionResult = intentfrag.getStringExtra("direccion");
        coordenadasResult = intentfrag.getStringExtra("coordenadas");

        metrosResult = intentfrag.getExtras().getInt("metros");

        estructuraResult = intentfrag.getStringExtra("estructura");
        comentarioResult = intentfrag.getStringExtra("comentario");
        estadoResult = intentfrag.getStringExtra("estado");

        vigenciaResult = intentfrag.getExtras().getInt("vigencia");
        imgResult = intentfrag.getStringExtra("img");

        pngResult = intentfrag.getStringExtra("png");
        jpegResult = intentfrag.getStringExtra("jpeg");
        pdfResult = intentfrag.getStringExtra("pdf");



        Bundle bundle = new Bundle();

        bundle.putInt("idUser", idUser);
        bundle.putString("nameUser", NameUser);


        bundle.putInt("id", idResult);
        bundle.putString("user", userResult);

        bundle.putString("nombre_segundo", user2Result);
        bundle.putString("curp", curpResult);
        bundle.putString("email", emailResult);
        bundle.putString("tel", telResult);
        bundle.putString("cel", celResult);
        bundle.putString("genero", generoResult);
        bundle.putString("giro", giroResult);
        bundle.putString("tags", tagsResult);
        bundle.putString("tipo", tipoResult);
        bundle.putString("tarjetonsindicato", tarjetonResult);

        bundle.putInt("horario", horarioResult);

        bundle.putString("descripcion", descripcionResult);
        bundle.putString("zona", zonaResult);
        bundle.putString("direccion", direccionResult);
        bundle.putString("coordenadas", coordenadasResult);

        bundle.putInt("metros", metrosResult);

        bundle.putString("estructura", estructuraResult);
        bundle.putString("comentario", comentarioResult);
        bundle.putString("estado", estadoResult);

        bundle.putInt("vigencia", vigenciaResult);
        bundle.putString("img", imgResult);

        bundle.putString("png", pngResult);
        bundle.putString("jpeg", jpegResult);
        bundle.putString("pdf", pdfResult);

        comercianteFragment.setArguments(bundle);
        mapFragment.setArguments(bundle);
        reportFragment.setArguments(bundle);

        /*Bundle bundleMap = new Bundle();

        bundleMap.putString("zona", zonaResult);
        bundleMap.putString("direccion", direccionResult);
        bundleMap.putString("coordenadas", coordenadasResult);
        mapFragment.setArguments(bundleMap);*/





        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, comercianteFragment)
                    .commit();
        }


        //TextView textViewuserNameComerciante = (TextView) findViewById(R.id.userNameComerciante);
        //textViewuserNameComerciante.setText(name);





        BottomNavigationView bottombar = findViewById(R.id.bottombar);
        bottombar.setSelectedItemId(R.id.comercianteItem);

        bottombar.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.comercianteItem:
                        addFragment(comercianteFragment);
                        viewIsAtHome = true;
                        break;
                    case R.id.mapaItem:
                        int permisolugar = ActivityCompat.checkSelfPermission(ContainerActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);

                        if (permisolugar != PackageManager.PERMISSION_GRANTED) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_ASK_PERMISSION);
                            }

                        }else {
                            addFragment(mapFragment);
                            viewIsAtHome = false;
                        }


                        break;
                    case R.id.reportesItem:
                        addFragment(reportFragment);
                        viewIsAtHome = false;
                        break;
                    case R.id.inicio:
                        finish();
                        break;
                }
                return true;
            }

            private void addFragment(Fragment fragment) {
                if (null != fragment) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, fragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
    }

    public void onBackPressed(){
        if (!viewIsAtHome) { //Si la vista actual no es el fragment Home
            BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottombar);
            bottomNavigationView.setSelectedItemId(R.id.comercianteItem); //Selecciona el fragment Home
        } else {
            finish();
        }
    }





}
