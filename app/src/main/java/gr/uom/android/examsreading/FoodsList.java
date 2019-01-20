package gr.uom.android.examsreading;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class FoodsList extends AppCompatActivity {
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods_list);
        DownloadData dl = new DownloadData();
        dl.execute("https://world.openfoodfacts.org/api/v0/product/737628064502.json");
        lv = findViewById(R.id.foodsList);

    }

    class DownloadData extends AsyncTask<String,Void,String> {
        String TAG = "HEYMAMBO";

        @Override
        protected void onPostExecute(String jsonData) {
            super.onPostExecute(jsonData);


            FetchDataFromWebService fDFW = new FetchDataFromWebService();
            fDFW.parse(jsonData);
            ArrayList<Foods> foods = fDFW.getFoods();
            for(Foods food: foods){
                Log.d(TAG, "onPostExecute: "+ food.toString());
            }
            FoodsAdapter fA = new FoodsAdapter(FoodsList.this,foods);
            lv.setAdapter(fA);

        }

        @Override
        protected String doInBackground(String... strings) {

            String postData = downloadJson(strings[0]);

            return postData;
        }
        private String downloadJson(String urlPath){
            StringBuilder sb = new StringBuilder();
            try {
                URL url = new URL(urlPath);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = bf.readLine();
                while (true){
                    Log.d(TAG, "downloadJson: "+line);
                    sb.append(line);
                    line = bf.readLine();
                    if(line == null){
                        break;
                    }
                }
                Log.d(TAG, "downloadJson: "+sb.toString());
            } catch (MalformedURLException e) {
                Log.d(TAG, "downloadJson: "+e.toString());
                e.printStackTrace();
            } catch (IOException e) {
                Log.d(TAG, "downloadJson: "+e.toString());
                e.printStackTrace();
            }


            return sb.toString();
        }
    }
}
