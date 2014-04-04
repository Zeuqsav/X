package com.example.jjj;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Chronometer chrono;
	private Button boton;
	private Button boton2;
	private Button boton3;
	private int pulsado;
	private String botones_pres;
	private int i1;
	private int score;
	private int rondas;
	
	private Random r = new Random();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		boton = (Button)findViewById(R.id.button1);
		chrono = (Chronometer)findViewById(R.id.chronometer1);
		boton2 = (Button)findViewById(R.id.button7);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void metodoB1 (View v){
		
		String valor = (String)boton.getText();
		
		if (valor == "Pulsado") {
			boton.setText("Pulsame");	
		} 
		else
		{	
		boton.setText("Pulsado");
		}
	}
	
	public void marca_boton (View v){
		
		boton3 = (Button)findViewById(v.getId());
		String valor = (String)boton3.getText(); 
		
		if (valor == "Pulsado") {
			boton3.setText("Pulsame");	
		} 
		else
		{	
		boton3.setText("Pulsado");
		pulsado = pulsado + 1 ;
		botones_pres=  v.getId() + ";" + botones_pres;
		boton3.setEnabled(false);
		}
	}
	
	
public void desmarca_botones (){
		String [] botoncito  =  botones_pres.split(";");
		for (int i = 0; i < botoncito.length-1; i++) {    
			boton3 = (Button)findViewById(Integer.parseInt(botoncito[i]));
			String valor = (String)boton3.getText(); 
			boton3.setText("Pulsame");	
			pulsado = 0 ;
			boton3.setEnabled(true);
		 }
			
	}
	
	
		public void muestra_random (View v){
		
		String valor = (String)boton2.getText();
		
		
		i1 = r.nextInt(20) + 1;
		
		boton2.setText("tienes " + i1 +  " seg");	

	}
	
	
	public void cuenta1 (View v){
		rondas= rondas +1;
		chrono.setBase(SystemClock.elapsedRealtime());
		chrono.start();
		chrono.setOnChronometerTickListener(new OnChronometerTickListener()
		{
            public void onChronometerTick(Chronometer chronometer) {
                long elapsedTime = SystemClock.elapsedRealtime()
                        - chronometer.getBase();
                if (elapsedTime < (i1 * 1000) && pulsado == 9 ) {

                	score=score+1;
                	Context context = getApplicationContext();
                	CharSequence text = "Ganaste! ronda: " + rondas + " score total " + score ;
                	int duration = Toast.LENGTH_SHORT;
                	Toast toast = Toast.makeText(context, text, 1000);
                	toast.show();  	
                	chrono.stop();
                	desmarca_botones();
                	
                
                }
           //     if (elapsedTime < (i1 * 1000)  )
           //     {
           //        	Context context = getApplicationContext();
           //     	CharSequence text = "Bamos!" + elapsedTime + " ; " + (i1 * 10000) +  " ; " + pulsado ;
           //     	int duration = Toast.LENGTH_SHORT;
           //     	Toast toast = Toast.makeText(context, text, 500);
           //     	toast.show();  	
           //     }
                
                if (elapsedTime > (i1 * 1000) && pulsado < 9   )
                 {
                	Context context = getApplicationContext();
                	score= score -1;
                	CharSequence text = "Perdiste! ronda: " + rondas + " score total " + score ;
                	// CharSequence text = "Perdiste!" + elapsedTime + " ; " + (i1 * 10000)  +  " ; " + pulsado;
                	int duration = Toast.LENGTH_SHORT;
                	Toast toast = Toast.makeText(context, text, 1000);
                	toast.show(); 
                	chrono.stop();
                	desmarca_botones();
                	
                } 
            }
            
		}		
		);	

	
	} 

}
