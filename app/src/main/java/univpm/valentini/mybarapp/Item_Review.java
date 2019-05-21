package univpm.valentini.mybarapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import app.util.All_resources;
import app.util.Category;
import app.util.Item;
import app.util.ItemNotFoundException;

public class Item_Review extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item__review);
        Intent intent = getIntent();
        int category = intent.getBundleExtra("Data").getInt("Category");
        int item = intent.getBundleExtra("Data").getInt("Item");
        ImageView image = findViewById(R.id.Itempic);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        while (!isNetworkAvailable()){
            Toast.makeText(this, "You need an internet connection to use this app", Toast.LENGTH_SHORT).show();
            android.os.SystemClock.sleep(1000);
        }
        try{
            URL url = new URL(All_resources.getCategoryByID(category).getItemByID(item).getImage());
            Bitmap itempic = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            image.setImageBitmap(itempic);
        }
        catch(ItemNotFoundException e){
            e.printStackTrace();
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
