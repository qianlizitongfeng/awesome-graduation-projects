package zhanlang.DAO.Imple;

import java.util.ArrayList;
import java.util.List;

import zhanlang.DAO.UserDAO;
import zhanlang.entity.Book;
import zhanlang.entity.Person;
import zhanlang.entity.User;

public class UserDAOImple implements UserDAO{
	DataExchange de = new DataExchange();
	/**
	 * 登录
	 */
	@Override
	public User login(String command,Object loginPerson) {
		User user = null;
		try {
			de.doWork(command, loginPerson);
			Object obj = de.getObject();
			System.out.println("是不是Person类" + (obj instanceof Person));
		//	System.out.println(obj.toString());
			if(obj instanceof Person){
				user = (User) obj;
				return  user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	/**
	 * 显示可借书籍
	 */
	public List<Book> showCanAll(String command,Object book) {
		List<Book> bookList = null;
		try {
			de.doWork(command, book);
			Object obj = de.getObject();
			if(obj instanceof List){
				bookList = (List<Book>) obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookList;
	}

	
	@Override
	/**
	 * 显示被借出书籍
	 */
	public List<Book> showAlready(String command,Person user) {
		List<Book> bookList = null;
		try {
			de.doWork(command, user);
			Object obj = de.getObject();
			if(obj instanceof List){
				bookList = (List<Book>) obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookList;
	}

	@Override
	/**
	 * 通过图书ID借书
	 */
	public Boolean borrowById(String command,Object book) {
		Boolean flage = false;
		try {
			de.doWork(command, book);
			Object obj = de.getObject();
			if(obj instanceof Boolean){
				flage = (Boolean) obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flage;
	}
	
	/**
	 * 通过id还书
	 */
	@Override
	public Boolean returnById(String command,Object book) {
		Boolean flage = false;
		try {
			de.doWork(command, book);
			Object obj = de.getObject();
			if(obj instanceof Boolean){
				flage = (Boolean) obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flage;
	}
	
	/**
	 * 注册
	 */
	@Override
	public boolean regester(String command,Object user) {
		boolean flage = false;
		try {
			flage = de.doWork(command, user);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flage;
	}
	
//	/**
//	 * 查看个人信息
//	 */
//	@Override
//	public void alterInfo() {
//		
//	}
	
	/**
	 * 根据图书编号查询图书
	 */
	@Override
	public Book selectById(String command,Object book) {
		Book book1 = null;
		try {
			de.doWork(command, book);
			Object obj = de.getObject();
			if(obj instanceof Book){
				book1 = (Book) obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book1;
	}

	/**
	 * 根据类型查找书籍
	 */
	@Override
	public List<Book> selectByType(String command, Object book) {
		List<Book> bookList = null;
		try {
			de.doWork(command, book);
			Object obj = de.getObject();
			if(obj instanceof List){
				bookList = (List<Book>) obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("我已经收到了：" + bookList);
		return bookList;
	}

	/**
	 * 修改个人信息
	 */
	@Override
	public boolean altInfo(String command, Object user) {
		// TODO Auto-generated method stub
		boolean flage = false;
		try {
			flage = de.doWork(command, user);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flage;
	
	}
	
	

}
