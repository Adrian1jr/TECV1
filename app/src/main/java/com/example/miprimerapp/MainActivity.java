package com.example.miprimerapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

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

        myItems=new ArrayList<>();
        lvitems=findViewById(R.id.lvitems);

         chavalon1 = new chavales("Adrian Tapia Herrera",17100297);

    }

    @Override
    protected void onResume() {
        super.onResume();
        myItems.add(chavalon1.toString());
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
                    if (student.getNoControl() == Integer.parseInt(result.getContents())) {
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
