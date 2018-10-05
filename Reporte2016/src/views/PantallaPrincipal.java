package views;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Toolkit;


public class PantallaPrincipal extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					PantallaPrincipal frame = new PantallaPrincipal();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					frame.setExtendedState(Frame.MAXIMIZED_BOTH);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PantallaPrincipal() {
		setForeground(new Color(0, 0, 255));
		setBackground(Color.DARK_GRAY);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\jcontreras\\workspace\\Reporte2016\\logo\\logoHeader.png"));
		setTitle("Reporteador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1845, 818);

		try {
			setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
			// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()
			// );
			UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
			// UIManager.setLookAndFeel("UpperEssential.UpperEssentialLookAndFeel");

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnSector = new JMenu("sectores");
		mnSector.setForeground(Color.DARK_GRAY);
		mnSector.setFont(new Font("Dialog", Font.BOLD, 13));
		menuBar.add(mnSector);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		final JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.WHITE);
		contentPane.add(desktopPane, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Reportes\\logoHeader.png"));
		lblNewLabel.setBounds(1649, 561, 170, 183);
		desktopPane.add(lblNewLabel);

		JMenuItem mntmProduccion = new JMenuItem("Producci\u00F3n");
		mntmProduccion.setBackground(Color.WHITE);
		mnSector.add(mntmProduccion);
		mntmProduccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produccion p = new Produccion();
				p.setVisible(true);
				p.setMaximizable(true);
				desktopPane.add(p);
				try {
					p.setSelected(true);
					Dimension desktopSize = desktopPane.getSize();
					Dimension jInternalFrameSize = p.getSize();
					p.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
							(desktopSize.height - jInternalFrameSize.height) / 2);
				} catch (java.beans.PropertyVetoException e) {
				}

			}
		});

		JMenuItem mntmCoordinacion = new JMenuItem("Coordinaci\u00F3n");
		mnSector.add(mntmCoordinacion);
		mntmCoordinacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Coordinacion c = new Coordinacion();
				c.setVisible(true);
				c.setMaximizable(true);
				desktopPane.add(c);
				try {
					c.setSelected(true);
					Dimension desktopSize = desktopPane.getSize();
					Dimension jInternalFrameSize = c.getSize();
					c.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
							(desktopSize.height - jInternalFrameSize.height) / 2);
				} catch (java.beans.PropertyVetoException e1) {
				}

			}
		});

		JMenuItem mntmDespacho = new JMenuItem("Despacho");
		mnSector.add(mntmDespacho);

		JMenuItem mntmWarehouse = new JMenuItem("Warehouse");
		mntmWarehouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Warehouse w = new Warehouse();
				w.setVisible(true);

				desktopPane.add(w);
				try {
					w.setSelected(true);
					Dimension desktopSize = desktopPane.getSize();
					Dimension jInternalFrameSize = w.getSize();
					w.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
							(desktopSize.height - jInternalFrameSize.height) / 2);
				} catch (java.beans.PropertyVetoException e) {
				}

			}
		});
		mnSector.add(mntmWarehouse);

		JMenuItem mntmControlDeProduccion = new JMenuItem("Control de Produccion");
		mntmControlDeProduccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ControlDeProduccion cp = new ControlDeProduccion();
				cp.setVisible(true);
				desktopPane.add(cp);
				try {
					cp.setSelected(true);
					Dimension desktopSize = desktopPane.getSize();
					Dimension jInternalFrameSize = cp.getSize();
					cp.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
							(desktopSize.height - jInternalFrameSize.height) / 2);
				} catch (java.beans.PropertyVetoException e) {
				}

			}
		});
		mnSector.add(mntmControlDeProduccion);

		JMenuItem mntmCargarListaCon = new JMenuItem("Cargar Lista Con Excel");
		mntmCargarListaCon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				CargadorExcel c = new CargadorExcel();
				c.setVisible(true);
				desktopPane.add(c);
				try {
					c.setSelected(true);
					Dimension desktopSize = desktopPane.getSize();
					Dimension jInternalFrameSize = c.getSize();
					c.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
							(desktopSize.height - jInternalFrameSize.height) / 2);

				} catch (java.beans.PropertyVetoException e) {
				}

			}
		});
		mnSector.add(mntmCargarListaCon);

		mntmDespacho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Despacho despacho = new Despacho();
				despacho.setVisible(true);
				desktopPane.add(despacho);
				try {
					despacho.setSelected(true);
					Dimension desktopSize = desktopPane.getSize();
					Dimension jInternalFrameSize = despacho.getSize();
					despacho.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
							(desktopSize.height - jInternalFrameSize.height) / 2);

				} catch (java.beans.PropertyVetoException e1) {
				}
			}
		});

	}
}
