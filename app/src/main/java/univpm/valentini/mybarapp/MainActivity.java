package univpm.valentini.mybarapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import app.util.All_resources;
import app.util.Chart;
import app.util.Item;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button, button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if (v.getId()==R.id.button2){
            Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
            Log.d("Intent", "Intent set");
            startActivity(intent);
            return;
        }
        Intent intent = new Intent(getApplicationContext(), Item_Review.class);
        Bundle bundle = new Bundle();
        bundle.putInt("Category", 0);
        bundle.putInt("Item", 0);
        intent.putExtra("Data", bundle);
        startActivity(intent);
    }
}
