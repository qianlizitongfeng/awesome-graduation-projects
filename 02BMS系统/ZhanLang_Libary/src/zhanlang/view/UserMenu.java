package zhanlang.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import zhanlang.DAO.UserDAO;
import zhanlang.DAO.Imple.UserDAOImple;
import zhanlang.entity.Book;

import java.awt.Canvas;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class UserMenu extends JFrame {

	private JPanel contentPane;
	static LoginMenu loginFrame;
	/**
	 * Create the frame.
	 */
	public UserMenu() {
		setFont(new Font("楷体", Font.BOLD, 12));
		setTitle("BMS");
		/***/
		setBackground(new Color(189, 183, 107));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		/**创建用户页面窗体，大小900*600  */
		setBounds(100, 100,900, 585);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu_1 = new JMenu("浏览");
		menu_1.setFont(new Font("楷体", Font.BOLD, 15));
		menuBar.add(menu_1);
		
		JMenuItem menuItem_2 = new JMenuItem("浏览");
		menuItem_2.setFont(new Font("楷体", Font.BOLD, 15));
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client.uBorrowbookFrame2= new U_BorrowBook();
				Client.uBorrowbookFrame2.setVisible(true);	
				Client.uBorrowbookFrame2.setLocationRelativeTo(null);
			}
		});
		menu_1.add(menuItem_2);
		
		JMenu menu = new JMenu("查看");
		menu.setFont(new Font("楷体", Font.BOLD, 14));
		menuBar.add(menu);
		JMenuItem menuItem = new JMenuItem("可借书籍");
		menuItem.setFont(new Font("楷体", Font.BOLD, 15));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client.showAllBookFrame= new U_ShowAllBook();
				Client.showAllBookFrame.setVisible(true);	
				Client.showAllBookFrame.setLocationRelativeTo(null);
			}
		});
		menu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("已借书籍");
		menuItem_1.setFont(new Font("楷体", Font.BOLD, 15));
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client.showCanAllFrame= new U_ShowAlready();
				Client.showCanAllFrame.setVisible(true);
				Client.showCanAllFrame.setLocationRelativeTo(null);
			}
		});
		menu.add(menuItem_1);
		
		JMenu menu_2 = new JMenu("用户");
		menu_2.setFont(new Font("楷体", Font.BOLD, 15));
		menuBar.add(menu_2);
		
		JMenuItem menuItem_4 = new JMenuItem("个人信息");
		menuItem_4.setFont(new Font("楷体", Font.BOLD, 15));
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client.showMessageFrame = new U_ShowMessage();
				Client.showMessageFrame.setVisible(true);
				Client.showMessageFrame.setLocationRelativeTo(null);
			}
		});
		menu_2.add(menuItem_4);
		
		JMenu menu_3 = new JMenu("退出");
		menu_3.setFont(new Font("楷体", Font.BOLD, 15));
		menu_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client.loginMenuFrame=new LoginMenu();
				Client.loginMenuFrame.setVisible(true);
				Client.userMenuFrame.dispose();
			}
		});
		JMenu menu_4 = new JMenu("帮助");
		menu_4.setFont(new Font("楷体", Font.BOLD, 15));
		menuBar.add(menu_4);
		
		JMenuItem menuItem_6 = new JMenuItem("关于我们");
		menuItem_6.setFont(new Font("楷体", Font.BOLD, 15));
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client.about=new M_About();
				Client.about.setVisible(true);
				Client.about.setLocationRelativeTo(null);
			}
		});
		menu_4.add(menuItem_6);
		menuBar.add(menu_3);
		
		JMenuItem menuItem_5 = new JMenuItem("退出登录");
		menuItem_5.setFont(new Font("楷体", Font.BOLD, 15));
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client.loginMenuFrame= new LoginMenu();	
				Client.loginMenuFrame.setVisible(true);	
				Client.loginMenuFrame.setLocationRelativeTo(null);
				Client.userMenuFrame.dispose();
			}
		});
		menu_3.add(menuItem_5);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(UserMenu.class.getResource("/picture/text/客户端背景.jpg")));
		label.setBounds(0, 0, 884, 531);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(0, 0, 54, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(0, 0, 54, 15);
		contentPane.add(label_2);
	}
}
