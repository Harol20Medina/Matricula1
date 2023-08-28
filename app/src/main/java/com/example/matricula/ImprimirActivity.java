package com.example.matricula;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ImprimirActivity extends AppCompatActivity {

    EditText alumno, escuela, carrera, gastosAdicionales, costoCarrera, pension, total;
    TextView cuotas, carnetBiblioteca, carnetPasaje;
    Button imprimirButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imprimir);

        alumno = findViewById(R.id.Alumno_I);
        escuela = findViewById(R.id.Escuela_I);
        carrera = findViewById(R.id.Carrera_I);
        gastosAdicionales = findViewById(R.id.Gastos_Adicionales_I);
        costoCarrera = findViewById(R.id.Costo_Carrera_I);
        pension = findViewById(R.id.Pension_I);
        total = findViewById(R.id.Total_I);
        cuotas = findViewById(R.id.Cuotas_I);
        imprimirButton = findViewById(R.id.Imprimir);

        // Recibir los datos pasados desde la actividad principal
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            alumno.setText(extras.getString("Alumno"));
            escuela.setText(extras.getString("Escuela"));
            carrera.setText(extras.getString("Carrera"));
            gastosAdicionales.setText(extras.getString("GastosAdicionales"));
            costoCarrera.setText(extras.getString("CostoCarrera"));
            pension.setText(extras.getString("Pension"));
            total.setText(extras.getString("Total"));
            cuotas.setText(extras.getString("Cuotas"));

            // Mostrar el estado de los CheckBox
            boolean tieneCarnetBiblioteca = extras.getBoolean("CarnetBiblioteca");
            carnetBiblioteca.setText(tieneCarnetBiblioteca ? "Sí" : "No");

            boolean tieneCarnetPasaje = extras.getBoolean("CarnetPasaje");
            carnetPasaje.setText(tieneCarnetPasaje ? "Sí" : "No");
        }
    }
}
