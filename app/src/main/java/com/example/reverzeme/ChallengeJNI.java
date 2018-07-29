package com.example.reverzeme;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//import com.example.reverzeme.R;
import com.marcohc.toasteroid.Toasteroid;


public class ChallengeJNI extends AppCompatActivity {
    EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_jni);

        Button clickButton = (Button) findViewById(R.id.buttonGo);
        clickButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Boolean isEmulator = checkIfDeviceIsEmulator();

                if(isEmulator==true)
                {
                    Toasteroid.show(ChallengeJNI.this, "This Device is not supported", Toasteroid.STYLES.ERROR, Toasteroid.LENGTH_SHORT);
                }
                else
                {
                    String pass = stringFromJNI();
                    Toasteroid.show(ChallengeJNI.this, "Verifying Password ...", Toasteroid.STYLES.WARNING, Toasteroid.LENGTH_SHORT);
                    editTextPassword  = (EditText)findViewById(R.id.editTextPassword);
                    if(pass.equals(editTextPassword.getText().toString()))
                    {
                        Toasteroid.show(ChallengeJNI.this, "Your Flag is: "+stringOtherHalfKey("s1r"), Toasteroid.STYLES.ERROR, Toasteroid.LENGTH_LONG);
                    }else
                    {
                        Toasteroid.show(ChallengeJNI.this, "Wrong Password", Toasteroid.STYLES.ERROR, Toasteroid.LENGTH_SHORT);
                    }


                }
            }
        });

    }

    public native String  stringFromJNI();
    public native String  stringOtherHalfKey(String s1r);


    static {
        try {
            System.loadLibrary("hello-jni");
        } catch (UnsatisfiedLinkError ule) {
            Log.e("HelloC", "WARNING: Could not load native library: " + ule.getMessage());
        }
    }



    private Boolean checkIfDeviceIsEmulator() {

        if(Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk".equals(Build.PRODUCT))
        {
            return true;
        }
        return false;
    }
}
