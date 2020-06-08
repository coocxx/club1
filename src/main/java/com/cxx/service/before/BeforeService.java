package com.cxx.service.before;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxx.pojo.Club;
import com.cxx.pojo.ClubApply;
import com.cxx.pojo.ClubType;
import com.cxx.pojo.Event;
import com.cxx.pojo.MemberJoin;
import com.cxx.pojo.News;
import com.cxx.pojo.Notice;
import com.cxx.pojo.Student;;

public interface BeforeService {
	/**
	 * 用户登录页面
	 * 
	 * @param student
	 * @return
	 */
	String login(Student student, Model model, HttpServletRequest request) throws IOException;

	/**
	 * 发送验证码
	 * 
	 * @param email
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	String preparedEmailLogin(String email, Model model, HttpServletRequest request) throws IOException;

	/**
	 * 邮箱验证码登录
	 * 
	 * @param student
	 * @param request
	 * @return
	 */
	String emailLogin(String email, String code, Model model, HttpServletRequest request) throws IOException;

	/*
	 * 注册前给注册页面，班级信息从数据库里面取出来
	 */

	String regClass(HttpServletRequest request);

	/**
	 * 发送验证码
	 * 
	 * @param email
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	void preparedReg(Student regStudent, HttpServletRequest request, HttpServletResponse response) throws IOException;

	/**
	 * 注册
	 * 
	 * @param email
	 * @param code
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	String reg(Student student, String code, HttpServletRequest request) throws IOException;

	/**
	 * 退出登录
	 * 
	 * @param request
	 * @return
	 */
	String logout(Model model, HttpServletRequest request);

	/**
	 * 修改某一个指定的用户信息
	 * 
	 * @param student
	 * @param model
	 * @param request
	 * @return
	 */
	String updOneStudent(Student student, Model model, HttpServletRequest request);

	/**
	 * 修改个人密码
	 */
	String updOneStudentPwd(Student student, String newPassword, Model model, HttpServletRequest request);
	/**
	 * 个人中心，判断是普通用户还是会长
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	String goCenter(Model model, HttpServletRequest request);

	/**
	 * 转向主页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	public String goIndex(Model model, String result, HttpServletRequest request);

	/**
	 * 某一个社团类型有哪些社团
	 * 
	 * @param clubType
	 * @param model
	 * @param request
	 * @return
	 */
	public String selClubOfOneType(ClubType clubType, Model model, HttpServletRequest request);

	/**
	 * 通过社团编号查询社团信息
	 * 
	 * @param club
	 * @param model
	 * @param request
	 * @return
	 */
	public String selClubById(Club club, Model model, HttpServletRequest request);

	/**
	 * 新闻动态
	 * 
	 * @param club
	 * @param model
	 * @param request
	 * @return
	 */
	String selNewsById(News news, Model model, HttpServletRequest request);

	/**
	 * 根据社团公告
	 * 
	 * @param notice
	 * @param model
	 * @param request
	 * @return
	 */
	String selNoticesById(Notice notice, Model model, HttpServletRequest request);

	/**
	 * 根据社团id查询公告
	 * 
	 * @param notice
	 * @param model
	 * @param request
	 * @return
	 */
	String selNoticesOfClub(Notice notice, Model model, HttpServletRequest request);

	String selEvents(Event event, Model model, HttpServletRequest request);

	String selEventById(Event event, Model model, HttpServletRequest request);
	
	/**
	 * 添加社团
	 * 申请加入社团
	 * @param club
	 * @param model
	 * @param request
	 * @return
	 */
	String addMemberJoin(Club club, String active, Model model, HttpServletRequest request);
	
	
	/**
	 * 查询某个学生
	 * 申请加入社团历史
	 * @param club
	 * @param model
	 * @param request
	 * @return
	 */
	String selClubsByStudent(Club club, Model model, HttpServletRequest request);
	
	/**
	 * 查询某个学生加入社团的历史
	 * @param model
	 * @param request
	 * @return
	 */
	String joinClubHistory(Model model, HttpServletRequest request);
	
	
	
	/**
	 * 撤销加入该社团
	 * @param model
	 * @param request
	 * @return
	 */
	public String cancelJoin(MemberJoin memberJoin, Model model, HttpServletRequest request);
	
	/**
	 * 进入个人中心
	 * @param model
	 * @param request
	 * @return
	 */
	String goStuCenterLeftOfMessage(Model model, HttpServletRequest request);
	
	/**
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	String selMessage(Model model, HttpServletRequest request);
	
	/**
	 * 学生新建社团
	 * @param model
	 * @param request
	 * @return
	 */
	String addClubApply(ClubApply clubApply, Model model, HttpServletRequest request);
	
	/**
	 * 准备学生新建社团
	 * @param model
	 * @param request
	 * @return
	 */
	String preStuClubApply(Model model, HttpServletRequest request);
	
	
	/**
	 * 查看新建社团历史信息
	 * @param model
	 * @param request
	 * @return
	 */
	String selStuClubApp(Model model, HttpServletRequest request);
	/**
	 * 取消申请的社团
	 * @param model
	 * @param request
	 * @return
	 */
	String delClubApply(int id, Model model, HttpServletRequest request);
	/**
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	String selImageOfClub(Model model, HttpServletRequest request);
}
