package Frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Frame_Login{
	String id,password;//记录下登录ID和密码，暂时没做密码匹配
	
	//无参构造
	public Frame_Login() {
		JFrame frame = new JFrame();
		Panel_Login panel_login = new Panel_Login();
		frame.add(panel_login);//添加面板，在面板上设计
		
		frame.setLayout(null);
		frame.setBackground(Color.WHITE);
		frame.setBounds(200, 100, 450, 600);
		frame.setResizable(false);//窗口大小固定
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//关闭设置
		frame.setVisible(true);//设置可见
		
		panel_login.b1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	//取出ID和password
		    	id=panel_login.t1.getText();
		    	password=panel_login.t2.getText();
		    	
		    	//切换下一个界面
		    	frame.setVisible(false);
		    	System.out.println(id+" "+password);
		    	new Frame_one_choice(id);//保留下登录者的ID
			}
		});
	}
}

//通过Panel来处理界面的绘制，以后的Frame都是这样，便于管理和copy
class Panel_Login extends JPanel{
	//变量域
	JButton b1;
	JTextField t1;
	JPasswordField t2;
	
	//无参构造
	public Panel_Login() {
		this.setLayout(null);//绝对布局
		this.setBounds(0, 0, 450, 600);
		
		t1 = new JTextField();
		t1.setBounds(190, 230, 150, 25);
		this.add(t1);
		
		t2 = new JPasswordField();
		t2.setBounds(190, 270, 150, 25);
		this.add(t2);
		
		//添加确定按钮
		b1 = new JButton("Login");
		b1.setBounds(185, 320, 80, 30);
		b1.setBackground(new Color(231,115,198,150));
		this.add(b1);
		
	}
	
	//画笔绘制界面
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);//清屏
		
		g.setFont(new Font("微软雅黑",Font.BOLD,55));
		g.setColor(new Color(39,72,98,210));
		g.drawString("Welcome",95,120);
		
		g.setFont(new Font("微软雅黑",Font.BOLD,15));
		g.setColor(new Color(39,72,98,210));
		g.drawString("UserID：",100,248);
		g.drawString("PassWord：",100,288);
	}
}