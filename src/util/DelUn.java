package util;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class DelUn extends JPanel implements ActionListener{
	String save=null;
	JTextField ���1,unitVehLicenseNo,unitDescription,maxPayLoad,officeNo;
	JButton ɾ��,����;
	
public DelUn(){
	try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
	catch(Exception e){System.err.println("�����������:   "+e);}
	���1=new JTextField(12);
	unitVehLicenseNo=new JTextField(12);
	unitDescription=new JTextField(12);
        maxPayLoad=new JTextField(12);
        officeNo=new JTextField(12);
	ɾ��=new JButton("ɾ��");
	����=new JButton("����");
	
	Box box1=Box.createHorizontalBox();//���box
	Box box2=Box.createHorizontalBox();
	Box box3=Box.createHorizontalBox();
	Box box4=Box.createHorizontalBox();
	Box box5=Box.createHorizontalBox();
	Box box6=Box.createHorizontalBox();
	box1.add(new JLabel("unitVehLicenseNo:",JLabel.CENTER));
	box1.add(unitVehLicenseNo);
	box2.add(new JLabel("unitDescription:",JLabel.CENTER));
	box2.add(unitDescription);
	box3.add(new JLabel("maxPayLoad:",JLabel.CENTER));
	box3.add(maxPayLoad);
	box4.add(new JLabel("officeNo:",JLabel.CENTER));
	box4.add(officeNo);
	box6.add(ɾ��);
	box5.add(new JLabel("unitVehLicenseNo:",JLabel.CENTER));
	box5.add(���1);
	box5.add(����);
	
	Box boxH=Box.createVerticalBox();//����box
	boxH.add(box1);
	boxH.add(box2);
	boxH.add(box3);
	boxH.add(box4);
    boxH.add(box6);
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
    String sql=null,sql1=null,sqlSC=null;
	if(obj==����){if(���1.getText().equals(""))JOptionPane.showMessageDialog(this,"����д��ѯ�İ�ţ�" );
	else{
	    sql1="select * from Unit where unitVehLicenseNo='"+���1.getText()+"'";
	    try{
	    Connection dbConn1=CONN();
		stmt=(Statement)dbConn1.createStatement();
		rs1=stmt.executeQuery(sql1);
	    if(rs1.next()){unitVehLicenseNo.setText(rs1.getString("unitVehLicenseNo").trim());
                            unitDescription.setText(rs1.getString("unitDescription").trim());
	                   maxPayLoad.setText(rs1.getString("maxPayLoad").trim());
                           officeNo.setText(rs1.getString("officeNo").trim());
	                   save=���1.getText().trim();	    	
	    }
	    else{JOptionPane.showMessageDialog(this,"û�������ŵİ�" );}
	    stmt.close();
	    rs1.close();
	    }catch(SQLException e1){
	    	JOptionPane.showMessageDialog(this,e1.getMessage());
		   }
	    }
	}
	else{
		if(obj==ɾ��){if(save==null)JOptionPane.showMessageDialog(this,"��û������Ҫɾ�����ϳ�" );
		else{sql="delete from TransportReqts where unitVehLicenseNo='"+save+"'";
		sqlSC="delete from Unit where unitVehLicenseNo='"+save+"'";
		try{
		    Connection dbConn1=CONN();
			stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			stmt.executeUpdate(sql);
			stmt.executeUpdate(sqlSC);
			save=null;
			JOptionPane.showMessageDialog(this,"ɾ�����" );
			unitVehLicenseNo.setText("");
                          unitDescription.setText("");
                            maxPayLoad.setText("");
                              officeNo.setText("");
			
			stmt.close();
		    }catch(SQLException e1){
		    	JOptionPane.showMessageDialog(this,e1.getMessage());
			   }
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

