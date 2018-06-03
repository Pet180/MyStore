package cn.tedu.store.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xslf.usermodel.XSLFSheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.bean.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.exc.UserNameAlreadyExistException;

@Controller
@RequestMapping("/user")	//路径对应用户模块
public class UserController extends BaseController{
	@Resource
	private IUserService userService;
	
	@RequestMapping("/showRegister.do")
	//显示注册页面
	public String showRegister(){
		System.out.println("UserController.showRegister()");
		return "register";
	}
	@RequestMapping("/checkUsername.do")
	@ResponseBody
	public ResponseResult<Void> checkUsername(String username){
		System.out.println("UserController.checkUsername()");
		ResponseResult<Void> rr = new ResponseResult<Void>();
		if (userService.checkUsername(username)) {
			//返回true，状态码设置为0，信息：用户名存在。
			rr.setState(0);
			rr.setMessage("用户名存在");
		}else {
			//否则1状态码为，信息：用户名可以使用。
			rr.setState(1);
			rr.setMessage("用户名可以使用");
		}
		return rr;
	}
	@RequestMapping("/checkEmail.do")
	@ResponseBody
	public ResponseResult<Void> checkEmail(String email){
		System.out.println("UserController.checkEmail()");
		ResponseResult<Void> rr = new ResponseResult<Void>();
		//调用userService.checkEmail()判断是否存在
		if (userService.checkEmail(email)) {
			//返回true，状态码设置为0，信息:email已存在
			rr.setState(0);
			rr.setMessage("email已存在");
		}else {
			//否则，状态码设置为1，信息:email可以使用			
			rr.setState(1);
			rr.setMessage("email可以使用");
		}
		return rr;
	}
	@RequestMapping("/checkPhone.do")
	@ResponseBody
	public ResponseResult<Void> checkPhone(String phone){
		System.out.println("UserController.checkPhone()");
		ResponseResult<Void> rr = new ResponseResult<Void>();
		//调用userService.checkPhone()判断是否存在
		if (userService.checkPhone(phone)) {
			//返回true，状态码设置为1，信息:phone已存在。
			rr.setState(0);
			rr.setMessage("电话已存在");
		}else {
			//否则，状态码设置为1，信息:phone可以使用。
			rr.setState(1);
			rr.setMessage("电话可以使用");
		}
		return rr;
	}
	/**
	 * 用户注册
	 * @param username
	 * @param password
	 * @param email
	 * @param phone
	 * @return
	 */
	@RequestMapping("/register.do")
	@ResponseBody
	public ResponseResult<Void> register(@RequestParam("uname") String username,
			@RequestParam("upwd") String password,String email,String phone){
		System.out.println("UserController.register()");
		ResponseResult<Void> rr = new  ResponseResult<Void>();
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setPhone(phone);
		//调用userService.register()
		try {
			//若没有异常，调用插入方法
			userService.register(user);
		} catch (UserNameAlreadyExistException e) {
			//若有异常，封装信息到rr对象
			rr.setState(0);
			rr.setMessage("用户名已存在");
		}
		return rr;
	}
	/**
	 * 显示登录页面
	 * @return
	 */
	@RequestMapping("/showLogin.do")
	public String showLogin(){
		System.out.println("UserController.showLogin()");
		return "login";
	}
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @param session
	 * @return
	 */
	 
	@RequestMapping("/login.do")
	@ResponseBody
	public ResponseResult<Void> login(String username,String password,HttpSession session){
		System.out.println("UserController.login()");
		ResponseResult<Void> rr = new ResponseResult<Void>();
			try {
				//1.调用业务层login()
				User user = userService.login(username, password);
				//2.设置state与message  分别为1与登录成功
				rr.setState(1);
				rr.setMessage("登录成功");
				//3.user对象将存在session内，提供给index页面使用。
				session.setAttribute("user", user);
			} catch (Exception e) {
				//4.设置state与message  分别为0与e.getMessage()
				rr.setState(0);
				rr.setMessage(e.getMessage());
			}
		return rr;
	}
	/**
	 * 点击登出重定向到首页并使session失效
	 * @param session
	 * @return 重定向到首页
	 */
	@RequestMapping("/exit.do")
	public String exit(HttpSession session){
		System.out.println("UserController.exit()");
		session.invalidate();
		return "redirect:/main/showIndex.do";
	}
	/**
	 * 显示安全管理页面
	 * @return
	 */
	@RequestMapping("/showPassword.do")
	public String showPassword(){
		System.out.println("UserController.showPassword()");
		
		return "personal_password";
	}
	/**
	 * 修改用户密码
	 * @param session
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 */
	@RequestMapping("/password.do")
	@ResponseBody
	public ResponseResult<Void> password(HttpSession session,String oldPwd,String newPwd){
		System.out.println("UserController.password()");
		System.out.println(oldPwd+","+newPwd);
		//1.创建rr
		ResponseResult<Void> rr = new ResponseResult<Void>();
		try {
			//2.调用业务层方法changePassword()
			userService.checkPassword(this.getId(session), oldPwd, newPwd);
			//3.密码修改完成
			rr.setState(1);
			rr.setMessage("密码修改成功");
		} catch (Exception e) {
			//4.密码错误
			rr.setState(0);
			rr.setMessage(e.getMessage());
		}
		return rr;
	}
	/**
	 * 显示个人信息之url
	 * @return
	 */
	@RequestMapping("/showPersonInfo.do")
	public String showPersonInfo(){
		System.out.println("UserController.showPersonInfo()");
		return "personInfo";
	}
	/**
	 * 修改个人信息
	 * @param session
	 * @param username
	 * @param gender
	 * @param phone
	 * @param email
	 * @return
	 */
	@RequestMapping("/updateUser.do")
	@ResponseBody
	public ResponseResult<Void> updateUser(HttpSession session,String username,Integer gender,String phone,String email){
		System.out.println("UserController.updateUser()");
		System.out.println(username+","+gender+","+phone+","+email);
		//1.创建rr
		ResponseResult<Void> rr = new ResponseResult<Void>();
		//2.调用userService.updateUser(),成功:封装状态码与响应信息。
		try {
			userService.updateUser(this.getId(session), username, gender, phone, email);
			session.setAttribute("user", userService.getUserById(this.getId(session)));
			rr.setState(1);
			rr.setMessage("个人信息修改成功");
		} catch (Exception e) {
			//3.封装失败的状态码与响应信息。
			rr.setState(0);
			rr.setMessage(e.getMessage());
		}
		return rr;
	}
	
