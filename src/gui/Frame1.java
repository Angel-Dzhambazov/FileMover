package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import common.FileMoverRun;

import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// TODO make a progres bar with moved files
public class Frame1 {

	private JFrame frmFileMoverGui;
	private JTextField txtSource;
	private JLabel lblSource;
	private JButton btnSource;
	private static String sourcePath;
	private static String destinationPath;
	private JTextField txtDestination;
	private JMenuItem mntmExit;
	private JMenuItem mntmAbout;
	private JButton btnDestination;
	private JButton btnStartMoving;
	private JButton btnStopMoving;
	private boolean sourceAssigned = false;
	private boolean destinationAssigned = false;
	private boolean isMoving = false;

	/**
	 *
	 * Launch the application.
	 */

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame1 window = new Frame1();
					window.frmFileMoverGui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	public Frame1() {
		initComponents();
		createEvents();
	}

	/*
	 * Initialize the contents of the frame.
	 */

	private void initComponents() {
		frmFileMoverGui = new JFrame("File mover");
		frmFileMoverGui.setIconImage(
				Toolkit.getDefaultToolkit().getImage(Frame1.class.getResource("/resources/shopping-cart.png")));
		frmFileMoverGui.setTitle("File Mover GUI");
		frmFileMoverGui.setBounds(100, 100, 747, 449);
		frmFileMoverGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		lblSource = new JLabel("source:");

		txtSource = new JTextField();
		txtSource.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});


		txtSource.setColumns(10);

		btnSource = new JButton("grab");

		JPanel pnlLogger = new JPanel();
		pnlLogger.setBorder(new TitledBorder(null, "Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JLabel lblDestination = new JLabel("destination:");

		txtDestination = new JTextField();
		txtDestination.setColumns(10);

		btnDestination = new JButton("grab");

		btnStartMoving = new JButton("Start Moving");
		btnStartMoving.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

			}
		});

		btnStopMoving = new JButton("Stop Moving");

		GroupLayout groupLayout = new GroupLayout(frmFileMoverGui.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDestination, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSource, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtSource, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnSource))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtDestination, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnDestination)))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnStartMoving)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnStopMoving)
							.addGap(20)))
					.addGap(4)
					.addComponent(pnlLogger, GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
					.addGap(6))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(pnlLogger, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtSource, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnSource))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblSource)
									.addGap(32)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblDestination)
										.addComponent(txtDestination, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnDestination))))
							.addPreferredGap(ComponentPlacement.RELATED, 264, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnStopMoving)
								.addComponent(btnStartMoving))))
					.addGap(23))
		);
		frmFileMoverGui.getContentPane().setLayout(groupLayout);

		JMenuBar menuBar = new JMenuBar();
		frmFileMoverGui.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mntmExit = new JMenuItem("Exit");
		mntmExit.setIcon(new ImageIcon(Frame1.class.getResource("/resources/exit.png")));

		mnFile.add(mntmExit);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		mntmAbout = new JMenuItem("About");

		mntmAbout.setIcon(new ImageIcon(Frame1.class.getResource("/resources/question.png")));

		mnHelp.add(mntmAbout);

	}

	private void createEvents() {

		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				About about = new About();
				about.setModal(true);
				about.setVisible(true);
			}
		});

		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?");
				if (result == JOptionPane.YES_OPTION) {
					// TODO check if source folder is empty!
					System.exit(0);
				}
			}
		});

		btnSource.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sourcePath = txtSource.getText();
				if (destinationPath != null && destinationPath.equalsIgnoreCase(sourcePath)) {
					JOptionPane.showMessageDialog(null,
							"Source Path is the same as already assigned Destination path. Please change destination or source location!");
					btnSource.setText("grab");
					btnSource.setForeground(Color.BLACK);
					sourcePath = null;
					sourceAssigned = false;
				} else if (FileMoverRun.checkIfDirectoryExists(sourcePath)) {
					JOptionPane.showMessageDialog(null, "Source Path is a valid directory! Please proceed");
					txtSource.setText(null);
					btnSource.setText("Assigned!");
					btnSource.setForeground(Color.BLUE);
					sourceAssigned = true;
				} else {
					JOptionPane.showMessageDialog(null,
							"Source Path is NOT a valid directory! Please reenter your source path!");

					btnSource.setText("grab");
					btnSource.setForeground(Color.BLACK);
					sourcePath = null;

					sourceAssigned = false;
				}

			}
		});

		btnDestination.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				destinationPath = txtDestination.getText();

				if (destinationPath.equalsIgnoreCase(sourcePath)) {
					JOptionPane.showMessageDialog(null,
							"Your destination Path cannot be the same as your Source path!");
					btnDestination.setText("grab");
					btnDestination.setForeground(Color.BLACK);
					destinationPath = null;
					destinationAssigned = false;
				} else if (FileMoverRun.checkIfDirectoryExists(destinationPath)) {
					JOptionPane.showMessageDialog(null, "Destination Path is a valid directory! Please proceed");
					txtDestination.setText(null);
					btnDestination.setText("Assigned!");
					btnDestination.setForeground(Color.BLUE);
					destinationAssigned = true;
				} else {
					JOptionPane.showMessageDialog(null,
							"Destination Path is NOT a valid directory! Please reenter your source path!");
					btnDestination.setText("grab");
					btnDestination.setForeground(Color.BLACK);
					destinationPath = null;
					destinationAssigned = false;
				}

			}
		});

		btnStartMoving.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (sourceAssigned && destinationAssigned) {
					FileMoverRun.startTheProgram();
					isMoving = true;
				} else {
					JOptionPane.showMessageDialog(null, "You need to assign directories first!");
				}

			}
		});

		btnStopMoving.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isMoving) {
					FileMoverRun.shutDownTheProgram();
					isMoving = false;
				} else {
					JOptionPane.showMessageDialog(null, "You need to start the moving process first!");
				}
			}
		});

		txtSource.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				txtSource.setToolTipText("copy and paste directory path in this field");
			}
		});

	}

	public static String getSourcePath() {
		return sourcePath;
	}

	public static String getDestinationPath() {
		return destinationPath;
	}
}
