package util;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class DelO extends JPanel implements ActionListener{
	String save=null;
	JTextField 办事处编号1,办事处编号,地址,电话号码,传真号码;
	JButton 删除,查找;
	
public DelO(){
	try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
	catch(Exception e){System.err.println("不能设置外观:   "+e);}
	
	办事处编号1=new JTextField(12);
	办事处编号=new JTextField(12);
	地址=new JTextField(12);
	电话号码=new JTextField(12);
    传真号码=new JTextField(12);
	删除=new JButton("删除");
	查找=new JButton("查找");
	
	Box box1=Box.createHorizontalBox();//横放box
	Box box2=Box.createHorizontalBox();
	Box box3=Box.createHorizontalBox();
	Box box4=Box.createHorizontalBox();
	Box box5=Box.createHorizontalBox();
	Box box6=Box.createHorizontalBox();
	box1.add(new JLabel("办事处编号:"));
	box1.add(办事处编号);
	box2.add(new JLabel("地址:"));
	box2.add(地址);
    box3.add(new JLabel("电话号码:"));
	box3.add(电话号码);
	box6.add(new JLabel("传真号码:"));
	box6.add(传真号码);
	box4.add(删除);
	box5.add(new JLabel("办事处编号:",JLabel.CENTER));
	box5.add(办事处编号1);
	box5.add(查找);
	Box boxH=Box.createVerticalBox();//竖放box
	boxH.add(box1);
	boxH.add(box2);
	boxH.add(box3);
	boxH.add(box6);
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
	ResultSet rs=null,rs1=null,rs2=null,rs3=null;
    String sql=null,sql1=null,sql2=null,sql3=null,sql4=null,sql5=null,sql6=null,sql7=null,sql8=null;
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
		if(obj==删除){if(save==null)JOptionPane.showMessageDialog(this,"还没查找需要删除的办事处" );
		else{
			sql6="delete from TransportReqts where trailerVehlicenseNo1 in( select trailerVehlicenseNo1 from TransportReqts,Trailer where TransportReqts.trailerVehlicenseNo1=Trailer.trailerVehlicenseNo and Trailer.officeNo='"+save+"')";
			sql5="delete from TransportReqts where unitVehLicenseNo in (select TransportReqts.unitVehLicenseNo from Unit, TransportReqts where Unit.unitVehLicenseNo=TransportReqts.unitVehLicenseNo and Unit.officeNo='"+save+"')";
			sql4="delete from Unit where officeNo='"+save+"'" ;
			sql3="delete from Trailer where officeNo='"+save+"'";
			sql2="delete from ClientOrder where clientNo in(select ClientOrder.clientNo from Client,ClientOrder where ClientOrder.clientNo=Client.clientNo and Client.officeNo='"+save+"')";
			sql1="delete from Client where officeNo='"+save+"'";
			sql="delete from Office where officeNo='"+save+"'";
		try{
		    Connection dbConn1=CONN();
			stmt=(Statement)dbConn1.createStatement();
			stmt.executeUpdate(sql6);
			stmt.executeUpdate(sql5);
			stmt.executeUpdate(sql4);
			stmt.executeUpdate(sql3);
			stmt.executeUpdate(sql2);
			stmt.executeUpdate(sql1);
			stmt.executeUpdate(sql);
			save=null;
			JOptionPane.showMessageDialog(this,"删除完成" );
			办事处编号.setText("");
            地址.setText("");
            电话号码.setText("");
            传真号码.setText("");
			stmt.close();
		    }catch(SQLException e1){
		    	JOptionPane.showMessageDialog(this,e1.getMessage());
			   }
		}
		}
}
}

//	连接数据库方法
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
