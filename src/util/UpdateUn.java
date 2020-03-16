package util;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class UpdateUn extends JPanel implements ActionListener{

	String save=null;
	JTextField UnitVehLicenseNo,UnitVehLicenseNo1,UnitDescription,MaxPayLoad,OfficeNo;
	JButton �޸�,����;
	
public UpdateUn(){
	try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
	catch(Exception e){System.err.println("�����������:   "+e);}
	
	UnitVehLicenseNo=new JTextField(12);
	UnitVehLicenseNo1=new JTextField(12);
	UnitDescription=new JTextField(12);
	MaxPayLoad=new JTextField(12);
    OfficeNo=new JTextField(12);
	�޸�=new JButton("�޸�");
	����=new JButton("����");
	
	Box box1=Box.createHorizontalBox();//���box
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
	box3.add(�޸�);
	box4.add(new JLabel("OfficeNo:",JLabel.CENTER));
	box4.add(OfficeNo);
	box5.add(new JLabel("UnitVehLicenseNo:",JLabel.CENTER));
	box5.add(UnitVehLicenseNo1);
	box5.add(����);
	
	�޸�.addActionListener(this);
        ����.addActionListener(this);
	
	Box boxH=Box.createVerticalBox();//����box
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
	JSplitPane splitV=new JSplitPane(JSplitPane.VERTICAL_SPLIT,messPanel,picPanel);//�ָ�
	add(splitV,BorderLayout.CENTER);
	validate();
}

public void actionPerformed(ActionEvent e){
	Object obj=e.getSource();
	Statement stmt=null;
	ResultSet rs=null,rs1=null;
    String sql=null,sql1=null,sqlSC=null;
	
	if(obj==����){if(UnitVehLicenseNo1.getText().equals(""))JOptionPane.showMessageDialog(this,"����д��ѯ�İ�ţ�" );
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
	    else{JOptionPane.showMessageDialog(this,"û��������" );}
	    stmt.close();
	    rs1.close();
	    }catch(SQLException e1){
	    	JOptionPane.showMessageDialog(this,e1.getMessage());
		   }
	    }
	}
	else{
	if(obj==�޸�){if(save==null){JOptionPane.showMessageDialog(this,"��û������Ҫ�޸ĵİ��" );}
	else{
		if(UnitVehLicenseNo.getText().equals("")||UnitDescription.getText().equals("")||MaxPayLoad.getText().equals("")||OfficeNo.getText().equals("")){
			JOptionPane.showMessageDialog(this,"�༶��Ϣ���������޸ģ�" );
		}
		else{sql="update Unit set UnitVehLicenseNo='"+UnitVehLicenseNo.getText()+"',UnitDescription='"+UnitDescription.getText()+"' ,MaxPayLoad='"+MaxPayLoad.getText()+"' ,OfficeNo='"+OfficeNo.getText()+"' where UnitVehLicenseNo='"+save+"'";
		if(save.trim().equals(UnitVehLicenseNo.getText().trim())){
		try{
		    Connection dbConn1=CONN();
			stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			stmt.executeUpdate(sql);
			save=null;
			JOptionPane.showMessageDialog(this,"�޸����" );
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
		    if(rs1.next()){  	JOptionPane.showMessageDialog(this,"�Ѵ��ڴ˰�" );
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
    String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";   //����JDBC����
   String dbURL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=ClientT";
       String userName = "sa";   //Ĭ���û���
   String userPwd = "12345";   //����
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