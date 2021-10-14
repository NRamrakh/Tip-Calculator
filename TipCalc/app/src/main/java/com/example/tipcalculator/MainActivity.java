package com.example.tipcalculator;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {
    final String TAG = "demo";

    SeekBar seekBar;
    TextView customTip;
    RadioGroup radioGroup;
    EditText totalBill;
    TextView tipValueTextView;
    TextView totalBillValue;
    int checkedId, new_checkedId, progress_custom;
    private CharSequence billValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.customTipBar);
        customTip = findViewById(R.id.customTipTextView);
        radioGroup = findViewById(R.id.radioGroup);
        totalBill = findViewById(R.id.editTextTotalBill);
        billValue = totalBill.getText();
        tipValueTextView = findViewById(R.id.totalTipTextView);
        totalBillValue = findViewById(R.id.totalTextView);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.button) {
                    finish();
                    System.exit(0);
                }
            }
        });

        totalBill.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (billValue.toString().isEmpty()) {
                    tipValueTextView.setText("");
                    totalBillValue.setText("");
                } else {
                    checkedId = radioGroup.getCheckedRadioButtonId();
                    changeOnRadioButton(checkedId, billValue);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (billValue.toString().isEmpty()) {
                    Toast.makeText(getBaseContext(), getResources().getString(R.string.Toast_Empty_input), Toast.LENGTH_SHORT).show();
                } else {
                    changeOnRadioButton(checkedId, billValue);

                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                customTip.setText(progress + "%");
                progress_custom = progress;
                cutomSeekBarChanges(billValue, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void changeOnRadioButton(int checkedId, CharSequence billValue) {
        String billValueInString = billValue.toString();
        if (checkedId == R.id.radioButton10) {
            double tipValue = (parseDouble(billValueInString) * 0.1);
            tipValue = Math.round(tipValue * 100.0) / 100.0;
            tipValueTextView.setText(tipValue + "");
            double finalTotal = tipValue + parseInt(billValueInString);
            totalBillValue.setText(finalTotal + "");
        } else if (checkedId == R.id.radioButton15) {
            double tipValue = parseDouble(billValueInString) * 0.15;
            tipValue = Math.round(tipValue * 100.0) / 100.0;
            tipValueTextView.setText(tipValue + "");
            double finalTotal = tipValue + parseInt(billValueInString);
            totalBillValue.setText(finalTotal + "");
        } else if (checkedId == R.id.radioButton18) {
            double tipValue = (parseDouble(billValueInString) * 0.18);
            tipValue = Math.round(tipValue * 100.0) / 100.0;
            tipValueTextView.setText(tipValue + "");
            double finalTotal = tipValue + parseInt(billValueInString);
            totalBillValue.setText(finalTotal + "");
        } else if(checkedId == R.id.radioButtonCustom){
            if(progress_custom == 0){
                double tipValue = (parseDouble(billValueInString) * 0.2);
                tipValue = Math.round(tipValue * 100.0) / 100.0;
                tipValueTextView.setText(tipValue + "");
                double finalTotal = tipValue + parseInt(billValueInString);
                totalBillValue.setText(finalTotal + "");
            } else {
                double tipValue = (parseDouble(billValueInString) * ((double) progress_custom / 100));
                tipValue = Math.round(tipValue * 100.0) / 100.0;
                tipValueTextView.setText(tipValue + "");
                double finalTotal = tipValue + parseInt(billValueInString);
                totalBillValue.setText(finalTotal + "");
            }
        }
    }

    private  void cutomSeekBarChanges(CharSequence billValue, int progess){
        String billValueInString = billValue.toString();
        new_checkedId = radioGroup.getCheckedRadioButtonId();
        if (new_checkedId == R.id.radioButtonCustom) {
            double tipValue = parseDouble(billValueInString) * ((double) progess / 100);
            tipValue = Math.round(tipValue * 100.0) / 100.0;
            tipValueTextView.setText(tipValue + "");
            double finalTotal = tipValue + parseInt(billValueInString);
            totalBillValue.setText(finalTotal + "");
        }
    }


}