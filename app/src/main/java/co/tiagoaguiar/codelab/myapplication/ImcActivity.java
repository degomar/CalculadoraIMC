package co.tiagoaguiar.codelab.myapplication;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ImcActivity extends AppCompatActivity {

    private EditText inputHeigt;
    private EditText inputWheight;
    private Button   btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);

        inputHeigt      = findViewById(R.id.input_alturaImc);
        inputWheight    = findViewById(R.id.input_pesoImc);
        btnSend         = findViewById(R.id.btn_calc);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validate()){
                    Toast.makeText(ImcActivity.this, R.string.fillTheFields, Toast.LENGTH_LONG).show();
                return;}

                String sHeight = inputHeigt.getText().toString();
                String sWheigh = inputWheight.getText().toString();
                int height = Integer.parseInt(sHeight);
                int wheight = Integer.parseInt(sWheigh);
                double result = calculoIMC(height, wheight);
                //Log.d("resultado","resultado " + result);
                int imcresult = responseIMC(result);

               AlertDialog dialog = new AlertDialog.Builder(ImcActivity.this)
                       .setTitle(getString(R.string.imcResponse, result))
                       .setMessage(imcresult)
                       .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {

                           }
                       })
                       .create();
               dialog.show();

               InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
               imm.hideSoftInputFromWindow(inputHeigt.getWindowToken(),0);
               imm.hideSoftInputFromWindow(inputWheight.getWindowToken(),0);
            }
        });

    }

    @StringRes
    public int responseIMC(double imc){
        if( imc < 15) return R.string.imc_serveral_low_weight;
        else if (imc < 16) return R.string.imc_very_low_weight;
        else if (imc < 18.5) return R.string.imc_low_weight;
        else if (imc < 25) return R.string.imc_normal;
        else if (imc < 30) return R.string.imc_high_weight;
        else if (imc < 35) return R.string.imc_so_high_weight;
        else if (imc < 40) return R.string.imc_severeal_high_weight;
        else return R.string.imc_severeal_high_weight;
    }

    public double calculoIMC(int height, int wheight){
        return wheight / (((double)height /100) * ((double)height /100));

    }

    public boolean validate(){
        return (!inputHeigt.getText().toString().startsWith("0") && !inputWheight.getText().toString().startsWith("0")
             && !inputHeigt.getText().toString().isEmpty()       && !inputWheight.getText().toString().isEmpty());
    }


}