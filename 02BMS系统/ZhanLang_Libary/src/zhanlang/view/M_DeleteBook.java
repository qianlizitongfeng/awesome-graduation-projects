package zhanlang.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import zhanlang.DAO.ManagerDAO;
import zhanlang.DAO.Imple.ManagerDAOImple;
import zhanlang.entity.Book;
import zhanlang.entity.User;

import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;

public class M_DeleteBook extends JFrame {

	private JPanel contentPane;
	private JTextField deleteId1;

	/**
	 * Create the frame.
	 */
	public M_DeleteBook(){
		setTitle("删除图书");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_1 = new JLabel("请输入图书编号 ：");
		label_1.setFont(new Font("楷体", Font.BOLD, 13));
		label_1.setBounds(60, 89, 121, 24);
		contentPane.add(label_1);
		
		deleteId1 = new JTextField();
		deleteId1.setFont(new Font("微软雅黑", Font.BOLD, 13));
		deleteId1.setBounds(191, 90, 159, 21);
		contentPane.add(deleteId1);
		deleteId1.setColumns(10);
		
		JButton button_4 = new JButton("");
		button_4.setIcon(new ImageIcon(M_DeleteBook.class.getResource("/picture/text/删除.jpg")));
		button_4.setBackground(new Color(211, 211, 211));
		button_4.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String id=deleteId1.getText();
				String command=new String("deleteBook");
				Book book=new Book(id);
				int x = JOptionPane.showConfirmDialog(null, "确认要删除吗？");
			
				if(x==0){
					ManagerDAO manager=new ManagerDAOImple();
					Object object=manager.deleteBook(command, book);
					if((boolean)object==true){
						JOptionPane.showMessageDialog(null, "图书删除成功！");
						
					}else{
					
					}
					//处理完上一次提交的操作申请，应当将输入框置空
					deleteId1.setText("");	
				}else{
					JOptionPane.showMessageDialog(null, "你是不是闲得慌~~?");
				}
					
				}
		});
		button_4.setBounds(92, 153, 93, 23);
		contentPane.add(button_4);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon(M_DeleteBook.class.getResource("/picture/text/返回.jpg")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client.mDeleteBookFrame.dispose();
			}
		});
		button.setBackground(new Color(211, 211, 211));
		button.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		button.setBounds(212, 153, 93, 23);
		contentPane.add(button);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(M_DeleteBook.class.getResource("/tubiao/删除图书.jpg")));
		label.setBounds(32, 89, 26, 24);
		contentPane.add(label);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(M_DeleteBook.class.getResource("/picture/text/添加图书背景.jpg")));
		label_2.setBounds(0, 0, 384, 262);
		contentPane.add(label_2);
	}
}
