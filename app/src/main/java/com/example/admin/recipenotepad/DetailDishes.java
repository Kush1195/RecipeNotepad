package com.example.admin.recipenotepad;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DetailDishes extends AppCompatActivity
{
    TextView dish_text,desc_text;
    ImageView imageView;
    Button next_btn,previous_btn;

    Bundle b;
    String subCategory;
    int position;
    String[] images;
    String imagePath = "";
    ArrayList<String>list = new ArrayList<>();
    int limit = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_dishes);

        init();

        b = getIntent().getExtras();
        subCategory = b.getString("Submenu");
        position = b.getInt("position");
        list = b.getStringArrayList("Array");

        limit = list.size();

        readData(position,subCategory);

        next_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                position++;
                if (position >= limit)
                {
                    position = 0;
                }
                readData(position, subCategory);
            }
        });

        previous_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                position--;
                if (position < 0)
                {
                    position = limit-1;
                }
                readData(position, subCategory);
            }
        });
    }

    private void readData(int position, String category)
    {
        String subCategory = list.get(position);
        category = subCategory.toLowerCase();

        try
        {
            images = getAssets().list("images");
        } catch (IOException e) {
            e.printStackTrace();
        }

        subCategory = category.trim();

        if (position == 0 && subCategory.contains("bhaji"))
        {
            String file = "bhajiya.txt";
            imagePath = "file:///android_asset/images/bhajiya.jpg";

            String read = ReadFromFile(file, this);
            dish_text.setText(subCategory);
            desc_text.setText(read);
            Glide.with(DetailDishes.this).load(Uri.parse(imagePath)).into(imageView);
        }
        else if (position == 0 && subCategory.contains("paneer"))
        {
            String file = "paneersabji.txt";
            imagePath = "file:///android_asset/images/paneersabji.jpg";

            String read = ReadFromFile(file, this);
            dish_text.setText(subCategory);
            desc_text.setText(read);
            Glide.with(DetailDishes.this).load(Uri.parse(imagePath)).into(imageView);
        }
        else if (position == 0 && subCategory.contains("vada"))
        {
            String file = "vadapav.txt";
            imagePath = "file:///android_asset/images/vadapav.jpg";

            String read = ReadFromFile(file, this);
            dish_text.setText(subCategory);
            desc_text.setText(read);
            Glide.with(DetailDishes.this).load(Uri.parse(imagePath)).into(imageView);
        }
        else if (position == 0 && subCategory.contains("dhosa"))
        {
            String file = "dhosa.txt";
            imagePath = "file:///android_asset/images/dhosa.jpg";

            String read = ReadFromFile(file, this);
            dish_text.setText(subCategory);
            desc_text.setText(read);
            Glide.with(DetailDishes.this).load(Uri.parse(imagePath)).into(imageView);
        }
        else
        {
            String file = subCategory.concat(".txt");
            imagePath = "file:///android_asset/images/" + subCategory + ".jpg";

            String read = ReadFromFile(file, this);
            dish_text.setText(subCategory);
            desc_text.setText(read);
            Glide.with(DetailDishes.this).load(Uri.parse(imagePath)).into(imageView);
        }
    }

    private String ReadFromFile(String file, DetailDishes detailDishes)
    {
        StringBuilder returnString = new StringBuilder();
        InputStream fIn = null;
        InputStreamReader isr = null;
        BufferedReader input = null;
        try
        {
            fIn = detailDishes.getAssets()
                    .open(file, DetailDishes.MODE_WORLD_READABLE);
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

    private void init()
    {
        dish_text = findViewById(R.id.dish_text);
        desc_text = findViewById(R.id.desc_text);
        desc_text.setMovementMethod(new ScrollingMovementMethod());
        imageView = findViewById(R.id.imageView);
        next_btn = findViewById(R.id.next_btn);
        previous_btn = findViewById(R.id.previous_btn);

        try
        {
            images = getAssets().list("images");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
