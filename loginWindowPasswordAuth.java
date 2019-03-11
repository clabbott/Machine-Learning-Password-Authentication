import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class loginWindowPasswordAuth {
	private JFrame frame;
	private JTextField txtOne;
	private JTextField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginWindowPasswordAuth window = new loginWindowPasswordAuth();
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
	public loginWindowPasswordAuth() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		//prompt the user for Username and Password
		txtOne = new JTextField();
		txtOne.setText("Username:");
		frame.getContentPane().add(txtOne);
		txtOne.setColumns(10);
		String username = txtOne.getText();
		//Password(maximum 5 processed characters)
		txtPassword = new JTextField();
		txtPassword.setText("Password:");
		frame.getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		String password = txtPassword.getText();		
		
		
		

		JButton btnSubmitUsername = new JButton("Submit Username and Password");
		
		frame.getContentPane().add(btnSubmitUsername);
		
	}


}
