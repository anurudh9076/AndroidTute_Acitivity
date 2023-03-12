package com.example.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    private static final String TAG = "LifeCycleALL";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: "+"SecondActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent=getIntent();


        //--------getting object using serializable------
//        User user=(User) intent.getSerializableExtra("user");
//        Toast.makeText(this, "name: "+user.getName()+" ,Number: "+user.getNumber(), Toast.LENGTH_LONG).show();

//----------------receiving object with parcelable---------------------------------
//                User2 user=(User2) intent.getParcelableExtra("user");
//        Toast.makeText(this, "name: "+user.getName()+" ,Number: "+user.getNumber(), Toast.LENGTH_LONG).show();

    }
        @Override
        protected void onStart() {
            super.onStart();
            Log.d(TAG, "onStart: "+"SecondActivity");
        }

        @Override
        protected void onResume() {
            super.onResume();
            Log.d(TAG, "onResume: "+"SecondActivity");
        }

        @Override
        protected void onPause() {
            super.onPause();
            Log.d(TAG, "onPause: "+"SecondActivity");
        }

        @Override
        protected void onRestart() {
            super.onRestart();
            Log.d(TAG, "onRestart: "+"SecondActivity");
        }

        @Override
        protected void onStop() {
            super.onStop();
            Log.d(TAG, "onStop: "+"SecondActivity");
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            Log.d(TAG, "onDestroy: "+"SecondActivity");
        }

        @Override
        public void onBackPressed() {

            Log.d(TAG, "onBackPressed: "+"SecondActivity");
            EditText etName=findViewById(R.id.etName);
            EditText etNumber=findViewById(R.id.etNumber);
            String name=etName.getText().toString();
            String number=etNumber.getText().toString();

            Intent data =new Intent();
            data.putExtra("name",name);
            data.putExtra("number",number);
            setResult(RESULT_OK,data);
            super.onBackPressed();

        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            Log.d(TAG, "onActivityResult: "+"SecondActivity");
        }

}