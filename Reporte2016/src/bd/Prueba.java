package bd;

import java.sql.SQLException;

import controllers.MaterialController;
import models.MaterialBean;

public class Prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hola");
		
		String cadena  = "Bueno vamos a ver si encuentra un 33 algo";
		
		
		if (cadena.contains("ch")|| cadena.contains("Ch") || cadena.contains("CH") || cadena.contains("ch") || cadena.contains("cH") ) {
			System.out.println("Si tiene el patron ");
		} else {

			System.out.println("No lo tiene ");
		}
		
		
		
		String codigo = "UPN200";
		
		if (codigo.contains("ch")||codigo.contains("Ch")||codigo.contains("CH")) {
			System.out.println("es una chapa");
			MaterialController mController = new MaterialController();
			int aux = 0;
			MaterialBean mat = new MaterialBean();
			try {
				aux = mController.getPorCodigo(codigo, mat);

			} catch (SQLException e1) {
				e1.printStackTrace();

			}

			if (aux == 1) {
				/*
				sub.setIdMaterial(mat.getIdmaterial());
				sub.setCodigoMaterial(mat.getCodigo());
				sub.setUnidadPeso(mat.getUnidadPeso());
				sub.setPesoMaterial(mat.getPesoEspecifico());
				sub.setObservaciones(mat.getCodigo());
	            sub.setAncho(mat.getAncho());
				*/
				System.out.println(
						"Id del material :  " + mat.getIdmaterial());
				System.out.println("Peso :  " + mat.getPesoEspecifico());
				System.out
						.println("Peso de Unidad : " + mat.getUnidadPeso());

			}
		}
		else{
			if (codigo.contains("*")) {
				//como tiene un * tengo que acotar el string , 
				String str = codigo;
				str = str.substring(0, str.indexOf("*"));
				
				MaterialController mController = new MaterialController();
				int aux = 0;
				MaterialBean mat = new MaterialBean();
				try {
					aux = mController.getPorCodigo(str, mat);

				} catch (SQLException e1) {
					e1.printStackTrace();

				}

				if (aux == 1) {
					/*
					sub.setIdMaterial(mat.getIdmaterial());
					sub.setCodigoMaterial(mat.getCodigo());
					sub.setUnidadPeso(mat.getUnidadPeso());
					sub.setPesoMaterial(mat.getPesoEspecifico());
					sub.setObservaciones(mat.getCodigo());
		            sub.setAncho(mat.getAncho());
					*/
					System.out.println(
							"Id del material :  " + mat.getIdmaterial());
					System.out.println("Peso :  " + mat.getPesoEspecifico());
					System.out
							.println("Peso de Unidad : " + mat.getUnidadPeso());

				}
			}else {
				
				if (codigo.contains("X")) {
					//como tiene un * tengo que acotar el string , 
					String str = codigo;
					str = str.substring(0, str.indexOf("X"));
					
					MaterialController mController = new MaterialController();
					int aux = 0;
					MaterialBean mat = new MaterialBean();
					try {
						aux = mController.getPorCodigo(str, mat);

					} catch (SQLException e1) {
						e1.printStackTrace();

					}

					if (aux == 1) {
						/*
						sub.setIdMaterial(mat.getIdmaterial());
						sub.setCodigoMaterial(mat.getCodigo());
						sub.setUnidadPeso(mat.getUnidadPeso());
						sub.setPesoMaterial(mat.getPesoEspecifico());
						sub.setObservaciones(mat.getCodigo());
			            sub.setAncho(mat.getAncho());
						*/
						System.out.println(
								"Id del material :  " + mat.getIdmaterial());
						System.out.println("Peso :  " + mat.getPesoEspecifico());
						System.out
								.println("Peso de Unidad : " + mat.getUnidadPeso());

					}
				}else { 

					
					
					MaterialController mController = new MaterialController();
					int aux = 0;
					MaterialBean mat = new MaterialBean();
					try {
						aux = mController.getPorCodigo(codigo, mat);

					} catch (SQLException e1) {
						e1.printStackTrace();

					}

					if (aux == 1) {
						/*
						sub.setIdMaterial(mat.getIdmaterial());
						sub.setCodigoMaterial(mat.getCodigo());
						sub.setUnidadPeso(mat.getUnidadPeso());
						sub.setPesoMaterial(mat.getPesoEspecifico());
						sub.setObservaciones(mat.getCodigo());
			            sub.setAncho(mat.getAncho());
						*/
						System.out.println(
								"Id del material :  " + mat.getIdmaterial());
						System.out.println("Peso :  " + mat.getPesoEspecifico());
						System.out
								.println("Peso de Unidad : " + mat.getUnidadPeso());

					}
				
					
					
					
					
				}
				
				
				
				
				
			}
			
			
			
			
			
			 
		}
		
		

		
		
		

	}

}
