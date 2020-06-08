package com.cxx.controller.before;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cxx.pojo.Club;
import com.cxx.pojo.ClubApply;
import com.cxx.pojo.ClubType;
import com.cxx.pojo.Event;
import com.cxx.pojo.MemberJoin;
import com.cxx.pojo.News;
import com.cxx.pojo.Notice;
import com.cxx.pojo.Student;
import com.cxx.service.before.BeforeService;

@RequestMapping("before")
@Controller
public class BeforeController {
	@Resource
	private BeforeService beforeServiceImpl;

	/**
	 * 账户密码登录
	 * 
	 * @param student
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("studentLogin")
	public String studentLogin(Student student,Model model, HttpServletRequest request) throws IOException {
		return beforeServiceImpl.login(student,model, request);
	}

	/**
	 * 发送验证码
	 * 
	 * @param email
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("preparedEmailLogin")
	public String preparedEmailLogin(String email,Model model, HttpServletRequest request) throws IOException {
		return beforeServiceImpl.preparedEmailLogin(email, model,request);
	}

	/**
	 * 邮箱验证码登录
	 * 
	 * @param student
	 * @param request
	 * @return
	 */
	@RequestMapping("emailLogin")
	public String emailLogin(String email, String code,Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		return beforeServiceImpl.emailLogin(email, code,model, request);
	}

	/**
	 * 注册前班级号
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("regClass")
	public String regClass(HttpServletRequest request) {
		return beforeServiceImpl.regClass(request);
	}

	/**
	 * 发送验证码
	 * 
	 * @param email
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("preparedReg")
	public void preparedReg(Student regStudent, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		beforeServiceImpl.preparedReg(regStudent, request, response);
	}

	@RequestMapping("preparedReg1")
	public void preparedReg1(String email, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		PrintWriter writer = response.getWriter();
		System.out.println("fffffffffffffffffffffffffff");
		writer.write("<script>history.back();</script>");
	}

	/**
	 * 注册
	 * 
	 * @param email
	 * @param code
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("reg")
	public String reg(Student student, String code, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		return beforeServiceImpl.reg(student, code, request);
	}

	/**
	 * 退出登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("logout")
	public String logout(Model model,HttpServletRequest request) {
		return beforeServiceImpl.logout(model,request);
	}

	// 修改用户个人信息
	@RequestMapping("updOneStudent")
	public String updOneStudent(Student student, Model model, HttpServletRequest request) {
		return beforeServiceImpl.updOneStudent(student, model, request);
	}

	/**
	 * 修改个人密码
	 * @param student
	 * @param newPassword
	 * @param model
	 * @param request
	 * @return String
	 */
	@RequestMapping("updOneStudentPwd")
	public String updOneStudentPwd(Student student,String newPassword, Model model, HttpServletRequest request) {
		return beforeServiceImpl.updOneStudentPwd(student,newPassword, model, request);
	}
	/**
	 * 转向主页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("goIndex")
	public String goIndex(Model model,String result,HttpServletRequest request) {
		return beforeServiceImpl.goIndex(model,result, request);
	}
	/**
	 * 个人中心，判断是普通用户还是会长
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("goCenter")
	public String goCenter(Model model,HttpServletRequest request) {
		return beforeServiceImpl.goCenter(model, request);
	}
	
	/*
	 *查询某一个特定的类型的社团 
	 */
	@RequestMapping("selClubOfOneType")
	public String selClubOfOneType(ClubType clubType,Model model,HttpServletRequest request) {
		return beforeServiceImpl.selClubOfOneType(clubType,model, request);
	}
	
	@RequestMapping("selClubById")
	public String selClubById(Club club,Model model,HttpServletRequest request) {
		return beforeServiceImpl.selClubById(club,model, request);
	}
	
	
	
	
	@RequestMapping("selNoticesById")
	public String selNoticesById(Notice notice,Model model,HttpServletRequest request) {
		return beforeServiceImpl.selNoticesById(notice,model, request);
	}
	
	/**
	 * 查询某一个具体社团的公告
	 * @param notice
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("selNoticesOfClub")
	public String selNoticesOfClub(Notice notice,Model model,HttpServletRequest request) {
		return beforeServiceImpl.selNoticesOfClub(notice,model, request);
	}

	/**
	 * 查看所有新闻动态
	 * @param news
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("selNewsById")
	public String selNewsById(News news,Model model,HttpServletRequest request) {
		return beforeServiceImpl.selNewsById(news,model, request);
	}
	
	
	/**
	 * 查询某个社团全部活动信息
	 * @param event
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("selEvents")
	public String selEvents(Event event,Model model,HttpServletRequest request) {
		return beforeServiceImpl.selEvents(event,model, request);
	}
	
	/**
	 * 通过活动Id查看具体详情
	 * @param event
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("selEventById")
	public String selEventById(Event event,Model model,HttpServletRequest request) {
		return beforeServiceImpl.selEventById(event,model, request);
	}
	
	/**
	 * 添加社团
	 * 申请加入社团
	 * @param club
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("addMemberJoin")
	public String addMemberJoin(Club club,String active,Model model,HttpServletRequest request) {
		System.err.println("active:"+active);
		return beforeServiceImpl.addMemberJoin(club,active,model, request);
	}
	
	
	/**
	 * 查询某个学生
	 * 申请加入社团历史
	 * @param club
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("selClubsByStudent")
	public String selClubsByStudent(Club club,Model model,HttpServletRequest request) {
		return beforeServiceImpl.selClubsByStudent(club,model, request);
	}
	
	
	
	/**
	 * 查询某个学生加入社团的历史
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("joinClubHistory")
	public String joinClubHistory(Model model,HttpServletRequest request) {
		return beforeServiceImpl.joinClubHistory(model, request);
	}
	
	
	/**
	 * 撤销加入该社团
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("cancelJoin")
	public String cancelJoin(MemberJoin memberJoin,Model model,HttpServletRequest request) {
		return beforeServiceImpl.cancelJoin(memberJoin,model, request);
	}
	
	/**
	 * 进入个人中心
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("goStuCenterLeftOfMessage")
	public String goStuCenterLeftOfMessage(Model model,HttpServletRequest request) {
		return beforeServiceImpl.goStuCenterLeftOfMessage(model, request);
	}
	
	/**
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("selMessage")
	public String selMessage(Model model,HttpServletRequest request) {
		return beforeServiceImpl.selMessage(model, request);
	}
	
	
	/**
	 * 学生新建社团
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("addClubApply")
	public String addClubApply(ClubApply clubApply,Model model,HttpServletRequest request) {
		return beforeServiceImpl.addClubApply(clubApply,model, request);
	}
	
	
	/**
	 * 准备学生新建社团
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("preStuClubApply")
	public String preStuClubApply(Model model,HttpServletRequest request) {
		return beforeServiceImpl.preStuClubApply(model, request);
	}
	
	/**
	 * 准备学生新建社团
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("selStuClubApp")
	public String selStuClubApp(Model model,HttpServletRequest request) {
		return beforeServiceImpl.selStuClubApp(model, request);
	}
	
	/**
	 * 取消申请的社团
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("delClubApply")
	public String delClubApply(int id,Model model,HttpServletRequest request) {
		return beforeServiceImpl.delClubApply(id,model, request);
	}
	
	
	//图片image----------------------------------------
	/**
	 * 取消
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("selImageOfClub")
	public String selImageOfClub(Model model,HttpServletRequest request) {
		return beforeServiceImpl.selImageOfClub(model, request);
	}
	
	
	
	
	
}
