package com.example.taxgo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Calculator extends AppCompatActivity {

    TextView Monthly_Salary;
    TextView Monthly_Tax;
    TextView Monthly_Salary_After_Tax;
    TextView Yearly_Salary;
    TextView Yearly_Tax;
    TextView Yearly_Salary_After_Tax;
    ImageView Calculate;
    EditText entermonthlysalary;
    Button Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        Back = findViewById(R.id.back_button);
        Back.setOnClickListener(view -> home());

        entermonthlysalary = findViewById(R.id.entermonthlysalary);
        Monthly_Salary = findViewById(R.id.monthlysalary);
        Monthly_Tax = findViewById(R.id.monthlytax);
        Monthly_Salary_After_Tax = findViewById(R.id.monthlysalaryaftertax);
        Yearly_Salary = findViewById(R.id.yearlysalary);
        Yearly_Tax = findViewById(R.id.yearlytax);
        Yearly_Salary_After_Tax = findViewById(R.id.yearlysalaryaftertax);

        Calculate = findViewById(R.id.monthlysalaryicon);
        Calculate.setOnClickListener(view -> tax_calculator());

    }
    ///Functionality of Tax Calculation///
    public void tax_calculator() {
        try {
            double amount = Integer.parseInt(entermonthlysalary.getText().toString());
            double monthlysal, monthlyt, monthlysalaftert, yearlysal, yearlyt, yearlysalaftert;

            if (amount < 20000000) {

                if (amount > 600000 && amount <= 1200000) {
                    monthlysal = amount;
                    yearlysal = monthlysal * 12;
                    monthlyt = amount * 25 / 1000;
                    monthlysalaftert = monthlysal - monthlyt;
                    yearlyt = monthlyt * 12;
                    yearlysalaftert = yearlysal - yearlyt;
                }
                else if (amount > 1200000 && amount <= 2400000) {
                    monthlysal = amount;
                    yearlysal = monthlysal * 12;
                    monthlyt = 15000 + (amount * 125 / 1000);
                    monthlysalaftert = monthlysal - monthlyt;
                    yearlyt = monthlyt * 12;
                    yearlysalaftert = yearlysal - yearlyt;
                }
                else if (amount > 2400000 && amount <= 3600000) {
                    monthlysal = amount;
                    yearlysal = monthlysal * 12;
                    monthlyt = 165000 + (amount * 200 / 1000);
                    monthlysalaftert = monthlysal - monthlyt;
                    yearlyt = monthlyt * 12;
                    yearlysalaftert = yearlysal - yearlyt;
                }
                else if (amount > 3600000 && amount <= 6000000) {
                    monthlysal = amount;
                    yearlysal = monthlysal * 12;
                    monthlyt = 405000 + (amount * 250 / 1000);
                    monthlysalaftert = monthlysal - monthlyt;
                    yearlyt = monthlyt * 12;
                    yearlysalaftert = yearlysal - yearlyt;
                }
                else if (amount > 6000000 && amount <= 12000000) {
                    monthlysal = amount;
                    yearlysal = monthlysal * 12;
                    monthlyt = 1005000 + (amount * 325 / 1000);
                    monthlysalaftert = monthlysal - monthlyt;
                    yearlyt = monthlyt * 12;
                    yearlysalaftert = yearlysal - yearlyt;
                }
                else if (amount > 12000000) {
                    monthlysal = amount;
                    yearlysal = monthlysal * 12;
                    monthlyt = 2955000 + (amount * 350 / 1000);
                    monthlysalaftert = monthlysal - monthlyt;
                    yearlyt = monthlyt * 12;
                    yearlysalaftert = yearlysal - yearlyt;
                }
                else {
                    monthlysal = amount;
                    monthlyt = 0;
                    monthlysalaftert = monthlysal - monthlyt;
                    yearlysal = monthlysal * 12;
                    yearlyt = 0;
                    yearlysalaftert = yearlysal - yearlyt;
                }
                Monthly_Salary.setText(String.valueOf(Math.round(monthlysal)));
                Monthly_Tax.setText(String.valueOf(Math.round(monthlyt)));
                Monthly_Salary_After_Tax.setText(String.valueOf(Math.round(monthlysalaftert)));
                Yearly_Salary.setText(String.valueOf(Math.round(yearlysal)));
                Yearly_Tax.setText(String.valueOf(Math.round(yearlyt)));
                Yearly_Salary_After_Tax.setText(String.valueOf(Math.round(yearlysalaftert)));
            }
            else {
                Toast.makeText(Calculator.this, "insert suitable amount", Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception e) {
            //  Block of code to handle errors
        }

    }
    public void home(){
        finish();
    }

}