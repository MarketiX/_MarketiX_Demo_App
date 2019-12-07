package riseup.ecommerce.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.LogManager;

public class HomeActivity extends AppCompatActivity {

    ViewFlipper imgBanner;
    LinearLayout parent;
    Switch soundSwitch;
    ImageButton menuButton;
    ProgressDialog progressDialog;
    float x, y;
    boolean sender = false;
    private static final int DISCOVER_DURATION = 300;
    int REQUEST_BLU = 1;

    int[] param1 = new int[2], param2 = new int[2], param3 = new int[2], param4 = new int[2], param5 = new int[2], param6 = new int[2];
    CardView laptopCard, lcdCard, blindStickCard, smartPhonesCard, bulbsCard, othersCard;
    MediaPlayer mPlayer1;
    MediaPlayer mPlayer2;
    MediaPlayer mPlayer3;
    MediaPlayer mPlayer4;
    MediaPlayer mPlayer5;
    MediaPlayer mPlayer6;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        laptopCard = findViewById(R.id.laptop_card);
        lcdCard = findViewById(R.id.lcd_card);
        blindStickCard = findViewById(R.id.blind_stick_card);
        smartPhonesCard = findViewById(R.id.smart_phones_card);
        bulbsCard = findViewById(R.id.bulb_card);
        othersCard = findViewById(R.id.others_card);
        soundSwitch = findViewById(R.id.sound_switch);
        menuButton = findViewById(R.id.menu_button);


        laptopCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soundSwitch.isChecked()) {
                    mPlayer1.start();
                    /*mPlayer2.stop();
                    mPlayer3.stop();
                    mPlayer4.stop();
                    mPlayer5.stop();
                    mPlayer6.stop();*/
                }
            }
        });
        lcdCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soundSwitch.isChecked()) {
                    // mPlayer1.stop();
                    mPlayer2.start();
                    /*mPlayer3.stop();
                    mPlayer4.stop();
                    mPlayer5.stop();
                    mPlayer6.stop();*/
                }
            }
        });
        blindStickCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soundSwitch.isChecked()) {
                    /*mPlayer1.stop();
                    mPlayer2.stop();
                    */
                    mPlayer3.start();
                    /*mPlayer4.stop();
                    mPlayer5.stop();
                    mPlayer6.stop();
                */
                }
            }
        });
        smartPhonesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soundSwitch.isChecked()) {
                    /*mPlayer1.stop();
                    mPlayer2.stop();
                    mPlayer3.stop();
                    */
                    mPlayer4.start();
                    /*mPlayer5.stop();
                    mPlayer6.stop();
                */
                }
            }
        });
        bulbsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soundSwitch.isChecked()) {
                    /*mPlayer1.stop();
                    mPlayer2.stop();
                    mPlayer3.stop();
                    mPlayer4.stop();
                    */
                    mPlayer5.start();
                    //mPlayer6.stop();
                }
            }
        });
        othersCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soundSwitch.isChecked()) {
                    /*mPlayer1.stop();
                    mPlayer2.stop();
                    mPlayer3.stop();
                    mPlayer4.stop();
                    mPlayer5.stop();*/
                    mPlayer6.start();
                }
            }
        });

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] items = new String[]{"ارسال المنتجات", "استقبال المنتجات"};
                final Integer[] icons = new Integer[]{R.drawable.send_icon, R.drawable.receive_icon};
                ListAdapter adapter = new ArrayAdapterWithIcon(HomeActivity.this, items, icons);

                new AlertDialog.Builder(HomeActivity.this).setTitle("اختر")
                        .setAdapter(adapter, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                enableBlu();

                                switch (item) {
                                    case 0:
                                        sender = true;
                                        break;
                                    case 1:
                                        sender = false;
                                        break;
                                }

                            }
                        }).show();
            }
        });


