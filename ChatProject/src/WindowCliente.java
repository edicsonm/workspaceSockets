import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class WindowCliente extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JButton connect = new JButton();
	private JTextArea chartRoom = new JTextArea();
	private JTextField chatMessage = new JTextField();
	
	public static void main(String[] args) {
		new WindowCliente();
	}
	
	public WindowCliente(){
		connect.setText("Connnect");
		connect.setText("Acceder");
		connect.setMnemonic(KeyEvent.VK_A);
		connect.setBounds(new Rectangle(10, 10, 100, 30));
		connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connect_actionPerformed(e);
			}
		});
		
		this.getContentPane().setLayout(null);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.getContentPane().add(connect, null);
		this.setTitle("Chat Client");
		this.setResizable(true);
		this.setVisible(true);
		this.setSize(300,300);
	}
	
	private void Connect_actionPerformed(ActionEvent e) {
		System.out.println("Presiona botton connect ... ");
	}

}
