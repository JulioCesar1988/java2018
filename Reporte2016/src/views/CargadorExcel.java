package views;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.List;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import tools.CargadorCombobox;
import javax.swing.border.BevelBorder;

import org.apache.poi.hslf.record.PPDrawing;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.lowagie.text.Row;
import com.toedter.calendar.JDateChooser;

import bd.Conector;
import bd.ParametrosConexion;
import controllers.ElementoController;
import controllers.EmpleadoController;
import controllers.InformacionDeCargaCSV;
import controllers.MaterialController;
import controllers.ObrasController;
import controllers.SistemaController;
import controllers.UsuarioController;
import groovyjarjarantlr.StringUtils;
import models.MaterialBean;
import models.PaqueteBean;
import models.PiezaBean;
import models.SubpiezaBean;
import net.sf.jasperreports.engine.JRException;
import reportes.ReporteGenerador;
import session.Session;

import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import java.awt.Scrollbar;
import java.awt.TextArea;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;

public class CargadorExcel extends JInternalFrame {
	public JTextField textFieldPath;
	public JComboBox<String> comboBoxEdificio;
	public JComboBox<String> comboBoxObra;
	public JComboBox<String> comboBoxSistemas;
	public TextArea textArea;
	public File selectedFile;
	private JTextField textFieldDescripcion;
	JDateChooser dateChooserFinalizacion;
	JDateChooser dateChooserDespacho;
	public ArrayList<PiezaBean> piezas = new ArrayList<PiezaBean>();
	private JTextField textFieldUsuario;
	private JPasswordField passwordFieldClave;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CargadorExcel frame = new CargadorExcel();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public CargadorExcel() {
		setResizable(true);
		setFrameIcon(new ImageIcon(CargadorExcel.class.getResource("/com/birosoft/liquid/icons/panther-blue.png")));
		setBackground(Color.DARK_GRAY);
		setClosable(true);
		setMaximizable(true);
		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setEnabled(false);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setForeground(Color.WHITE);
		setTitle("Cargador Excel");
		setBounds(100, 100, 1200, 850);
		getContentPane().setLayout(null);

		final JComboBox comboBoxEdificio = new JComboBox();
		comboBoxEdificio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		JLabel lblEdificio = new JLabel("Edificio");
		lblEdificio.setBackground(Color.BLACK);
		lblEdificio.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblEdificio.setBounds(274, 109, 63, 14);
		getContentPane().add(lblEdificio);

		CargadorCombobox cc = new CargadorCombobox();
		comboBoxObra = new JComboBox(cc.generarContenidoObra());
		comboBoxObra.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String obra = (String) comboBoxObra.getSelectedItem();
				StringTokenizer tk2 = new StringTokenizer(obra, " - ");
				int obra_num = Integer.parseInt(tk2.nextToken());

				CargadorCombobox cc = new CargadorCombobox();
				String[] lista = cc.generarContenidoEdificiosParaModificar(obra_num);

				if (lista.length > 0) {
					comboBoxEdificio.setModel(new DefaultComboBoxModel(lista));

					System.out.println(" Edificio seleccionado :" + comboBoxEdificio.getSelectedItem());

				} else {
					comboBoxEdificio.setModel(new DefaultComboBoxModel(lista));
					System.out.println("esta obra no tiene edificio ... ");

				}

			}

		});

		JLabel lblObra = new JLabel("Obra");
		lblObra.setBackground(Color.DARK_GRAY);
		lblObra.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblObra.setBounds(274, 60, 46, 14);
		getContentPane().add(lblObra);

		JLabel lblSistemaDiseo = new JLabel("Sistema");
		lblSistemaDiseo.setBackground(SystemColor.desktop);
		lblSistemaDiseo.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblSistemaDiseo.setBounds(549, 57, 113, 14);
		getContentPane().add(lblSistemaDiseo);
		comboBoxObra.setBackground(Color.WHITE);
		comboBoxObra.setForeground(Color.DARK_GRAY);
		comboBoxObra.setBounds(33, 54, 231, 20);
		getContentPane().add(comboBoxObra);

		comboBoxEdificio.setBackground(Color.WHITE);
		comboBoxEdificio.setForeground(Color.DARK_GRAY);
		comboBoxEdificio.setBounds(33, 106, 231, 20);
		getContentPane().add(comboBoxEdificio);

		SistemaController s = new SistemaController();
		try {
			comboBoxSistemas = new JComboBox(s.getListaDeSistemas());
			comboBoxSistemas.setBackground(Color.WHITE);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		comboBoxSistemas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Sistema seleccionado : " + comboBoxSistemas.getSelectedItem());

			}
		});
		comboBoxSistemas.setBounds(349, 54, 198, 20);
		getContentPane().add(comboBoxSistemas);

		textFieldPath = new JTextField();
		textFieldPath.setEditable(false);
		textFieldPath.setBackground(Color.WHITE);
		textFieldPath.setBounds(33, 153, 320, 20);
		getContentPane().add(textFieldPath);
		textFieldPath.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 244, 844, 2);
		getContentPane().add(separator);

		JButton btnSeleccionarExcel = new JButton("Seleccionar Excel");
		btnSeleccionarExcel.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSeleccionarExcel.setBackground(Color.WHITE);
		btnSeleccionarExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Áca vamos a seleccionar el archivo excel.

				JFileChooser fileChooser = new JFileChooser();
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					selectedFile = fileChooser.getSelectedFile();

					if (selectedFile.exists()) {
						System.out.println("Existe el arhivo seleccionado");
						textFieldPath.setText(selectedFile.getPath());

					} else {
						System.out.println("No existe el archivo seleccionado");
					}

				}

			}
		});

		JButton btnCargar = new JButton("Cargar");
		btnCargar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCargar.setBackground(Color.WHITE);
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!textFieldUsuario.getText().isEmpty() && !(passwordFieldClave.getText().isEmpty())) {
					String usuario = textFieldUsuario.getText();
					String password = passwordFieldClave.getText();
					// Session session = new Session();

					// session.cargarUsuario(usuario, password);
					// UsuarioController uController = new UsuarioController();
					// int res = 0;
					// int u = Integer.parseInt(usuario);
					// res = uController.getUsuario( , password);
					if (validarUsuario(usuario, password)) {
						System.out.println("TRUE");
					} else {
						System.out.println("FALSE");
					}
				}
				if (dateChooserFinalizacion.getDate() != null) {
					if (dateChooserDespacho.getDate() != null) {
						if (textFieldDescripcion.getText().length() > 0) {
							if (comboBoxEdificio.getSelectedItem() != null) {
								if (generarSession(textFieldUsuario.getText(), passwordFieldClave.getText())) {
									System.out.println("EXITO " + Session.getNombreUsuario());
									InformacionDeCargaCSV print = new InformacionDeCargaCSV();
									print.setVisible(true);
									// System.out.println("Usuario logeado : " +
									// session.Session.getNombreUsuario());
									PaqueteBean paq = new PaqueteBean(); // MODELO
																			// DEL
																			// PAQUETE
																			// QUE
																			// TENEMOS
																			// QUE
																			// GUARDAR
																			// EN
																			// LA
																			// BASE
																			// DE
																			// DATOS
																			// .
									PiezaBean pieza = null; // MODELO DE LA
															// PIEZA LA CUAL
															// TENGO QUE
															// CARGAR CON LOS
															// DATOS DEL EXCEL
									SubpiezaBean subpieza = null; // MODELO DE
																	// LA
																	// SUBPIEZA
									ElementoController ctl_elem = new ElementoController(); // CONTROLADOR
																							// NECESARIO
																							// PARA
																							// ALLAR
																							// EN
																							// AREA
																							// DE
																							// UNA
																							// PIEZA.

									// System.out.println(selectedFile.getPath().length());

									// Lectura del CSV

									String csvFile = selectedFile.getPath();
									String line = "";
									String cvsSplitBy = ";";
									try {

										BufferedReader br = new BufferedReader(new FileReader(csvFile));
										line = br.readLine(); // Leo la primera
																// fila con los
																// nombres
																// de las
																// columnas
										line = br.readLine(); // Leo la primera
																// pieza
										// String pieza = "";

										ElementoController elemctrls = new ElementoController();

										String[] country = line.split(cvsSplitBy);
										while ((line != null && country.length > 0)) {

											if ((country.length > 0 && country[0].trim().length() > 0)) { // aca
																											// se
																											// rompia
												System.out.println("String[] country = line.split(cvsSplitBy);  -- > "
														+ country[0].trim().length());
												// ENTRO A PROCESAR LA PIEZA DEL
												// EXCEL (CSV)
												pieza = new PiezaBean(); // INSTANCIO
																			// EL
																			// MODELO
																			// ,
																			// PARA
																			// PODER
																			// IR
																			// CARGANDO
																			// CON
																			// LOS
																			// CAMPOS
																			// DEL
																			// CSV
												System.out.println("Creando una pieza... "); // VEO
																								// LOS
																								// DATOS
																								// QUE
																								// ESTOY
																								// LEYENDO
												System.out.print("   Conjunto: " + country[0].trim());
												System.out.print("   Dato1: " + country[1].trim());
												System.out.print("   Cant. Conj.: " + country[3].trim());
												System.out.print("   Longitud: " + country[6].trim());
												System.out.print("   Peso: " + country[8].trim());
												System.out.print("   Pintura: " + country[9].trim());
												System.out.println("   Descripcion: " + country[10].trim());

												String c = country[3].trim();
												int Cantidad = Integer.parseInt(c); // COMENAZAMOS
																					// A
																					// CARGAR
																					// LA
																					// PIEZA
																					// CON
																					// LOS
																					// DATOS
																					// DEL
																					// EXCEL
																					// HACIENDO
																					// LAS
																					// CONVERSIONES
																					// QUE
																					// SEAN
																					// NECESARIAS
																					// DADO
																					// QUE
																					// ALGUNOS
																					// DATOS
																					// SON
																					// INT.
												pieza.setCantidad(Cantidad);
												// System.out.println("Cantidad
												// " +
												// pieza.getCantidad()); // DE
												// PASO , VALIDAMOS SI
												// LA PIEZA SE CARGO CON SU
												// CAMPO
												String correlatividad = country[1].trim();
												pieza.setCorrelatividad(correlatividad);
												// System.out.println("correlatividad
												// " +
												// pieza.getCorrelatividad() );
												String elem = country[0].trim();

												// System.out.println("Elemento
												// " +
												// pieza.getElemento());

												// Vamos a conseguir la
												// descripcion del material ,
												// si no esta ponemos una por
												// defecto ...
												boolean encontrado = false;
												try {

													ArrayList<Vector<Object>> as = elemctrls
															.generarListaDeElementosPrecargados();

													for (int i = 0; i < as.size(); i++) {

														if (as.get(i).get(1).toString().equals(elem)
																&& (as.get(i).get(0).toString()
																		.equals(comboBoxSistemas.getSelectedItem()))) {
															System.out.println("Elementos : " + as.get(i).toString());
															pieza.setDescripcion(as.get(i).get(2).toString());
															pieza.setElemento(elem);
															encontrado = true;
														}

													}

												} catch (SQLException e2) {
													// TODO Auto-generated catch
													// block
													e2.printStackTrace();
												}

												// Si no lo encontre
												if (!encontrado) {
													pieza.setDescripcion("Elemento Desconocido en la BD");
													pieza.setElemento("No disponible");
												}

												// System.out.println("Descripcion
												// " +
												// pieza.getDescripcion() );
												String descripcion_extra = country[10].trim();
												pieza.setDescripcion_extra(descripcion_extra);
												// System.out.println("Descripcion
												// extra " +
												// pieza.getDescripcion_extra()
												// );
												String Pintura = country[9].trim(); //
												if (Pintura.length() > 0) {
													pieza.setPintura(true);
													pieza.setColor(Pintura);
													// System.out.println("Pintar
													// " +
													// pieza.getColor());
												} else {
													pieza.setPintura(false);
													pieza.setColor("");
												}
												int longitud = Integer.parseInt(country[6].trim());// Cargamos
																									// la
																									// longitud(el
																									// largo
																									// de
																									// la
																									// pieza).
												pieza.setLargo(longitud);
												// System.out.println("Largo " +
												// pieza.getLargo() );
												pieza.setPesoUnitario(0);// Deberia
																			// de
																			// traerlo
																			// de
																			// la
																			// BD.
												String s = (String) comboBoxSistemas.getSelectedItem();
												String area;
												try {
													area = ctl_elem.getArea(elem, s);
													pieza.setArea(area); // Cargamos
																			// el
																			// area
																			// de
																			// la
																			// pieza
																			// dado
																			// el
																			// elemento
																			// de
																			// esa
																			// pieza.
													// System.out.println("Area
													// " +
													// pieza.getArea());
												} catch (SQLException e1) {
													// TODO Auto-generated catch
													// block
													e1.printStackTrace();
												}

												line = br.readLine();
												if (line != null)
													country = line.split(cvsSplitBy);

												// TERMINAMOS DE CARGAR LA PIEZA
												// , AHORA AL SALIR DE
												// ACA , DEBERIAMOS CARGAR LAS
												// SUBPIEZAS DE MI
												// PIEZA.

											} else {

												if ((country.length > 0) && (country[2].trim().length() == 0)) { // ACA
																													// ENTRAMOS
																													// A
																													// PROCESAR
																													// LAS
																													// SUBPIEZAS

													System.out.println(" country[2].trim().length() --> "
															+ country[2].trim().length());
													// GUARDAMOS LOS DATOS DE LA
													// SUBPIEZAS , ESTAS
													// SUBPIEZA PROCESADA
													// CORRESPONDE A LA PIEZA
													// DEL
													// PRIMER IF
													System.out.println("     Creando una subpieza... ");
													System.out.print("        Parte: " + country[2].trim());
													System.out.print("        Cant.: " + country[4].trim());
													System.out.print("        Perfil: " + country[5].trim());
													System.out.print("        Longitud: " + country[6].trim());
													System.out.print("        Area: " + country[7].trim());
													System.out.println("        Peso: " + country[8].trim());

													/*
													 * SubpiezaBean sub = new
													 * SubpiezaBean(); // GENERO
													 * LA INSTANCIA DE LA
													 * SUBPIEZAS PARA CARGAR
													 * 
													 * sub.setIdPiezaPadre(pieza
													 * .getIdPieza());// Asigno
													 * el ID de mi pieza. int
													 * cantSub =
													 * Integer.parseInt(country[
													 * 4].trim());
													 * sub.setCantidad(cantSub);
													 * // Cargo la Cantidad
													 * 
													 * int largoSubpieza =
													 * Integer.parseInt(country[
													 * 6].trim()); sub.setLargo(
													 * largoSubpieza); //
													 * Asignacion del largo de
													 * la subpiezas.
													 * 
													 * String codMaterial =
													 * (String )
													 * country[5].trim();
													 * sub.setCodigoMaterial(
													 * codMaterial);// Asignamos
													 * condigo de material(el
													 * material tenemos que
													 * buscarlo de la BD) .
													 * 
													 * sub.setIdMaterial(2415);
													 * // ASIGNAMOS ALGUN ID DE
													 * UN MATERIAL .
													 * sub.setCorrelatividad(
													 * "5555");
													 * 
													 * sub.setCorrelatividad(
													 * country[2].trim());
													 * 
													 * 
													 * pieza.añadirSubpieza(sub)
													 * ;
													 */

													line = br.readLine();
													if (line != null)
														country = line.split(cvsSplitBy);

												} else {

													// Mientras encuentre
													// subpiezas las proceso
													System.out
															.println("     Creando subpiezas para la pieza: " + pieza);
													if (country.length > 0 && (country[2].trim().length() > 0)) {
														System.out.println(" CUMPLE SUBPIEZA");

													} else {

														System.out.println("NO CUMPLE SUBPIEZA");

													}
													while (line != null && country.length > 0
															&& country[2].trim().length() > 0) {
														System.out.print("        Parte: " + country[2].trim());
														System.out.print("        Cant.: " + country[4].trim());
														System.out.print("        Perfil: " + country[5].trim());
														System.out.print("        Longitud: " + country[6].trim());
														System.out.print("        Area: " + country[7].trim());
														System.out.println("        Peso: " + country[8].trim());

														SubpiezaBean sub = new SubpiezaBean(); // GENERO
																								// LA
																								// INSTANCIA
																								// DE
																								// LA
																								// SUBPIEZAS
																								// PARA
																								// CARGAR

														sub.setIdPiezaPadre(pieza.getIdPieza());// Asigno
																								// el
																								// ID
																								// de
																								// mi
																								// pieza.

														sub.setElemento(country[2].trim());// ELEMENTO
																							// AUNQUE
																							// EN
																							// PANTALLAS
																							// APARECE
																							// COMO
																							// POSCION.

														int cantSub = Integer.parseInt(country[4].trim());
														sub.setCantidad(cantSub); // Cargo
																					// la
																					// Cantidad

														// int largoSubpieza =
														// Integer.parseInt(country[6].trim());
														
														
														
														// Si Codigo del material tiene CH cargo el largo , sino el area.
														if (country[5].trim().contains("ch")|| country[5].trim().contains("Ch") || country[5].trim().contains("CH") || country[5].trim().contains("ch") || country[5].trim().contains("cH") ) {
															float largoSubpieza = Float.parseFloat(country[6].trim());
															sub.setLargo(largoSubpieza); // Asignacion
																							// del
																							// largo
																							// de
																							// la
																							// subpiezas.
														} else{
														
															
														String num = country[7].trim();	
												     	float AreaSubpieza = Float.parseFloat(num);
														sub.setLargo(AreaSubpieza); // Asignacion
																						// del|
																						// largo
																						// de
																						// la
																						// subpiezas.
														}
														
														
														
														String codigo = (String) country[5].trim();
														MaterialController mController = new MaterialController();
														int aux = 1;
														MaterialBean mat = new MaterialBean();
													    try {
															aux = mController.getPorCodigo(codigo, mat);
														} catch (SQLException e1) {
															// TODO Auto-generated catch block
															e1.printStackTrace();
														}
															
														if (aux == 1 ) {
															System.out.println("tipo de material " + mat.getTipo());
															System.out.println("codigo de material "+mat.getCodigo());
															System.out.println("espesor de material "+ mat.getEspesor());
															System.out.println("unidad de peso " +mat.getUnidadPeso());
															System.out.println("Peso especifico  "+mat.getPesoEspecifico());
															System.out.println("ancho  "+mat.getAncho());
															sub.setIdMaterial(mat.getIdmaterial());
															sub.setCodigoMaterial(mat.getCodigo());
															sub.setUnidadPeso(mat.getUnidadPeso());
															sub.setPesoMaterial(mat.getPesoEspecifico());
															sub.setObservaciones(mat.getCodigo());
                                                            sub.setAncho(mat.getAncho());
															System.out.println(
																	"Id del material :  " + mat.getIdmaterial());
															System.out.println("Peso :  " + mat.getPesoEspecifico());
															System.out
																	.println("Peso de Unidad : " + mat.getUnidadPeso());
															
															
															
														}
														
														else {
															sub.setIdMaterial(2);
															String codMaterial = (String) country[5].trim();
															sub.setCodigoMaterial(codMaterial);// Asignamos
																								// condigo
																								// de
																								// material(el
																								// material
																								// tenemos
																								// que
																								// buscarlo
																								// de
																								// la
																								// BD)
																								// .
															sub.setIdMaterial(2); // ASIGNAMOS
																						// ALGUN
																						// ID
																						// DE
																						// UN
																						// MATERIAL
																						// .
														
														}
														sub.setCorrelatividad(" ");
														pieza.añadirSubpieza(sub);
														line = br.readLine();
														if (line != null) {
															country = line.split(cvsSplitBy);
														}

													}
													
													
											 piezas.add(pieza);

												}
												
												
												

											}

										}
										
										
									    // Actualizar los pesos de la pieza .	
										
										for (int i = 0; i < piezas.size(); i++) {
											float peso_nuevo = 0;
											for (int j = 0; j < piezas.get(i).getSubPiesas().size(); j++) {
                                            System.out.println( "Peso Subpieza : " + piezas.get(i).getSubPiesas().get(j).getPeso() );
												peso_nuevo = peso_nuevo + piezas.get(i).getSubPiesas().get(j).getCantidad() * piezas.get(i).getSubPiesas().get(j).getPeso();
											}
											
											piezas.get(i).setPesoTotal(piezas.get(i).getCantidad()*peso_nuevo);
										   
											System.out.println("PESO DE LA PIEZA _ " + piezas.get(i).getPesoTotal());
											
										}
										
										
										

										System.out.println("CARGANDO PAQUETE #######################");
										System.out.println("**************" + piezas.size());

										String formato = dateChooserFinalizacion.getDateFormatString();
										java.util.Date date = dateChooserFinalizacion.getDate();
										SimpleDateFormat sdf = new SimpleDateFormat(formato);

										String formato1 = dateChooserDespacho.getDateFormatString();
										java.util.Date date1 = dateChooserDespacho.getDate();
										SimpleDateFormat sdf1 = new SimpleDateFormat(formato1);

										String obra = (String) comboBoxObra.getSelectedItem();
										StringTokenizer tk2 = new StringTokenizer(obra, " - ");
										int obra_num = Integer.parseInt(tk2.nextToken());
										paq.setFechaFinalizacion(String.valueOf(sdf.format(date)));
										paq.setPiezas(piezas);
										paq.setFechaDespacho(String.valueOf(sdf.format(date1)));
										paq.setNumeroObra(obra_num);
										paq.setDescripcion(textFieldDescripcion.getText());
										String Edificio = (String) comboBoxEdificio.getSelectedItem();
										paq.setNombreEdificio(Edificio);
										paq.setSoftDiseno((String) comboBoxSistemas.getSelectedItem());

										paq.guardarPaquete();

										javax.swing.JOptionPane.showMessageDialog(null, "Carga Exitosa");
										
										ReporteGenerador rp = new ReporteGenerador();
										try {
											rp.generarReportePaquetePiezasPDF(obra_num, paq.getNumero());
										} catch (JRException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}

										br.close();

									} catch (IOException e1) {
										e1.printStackTrace();

									}
								} else {

									javax.swing.JOptionPane.showMessageDialog(null,
											"Usuario ó contraseña no validos , verifique.");
									System.out.println("No EXITO");

								}
							} else {
								javax.swing.JOptionPane.showMessageDialog(null,
										"La El Edificio asignacion al paquete debe ser existente ");

							}

						} else {
							javax.swing.JOptionPane.showMessageDialog(null,
									"La descripcion del paquete debe ser obligatoria ");
						}

					} else {
						javax.swing.JOptionPane.showMessageDialog(null, "La fecha de despacho es obligatoria");
					}
				} else {
					javax.swing.JOptionPane.showMessageDialog(null, "La fecha de fabricación es obligatoria");
				}

			}
            
			
			
			

			private void bloquear() {
				// TODO Auto-generated method stub

				comboBoxEdificio.setEditable(false);
				comboBoxObra.setEditable(false);
				comboBoxSistemas.setEditable(false);
				textFieldDescripcion.setEditable(false);
				textFieldUsuario.setEditable(false);

			}

		}

		);

		dateChooserDespacho = new JDateChooser();
		dateChooserDespacho.setBounds(645, 200, 167, 23);
		getContentPane().add(dateChooserDespacho);

		dateChooserFinalizacion = new JDateChooser();
		dateChooserFinalizacion.setSize(167, 23);
		dateChooserFinalizacion.setLocation(443, 200);
		GridBagConstraints gbc_dateChooserFinalizacion = new GridBagConstraints();
		gbc_dateChooserFinalizacion.fill = GridBagConstraints.HORIZONTAL;

		getContentPane().add(dateChooserFinalizacion, gbc_dateChooserFinalizacion);
		// panel_3.add(dateChooser_1, gbc_dateChooser_1);

		btnSeleccionarExcel.setBounds(363, 152, 176, 20);
		getContentPane().add(btnSeleccionarExcel);
		btnCargar.setForeground(Color.BLACK);
		btnCargar.setBounds(33, 273, 89, 23);
		getContentPane().add(btnCargar);

		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setBounds(349, 106, 586, 23);

		getContentPane().add(textFieldDescripcion);
		textFieldDescripcion.setColumns(10);

		JLabel lblDescripcionPaquete = new JLabel("Descripcion Paquete");
		lblDescripcionPaquete.setBackground(SystemColor.desktop);
		lblDescripcionPaquete.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblDescripcionPaquete.setBounds(349, 82, 514, 14);
		getContentPane().add(lblDescripcionPaquete);

		JLabel lblFechaDeFabricacion = new JLabel("Fecha de fabricacion");
		lblFechaDeFabricacion.setBackground(SystemColor.activeCaption);
		lblFechaDeFabricacion.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblFechaDeFabricacion.setBounds(443, 175, 167, 14);
		getContentPane().add(lblFechaDeFabricacion);

		JLabel lblNewLabel = new JLabel("Fecha de despacho");
		lblNewLabel.setBackground(SystemColor.activeCaption);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel.setBounds(645, 175, 167, 14);
		getContentPane().add(lblNewLabel);

		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(977, 53, 122, 28);
		getContentPane().add(textFieldUsuario);
		textFieldUsuario.setColumns(10);

		passwordFieldClave = new JPasswordField();
		passwordFieldClave.setBounds(977, 118, 122, 28);
		getContentPane().add(passwordFieldClave);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsuario.setBackground(Color.DARK_GRAY);
		lblUsuario.setBounds(977, 24, 55, 16);
		getContentPane().add(lblUsuario);

		JLabel lblClave = new JLabel("Clave");
		lblClave.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblClave.setBounds(977, 94, 55, 16);
		getContentPane().add(lblClave);
	}

	private boolean validarUsuario(String usuario, String password) {
		System.out.println("Entro al metodo para validar el usuario ");
		try {
			System.out.println("Creacion del controlador del usuario");
			UsuarioController uController = new UsuarioController();

			int res = uController.getUsuario(Integer.parseInt(usuario), password);
			if (res == 0) {

				return false;

			} else {

				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	private boolean generarSession(String nombreDeUsuario, String pass) {
		System.out.println("	Genera la sesion	");
		Session session = new Session();
		return session.cargarUsuario(nombreDeUsuario, pass);

	}
}
