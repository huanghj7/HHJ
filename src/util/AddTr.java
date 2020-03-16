package util;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;


public class AddTr extends JPanel implements ActionListener{
	JTextField TransportReqPartNo,UnitVehLicenseNo,TrailerVehLicenseNo1,OrderNo;
	JButton 录入;
	
public AddTr(){
	try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
	catch(Exception e){System.err.println("不能设置外观:   "+e);}
	
	TransportReqPartNo=new JTextField(12);
	UnitVehLicenseNo=new JTextField(12);
       TrailerVehLicenseNo1=new JTextField(12);
        OrderNo=new JTextField(12);
	录入=new JButton("录入");
	录入.addActionListener(this);
	
	Box box1=Box.createHorizontalBox();//横放box
	Box box2=Box.createHorizontalBox();
	Box box3=Box.createHorizontalBox();

        Box box5=Box.createHorizontalBox();
        Box box6=Box.createHorizontalBox();
	box1.add(new JLabel("TransportReqPartNo:"));
	box1.add(TransportReqPartNo);
	box2.add(new JLabel("UnitVehLicenseNo:"));
	box2.add(UnitVehLicenseNo);
        box3.add(new JLabel("TrailerVehLicenseNo1:"));
	box3.add(TrailerVehLicenseNo1);

        box5.add(new JLabel("OrderNo:"));
	box5.add(OrderNo);
	box6.add(录入);
	Box boxH=Box.createVerticalBox();//竖放box
	boxH.add(box1);
	boxH.add(box5);
        boxH.add(box2);
        boxH.add(box3);

	boxH.add(box6);
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
		if(TransportReqPartNo.getText().equals("")||UnitVehLicenseNo.getText().equals("")||TrailerVehLicenseNo1.getText().equals("")||OrderNo.getText().equals("")){
			JOptionPane.showMessageDialog(this,"信息请填满再录入！" );
		}
		Statement stmt=null;
		ResultSet rs=null,rs1=null;
		String sql,sql1;
		    sql1="select * from TransportReqts where TransportReqPartNo='"+TransportReqPartNo.getText()+"'";
		    sql="insert into TransportReqts values('"+OrderNo.getText()+"','"+TransportReqPartNo.getText()+"','"+UnitVehLicenseNo.getText()+"','"+TrailerVehLicenseNo1.getText()+"')";
	   try{
		   Connection dbConn1=CONN();
			stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
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

