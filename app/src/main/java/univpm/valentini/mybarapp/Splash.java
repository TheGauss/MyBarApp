package univpm.valentini.mybarapp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.StrictMode;
import android.speech.tts.UtteranceProgressListener;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import java.net.ConnectException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import app.util.All_resources;
import app.util.Category;
import app.util.Item;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class Splash extends Activity {

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.layout_splash);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        /* Create an Intent that will start the Menu-Activity. */
        Log.d("Splash", "Splash started");
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        downloadAssets();
        Intent mainIntent = new Intent(Splash.this, MainActivity.class);
        Splash.this.startActivity(mainIntent);
        Splash.this.finish();
    }
    public void downloadAssets(){
        HttpsURLConnection connect = null;
        while(!isNetworkAvailable()) {
            android.os.SystemClock.sleep(1000);
        }
        try{
            URL url = new URL("https://mybarapp.altervista.org/assetrequest.php");
            connect = (HttpsURLConnection) url.openConnection();
            connect.setRequestMethod("GET");
            BufferedInputStream in = new BufferedInputStream(connect.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            Log.d("Splash", "Download completed");
            StringBuilder total = new StringBuilder();
            for (String line; (line = reader.readLine()) != null; ) {
                total.append(line).append('\n');
            }
            All_resources.parseJSON(new JSONObject(total.toString()));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally{
            if (connect!=null)connect.disconnect();
        }
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
