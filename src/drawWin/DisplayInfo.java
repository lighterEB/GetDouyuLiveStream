package drawWin;

import getLiveInfo.GetLiveInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class DisplayInfo {
	JTextField entry;
	JTextField res;
	public static void main(String[] args) {
		
		DisplayInfo Ui = new DisplayInfo();
		Ui.show();
	}
	
	public void show() {
		JFrame f = new JFrame();
		f.setTitle("斗鱼直播源获取");
		f.setDefaultCloseOperation(3);
		f.setSize(340,120);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setLayout(new FlowLayout());
		JLabel label1 = new JLabel("房间号");
		entry = new JTextField(25);
		entry.addKeyListener((KeyListener) new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {
					
				}else {
					e.consume();
				}
			}
		});
		JLabel label2 = new JLabel("直播源");
		res = new JTextField(25);
		res.setEditable(false);
		
		JButton getinfo = new JButton("获取");
		getinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				String send = entry.getText();
				System.out.println(send);
				if(!send.isBlank()) {
					String rec = GetLiveInfo.main(send);
					res.setText(rec);
				}
					
			}
		});
		
		f.add(label1);
		f.add(entry);
		f.add(label2);
		f.add(res);
		f.add(getinfo);
		f.setVisible(true);
	}
}
