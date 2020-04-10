
package telnet_chat;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.PrintWriter;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


	        
public class View extends JFrame implements Runnable, Action{
	
    private int height, width;
    private JPanel panel;
    private JTextField textField;
    private JLabel texts[];
    
    private String user;
    private PrintWriter out = null;
    private int count = 0;
    private boolean connect = false;

    View(PrintWriter out){
        this.out = out;
        texts = new JLabel[8];
    }
   
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("Enter")) {
			user = textField.getText();
			out.println(" ");
			startChat();
		}
		else if (connect){
			out.println(user + ": " + textField.getText());
	        setText(user + ": " + textField.getText());
	        textField.setText("");
		}
	}

	@Override
	public void run() {
    	height = Toolkit.getDefaultToolkit().getScreenSize().height;
        width = Toolkit.getDefaultToolkit().getScreenSize().width;
    	setTitle("Telnet-Chat");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 300, 200);
        
        panel = new JPanel();
        panel.setLayout(null);
        add(panel);
        
        JLabel label = new JLabel("Insert Username");
        label.setBounds(0, 0, 300, 50);
        panel.add(label);
        
        textField = new JTextField("Insert here");
        textField.setVisible(true);
        textField.setBounds(0, 50, 200, 150);
        panel.add(textField);
        
        JButton button = new JButton("Enter");
        button.setBounds(200, 50, 100, 150);
        panel.add(button);
        button.addActionListener(this);     
	}
	
	public void startChat() {
		remove(panel);
		setTitle(user);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 500, 500);
        setLayout(null);
        
        textField = new JTextField("Write HERE");
        textField.setVisible(true);
        textField.setBounds(0, 400, 500, 100);
        add(textField);
        
        textField.addActionListener(this);
	}

    public void setText(String mes) {
    	if (count == 8) {
    		for (JLabel text : texts)
    			remove(text);
			texts = new JLabel[9];
    		count = 0;
    	}
    	texts[count] = new JLabel(mes);
    	texts[count].setBounds(0, count * 50, 500, 50);
    	add(texts[count]);
    	repaint();
        count++;
    }
	
    public void setConnect() {
    	connect = true;
    }
    
	@Override
	public Object getValue(String arg0) {return null;}
	@Override
	public void putValue(String arg0, Object arg1) {}
}
