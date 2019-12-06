package app.bissecao;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      
        Button resultado = (Button) findViewById(R.id.resultado);
      
       
        resultado.setOnClickListener( new View.OnClickListener()
        {
        		public void onClick(View v)
        		{
        			   EditText entrada = (EditText) findViewById(R.id.entrada);
        			   TextView mostrar = (TextView) findViewById(R.id.texto);
        		       String s = entrada.getText().toString();
        		       EditText xvalue = (EditText) findViewById(R.id.xvalue);
        		       double x = Double.valueOf(xvalue.getText().toString());
        		       Parser a= new Parser("");
        		       Double result= a.computing(s,x);
        		       mostrar.setText(result.toString());
        			
        		}
        	
       	});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
