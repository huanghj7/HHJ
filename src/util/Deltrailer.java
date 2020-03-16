package util;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Deltrailer extends JPanel implements ActionListener{
	String save=null;
	JTextField TrailerVehLicenseNo,TrailerVehLicenseNo1,TrailerDescription,TrailerLength,MaxCarryingWeight,OfficeNo;
	JButton ɾ��,����;
	
public Deltrailer(){
	try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
	catch(Exception e){System.err.println("�����������:   "+e);}
	
	TrailerVehLicenseNo1=new JTextField(12);
	TrailerVehLicenseNo=new JTextField(12);
	TrailerDescription=new JTextField(12);
        TrailerLength=new JTextField(12);
        MaxCarryingWeight=new JTextField(12);
        OfficeNo=new JTextField(12);
	ɾ��=new JButton("ɾ��");
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
	box4.add(ɾ��);
	box5.add(new JLabel("TrailerLength:",JLabel.CENTER));
	box5.add(TrailerLength);
        box3.add(new JLabel("MaxCarryingWeight:",JLabel.CENTER));
	box3.add(MaxCarryingWeight);
        box6.add(new JLabel("OfficeNo:",JLabel.CENTER));
	box6.add(OfficeNo);
	box7.add(new JLabel("TrailerVehLicenseNo:",JLabel.CENTER));
	box7.add(TrailerVehLicenseNo1);
	box7.add(����);
	Box boxH=Box.createVerticalBox();//����box
	boxH.add(box1);
	boxH.add(box2);
	boxH.add(box5);
	boxH.add(box3);
        boxH.add(box6);
	boxH.add(box4);
	boxH.add(Box.createVerticalGlue());
	
	ɾ��.addActionListener(this);
    ����.addActionListener(this);
	
	JPanel picPanel=new JPanel();
	JPanel messPanel=new JPanel();
	messPanel.add(box7);
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
    String sql=null,sql1=null;
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
	                   save=TrailerVehLicenseNo1.getText().trim();	    	
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
		if(obj==ɾ��){if(save==null)JOptionPane.showMessageDialog(this,"��û������Ҫɾ�����ϳ�" );
		else{
			sql1="delete from TransportReqts where TrailerVehLicenseNo1='"+save+"'";
			sql="delete from Trailer where TrailerVehLicenseNo='"+save+"'";

		try{
		    Connection dbConn1=CONN();
			stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			stmt.executeUpdate(sql);

			save=null;
			JOptionPane.showMessageDialog(this,"ɾ�����" );
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
		}
}
}

//	�������ݿⷽ��
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
