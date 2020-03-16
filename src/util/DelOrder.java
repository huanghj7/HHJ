package util;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class DelOrder extends JPanel implements ActionListener{
String delete=null;
JTextField orderNo1,orderNo,dateOrder,collectionDate,collectionAddress,deliveryDate,deliveryAddress,loadWeight,loadDescription,clientNo;
JButton 删除,查找;
	
public DelOrder(){
try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
catch(Exception e){System.err.println("不能设置外观:   "+e);}
	
orderNo1=new JTextField(12);
orderNo=new JTextField(12);
dateOrder=new JTextField(12);
collectionDate=new JTextField(12);
    collectionAddress=new JTextField(12);
    deliveryDate=new JTextField(12);
    deliveryAddress=new JTextField(12);
    loadWeight=new JTextField(12);
    loadDescription=new JTextField(12);
    clientNo=new JTextField(12);
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

box1.add(new JLabel("orderNo:",JLabel.CENTER));
box1.add(orderNo);
box2.add(new JLabel("dateOrder:",JLabel.CENTER));
box2.add(dateOrder);
box4.add(new JLabel("collectionDate:",JLabel.CENTER));
box4.add(collectionDate);
box5.add(new JLabel("collectionAddress:",JLabel.CENTER));
box5.add(collectionAddress);
box6.add(new JLabel("deliveryDate:",JLabel.CENTER));
box6.add(deliveryDate);
box7.add(new JLabel("deliveryAddress:",JLabel.CENTER));
box7.add(deliveryAddress);
box8.add(new JLabel("loadWeight:",JLabel.CENTER));
box8.add(loadWeight);
box9.add(new JLabel("loadDescription:",JLabel.CENTER));
box9.add(loadDescription);
box10.add(new JLabel("clientNo:",JLabel.CENTER));
box10.add(clientNo);
box3.add(删除);
box11.add(new JLabel("orderNo:",JLabel.CENTER));
box11.add(orderNo1);
box11.add(查找);
Box boxH=Box.createVerticalBox();//竖放box
boxH.add(box1);
boxH.add(box2);
boxH.add(box4);
    boxH.add(box5);
    boxH.add(box6);
    boxH.add(box7);
    boxH.add(box8);
    boxH.add(box9);
    boxH.add(box10);
    boxH.add(box3);
boxH.add(Box.createVerticalGlue());

删除.addActionListener(this);
查找.addActionListener(this);

JPanel picPanel=new JPanel();
JPanel messPanel=new JPanel();
messPanel.add(box11);
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
    String sql=null,sql1=null,sqlSC=null;

if(obj==查找){if(orderNo1.getText().equals(""))JOptionPane.showMessageDialog(this,"请填写查询的订单编号！" );
else{
	sql1="select * from ClientOrder where orderNo='"+orderNo1.getText()+"'";

    try{
    Connection dbConn1=CONN();
stmt=(Statement)dbConn1.createStatement();
rs1=stmt.executeQuery(sql1);
    if(rs1.next()){orderNo.setText(rs1.getString("orderNo").trim());
    dateOrder.setText(rs1.getString("dateOrder").trim());
    collectionDate.setText(rs1.getString("collectionDate").trim());
    collectionAddress.setText(rs1.getString("collectionAddress").trim());
    deliveryDate.setText(rs1.getString("deliveryDate").trim());
    deliveryAddress.setText(rs1.getString("deliveryAddress").trim());
    loadWeight.setText(rs1.getString("loadWeight").trim());
    loadDescription.setText(rs1.getString("loadDescription").trim());
    clientNo.setText(rs1.getString("clientNo").trim());
    delete=orderNo1.getText();	    	
    }
    else{JOptionPane.showMessageDialog(this,"没有这个订单" );}
    stmt.close();
    rs1.close();
    }catch(SQLException e1){
    	JOptionPane.showMessageDialog(this,e1.getMessage());
   }
    }
}
else{
if(obj==删除){if(delete==null)JOptionPane.showMessageDialog(this,"还没查找需要删除的订单" );
else{sql="delete from ClientOrder where orderNo='"+delete+"'";
     
try{
    Connection dbConn1=CONN();
stmt=(Statement)dbConn1.createStatement();
stmt.executeUpdate(sql);
delete=null;
JOptionPane.showMessageDialog(this,"删除完成" );
orderNo.setText("");
dateOrder.setText("");
collectionDate.setText("");
collectionAddress.setText("");
deliveryDate.setText("");
deliveryAddress.setText("");
loadWeight.setText("");
loadDescription.setText("");
clientNo.setText("");
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
