package util;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class UpdateUn extends JPanel implements ActionListener{

	String save=null;
	JTextField UnitVehLicenseNo,UnitVehLicenseNo1,UnitDescription,MaxPayLoad,OfficeNo;
	JButton 修改,查找;
	
public UpdateUn(){
	try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
	catch(Exception e){System.err.println("不能设置外观:   "+e);}
	
	UnitVehLicenseNo=new JTextField(12);
	UnitVehLicenseNo1=new JTextField(12);
	UnitDescription=new JTextField(12);
	MaxPayLoad=new JTextField(12);
    OfficeNo=new JTextField(12);
	修改=new JButton("修改");
	查找=new JButton("查找");
	
	Box box1=Box.createHorizontalBox();//横放box
	Box box2=Box.createHorizontalBox();
	Box box3=Box.createHorizontalBox();
	Box box4=Box.createHorizontalBox();
	Box box5=Box.createHorizontalBox();
        Box box6=Box.createHorizontalBox();
	box1.add(new JLabel("UnitVehLicenseNo:",JLabel.CENTER));
	box1.add(UnitVehLicenseNo);
	box2.add(new JLabel("UnitDescription:",JLabel.CENTER));
	box2.add(UnitDescription);
        box6.add(new JLabel("MaxPayLoad:",JLabel.CENTER));
	box6.add(MaxPayLoad);
	box3.add(修改);
	box4.add(new JLabel("OfficeNo:",JLabel.CENTER));
	box4.add(OfficeNo);
	box5.add(new JLabel("UnitVehLicenseNo:",JLabel.CENTER));
	box5.add(UnitVehLicenseNo1);
	box5.add(查找);
	
	修改.addActionListener(this);
        查找.addActionListener(this);
	
	Box boxH=Box.createVerticalBox();//竖放box
	boxH.add(box1);
	boxH.add(box2);
        boxH.add(box6);
	boxH.add(box4);
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
    String sql=null,sql1=null,sqlSC=null;
	
	if(obj==查找){if(UnitVehLicenseNo1.getText().equals(""))JOptionPane.showMessageDialog(this,"请填写查询的班号！" );
	else{
	    sql1="select * from Unit where UnitVehLicenseNo='"+UnitVehLicenseNo1.getText()+"'";
	    try{
	    Connection dbConn1=CONN();
		stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		rs1=stmt.executeQuery(sql1);
	    if(rs1.next()){UnitVehLicenseNo.setText(rs1.getString("UnitVehLicenseNo").trim());
	                   UnitDescription.setText(rs1.getString("UnitDescription").trim());
                           MaxPayLoad.setText(rs1.getString("MaxPayLoad").trim());
                           OfficeNo.setText(rs1.getString("OfficeNo").trim());
	                   save=UnitVehLicenseNo1.getText();	    	
	    }
	    else{JOptionPane.showMessageDialog(this,"没有这个班号" );}
	    stmt.close();
	    rs1.close();
	    }catch(SQLException e1){
	    	JOptionPane.showMessageDialog(this,e1.getMessage());
		   }
	    }
	}
	else{
	if(obj==修改){if(save==null){JOptionPane.showMessageDialog(this,"还没查找需要修改的班号" );}
	else{
		if(UnitVehLicenseNo.getText().equals("")||UnitDescription.getText().equals("")||MaxPayLoad.getText().equals("")||OfficeNo.getText().equals("")){
			JOptionPane.showMessageDialog(this,"班级信息填满才能修改！" );
		}
		else{sql="update Unit set UnitVehLicenseNo='"+UnitVehLicenseNo.getText()+"',UnitDescription='"+UnitDescription.getText()+"' ,MaxPayLoad='"+MaxPayLoad.getText()+"' ,OfficeNo='"+OfficeNo.getText()+"' where UnitVehLicenseNo='"+save+"'";
		if(save.trim().equals(UnitVehLicenseNo.getText().trim())){
		try{
		    Connection dbConn1=CONN();
			stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			stmt.executeUpdate(sql);
			save=null;
			JOptionPane.showMessageDialog(this,"修改完成" );
			UnitVehLicenseNo.setText("");
                        UnitDescription.setText("");
                        MaxPayLoad.setText("");
                        OfficeNo.setText("");
            stmt.close();
		    }catch(SQLException e1){
		    	JOptionPane.showMessageDialog(this,e1.getMessage());
				   //System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
			   }
	}
		else{sql1="select * from Unit where UnitVehLicenseNo='"+UnitVehLicenseNo.getText()+"'";
		try{
		    Connection dbConn1=CONN();
			stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs1=stmt.executeQuery(sql1);
		    if(rs1.next()){  	JOptionPane.showMessageDialog(this,"已存在此班" );
		    }
		    
		    stmt.close();
		    rs1.close();
		    }catch(SQLException e1) {
		    	
		    	JOptionPane.showMessageDialog(this,e1.getMessage());
				  // System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
			   }
		}
	}}}}
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
   System.out.println("Connection Successful!");   
   } catch (Exception e) {
   e.printStackTrace();
   }
   return dbConn;
}
}