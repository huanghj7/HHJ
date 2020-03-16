package util;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class AddO extends JPanel implements ActionListener{//JFrame 是Java的窗体类,实现 ActionListener动作监听窗体 接口，
	JTextField 办事处编号,地址,电话号码,传真号码;
	JButton 录入;
	
public AddO(){
	try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
	catch(Exception e){System.err.println("不能设置外观:   "+e);}
	
	办事处编号=new JTextField(12);
	地址=new JTextField(12);
    电话号码=new JTextField(12);
    传真号码=new JTextField(12);
	录入=new JButton("录入");
	录入.addActionListener(this);
	
	Box box1=Box.createHorizontalBox();//横放box
	Box box2=Box.createHorizontalBox();
	Box box3=Box.createHorizontalBox();
	Box box4=Box.createHorizontalBox();
    Box box5=Box.createHorizontalBox();
	box1.add(new JLabel("办事处编号:"));
	box1.add(办事处编号);
	box2.add(new JLabel("地址:"));
	box2.add(地址);
    box3.add(new JLabel("电话号码:"));
	box3.add(电话号码);
	box5.add(new JLabel("传真号码:"));
	box5.add(传真号码);
	box4.add(录入);
	Box boxH=Box.createVerticalBox();//竖放box
	boxH.add(box1);
	boxH.add(box2);
	boxH.add(box3);
    boxH.add(box5);
	boxH.add(box4);
	boxH.add(Box.createVerticalGlue());
	JPanel messPanel=new JPanel();
	messPanel.add(boxH);
	setLayout(new BorderLayout());
	add(messPanel,BorderLayout.CENTER);
	validate();
}
public void actionPerformed(ActionEvent c){
	Object obj=c.getSource();
	if(obj==录入){
		if(办事处编号.getText().equals("")||电话号码.getText().equals("")){
			JOptionPane.showMessageDialog(this,"必填信息请填满再录入！" );
		}
		Statement stmt=null;
		ResultSet rs1=null;
		String sql,sql1;
		    sql1="select * from Office where officeNo='"+办事处编号.getText()+"'";
		    sql="insert into Office values('"+办事处编号.getText()+"','"+地址.getText()+"','"+电话号码.getText()+"','"+传真号码.getText()+"')";
	   try{
		   Connection dbConn1=CONN();
			stmt=(Statement)dbConn1.createStatement();
			rs1=stmt.executeQuery(sql1);
			if(rs1.next()){JOptionPane.showMessageDialog(this,"该编号已经存在，无法添加");}
			else{
			stmt.executeUpdate(sql);	
			JOptionPane.showMessageDialog(this,"添加成功");
			}		
			rs1.close();
			
			stmt.close();
	   }
	   catch(SQLException e){
		   JOptionPane.showMessageDialog(this,e.getMessage());
		   }
	}
}

//连接数据库方法
public static Connection CONN(){
    String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";   //加载JDBC驱动
   String dbURL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=ClientT";
       String userName = "sa";   //默认用户名
   String userPwd = "12345";   //密码
   Connection dbConn=null;
   try {
   Class.forName(driverName);
   dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
   System.out.println("Connection Successful!");   //如果连接成功 控制台输出Connection Successful!
   } catch (Exception e) {
   e.printStackTrace();
   }
   return dbConn;
}
}