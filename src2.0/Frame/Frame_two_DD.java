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

//点单界面实现
//通过在最下方输入特定格式数据模拟用户点单
public class Frame_two_DD{
	String id;
	
	//构造，传入登录者ID 模式，还有ID
	public Frame_two_DD(String id,String model,String searchID) {
		this.id= id;
		JFrame frame = new JFrame();
		Panel_DD panel_dd = new Panel_DD(model,searchID);
		frame.add(panel_dd);//添加面板，在面板上设计
		
		frame.setLayout(null);
		frame.setBackground(Color.WHITE);
		frame.setBounds(200, 100, 450, 600);
		frame.setResizable(false);//窗口大小固定
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//关闭设置
		frame.setVisible(true);//设置可见
		
		//搜索按钮
		panel_dd.Bsearch_Mer.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	//按商家搜索
		    	frame.setVisible(false);
		    	String x = panel_dd.TMer.getText();
		    	new Frame_two_DD(id,"Merc_ID",x);
			}
		});
		panel_dd.Bsearch_Veg.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	//按商家搜索
		    	frame.setVisible(false);
		    	String x = panel_dd.Tveg.getText();
		    	new Frame_two_DD(id,"Veg_ID",x);
			}
		});
		
		//点单
		panel_dd.Bdd.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	//按商家搜索，暂时只支持一次在一个商家点单
		    	String temp=panel_dd.Tdd.getText();
		    	Function_add add = new Function_add();
		    	add.myOrder(id,temp);//产生订单
		    	JOptionPane.showMessageDialog(null, "点单成功");  
			}
		});
		
		//切换界面按键
		panel_dd.back.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	//切换上一个界面
		    	frame.setVisible(false);
		    	new Frame_one_choice(id);
			}
		});
		panel_dd.next.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	//切换下一个界面
		    	frame.setVisible(false);
		    	new Frame_three_Mine(id,new JTable());
			}
		});
		
	}
}

class Panel_DD extends JPanel{
	//变量域
	JScrollPane scroll;
	JButton back,next;//切换界面按键
	JButton Bsearch_Veg,Bsearch_Mer;//搜索按键
	JTextField Tveg,TMer;
	
	JTable table;//表格绘制
	Object[][] indata;
	Object[] intitle;
	
	JTextField Tdd;//text_点单
	JButton Bdd;
	
	//无参构造
	public Panel_DD(String model,String searchID) {
		this.setLayout(null);//绝对布局
		this.setBounds(0, 0, 450, 600);
		this.setBackground(Color.WHITE);
		//搜索区域绘制
		Tveg=new JTextField();
		Tveg.setBounds(50,160, 100,25);
		this.add(Tveg);
		Bsearch_Veg = new JButton("搜索");
		Bsearch_Veg.setBounds(160,160,60,25);
		this.add(Bsearch_Veg);
		
		TMer=new JTextField();
		TMer.setBounds(240,160, 100,25);
		this.add(TMer);
		Bsearch_Mer = new JButton("搜索");
		Bsearch_Mer.setBounds(350,160,60,25);
		this.add(Bsearch_Mer);
		
		//调用Function_Select取出数据，并绘制table
		Function_Select s = new Function_Select();
		indata=s.vegetables(model,searchID);
		intitle= new Object[]{"菜品ID","菜品","商家ID","单价"};
		table=new JTable(indata,intitle);
		table.setBounds(0,0,350,450);
		scroll=new JScrollPane(table);
		scroll.setBounds(50, 200, 350, 250);
		
		this.add(scroll);
		
		//添加购物车，下单机制
		Tdd = new JTextField();
		Tdd.setBounds(50,490,250,26);
		this.add(Tdd);
		Bdd = new JButton("确定");
		Bdd.setBounds(320,490,80,25);
		this.add(Bdd);
		
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
		g.drawString("点单界面",100,80);
		
		g.setFont(new Font("微软雅黑",Font.BOLD,15));
		g.drawString("搜索菜品",50,150);
		g.drawString("搜索商家",240,150);
		g.drawString("请输入蔬菜ID,商家ID,购买数量后点击确定",50,470);
	}
}