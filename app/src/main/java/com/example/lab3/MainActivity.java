package com.example.lab3;

import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements dialog.dialogListener{
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(MainActivity.this, "кнопка номер 1 нажата", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(MainActivity.this, "кнопка номер 2 нажата", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        final String[] listItems = new String[]{"12", "7", "11", "6"};
        final String[] correctItems = new String[]{"12", "6"};
        final boolean[] checkedItems = new boolean[listItems.length];
        final List<String> selectedItems = Arrays.asList(listItems);
        List<String> correctItemsList = Arrays.asList(correctItems);
        List<String> selected = new ArrayList<String>();

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected.clear();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Выберите четные числа")
                        .setMultiChoiceItems(listItems, checkedItems, (dialog, which, isChecked) -> {
                            checkedItems[which] = isChecked;
                            String currentItem = selectedItems.get(which);
                        })
                        .setCancelable(false)
                        .setNegativeButton("Отмена", (dialog, which) -> {})
                        .setPositiveButton("Принять",  (dialog, which) -> {
                            for (int i = 0; i < checkedItems.length; i++) {
                                if (checkedItems[i]) {
                                    selected.add(selectedItems.get(i));
                                }
                            }

                            Log.d("DEBUG", String.valueOf(selected));
                            Log.d("DEBUG", String.valueOf(correctItemsList));
                            if (correctItemsList.equals(selected)) {
                                Toast.makeText(MainActivity.this, "Ответ верный!", Toast.LENGTH_LONG).show();
                            } else {
                                btn1.setVisibility(View.INVISIBLE);
                                btn2.setVisibility(View.INVISIBLE);
                                btn3.setVisibility(View.INVISIBLE);
                                btn4.setVisibility(View.INVISIBLE);
                            }
                        })
                        .setNeutralButton("Сброс", (dialog, which) -> {
                            Arrays.fill(checkedItems, false);
                        });

                builder.create();

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    public void openDialog() {
        dialog dialog = new dialog();
        dialog.show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void apply(Boolean apply) {
        if (apply) {
            btn1.setTextColor(Color.RED);
            btn2.setTextColor(Color.RED);
            btn3.setTextColor(Color.RED);
            btn4.setTextColor(Color.RED);
        }
        else {
            Toast.makeText(MainActivity.this, "Диалоговое окно было закрыто", Toast.LENGTH_LONG).show();
        }
    }
}