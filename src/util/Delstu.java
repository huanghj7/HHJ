package util;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;


public class Delstu extends JPanel implements ActionListener{
String delete=null;
JTextField �ͻ����1,�ͻ����,����,�ֵ�,����,������,��������,�绰����,�������,��ҳ��ַ,��ϵ������,��ϵ�˵绰,��ϵ�˴���,��ϵ������,�칫�ұ��;
JButton ɾ��,����;
	
public Delstu(){
try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
catch(Exception e){System.err.println("�����������:   "+e);}
	
�ͻ����1=new JTextField(12);
�ͻ����=new JTextField(12);
����=new JTextField(12);
�ֵ�=new JTextField(12);
����=new JTextField(12);
������=new JTextField(12);
��������=new JTextField(12);
�绰����=new JTextField(12);
�������=new JTextField(12);
��ҳ��ַ=new JTextField(12);
��ϵ������=new JTextField(12);
��ϵ�˵绰=new JTextField(12);
��ϵ�˴���=new JTextField(12);
��ϵ������=new JTextField(12);
�칫�ұ��=new JTextField(12);    
ɾ��=new JButton("ɾ��");
����=new JButton("����");

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
Box box11=Box.createHorizontalBox();
Box box12=Box.createHorizontalBox();
Box box13=Box.createHorizontalBox();
Box box14=Box.createHorizontalBox();
Box box15=Box.createHorizontalBox();
Box box16=Box.createHorizontalBox();
box1.add(new JLabel("�ͻ����:",JLabel.CENTER));
box1.add(�ͻ����);
box2.add(new JLabel("����:",JLabel.CENTER));
box2.add(����);
box3.add(new JLabel("�ֵ�:",JLabel.CENTER));
box3.add(�ֵ�);
box6.add(new JLabel("����:",JLabel.CENTER));
box6.add(����);
box7.add(new JLabel("������:",JLabel.CENTER));
box7.add(������);
box8.add(new JLabel("��������:",JLabel.CENTER));
box8.add(��������);
box9.add(new JLabel("�绰����:",JLabel.CENTER));
box9.add(�绰����);
box10.add(new JLabel("�������:",JLabel.CENTER));
box10.add(�������);
box11.add(new JLabel("��ҳ��ַ:",JLabel.CENTER));
box11.add(��ҳ��ַ);
box12.add(new JLabel("��ϵ������:",JLabel.CENTER));
box12.add(��ϵ������);
box13.add(new JLabel("��ϵ�˵绰:",JLabel.CENTER));
box13.add(��ϵ�˵绰);
box14.add(new JLabel("��ϵ�˴���:",JLabel.CENTER));
box14.add(��ϵ�˴���);
box15.add(new JLabel("��ϵ������:",JLabel.CENTER));
box15.add(��ϵ������);
box16.add(new JLabel("�칫�ұ��:",JLabel.CENTER));
box16.add(�칫�ұ��);
box4.add(ɾ��);
box5.add(new JLabel("�ͻ����:",JLabel.CENTER));
box5.add(�ͻ����1);
box5.add(����);
Box boxH=Box.createVerticalBox();//����box
boxH.add(box1);
boxH.add(box2);
boxH.add(box3);
boxH.add(box6);
boxH.add(box7);
boxH.add(box8);
boxH.add(box9);
boxH.add(box10);
boxH.add(box11);
boxH.add(box12);
boxH.add(box13);
boxH.add(box14);
boxH.add(box15);
boxH.add(box16);
boxH.add(box4);
boxH.add(Box.createVerticalGlue());

ɾ��.addActionListener(this);
����.addActionListener(this);

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
    String sql=null,sql1=null,sql2=null;

if(obj==����){if(�ͻ����1.getText().equals(""))JOptionPane.showMessageDialog(this,"����д��ѯ�Ŀͻ���ţ�" );
else{
    sql1="select * from Client where clientNo='"+�ͻ����1.getText()+"'";
    try{
    Connection dbConn1=CONN();
stmt=(Statement)dbConn1.createStatement();
rs1=stmt.executeQuery(sql1);
    if(rs1.next()){�ͻ����.setText(rs1.getString("clientNo").trim());
    ����.setText(rs1.getString("clientName").trim());
    �ֵ�.setText(rs1.getString("clientStreet").trim());
    ����.setText(rs1.getString("clientCity").trim());
    ������.setText(rs1.getString("clientState").trim());
    ��������.setText(rs1.getString("clientZipCode").trim());
    �绰����.setText(rs1.getString("clientTelNo").trim());
    �������.setText(rs1.getString("clientFaxNo").trim());
    ��ҳ��ַ.setText(rs1.getString("clientWebAddress").trim());
    ��ϵ������.setText(rs1.getString("contactName").trim());
    ��ϵ�˵绰.setText(rs1.getString("contactTelNo").trim());
    ��ϵ�˴���.setText(rs1.getString("contactFaxNo").trim());
    ��ϵ������.setText(rs1.getString("contactEmailNo").trim());
    �칫�ұ��.setText(rs1.getString("officeNo").trim());
    delete=�ͻ����1.getText();	    	
    }
    else{JOptionPane.showMessageDialog(this,"û������ͻ�" );}
    stmt.close();
    rs1.close();
    }catch(SQLException e1){
    	JOptionPane.showMessageDialog(this,e1.getMessage());
   }
    }
}
else{
if(obj==ɾ��){if(delete==null)JOptionPane.showMessageDialog(this,"��û������Ҫɾ����ѧ��" );
else{sql2="delete from ClientOrder where clientNo='"+delete+"'";
	sql="delete from Client where clientNo='"+delete+"'";
     
try{
    Connection dbConn1=CONN();
stmt=(Statement)dbConn1.createStatement();
stmt.executeUpdate(sql2);
stmt.executeUpdate(sql);
delete=null;
JOptionPane.showMessageDialog(this,"ɾ�����" );
�ͻ����.setText("");
����.setText("");
�ֵ�.setText("");
����.setText("");
������.setText("");
��������.setText("");
�绰����.setText("");
�������.setText("");
��ҳ��ַ.setText("");
��ϵ������.setText("");
��ϵ�˵绰.setText("");
��ϵ�˴���.setText("");
��ϵ������.setText("");
�칫�ұ��.setText("");

stmt.close();
    }catch(SQLException e1){
    	JOptionPane.showMessageDialog(this,e1.getMessage());
   }
}
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
