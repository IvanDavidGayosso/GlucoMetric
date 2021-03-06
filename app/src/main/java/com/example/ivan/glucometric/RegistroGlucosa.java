package com.example.ivan.glucometric;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.UUID;

import static com.example.ivan.glucometric.BuscarDispositivos.EXTRA_DEVICE_ADDRESS;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistroGlucosa extends AppCompatActivity {
    //1)
    Button IdEncender, IdApagar,IdDesconectar;
    TextView IdBufferIn,IdFecha,IdHora,IdGlucosa;
    //-------------------------------------------
    Handler bluetoothIn;
    final int handlerState = 0;
    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private StringBuilder DataStringIN = new StringBuilder();
    private ConnectedThread MyConexionBT;
    // Identificador unico de servicio - SPP UUID
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    // String para la direccion MAC
    private static String address = null;
    //-------------------------------------------

    String id;
    BaseDeDatos base_datos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_registro_glucosa);

        //Enlaza los controles con sus respectivas vistas
        IdDesconectar = (Button) findViewById(R.id.IdDesconectar);
        IdBufferIn = (TextView) findViewById(R.id.IdBufferIn);
        IdFecha= (TextView) findViewById(R.id.IdFecha);
        IdHora= (TextView) findViewById(R.id.IdHora);
        IdGlucosa= (TextView) findViewById(R.id.IdGlucosa);

        final Intent regi_glu= getIntent();
        id=regi_glu.getStringExtra("IDUSUARIO");
        System.out.println("Se recivio dato:"+id);

        base_datos= new BaseDeDatos(this);


        bluetoothIn = new Handler() {
            String fecha;
            String hora;
            String glucosa;
            public void handleMessage(android.os.Message msg) {

                if (msg.what == handlerState) {
                    String readMessage = (String) msg.obj;
                    DataStringIN.append(readMessage);

                    int endOfLineIndex = DataStringIN.indexOf("#");

                    if (endOfLineIndex > 0) {
                        String dataInPrint = DataStringIN.substring(0, endOfLineIndex);
                        obtenerDatos( dataInPrint);
                        IdBufferIn.setText("Dato: " + dataInPrint);//<-<- PARTE A MODIFICAR >->-
                        IdFecha.setText("Fecha: "+ fecha);
                        IdHora.setText("Hora: "+hora);
                        IdGlucosa.setText("Glucosa: "+glucosa+ " dg/ml");
                        base_datos.crearEstado();
                        base_datos.insetarGlucosa(fecha,hora,glucosa,"dg/ml",id,1);
                        base_datos.mostrarRegistros(id);
                        DataStringIN.delete(0, DataStringIN.length());
                        if (btSocket!=null)
                        {
                            try {btSocket.close();}
                            catch (IOException e)
                            { Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();}
                        }

                    }

                }
            }

            private void obtenerDatos(String datos) {
                int corte_uno = datos.indexOf("|");
                int  corte_dos = datos.indexOf("*");

                fecha = DataStringIN.substring(0, (corte_uno));
                hora = DataStringIN.substring((corte_uno+1), corte_dos);
                glucosa = DataStringIN.substring((corte_dos+1), datos.length());

                System.out.println(fecha);
                System.out.println(hora);
                System.out.println(glucosa);
            }
        };

        btAdapter = BluetoothAdapter.getDefaultAdapter(); // get Bluetooth adapter
        VerificarEstadoBT();

        // Configuracion onClick listeners para los botones
        // para indicar que se realizara cuando se detecte
        // el evento de Click
        /*IdEncender.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                MyConexionBT.write("1");
            }
        });

        IdApagar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MyConexionBT.write("0");
            }
        });*/

        IdDesconectar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (btSocket!=null)
                {
                    Intent i= new Intent(RegistroGlucosa.this,GraficasGlucosa.class);
                    startActivity(i);
                    finish();
                    try {btSocket.close();}
                    catch (IOException e)
                    { Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();}
                }

            }
        });

    }

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException
    {
        //crea un conexion de salida segura para el dispositivo
        //usando el servicio UUID
        return device.createRfcommSocketToServiceRecord(BTMODULEUUID);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        //Consigue la direccion MAC desde DeviceListActivity via intent
        Intent intent = getIntent();
        //Consigue la direccion MAC desde DeviceListActivity via EXTRA
        address = intent.getStringExtra(EXTRA_DEVICE_ADDRESS);//<-<- PARTE A MODIFICAR >->->
        //Setea la direccion MAC
        BluetoothDevice device = btAdapter.getRemoteDevice(address);

        try
        {
            btSocket = createBluetoothSocket(device);
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), "La creacción del Socket fallo", Toast.LENGTH_LONG).show();
        }
        // Establece la conexión con el socket Bluetooth.
        try
        {
            btSocket.connect();
        } catch (IOException e) {
            try {
                btSocket.close();
            } catch (IOException e2) {}
        }
        MyConexionBT = new ConnectedThread(btSocket);
        MyConexionBT.start();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        try
        { // Cuando se sale de la aplicación esta parte permite
            // que no se deje abierto el socket
            btSocket.close();
        } catch (IOException e2) {}
    }

    //Comprueba que el dispositivo Bluetooth Bluetooth está disponible y solicita que se active si está desactivado
    private void VerificarEstadoBT() {

        if(btAdapter==null) {
            Toast.makeText(getBaseContext(), "El dispositivo no soporta bluetooth", Toast.LENGTH_LONG).show();
        } else {
            if (btAdapter.isEnabled()) {
            } else {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }
    }



    //Crea la clase que permite crear el evento de conexion
    private class ConnectedThread extends Thread
    {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket)
        {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            try
            {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }
            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run()
        {
            byte[] buffer = new byte[256];
            int bytes;

            // Se mantiene en modo escucha para determinar el ingreso de datos
            while (true) {
                try {
                    bytes = mmInStream.read(buffer);
                    String readMessage = new String(buffer, 0, bytes);
                    // Envia los datos obtenidos hacia el evento via handler
                    bluetoothIn.obtainMessage(handlerState, bytes, -1, readMessage).sendToTarget();
                } catch (IOException e) {
                    break;
                }
            }
        }
        //Envio de trama
        public void write(String input)
        {
            try {
                mmOutStream.write(input.getBytes());
            }
            catch (IOException e)
            {
                //si no es posible enviar datos se cierra la conexión
                Toast.makeText(getBaseContext(), "La Conexión fallo", Toast.LENGTH_LONG).show();
                //finish();
            }
        }
    }

}
