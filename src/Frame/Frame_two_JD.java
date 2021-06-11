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

//接单界面
//通过最下方文本框实现数据输入，然后按下按键实现接单
public class Frame_two_JD{
	String id;
	
	//无参构造
	public Frame_two_JD(String id,String model) {
		this.id= id;
		JFrame frame = new JFrame();
		Panel_JD panel_jd = new Panel_JD(id);
		frame.add(panel_jd);//添加面板，在面板上设计
		
		frame.setLayout(null);
		frame.setBackground(Color.WHITE);
		frame.setBounds(200, 100, 450, 600);
		frame.setResizable(false);//窗口大小固定
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//关闭设置
		frame.setVisible(true);//设置可见
		
		//选择订单接收
		panel_jd.Bjd.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	//选择订单接收，为myorder表中psy_id更新为用户ID
		    	String orderids = panel_jd.Tjd.getText();
		    	for(int i=0;i<orderids.split(",").length;i++) {//空格做分隔符
		    		String temp = orderids.split(",")[i];
		    		System.out.println(temp);
		    		Function_update f = new Function_update();
		    		f.myorder(id,temp);//更新数据
		    		JOptionPane.showMessageDialog(null, "接单成功");  
		    	}
			}
		});
		
		//切换界面按键
		panel_jd.back.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	//切换上一个界面
		    	frame.setVisible(false);
		    	new Frame_one_choice(id);
			}
		});
		panel_jd.next.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	//切换下一个界面
		    	frame.setVisible(false);
		    	new Frame_three_Mine(id,new JTable());
			}
		});
		
	}
}

class Panel_JD extends JPanel{
	//变量域
	JScrollPane scroll;
	JButton back,next;
	JTable table;
	
	JTextField Tjd;
	JButton Bjd;
	
	Object[][] indata;
	Object[] intitle;
	
	//无参构造
	public Panel_JD(String id) {
		this.setLayout(null);//绝对布局
		this.setBounds(0, 0, 450, 600);
		
		//订单选择区
		Function_Select s = new Function_Select();
		indata=s.myorder(id,"");
		intitle= new Object[]{"Order_ID","weight","Price",
				              "Origin","Destination"};
		table=new JTable(indata,intitle);
		table.setBounds(0,0,350,450);
		
		scroll=new JScrollPane(table);
		scroll.setBounds(50, 180, 350, 270);
		
		this.add(scroll);
		
		//接单区
		Tjd = new JTextField();
		Tjd.setBounds(50,490,250,26);
		this.add(Tjd);
		Bjd = new JButton("确定");
		Bjd.setBounds(320,490,80,25);
		this.add(Bjd);
		
		//前进返回按钮
		back = new JButton("返回");
		back.setBounds(10,15,80,25);
		back.setBackground(new Color(152,224,69,200));
		this.add(back);
		
		next = new JButton("下一页");
		next.setBackground(new Color(152,224,69,200));
		next.setBounds(345, 15, 80, 25);
		this.add(next);
	}
	
	//画笔绘制界面
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);//清屏
		g.setFont(new Font("微软雅黑",Font.BOLD,55));
		g.setColor(new Color(39,72,98,210));
		g.drawString("接单界面",110,80);
		
		g.setFont(new Font("微软雅黑",Font.BOLD,15));
		g.drawString("请输入订单编号后点击确定",50,475);
	}
}