package com.example.alexa.project2;

import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

public class MainActivity extends AppCompatActivity implements QRCodeReaderView.OnQRCodeReadListener, View.OnClickListener {

    QRCodeReaderView mydecoderview ;
    private TextView resultTextView;
    Button b1;
    RelativeLayout cameraLayout;
    LinearLayout resultLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cameraLayout = (RelativeLayout) findViewById(R.id.cameraLayout);
        cameraLayout.setVisibility(RelativeLayout.VISIBLE);

        resultLayout = (LinearLayout) findViewById(R.id.resultLayout);
        resultLayout.setVisibility(RelativeLayout.GONE);

        b1 = (Button) findViewById(R.id.b1);
        b1.setOnClickListener(this);

        resultTextView = (TextView) findViewById(R.id.textView);
        resultTextView.setText("Please Scan a QR code");

        mydecoderview = (QRCodeReaderView) findViewById(R.id.qrdecoderview);
        mydecoderview.setOnQRCodeReadListener(this);

        // Use this function to enable/disable decoding
        mydecoderview.setQRDecodingEnabled(true);

        // Use this function to change the autofocus interval (default is 5 secs)
        mydecoderview.setAutofocusInterval(1000L);

        // Use this function to enable/disable Torch
        //mydecoderview.setTorchEnabled(true);

        // Use this function to set front camera preview
        //mydecoderview.setFrontCamera();

        // Use this function to set back camera preview
        mydecoderview.setBackCamera();
    }

    @Override
    protected void onResume(){
        super.onResume();
        mydecoderview.startCamera();
    }

    @Override
    protected void onPause(){
        super.onPause();
        mydecoderview.stopCamera();

    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        Toast.makeText(this,"Scanned : "+text,Toast.LENGTH_SHORT).show();
        resultTextView.setText(text);
        mydecoderview.setQRDecodingEnabled(false);
        cameraLayout.setVisibility(RelativeLayout.GONE);
        resultLayout.setVisibility(RelativeLayout.VISIBLE);
        mydecoderview.stopCamera();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.b1:
                mydecoderview.startCamera();
                mydecoderview.setQRDecodingEnabled(true);
                cameraLayout.setVisibility(RelativeLayout.VISIBLE);
                resultLayout.setVisibility(RelativeLayout.GONE);
                break;
        }
    }

}
