package com.knstech.apnaopd;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.knstech.apnaopd.Patient.LoginActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class SplashActivity extends Activity implements Animation.AnimationListener{

    private CircleImageView img;
    private static final int PERMISSION_CALLBACK_CONSTANT = 100;
    private static final int REQUEST_PERMISSION_CONSTANT = 101;
    String[] permissionRequired = new String[]{
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET
    };

    private SharedPreferences permissionStatus;
    private boolean sentToSettings = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        permissionStatus = getSharedPreferences("permissionStatus",MODE_PRIVATE);
        if(ActivityCompat.checkSelfPermission(SplashActivity.this,permissionRequired[0])!= PackageManager.PERMISSION_GRANTED ||
              ActivityCompat.checkSelfPermission(SplashActivity.this,permissionRequired[1])!= PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(SplashActivity.this,permissionRequired[2])!= PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(SplashActivity.this,permissionRequired[3])!= PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(SplashActivity.this,permissionRequired[4])!= PackageManager.PERMISSION_GRANTED
                ){


            if(ActivityCompat.shouldShowRequestPermissionRationale(SplashActivity.this,this.permissionRequired[0])||
            ActivityCompat.shouldShowRequestPermissionRationale(SplashActivity.this,this.permissionRequired[1])||
            ActivityCompat.shouldShowRequestPermissionRationale(SplashActivity.this,this.permissionRequired[2])||
            ActivityCompat.shouldShowRequestPermissionRationale(SplashActivity.this,this.permissionRequired[3])||
            ActivityCompat.shouldShowRequestPermissionRationale(SplashActivity.this,this.permissionRequired[4])
            ){
                AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
                builder.setTitle("Need Multiple Permission");
                builder.setTitle("This app needs Storage, Internet and wifi permissions ");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        ActivityCompat.requestPermissions(SplashActivity.this,permissionRequired,PERMISSION_CALLBACK_CONSTANT);
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Toast.makeText(SplashActivity.this, "Application won't run properly!!! you must grant permission", Toast.LENGTH_LONG).show();
                    }
                });
                builder.show();
            }

            else if(permissionStatus.getBoolean(permissionRequired[0],false)){
                AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
                builder.setTitle("Need Multiple Permission");
                builder.setTitle("This app needs Storage, Internet and wifi permissions ");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                        sentToSettings = true;
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri= Uri.fromParts("package",getPackageName(),null);
                        intent.setData(uri);
                        startActivityForResult(intent,REQUEST_PERMISSION_CONSTANT);

                        }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                           }
                });
                builder.show();

            }
            else{
                ActivityCompat.requestPermissions(SplashActivity.this,permissionRequired,PERMISSION_CALLBACK_CONSTANT);

            }

            SharedPreferences.Editor editor = permissionStatus.edit();
            editor.putBoolean(permissionRequired[0],true);
            editor.commit();
        }

        else{

            // you already have permissions
            proceedAfterPermission();
        }


    }

    private void proceedAfterPermission() {


        if(Build.VERSION.SDK_INT < 16){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        }
        else{
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }

        img = (CircleImageView) findViewById(R.id.img_splash);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.myanimation);
        animation.setAnimationListener(this);
        img.setAnimation(animation);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

        startActivity(new Intent(SplashActivity.this, WelcomeActivity.class));
        this.finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == PERMISSION_CALLBACK_CONSTANT) {
            // check if all permissions are granted or not
            boolean allGranted = false;

            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    allGranted = false;
                } else {
                    allGranted = false;
                    break;
                }
            }

            if (allGranted) {
                proceedAfterPermission();
            } else if (ActivityCompat.shouldShowRequestPermissionRationale(SplashActivity.this, this.permissionRequired[0]) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(SplashActivity.this, this.permissionRequired[1]) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(SplashActivity.this, this.permissionRequired[2]) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(SplashActivity.this, this.permissionRequired[3]) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(SplashActivity.this, this.permissionRequired[4])
                    ) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
                builder.setTitle("Permission Required");
                builder.setTitle("This app needs Storage, Internet and wifi permissions ");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        ActivityCompat.requestPermissions(SplashActivity.this, permissionRequired, PERMISSION_CALLBACK_CONSTANT);
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Toast.makeText(SplashActivity.this, "Application won't run properly!!! you must grant permission", Toast.LENGTH_LONG).show();
                    }
                });
                builder.show();
            } else if (permissionStatus.getBoolean(permissionRequired[0], false)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
                builder.setTitle("Need Multiple Permission");
                builder.setTitle("This app needs Storage, Internet and wifi permissions ");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                        sentToSettings = true;
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivityForResult(intent, REQUEST_PERMISSION_CONSTANT);

                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();

            }
            else{
                Toast.makeText(this, "Unable to get Permissions", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_PERMISSION_CONSTANT){
            if(ActivityCompat.checkSelfPermission(SplashActivity.this,permissionRequired[0]) == PackageManager.PERMISSION_GRANTED){
                proceedAfterPermission();
            }
        }
    }
}
