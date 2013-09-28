import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JTextArea;


public class BeTheWeb {

	private JFrame frame;
	private JTextArea tfOutput;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BeTheWeb window = new BeTheWeb();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BeTheWeb() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// Configuring lables that we dont need access to
		JLabel step1Lable = new JLabel("1) Start The server");
		step1Lable.setFont(new Font("Tahoma", Font.PLAIN, 18));
		step1Lable.setBounds(10, 11, 160, 38);
		frame.getContentPane().add(step1Lable);
		
		JLabel step2Lable = new JLabel("2) Config the Client");
		step2Lable.setFont(new Font("Tahoma", Font.PLAIN, 18));
		step2Lable.setBounds(10, 58, 160, 38);
		frame.getContentPane().add(step2Lable);
		// End lables

		tfOutput = new JTextArea();
		tfOutput.setBounds(10, 110, 414, 140);
		frame.getContentPane().add(tfOutput);
		
		JButton btStartServer = new JButton("Start");
		btStartServer.setBounds(310, 20, 114, 27);
		frame.getContentPane().add(btStartServer);
		btStartServer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				tfOutput.append("Console: Server Starting ...\n");
			}
		});
		
		JLabel serverStatusLable = new JLabel("Status: Off");
		serverStatusLable.setBounds(212, 26, 77, 14);
		frame.getContentPane().add(serverStatusLable);
		
		
		JButton btOpenClientConfig = new JButton("Config");
		btOpenClientConfig.setBounds(310, 67, 114, 27);
		frame.getContentPane().add(btOpenClientConfig);
		
		btOpenClientConfig.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
	}
}
