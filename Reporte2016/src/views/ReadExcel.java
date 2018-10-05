
 
 
 
 
 
 
 
 
 package views;

 import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import controllers.ElementoController;
import controllers.MaterialController;
import groovyjarjarantlr.collections.impl.Vector;
import models.MaterialBean;

 public class ReadExcel {

 	public static void main(String[] args) {
 		ReadExcel re = new ReadExcel();
 		re.test();
 	}
 	
 	private void test() {
 		
 		
 		
 		
 		
 		String condigo =  "Z165x54x20x2,85L";
		MaterialController mController = new MaterialController();
		int aux = 0;
			MaterialBean mat = new MaterialBean();
			try {
				aux = mController.getPorCodigo( condigo , mat);			
				
				System.out.println("aux " + aux);
				
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
			
			if (aux==1) {
				System.out.println("Id del material :  " + mat.getIdmaterial());
				System.out.println("Peso :  " + mat.getPesoEspecifico() );
				System.out.println("Peso de Unidad : " + mat.getUnidadPeso());
				
				
								
			} else {
				
				System.out.println("XXXXXXXXX sin matetial");
				
				
			}
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		/*
 		
 		
 		String csvFile = "C:\\Lista de conjunto excel.csv";
         String line = "";
         String cvsSplitBy = ";";
         

         try {
         	
        	// System.out.println(" 00000000000001 ");
         	BufferedReader br = new BufferedReader(new FileReader(csvFile));
         	
         	line = br.readLine(); // Leo la primera fila con los nombres de las columnas
         	line = br.readLine(); // Leo la primera pieza
         	String pieza = "";

       	 //System.out.println(" 00000000000002 ");
      
         	String[] country = line.split(cvsSplitBy);
             while ((line != null && country.length > 0) )   {
            	 
            	 //System.out.println(" 00000000000003 ");
             	

           	// System.out.println(" 00000000000004 ");
           	 
         
                 
             	if(country.length>0 && country[0].trim().length()>0 ) {

              // 	 System.out.println(" 00000000000005 ");
             		pieza = country[0].trim();
             		System.out.print("Creando la pieza: " + pieza);
             		System.out.print("   Dato1: " + country[1].trim()); System.out.print("   Cant. Conj.: " + country[3].trim()); 
             		System.out.print("   Longitud: " + country[6].trim()); System.out.print("   Peso: " + country[8].trim()); 
             		System.out.print("   Pintura: " + country[9].trim()); System.out.println("   Descripcion: " + country[10].trim());
             		
             		line = br.readLine();
             		if(line!=null) country = line.split(cvsSplitBy);
             		
             	} else if (country.length>0 && (country[2].trim().length()==0))   { // OPCIONAL: se podría quitar este else si se borran los totales del excel
             		System.out.println();
             		//System.out.println("                                                                                             Peso total: "+country[8].trim());
             		System.out.println("   -----------------------------------------------------------------------------------------------------------------");
             		
             		line = br.readLine();
             		if(line!=null) country = line.split(cvsSplitBy);

               	// System.out.println(" 00000000000006 ");
             		
             		
             	
             	} else {
             		// Mientras encuentre subpiezas las proceso
             		System.out.println("     Creando subpiezas para la pieza: " + pieza);
             		if (country.length>0 && (country[2].trim().length() > 0)  ) {
             			 System.out.println(" CUMPLE SUBPIEZA");
						
					}
             		else {
             			
             			 System.out.println("NO CUMPLE SUBPIEZA");
             	
             			
             		}
             		
             
             		
             		
 	            	while (line != null && country.length>0 && country[2].trim().length() > 0  ) {

 	              //	 System.out.println(" 00000000000007 ");
 	        			System.out.print("        Parte: " + country[2].trim());                		
 	            		System.out.print("        Cant.: " + country[4].trim());
 	            		System.out.print("        Perfil: " + country[5].trim());
 	            		System.out.print("        Longitud: " + country[6].trim());
 	            		System.out.print("        Area: " + country[7].trim());
 	            		System.out.println("        Peso: " + country[8].trim());
 	            		
 	            		line = br.readLine();

 	              	// System.out.println(" 00000000000008 ");
 	            		if(line!=null) {

 	                  //	 System.out.println(" 00000000000009 ");
 	            			country = line.split(cvsSplitBy);

 	                  	// System.out.println(" 000000000000010 " +line );
 	                  	 
 	            		
 	    // que te parece la interfaz que hice ¿?jajaja  Esta buena, aunque lo que más importa es lo interior ;) relamente sos un verdadero fluxero jajja jajaja me desconecto ok mil gracias oman de verdad gracias , espero mañana avanzar :D
 	                  	 
 	            		}
 	            		


 	            	}
 	           
             	
             	}
          
             	
             }
             
           
             
       
             br.close();
   
         } catch (IOException e) {
             e.printStackTrace();
         
         }
     */
 		
 		
 		
 	}

 }
            	
            