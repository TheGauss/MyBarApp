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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import app.util.All_resources;
import app.util.Chart;
import app.util.Item;
import app.util.ItemNotFoundException;

public class Item_Review extends AppCompatActivity implements View.OnClickListener {
    int itemnumber = 1;
    Button addbutton = null, removebutton = null, tochartbutton = null;
    TextView item_number = null, item_price;
    Item item = null;
    final String price = "Price: €";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item__review);
        Intent intent = getIntent();
        int category_num = intent.getBundleExtra("Data").getInt("Category");
        int item_num = intent.getBundleExtra("Data").getInt("Item");
        ImageView image = findViewById(R.id.ItemPicview);
        try{
            item = All_resources.getCategoryByID(category_num).getItemByID(item_num);
        }
        catch(ItemNotFoundException e){
            e.printStackTrace();
        }
        TextView item_name = findViewById(R.id.ItemNameview);
        TextView item_desc = findViewById(R.id.ItemDescriptionView);
        item_number = findViewById(R.id.ItemNumberView);
        item_number.setText(""+itemnumber);
        item_price = findViewById(R.id.ItemPriceview);
        item_price.setText(price+item.priceToString(itemnumber));
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
        addbutton = findViewById(R.id.IncreaseButton);
        removebutton = findViewById(R.id.DecreaseButton);
        tochartbutton = findViewById(R.id.AddtoChartButton);
        addbutton.setOnClickListener(this);
        removebutton.setOnClickListener(this);
        tochartbutton.setOnClickListener(this);

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.IncreaseButton:
                if (itemnumber<10){
                    itemnumber++;
                    item_number.setText(""+itemnumber);
                    item_price.setText(price+item.priceToString(itemnumber));
                }
                else Toast.makeText(this, "You can't put more than 10 items of the same kind in the chart", Toast.LENGTH_SHORT).show();
                break;
            case R.id.DecreaseButton:
                if(itemnumber>1){
                    itemnumber--;
                    item_number.setText(""+itemnumber);
                    item_price.setText(price+item.priceToString(itemnumber));
                }
                else Toast.makeText(this, "You can't put 0 items in the chart", Toast.LENGTH_SHORT).show();
                break;
            case R.id.AddtoChartButton:
                Chart.add_item(item, itemnumber);
                Toast.makeText(this, "Item added to the chart", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
