package gr.uom.android.examsreading;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.MovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static String TAG = "PRODROMOS";
    private TextView textView;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        textView.setMovementMethod(new ScrollingMovementMethod() {
        });


    }

    public void add(View view) {
        Intent intent = new Intent(MainActivity.this,FoodsList.class);
        startActivity(intent);
        //setOnTextView(editText.getText().toString());
    }
    public void setOnTextView(String aText){
        if(textView.getText().equals("")){
            textView.setText(aText);
            return;
        }
        textView.setText(textView.getText()+"\n"+aText);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: in");
        outState.putString(TAG, textView.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreInstanceState: in");
        super.onRestoreInstanceState(savedInstanceState);
        String savedString = savedInstanceState.getString(TAG);
        textView.setText(savedString);
    }


}
