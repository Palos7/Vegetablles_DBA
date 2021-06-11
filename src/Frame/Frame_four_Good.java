package Frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Frame_four_Good{
	String id;
	
	//构造
	public Frame_four_Good(String id,JTable table) {//传入登录者的ID
		this.id= id;
		JFrame frame = new JFrame();
		Panel_Good panel_good = new Panel_Good(table);
		frame.add(panel_good);//添加面板，在面板上设计
		
		frame.setLayout(null);
		frame.setBackground(Color.WHITE);
		frame.setBounds(200, 100, 450, 600);
		frame.setResizable(false);//窗口大小固定
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//关闭设置
		frame.setVisible(true);//设置可见
		
		//点赞差评按钮
		panel_good.Bp.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	Function_update u = new Function_update();
		    	String temp = panel_good.TPid.getText();
		    	u.PsyGood(temp,"good");
		    	JOptionPane.showMessageDialog(null, "感谢您的评价");  
			}
		});	
		panel_good.Bp2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	Function_update u = new Function_update();
		    	String temp = panel_good.TPid.getText();
		    	u.PsyGood(temp,"bad");
		    	JOptionPane.showMessageDialog(null, "感谢您的反馈");  
			}
		});	
		panel_good.Bm.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	Function_update u = new Function_update();
		    	String temp = panel_good.TMid.getText();
		    	u.MercGood(temp,"good");
		    	JOptionPane.showMessageDialog(null, "感谢您的评价"); 
			}
		});	
		panel_good.Bm2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	Function_update u = new Function_update();
		    	String temp = panel_good.TMid.getText();
		    	u.MercGood(temp,"bad");
		    	JOptionPane.showMessageDialog(null, "感谢您的反馈");
			}
		});	
		
		//搜索按钮
		panel_good.Bs.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	//切换下一个界面+
		    	frame.setVisible(false);
		    	String temp = panel_good.TOid.getText();
		    	Function_Select s = new Function_Select();
		    	Object[][] indata = s.Ghisorder(id,temp);
		    	Object[] intitle= new Object[]{"订单ID","用户ID","配送员ID","商家ID",
                                        "内容","重量","价格","出发地","目的地","时间"};
		    	JTable tablex = new JTable(indata,intitle);
		    	new Frame_four_Good(id,tablex);
			}
		});	
		
		//切换界面按键
		panel_good.back.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	//切换上一个界面
		    	frame.setVisible(false);
		    	new Frame_three_Mine(id, new JTable());
			}
		});	
	}
}

class Panel_Good extends JPanel{
	//变量域
	JScrollPane scroll;
	JButton back,next;//切换界面按键
	JTextField TOid;
	JButton Bs;//搜索按键
	
	JTextField TPid,TMid;//为配送员和商家点赞
	JButton Bp,Bm;
	JButton Bp2,Bm2;
	
	//无参构造
	public Panel_Good(JTable table) {
		this.setLayout(null);//绝对布局
		this.setBounds(0, 0, 450, 600);
		this.setBackground(Color.WHITE);
		
		//搜索区域绘制
		TOid = new JTextField();
		TOid.setBounds(140,120,150,25);
		this.add(TOid);
		
		Bs = new JButton("搜索");
		Bs.setBounds(300,120,100,25);
		this.add(Bs);
		
		//调用Function_Select取出数据，并绘制table
		table.setBounds(0,0,350,450);
		scroll=new JScrollPane(table);
		scroll.setBounds(50, 150, 350, 300);
		
		this.add(scroll);
		
		//点赞区
		TPid = new JTextField();
		TPid.setBounds(50,490,90,25);
		this.add(TPid);
		Bp = new JButton("点赞");
		Bp.setBounds(150,475,60,25);
		this.add(Bp);
		Bp2 = new JButton("差评");
		Bp2.setBounds(150,505,60,25);
		this.add(Bp2);
		
		TMid = new JTextField();
		TMid.setBounds(240,490,90,25);
		this.add(TMid);
		Bm = new JButton("点赞");
		Bm.setBounds(340,475,60,25);
		this.add(Bm);
		Bm2 = new JButton("差评");
		Bm2.setBounds(340,505,60,25);
		this.add(Bm2);
		
		//前进返回按钮
		back = new JButton("返回");
		back.setBackground(new Color(152,224,69,200));
		back.setBounds(10,15,80,25);
		this.add(back);
	}
	
	//画笔绘制界面
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);//清屏
		
		g.setFont(new Font("微软雅黑",Font.BOLD,55));
		g.setColor(new Color(39,72,98,210));
		g.drawString("点赞界面",110,80);
		
		g.setFont(new Font("微软雅黑",Font.BOLD,15));
		g.drawString("输入订单ID",55,135);
		g.drawString("为配送员点赞",50,475);
		g.drawString("为商家点赞",240,475);
	}
}