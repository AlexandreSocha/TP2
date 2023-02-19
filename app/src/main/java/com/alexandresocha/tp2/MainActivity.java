package com.alexandresocha.tp2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextNumber;
    EditText editTextUrl;

    String Pseudo, Password;

    ActivityResultLauncher<Intent> activityLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == 1){
                Intent intent = result.getData();
                if(intent != null){
                    Pseudo = intent.getStringExtra("Pseudo");
                    Password = intent.getStringExtra("Password");

                    Context context = getApplicationContext();
                    CharSequence text = "Pseudo : " + Pseudo + " password : " + Password;
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

            }
            if(result.getResultCode() == -1){
                Intent intent = result.getData();
                if(intent != null){
                    String message = intent.getStringExtra("MessageAnnulation");

                    Context context = getApplicationContext();
                    CharSequence text = "Message : " + message;
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void call(View view){
        editTextNumber = findViewById(R.id.editTextCallNumber);
        String url = "tel:" + editTextNumber.getText().toString();
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));
        startActivity(intent);
    }

    public void openLogin(View v){
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.nouveaupackage.composantlogin", "com.nouveaupackage.composantlogin.MainActivity"));
        activityLauncher.launch(intent);
    }

    public void openBrowser(View view){
        editTextUrl = findViewById(R.id.editTextUrl);
        String url = editTextUrl.getText().toString();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}