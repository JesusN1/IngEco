package com.example.ingeco;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private static final DecimalFormat decimales = new DecimalFormat("0.00");

    EditText VariableP, VariableN, VariableTEM;
    Double p, n, TEM, A, Porcentaje;
    TableLayout TablaArmotizacionFrances;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VariableP = (EditText) findViewById(R.id.VariableP);
        VariableN = (EditText) findViewById(R.id.VariableN);
        VariableTEM = (EditText) findViewById(R.id.VariableTEM);
        TablaArmotizacionFrances = (TableLayout) findViewById(R.id.TablaArmotizacionFrances);
    }

    public void Resolver(View view){

        TablaArmotizacionFrances.removeAllViews();

        p = Double.valueOf(VariableP.getText().toString());
        n = Double.valueOf(VariableN.getText().toString());
        TEM = Double.valueOf(VariableTEM.getText().toString());
        Porcentaje = TEM / 100;

        A = p * (Porcentaje * Math.pow((1 + Porcentaje),n)) / (Math.pow((1 + Porcentaje),n) - 1);


        //------------------------------------------------------------// f = fila
        TableRow Fila0 = new TableRow(this);
        TextView ppago = FormatoTexto("    Periodos de Pago    ","#ffffff");
        Fila0.addView(ppago);

        TextView mrenta = FormatoTexto("    Mensualidad Renta    ","#ffffff");
        Fila0.addView(mrenta);

        TextView interes = FormatoTexto("    Interes    ",   "#ffffff");
        Fila0.addView(interes);

        TextView amortizacion = FormatoTexto("    Amortizacion    ","#ffffff");
        Fila0.addView(amortizacion);

        TextView saldo = FormatoTexto("    Saldo    ","#ffffff");
        Fila0.addView(saldo);

        TablaArmotizacionFrances.addView(Fila0);


        //------------------------------------------------------------//
        TableRow Fila1 = new TableRow(this);
        TextView fPeriodo = FormatoTexto("0", "#ffffff");
        Fila1.addView(fPeriodo);

        TextView fRenta = FormatoTexto("", "#ffffff");
        Fila1.addView(fRenta);

        TextView fInteres = FormatoTexto("", "#ffffff");
        Fila1.addView(fInteres);

        TextView fAmortizacion = FormatoTexto("", "#ffffff");
        Fila1.addView(fAmortizacion);

        TextView fSaldo = FormatoTexto(Double.toString(p), "#ffffff");
        Fila1.addView(fSaldo);

        TablaArmotizacionFrances.addView(Fila1);

        //------------------------------------------------------------// c = columna
        Double Interes = 0.0;
        Double Amortizacion = 0.0;
        Double Saldo = 0.0;
        int i;

        for (i = 0; i < n; i++){
            TableRow fila = new TableRow(this);
            TextView cPeriodo = FormatoTexto(Integer.toString(i+1),"#ffffff");
            fila.addView(cPeriodo);

            TextView cRenta = FormatoTexto(decimales.format(A),"#ffffff");
            fila.addView(cRenta);

            if (i == 0){
                Interes = Porcentaje * p;
                Amortizacion = A - Interes;
                Saldo = p - Amortizacion;

                TextView cInteres = FormatoTexto(decimales.format(Interes),"#ffffff");
                fila.addView(cInteres);

                TextView cAmortizacion = FormatoTexto(decimales.format(Amortizacion),"#ffffff");
                fila.addView(cAmortizacion);

                TextView cPago = FormatoTexto(decimales.format(Saldo),"#ffffff");
                fila.addView(cPago);

            }
            else{
                Interes = Saldo*Porcentaje;
                Amortizacion = A-Interes;
                Saldo = Saldo-Amortizacion;

                TextView cInteres = FormatoTexto(decimales.format(Interes),"#ffffff");
                fila.addView(cInteres);

                TextView cAmortizacion = FormatoTexto(decimales.format(Amortizacion),"#ffffff");
                fila.addView(cAmortizacion);

                TextView cPago = FormatoTexto(decimales.format(Saldo),"#ffffff");
                fila.addView(cPago);

            }

            TablaArmotizacionFrances.addView(fila);
        }
    }

    public TextView FormatoTexto(String text, String color){

        TextView ver = new TextView(this);
        ver.setText(text);
        ver.setTextColor(Color.parseColor(color));
        ver.setTextSize(15);
        ver.setGravity(Gravity.CENTER);
        return ver;

    }
}