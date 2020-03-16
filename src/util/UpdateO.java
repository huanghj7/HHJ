package util;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class UpdateO extends JPanel implements ActionListener{
	String save=null;
	JTextField 办事处编号1,办事处编号,地址,电话号码,传真号码;
	JButton 修改,查找;
	
public UpdateO(){
	try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
	catch(Exception e){System.err.println("不能设置外观:   "+e);}
	
	办事处编号1=new JTextField(12);
	办事处编号=new JTextField(12);
	地址=new JTextField(12);
	电话号码=new JTextField(12);
    传真号码=new JTextField(12);
	修改=new JButton("修改");
	查找=new JButton("查找");
	
	Box box1=Box.createHorizontalBox();//横放box
	Box box2=Box.createHorizontalBox();
	Box box3=Box.createHorizontalBox();
	Box box4=Box.createHorizontalBox();
	Box box5=Box.createHorizontalBox();
    Box box6=Box.createHorizontalBox();
	box1.add(new JLabel("办事处编号:",JLabel.CENTER));
	box1.add(办事处编号);
	box2.add(new JLabel("地址:",JLabel.CENTER));
	box2.add(地址);
    box4.add(new JLabel("电话号码:",JLabel.CENTER));
	box4.add(电话号码);
	box6.add(new JLabel("传真号码:",JLabel.CENTER));
	box6.add(传真号码);
	box3.add(修改);
	box5.add(new JLabel("办事处编号:",JLabel.CENTER));
	box5.add(办事处编号1);
	box5.add(查找);
	
	修改.addActionListener(this);
    查找.addActionListener(this);
	
	Box boxH=Box.createVerticalBox();//竖放box
	boxH.add(box1);
	boxH.add(box2);
    boxH.add(box4);
    boxH.add(box6);
	boxH.add(box3);
	
	boxH.add(Box.createVerticalGlue());
	JPanel picPanel=new JPanel();
	JPanel messPanel=new JPanel();
	messPanel.add(box5);
	picPanel.add(boxH);
	setLayout(new BorderLayout());
	JSplitPane splitV=new JSplitPane(JSplitPane.VERTICAL_SPLIT,messPanel,picPanel);//分割
	add(splitV,BorderLayout.CENTER);
	validate();
}

public void actionPerformed(ActionEvent e){
	Object obj=e.getSource();
	Statement stmt=null;
	ResultSet rs=null,rs1=null;
    String sql=null,sql1=null,sqlct=null;
	
	if(obj==查找){if(办事处编号1.getText().equals(""))JOptionPane.showMessageDialog(this,"请填写查询的编号！" );
	else{
	    sql1="select * from Office where officeNo='"+办事处编号1.getText()+"'";
	    try{
	    Connection dbConn1=CONN();
		stmt=(Statement)dbConn1.createStatement();
		rs1=stmt.executeQuery(sql1);
	    if(rs1.next()){办事处编号.setText(rs1.getString("officeNo").trim());
        				地址.setText(rs1.getString("officeAddress").trim());
        					电话号码.setText(rs1.getString("officeTelNo").trim());
        					传真号码.setText(rs1.getString("officeFaxNo").trim());
        					save=办事处编号1.getText().trim();	    	 	
	    }
	    else{JOptionPane.showMessageDialog(this,"没有这个办事处" );}
	    stmt.close();
	    rs1.close();
	    }catch(SQLException e1){
	    	JOptionPane.showMessageDialog(this,e1.getMessage());
		   }
	    }
	}
	else{
	if(obj==修改){if(save==null){JOptionPane.showMessageDialog(this,"还没查找需要修改的办事处" );}
	else{
		if(办事处编号.getText().equals("")||电话号码.getText().equals("")){
			JOptionPane.showMessageDialog(this,"必填信息填满才能修改！" );
		}
		else{sql="update Office set officeNo='"+办事处编号.getText()+"',officeAddress='"+地址.getText()+"' ,officeTelNo='"+电话号码.getText()+"',officeFaxNo='"+传真号码.getText()+"' where officeNo='"+save+"'";
		if(save.trim().equals(办事处编号.getText().trim())){
		try{
		    Connection dbConn1=CONN();
			stmt=(Statement)dbConn1.createStatement();
			stmt.executeUpdate(sql);
			save=null;
			JOptionPane.showMessageDialog(this,"修改完成" );
			办事处编号.setText("");
            地址.setText("");
            电话号码.setText("");
            传真号码.setText("");
            stmt.close();
		    }catch(SQLException e1){
		    	JOptionPane.showMessageDialog(this,e1.getMessage());
			   }
	}
		else{/*sql1="select * from Office where officeNo='"+办事处编号.getText()+"'";
		try{
		    Connection dbConn1=CONN();
			stmt=(Statement)dbConn1.createStatement();
			rs1=stmt.executeQuery(sql1);
		    if(rs1.next()){  	JOptionPane.showMessageDialog(this,"已存在此办事处" );
		    }
		   
		    stmt.close();
		    rs1.close();
		    }catch(SQLException e1){
		    	JOptionPane.showMessageDialog(this,e1.getMessage());*/
			 JOptionPane.showMessageDialog(this,"不允许修改客户编号" );
			   }
		
		}
	}}}}

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
