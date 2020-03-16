package util;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;


public class Delstu extends JPanel implements ActionListener{
String delete=null;
JTextField 客户编号1,客户编号,姓名,街道,城市,所在州,邮政编码,电话号码,传真号码,网页地址,联系人姓名,联系人电话,联系人传真,联系人邮箱,办公室编号;
JButton 删除,查找;
	
public Delstu(){
try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
catch(Exception e){System.err.println("不能设置外观:   "+e);}
	
客户编号1=new JTextField(12);
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
删除=new JButton("删除");
查找=new JButton("查找");

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
Box box16=Box.createHorizontalBox();
box1.add(new JLabel("客户编号:",JLabel.CENTER));
box1.add(客户编号);
box2.add(new JLabel("姓名:",JLabel.CENTER));
box2.add(姓名);
box3.add(new JLabel("街道:",JLabel.CENTER));
box3.add(街道);
box6.add(new JLabel("城市:",JLabel.CENTER));
box6.add(城市);
box7.add(new JLabel("所在州:",JLabel.CENTER));
box7.add(所在州);
box8.add(new JLabel("邮政编码:",JLabel.CENTER));
box8.add(邮政编码);
box9.add(new JLabel("电话号码:",JLabel.CENTER));
box9.add(电话号码);
box10.add(new JLabel("传真号码:",JLabel.CENTER));
box10.add(传真号码);
box11.add(new JLabel("网页地址:",JLabel.CENTER));
box11.add(网页地址);
box12.add(new JLabel("联系人姓名:",JLabel.CENTER));
box12.add(联系人姓名);
box13.add(new JLabel("联系人电话:",JLabel.CENTER));
box13.add(联系人电话);
box14.add(new JLabel("联系人传真:",JLabel.CENTER));
box14.add(联系人传真);
box15.add(new JLabel("联系人邮箱:",JLabel.CENTER));
box15.add(联系人邮箱);
box16.add(new JLabel("办公室编号:",JLabel.CENTER));
box16.add(办公室编号);
box4.add(删除);
box5.add(new JLabel("客户编号:",JLabel.CENTER));
box5.add(客户编号1);
box5.add(查找);
Box boxH=Box.createVerticalBox();//竖放box
boxH.add(box1);
boxH.add(box2);
boxH.add(box3);
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
boxH.add(box16);
boxH.add(box4);
boxH.add(Box.createVerticalGlue());

删除.addActionListener(this);
查找.addActionListener(this);

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
    String sql=null,sql1=null,sql2=null;

if(obj==查找){if(客户编号1.getText().equals(""))JOptionPane.showMessageDialog(this,"请填写查询的客户编号！" );
else{
    sql1="select * from Client where clientNo='"+客户编号1.getText()+"'";
    try{
    Connection dbConn1=CONN();
stmt=(Statement)dbConn1.createStatement();
rs1=stmt.executeQuery(sql1);
    if(rs1.next()){客户编号.setText(rs1.getString("clientNo").trim());
    姓名.setText(rs1.getString("clientName").trim());
    街道.setText(rs1.getString("clientStreet").trim());
    城市.setText(rs1.getString("clientCity").trim());
    所在州.setText(rs1.getString("clientState").trim());
    邮政编码.setText(rs1.getString("clientZipCode").trim());
    电话号码.setText(rs1.getString("clientTelNo").trim());
    传真号码.setText(rs1.getString("clientFaxNo").trim());
    网页地址.setText(rs1.getString("clientWebAddress").trim());
    联系人姓名.setText(rs1.getString("contactName").trim());
    联系人电话.setText(rs1.getString("contactTelNo").trim());
    联系人传真.setText(rs1.getString("contactFaxNo").trim());
    联系人邮箱.setText(rs1.getString("contactEmailNo").trim());
    办公室编号.setText(rs1.getString("officeNo").trim());
    delete=客户编号1.getText();	    	
    }
    else{JOptionPane.showMessageDialog(this,"没有这个客户" );}
    stmt.close();
    rs1.close();
    }catch(SQLException e1){
    	JOptionPane.showMessageDialog(this,e1.getMessage());
   }
    }
}
else{
if(obj==删除){if(delete==null)JOptionPane.showMessageDialog(this,"还没查找需要删除的学生" );
else{sql2="delete from ClientOrder where clientNo='"+delete+"'";
	sql="delete from Client where clientNo='"+delete+"'";
     
try{
    Connection dbConn1=CONN();
stmt=(Statement)dbConn1.createStatement();
stmt.executeUpdate(sql2);
stmt.executeUpdate(sql);
delete=null;
JOptionPane.showMessageDialog(this,"删除完成" );
客户编号.setText("");
姓名.setText("");
街道.setText("");
城市.setText("");
所在州.setText("");
邮政编码.setText("");
电话号码.setText("");
传真号码.setText("");
网页地址.setText("");
联系人姓名.setText("");
联系人电话.setText("");
联系人传真.setText("");
联系人邮箱.setText("");
办公室编号.setText("");

stmt.close();
    }catch(SQLException e1){
    	JOptionPane.showMessageDialog(this,e1.getMessage());
   }
}
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
