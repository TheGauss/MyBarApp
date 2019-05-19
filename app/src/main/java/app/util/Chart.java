package app.util;

import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Chart {
    static private Item[] items = new Item[0];
    static private int[] quantities = new int[0];

    static public void add_item(Item item){
        for (int i = 0; i < items.length; i++) if (items[i].equals(item)){
            quantities[i]++;
            return;
        }
        int[] temp2=new int[items.length+1];
        Item[] temp1=new Item[items.length+1];
        for (int i = 0; i < items.length; i++){
            temp1[i]=items[i];
            temp2[i]=quantities[i];
        }
        temp1[items.length]=item;
        temp2[items.length]=1;
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
    static public Item getItem(int a){
        Item returned = items[a];
        return returned;
    }
    static public void sendChart() throws Exception {
        HttpsURLConnection connect = null;
        try{
            URL url = new URL("https://mybarapp.altervista.org/orderresolver.php");
            Log.d("Connection", "Created URL");
            connect = (HttpsURLConnection) url.openConnection();
            Log.d("Connection", "Opened Connection");
            connect.setRequestMethod("POST");
            connect.setDoInput(false);
            connect.setDoOutput(true);
            Log.d("Connection", "Set request method");
            OutputStream out = new BufferedOutputStream(connect.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.write("a");
            writer.flush();
            writer.close();
            out.close();
        }
        catch (Exception e){
            throw new Exception();
        }
        finally{
            if(connect!=null) connect.disconnect();
        }
    }
}
