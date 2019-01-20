package gr.uom.android.examsreading;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FetchDataFromWebService  {
    public static final String ARRAY_NAME = "ingredients";
    public static final String NAME = "text";
    public static final String RANK_NAME = "rank";
    public static final String ID_NAME="id";

    public static final String TAG = "aTAG";
    private ArrayList<Foods> foods;

    public FetchDataFromWebService(){
        foods = new ArrayList<>();
    }

    public boolean parse(String jsonData){
        try {
            JSONObject object1 = new JSONObject(jsonData);
            JSONObject object = new JSONObject(object1.getString("product"));
            JSONArray jArray = object.getJSONArray(ARRAY_NAME);

            for(int i = 0; i<jArray.length(); i++){
                Log.d(TAG, "parse: "+jArray.get(i));
                JSONObject any= (JSONObject) jArray.get(i);
                String name = any.getString(NAME);
                //int rank = any.getInt(RANK_NAME);
                String id = any.getString(ID_NAME);
                Log.d(TAG, "parse: "+id);
                Foods f = new Foods(name,2,id);
                addFood(f);
            }
        } catch (JSONException e) {
            Log.d(TAG, "parse: SOMETHING WENT WRONG");
            e.printStackTrace();

        }


        return true;
    }
    public void addFood(Foods aFood){
        foods.add(aFood);
    }
    public ArrayList<Foods> getFoods(){
        return foods;
    }
}
