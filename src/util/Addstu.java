package util;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;


public  class Addstu extends JPanel implements ActionListener{
	JTextField �ͻ����,����,�ֵ�,����,������,��������,�绰����,�������,��ҳ��ַ,��ϵ������,��ϵ�˵绰,��ϵ�˴���,��ϵ������,�칫�ұ��;
	JButton ¼��;
	
public Addstu(){
	try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
	catch(Exception e){System.err.println("�����������:   "+e);}
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
	¼��=new JButton("¼��");
	¼��.addActionListener(this);//����¼�����
	
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
	box1.add(new JLabel("�ͻ����:",JLabel.CENTER));
	box1.add(�ͻ����);
	box2.add(new JLabel("����:",JLabel.CENTER));
	box2.add(����);
	box3.add(new JLabel("�ֵ�:",JLabel.CENTER));
	box3.add(�ֵ�);
    box5.add(new JLabel("����:",JLabel.CENTER));
	box5.add(����);
    box6.add(new JLabel("������:",JLabel.CENTER));
	box6.add(������);
	box7.add(new JLabel("��������:",JLabel.CENTER));
	box7.add(��������);
	box8.add(new JLabel("�绰����:",JLabel.CENTER));
	box8.add(�绰����);
	box9.add(new JLabel("�������:",JLabel.CENTER));
	box9.add(�������);
	box10.add(new JLabel("��ҳ��ַ:",JLabel.CENTER));
	box10.add(��ҳ��ַ);
	box11.add(new JLabel("��ϵ������:",JLabel.CENTER));
	box11.add(��ϵ������);
	box12.add(new JLabel("��ϵ�˵绰:",JLabel.CENTER));
	box12.add(��ϵ�˵绰);
	box13.add(new JLabel("��ϵ�˴���:",JLabel.CENTER));
	box13.add(��ϵ�˴���);
	box14.add(new JLabel("��ϵ������:",JLabel.CENTER));
	box14.add(��ϵ������);
	box15.add(new JLabel("�칫�ұ��:",JLabel.CENTER));
	box15.add(�칫�ұ��);
	box4.add(¼��);
	Box boxH=Box.createVerticalBox();//����box
	boxH.add(box1);
	boxH.add(box2);
	boxH.add(box3);
    boxH.add(box5);
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
	boxH.add(box4);
	boxH.add(Box.createVerticalGlue());
	JPanel messPanel=new JPanel();
	messPanel.add(boxH);
	setLayout(new BorderLayout());
	add(messPanel,BorderLayout.CENTER);

	}
public void actionPerformed(ActionEvent c){
	Object obj=c.getSource();
	if(obj==¼��){
		if(�ͻ����.getText().equals("")||����.getText().equals("")||�绰����.getText().equals("")||��ϵ������.getText().equals("")||��ϵ�˵绰.getText().equals("")){
			JOptionPane.showMessageDialog(this,"������Ϣ��������¼�룡" );
		}
		Statement stmt=null;
		ResultSet rs1=null;
		String sql,sql1;
		    sql1="select * from Client where clientNo='"+�ͻ����.getText()+"'";
		    sql="insert into Client values('"+�ͻ����.getText()+"','"+����.getText()+"','"+�ֵ�.getText()+"','"+����.getText()+"','"+������.getText()+"','"+��������.getText()+"','"+�绰����.getText()+"','"+�������.getText()+"','"+��ҳ��ַ.getText()+"','"+��ϵ������.getText()+"','"+��ϵ�˵绰.getText()+"','"+��ϵ�˴���.getText()+"','"+��ϵ������.getText()+"','"+�칫�ұ��.getText()+"')";
	   try{
		   Connection dbConn1=CONN();
			stmt=(Statement)dbConn1.createStatement();
			rs1=stmt.executeQuery(sql1);
			if(rs1.next()){JOptionPane.showMessageDialog(this,"�ÿͻ�����Դ��ڣ��޷����");}
			else{
			stmt.executeUpdate(sql);	
			JOptionPane.showMessageDialog(this,"��ӳɹ�");
			}		
			rs1.close();
			stmt.close();
	   }
	   catch(SQLException e1){
		   JOptionPane.showMessageDialog(this,e1.getMessage());
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
