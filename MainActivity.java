package com.example.realmdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    EditText edt1,edt2;
    Button btn1,btn2;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realm=Realm.getInstance(Realm.getDefaultConfiguration());


        edt1=findViewById(R.id.edt1);
        edt2=findViewById(R.id.edt2);

        btn1=findViewById(R.id.btn1);

        btn2=findViewById(R.id.btn2);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String name=edt1.getText().toString();
                String pass=edt2.getText().toString();

            realm.beginTransaction();

            Model model=realm.createObject(Model.class);

            model.setName(name);
            model.setPass(pass);

            Toast.makeText(MainActivity.this, "inserted", Toast.LENGTH_SHORT).show();


            realm.commitTransaction();

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(i);
            }
        });
    }
}
