package Frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

//历史订单查询
public class Frame_threePointfive_History{
	String id;
	
	//无参构造
	public Frame_threePointfive_History(String id,JTable table) {
		this.id= id;
		JFrame frame = new JFrame();
		Panel_History panel_his = new Panel_History(id,table);
		frame.add(panel_his);//添加面板，在面板上设计
		
		frame.setLayout(null);
		frame.setBackground(Color.WHITE);
		frame.setBounds(200, 100, 450, 600);
		frame.setResizable(false);//窗口大小固定
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//关闭设置
		frame.setVisible(true);//设置可见
		
		//历史订单接收
		panel_his.Bjd.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	frame.setVisible(false);
		    	Function_Select s = new Function_Select();
		    	Object[][] indata = s.hisorder(id,"psy");
		    	Object[] intitle= new Object[]{"订单ID","用户ID","配送员ID","商家ID",
                                        "内容","重量","价格","出发地","目的地","时间"};
		    	JTable tablex = new JTable(indata,intitle);
		    	new Frame_threePointfive_History(id, tablex);
			}
		});
		panel_his.Bdd.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	frame.setVisible(false);
		    	Function_Select s = new Function_Select();
		    	Object[][] indata = s.hisorder(id,"yh");
		    	Object[] intitle= new Object[]{"订单ID","用户ID","配送员ID","商家ID",
                                        "内容","重量","价格","出发地","目的地","时间"};
		    	JTable tablex = new JTable(indata,intitle);
		    	new Frame_threePointfive_History(id, tablex);
			}
		});
		
		//切换界面按键
		panel_his.back.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	//切换上一个界面
		    	frame.setVisible(false);
		    	new Frame_one_choice(id);
			}
		});
		panel_his.next.addActionListener(new ActionListener() {
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

class Panel_History extends JPanel{
	//变量域
	JScrollPane scroll;
	JButton back,next;
	JTable table;
	
	JButton Bjd,Bdd;
	
	//无参构造
	public Panel_History(String id,JTable table) {
		this.setLayout(null);//绝对布局
		this.setBounds(0, 0, 450, 600);
		
		//订单选择区
		table.setBounds(0,0,350,450);
		scroll=new JScrollPane(table);
		scroll.setBounds(50, 150, 350, 350);
		this.add(scroll);	
		
		//接单下单按钮
		Bjd = new JButton("历史接单");
		Bjd.setBounds(100,110, 100, 25);
		this.add(Bjd);
		Bdd = new JButton("历史点单");
		Bdd.setBounds(250,110, 100, 25);
		this.add(Bdd);
		
		//前进返回按钮
		back = new JButton("Back");
		back.setBounds(10,15,80,25);
		back.setBackground(new Color(152,224,69,200));
		this.add(back);
		
		next = new JButton("Next");
		next.setBounds(345, 15, 80, 25);
		next.setBackground(new Color(152,224,69,200));
		this.add(next);
	}
	
	//画笔绘制界面
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);//清屏
		g.setFont(new Font("微软雅黑",Font.BOLD,55));
		g.setColor(new Color(39,72,98,210));
		g.drawString("历史订单",110,80);
	}
}