package com.wildfi.budPhiladelphia;
import java.io.File;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;



import com.wildfi.budPhiladelphiadb.DatabaseHandler;
import com.wildfi.budPhiladelphiadb.User;
import com.wildfi.budPhiladelphia.R;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.app.AlertDialog;
import android.widget.TextView;
import android.graphics.Typeface;

public class Registration extends Activity implements OnClickListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration2);
     
        TextView tRegCedula = (TextView) findViewById(R.id.tRegCedula);
        TextView tRegCelular = (TextView) findViewById(R.id.tRegCelular);

        TextView tRegEmail = (TextView) findViewById(R.id.tRegEmail);
        TextView tRegNacimiento = (TextView) findViewById(R.id.tRegNacimiento);
        TextView tRegNombre = (TextView) findViewById(R.id.tRegNombre);
        
        //Grab you font from the asset folder:

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/times.otf");

        
       // Make your TextView look great:

      
        tRegCedula.setTypeface(tf);
        tRegCelular.setTypeface(tf);
         
        tRegEmail.setTypeface(tf);
        tRegNacimiento.setTypeface(tf);        
        tRegNombre.setTypeface(tf);
        
        
        
        
        
    
        EditText RegCedula = (EditText) findViewById(R.id.RegCedula);
        EditText RegCelular = (EditText) findViewById(R.id.RegCelular);

        EditText RegEmail = (EditText) findViewById(R.id.RegEmail);
        EditText RegNacimiento_day = (EditText) findViewById(R.id.RegNacimiento_day);
        EditText RegNacimiento_month = (EditText) findViewById(R.id.RegNacimiento_month);
        EditText RegNacimiento_year = (EditText) findViewById(R.id.RegNacimiento_year);
        EditText RegNombre = (EditText) findViewById(R.id.RegNombre);
        
        //Grab you font from the asset folder:

       

        
       // Make your TextView look great:

     
        RegCedula.setTypeface(tf);
        RegCelular.setTypeface(tf);
       
        RegEmail.setTypeface(tf);
        RegNacimiento_day.setTypeface(tf);  
        RegNacimiento_month.setTypeface(tf);  
        RegNacimiento_year.setTypeface(tf);  
        RegNombre.setTypeface(tf);
        
        
       
        Button regButton = (Button)findViewById(R.id.buttonRegister);
        Button resetButton = (Button)findViewById(R.id.buttonReset);
        Button resetFile = (Button)findViewById(R.id.buttonFile);
        regButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);
        resetFile.setOnClickListener(this);
       
    }

	@Override
	public void onClick(View v) {
		Intent intent;
		switch(v.getId()) {
		case R.id.buttonRegister :
			register();
			break;
	
		case R.id.buttonFile:
			
			SimpleJExcelExample obj = new SimpleJExcelExample();
			obj.writeXLSFile();	
			intent= new Intent();
			intent.setAction(android.content.Intent.ACTION_VIEW);
			File file = new File(Environment.getExternalStorageDirectory()+"/DB.xls");
			intent.setDataAndType(Uri.fromFile(file), "application/vnd.ms-excel");
			startActivity(intent);
		
			break;
	
		
		case R.id.buttonReset :
			reset();
			break;
	
		}
	}

	private void register() {
		
		String nombre = ((EditText)findViewById(R.id.RegNombre)).getText().toString();
		
		String cedula= ((EditText)findViewById(R.id.RegCedula)).getText().toString();
		String email = ((EditText)findViewById(R.id.RegEmail)).getText().toString();
		String celular = ((EditText)findViewById(R.id.RegCelular)).getText().toString();
		
		String day = ((EditText)findViewById(R.id.RegNacimiento_day)).getText().toString();
		String month = ((EditText)findViewById(R.id.RegNacimiento_month)).getText().toString();
		String year = ((EditText)findViewById(R.id.RegNacimiento_year)).getText().toString();
		
		
		
		
		String nacimiento = day +"-"+ month+"-"+year ;//  String.valueOf(day) +"-"+ String.valueOf(month)  +"-"+ String.valueOf(year);
		
		
		
		
		if(nombre.equals("")  || cedula.equals("") || email.equals("") || celular.equals("") ||  day.equals("")|| month.equals("")|| year.equals("")){
			AlertDialog alertDialog = new AlertDialog.Builder(Registration.this).create();	
			alertDialog.setTitle("Advertencia");
			alertDialog.setMessage("Todos los campos son requeridos.");
			
			alertDialog.show();
		}else{
		
		DatabaseHandler db = new DatabaseHandler(this);
		int id = db.addUser(new User(nombre,cedula, email, celular,nacimiento));
		db.close();
		if(id>0) {
			AlertDialog alertDialog = new AlertDialog.Builder(Registration.this).create();	
			alertDialog.setTitle("Registro Completo");
			alertDialog.setMessage("La persona ha sido registrada correctamente");
			alertDialog.show();
		}
		else {
			AlertDialog alertDialog = new AlertDialog.Builder(Registration.this).create();	
			alertDialog.setTitle("Error");
			alertDialog.setMessage("Complete correctamente el formulario.");
			alertDialog.show();
		}
		((EditText)findViewById(R.id.RegNombre)).setText("");

		((EditText)findViewById(R.id.RegCedula)).setText("");
		((EditText)findViewById(R.id.RegEmail)).setText("");
		((EditText)findViewById(R.id.RegCelular)).setText("");
	
		((EditText)findViewById(R.id.RegNacimiento_day)).setText("");
		((EditText)findViewById(R.id.RegNacimiento_month)).setText("");
		((EditText)findViewById(R.id.RegNacimiento_year)).setText("");
	}
	}
	
	private void reset() {

		
		((EditText)findViewById(R.id.RegNombre)).setText("");
	
		((EditText)findViewById(R.id.RegCedula)).setText("");
		((EditText)findViewById(R.id.RegEmail)).setText("");
		((EditText)findViewById(R.id.RegCelular)).setText("");	
		((EditText)findViewById(R.id.RegNacimiento_day)).setText("");
		((EditText)findViewById(R.id.RegNacimiento_month)).setText("");
		((EditText)findViewById(R.id.RegNacimiento_year)).setText("");
	}
	
	public class SimpleJExcelExample {
		public void writeXLSFile() 	{
			System.out.println("--- writeXLSFile() ---");
			DatabaseHandler DBob = new DatabaseHandler(getApplicationContext());
			File file = new File(Environment.getExternalStorageDirectory(), "DB.xls");
	
		try {
		
			SQLiteDatabase db = DBob.getReadableDatabase();
			Cursor curCSV = db.rawQuery("SELECT * FROM clientes" , null);  		
			String a[] = curCSV.getColumnNames();		
			WritableWorkbook workbook = Workbook.createWorkbook(file);	
			WritableSheet sheet = workbook.createSheet("Sheet1", 0);	
			WritableFont headerFont = new WritableFont (WritableFont.TIMES, 10,WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE );
			WritableCellFormat headerCells = new WritableCellFormat(headerFont);
			headerCells.setBackground(Colour.TAN);	
			int column = 0;	
			for (int row=0;row <6;row++) {	
				Label label = new Label(row,column,a[row],headerCells);
				label.setCellFormat(headerCells);
				sheet.addCell(label);
			}
			column ++;	
			WritableFont normalFont = new WritableFont(WritableFont.TIMES, 10);
			WritableCellFormat normalCell = new WritableCellFormat(normalFont);
	
			for (int col=column;   curCSV.moveToNext()  ;col++) 	{
				for (int row=0;row <6;row++) 		{	
					Label label = new Label(row,col, curCSV.getString(row)  ,normalCell);
					label.setCellFormat(normalCell);
					sheet.addCell(label);
				}
			}
			workbook.write();
			System.out.println(file.getAbsoluteFile()	);
			workbook.close();
			System.out.println("Sample.xls created sucessfully");
		}
		catch(Exception ee) {
			System.out.println("Exception :: "+ee);
		}
	}
}
}
