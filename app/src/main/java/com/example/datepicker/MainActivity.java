package com.example.datepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    EditText dateTxt, timeTxt;

    ImageButton datePick, timePick;

    Button applyBtn;
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateTxt = findViewById(R.id.date_txt);
        timeTxt = findViewById(R.id.time_txt);

        datePick = findViewById(R.id.date_pick);
        timePick = findViewById(R.id.time_pick);

        applyBtn = findViewById(R.id.button_zap);
        datePicker = findViewById(R.id.datePicker);


        datePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedYear = 2000;
                int selectedMonth = 2;
                int selectedDay =2;
                Calendar today = Calendar.getInstance();
                datePicker.setVisibility(View.VISIBLE);

                datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
                        today.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {

                            @Override
                            public void onDateChanged(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                Toast.makeText(getApplicationContext(),
                                        "onDateChanged", Toast.LENGTH_SHORT).show();

                                dateTxt.setText(year + "." + (monthOfYear + 1) + "." + dayOfMonth);
                                datePicker.setVisibility(View.GONE);
                            }
                        });

            }
        });

        timePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean is24HView = true;
                int selectedHour = 10;
                int selectedMinute = 20;

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        timeTxt.setText(hourOfDay + ":" + minute);
                    }
                };

                TimePickerDialog timePickerDialog = new TimePickerDialog (MainActivity.this, timeSetListener, selectedHour,  selectedMinute, is24HView);

                timePickerDialog.show();
            }
        });

        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                // Создание спанnable строки
                SpannableString spannableString = new SpannableString("                    Вы подтверждаете вашу запись?");
                Drawable drawable = getResources().getDrawable(R.drawable.img_2); // Получение изображения из ресурсов
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight()); // Задание размеров изображения
                ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM); // Создание ImageSpan с изображением
                spannableString.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); // Добавление ImageSpan к спанnable строке

                builder.setTitle("Подтверждение записи")
                        .setMessage(spannableString)
                        .setPositiveButton("Podtverdit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                                Toast.makeText(MainActivity.this, "zapis podtverzdena", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Otmenit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .setIcon(R.drawable.ic_bunny).create();

                AlertDialog dialog = builder.show();
                Button positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                positiveButton.setTextColor(getResources().getColor(R.color.GreenYellow));

                Button negativeButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                negativeButton.setTextColor(getResources().getColor(R.color.Maroon));


            }
        });
    }
}
