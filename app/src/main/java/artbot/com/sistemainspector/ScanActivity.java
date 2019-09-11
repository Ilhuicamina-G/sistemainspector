package artbot.com.sistemainspector;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
    private String scaneo;

    final private int REQUEST_CODE_ASK_PERMISSION=111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        solicitarpermiso();


        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();

    }

    @Override
    public void handleResult(Result rawResult) {
        Log.v("HandleResult",rawResult.getText());
        scaneo = rawResult.getText();
        //Toast.makeText(getApplicationContext(),valor,Toast.LENGTH_SHORT).show();
        //mScannerView.resumeCameraPreview(this);
        Intent data = new Intent();
        data.setData(Uri.parse(scaneo));
        setResult(RESULT_OK, data);
        onPause();
        finish();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    private void  solicitarpermiso() {

        int permisoCamara = ActivityCompat.checkSelfPermission(ScanActivity.this, Manifest.permission.CAMERA);

        if (permisoCamara!= PackageManager.PERMISSION_GRANTED){
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
               requestPermissions(new String[]{Manifest.permission.CAMERA},REQUEST_CODE_ASK_PERMISSION);
        }


        }
    }


}
