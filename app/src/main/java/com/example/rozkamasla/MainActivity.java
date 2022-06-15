package com.example.rozkamasla;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RadioGroup rg;
    ListView[] ALLlISTS;
    Button btn;
    EditText item;
    ArrayList<String> gosht;
    ArrayList<String> sabzi;
    ArrayList<String> daal;
    ArrayList<String> chawal;
    MyDbHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg=findViewById(R.id.radioG);
        gosht=new ArrayList<String>();
        sabzi=new ArrayList<String>();
        daal=new ArrayList<String>();
        chawal=new ArrayList<String>();
        db= new MyDbHandler(MainActivity.this);
        gosht= (ArrayList<String>) db.getAllList("GOSHT");
        if(gosht.isEmpty()) {
            Log.d("goshtlist", "emptylist");
        }else{
            for(int i=0;i<gosht.size();i++) {
                Log.d("gosht",gosht.get(i));
            }

        }
        sabzi= (ArrayList<String>) db.getAllList("SABZI");
        daal= (ArrayList<String>) db.getAllList("DAAL");
        chawal= (ArrayList<String>) db.getAllList("CHAWAL");


        item=findViewById(R.id.item);
        btn=findViewById(R.id.additem);

        ALLlISTS=new ListView[4];
        ALLlISTS[0]=findViewById(R.id.SabziList);
        ALLlISTS[1]=findViewById(R.id.DaalList);
        ALLlISTS[2]=findViewById(R.id.GoshtList);
        ALLlISTS[3]=findViewById(R.id.ChawalList);
        ArrayAdapter<String> a1= new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,gosht);
        ArrayAdapter<String> a2= new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,sabzi);
        ArrayAdapter<String> a3= new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,daal);
        ArrayAdapter<String> a4= new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,chawal);
        ALLlISTS[0].setAdapter(a2);
        ALLlISTS[1].setAdapter(a3);
        ALLlISTS[2].setAdapter(a1);
        ALLlISTS[3].setAdapter(a4);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId=rg.getCheckedRadioButtonId();

                if(radioId==R.id.sabzi)
                {

                    db.addSabzi(item.getText().toString());
                    sabzi.add(item.getText().toString());
                    Log.d("sabzi",item.getText().toString());
                    a2.notifyDataSetChanged();

                }else if(radioId==R.id.Daal)
                {
                    db.addDaal(item.getText().toString());
                    daal.add(item.getText().toString());
                    a3.notifyDataSetChanged();
                }else if(R.id.Gosht==radioId)
                {
                    db.addGosht(item.getText().toString());
                    gosht.add(item.getText().toString());
                    a1.notifyDataSetChanged();
                }else if(R.id.Chawal==radioId)
                {
                    db.addChawal(item.getText().toString());
                    chawal.add(item.getText().toString());
                    a4.notifyDataSetChanged();
                }
            }
        });


            ALLlISTS[0].setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Delete Recod")
                            .setMessage("Do you really want to deete this task!")
//                            .setIcon(R.drawable.ic_baseline_delete_forever_24)
                            .setCancelable(false)
                            .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String selectedFromList = (String) (ALLlISTS[0].getItemAtPosition(position));
                                    sabzi.remove(position);
                                    db.deleteRow(selectedFromList,"sabzi");
                                    a2.notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();

                }
            });
            ALLlISTS[1].setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Delete Recod")
                            .setMessage("Do you really want to delete this task!")
//                            .setIcon(R.drawable.ic_baseline_delete_forever_24)
                            .setCancelable(false)
                            .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String selectedFromList = (String) (ALLlISTS[1].getItemAtPosition(position));
                                    daal.remove(position);
                                    db.deleteRow(selectedFromList,"daal");
                                    a3.notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();

                }
            });ALLlISTS[2].setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Delete Recod")
                            .setMessage("Do you really want to delete this task!")
//                            .setIcon(R.drawable.ic_baseline_delete_forever_24)
                            .setCancelable(false)
                            .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String selectedFromList = (String) (ALLlISTS[2].getItemAtPosition(position));
                                    gosht.remove(position);
                                    db.deleteRow(selectedFromList,"gosht");
                                    a1.notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();

                }
            });ALLlISTS[3].setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Delete Recod")
                            .setMessage("Do you really want to delete this task!")
//                            .setIcon(R.drawable.ic_baseline_delete_forever_24)
                            .setCancelable(false)
                            .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String selectedFromList = (String) (ALLlISTS[3].getItemAtPosition(position));
                                    chawal.remove(position);
                                    db.deleteRow(selectedFromList,"chawal");
                                    a4.notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();

                }
            });




    }
}