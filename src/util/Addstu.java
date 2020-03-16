package util;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;


public  class Addstu extends JPanel implements ActionListener{
	JTextField 客户编号,姓名,街道,城市,所在州,邮政编码,电话号码,传真号码,网页地址,联系人姓名,联系人电话,联系人传真,联系人邮箱,办公室编号;
	JButton 录入;
	
public Addstu(){
	try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
	catch(Exception e){System.err.println("不能设置外观:   "+e);}
	客户编号=new JTextField(12);
	姓名=new JTextField(12);
    街道=new JTextField(12);
    城市=new JTextField(12);
	所在州=new JTextField(12);
	邮政编码=new JTextField(12);
	电话号码=new JTextField(12);
	传真号码=new JTextField(12);
	网页地址=new JTextField(12);
	联系人姓名=new JTextField(12);
	联系人电话=new JTextField(12);
	联系人传真=new JTextField(12);
	联系人邮箱=new JTextField(12);
	办公室编号=new JTextField(12);
	录入=new JButton("录入");
	录入.addActionListener(this);//添加事件监听
	
	Box box1=Box.createHorizontalBox();//横放box
	Box box2=Box.createHorizontalBox();
	Box box3=Box.createHorizontalBox();
	Box box4=Box.createHorizontalBox();
    Box box5=Box.createHorizontalBox();
	Box box6=Box.createHorizontalBox();
	Box box7=Box.createHorizontalBox();
	Box box8=Box.createHorizontalBox();
	Box box9=Box.createHorizontalBox();
	Box box10=Box.createHorizontalBox();
	Box box11=Box.createHorizontalBox();
	Box box12=Box.createHorizontalBox();
	Box box13=Box.createHorizontalBox();
	Box box14=Box.createHorizontalBox();
	Box box15=Box.createHorizontalBox();
	box1.add(new JLabel("客户编号:",JLabel.CENTER));
	box1.add(客户编号);
	box2.add(new JLabel("姓名:",JLabel.CENTER));
	box2.add(姓名);
	box3.add(new JLabel("街道:",JLabel.CENTER));
	box3.add(街道);
    box5.add(new JLabel("城市:",JLabel.CENTER));
	box5.add(城市);
    box6.add(new JLabel("所在州:",JLabel.CENTER));
	box6.add(所在州);
	box7.add(new JLabel("邮政编码:",JLabel.CENTER));
	box7.add(邮政编码);
	box8.add(new JLabel("电话号码:",JLabel.CENTER));
	box8.add(电话号码);
	box9.add(new JLabel("传真号码:",JLabel.CENTER));
	box9.add(传真号码);
	box10.add(new JLabel("网页地址:",JLabel.CENTER));
	box10.add(网页地址);
	box11.add(new JLabel("联系人姓名:",JLabel.CENTER));
	box11.add(联系人姓名);
	box12.add(new JLabel("联系人电话:",JLabel.CENTER));
	box12.add(联系人电话);
	box13.add(new JLabel("联系人传真:",JLabel.CENTER));
	box13.add(联系人传真);
	box14.add(new JLabel("联系人邮箱:",JLabel.CENTER));
	box14.add(联系人邮箱);
	box15.add(new JLabel("办公室编号:",JLabel.CENTER));
	box15.add(办公室编号);
	box4.add(录入);
	Box boxH=Box.createVerticalBox();//竖放box
	boxH.add(box1);
	boxH.add(box2);
	boxH.add(box3);
    boxH.add(box5);
    boxH.add(box6);
    boxH.add(box7);
    boxH.add(box8);
    boxH.add(box9);
    boxH.add(box10);
    boxH.add(box11);
    boxH.add(box12);
    boxH.add(box13);
    boxH.add(box14);
	boxH.add(box15);
	boxH.add(box4);
	boxH.add(Box.createVerticalGlue());
	JPanel messPanel=new JPanel();
	messPanel.add(boxH);
	setLayout(new BorderLayout());
	add(messPanel,BorderLayout.CENTER);

	}
public void actionPerformed(ActionEvent c){
	Object obj=c.getSource();
	if(obj==录入){
		if(客户编号.getText().equals("")||姓名.getText().equals("")||电话号码.getText().equals("")||联系人姓名.getText().equals("")||联系人电话.getText().equals("")){
			JOptionPane.showMessageDialog(this,"必填信息请填满再录入！" );
		}
		Statement stmt=null;
		ResultSet rs1=null;
		String sql,sql1;
		    sql1="select * from Client where clientNo='"+客户编号.getText()+"'";
		    sql="insert into Client values('"+客户编号.getText()+"','"+姓名.getText()+"','"+街道.getText()+"','"+城市.getText()+"','"+所在州.getText()+"','"+邮政编码.getText()+"','"+电话号码.getText()+"','"+传真号码.getText()+"','"+网页地址.getText()+"','"+联系人姓名.getText()+"','"+联系人电话.getText()+"','"+联系人传真.getText()+"','"+联系人邮箱.getText()+"','"+办公室编号.getText()+"')";
	   try{
		   Connection dbConn1=CONN();
			stmt=(Statement)dbConn1.createStatement();
			rs1=stmt.executeQuery(sql1);
			if(rs1.next()){JOptionPane.showMessageDialog(this,"该客户编号以存在，无法添加");}
			else{
			stmt.executeUpdate(sql);	
			JOptionPane.showMessageDialog(this,"添加成功");
			}		
			rs1.close();
			stmt.close();
	   }
	   catch(SQLException e1){
		   JOptionPane.showMessageDialog(this,e1.getMessage());
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
