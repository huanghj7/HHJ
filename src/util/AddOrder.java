package util;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;


public class AddOrder extends JPanel implements ActionListener {
	String saveC=null;
	String saveS=null;
	JTextField orderNo,dateOrder,collectionDate,collectionAddress,deliveryDate,deliveryAddress,loadWeight,loadDescription,clientNo;
	JButton ¼��;
	public AddOrder(){
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch(Exception e){System.err.println("�����������:   "+e);}
		
		orderNo=new JTextField(12);
		dateOrder=new JTextField(12);
		collectionDate=new JTextField(12);
	        collectionAddress=new JTextField(12);
	        deliveryDate=new JTextField(12);
	        deliveryAddress=new JTextField(12);
	        loadWeight=new JTextField(12);
	        loadDescription=new JTextField(12);
	        clientNo=new JTextField(12);
		¼��=new JButton("¼��");
		¼��.addActionListener(this);
		
		Box box1=Box.createHorizontalBox();//���box
		Box box2=Box.createHorizontalBox();
		Box box3=Box.createHorizontalBox();
		Box box4=Box.createHorizontalBox();
	        Box box5=Box.createHorizontalBox();
	        Box box6=Box.createHorizontalBox();
	        Box box7=Box.createHorizontalBox();
	        Box box8=Box.createHorizontalBox();
	        Box box9=Box.createHorizontalBox();
	        Box box10=Box.createHorizontalBox();
		box1.add(new JLabel("orderNo:"));
		box1.add(orderNo);
		box2.add(new JLabel("dateOrder:"));
		box2.add(dateOrder);
		box3.add(new JLabel("collectionDate:"));
		box3.add(collectionDate);
	        box4.add(new JLabel("collectionAddress:"));
		box4.add(collectionAddress);
	        box5.add(new JLabel("deliveryDate:"));
		box5.add(deliveryDate);
	        box6.add(new JLabel("deliveryAddress:"));
		box6.add(deliveryAddress);
	        box7.add(new JLabel("loadWeight:"));
		box7.add(loadWeight);
	        box8.add(new JLabel("loadDescription:"));
		box8.add(loadDescription);
	        box9.add(new JLabel("clientNo:"));
		box9.add(clientNo);
		box10.add(¼��);
		Box boxH=Box.createVerticalBox();//����box
		boxH.add(box1);
		boxH.add(box2);
		boxH.add(box3);
		boxH.add(box4);
	        boxH.add(box5);
	        boxH.add(box6);
	        boxH.add(box7);
	        boxH.add(box8);
	        boxH.add(box9);
	        boxH.add(box10);
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
			if(orderNo.getText().equals("")||clientNo.getText().equals("")){
				JOptionPane.showMessageDialog(this,"��д�����ź���ͻ���Ų���¼�룡" );
			}
			else
			{
			Statement stmt=null;
			ResultSet rs1=null;
			String sql,sql1;
			    sql1="select * from ClientOrder where orderNo='"+orderNo.getText()+"'";
			    sql="insert into ClientOrder values('"+orderNo.getText()+"','"+dateOrder.getText()+"','"+collectionDate.getText()+"','"+collectionAddress.getText()+"','"+deliveryDate.getText()+"','"+deliveryAddress.getText()+"','"+loadWeight.getText()+"','"+loadDescription.getText()+"','"+clientNo.getText()+"')";
		   try{
			   Connection dbConn1=CONN();
				stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				rs1=stmt.executeQuery(sql1);
				if(rs1.next()){JOptionPane.showMessageDialog(this,"�ÿͻ��Ѵ����ö����ţ��޷����");}
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