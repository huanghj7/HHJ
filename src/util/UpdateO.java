package util;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class UpdateO extends JPanel implements ActionListener{
	String save=null;
	JTextField ���´����1,���´����,��ַ,�绰����,�������;
	JButton �޸�,����;
	
public UpdateO(){
	try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
	catch(Exception e){System.err.println("�����������:   "+e);}
	
	���´����1=new JTextField(12);
	���´����=new JTextField(12);
	��ַ=new JTextField(12);
	�绰����=new JTextField(12);
    �������=new JTextField(12);
	�޸�=new JButton("�޸�");
	����=new JButton("����");
	
	Box box1=Box.createHorizontalBox();//���box
	Box box2=Box.createHorizontalBox();
	Box box3=Box.createHorizontalBox();
	Box box4=Box.createHorizontalBox();
	Box box5=Box.createHorizontalBox();
    Box box6=Box.createHorizontalBox();
	box1.add(new JLabel("���´����:",JLabel.CENTER));
	box1.add(���´����);
	box2.add(new JLabel("��ַ:",JLabel.CENTER));
	box2.add(��ַ);
    box4.add(new JLabel("�绰����:",JLabel.CENTER));
	box4.add(�绰����);
	box6.add(new JLabel("�������:",JLabel.CENTER));
	box6.add(�������);
	box3.add(�޸�);
	box5.add(new JLabel("���´����:",JLabel.CENTER));
	box5.add(���´����1);
	box5.add(����);
	
	�޸�.addActionListener(this);
    ����.addActionListener(this);
	
	Box boxH=Box.createVerticalBox();//����box
	boxH.add(box1);
	boxH.add(box2);
    boxH.add(box4);
    boxH.add(box6);
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
    String sql=null,sql1=null,sqlct=null;
	
	if(obj==����){if(���´����1.getText().equals(""))JOptionPane.showMessageDialog(this,"����д��ѯ�ı�ţ�" );
	else{
	    sql1="select * from Office where officeNo='"+���´����1.getText()+"'";
	    try{
	    Connection dbConn1=CONN();
		stmt=(Statement)dbConn1.createStatement();
		rs1=stmt.executeQuery(sql1);
	    if(rs1.next()){���´����.setText(rs1.getString("officeNo").trim());
        				��ַ.setText(rs1.getString("officeAddress").trim());
        					�绰����.setText(rs1.getString("officeTelNo").trim());
        					�������.setText(rs1.getString("officeFaxNo").trim());
        					save=���´����1.getText().trim();	    	 	
	    }
	    else{JOptionPane.showMessageDialog(this,"û��������´�" );}
	    stmt.close();
	    rs1.close();
	    }catch(SQLException e1){
	    	JOptionPane.showMessageDialog(this,e1.getMessage());
		   }
	    }
	}
	else{
	if(obj==�޸�){if(save==null){JOptionPane.showMessageDialog(this,"��û������Ҫ�޸ĵİ��´�" );}
	else{
		if(���´����.getText().equals("")||�绰����.getText().equals("")){
			JOptionPane.showMessageDialog(this,"������Ϣ���������޸ģ�" );
		}
		else{sql="update Office set officeNo='"+���´����.getText()+"',officeAddress='"+��ַ.getText()+"' ,officeTelNo='"+�绰����.getText()+"',officeFaxNo='"+�������.getText()+"' where officeNo='"+save+"'";
		if(save.trim().equals(���´����.getText().trim())){
		try{
		    Connection dbConn1=CONN();
			stmt=(Statement)dbConn1.createStatement();
			stmt.executeUpdate(sql);
			save=null;
			JOptionPane.showMessageDialog(this,"�޸����" );
			���´����.setText("");
            ��ַ.setText("");
            �绰����.setText("");
            �������.setText("");
            stmt.close();
		    }catch(SQLException e1){
		    	JOptionPane.showMessageDialog(this,e1.getMessage());
			   }
	}
		else{/*sql1="select * from Office where officeNo='"+���´����.getText()+"'";
		try{
		    Connection dbConn1=CONN();
			stmt=(Statement)dbConn1.createStatement();
			rs1=stmt.executeQuery(sql1);
		    if(rs1.next()){  	JOptionPane.showMessageDialog(this,"�Ѵ��ڴ˰��´�" );
		    }
		   
		    stmt.close();
		    rs1.close();
		    }catch(SQLException e1){
		    	JOptionPane.showMessageDialog(this,e1.getMessage());*/
			 JOptionPane.showMessageDialog(this,"�������޸Ŀͻ����" );
			   }
		
		}
	}}}}

//�������ݿⷽ��
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
