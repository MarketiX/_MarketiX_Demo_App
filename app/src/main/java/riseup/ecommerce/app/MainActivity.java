package riseup.ecommerce.app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Button;
import android.bluetooth.BluetoothAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button button1, sendButton, receiveButton;
    LinearLayout parent;
    float x, y;
    int[] param = new int[2];
    private static final int DISCOVER_DURATION = 300;

    // our request code (must be greater than zero)
    int REQUEST_BLU = 1;
    private static final int REQUEST_BLU2 = 5;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, ""+Environment.getExternalStorageDirectory(), Toast.LENGTH_SHORT).show();


        button1 = findViewById(R.id.button_1);
        sendButton = findViewById(R.id.button_2);
        receiveButton = findViewById(R.id.button_3);
        parent = findViewById(R.id.parent);

        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                REQUEST_BLU = 1;
                enableBlu();
            }
        });

        receiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                REQUEST_BLU = 5;
                enableBlu();
            }
        });

        parent.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.d("tag", "in onTouch...");
                checkTouch(event);

                return true;
            }
        });


        button1.getLocationOnScreen(param);


    }

    public void checkTouch(MotionEvent event) {
        x = event.getX();
        y = event.getY();
        button1.getLocationOnScreen(param);

        Toast.makeText(this, "" + param[0] + ", " + (+button1.getWidth() + param[0]) + ", " + x, Toast.LENGTH_SHORT).show();

        if (x >= param[0] && x <= (param[0] + button1.getWidth())) {
            if (y >= param[1] && y <= (param[1] + button1.getHeight())) {
                Log.d("tag", "this touch is in button area");
                // do what you want to do when touch/click comes in button area
                Toast.makeText(MainActivity.this, "**********", Toast.LENGTH_SHORT).show();
                MediaPlayer mPlayer = MediaPlayer.create(MainActivity.this, R.raw.b1);
                mPlayer.start();
            }
        }

/*
        Toast.makeText(MainActivity.this, "**********", Toast.LENGTH_SHORT).show();
        MediaPlayer mPlayer = MediaPlayer.create(MainActivity.this, R.raw.b1);
        mPlayer.start();*/
    }

    public void enableBlu() {
// enable device discovery - this will automatically enable Bluetooth
        Intent discoveryIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);

        discoveryIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,
                DISCOVER_DURATION);

        startActivityForResult(discoveryIntent, REQUEST_BLU);
    }


    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {

        if (resultCode == DISCOVER_DURATION
                && requestCode == REQUEST_BLU) {

            if (requestCode == 1) {
                // processing code goes here
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("*/*");
                String path = Environment.getExternalStorageDirectory() + "/test/" + "tt.txt";
                File f = new File(path);
                intent.setClipData(null);
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(f));
//...

                PackageManager pm = getPackageManager();
                List appsList = pm.queryIntentActivities(intent, 0);

                if (appsList.size() > 0) {
                    // proceed
                    String packageName = null;
                    String className = null;
                    boolean found = false;

                    for (ResolveInfo info : (List<ResolveInfo>) appsList) {
                        packageName = info.activityInfo.packageName;
                        if (packageName.equals("com.android.bluetooth")) {
                            className = info.activityInfo.name;
                            found = true;
                            break;// found
                        }
                    }
                    if (!found) {
                        Toast.makeText(MainActivity.this, "You have no bluetooth", Toast.LENGTH_SHORT).show();
                        // exit
                    } else {

                        intent.setClassName(packageName, className);
                        startActivity(intent);
                    }
                }

            }else{
                Toast.makeText(this, "wait for the sender data", Toast.LENGTH_SHORT).show();
            }


        } else { // cancelled or error
            Toast.makeText(this, "you have to open bluetooth for the offline process proceed",
                    Toast.LENGTH_SHORT).show();
        }

    }

}