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


//我的订单界面
public class Frame_three_Mine{
	String id;
	
	//构造
	public Frame_three_Mine(String id,JTable table) {//传入登录者的ID
		this.id= id;
		JFrame frame = new JFrame();
		Panel_Mine panel_mine = new Panel_Mine(table);
		frame.add(panel_mine);//添加面板，在面板上设计
		
		frame.setLayout(null);
		frame.setBackground(Color.WHITE);
		frame.setBounds(200, 100, 450, 600);
		frame.setResizable(false);//窗口大小固定
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//关闭设置
		frame.setVisible(true);//设置可见
		
		//完成订单按钮
		panel_mine.Bfo.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	//取得文本框的ID，将其myorder中的内容添加到historyorder
		    	String temp = panel_mine.Tfo.getText();
		    	System.out.print(temp);
		    	Function_add f = new Function_add();
		    	f.FinishOrder(temp);
		    	//完成单子加一
		    	String mercid = f.getMercID(temp);
		    	Function_update u = new Function_update();
		    	u.MorderNum(mercid);
		    	u.PorderNum(id);
		    	JOptionPane.showMessageDialog(null, "成功完成订单");
			}
		});
		
		//搜索按钮
		panel_mine.Bjds.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	//未完成的接单
		    	frame.setVisible(false);
		    	Function_Select s = new Function_Select();
		    	Object[][] indata= s.myorder(id,"jd");
				Object[] intitle= new Object[]{"订单ID","重量","价格","起点","终点"};
				JTable tablex=new JTable(indata,intitle);
				tablex.setBounds(0,0,350,450);
		    	new Frame_three_Mine(id,tablex);
			}
		});
		panel_mine.Bdds.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	//未完成的点单
		    	frame.setVisible(false);
		    	Function_Select s = new Function_Select();
		    	Object[][] indata=s.myorder(id,"dd");
				Object[] intitle= new Object[]{"订单ID","重量","价格","起点","终点"};
				JTable tablex=new JTable(indata,intitle);
				tablex.setBounds(0,0,350,450);
		    	new Frame_three_Mine(id,tablex);
			}
		});
		panel_mine.Bhd.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	//历史订单查阅
		    	frame.setVisible(false);
		    	Function_Select s = new Function_Select();
		    	Object[][] indata= s.hisorder(id,"");
				Object[] intitle= new Object[] {"订单ID","用户ID","配送员ID","商家ID",
	                                            "内容","重量","价格","出发地","目的地","时间"};
		    	JTable tablex = new JTable(indata,intitle);
		    	new Frame_threePointfive_History(id,tablex);//传入用户ID和历史订单表
			}
		});
		
		//切换界面按键
		panel_mine.back.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	//切换上一个界面
		    	frame.setVisible(false);
		    	new Frame_one_choice(id);
			}
		});
		panel_mine.next.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	//切换下一个界面
		    	frame.setVisible(false);
		    	Function_Select s = new Function_Select();
		    	Object[][] indata = s.Ghisorder(id,"");
		    	Object[] intitle= new Object[]{"订单ID","用户ID","配送员ID","商家ID",
                                        "内容","重量","价格","出发地","目的地","时间"};
		    	JTable tablex = new JTable(indata,intitle);
		    	new Frame_four_Good(id,tablex);
			}
		});
		
	}
}

class Panel_Mine extends JPanel{
	//变量域
	JScrollPane scroll;
	JButton back,next;//切换界面按键
	JButton Bdds,Bjds,Bhd;//搜索按键
	
	JTextField Tfo;//完成订单
	JButton Bfo;
	
	//无参构造
	public Panel_Mine(JTable table) {
		this.setLayout(null);//绝对布局
		this.setBounds(0, 0, 450, 600);
		this.setBackground(Color.WHITE);
		//搜索区域绘制
		Bdds = new JButton("点单搜索");
		Bdds.setBounds(50,120,100,25);
		this.add(Bdds);
		
		Bjds = new JButton("接单搜索");
		Bjds.setBounds(175,120,100,25);
		this.add(Bjds);
		
		Bhd = new JButton("历史订单");
		Bhd.setBounds(300,120,100,25);
		this.add(Bhd);
		
		//调用Function_Select取出数据，并绘制table
		table.setBounds(0,0,350,450);
		scroll=new JScrollPane(table);
		scroll.setBounds(50, 150, 350, 300);
		
		this.add(scroll);
		
		//添加购物车，下单机制
		Tfo = new JTextField();
		Tfo.setBounds(50,490,250,26);
		this.add(Tfo);
		Bfo = new JButton("完成");
		Bfo.setBounds(320,490,80,25);
		this.add(Bfo);
		
		//前进返回按钮
		back = new JButton("返回");
		back.setBackground(new Color(152,224,69,200));
		back.setBounds(10,15,80,25);
		this.add(back);
		
		next = new JButton("下一页");
		next.setBackground(new Color(152,224,69,200));
		next.setBounds(345, 15, 80, 25);
		this.add(next);
		//repaint();
	}
	
	//画笔绘制界面
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);//清屏
		
		g.setFont(new Font("微软雅黑",Font.BOLD,55));
		g.setColor(new Color(39,72,98,210));
		g.drawString("我的订单",110,80);
		
		g.setFont(new Font("微软雅黑",Font.BOLD,15));
		g.drawString("输入订单ID点击完成",50,475);
	}
}