package util;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Menu extends JFrame implements ActionListener {
	Addstu ���ӿͻ���Ϣ����;
	Updatastu �޸Ŀͻ���Ϣ����;
	Delstu ɾ���ͻ���Ϣ����;
	AddO ���Ӱ��´�����;
	DelO ɾ�����´�����;
	UpdateO �޸İ��´�����;
	AddOrder ���Ӷ�������;
	DelOrder ɾ����������;
	UpdateOrder �޸Ķ�������;
	Addtrailer �����ϳ�����;
	Deltrailer ɾ���ϳ�����;
	Updatetrailer �޸��ϳ�����;
	AddTr �����������;
	DelTr ɾ���������;
	UpdateTr �޸��������;
	AddUn ������������;
	DelUn ɾ����������;
	UpdateUn �޸���������;
	
	JPanel pCenter;
	CardLayout card = null;
	JLabel label = null;
	JMenuBar mb = new JMenuBar();// �˵���
	JMenu m1 = new JMenu("�ͻ���Ϣ����");
	JMenuItem add1 = new JMenuItem("���ӿͻ�   ");
	JMenuItem updata1 = new JMenuItem("�޸Ŀͻ�   ");
	JMenuItem delete1 = new JMenuItem("ɾ���ͻ�   ");
	JMenu m2 = new JMenu("���´�����");
	JMenuItem add2 = new JMenuItem("���Ӱ��´�  ");
	JMenuItem updata2 = new JMenuItem("�޸İ��´�  ");
	JMenuItem delete2 = new JMenuItem("ɾ�����´�   ");
	JMenu m3 = new JMenu("��������");
	JMenuItem add3 = new JMenuItem("��Ӷ�����Ϣ   ");
	JMenuItem updata3 = new JMenuItem("�޸Ķ�����Ϣ   ");
	JMenuItem delete3 = new JMenuItem("ɾ��������Ϣ   ");
	JMenu m4 = new JMenu("�ϳ�����");
	JMenuItem add4 = new JMenuItem("����ϳ���Ϣ   ");
	JMenuItem updata4 = new JMenuItem("�޸��ϳ���Ϣ   ");
	JMenuItem delete4 = new JMenuItem("ɾ���ϳ���Ϣ   ");
	JMenu m5 = new JMenu("����Ҫ��");
	JMenuItem add5 = new JMenuItem("���������Ϣ   ");
	JMenuItem updata5 = new JMenuItem("�޸�������Ϣ   ");
	JMenuItem delete5 = new JMenuItem("ɾ��������Ϣ   ");
	JMenu m6 = new JMenu("��������");
	JMenuItem add6 = new JMenuItem("����������Ϣ   ");
	JMenuItem updata6 = new JMenuItem("�޸��������Ϣ   ");
	JMenuItem delete6 = new JMenuItem("ɾ���������Ϣ   ");
	JMenuItem m7 = new JMenuItem("ϵͳ�˳�");
	Font t = new Font("����", Font.PLAIN, 15);

	public Menu() {
		this.setTitle("�ͻ����͹���ϵͳ");

		// ��ϲ˵������
		addMenu1();
		addMenu2();
		addMenu3();
		addMenu4();
		addMenu5();
		addMenu6();
		addJMenuBar();
		setJMenuBar(mb);

		label = new JLabel("�ͻ����͹���ϵͳ", JLabel.CENTER);
		label.setFont(new Font("����", Font.BOLD, 26));
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setForeground(Color.black);
		// ����¼�
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

		���ӿͻ���Ϣ���� = new Addstu();
		�޸Ŀͻ���Ϣ���� = new Updatastu();
		ɾ���ͻ���Ϣ���� = new Delstu();
		���Ӱ��´����� = new AddO();
		ɾ�����´����� = new DelO();
		�޸İ��´����� = new UpdateO();
		���Ӷ�������    =new AddOrder();
		ɾ����������  =new DelOrder() ;
		�޸Ķ�������  = new UpdateOrder() ;
		�����ϳ����� = new Addtrailer();
		ɾ���ϳ����� = new Deltrailer();
		�޸��ϳ����� = new Updatetrailer();
		����������� =new AddTr() ;
		ɾ��������� =new DelTr() ;
		�޸�������� =new UpdateTr() ;
		������������ =new AddUn() ;
		ɾ���������� =new DelUn() ;
		�޸���������=new UpdateUn() ;


		pCenter.add("��ӭ����", label);//�������ӵ�����е�ָ��λ��
		pCenter.add("���ӿͻ���Ϣ����", ���ӿͻ���Ϣ����);
		pCenter.add("�޸Ŀͻ���Ϣ����", �޸Ŀͻ���Ϣ����);
		pCenter.add("ɾ���ͻ���Ϣ����", ɾ���ͻ���Ϣ����);
		pCenter.add("���Ӱ��´�����", ���Ӱ��´�����);
		pCenter.add("ɾ�����´�����", ɾ�����´�����);
		pCenter.add("�޸İ��´�����", �޸İ��´�����);
		pCenter.add("���Ӷ�������", ���Ӷ�������);
		pCenter.add("ɾ����������", ɾ����������);
		pCenter.add("�޸Ķ�������", �޸Ķ�������);
		pCenter.add("�����ϳ�����", �����ϳ�����);
		pCenter.add("ɾ���ϳ�����", ɾ���ϳ�����);
		pCenter.add("�޸��ϳ�����", �޸��ϳ�����);
		pCenter.add("�����������", �����������);
		pCenter.add("ɾ���������", ɾ���������);
		pCenter.add("�޸��������", �޸��������);
		pCenter.add("������������", ������������);
		pCenter.add("ɾ����������", ɾ����������);
		pCenter.add("�޸���������", �޸���������);
	


		add(pCenter, BorderLayout.CENTER);//���ñ߽粼�ַ�ʽ ���������
		
		setVisible(true);
		setBounds(400, 200, 600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {// �رճ���ʱ�Ĳ���
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

	private void addMenu2() {// ���˵����뵽�˵�����
		m2.add(add2);
		m2.add(updata2);
		m2.add(delete2);
		m2.setFont(t);
	}

	private void addMenu1() {
		m1.add(add1);
		m1.add(updata1);
		m1.add(delete1);
		m1.setFont(t);// ����
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == m7) {
			System.exit(0);
		} else {
			if (obj == add1) {
				card.show(pCenter, "���ӿͻ���Ϣ����");//��������ʾ��Ӧ�����
			} else {
				if (obj == updata1) {
					card.show(pCenter, "�޸Ŀͻ���Ϣ����");
				} else {
					if (obj == delete1) {
						card.show(pCenter, "ɾ���ͻ���Ϣ����");
					} else {
						if (obj == add2) {
							card.show(pCenter, "���Ӱ��´�����");
						} else {
							if (obj == delete2) {
								card.show(pCenter, "ɾ�����´�����");
							} else {
								if (obj == updata2) {
									card.show(pCenter, "�޸İ��´�����");
								} else {
									if (obj == add3) {
										card.show(pCenter, "���Ӷ�������");
									} else {
										if (obj == delete3) {
											card.show(pCenter, "ɾ����������");
										} else {
											if (obj == updata3) {
												card.show(pCenter, "�޸Ķ�������");
											} else {
												if (obj == add4) {
													card.show(pCenter, "�����ϳ�����");
												} else {
											
												if (obj == updata4) {
													card.show(pCenter, "�޸��ϳ�����");
												} else {
													if (obj == delete4) {
														card.show(pCenter, "ɾ���ϳ�����");
													} else {
														if (obj == add5) {
															card.show(pCenter, "�����������");
														} else {
															if (obj == delete5) {
																card.show(pCenter, "ɾ���������");
															} else {
																if (obj == updata5) {
																	card.show(pCenter, "�޸��������");
																} else {
																	if (obj == add6) {
																		card.show(pCenter, "������������");
																	} else {
																		if (obj == delete6) {
																			card.show(pCenter, "ɾ����������");
																		} else {
																			if (obj == updata6) {
																				card.show(pCenter, "�޸���������");
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

