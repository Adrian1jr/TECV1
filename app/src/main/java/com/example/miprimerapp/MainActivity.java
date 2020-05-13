package com.example.miprimerapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ArrayList<chavales> students;
    private ArrayList<String> myItems;
    private ListView lvitems;
    private ArrayAdapter<String> arrayAdapter;
    chavales chavalon1;

    Button scanBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanBtn = findViewById(R.id.scanBtn);
        scanBtn.setOnClickListener(this);

        myItems = new ArrayList<>();
        students = new ArrayList<>();
        lvitems=findViewById(R.id.lvitems);

        students.add(new chavales("ADRIÁN TAPIA HERRERA",17100297));
        students.add(new chavales("SERGIO ALBERTO VILLASANA ACOSTA",17100850));
        students.add(new chavales("JOHAN GARCIA VAZQUEZ",17100217));
        students.add(new chavales("MIGUEL ANGEL MENDEZ CRUZ",17100254));
        students.add(new chavales("LAURA LIZETH PALOMO CONTRERAS",16100248));
        students.add(new chavales("JUAN PATRICIO GARCIA MORENO",17100214));
        students.add(new chavales("JUAN ANGEL SALINAS RIOS",17100290));
        students.add(new chavales("CARLOS ADOLFO GARCIA CORTES",17100212));
        students.add(new chavales("MARCO ANTONIO FLORES GONZALEZ",17100209));
        students.add(new chavales("IRVING HUMBERTO JIMENEZ MORALES",16100220));
        students.add(new chavales("RODRIGO URIEL GUEVARA DE LEON",17100852));
        students.add(new chavales("JOAN OMAR MEJIA GUTIERREZ",16100233));
        students.add(new chavales("ANTONIO HERNANDEZ MORENO",17100235));
        students.add(new chavales("ANTONIO DE JESUS MORALES CALDERON",17100259));
        students.add(new chavales("LESLIE IRAIS MALTOS GONZALEZ",17100246));
        students.add(new chavales("RAUL CESAR MONTES ROSALES",17100258));
        students.add(new chavales("ANGEL GONZALEZ MORENO",17100223));
        students.add(new chavales("SERGIO TOGA CAMACHO",17100223));

    }

    @Override
    protected void onResume() {
        super.onResume();
        myItems.clear();
        for (chavales student :
        students) {
            myItems.add(student.toString());
        }
        arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,myItems);
        lvitems.setAdapter(arrayAdapter);
        // La actividad se ha vuelto visible (ahora se "reanuda").
    }

    @Override
    public void onClick(View v){
        scanCode();
    }
    private void scanCode(){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning Code");
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode,data);
        if(result != null){
            if(result.getContents() !=null){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(result.getContents()); //ocuparemos get content perros :V
                for (chavales student :
                        students) {
                    if (result.getContents().equals(Integer.toString(student.getNoControl()))) {
                        student.setPresente(true);
                    }
                }
                builder.setTitle("Dato Guardado");
                builder.setPositiveButton("Escanea otra vez", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                }
                }).setNegativeButton("Terminado", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            else{
                Toast.makeText(this, "No hay resultados", Toast.LENGTH_LONG).show();
            }
        }else{
            super.onActivityResult(requestCode,resultCode,data);
        }
    }
}
