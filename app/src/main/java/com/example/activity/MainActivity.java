package com.example.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;


/**
 * ----------------Sending Image between activity--------------
 * Convert it to a Byte array before you add it to the intent, send it out, and decode.
 * <p>
 * //Convert to byte array
 * ByteArrayOutputStream stream = new ByteArrayOutputStream();
 * bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
 * byte[] byteArray = stream.toByteArray();
 * <p>
 * Intent in1 = new Intent(this, Activity2.class);
 * in1.putExtra("image",byteArray);
 *
 * //Then in Activity 2:
 * <p>
 * byte[] byteArray = getIntent().getByteArrayExtra("image");
 * Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
 * <p>
 * <p>
 * <p>
 * --------------Send image using file System------------------------
 * <p>
 * In your first activity, you should save the Bitmap to disk then load it up in the next activity. Make sure to recycle your bitmap in the first activity to prime it for garbage collection:
 * <p>
 * Activity 1:
 * <p>
 * try {
 * //Write file
 * String filename = "bitmap.png";
 * FileOutputStream stream = this.openFileOutput(filename, Context.MODE_PRIVATE);
 * bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
 * <p>
 * //Cleanup
 * stream.close();
 * bmp.recycle();
 * <p>
 * //Pop intent
 * Intent in1 = new Intent(this, Activity2.class);
 * in1.putExtra("image", filename);
 * startActivity(in1);
 * } catch (Exception e) {
 * e.printStackTrace();
 * }
 * In Activity 2, load up the bitmap:
 * <p>
 * Bitmap bmp = null;
 * String filename = getIntent().getStringExtra("image");
 * try {
 * FileInputStream is = this.openFileInput(filename);
 * bmp = BitmapFactory.decodeStream(is);
 * is.close();
 * } catch (Exception e) {
 * e.printStackTrace();
 * }
 * ---------------send bitmap using intent----------------
 * -->Bitmap implements Parcelable, so you could always pass it in the intent:
 *
 * Intent intent = new Intent(this, NewActivity.class);
 * intent.putExtra("BitmapImage", bitmap);
 *
 * -->and retrieve it on the other end:
 *
 * Intent intent = getIntent();
 * Bitmap bitmap = (Bitmap) intent.getParcelableExtra("BitmapImage");
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "LifeCycleALL";
    private final int REQUEST_CODE_SECOND_ACTIVITY = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: " + "MainActivity");
        setContentView(R.layout.activity_main);
        Button btnClick = findViewById(R.id.btnClick);
        btnClick.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);

//            -----------------Sending object using Serializable---------------------
//                  User class should implement Serializable
//                   User user=new User("akash","8349028");
//                  intent.putExtra("user",user);


        //------------------send object using parcelable--------------
         //   User class should implement parcelable..modify writeToParcel..modify constructor with Parcel parameter
//             User2 user=new User2("tom","8943208");
//             intent.putExtra("user",user);


            // startActivity(intent);

            //----get result from another activity----
            //----result returned in onActivityResult function--
            startActivityForResult(intent, REQUEST_CODE_SECOND_ACTIVITY);

        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: " + "MainActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: " + "MainActivity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: " + "MainActivity");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: " + "MainActivity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: " + "MainActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: " + "MainActivity");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG, "onBackPressed: " + "MainActivity");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: " + "MainActivity");

        if (data != null)
            Log.d(TAG, "onActivityResult: data" + data);
        if (resultCode == RESULT_OK) {
            String name = data.getStringExtra("name");
            String number = data.getStringExtra("number");

            Toast.makeText(this, "From Second Activity: " + name + "," + number, Toast.LENGTH_LONG).show();
        }


    }

}

class User implements Serializable {
    private String name;
    private String number;

    User(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
}

class User2 implements Parcelable
{

    private String name ;

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public User2(String name, String number) {
        this.name = name;
        this.number = number;
    }

    private String number;


    protected User2(Parcel in) {
        this.name=in.readString();
        this.number=in.readString();
    }

    public static final Creator<User2> CREATOR = new Creator<User2>() {
        @Override
        public User2 createFromParcel(Parcel in) {
            return new User2(in);
        }

        @Override
        public User2[] newArray(int size) {
            return new User2[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.number);

    }
}