package window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.ConnectException;
import java.net.URL;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.DefaultCaret;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import connected.CheckConnected;
import socket.ClientSocket;


public class Cliente extends JFrame {
	
	private Font fuente = new Font("Dialog", 1, 13);
	private static final long serialVersionUID = -891913687793648474L;
	
	/*Components to create Panel to login*/
	private JPanel panelLogin = new JPanel();
	private JTextField user = new JTextField();
	private JTextField password = new JTextField();
	private JButton login = new JButton();
	private JLabel labelUser = new JLabel();
	private JLabel labelPassword = new JLabel();
	
	/*Components to create Panel to have a chat*/
	private JTabbedPane tabbedPaneActiveChats = new JTabbedPane();
	
	private JPanel panelChat = new JPanel();
	public JTextField chatRoomDestiny = new JTextField();
	private JTextField chatMessage = new JTextField();
	private JButton send = new JButton();
	public JTextArea chatRoom = new JTextArea();
	JScrollPane scrollPane = new JScrollPane();
	
	/*Components to create Panel to See connected users*/
	private JPanel panelConnected = new JPanel();
	private JList list;
	private DefaultListModel modelListConnected;
	private JScrollPane jScrollPaneListConnected;
	
	URL Ierror = this.getClass().getResource("images/error.png");
	
	/*To create Socket*/
	ClientSocket clientSocket;
	private String id;
	
