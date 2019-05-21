package univpm.valentini.mybarapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import app.util.All_resources;
import app.util.Item;
import app.util.ItemNotFoundException;

public class Item_Review extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item__review);
        Intent intent = getIntent();
        int category_num = intent.getBundleExtra("Data").getInt("Category");
        int item_num = intent.getBundleExtra("Data").getInt("Item");
        ImageView image = findViewById(R.id.ItemPicview);
        Item item = null;
        try{
            item = All_resources.getCategoryByID(category_num).getItemByID(item_num);
        }
        catch(ItemNotFoundException e){
            e.printStackTrace();
        }
        TextView item_name = findViewById(R.id.ItemNameview);
        TextView item_desc = findViewById(R.id.ItemDescriptionView);
        item_name.setText(item.getName());
        item_desc.setText(item.getDescritpion());
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        while (!isNetworkAvailable()){
            Toast.makeText(this, "You need an internet connection to use this app", Toast.LENGTH_SHORT).show();
            android.os.SystemClock.sleep(1000);
        }
        try{
            URL url = new URL(item.getImage());
            Bitmap itempic = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            image.setImageBitmap(itempic);
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
