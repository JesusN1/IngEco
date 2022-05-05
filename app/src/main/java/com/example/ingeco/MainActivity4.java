package com.example.ingeco;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity4 extends AppCompatActivity {

    private static final DecimalFormat decimales = new DecimalFormat("0.00");

    EditText VariableP, VariableN, VariableTEM;
    Double p, n, TEM, A, Porcentaje;
    TableLayout TablaArmotizacionFlat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        VariableP = (EditText) findViewById(R.id.VariableP);
        VariableN = (EditText) findViewById(R.id.VariableN);
        VariableTEM = (EditText) findViewById(R.id.VariableTEM);
        TablaArmotizacionFlat = (TableLayout) findViewById(R.id.TablaArmotizacionFlat);
    }

    public void Resolver4(View view){

        TablaArmotizacionFlat.removeAllViews();

        p = Double.valueOf(VariableP.getText().toString());
        n = Double.valueOf(VariableN.getText().toString());
        TEM = Double.valueOf(VariableTEM.getText().toString());
        Porcentaje = TEM / 100;

        A = p * (Porcentaje * Math.pow((1 + Porcentaje),n)) / (Math.pow((1 + Porcentaje),n) - 1);


        //------------------------------------------------------------// f = fila
        TableRow Fila0 = new TableRow(this);
        TextView ppago = FormatoTexto("    Periodos de Pago    ","#ffffff");
        Fila0.addView(ppago);

        TextView interes = FormatoTexto("    Interes    ",   "#ffffff");
        Fila0.addView(interes);

        TextView mrenta = FormatoTexto("  Mensualidad Servicio  ","#ffffff");
        Fila0.addView(mrenta);

        TextView amortizacion = FormatoTexto("   Amortizacion   ","#ffffff");
        Fila0.addView(amortizacion);

        TextView saldo = FormatoTexto("   Saldo   ","#ffffff");
        Fila0.addView(saldo);

        TablaArmotizacionFlat.addView(Fila0);


        //------------------------------------------------------------//
        TableRow Fila1 = new TableRow(this);
        TextView fPeriodo = FormatoTexto("0", "#ffffff");
        Fila1.addView(fPeriodo);

        TextView fInteres = FormatoTexto("", "#ffffff");
        Fila1.addView(fInteres);

        TextView fRenta = FormatoTexto("", "#ffffff");
        Fila1.addView(fRenta);

        TextView fAmortizacion = FormatoTexto("", "#ffffff");
        Fila1.addView(fAmortizacion);

        TextView fSaldo = FormatoTexto(Double.toString(p), "#ffffff");
        Fila1.addView(fSaldo);

        TablaArmotizacionFlat.addView(Fila1);

        //------------------------------------------------------------// c = columna
        Double Mensualidad = 0.0;
        Double Interes = 0.0;
        Double Amortizacion = p/n;
        Double Saldo = 0.0;
        int i;

        for (i = 0; i < n; i++){
            TableRow fila = new TableRow(this);

            TextView cPeriodo = FormatoTexto(Integer.toString(i+1),"#ffffff");
            fila.addView(cPeriodo);

            if (i == 0){// 0 = 1
                Saldo = p - Amortizacion;
            }
            else{
                Saldo = Saldo - Amortizacion;
            }
            Interes = Porcentaje * p;
            Mensualidad = Interes + Amortizacion;

            TextView cInteres = FormatoTexto(decimales.format(Interes),"#ffffff");
            fila.addView(cInteres);

            TextView cMensualidad = FormatoTexto(decimales.format(Mensualidad),"#ffffff");
            fila.addView(cMensualidad);

            TextView cAmortizacion = FormatoTexto(decimales.format(Amortizacion),"#ffffff");
            fila.addView(cAmortizacion);

            TextView cSaldo = FormatoTexto(decimales.format(Saldo),"#ffffff");
            fila.addView(cSaldo);

            TablaArmotizacionFlat.addView(fila);
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