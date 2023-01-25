package fr.prawse.mariokartbis;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

public class GraphFragment extends AppCompatActivity {
    private GraphView mGraphView;
    private List<LineGraphSeries<DataPoint>> mGraphData;
    private Spinner mSpinner;
    private int nb_secteurs;
    private int nb_tours;
    GraphView graphView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        // on below line we are initializing our graph view.
        /*graphView = findViewById(R.id.idGraphView);

        // on below line we are adding data to our graph view.
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                // on below line we are adding
                // each point on our x and y axis.
                new DataPoint(0, 1),
                new DataPoint(1, 3),
                new DataPoint(2, 4),
                new DataPoint(3, 9),
                new DataPoint(4, 6),
                new DataPoint(5, 3),
                new DataPoint(6, 6),
                new DataPoint(7, 1),
                new DataPoint(8, 2)
        });

        // after adding data to our line graph series.
        // on below line we are setting
        // title for our graph view.
        graphView.setTitle("My Graph View");

        // on below line we are setting
        // text color to our graph view.
        graphView.setTitleColor(R.color.purple_200);

        // on below line we are setting
        // our title text size.
        graphView.setTitleTextSize(18);

        // on below line we are adding
        // data series to our graph view.
        graphView.addSeries(series);*/




        /*@SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button menuPrincipalBtnBtn = (Button) findViewById(R.id.menuPrincipalBtn);
        menuPrincipalBtnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO revenir à l'activité principale ...
            }
        });*/

        /*mGraphView = (GraphView) findViewById(R.id.idGraphView);
        GridLabelRenderer gridLabel = mGraphView.getGridLabelRenderer();
        gridLabel.setHorizontalLabelsVisible(true);
        mGraphData = generateGraphData();
        mSpinner = (Spinner) findViewById(R.id.spinnerGraph);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listTitle(nb_secteurs));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                showSelectedGraph(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Ne rien faire
            }
        });

    }


    private void showSelectedGraph(int position) {
        mGraphView.removeAllSeries();
        mGraphView.addSeries(mGraphData.get(position));
    }

    //génère la séquence des titres qui doivent aller dans le spinner suivant le nombre de secteurs
    public static String[] listTitle(int nb_secteurs) {
        List<String> list = new ArrayList<>();
        list.add("tours");
        for (int i = 1; i <= nb_secteurs; i++) {
            list.add("secteur " + i);
        }
        return list.toArray(new String[0]);
    }

    private List<LineGraphSeries<DataPoint>> generateGraphData() {
        // Génère ici les données des graphique
        // Retournez une liste de LineGraphSeries, où chaque élément représente les données d'un graphique différent.
        List<LineGraphSeries<DataPoint>> data = new ArrayList<>();
        nb_tours = 10;
        nb_secteurs = 2;

        // Générer les données du graphique des tours:
        //(exemple:)
        DataPoint[] points2 = new DataPoint[nb_tours];
        for (int i = 0; i < nb_tours; i++) {
            points2[i] = new DataPoint(i, Math.cos(i));
        }
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(points2);
        data.add(series2);

        // Générer les données des secteurs
        //(exemple:)
        for (int k = 1; k<=nb_tours;k++) {
            DataPoint[] points1 = new DataPoint[nb_tours];
            for (int i = 0; i < nb_tours; i++) {
                points1[i] = new DataPoint(i, Math.sin(i));
            }
            LineGraphSeries<DataPoint> series1 = new LineGraphSeries<>(points1);
            data.add(series1);
        }


        return data;*/
    }
}