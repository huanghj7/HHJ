package util;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class Updatetrailer extends JPanel implements ActionListener{
	String save=null;
	JTextField TrailerVehLicenseNo,TrailerVehLicenseNo1,TrailerDescription,TrailerLength,MaxCarryingWeight,OfficeNo;
	JButton 修改,查找;
	
public Updatetrailer(){
	try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
	catch(Exception e){System.err.println("不能设置外观:   "+e);}
	
	TrailerVehLicenseNo1=new JTextField(12);
	TrailerVehLicenseNo=new JTextField(12);
	TrailerDescription=new JTextField(12);
        TrailerLength=new JTextField(12);
        MaxCarryingWeight=new JTextField(12);
        OfficeNo=new JTextField(12);
	修改=new JButton("修改");
	查找=new JButton("查找");
	
	Box box1=Box.createHorizontalBox();//横放box
	Box box2=Box.createHorizontalBox();
	Box box3=Box.createHorizontalBox();
	Box box4=Box.createHorizontalBox();
	Box box5=Box.createHorizontalBox();
        Box box6=Box.createHorizontalBox();
        Box box7=Box.createHorizontalBox();
     
	box1.add(new JLabel("TrailerVehLicenseNo:",JLabel.CENTER));
	box1.add(TrailerVehLicenseNo);
	box2.add(new JLabel("TrailerDescription:",JLabel.CENTER));
	box2.add(TrailerDescription);
    box6.add(new JLabel("TrailerLength:",JLabel.CENTER));
	box6.add(TrailerLength);
	box3.add(修改);
	box4.add(new JLabel("MaxCarryingWeight:",JLabel.CENTER));
	box4.add(MaxCarryingWeight);
    box7.add(new JLabel("OfficeNo:",JLabel.CENTER));
	box7.add(OfficeNo);
	box5.add(new JLabel("TrailerVehLicenseNo:",JLabel.CENTER));
	box5.add(TrailerVehLicenseNo1);
	box5.add(查找);
	
	修改.addActionListener(this);
        查找.addActionListener(this);
	
	Box boxH=Box.createVerticalBox();//竖放box
	boxH.add(box1);
	boxH.add(box2);
        boxH.add(box6);
		boxH.add(box4);
        boxH.add(box7);
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
	
	if(obj==查找){if(TrailerVehLicenseNo1.getText().equals(""))JOptionPane.showMessageDialog(this,"请填写查询的拖车号！" );
	else{
	    sql1="select * from Trailer where TrailerVehLicenseNo='"+TrailerVehLicenseNo1.getText()+"'";
	    try{
	    Connection dbConn1=CONN();
		stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		rs1=stmt.executeQuery(sql1);
	    if(rs1.next()){TrailerVehLicenseNo.setText(rs1.getString("TrailerVehLicenseNo").trim());
	                   TrailerDescription.setText(rs1.getString("TrailerDescription").trim());
                           TrailerLength.setText(rs1.getString("TrailerLength").trim());
                           MaxCarryingWeight.setText(rs1.getString("MaxCarryingWeight").trim());
                           OfficeNo.setText(rs1.getString("OfficeNo").trim());
	                   save=TrailerVehLicenseNo1.getText();	    	
	    }
	    else{JOptionPane.showMessageDialog(this,"没有这个编号的拖车" );}
	    stmt.close();
	    rs1.close();
	    }catch(SQLException e1){
	    	JOptionPane.showMessageDialog(this,e1.getMessage());
		   }
	    }
	}
	else{
	if(obj==修改){if(save==null){JOptionPane.showMessageDialog(this,"还没查找需要修改的拖车号" );}
	else{
		if(TrailerVehLicenseNo.getText().equals("")||TrailerDescription.getText().equals("")||TrailerLength.getText().equals("")||MaxCarryingWeight.getText().equals("")||OfficeNo.getText().equals("")){
			JOptionPane.showMessageDialog(this,"拖车信息填满才能修改！" );
		}
		else{sql="update Trailer set TrailerVehLicenseNo='"+TrailerVehLicenseNo.getText()+"',TrailerDescription='"+TrailerDescription.getText()+"' ,TrailerLength='"+TrailerLength.getText()+"' ,MaxCarryingWeight='"+MaxCarryingWeight.getText()+"' ,OfficeNo='"+OfficeNo.getText()+"' where TrailerVehLicenseNo='"+save+"'";
		if(save.trim().equals(TrailerVehLicenseNo.getText().trim())){
		try{
		    Connection dbConn1=CONN();
			stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			stmt.executeUpdate(sql);
			save=null;
			JOptionPane.showMessageDialog(this,"修改完成" );
			TrailerVehLicenseNo.setText("");
                        TrailerDescription.setText("");
                        TrailerLength.setText("");
                        MaxCarryingWeight.setText("");
                        OfficeNo.setText("");
            stmt.close();
		    }catch(SQLException e1){
		    	JOptionPane.showMessageDialog(this,e1.getMessage());
			   }
	}
		else{sql1="select * from Trailer where TrailerVehLicenseNo='"+TrailerVehLicenseNo.getText()+"'";
		try{
		    Connection dbConn1=CONN();
			stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs1=stmt.executeQuery(sql1);
		    if(rs1.next()){  	JOptionPane.showMessageDialog(this,"已存在此编号拖车" );
		    }
		    
                    
		    stmt.close();
		    rs1.close();
		    }catch(SQLException e1){
		    	JOptionPane.showMessageDialog(this,e1.getMessage());
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
   System.out.println("Connection Successful!");   //如果连接成功 控制台输出Connection Successful!
   } catch (Exception e) {
   e.printStackTrace();
   }
   return dbConn;
}
}