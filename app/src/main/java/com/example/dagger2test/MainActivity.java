package com.example.dagger2test;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    String TAG = "token in main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyApplication.mActivity= this;

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (!task.isSuccessful()) {
                    Log.d(TAG, task.getException() + "");
                    return;
                }
                String token = task.getResult();
                Log.d(TAG, token);

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.mActivity=null;
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyApplication.mActivity=null;

    }
}