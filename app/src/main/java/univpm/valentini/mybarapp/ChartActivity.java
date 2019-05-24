package univpm.valentini.mybarapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import app.util.Chart;

public class ChartActivity extends AppCompatActivity {
    Button button = null;
    TextView text = null;
    ListView chart = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        button = findViewById(R.id.Purchase);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Chart.sendChart();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        text.setText("Total: €" + Chart.getTotalString());
        chart = findViewById(R.id.ChartView);
        ChartAdapter adapter = new ChartAdapter(getApplicationContext(), R.layout.layout_chart_list);
        chart.setAdapter(adapter);
    }

}
