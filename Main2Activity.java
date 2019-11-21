package com.example.realmdatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class Main2Activity extends AppCompatActivity {


    ListView listView;
    Realm realm;
    ArrayList<Model> arrayList = new ArrayList<>();
    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        
        listView=findViewById(R.id.list);
        realm=Realm.getInstance(Realm.getDefaultConfiguration());


        final RealmResults<Model>realmResults=realm.where(Model.class).findAll();


        realm.beginTransaction();

        for(int i=0;i<realmResults.size();i++)
        {
            arrayList.add(realmResults.get(i));
        }

        MyAdapter adapter=new MyAdapter(getApplicationContext(),arrayList);
        listView.setAdapter(adapter);

        realm.commitTransaction();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id)
            {

                AlertDialog.Builder alert=new AlertDialog.Builder(Main2Activity.this);
                alert.setTitle("select operations");

                alert.setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                        realm.beginTransaction();
                        RealmResults<Model> updatedata = realm.where(Model.class).findAll();

                        AlertDialog.Builder custom = new AlertDialog.Builder(Main2Activity.this);



                        inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View view1 = inflater.inflate(R.layout.edit,null);

                        final EditText editData =  view1.findViewById(R.id.edt1);
                        final EditText editPass =  view1.findViewById(R.id.edt2);

                        editData.setText(updatedata.get(position).getName());
                        editPass.setText(updatedata.get(position).getPass());

                        custom.setView(view1);

                        custom.setPositiveButton("update", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                realm.beginTransaction();
                                RealmResults<Model> updatedata = realm.where(Model.class).findAll();
                                updatedata.get(position).setPass(editPass.getText().toString());
                                updatedata.get(position).setName(editData.getText().toString());
                                realm.commitTransaction();

                                Toast.makeText(Main2Activity.this, "updated", Toast.LENGTH_SHORT).show();

                                Intent i =new Intent(Main2Activity.this,Main2Activity.class);
                                startActivity(i);



                            }
                        });





                        realm.commitTransaction();
                        custom.show();







                    }
                });

                alert.setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        realm.beginTransaction();
                        RealmResults<Model> deletedata = realm.where(Model.class).findAll();
                        deletedata.deleteFromRealm(position);
                        realm.commitTransaction();

                        Intent i =new Intent(Main2Activity.this,Main2Activity.class);
                        startActivity(i);


                    }
                });


                AlertDialog alertDialog = alert.create();
                alertDialog.show();


                return true;
            }
        });



    }



}
