package util;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class AddUn extends JPanel implements ActionListener{
	JTextField unitVehLicenseNo,unitDescription,maxPayLoad,officeNo;
	JButton 录入;
	public AddUn(){
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch(Exception e){System.err.println("不能设置外观:   "+e);}
		
		unitVehLicenseNo=new JTextField(12);
		unitDescription=new JTextField(12);
	        maxPayLoad=new JTextField(12);
	        officeNo=new JTextField(12);

		录入=new JButton("录入");
		录入.addActionListener(this);
		
		Box box1=Box.createHorizontalBox();//横放box
		Box box2=Box.createHorizontalBox();
		Box box3=Box.createHorizontalBox();
		Box box4=Box.createHorizontalBox();
	        Box box5=Box.createHorizontalBox();

		box1.add(new JLabel("unitVehLicenseNo:",JLabel.CENTER));
		box1.add(unitVehLicenseNo);
		box2.add(new JLabel(" unitDescription:",JLabel.CENTER));
		box2.add(unitDescription);
		box3.add(new JLabel("   maxPayLoad   :",JLabel.CENTER));
		box3.add(maxPayLoad);
	    box5.add(new JLabel("    officeNo    :",JLabel.CENTER));
		box5.add(officeNo);
		box4.add(录入);
		Box boxH=Box.createVerticalBox();//竖放box
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
		if(obj==录入){
			if(unitVehLicenseNo.getText().equals("")||maxPayLoad.getText().equals("")||officeNo.getText().equals("")){
				JOptionPane.showMessageDialog(this,"请填满再录入！" );
			}
			Statement stmt=null;
			ResultSet rs1=null;
			String sql,sql1;
			    sql1="select * from Unit where unitVehLicenseNo='"+unitVehLicenseNo.getText()+"'";
			    sql="insert into Unit values('"+unitVehLicenseNo.getText()+"','"+unitDescription.getText()+"','"+maxPayLoad.getText()+"','"+officeNo.getText()+"')";
		   try{
			   Connection dbConn1=CONN();
				stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				rs1=stmt.executeQuery(sql1);
				if(rs1.next()){JOptionPane.showMessageDialog(this,"该车牌号以存在，无法添加");}
				else{
				stmt.executeUpdate(sql);	
				JOptionPane.showMessageDialog(this,"添加成功");
				}		
				rs1.close();
				stmt.close();
		   }
		   catch(SQLException e){
			   JOptionPane.showMessageDialog(this,e.getMessage());
			   }
		}
	}
	public static Connection CONN(){
	    String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";   //加载JDBC驱动
	   String dbURL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=ClientT";
	       String userName = "sa";   //默认用户名
	   String userPwd = "12345";   //密码
	   Connection dbConn=null;
	   try {
	   Class.forName(driverName);
	   dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
	   System.out.println("Connection Successful!");   //如果连接成功 控制台输出Connection Successful!
	   } catch (Exception e) {
	   e.printStackTrace();
	   }
	   return dbConn;
	}
}

