package fr.prawse.mariokartbis;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    // Eléments de l'application
    TextInputLayout inputNbLap, inputNbCheckpoint;
    TextView textBestLap, textCurrentLapTime, textSectorsTimes, textCntLap, textCntCheckpoint;
    Button buttonStart, buttonCheckpoint;
    FloatingActionButton buttonGraph;

    // Compte des tours et checkpoints
    int nbLap, nbCheckpoint, currentLap, currentCheckpoint;

    // Chronomètres
    Runnable runnable;
    int minuteSector = 0, secondeSector = 0, millisecondeSector = 0,
            minuteLap = 0, secondeLap = 0, millisecondeLap = 0,
            bestMinute = 10000, bestSeconde = 10000, bestMilliseconde = 10000;
    boolean timerEstTermine = false;

    // Sauvegarde des temps ; exemple : [[TempsSecteur1, TempsSecteur2, ..., TempsDuTour]]
    List<List<Integer>> listOfListsOfTimes = new ArrayList<>();
    List<Integer> tempList = new ArrayList<>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputNbLap = findViewById(R.id.inputlayout1);
        inputNbCheckpoint = findViewById(R.id.inputlayout2);

        textBestLap = findViewById(R.id.bestTime);
        textCurrentLapTime = findViewById(R.id.currentLap);
        textSectorsTimes = findViewById(R.id.sectorsTimes);
        textCntLap = findViewById(R.id.cntLap);
        textCntCheckpoint = findViewById(R.id.cntCheckpoint);

        buttonStart = findViewById(R.id.button1);
        buttonCheckpoint = findViewById(R.id.button2);
        buttonCheckpoint.setEnabled(false);

        buttonGraph = findViewById(R.id.floatingButton);
        buttonGraph.setEnabled(false);

        // Mise à jour du chronomètre
        runnable = new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                millisecondeSector += 1;
                millisecondeLap += 1;

                // Si une seconde est passée
                if (millisecondeSector == 100) {
                    secondeSector += 1;
                    millisecondeSector = 0;
                    secondeLap += 1;
                    millisecondeLap = 0;
                }

                // Si une minute est passée
                if (secondeSector == 60) {
                    minuteSector += 1;
                    secondeSector = 0;
                    minuteLap += 1;
                    secondeLap = 0;
                }

                // Arrêt du timer
                if (!timerEstTermine) {
                    new Handler().postDelayed(runnable, 10);
                    textCurrentLapTime.setText("Tour en cours : " + minuteLap + ":" + secondeLap + "," + millisecondeLap);
                } else {
                    textCurrentLapTime.setText("Tour en cours : Done");
                }
            }
        };
    }

    @SuppressLint("SetTextI18n")
    public void startRace(View view) {
        // Récupération des informations des Input
        nbLap = Integer.parseInt(String.valueOf(Objects.requireNonNull(inputNbLap.getEditText()).getText()));
        nbCheckpoint = Integer.parseInt(String.valueOf(Objects.requireNonNull(inputNbCheckpoint.getEditText()).getText()));

        // Lancement du timer
        new Handler().postDelayed(runnable, 10);

        // Désactiver le bouton
        buttonStart.setEnabled(false);
        buttonCheckpoint.setEnabled(true);
        inputNbLap.setEnabled(false);
        inputNbCheckpoint.setEnabled(false);

        // Affichage des compteurs de tours et checkpoints
        textCntLap.setText("Tour : " + currentLap + "/" + nbLap);
        textCntCheckpoint.setText("Checkpoint : " + currentCheckpoint + "/" + nbCheckpoint);
    }

    @SuppressLint("SetTextI18n")
    public void passCheckpoint(View view) {
        textSectorsTimes.setText("Secteur passé : " + minuteSector + ":" + secondeSector + "," + millisecondeSector);
        currentCheckpoint += 1;
        textCntCheckpoint.setText("Checkpoint : " + currentCheckpoint + "/" + nbCheckpoint);

        // Sauvegarde des temps
        tempList.add(millisecondeSector +
                secondeSector * 100 +
                minuteSector * 100 * 60);
        Log.d("TAGCHRONO", "tempList1: " + String.valueOf(tempList));

        // Si un tour est fini
        if (currentCheckpoint == nbCheckpoint) {
            currentLap += 1;
            currentCheckpoint = 0;
            textCntLap.setText("Tour : " + currentLap + "/" + nbLap);

            // Si le temps tour courant est mieux que le temps meilleur tour
            if (minuteLap < bestMinute
                    || minuteLap == bestMinute && secondeLap < bestSeconde
                    || minuteLap == bestMinute && secondeLap == bestSeconde && millisecondeLap < bestMilliseconde) {
                bestMinute = minuteLap;
                bestSeconde = secondeLap;
                bestMilliseconde = millisecondeLap;
                textBestLap.setText("Meilleur tour : " + minuteLap + ":" + secondeLap + "," + millisecondeLap);
            }

            // Sauvegarde des temps
            tempList.add(millisecondeLap +
                    secondeLap * 100 +
                    minuteLap * 100 * 60);
            Log.d("TAGCHRONO", "tempList2: " + String.valueOf(tempList));
            List<Integer> nouvelleListe = new ArrayList<>(tempList);
            listOfListsOfTimes.add(nouvelleListe);
            Log.d("TAGCHRONO", "total: " + String.valueOf(listOfListsOfTimes));
            tempList.clear();
            Log.d("TAGCHRONO", "tempList3: " + String.valueOf(tempList));

            // On remet les timers du tour à 0
            minuteLap = 0;
            secondeLap = 0;
            millisecondeLap = 0;
        }

        // Si la course est fini
        if (currentLap == nbLap) {
            timerEstTermine = true;

            // Désactiver le bouton
            buttonCheckpoint.setEnabled(false);
            buttonGraph.setEnabled(true);

            Log.d("TAGCHRONO", String.valueOf(listOfListsOfTimes));
        }

        // On remet les timers du secteur à 0
        minuteSector = 0;
        secondeSector = 0;
        millisecondeSector = 0;
    }

    public void printGraph(View view) {
        Intent myIntent = new Intent(MainActivity.this, GraphFragment.class);
        startActivity(myIntent);
    }
}