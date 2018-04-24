package com.example.ivan.glucometric;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class GenerarReporte extends AppCompatActivity {
    private static final String NOMBRE_CARPETA_APP="com.example.ivan.glucometric";
    private static final String GENERADOS ="MisArchivos";
    Button btn_pdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generar_reporte);
        btn_pdf = (Button) findViewById(R.id.btn_pdf);

        btn_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Document document = new Document(PageSize.LETTER);
                String NOMBRE_ARCHIVO = "MiArchivoPDF.pdf";
                String tarjetaSD = Environment.getExternalStorageDirectory().toString();
                File pdfDIR = new File(tarjetaSD + File.separator + NOMBRE_CARPETA_APP);
                if (!pdfDIR.exists()) {
                    pdfDIR.mkdir();
                    System.out.println(" ---- Segenero carpeta 1 ---");
                }
                File pdfSubDir = new File(pdfDIR.getPath() + File.separator + GENERADOS);
                if (!pdfSubDir.exists()) {
                    pdfSubDir.mkdir();
                    System.out.println(" ---- Segenero carpeta 2 ---");

                }

                String nombre_completo = Environment.getExternalStorageDirectory() + File.separator + NOMBRE_CARPETA_APP
                        + File.separator + GENERADOS + File.separator + NOMBRE_ARCHIVO;

                File outputfile = new File(nombre_completo);
                if (outputfile.exists()) {
                    outputfile.delete();
                }

                try {
                    PdfWriter pdfwriter = PdfWriter.getInstance(document, new FileOutputStream(nombre_completo));
                    /*Crear documento */

                    document.open();
                    document.addAuthor("GlucoMetric");
                    document.addCreator("GlucoMetric");
                    document.addSubject("Gracias por usa GlucoMetric");
                    document.addCreationDate();
                    document.addTitle("Reporte de Glucosa");
                    XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
                    String htmlToPDF = "<html><head></head><body><h1>Hola que tal</h1><p>lalal</p></body></html>";
                    try {
                        worker.parseXHtml(pdfwriter, document, new StringReader(htmlToPDF));
                        document.close();
                        Toast.makeText(getApplicationContext(), "PDF generado", Toast.LENGTH_LONG).show();
                        muestraPDF(nombre_completo, getApplicationContext());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } catch (DocumentException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }

            public void muestraPDF(String archivo, Context context) {
                Toast.makeText(context, "Leyendo el archivo", Toast.LENGTH_LONG).show();
                File file = new File(archivo);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(file), "application/pdf");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                try {
                    context.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(context, "No tiene una APP para abrir el archivo", Toast.LENGTH_LONG).show();

                }

            }
        });
    }
}

