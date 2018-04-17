package sg.edu.rp.c346.pushuppower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class GraphOfPower extends AppCompatActivity {

    BarChart bcGraph;
    ArrayList<BarEntry> alBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_of_power);

        bcGraph = (BarChart)findViewById(R.id.barChart);

        alBar = new ArrayList<>();
        alBar.add(new BarEntry(8, 0));
        alBar.add(new BarEntry(10,1));
        alBar.add(new BarEntry(10,2));
        alBar.add(new BarEntry(10,3));
        alBar.add(new BarEntry(10,4));

        BarDataSet bardataset = new BarDataSet(alBar, "Cells");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("1st");
        labels.add("2nd");
        labels.add("3rd");
        labels.add("4th");
        labels.add("5th");

        BarData data = new BarData(labels, bardataset);
        bcGraph.setData(data);
    }
}