/*
        laptopCard.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                checkTouch(event);
                return true;
            }
        });
        lcdCard.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                checkTouch(event);
                return true;
            }
        });
        blindStickCard.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                checkTouch(event);
                return true;
            }
        });
        smartPhonesCard.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                checkTouch(event);
                return true;
            }
        });
        bulbsCard.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                checkTouch(event);
                return true;
            }
        });
        othersCard.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                checkTouch(event);
                return true;
            }
        });
*/
        mPlayer1 = MediaPlayer.create(HomeActivity.this, R.raw.b1);
        mPlayer2 = MediaPlayer.create(HomeActivity.this, R.raw.b2);
        mPlayer3 = MediaPlayer.create(HomeActivity.this, R.raw.b3);
        mPlayer4 = MediaPlayer.create(HomeActivity.this, R.raw.b1);
        mPlayer5 = MediaPlayer.create(HomeActivity.this, R.raw.b2);
        mPlayer6 = MediaPlayer.create(HomeActivity.this, R.raw.b3);

    }


    public void checkTouch(MotionEvent event) {
        x = event.getX();
        y = event.getY();
        laptopCard.getLocationOnScreen(param1);
        lcdCard.getLocationOnScreen(param2);
        blindStickCard.getLocationOnScreen(param3);
        smartPhonesCard.getLocationOnScreen(param4);
        othersCard.getLocationOnScreen(param5);
        bulbsCard.getLocationOnScreen(param6);

        //Toast.makeText(this, "" + param[0] + ", " + (+button1.getWidth() + param[0]) + ", " + x, Toast.LENGTH_SHORT).show();


        if (x >= param1[0] && x <= (param1[0] + laptopCard.getWidth())) {
            if (y >= param1[1] && y <= (param1[1] + laptopCard.getHeight())) {
                Log.d("tag", "this touch is in button area");
                // do what you want to do when touch/click comes in button area
                //Toast.makeText(HomeActivity.this, "**********", Toast.LENGTH_SHORT).show();
                mPlayer1.start();
                mPlayer2.stop();
                mPlayer3.stop();
                mPlayer4.stop();
                mPlayer5.stop();
                mPlayer6.stop();

            }
        } else if (x >= param2[0] && x <= (param2[0] + lcdCard.getWidth())) {
            if (y >= param2[1] && y <= (param2[1] + lcdCard.getHeight())) {
                Log.d("tag", "this touch is in button area");
                // do what you want to do when touch/click comes in button area
                //Toast.makeText(HomeActivity.this, "**********", Toast.LENGTH_SHORT).show();
                mPlayer1.stop();
                mPlayer2.start();
                mPlayer3.stop();
                mPlayer4.stop();
                mPlayer5.stop();
                mPlayer6.stop();

            }
        } else if (x >= param3[0] && x <= (param3[0] + blindStickCard.getWidth())) {
            if (y >= param3[1] && y <= (param3[1] + blindStickCard.getHeight())) {
                Log.d("tag", "this touch is in button area");
                // do what you want to do when touch/click comes in button area
                //Toast.makeText(HomeActivity.this, "**********", Toast.LENGTH_SHORT).show();
                mPlayer1.stop();
                mPlayer2.stop();
                mPlayer3.start();
                mPlayer4.stop();
                mPlayer5.stop();
                mPlayer6.stop();

            }
        } else if (x >= param4[0] && x <= (param4[0] + smartPhonesCard.getWidth())) {
            if (y >= param4[1] && y <= (param4[1] + smartPhonesCard.getHeight())) {
                Log.d("tag", "this touch is in button area");
                // do what you want to do when touch/click comes in button area
                //Toast.makeText(HomeActivity.this, "**********", Toast.LENGTH_SHORT).show();
                mPlayer1.stop();
                mPlayer2.stop();
                mPlayer3.stop();
                mPlayer4.start();
                mPlayer5.stop();
                mPlayer6.stop();

            }
        } else if (x >= param5[0] && x <= (param5[0] + othersCard.getWidth())) {
            if (y >= param5[1] && y <= (param5[1] + othersCard.getHeight())) {
                Log.d("tag", "this touch is in button area");
                // do what you want to do when touch/click comes in button area
                //Toast.makeText(HomeActivity.this, "**********", Toast.LENGTH_SHORT).show();
                mPlayer1.stop();
                mPlayer2.stop();
                mPlayer3.stop();
                mPlayer4.stop();
                mPlayer5.start();
                mPlayer6.stop();

            }
        } else if (x >= param6[0] && x <= (param6[0] + bulbsCard.getWidth())) {
            if (y >= param6[1] && y <= (param6[1] + bulbsCard.getHeight())) {
                Log.d("tag", "this touch is in button area");
                // do what you want to do when touch/click comes in button area
                //Toast.makeText(HomeActivity.this, "**********", Toast.LENGTH_SHORT).show();
                mPlayer1.stop();
                mPlayer2.stop();
                mPlayer3.stop();
                mPlayer4.stop();
                mPlayer5.stop();
                mPlayer6.start();

            }
        }


    }

    public class ArrayAdapterWithIcon extends ArrayAdapter<String> {

        private List<Integer> images;

        public ArrayAdapterWithIcon(Context context, List<String> items, List<Integer> images) {
            super(context, android.R.layout.select_dialog_item, items);
            this.images = images;
        }

        public ArrayAdapterWithIcon(Context context, String[] items, Integer[] images) {
            super(context, android.R.layout.select_dialog_item, items);
            this.images = Arrays.asList(images);
        }

       /* public ArrayAdapterWithIcon(Context context, int items, int images) {
            super();
            //super(context, android.R.layout.select_dialog_item, context.getResources().getTextArray(items));

            final TypedArray imgs = context.getResources().obtainTypedArray(images);
            this.images = new ArrayList<Integer>() {{ for (int i = 0; i < imgs.length(); i++) {add(imgs.getResourceId(i, -1));} }};

            // recycle the array
            imgs.recycle();
        }*/

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            TextView textView = (TextView) view.findViewById(android.R.id.text1);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                textView.setCompoundDrawablesRelativeWithIntrinsicBounds(images.get(position), 0, 0, 0);
            } else {
                textView.setCompoundDrawablesWithIntrinsicBounds(images.get(position), 0, 0, 0);
            }
            textView.setCompoundDrawablePadding(
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, getContext().getResources().getDisplayMetrics()));
            return view;
        }

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

            if (sender) {
                //Toast.makeText(this, "****************", Toast.LENGTH_SHORT).show();

                @SuppressLint("HandlerLeak") final Handler handle = new Handler() {
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        progressDialog.incrementProgressBy(2);
                    }
                };

                progressDialog = new ProgressDialog(HomeActivity.this);
                progressDialog.setMax(100);
                progressDialog.setMessage("جار الارسال...");
                progressDialog.setTitle("احدث المنتجات");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.show();
                progressDialog.setCancelable(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (progressDialog.getProgress() <= progressDialog.getMax()) {
                                Thread.sleep(250);
//                                int x= (int)Math.random()*4;
//                                progressDialog.incrementProgressBy(x);
                                handle.sendMessage(handle.obtainMessage());
                                if (progressDialog.getProgress() >= progressDialog.getMax()) {
                                    progressDialog.dismiss();

                                    Toast.makeText(HomeActivity.this, "تم الارسال بنجاح", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                // processing code goes here
                /*Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);*/
                //intent.setType("*/*");
                /*String path = Environment.getExternalStorageDirectory() + "/test/" + "tt.txt";
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
                        Toast.makeText(HomeActivity.this, "You have no bluetooth", Toast.LENGTH_SHORT).show();
                        // exit
                    } else {

                        intent.setClassName(packageName, className);
                        startActivity(intent);
                    }
                }
*/
            } else {

                @SuppressLint("HandlerLeak") final Handler handle = new Handler() {
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        progressDialog.incrementProgressBy(2);
                    }
                };

                progressDialog = new ProgressDialog(HomeActivity.this);
                progressDialog.setMax(100);
                progressDialog.setMessage("جار الاستقبال...");
                progressDialog.setTitle("احدث المنتجات");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.show();
                progressDialog.setCancelable(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (progressDialog.getProgress() <= progressDialog.getMax()) {
                                Thread.sleep(300);
//                                int x= (int)Math.random()*4;
//                                progressDialog.incrementProgressBy(x);
                                handle.sendMessage(handle.obtainMessage());
                                if (progressDialog.getProgress() >= progressDialog.getMax()) {
                                    progressDialog.dismiss();

                                    Toast.makeText(HomeActivity.this, "تم الاستلام بنجاح", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();


            }


        } else { // cancelled or error
            Toast.makeText(this, "you have to open bluetooth for the offline process proceed",
                    Toast.LENGTH_SHORT).show();
        }

    }


        }
