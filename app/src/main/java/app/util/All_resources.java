package app.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class All_resources {
    private static ArrayList<Category> Categories = new ArrayList<>();
    public static void addCategory(Category cat){
        Categories.add(cat);
    }
    public static void addItem(Item itm, String[] in_cats){ ;
        Category[] cats = new Category[in_cats.length];
        for (int i = 0; i < cats.length; i++){
            cats[i] = new Category(in_cats[i], "");
        }
        for (int i = 0; i < cats.length; i++) {
            for (int u = 0; i < Categories.size(); u++) {
                if (Categories.get(u).equals(cats[i])) {
                    Categories.get(u).addItem(itm);
                }
            }
        }
    }
    public static Category[] getResources(){
        Category[] array = new Category[Categories.size()];
        for (int i = 0; i < array.length; i++) array[i] = Categories.get(i);
        return array;
    }
    public static void parseJSON(JSONObject obj){
        Log.d("JSON DATA", obj.toString());
        JSONArray categories = null;
        try {
            categories = obj.getJSONArray("categories");
            for (int i = 0; i<categories.length(); i++){
                JSONObject temp = (JSONObject) categories.get(i);
                Log.d("JSON_TEST", temp.toString());
                Category temp2 = new Category(temp.getString("Name"), temp.getString("Image"));
                All_resources.addCategory(temp2);
            }
            Log.d("JSON_TEST_POST", All_resources.getResources()[0].toString());
            JSONArray items = obj.getJSONArray("Items");
            for (int i = 0; i<items.length(); i++){

            }
        }
        catch (JSONException e){
            Log.wtf("JSON_PARSE", "Could not find crucial resources in " + obj.toString());
        }
    }
}
