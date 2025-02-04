package zhanlang.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import zhanlang.DAO.UserDAO;
import zhanlang.DAO.Imple.UserDAOImple;
import zhanlang.entity.User;

public class U_ModifyMessage extends JFrame {

	private JPanel contentPane;
	private JTextField userName;
	private JTextField name;
	private JTextField age;
	private JComboBox city;
	private JRadioButton boy;
	private JRadioButton girl;
	private JPasswordField password1;
	private JPasswordField password2;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private JTextField tel;

	/**
	 * Create the frame.
	 */
	public U_ModifyMessage(){
		setTitle("修改信息");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(676, 10, 93, 77);
		contentPane.add(label);
		
		JButton button_4 = new JButton("");
		button_4.setIcon(new ImageIcon(U_ModifyMessage.class.getResource("/picture/text/提交.jpg")));
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String newPassword = null;
				String newUserName = userName.getText();
				String newName = name.getText();
				String newTel = tel.getText();
				String newCity = (String) city.getSelectedItem();
				String sex = "";
				if(boy.isSelected()){
					sex = "男";
				}else{
					sex = "女";
				}
				if(password1.getText().equals(password2.getText())){
					newPassword = password1.getText();
				}else{
					JOptionPane.showMessageDialog(null, "修改失败，请确保您两次输入的密码相同！");
					return;
				}
				
				UserDAO ud = new UserDAOImple();
				boolean b = ud.altInfo("alterInfo", new User(newName,sex,newTel,"",newCity,"",newPassword));
				if(b){
					//修改成功
					JOptionPane.showMessageDialog(null, "修改成功");
					Client.uModifyMessageFrame.dispose();
				}else{
					//修改失败
					JOptionPane.showMessageDialog(null, "修改失败");
					Client.uModifyMessageFrame.dispose();
				}
				
			}
		});
		button_4.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		button_4.setBackground(new Color(211, 211, 211));
		button_4.setBounds(195, 256, 85, 23);
		contentPane.add(button_4);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon(U_ModifyMessage.class.getResource("/picture/text/返回.jpg")));
		button.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client.showMessageFrame= new U_ShowMessage();
				Client.showMessageFrame.setVisible(true);	
				Client.showMessageFrame.setLocationRelativeTo(null);
				Client.uModifyMessageFrame.dispose();
			}
		});
		button.setBounds(313, 256, 85, 23);
		contentPane.add(button);
		
		JLabel label_1 = new JLabel("用户名：");
		label_1.setFont(new Font("楷体", Font.BOLD, 15));
		label_1.setBounds(93, 40, 66, 18);
		contentPane.add(label_1);
		
		userName = new JTextField(new LoginMenu().userlogin.getUserName());
		userName.setFont(new Font("楷体", Font.BOLD, 12));
		userName.setColumns(10);
		userName.setBounds(169, 40, 93, 21);
		contentPane.add(userName);
		
		JLabel label_2 = new JLabel("姓名：");
		label_2.setFont(new Font("楷体", Font.BOLD, 15));
		label_2.setBounds(303, 40, 55, 18);
		contentPane.add(label_2);
		
		name = new JTextField(new LoginMenu().userlogin.getName());
		name.setFont(new Font("楷体", Font.BOLD, 12));
		name.setColumns(10);
		name.setBounds(350, 40, 129, 21);
		contentPane.add(name);
		
		age = new JTextField(new LoginMenu().userlogin.getAge());
		age.setFont(new Font("楷体", Font.BOLD, 12));
		age.setColumns(10);
		age.setBounds(350, 82, 129, 21);
		contentPane.add(age);
		
		JLabel label_3 = new JLabel("年龄：");
		label_3.setFont(new Font("楷体", Font.BOLD, 15));
		label_3.setBounds(303, 82, 55, 18);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("联系方式：");
		label_4.setFont(new Font("楷体", Font.BOLD, 15));
		label_4.setBounds(93, 82, 85, 18);
		contentPane.add(label_4);
		
		JLabel label_6 = new JLabel("城市：");
		label_6.setFont(new Font("楷体", Font.BOLD, 15));
		label_6.setBounds(93, 122, 55, 18);
		contentPane.add(label_6);
		
		city = new JComboBox();
		city.setFont(new Font("楷体", Font.BOLD, 12));
		city.setModel(new DefaultComboBoxModel(new String[] {"北京市 ", "天津市", "上海市", "重庆市", "河北省", "山西省", "辽宁省", "吉林省", "黑龙江省", "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省", "河南省", "湖北省", "湖南省", "广东省", "海南省", "四川省", "贵州省", "云南省", "陕西省", "甘肃省", "青海省", "台湾省", "内蒙古自治区", "广西壮族自治区", "西藏自治区", "宁夏回族自治区", "新疆维吾尔自治区", "香港特别行政区", "澳门特别行政区"}));
		city.setBounds(168, 122, 93, 21);
		contentPane.add(city);
		
		JLabel label_7 = new JLabel("性别：");
		label_7.setFont(new Font("楷体", Font.BOLD, 15));
		label_7.setBounds(303, 122, 55, 18);
		contentPane.add(label_7);
		
		boy = new JRadioButton("男");
		boy.setFont(new Font("楷体", Font.BOLD, 12));
		boy.setBounds(364, 121, 42, 23);
		contentPane.add(boy);
		
		girl = new JRadioButton("女");
		girl.setFont(new Font("楷体", Font.BOLD, 12));
		girl.setBounds(420, 122, 42, 23);
		contentPane.add(girl);
		
		JLabel label_8 = new JLabel("输入密码：");
		label_8.setFont(new Font("楷体", Font.BOLD, 15));
		label_8.setBounds(93, 162, 85, 18);
		contentPane.add(label_8);
		
		password1 = new JPasswordField();
		password1.setBounds(168, 162, 129, 21);
		contentPane.add(password1);
		
		JLabel label_9 = new JLabel("重复密码：");
		label_9.setFont(new Font("楷体", Font.BOLD, 15));
		label_9.setBounds(93, 202, 85, 18);
		contentPane.add(label_9);
		
		password2 = new JPasswordField();
		password2.setBounds(168, 202, 129, 21);
		contentPane.add(password2);
		
		tel = new JTextField((String) null);
		tel.setFont(new Font("楷体", Font.BOLD, 12));
		tel.setColumns(10);
		tel.setBounds(169, 82, 93, 21);
		contentPane.add(tel);
		
		JLabel label_5 = new JLabel("");
		label_5.setIcon(new ImageIcon(U_ModifyMessage.class.getResource("/picture/text/借书页面背景.jpg")));
		label_5.setBounds(0, 0, 584, 362);
		contentPane.add(label_5);
	}
}
