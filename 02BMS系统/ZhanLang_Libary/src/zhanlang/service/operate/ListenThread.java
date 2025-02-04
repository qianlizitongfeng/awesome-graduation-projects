package zhanlang.service.operate;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

import zhanlang.entity.Book;
import zhanlang.entity.Person;
import zhanlang.entity.ServiceFrame;
import zhanlang.service.Dao.Imple.LibServerDAOimple;



public class ListenThread implements Runnable{
	private Socket socket;
	
	public ListenThread(Socket socket) {
		super();
		this.socket = socket;
	}
	/**
	 * 用来处理每个用户,每个用户一个线程
	 */
	@Override
	public void run() {
		//当前用户
		Person person = new Person();
		//执行循环的次数
		int i=1;
		//服务器输入流
		InputStream is=null;
		//服务器的输出流
		OutputStream os=null;
		//当前时间
		Date date=new Date();
		try {
			//获取服务器的输入流，对应客户端的输出流
			is=this.socket.getInputStream();
		} catch (IOException e1) {
			//一般为客户端关闭程序，造成与服务器断开链接 
			e1.printStackTrace();
			//处理用户断开后的退出登录处理
			if(person!=null){
				System.out.println(Thread.currentThread().getName()+"---"+person.getUserName()+" 用户断开！"+"时间是："+"["+(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"]");
				System.out.println("正在删除："+person);
				System.out.println("容器删除前："+ServerListen.list);
				ServerListen.list.remove(person);
				System.out.println("容器删除后："+ServerListen.list);
			}else{
				System.out.println(Thread.currentThread().getName()+"---"+" 用户断开！"+"时间是："+"["+(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"]");
			}
			//刷新当前在线用户
			ServiceFrame.jta1.setText("");
			for (Person per : ServerListen.list) {
				ServiceFrame.jta1.append(per.getUserName());
				ServiceFrame.jta1.append("\n");
			}
			return ;
			//e1.printStackTrace();
		}
		try {
			//获取服务器的输出流，对应客户端的输入流
			os=this.socket.getOutputStream();
		} catch (IOException e) {
			//一般为客户端关闭程序，造成与服务器断开链接 
			e.printStackTrace();
			//处理用户断开后的退出登录处理
			if(person!=null){
				System.out.println(Thread.currentThread().getName()+"---"+person.getUserName()+" 用户断开！"+"时间是："+"["+(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"]");
				System.out.println("正在删除："+person);
				System.out.println("容器删除前："+ServerListen.list);
				ServerListen.list.remove(person);
				System.out.println("容器删除后："+ServerListen.list);
			}else{
				System.out.println(Thread.currentThread().getName()+"---"+" 用户断开！"+"时间是："+(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"]");
			}
			//刷新当前在线用户
			ServiceFrame.jta1.setText("");
			for (Person per : ServerListen.list) {
				ServiceFrame.jta1.append(per.getUserName());
				ServiceFrame.jta1.append("\n");
			}
			return ;
		}
		//处理用户传来的命令
		ChooseOperation co=new ChooseOperation();
		while(true) {
				//输入流
				//接受客户端传过来的person对象
				//读一个对象
				ObjectInputStream ois = null;
				ObjectOutputStream oos=null;
				//ObjectInput
				System.out.println("正在获取对象流！");
				try {
					//对象输入流，有锁机制，每次都要new才可以,socket.getInputStream()必须放循环外面
					ois = new ObjectInputStream(is);
					System.out.println("获取对象输入流成功！");
					//对象输出流，有锁机制，每次都要new才可以,socket.getOutputStream()必须放循环外面
					oos=new ObjectOutputStream(os);
					System.out.println("获取对象输出流成功！");
				} catch (IOException e1) {
					//一般为客户端关闭程序，造成与服务器断开链接 
					e1.printStackTrace();
					//处理用户断开后的退出登录处理
					if(person!=null){
						System.out.println(Thread.currentThread().getName()+"---"+person.getUserName()+" 用户断开！"+"时间是："+"["+(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"]");
						System.out.println("正在删除："+person);
						System.out.println("容器删除前："+ServerListen.list);
						ServerListen.list.remove(person);
						System.out.println("容器删除后："+ServerListen.list);
					}else{
						System.out.println(Thread.currentThread().getName()+"---"+" 用户断开！"+"时间是："+"["+(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"]");
					}
					//关闭流，回收资源
					try {
						ois.close();
						oos.close();
					} catch (IOException ee3) {
						ee3.printStackTrace();
					}catch (NullPointerException eee3) {
						//为空就不表示已经关闭流
					}
					System.out.println("服务器回收资源!");
					//刷新当前在线用户
					ServiceFrame.jta1.setText("");
					for (Person per : ServerListen.list) {
						ServiceFrame.jta1.append(per.getUserName());
						ServiceFrame.jta1.append("\n");
					}
					return ;
				}
				System.out.println("现在是第"+(i++)+"轮循环！");
				//用于引用命令或数据对象
				Object object = null;
				//当前时间
				date=new Date();
				try {
					System.out.println("读取指令中----");
					//读取一个对象，第一个为指令
					object = ois.readObject();
					System.out.println("读取命令成功---"+object);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
					//处理用户断开后的退出登录处理
					if(person!=null){
						System.out.println(Thread.currentThread().getName()+"---"+person.getUserName()+" 用户断开！"+"时间是："+"["+(date.getYear()+1900)+"-"+date.getMonth()+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"]");
						System.out.println("正在删除："+person);
						System.out.println("容器删除前："+ServerListen.list);
						ServerListen.list.remove(person);
						System.out.println("容器删除后："+ServerListen.list);
					}else{
						System.out.println(Thread.currentThread().getName()+"---"+" 用户断开！"+"时间是："+"["+(date.getYear()+1900)+"-"+date.getMonth()+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"]");
					}
					//关闭流，回收资源
					try {
						ois.close();
						oos.close();
					} catch (IOException ee2) {
						ee2.printStackTrace();
					}catch (NullPointerException eee2) {
						//为空就不表示已经关闭流
					}
					System.out.println("服务器回收资源!");
					//刷新当前在线用户
					ServiceFrame.jta1.setText("");
					for (Person per : ServerListen.list) {
						ServiceFrame.jta1.append(per.getUserName());
						ServiceFrame.jta1.append("\n");
					}
					return ;
				}
			
				//输出流	
				//实现根据传过来的方法名,调用相应的方法
				//是否是String类的实例
				if(object instanceof String) {
					String str=(String)object;
					//检测是否需要读第二次
					co.isReader(str);
					//需要读第二次返回true,不需要返回false
					boolean flag=co.isFlag();
					try {
						System.out.println("等待读取实体！");
						//读取一个对象，第二个为数据对象
						object = ois.readObject();
						System.out.println("读取实体成功！--"+object);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
						//处理用户断开后的退出登录处理
						if(person!=null){
							System.out.println(Thread.currentThread().getName()+"---"+person.getUserName()+" 用户断开！"+"时间是："+"["+(date.getYear()+1900)+"-"+date.getMonth()+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"]");
							System.out.println("正在删除："+person);
							System.out.println("容器删除前："+ServerListen.list);
							ServerListen.list.remove(person);
							System.out.println("容器删除后："+ServerListen.list);
						}else{
							System.out.println(Thread.currentThread().getName()+"---"+" 用户断开！"+"时间是："+"["+(date.getYear()+1900)+"-"+date.getMonth()+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"]");
						}
						//关闭流，回收资源
						try {
							ois.close();
							oos.close();
						} catch (IOException ee) {
							ee.printStackTrace();
						}catch (NullPointerException eee) {
							//为空就不表示已经关闭流
						}
						System.out.println("服务器回收资源!");
						//刷新当前在线用户
						ServiceFrame.jta1.setText("");
						for (Person per : ServerListen.list) {
							ServiceFrame.jta1.append(per.getUserName());
							ServiceFrame.jta1.append("\n");
						}
						return ;
						
					}
					//需要读两次就判断，不需要就不判断
					if(flag){
						//是否是Person的实例
						if(object instanceof Person) {
							person=(Person)object;
							co.setPerson(person);
							/*//是否是User类的实例
							if(person instanceof User){
								User user=(User) person;
							}
							//是否是Manager类的实例
							if(person instanceof Manager) {
								Manager manager=(Manager) person;
								
							}*/
						}
						if(object instanceof Book){
							Book book = (Book) object;
							co.setBook(book);
							System.out.println("获取图书----:"+book.toString());
						}
					}
					//调用处理命令方法，传入命令，及输入输出流,传入输入流和输出流用于以后扩展
					co.operation(str,ois,oos);
					System.out.println("调用operation方法成功!");
					//用户退出信息
					person=co.getPerson();
				}
		}
		
	}

}
