package com.example.matricula;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.EditText;
import android.widget.CompoundButton;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private CheckBox carnetBibliotecaCheckbox;
    private CheckBox carnetPasajeCheckbox;
    private RadioButton cuotas2RadioButton;
    private RadioButton cuotas5RadioButton;
    private EditText costoCarreraEditText;
    private EditText D_PensionEditText;
    private EditText gastosAdicionalesEditText;
    private Button calcularButton;
    private Button imprimirButton;
    private TextView totalTextView;

    private double total = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carnetBibliotecaCheckbox = findViewById(R.id.Carnet_Biblioteca);
        carnetPasajeCheckbox = findViewById(R.id.Carnet_Pasaje);
        cuotas2RadioButton = findViewById(R.id.Cuotas2);
        cuotas5RadioButton = findViewById(R.id.Cuotas5);
        costoCarreraEditText = findViewById(R.id.D_Costo_Carrera);
        D_PensionEditText = findViewById(R.id.D_Pesion);
        gastosAdicionalesEditText = findViewById(R.id.D_Gastos_Adicionales);
        totalTextView = findViewById(R.id.D_Total);
        calcularButton = findViewById(R.id.Calcular);
        imprimirButton = findViewById(R.id.Imprimir);

        carnetBibliotecaCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> updateTotal(isChecked, 25.0));
        carnetPasajeCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> updateTotal(isChecked, 22.0));

        cuotas2RadioButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                calculateTotalWithCuotas(0.12);
            }
        });

        cuotas5RadioButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                calculateTotalWithCuotas(0.12);
            }
        });

        calcularButton.setOnClickListener(v -> calculateTotal());

    }

    private void updateTotal(boolean isChecked, double amount) {
        total += isChecked ? amount : -amount;
        totalTextView.setText(String.valueOf(total));
    }

    private void calculateTotalWithCuotas(double interestRate) {
        double costoCarrera = obtenerCostoCarrera();
        double gastosAdicionales = obtenerGastosAdicionales();
        double totalCuotas = costoCarrera + (costoCarrera * interestRate) + gastosAdicionales;
        updateTotal(true, totalCuotas - total);
    }

    private void calculateTotal() {
        double costoCarrera = obtenerCostoCarrera();
        double pension = obtenerPension();
        double gastosAdicionales = obtenerGastosAdicionales();

        total = costoCarrera + pension + gastosAdicionales;

        if (carnetBibliotecaCheckbox.isChecked()) {
            total += 25.0;
        }

        if (carnetPasajeCheckbox.isChecked()) {
            total += 22.0;
        }

        if (cuotas2RadioButton.isChecked() || cuotas5RadioButton.isChecked()) {
            double interestRate = 0.12;
            double totalCuotas = costoCarrera + (costoCarrera * interestRate) + gastosAdicionales;
            total += totalCuotas - total;
        }

        totalTextView.setText(String.valueOf(total));
    }

    private double obtenerCostoCarrera() {
        String costoCarreraStr = costoCarreraEditText.getText().toString();
        return Double.parseDouble(costoCarreraStr);
    }

    private double obtenerPension() {
        String pensionStr = D_PensionEditText.getText().toString();
        return Double.parseDouble(pensionStr);
    }

    private double obtenerGastosAdicionales() {
        String gastosAdicionalesStr = gastosAdicionalesEditText.getText().toString();
        return Double.parseDouble(gastosAdicionalesStr);
    }

    private String obtenerTexto(EditText editText) {
        return editText.getText().toString();
    }
}
