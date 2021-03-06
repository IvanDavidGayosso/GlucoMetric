package com.example.ivan.glucometric;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.Color;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import java.util.ArrayList;

//Se generá la clase de Graficas de glucosa en la cual se obtienen los valores de la grafica.
public class GraficasGlucosa extends AppCompatActivity {
    BaseDeDatos basedatos;
    private  LineChart mChart;
    View graficas_glucosa;
    public GraficasGlucosa() {
        // Required empty public constructor
    }
   
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_graficas_glucosa);

        //En este metodo se generán las las propiedades de la grafica, desde el nivel exedente (el punto en el cual será 
        //exedente el nivel de glucosa en la grafica que será señalado de color Rojo).
        mChart = (LineChart) findViewById(R.id.linechart);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        LimitLine upper_Limit = new LimitLine(180f, "Exedente");
        upper_Limit.setLineWidth(4f);
        upper_Limit.enableDashedLine(10f, 10f, 0f);
        upper_Limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        upper_Limit.setTextColor(Color.RED);
        upper_Limit.setTextSize(15f);

        //Posteriormente se crea el nivel Bajo (el punto en el cual el nivel de azucar es muy bajo en este 
        //se generán las propiedades para señalarlo de color amarillo).
        LimitLine lower_Limit = new LimitLine(60f, "Bajo");
        lower_Limit.setLineWidth(3f);
        lower_Limit.enableDashedLine(10f, 10f, 0f);
        lower_Limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        lower_Limit.setTextColor(Color.YELLOW);
        lower_Limit.setTextSize(15f);

        //Ademas se llaman los niveles de la glucosa para que se puedan registrar y mostrar estos limites.
        //Se crean los limites de los puntos "Y" de las graficas el cual empezará de 5 y terminará en 250.
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.addLimitLine(upper_Limit);
        leftAxis.addLimitLine(lower_Limit);
        leftAxis.setAxisMaximum(250f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.enableGridDashedLine(10f,10f,0);
        leftAxis.setDrawLimitLinesBehindData(true);

        mChart.getAxisRight().setEnabled(false);

         //Despues de esto se generá un ArrayList para agregar los valores de los niveles de glucosa y mostrandolos en un histograma 
        //con lineas color blanco en el cual se obserba que se agregarán 12 valores
        basedatos = new BaseDeDatos(this);
        Cursor c = basedatos.mostrarRegistros("1");
        ArrayList<Entry> yValues = new ArrayList<>();
        int i=0;

        if(c.moveToFirst()){
            do{
                yValues.add(new Entry(i,c.getFloat(3)));
                System.out.println(i + "Listo "+c.getFloat(3));
                i=i+1;
            }while (c.moveToNext());
        }
        LineDataSet set1 = new LineDataSet(yValues, "Glucosa");
        set1.setColor(Color.WHITE);
        set1.setValueTextColor(Color.WHITE);
        set1.setFillAlpha(90);
        set1.setLineWidth(2.5f);
        set1.setValueTextSize(10f);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        LineData data = new LineData(dataSets);
        mChart.setData(data);

        //Aquí los valores insertados anteriormente en el cual se visualizarán los meses en el eje X
        String[] values =new String[i];
        i=0;
        if(c.moveToFirst()){
            do{
                values[i]=c.getString(2);
                i=i+1;
            }while (c.moveToNext());
        }
        XAxis xAxis = mChart.getXAxis();
        xAxis.setValueFormatter(new MyAxisValueFormatter(values));
        xAxis.setGranularity(1f);

    }
    //La clase de Axis en el cual se llamarán a los valores
    public  class MyAxisValueFormatter implements IAxisValueFormatter {
        private String[] mValues;

        public MyAxisValueFormatter(String[] values) {this.mValues = values; }
        //Se mandan a llamar los valores enteros Axis para colocarlos
        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValues[(int)value];
        }
    }



}
