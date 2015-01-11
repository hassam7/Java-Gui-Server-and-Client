package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.GridLayout;

import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.DropMode;
import javax.swing.BoxLayout;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JTextPane;
import javax.swing.JButton;

import com.sun.net.httpserver.Authenticator.Success;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Driver extends JFrame {

	private JPanel contentPane;
	private JTextField txtFieldPort;
	private JLabel lblMessageFor;
	private JTextField txtFieldMessageFor;

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
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 641, 389);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{613, 0};
		gbl_contentPane.rowHeights = new int[]{21, 21, 21, 21, 21, 21, 21, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblServerAddress = new JLabel("Server Address");
		GridBagConstraints gbc_lblServerAddress = new GridBagConstraints();
		gbc_lblServerAddress.fill = GridBagConstraints.BOTH;
		gbc_lblServerAddress.insets = new Insets(0, 0, 5, 0);
		gbc_lblServerAddress.gridx = 0;
		gbc_lblServerAddress.gridy = 0;
		contentPane.add(lblServerAddress, gbc_lblServerAddress);
		final JTextField txtFieldAddress = new JTextField();
		GridBagConstraints gbc_txtFieldAddress = new GridBagConstraints();
		gbc_txtFieldAddress.fill = GridBagConstraints.BOTH;
		gbc_txtFieldAddress.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldAddress.gridx = 0;
		gbc_txtFieldAddress.gridy = 1;
		contentPane.add(txtFieldAddress, gbc_txtFieldAddress);
		
		JLabel lblServerPort = new JLabel("Server Port");
		GridBagConstraints gbc_lblServerPort = new GridBagConstraints();
		gbc_lblServerPort.fill = GridBagConstraints.BOTH;
		gbc_lblServerPort.insets = new Insets(0, 0, 5, 0);
		gbc_lblServerPort.gridx = 0;
		gbc_lblServerPort.gridy = 2;
		contentPane.add(lblServerPort, gbc_lblServerPort);
		
		txtFieldPort = new JTextField();
		GridBagConstraints gbc_txtFieldPort = new GridBagConstraints();
		gbc_txtFieldPort.fill = GridBagConstraints.BOTH;
		gbc_txtFieldPort.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldPort.gridx = 0;
		gbc_txtFieldPort.gridy = 3;
		contentPane.add(txtFieldPort, gbc_txtFieldPort);
		txtFieldPort.setColumns(10);
		
		lblMessageFor = new JLabel("Message For");
		GridBagConstraints gbc_lblMessageFor = new GridBagConstraints();
		gbc_lblMessageFor.fill = GridBagConstraints.BOTH;
		gbc_lblMessageFor.insets = new Insets(0, 0, 5, 0);
		gbc_lblMessageFor.gridx = 0;
		gbc_lblMessageFor.gridy = 4;
		contentPane.add(lblMessageFor, gbc_lblMessageFor);
		
		txtFieldMessageFor = new JTextField();
		GridBagConstraints gbc_txtFieldMessageFor = new GridBagConstraints();
		gbc_txtFieldMessageFor.fill = GridBagConstraints.BOTH;
		gbc_txtFieldMessageFor.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldMessageFor.gridx = 0;
		gbc_txtFieldMessageFor.gridy = 5;
		contentPane.add(txtFieldMessageFor, gbc_txtFieldMessageFor);
		txtFieldMessageFor.setColumns(10);
		
		JLabel lblMessage = new JLabel("Message Text");
		GridBagConstraints gbc_lblMessage = new GridBagConstraints();
		gbc_lblMessage.insets = new Insets(0, 0, 5, 0);
		gbc_lblMessage.fill = GridBagConstraints.BOTH;
		gbc_lblMessage.gridx = 0;
		gbc_lblMessage.gridy = 6;
		contentPane.add(lblMessage, gbc_lblMessage);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 7;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		final JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		final JLabel lblStatus = new JLabel("Status: Ready!");
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.insets = new Insets(0, 0, 5, 0);
		gbc_lblStatus.gridx = 0;
		gbc_lblStatus.gridy = 8;
		contentPane.add(lblStatus, gbc_lblStatus);
		
		JButton btnSend = new JButton("Send!");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String host = txtFieldAddress.getText();
				int port	   = Integer.parseInt(txtFieldPort.getText());
				String destination = txtFieldMessageFor.getText();
				String message			= textArea.getText();
				boolean success=true;
				try{
					ClientRelay.start(host, port, destination, message);
				}catch(Exception ex){
					success=false;
				} finally{
					if(success){
						lblStatus.setText("Success!!");
					}else{

						lblStatus.setText("Error Occured. Please Try Again!!");
					}
				}
			}
		});
		GridBagConstraints gbc_btnSend = new GridBagConstraints();
		gbc_btnSend.gridx = 0;
		gbc_btnSend.gridy = 9;
		contentPane.add(btnSend, gbc_btnSend);
		
		
		
	}

}
