package util;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class Updatetrailer extends JPanel implements ActionListener{
	String save=null;
	JTextField TrailerVehLicenseNo,TrailerVehLicenseNo1,TrailerDescription,TrailerLength,MaxCarryingWeight,OfficeNo;
	JButton �޸�,����;
	
public Updatetrailer(){
	try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
	catch(Exception e){System.err.println("�����������:   "+e);}
	
	TrailerVehLicenseNo1=new JTextField(12);
	TrailerVehLicenseNo=new JTextField(12);
	TrailerDescription=new JTextField(12);
        TrailerLength=new JTextField(12);
        MaxCarryingWeight=new JTextField(12);
        OfficeNo=new JTextField(12);
	�޸�=new JButton("�޸�");
	����=new JButton("����");
	
	Box box1=Box.createHorizontalBox();//���box
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
	box3.add(�޸�);
	box4.add(new JLabel("MaxCarryingWeight:",JLabel.CENTER));
	box4.add(MaxCarryingWeight);
    box7.add(new JLabel("OfficeNo:",JLabel.CENTER));
	box7.add(OfficeNo);
	box5.add(new JLabel("TrailerVehLicenseNo:",JLabel.CENTER));
	box5.add(TrailerVehLicenseNo1);
	box5.add(����);
	
	�޸�.addActionListener(this);
        ����.addActionListener(this);
	
	Box boxH=Box.createVerticalBox();//����box
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
	JSplitPane splitV=new JSplitPane(JSplitPane.VERTICAL_SPLIT,messPanel,picPanel);//�ָ�
	add(splitV,BorderLayout.CENTER);
	validate();
}

public void actionPerformed(ActionEvent e){
	Object obj=e.getSource();
	Statement stmt=null;
	ResultSet rs=null,rs1=null;
    String sql=null,sql1=null,sqlSC=null;
	
	if(obj==����){if(TrailerVehLicenseNo1.getText().equals(""))JOptionPane.showMessageDialog(this,"����д��ѯ���ϳ��ţ�" );
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
	    else{JOptionPane.showMessageDialog(this,"û�������ŵ��ϳ�" );}
	    stmt.close();
	    rs1.close();
	    }catch(SQLException e1){
	    	JOptionPane.showMessageDialog(this,e1.getMessage());
		   }
	    }
	}
	else{
	if(obj==�޸�){if(save==null){JOptionPane.showMessageDialog(this,"��û������Ҫ�޸ĵ��ϳ���" );}
	else{
		if(TrailerVehLicenseNo.getText().equals("")||TrailerDescription.getText().equals("")||TrailerLength.getText().equals("")||MaxCarryingWeight.getText().equals("")||OfficeNo.getText().equals("")){
			JOptionPane.showMessageDialog(this,"�ϳ���Ϣ���������޸ģ�" );
		}
		else{sql="update Trailer set TrailerVehLicenseNo='"+TrailerVehLicenseNo.getText()+"',TrailerDescription='"+TrailerDescription.getText()+"' ,TrailerLength='"+TrailerLength.getText()+"' ,MaxCarryingWeight='"+MaxCarryingWeight.getText()+"' ,OfficeNo='"+OfficeNo.getText()+"' where TrailerVehLicenseNo='"+save+"'";
		if(save.trim().equals(TrailerVehLicenseNo.getText().trim())){
		try{
		    Connection dbConn1=CONN();
			stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			stmt.executeUpdate(sql);
			save=null;
			JOptionPane.showMessageDialog(this,"�޸����" );
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
		    if(rs1.next()){  	JOptionPane.showMessageDialog(this,"�Ѵ��ڴ˱���ϳ�" );
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
    String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";   //����JDBC����
   String dbURL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=ClientT";
       String userName = "sa";   //Ĭ���û���
   String userPwd = "12345";   //����
   Connection dbConn=null;
   try {
   Class.forName(driverName);
   dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
   System.out.println("Connection Successful!");   //������ӳɹ� ����̨���Connection Successful!
   } catch (Exception e) {
   e.printStackTrace();
   }
   return dbConn;
}
}