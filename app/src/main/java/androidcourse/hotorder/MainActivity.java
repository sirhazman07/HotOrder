package androidcourse.hotorder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {
    final Context context = this;
    private ImageButton callbtn;
    //TODO: not sure what this line below is doing to the app
    //TODO: time out after 2 secs and initial welcome animations

    public final static String EXTRA_MESSAGE = "com.mycompany.hotorder.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO: Click on Products then proceed to next List activity.. yai
        //Button for Products List
        ImageButton productsbtn = (ImageButton) findViewById(R.id.imageButtonProducts);
        //Products Button Listener
        productsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getBaseContext(), ChilliListActivity.class);
                startActivity(p);
            }
        });

        //TODO: Make call on contact button and all call related actions, permissions, call state handling - Done
        // Button for Call
        ImageButton callbtn = (ImageButton) findViewById(R.id.imageButtonCall);
        // Phone State Listener
        PhoneStateListener phoneListener = new PhoneStateListener();
        TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);

        // Call button listener
        callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phonenumber = "0415852544";
                Intent makeCall = new Intent(Intent.ACTION_DIAL);
                makeCall.setData(Uri.parse("tel:" + phonenumber));
                //IMPORTANT:Check for device permissions before making the call
                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.WRITE_CALENDAR);
                if (permissionCheck == 0) {
                    startActivity(makeCall);
                }if (permissionCheck == 1){
                    Toast.makeText(MainActivity.this, "App does not support/have permission to make calls", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //Monitor phone call activities (e.g. Ringing, is Off-hook, Idle)
    private class PhoneCallListener extends PhoneStateListener {
        private boolean isPhoneCalling = false;
        String LOG_TAG = "LOGGING 123";

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            if (TelephonyManager.CALL_STATE_RINGING == state) {
                //Phone ringing
                Log.i(LOG_TAG, "RINGING, number: " + incomingNumber);
            }
            if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
                // active
                Log.i(LOG_TAG, "OFFHOOK");
                isPhoneCalling = true;
            }
            if (TelephonyManager.CALL_STATE_IDLE == state) {
                // Run when class initial and phone call ended,
                // Need detect flag from CALL_STATE_OFFHOOK
                Log.i(LOG_TAG, "IDLE");
                if (isPhoneCalling) {
                    Log.i(LOG_TAG, "restart app");

                    //restart the app
                    Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(
                            getBaseContext().getPackageName());
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);

                    isPhoneCalling = false;

                }
            }
        }
    }
}



