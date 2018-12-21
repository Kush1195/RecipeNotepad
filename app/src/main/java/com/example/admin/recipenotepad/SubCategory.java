package com.example.admin.recipenotepad;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class SubCategory extends AppCompatActivity
{
    ListView Subcat_list;
    Bundle b;
    String mainCategory;
    int position;
    String[] array;
    ArrayList<String>strings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        Subcat_list = findViewById(R.id.Subcat_list);

        b = getIntent().getExtras();

        mainCategory = b.getString("main");
        position = b.getInt("position",0);

        readData(mainCategory,position);
    }

    private void readData(String main,int position)
    {
        String file = "Gujarati.txt";

        if (position > 0)
        {
            file = main.concat(".txt");
        }

        String readData = ReadFromfile(file,this);

        array = readData.split(",");

        strings = new ArrayList<>(Arrays.asList(array));
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array);
        Subcat_list.setAdapter(adapter);

            Subcat_list.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    String data = array[position];
                    Intent intent = new Intent(SubCategory.this,DetailDishes.class);
                    intent.putExtra("Submenu",data);
                    intent.putExtra("position",position);
                    intent.putStringArrayListExtra("Array",strings);
                    startActivity(intent);
                }
            });

    }
    public String ReadFromfile(String fileName, Context context)
    {
        StringBuilder returnString = new StringBuilder();
        InputStream fIn = null;
        InputStreamReader isr = null;
        BufferedReader input = null;
        try
        {
            fIn = context.getAssets()
                    .open(fileName, Context.MODE_WORLD_READABLE);
            isr = new InputStreamReader(fIn);
            input = new BufferedReader(isr);
            String line = "";

            while ((line = input.readLine()) != null)
            {
                returnString.append(line);
            }
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        finally
        {
            try
            {
                if (isr != null)
                    isr.close();
                if (fIn != null)
                    fIn.close();
                if (input != null)
                    input.close();
            }
            catch (Exception e2)
            {
                e2.getMessage();
            }
        }
        return returnString.toString();
    }
}
