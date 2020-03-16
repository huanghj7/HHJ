package util;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class DelUn extends JPanel implements ActionListener{
	String save=null;
	JTextField 班号1,unitVehLicenseNo,unitDescription,maxPayLoad,officeNo;
	JButton 删除,查找;
	
public DelUn(){
	try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
	catch(Exception e){System.err.println("不能设置外观:   "+e);}
	班号1=new JTextField(12);
	unitVehLicenseNo=new JTextField(12);
	unitDescription=new JTextField(12);
        maxPayLoad=new JTextField(12);
        officeNo=new JTextField(12);
	删除=new JButton("删除");
	查找=new JButton("查找");
	
	Box box1=Box.createHorizontalBox();//横放box
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
	box6.add(删除);
	box5.add(new JLabel("unitVehLicenseNo:",JLabel.CENTER));
	box5.add(班号1);
	box5.add(查找);
	
	Box boxH=Box.createVerticalBox();//竖放box
	boxH.add(box1);
	boxH.add(box2);
	boxH.add(box3);
	boxH.add(box4);
    boxH.add(box6);
	boxH.add(Box.createVerticalGlue());
	
	删除.addActionListener(this);
    查找.addActionListener(this);
	
	JPanel picPanel=new JPanel();
	JPanel messPanel=new JPanel();
	messPanel.add(box5);
	picPanel.add(boxH);
	setLayout(new BorderLayout());
	JSplitPane splitV=new JSplitPane(JSplitPane.VERTICAL_SPLIT,messPanel,picPanel);//分割
	add(splitV,BorderLayout.CENTER);
	validate();

}
public void actionPerformed(ActionEvent e){
	Object obj=e.getSource();
	Statement stmt=null;
	ResultSet rs=null,rs1=null;
    String sql=null,sql1=null,sqlSC=null;
	if(obj==查找){if(班号1.getText().equals(""))JOptionPane.showMessageDialog(this,"请填写查询的班号！" );
	else{
	    sql1="select * from Unit where unitVehLicenseNo='"+班号1.getText()+"'";
	    try{
	    Connection dbConn1=CONN();
		stmt=(Statement)dbConn1.createStatement();
		rs1=stmt.executeQuery(sql1);
	    if(rs1.next()){unitVehLicenseNo.setText(rs1.getString("unitVehLicenseNo").trim());
                            unitDescription.setText(rs1.getString("unitDescription").trim());
	                   maxPayLoad.setText(rs1.getString("maxPayLoad").trim());
                           officeNo.setText(rs1.getString("officeNo").trim());
	                   save=班号1.getText().trim();	    	
	    }
	    else{JOptionPane.showMessageDialog(this,"没有这个编号的班" );}
	    stmt.close();
	    rs1.close();
	    }catch(SQLException e1){
	    	JOptionPane.showMessageDialog(this,e1.getMessage());
		   }
	    }
	}
	else{
		if(obj==删除){if(save==null)JOptionPane.showMessageDialog(this,"还没查找需要删除的拖车" );
		else{sql="delete from TransportReqts where unitVehLicenseNo='"+save+"'";
		sqlSC="delete from Unit where unitVehLicenseNo='"+save+"'";
		try{
		    Connection dbConn1=CONN();
			stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			stmt.executeUpdate(sql);
			stmt.executeUpdate(sqlSC);
			save=null;
			JOptionPane.showMessageDialog(this,"删除完成" );
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

