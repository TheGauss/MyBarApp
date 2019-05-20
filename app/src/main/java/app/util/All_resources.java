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
    public static void addItem(Item itm, Category[] cats){ ;
        for (int i = 0; i < cats.length; i++) {
            for (int u = 0; u < Categories.size(); u++) {
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
            JSONArray items = obj.getJSONArray("items");
            for (int i = 0; i<items.length(); i++){
                JSONObject temp = (JSONObject) items.get(i);
                Log.d("JSON_PARSE", "Truing to parse: " + temp.toString());
                categories = temp.getJSONArray("categories");
                Category[] cats = new Category[categories.length()];
                for (int u = 0; u<categories.length(); u++){
                    cats[u] = new Category(categories.getString(u), "");
                }
                String[] temp2 =temp.getString("Price").split("\\.");
                Item a = new Item (Integer.parseInt(temp2[0]+temp2[1]), temp.getString("Name"), temp.getString("Image"));
                addItem(a, cats);
                Log.d("JSON_PARSE", "Created item: " + a.toString());
            }
        }
        catch (JSONException e){
            e.printStackTrace();
            Log.wtf("JSON_PARSE", "Could not find crucial resources in " + obj.toString());
        }
    }
}
