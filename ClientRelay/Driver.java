package ClientRelay;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Driver extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtServerAddress;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Driver frame = new Driver();
		frame.setVisible(true);
		
	}

	/**
	 * Create the frame.
	 */
	public Driver() {
		setTitle("Relay Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 521, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblYourName = new JLabel("Your Name");
		GridBagConstraints gbc_lblYourName = new GridBagConstraints();
		gbc_lblYourName.insets = new Insets(0, 0, 5, 5);
		gbc_lblYourName.gridx = 1;
		gbc_lblYourName.gridy = 1;
		contentPane.add(lblYourName, gbc_lblYourName);
		
		txtName = new JTextField();
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.gridwidth = 6;
		gbc_txtName.insets = new Insets(0, 0, 5, 5);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 3;
		gbc_txtName.gridy = 1;
		contentPane.add(txtName, gbc_txtName);
		txtName.setColumns(10);
		
		JLabel lblServerAddress = new JLabel("Server Address");
		GridBagConstraints gbc_lblServerAddress = new GridBagConstraints();
		gbc_lblServerAddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblServerAddress.gridx = 1;
		gbc_lblServerAddress.gridy = 3;
		contentPane.add(lblServerAddress, gbc_lblServerAddress);
		
		txtServerAddress = new JTextField();
		GridBagConstraints gbc_txtServerAddress = new GridBagConstraints();
		gbc_txtServerAddress.gridwidth = 6;
		gbc_txtServerAddress.insets = new Insets(0, 0, 5, 5);
		gbc_txtServerAddress.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtServerAddress.gridx = 3;
		gbc_txtServerAddress.gridy = 3;
		contentPane.add(txtServerAddress, gbc_txtServerAddress);
		txtServerAddress.setColumns(10);
		
		JLabel lblMessages = new JLabel("Messages");
		GridBagConstraints gbc_lblMessages = new GridBagConstraints();
		gbc_lblMessages.insets = new Insets(0, 0, 5, 5);
		gbc_lblMessages.gridx = 1;
		gbc_lblMessages.gridy = 5;
		contentPane.add(lblMessages, gbc_lblMessages);
		
		final JTextArea textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridwidth = 6;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 3;
		gbc_textArea.gridy = 5;
		contentPane.add(textArea, gbc_textArea);
		
		JButton btnConnect = new JButton("Get Messages");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name,address;
				name=txtName.getText();
				address = txtServerAddress.getText();
				
				RelayClient.Start(name, address, textArea);
			}
		});
		GridBagConstraints gbc_btnConnect = new GridBagConstraints();
		gbc_btnConnect.gridwidth = 2;
		gbc_btnConnect.insets = new Insets(0, 0, 0, 5);
		gbc_btnConnect.gridx = 7;
		gbc_btnConnect.gridy = 7;
		contentPane.add(btnConnect, gbc_btnConnect);
	}

}
