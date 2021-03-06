package app.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Chart {
    static private Item[] items = new Item[0];
    static private int[] quantities = new int[0];

    static public void add_item(Item item, int copies){
        for (int i = 0; i < items.length; i++) if (items[i].equals(item)){
            quantities[i]+=copies;
            if (quantities[i]>10) quantities[i]=10;
            return;
        }
        if (copies >10) copies = 10;
        int[] temp2=new int[items.length+1];
        Item[] temp1=new Item[items.length+1];
        for (int i = 0; i < items.length; i++){
            temp1[i]=items[i];
            temp2[i]=quantities[i];
        }
        temp1[items.length]=item;
        temp2[items.length]=copies;
        items=temp1;
        quantities=temp2;
    }
    static public void remove_item(Item item) throws ItemNotFoundException {
        for (int i = 0; i < items.length; i++) if (items[i].equals(item)){
            if (quantities[i] > 1) {
                quantities[i]--;
            }
            else {
                int[] temp2=new int[items.length-1];
                Item[] temp1=new Item[items.length-1];
                int z =0;
                for (int u = 0; u < items.length; u++){
                    if (u!=i){
                        temp1[z]=items[u];
                        temp2[z]=quantities[u];
                        z++;
                    }
                }
                items=temp1;
                quantities=temp2;
            }
            return;
        }
        throw new ItemNotFoundException();
    }

    static public int getItemsNumber(){
        return items.length;
    }
    static public int getItemQuantity(int a){
        return quantities[a];
    }
    static public Item[] getItems(){return items;}
    static public Item getItem(int a){return items[a];}
    static public int getTotal(){
        int total = 0;
        for (int i = 0; i<items.length; i++) total+= total + (items[i].getPrice()*quantities[i]);
        return total;
    }
    static public String getTotalString(){
        int total = getTotal();
        if(total%100==0) return total/100+".00";
        return total/100 + "." + total%100;
    }
    static public void sendChart() throws Exception {
        HttpsURLConnection connect = null;
        try{
            URL url = new URL("https://mybarapp.altervista.org/orderresolver.php");
            Log.d("Connection", "Created URL");
            connect = (HttpsURLConnection) url.openConnection();
            Log.d("Connection", "Opened Connection");
            connect.setRequestMethod("POST");
            connect.setDoOutput(true);
            Log.d("Connection", "Set request method");
            OutputStream out = new BufferedOutputStream(connect.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.write(chartParser());
            writer.flush();
            writer.close();
            out.close();
            items = new Item[0];
            quantities = new int[0];
            Log.d("Connection", "Connection resolved successfully");
        }
        catch (Exception e){
            throw new Exception();
        }
        finally{
            if(connect!=null) connect.disconnect();
        }
    }
    public static String chartParser(){
        JSONArray name = new JSONArray();
        JSONArray quants = new JSONArray();
        for (int i = 0; i < items.length; i++){
            name.put(items[i].getName());
            quants.put(quantities[i]);
        }
        JSONObject a = new JSONObject();
        try {
            a.put("Names", name);
            a.put("Quantities", quants);
            a.put("Mail", "s1079707@studenti.univpm.it");
            Log.v("JSON", "Created  JSON: " + a.toString());
        }
        catch (JSONException e){
            Log.d("JSON", "Something went wrong");
        }
        return a.toString();
    }
}
