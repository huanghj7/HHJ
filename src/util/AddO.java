package util;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class AddO extends JPanel implements ActionListener{//JFrame ��Java�Ĵ�����,ʵ�� ActionListener������������ �ӿڣ�
	JTextField ���´����,��ַ,�绰����,�������;
	JButton ¼��;
	
public AddO(){
	try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
	catch(Exception e){System.err.println("�����������:   "+e);}
	
	���´����=new JTextField(12);
	��ַ=new JTextField(12);
    �绰����=new JTextField(12);
    �������=new JTextField(12);
	¼��=new JButton("¼��");
	¼��.addActionListener(this);
	
	Box box1=Box.createHorizontalBox();//���box
	Box box2=Box.createHorizontalBox();
	Box box3=Box.createHorizontalBox();
	Box box4=Box.createHorizontalBox();
    Box box5=Box.createHorizontalBox();
	box1.add(new JLabel("���´����:"));
	box1.add(���´����);
	box2.add(new JLabel("��ַ:"));
	box2.add(��ַ);
    box3.add(new JLabel("�绰����:"));
	box3.add(�绰����);
	box5.add(new JLabel("�������:"));
	box5.add(�������);
	box4.add(¼��);
	Box boxH=Box.createVerticalBox();//����box
	boxH.add(box1);
	boxH.add(box2);
	boxH.add(box3);
    boxH.add(box5);
	boxH.add(box4);
	boxH.add(Box.createVerticalGlue());
	JPanel messPanel=new JPanel();
	messPanel.add(boxH);
	setLayout(new BorderLayout());
	add(messPanel,BorderLayout.CENTER);
	validate();
}
public void actionPerformed(ActionEvent c){
	Object obj=c.getSource();
	if(obj==¼��){
		if(���´����.getText().equals("")||�绰����.getText().equals("")){
			JOptionPane.showMessageDialog(this,"������Ϣ��������¼�룡" );
		}
		Statement stmt=null;
		ResultSet rs1=null;
		String sql,sql1;
		    sql1="select * from Office where officeNo='"+���´����.getText()+"'";
		    sql="insert into Office values('"+���´����.getText()+"','"+��ַ.getText()+"','"+�绰����.getText()+"','"+�������.getText()+"')";
	   try{
		   Connection dbConn1=CONN();
			stmt=(Statement)dbConn1.createStatement();
			rs1=stmt.executeQuery(sql1);
			if(rs1.next()){JOptionPane.showMessageDialog(this,"�ñ���Ѿ����ڣ��޷����");}
			else{
			stmt.executeUpdate(sql);	
			JOptionPane.showMessageDialog(this,"��ӳɹ�");
			}		
			rs1.close();
			
			stmt.close();
	   }
	   catch(SQLException e){
		   JOptionPane.showMessageDialog(this,e.getMessage());
		   }
	}
}

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