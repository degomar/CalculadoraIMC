package com.androidexpress.AppAlongamento;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TimePicker clock;
    private Button btn_notify;
    private EditText interval;
    boolean activated = false;

    private SharedPreferences bancoDados;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clock           = findViewById(R.id.time_Picker);
        btn_notify      = findViewById(R.id.btn_notify);
        interval        = findViewById(R.id.interval_txt);
        clock.setIs24HourView(true);
        bancoDados =  getSharedPreferences("dataTime", Context.MODE_PRIVATE);
        activated = bancoDados.getBoolean("activated", false);
        if (activated){
            btn_notify.setText(R.string.pause);
            int color = ContextCompat.getColor(this, R.color.statePaused);
            btn_notify.setBackgroundTintList(ColorStateList.valueOf(color));

            //chamada do banco de dados
            int intervalBD  = bancoDados.getInt("interval",0);
            int hourBd      = bancoDados.getInt("hour",clock.getCurrentHour());
            int minuteBD    = bancoDados.getInt("minute",clock.getCurrentMinute());

            interval.setText(String.valueOf(intervalBD));
            clock.setHour(hourBd);
            clock.setMinute(minuteBD);
        }


    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void notifyClick(View view) {
        String space_interval = interval.getText().toString();

        if (space_interval.isEmpty()){
            Toast.makeText(this, R.string.campoVazio, Toast.LENGTH_LONG).show();
            return;
        }

        if (!activated){
        int minute_interval = Integer.parseInt(space_interval);
        int hour = clock.getCurrentHour();
        int minute = clock.getCurrentMinute();
        Log.d("teste", "programa funcionando imprimindo as " + hour + " horas " + minute +" minutos " + " no intervalo de " + minute_interval + " minutos");

        btn_notify.setText(R.string.pause);
        int color = ContextCompat.getColor(this, R.color.statePaused);
        btn_notify.setBackgroundTintList(ColorStateList.valueOf(color));
        activated = true;

        SharedPreferences.Editor editor = bancoDados.edit();
            editor.putBoolean("activated", true);
            editor.putInt("interval", minute_interval);
            editor.putInt("hour", hour);
            editor.putInt("minute", minute);
            editor.apply();
        } else {
                btn_notify.setText(R.string.intervalo);
                int color = ContextCompat.getColor(this, R.color.colorAccent);
                btn_notify.setBackgroundTintList(ColorStateList.valueOf(color));
                activated = false;
            SharedPreferences.Editor editor = bancoDados.edit();
            editor.remove("activated");
            editor.remove("interval");
            editor.remove("hour");
            editor.remove("minute");
            editor.apply();
                }
}
}