	public Cliente() {
//		this.getContentPane().add(createTabs(), null);
		this.getContentPane().add(createLogin(), null);
//		this.getContentPane().add(createChat(), null);
//		id = String.valueOf(connectServer());
		this.setTitle("Chat Client");
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("Usuario cierra conexiones ....");
				if(clientSocket != null) clientSocket.stopClient(id);
				System.exit(0);
			}
		});
		this.setResizable(false);
		this.setVisible(true);
		this.setSize(500,300);
	}
	
	public JTabbedPane createTabs(){
		tabbedPaneActiveChats.setTabPlacement(JTabbedPane.RIGHT);
		tabbedPaneActiveChats.setBounds(new Rectangle(0, 10, 345, 390));
		tabbedPaneActiveChats.setOpaque(false);
		tabbedPaneActiveChats.addTab("CLIENTE2", createChat(new JPanel()));
		tabbedPaneActiveChats.addTab("CLIENTE3", createChat(new JPanel()));
		return tabbedPaneActiveChats;
	}
	
	public JPanel createLogin() {
		
		labelUser.setText("User:");
		labelUser.setBounds(new Rectangle(30, 100, 150, 30));
		user.setBounds(new Rectangle(180, 100, 150, 30));
		user.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		    	Send_actionPerformed(e);
		    }
		});
		
		labelPassword.setText("Password:");
		labelPassword.setBounds(new Rectangle(30, 140, 150, 30));
		password.setBounds(new Rectangle(180, 140, 150, 30));
		password.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		    	Send_actionPerformed(e);
		    }
		});
		
		login.setText("Login");
		login.setMnemonic(KeyEvent.VK_A);
		login.setBounds(new Rectangle(200, 180, 100, 30));
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login_actionPerformed(e);
			}
		});
		
		user.setText("edicson");
		password.setText("edicson01");
		
		panelLogin.add(labelUser);
		panelLogin.add(user);
		panelLogin.add(labelPassword);
		panelLogin.add(password);
		panelLogin.add(login);
		
		panelLogin.setBounds(new Rectangle(0, 0, 510, 300));
		panelLogin.setBackground(new Color(115, 165, 255));
		panelLogin.setLayout(null);
		
		return panelLogin;
	}
	
	public JPanel createChat(JPanel jPanel){
		
		chatRoom.setFont(fuente);
		chatRoom.setBounds(new Rectangle(10, 10,480, 400));
		chatRoom.setCaret(new DefaultCaret());
		chatRoom.setVerifyInputWhenFocusTarget(false);
		chatRoom.setEditable(false);
		 
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(250, 800));
		scrollPane.setBounds(new Rectangle(10,10, 480, 200));
		scrollPane.getViewport().setLayout(null);
		scrollPane.getViewport().add(chatRoom, null);
		
		chatRoomDestiny.setBounds(new Rectangle(10, 230, 50, 30));

		chatMessage.setBounds(new Rectangle(70, 230, 310, 30));
		chatMessage.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		    	Send_actionPerformed(e);
		    }
		});
		
		send.setText("Send");
		send.setMnemonic(KeyEvent.VK_A);
		send.setBounds(new Rectangle(390, 230, 100, 30));
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChatMessage_actionPerformed(e);
			}
		});
		
		this.getContentPane().setLayout(null);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		jPanel.setBounds(new Rectangle(-5, 0, 500, 300));
		jPanel.setBackground(new Color(115, 165, 255));
		jPanel.setLayout(null);
		
		jPanel.add(scrollPane);
		jPanel.add(chatRoomDestiny);
		jPanel.add(chatMessage);
		jPanel.add(send);
		return jPanel;
		
	}
	
	public JPanel createChat(){
		
//		connectServer();
//		clientSocket.start();
		
		chatRoom.setFont(fuente);
		chatRoom.setBounds(new Rectangle(10, 10,480, 400));
		chatRoom.setCaret(new DefaultCaret());
		chatRoom.setVerifyInputWhenFocusTarget(false);
		chatRoom.setEditable(false);
		 
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(250, 800));
		scrollPane.setBounds(new Rectangle(10,10, 480, 200));
		scrollPane.getViewport().setLayout(null);
		scrollPane.getViewport().add(chatRoom, null);
		
		chatRoomDestiny.setBounds(new Rectangle(10, 230, 50, 30));

		chatMessage.setBounds(new Rectangle(70, 230, 310, 30));
		chatMessage.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		    	Send_actionPerformed(e);
		    }
		});
		
		send.setText("Send");
		send.setMnemonic(KeyEvent.VK_A);
		send.setBounds(new Rectangle(390, 230, 100, 30));
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChatMessage_actionPerformed(e);
			}
		});
		
		this.getContentPane().setLayout(null);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		panelChat.setBounds(new Rectangle(-5, 0, 500, 300));
		panelChat.setBackground(new Color(115, 165, 255));
		panelChat.setLayout(null);
		
		panelChat.add(scrollPane);
		panelChat.add(chatRoomDestiny);
		panelChat.add(chatMessage);
		panelChat.add(send);
		return panelChat;
		
	}

	public JPanel createConnected(){
		modelListConnected = new DefaultListModel();
		
		list = new JList(modelListConnected);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) System.out.println("Selecciono1: " + list.getSelectedIndex());
			}
		});
		
		jScrollPaneListConnected = new JScrollPane(list);
		jScrollPaneListConnected.setBounds(new Rectangle(10, 10, 180, 250));
		
		panelConnected.add(jScrollPaneListConnected);
		panelConnected.setBounds(new Rectangle(495, 0, 210, 280));
		panelConnected.setBackground(new Color(115, 165, 255));
		panelConnected.setLayout(null);
		return panelConnected;
	}
	
	public boolean connectServer() {
		boolean connected = false;
		try {
			clientSocket = new ClientSocket(this);
//			clientSocket.start();
			connected = true;
		} catch (ConnectException e){
			JOptionPane.showMessageDialog(
					this,
					"Server is not online",
					"Error", JOptionPane.PLAIN_MESSAGE,
					new ImageIcon(Ierror));
		} catch (IOException e) {
			e.printStackTrace();
			connected = false;
		}
		return connected;
	}

	private void login_actionPerformed(ActionEvent e) {
		try {
			if(connectServer()){
				if(clientSocket.isConected()){
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("typeMessage", "autentication");
					jsonObject.put("user", user.getText());
					jsonObject.put("password", password.getText());
					clientSocket.outputData.writeUTF(jsonObject.toString());
					
					String messageJson = clientSocket.inputData.readUTF();
					System.out.println("Respuesta a la autenticacion: " + messageJson);
					
					JSONParser parser = new JSONParser();
					Object obj = parser.parse(messageJson);
					JSONObject jSONObject = (JSONObject) obj;
					if(jSONObject.get("answer").toString().equalsIgnoreCase("ok")){
						id = jSONObject.get("idSession").toString();
						this.setTitle(this.getTitle() +"-"+id);
						this.getContentPane().remove(panelLogin);
						this.getContentPane().add(createChat(), null);
						this.getContentPane().add(createConnected(), null);
						this.setSize(700,300);
						this.repaint();
						clientSocket.start();
					}else if(jSONObject.get("answer").toString().equalsIgnoreCase("refuse")){
						JOptionPane.showMessageDialog(
								this,
								"User or password incorrect",
								"Error", JOptionPane.PLAIN_MESSAGE,
								new ImageIcon(Ierror));
					}
				}
			}
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void ChatMessage_actionPerformed(ActionEvent e) {
		try {
			chatRoom.append(id+":"+chatMessage.getText()+"\n");
//			clientSocket.outputData.writeUTF(id+":"+chatMessage.getText());
//			clientSocket.outputData.writeUTF(chatMessage.getText());
			clientSocket.outputData.writeUTF(processMessage(chatRoomDestiny.getText(),chatMessage.getText()).toString());
			chatMessage.setText("");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	private void Send_actionPerformed(ActionEvent e) {
		try {
			chatRoom.append(id+":"+chatMessage.getText()+"\n");
//			clientSocket.outputData.writeUTF(id+":"+chatMessage.getText());
//			clientSocket.outputData.writeUTF(chatMessage.getText());
			clientSocket.outputData.writeUTF(processMessage(chatRoomDestiny.getText(),chatMessage.getText()).toString());
			chatMessage.setText("");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public JSONObject processMessage(String destiny, String message){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("IDDestiny", destiny);
		jsonObject.put("origen", id);
		jsonObject.put("typeMessage", "message");
		jsonObject.put("message", message);
		return jsonObject;
	}
	
//	public void getConnected() {
//		try {
//			JSONObject jsonObject = new JSONObject();
//			jsonObject.put("typeMessage", "getConnected");
//			clientSocket.outputData.writeUTF(jsonObject.toString());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	public void valueChanged(ListSelectionEvent e) {
		
	}
	
	public static void main(String[] args) {
		new Cliente();
	}

	public DefaultListModel getModelListConnected() {
		return modelListConnected;
	}

	public void setModelListConnected(DefaultListModel modelListConnected) {
		this.modelListConnected = modelListConnected;
	}

	public JTextArea getChatRoom() {
		return chatRoom;
	}

	public void setChatRoom(JTextArea chatRoom) {
		this.chatRoom = chatRoom;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
