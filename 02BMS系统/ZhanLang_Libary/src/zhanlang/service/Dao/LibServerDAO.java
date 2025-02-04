package zhanlang.service.Dao;

import java.util.List;
import zhanlang.entity.Book;
import zhanlang.entity.Person;
import zhanlang.entity.User;


public interface LibServerDAO {
	/**
	 * 注册
	 * @return
	 */
	public abstract boolean addUser(Person per);
	
	/**
	 * 登录
	 * @return
	 */
	public abstract Person login(Person per);
	
	/**
	 * 查看可借图书信息
	 * @return
	 */
	public abstract List<Book> showCanAll();
	
	/**
	 * 查看已借图书信息
	 * @return
	 */
	public abstract List<Book> showAlready(Person per);
	
	/**
	 * 根据图书编号完成借书
	 * @return
	 */
	public abstract boolean borrowById(Book book,Person per);
	
	/**
	 * 根据图书编号还书
	 * @return
	 */
	public abstract boolean returnById(Book book);
	
	/**
	 * 修改个人信息（修改密码）
	 * @return
	 */
	public abstract boolean alterInfo(Object obj,String property,String content);
	
	/**
	 * 	根据图书编号查询图书信息
	 * @return
	 */
	public abstract Book selectById(Book book);

	
	/**
	 * 删除用户
	 * @param per
	 * @return
	 */
	public abstract boolean deleteUser(Person per);
	
	/**
	 * 添加图书
	 * @param book
	 * @return
	 */
	public abstract boolean addBook(Book book);
	
	/**
	 * 删除图书信息
	 * @param book
	 * @return
	 */
	public abstract boolean deleteBook(Book book);
	/**
	 * 根据类型查书
	 * @param book
	 * @return
	 */
	public abstract List<Book> selectByType(Book book);
	
	/**
	 * 查询所有借书信息
	 * @return
	 */
	public abstract List<List<Book>> borrowInfo();
}
