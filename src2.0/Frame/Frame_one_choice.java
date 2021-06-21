package Frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

//该界面提供给用户选择接单jd还是下单dd，或者说是“我的”界面
public class Frame_one_choice{
	String id;
	//无参构造
	public Frame_one_choice(String id) {
		this.id = id;
		
		//界面绘制区
		JFrame frame = new JFrame();
		Panel_one panel_one = new Panel_one();
		frame.add(panel_one);//添加面板，在面板上设计
		
		frame.setLayout(null);
		frame.setBackground(Color.WHITE);
		frame.setBounds(200, 100, 450, 600);
		frame.setResizable(false);//窗口大小固定
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//关闭设置
		frame.setVisible(true);//设置可见
		
		//接单与下单按键处理
		panel_one.b1.addActionListener(new ActionListener() {//接单界面
		    public void actionPerformed(ActionEvent e) {
		    	//切换下一个界面
		    	frame.setVisible(false);
		    	new Frame_two_JD(id,"");
			}
		});
		panel_one.b2.addActionListener(new ActionListener() {//点单界面
		    public void actionPerformed(ActionEvent e) {
		    	//切换下一个界面
		    	frame.setVisible(false);
		    	//传入id 搜索模式 搜索ID，初始为空->默认显示全部订单
		    	new Frame_two_DD(id,"","");
			}
		});
		panel_one.b3.addActionListener(new ActionListener() {//我的订单界面
		    public void actionPerformed(ActionEvent e) {
		    	//切换下一个界面
		    	frame.setVisible(false);
		    	new Frame_three_Mine(id,new JTable());
			}
		});
	}
}

class Panel_one extends JPanel{
	//变量域
	JButton b1,b2,b3;
	
	//无参构造
	public Panel_one() {
		this.setLayout(null);//绝对布局
		this.setBounds(0, 0, 450, 600);
				
		//添加确定按钮
		b1 = new JButton("接单");
		b1.setBounds(175, 220, 100, 50);
		b1.setBackground(new Color(231,115,198,150));
		this.add(b1);
		
		b2 = new JButton("点单");
		b2.setBounds(175, 320, 100, 50);
		b2.setBackground(new Color(231,115,198,150));
		this.add(b2);
		
		b3 = new JButton("我的");
		b3.setBounds(175, 420, 100, 50);
		b3.setBackground(new Color(231,115,198,150));
		this.add(b3);
		
	}
	
	//画笔绘制界面
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);//清屏
		
		g.setFont(new Font("微软雅黑",Font.BOLD,55));
		g.setColor(new Color(39,72,98,210));
		g.drawString("Choice",120,120);
	}
}