package util;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Menu extends JFrame implements ActionListener {
	Addstu 增加客户信息界面;
	Updatastu 修改客户信息界面;
	Delstu 删除客户信息界面;
	AddO 增加办事处界面;
	DelO 删除办事处界面;
	UpdateO 修改办事处界面;
	AddOrder 增加订单界面;
	DelOrder 删除订单界面;
	UpdateOrder 修改订单界面;
	Addtrailer 增加拖车界面;
	Deltrailer 删除拖车界面;
	Updatetrailer 修改拖车界面;
	AddTr 增加运输界面;
	DelTr 删除运输界面;
	UpdateTr 修改运输界面;
	AddUn 增加运输班界面;
	DelUn 删除运输班界面;
	UpdateUn 修改运输班界面;
	
	JPanel pCenter;
	CardLayout card = null;
	JLabel label = null;
	JMenuBar mb = new JMenuBar();// 菜单条
	JMenu m1 = new JMenu("客户信息管理");
	JMenuItem add1 = new JMenuItem("增加客户   ");
	JMenuItem updata1 = new JMenuItem("修改客户   ");
	JMenuItem delete1 = new JMenuItem("删除客户   ");
	JMenu m2 = new JMenu("办事处管理");
	JMenuItem add2 = new JMenuItem("增加办事处  ");
	JMenuItem updata2 = new JMenuItem("修改办事处  ");
	JMenuItem delete2 = new JMenuItem("删除办事处   ");
	JMenu m3 = new JMenu("订单管理");
	JMenuItem add3 = new JMenuItem("添加订单信息   ");
	JMenuItem updata3 = new JMenuItem("修改订单信息   ");
	JMenuItem delete3 = new JMenuItem("删除订单信息   ");
	JMenu m4 = new JMenu("拖车管理");
	JMenuItem add4 = new JMenuItem("添加拖车信息   ");
	JMenuItem updata4 = new JMenuItem("修改拖车信息   ");
	JMenuItem delete4 = new JMenuItem("删除拖车信息   ");
	JMenu m5 = new JMenu("运输要求");
	JMenuItem add5 = new JMenuItem("添加运输信息   ");
	JMenuItem updata5 = new JMenuItem("修改运输信息   ");
	JMenuItem delete5 = new JMenuItem("删除运输信息   ");
	JMenu m6 = new JMenu("运输班管理");
	JMenuItem add6 = new JMenuItem("添加运输班信息   ");
	JMenuItem updata6 = new JMenuItem("修改运输班信息   ");
	JMenuItem delete6 = new JMenuItem("删除运输班信息   ");
	JMenuItem m7 = new JMenuItem("系统退出");
	Font t = new Font("宋体", Font.PLAIN, 15);

	public Menu() {
		this.setTitle("客户运送管理系统");

		// 组合菜单，添加
		addMenu1();
		addMenu2();
		addMenu3();
		addMenu4();
		addMenu5();
		addMenu6();
		addJMenuBar();
		setJMenuBar(mb);

		label = new JLabel("客户运送管理系统", JLabel.CENTER);
		label.setFont(new Font("宋体", Font.BOLD, 26));
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setForeground(Color.black);
		// 点击事件
		add1.addActionListener(this);
		updata1.addActionListener(this);
		delete1.addActionListener(this);
		add2.addActionListener(this);
		delete2.addActionListener(this);
		updata2.addActionListener(this);
		add3.addActionListener(this);
		delete3.addActionListener(this);
		updata3.addActionListener(this);
		add4.addActionListener(this);
		delete4.addActionListener(this);
		updata4.addActionListener(this);
		add5.addActionListener(this);
		delete5.addActionListener(this);
		updata5.addActionListener(this);
		add6.addActionListener(this);
		delete6.addActionListener(this);
		updata6.addActionListener(this);
		m7.addActionListener(this);
		card = new CardLayout();
		pCenter = new JPanel();
		pCenter.setLayout(card);

		增加客户信息界面 = new Addstu();
		修改客户信息界面 = new Updatastu();
		删除客户信息界面 = new Delstu();
		增加办事处界面 = new AddO();
		删除办事处界面 = new DelO();
		修改办事处界面 = new UpdateO();
		增加订单界面    =new AddOrder();
		删除订单界面  =new DelOrder() ;
		修改订单界面  = new UpdateOrder() ;
		增加拖车界面 = new Addtrailer();
		删除拖车界面 = new Deltrailer();
		修改拖车界面 = new Updatetrailer();
		增加运输界面 =new AddTr() ;
		删除运输界面 =new DelTr() ;
		修改运输界面 =new UpdateTr() ;
		增加运输班界面 =new AddUn() ;
		删除运输班界面 =new DelUn() ;
		修改运输班界面=new UpdateUn() ;


		pCenter.add("欢迎界面", label);//将组件添加到面板中的指定位置
		pCenter.add("增加客户信息界面", 增加客户信息界面);
		pCenter.add("修改客户信息界面", 修改客户信息界面);
		pCenter.add("删除客户信息界面", 删除客户信息界面);
		pCenter.add("增加办事处界面", 增加办事处界面);
		pCenter.add("删除办事处界面", 删除办事处界面);
		pCenter.add("修改办事处界面", 修改办事处界面);
		pCenter.add("增加订单界面", 增加订单界面);
		pCenter.add("删除订单界面", 删除订单界面);
		pCenter.add("修改订单界面", 修改订单界面);
		pCenter.add("增加拖车界面", 增加拖车界面);
		pCenter.add("删除拖车界面", 删除拖车界面);
		pCenter.add("修改拖车界面", 修改拖车界面);
		pCenter.add("增加运输界面", 增加运输界面);
		pCenter.add("删除运输界面", 删除运输界面);
		pCenter.add("修改运输界面", 修改运输界面);
		pCenter.add("增加运输班界面", 增加运输班界面);
		pCenter.add("删除运输班界面", 删除运输班界面);
		pCenter.add("修改运输班界面", 修改运输班界面);
	


		add(pCenter, BorderLayout.CENTER);//采用边界布局方式 将组件居中
		
		setVisible(true);
		setBounds(400, 200, 600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {// 关闭程序时的操作
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
	}

	private void addJMenuBar() {
		mb.add(m1);
		mb.add(m2);
		mb.add(m3);
		mb.add(m4);
		mb.add(m5);
		mb.add(m6);
		mb.add(m7);
	}

	private void addMenu6() {
		m6.add(add6);
		m6.add(updata6);
		m6.add(delete6);
		m6.setFont(t);
	}
	private void addMenu5() {
		m5.add(add5);
		m5.add(updata5);
		m5.add(delete5);
		m5.setFont(t);
	}
	private void addMenu4() {
		m4.add(add4);
		m4.add(updata4);
		m4.add(delete4);
		m4.setFont(t);
	}
	private void addMenu3() {
		m3.add(add3);
		m3.add(updata3);
		m3.add(delete3);
		m3.setFont(t);
	}

	private void addMenu2() {// 将菜单加入到菜单栏中
		m2.add(add2);
		m2.add(updata2);
		m2.add(delete2);
		m2.setFont(t);
	}

	private void addMenu1() {
		m1.add(add1);
		m1.add(updata1);
		m1.add(delete1);
		m1.setFont(t);// 字体
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == m7) {
			System.exit(0);
		} else {
			if (obj == add1) {
				card.show(pCenter, "增加客户信息界面");//让容器显示相应的组件
			} else {
				if (obj == updata1) {
					card.show(pCenter, "修改客户信息界面");
				} else {
					if (obj == delete1) {
						card.show(pCenter, "删除客户信息界面");
					} else {
						if (obj == add2) {
							card.show(pCenter, "增加办事处界面");
						} else {
							if (obj == delete2) {
								card.show(pCenter, "删除办事处界面");
							} else {
								if (obj == updata2) {
									card.show(pCenter, "修改办事处界面");
								} else {
									if (obj == add3) {
										card.show(pCenter, "增加订单界面");
									} else {
										if (obj == delete3) {
											card.show(pCenter, "删除订单界面");
										} else {
											if (obj == updata3) {
												card.show(pCenter, "修改订单界面");
											} else {
												if (obj == add4) {
													card.show(pCenter, "增加拖车界面");
												} else {
											
												if (obj == updata4) {
													card.show(pCenter, "修改拖车界面");
												} else {
													if (obj == delete4) {
														card.show(pCenter, "删除拖车界面");
													} else {
														if (obj == add5) {
															card.show(pCenter, "增加运输界面");
														} else {
															if (obj == delete5) {
																card.show(pCenter, "删除运输界面");
															} else {
																if (obj == updata5) {
																	card.show(pCenter, "修改运输界面");
																} else {
																	if (obj == add6) {
																		card.show(pCenter, "增加运输班界面");
																	} else {
																		if (obj == delete6) {
																			card.show(pCenter, "删除运输班界面");
																		} else {
																			if (obj == updata6) {
																				card.show(pCenter, "修改运输班界面");
																			} 
										}}}}}}}}}}
									}
								}
							}
						}
					}
				}
			}
			}	
			
	}

}

