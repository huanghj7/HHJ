package util;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class DelO extends JPanel implements ActionListener{
	String save=null;
	JTextField ���´����1,���´����,��ַ,�绰����,�������;
	JButton ɾ��,����;
	
public DelO(){
	try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
	catch(Exception e){System.err.println("�����������:   "+e);}
	
	���´����1=new JTextField(12);
	���´����=new JTextField(12);
	��ַ=new JTextField(12);
	�绰����=new JTextField(12);
    �������=new JTextField(12);
	ɾ��=new JButton("ɾ��");
	����=new JButton("����");
	
	Box box1=Box.createHorizontalBox();//���box
	Box box2=Box.createHorizontalBox();
	Box box3=Box.createHorizontalBox();
	Box box4=Box.createHorizontalBox();
	Box box5=Box.createHorizontalBox();
	Box box6=Box.createHorizontalBox();
	box1.add(new JLabel("���´����:"));
	box1.add(���´����);
	box2.add(new JLabel("��ַ:"));
	box2.add(��ַ);
    box3.add(new JLabel("�绰����:"));
	box3.add(�绰����);
	box6.add(new JLabel("�������:"));
	box6.add(�������);
	box4.add(ɾ��);
	box5.add(new JLabel("���´����:",JLabel.CENTER));
	box5.add(���´����1);
	box5.add(����);
	Box boxH=Box.createVerticalBox();//����box
	boxH.add(box1);
	boxH.add(box2);
	boxH.add(box3);
	boxH.add(box6);
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
	ResultSet rs=null,rs1=null,rs2=null,rs3=null;
    String sql=null,sql1=null,sql2=null,sql3=null,sql4=null,sql5=null,sql6=null,sql7=null,sql8=null;
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
		if(obj==ɾ��){if(save==null)JOptionPane.showMessageDialog(this,"��û������Ҫɾ���İ��´�" );
		else{
			sql6="delete from TransportReqts where trailerVehlicenseNo1 in( select trailerVehlicenseNo1 from TransportReqts,Trailer where TransportReqts.trailerVehlicenseNo1=Trailer.trailerVehlicenseNo and Trailer.officeNo='"+save+"')";
			sql5="delete from TransportReqts where unitVehLicenseNo in (select TransportReqts.unitVehLicenseNo from Unit, TransportReqts where Unit.unitVehLicenseNo=TransportReqts.unitVehLicenseNo and Unit.officeNo='"+save+"')";
			sql4="delete from Unit where officeNo='"+save+"'" ;
			sql3="delete from Trailer where officeNo='"+save+"'";
			sql2="delete from ClientOrder where clientNo in(select ClientOrder.clientNo from Client,ClientOrder where ClientOrder.clientNo=Client.clientNo and Client.officeNo='"+save+"')";
			sql1="delete from Client where officeNo='"+save+"'";
			sql="delete from Office where officeNo='"+save+"'";
		try{
		    Connection dbConn1=CONN();
			stmt=(Statement)dbConn1.createStatement();
			stmt.executeUpdate(sql6);
			stmt.executeUpdate(sql5);
			stmt.executeUpdate(sql4);
			stmt.executeUpdate(sql3);
			stmt.executeUpdate(sql2);
			stmt.executeUpdate(sql1);
			stmt.executeUpdate(sql);
			save=null;
			JOptionPane.showMessageDialog(this,"ɾ�����" );
			���´����.setText("");
            ��ַ.setText("");
            �绰����.setText("");
            �������.setText("");
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
