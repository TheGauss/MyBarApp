package univpm.valentini.mybarapp;

import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import app.util.Chart;
import app.util.Item;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        /*HttpsURLConnection connect = null;
        try{
            URL url = new URL("https://mybarapp.altervista.org/mailtest.php");
            Log.d("Connection", "Created URL");
            connect = (HttpsURLConnection) url.openConnection();
            Log.d("Connection", "Opened Connection");
            connect.setRequestMethod("GET");
            Log.d("Connection", "Set request method");
            InputStream input = connect.getInputStream();
            Toast.makeText(this, "Communication done", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
        finally {
            if(connect!=null)connect.disconnect();
        }*/
        String[] a = {"caffe", "colazione"};
        Log.d("Test", "Crated string array");
        Item b = new Item(100, "Caffe macchiato", a);
        Log.d("Test", "Created Item");
        Chart.add_item(b);
        Toast.makeText(this, Chart.getItem(0).toString(), Toast.LENGTH_LONG).show();
        Snackbar.make(v, "Quantity of this item= " + Chart.getItemQuantity(0)+" This is the item no. = " + Chart.getItemsNumber(), Snackbar.LENGTH_LONG).show();

    }
}
