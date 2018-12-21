package com.example.admin.recipenotepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity
{
    ListView cat_list;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cat_list = findViewById(R.id.cat_list);

        try
        {
            String abc = "";
            InputStream inputStream = getAssets().open("menu(old).txt");

            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            abc = new String(buffer);
            final String[] array = abc.split(",");

            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array);
            cat_list.setAdapter(adapter);

            cat_list.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    String data = array[position];

                    Intent intent = new Intent(MainActivity.this,SubCategory.class);
                    intent.putExtra("main",data);
                    intent.putExtra("position",position);
                    startActivity(intent);
                }
            });
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