	@RequestMapping("/upload.do")
	@ResponseBody
	public ResponseResult<Void> upload(@RequestParam("file") MultipartFile file,HttpSession session) throws IllegalStateException, IOException{
		System.out.println("UserController.upload()");
		ResponseResult<Void> rr = new ResponseResult<Void>();
		//獲取server的專案真實路徑
		String path = session.getServletContext().getRealPath("/");
		System.out.println("path:"+path);
		//上傳圖片
		file.transferTo(new File(path,"/upload/"+file.getOriginalFilename()));
		System.out.println("上傳路徑: /upload/"+file.getOriginalFilename());
		//修改image
		userService.updateImageById("/upload/"+file.getOriginalFilename(), this.getId(session));
		//重新設置session中的user對象
		User u = userService.getUserById(this.getId(session));
		session.setAttribute("user", u);
		
		rr.setState(1);
		rr.setMessage("上傳成功");
		return rr;
	}
	/**
	 * 图片下载功能
	 * @throws Exception 
	 */
	@RequestMapping(value="/img.do",produces="image/png")
	@ResponseBody
	public byte[] image(HttpServletResponse response) throws Exception{
		System.out.println("UserController.image()");
		//@ResponseBody与返回值byte[]配合，
		//Spring MVC会将byte[]填充到响应的消息正文中，
		//并发送给浏览器
		String file = URLEncoder.encode("演示.png","UTF-8");
		//还需要指定两个响应头
		//Content-Type
		//Content-Disposition: attachment; filename="fname.ext"
//		response.setheader("Content-Disposition","attachment; filename=\""+file+"\"");
		response.setHeader("Content-Disposition","attachment; filename=\""+file+"\"");
		byte[]body = createImage();
		response.setContentLength(body.length);
		return body;
		
	}
	//创建生成图片数据
	private byte[] createImage() throws Exception {
		BufferedImage img = new BufferedImage(100, 50, BufferedImage.TYPE_3BYTE_BGR); 
		img.setRGB(50, 25, 0xffffff);
		//out相当于酱油瓶
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		//将图片的是据导入酱油瓶
		ImageIO.write(img, "png", out);
		out.close();
		//将酱油瓶的数据倒出来
		byte[] bytes = out.toByteArray();
		return bytes;
	}
	
	//生成Excel控制器方法
	@RequestMapping("/excel.do")
	@ResponseBody
	public byte[] excel(HttpServletResponse response) throws Exception{
		System.out.println("UserController.excel()");
		String file = URLEncoder.encode("table.xlsx","UTF-8");
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition", "attachment; filename=\""+file+"\"");
		byte[]body = createExcel();
		return body;
	}
	//透过POI生成Excel文件的方法
	private byte[] createExcel() throws Exception {
		//Workbook代表一个Excel文件
		XSSFWorkbook workbook = new XSSFWorkbook();
		//在工作簿中创建一个工作表(sheet)
		XSSFSheet sheet = workbook.createSheet();
		//在工作表中创建一行(row)，参数行号为:0123...
		XSSFRow row = sheet.createRow(0);
		//在行中可以添加格子，参数列号为0123
		XSSFCell cell = row.createCell(0);
		cell.setCellValue("Hello World!");
		
		ByteArrayOutputStream out = new  ByteArrayOutputStream();
		workbook.write(out);
		workbook.close();
		out.close();
		byte[]bytes = out.toByteArray();
		return bytes;
	}
}
