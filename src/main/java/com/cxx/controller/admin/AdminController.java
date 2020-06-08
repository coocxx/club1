package com.cxx.controller.admin;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.core.appender.rewrite.MapRewritePolicy.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cxx.mapper.ClubMapper;
import com.cxx.pojo.Academy;
import com.cxx.pojo.Admin;
import com.cxx.pojo.Classes;
import com.cxx.pojo.Club;
import com.cxx.pojo.ClubApply;
import com.cxx.pojo.ClubMember;
import com.cxx.pojo.ClubType;
import com.cxx.pojo.Event;
import com.cxx.pojo.Image;
import com.cxx.pojo.Major;
import com.cxx.pojo.MemberJoin;
import com.cxx.pojo.MemberQuit;
import com.cxx.pojo.News;
import com.cxx.pojo.Notice;
import com.cxx.pojo.PrModel;
import com.cxx.pojo.Student;
import com.cxx.service.admin.AdminService;
import com.cxx.util.Page;

@Controller
@RequestMapping(value="admin")
public class AdminController {
	@Resource
	private AdminService adminserviceImpl;
	/**
	 * 管理员登录
	 * @param admin
	 * @param request
	 * @return
	 */
	@RequestMapping(value="adminLogin")
	public String adminLogin(Admin admin,Model model,HttpServletRequest request) {
		return adminserviceImpl.adminLogin(admin, model,request);
	}
	
	//修改管理员个人信息
	@RequestMapping("updOneAdmin")
	public String updOneAdmin(Admin admin,Model model,HttpServletRequest request) {
		return adminserviceImpl.updOneAdmin(admin, model, request);
	}
	
	/**
	 * 管理员注销登录
	 * @param request
	 * @return
	 */
	@RequestMapping("adminLogout")
	public String adminLogout(HttpServletRequest request) {
		return adminserviceImpl.adminLogout(request);
	}
	
	/**
	 * 查询全部管理员账号信息
	 * @param request
	 * @return
	 */
	@RequestMapping("selAdminByPage")
	public String selAdminByPage(Model model,Page page) {
		return adminserviceImpl.adminSelectAll(page,model);
	}
	
	/**
	 * 准备修改管理员信息
	 * @param id
	 * @return
	 */
	@RequestMapping("preUpdAdmin")
	public ModelAndView preUpdAdmin(int id) {
		return adminserviceImpl.preUpdAdmin(id);
	}
	
	/**
	 * 修改管理员信息
	 * @param id
	 * @return
	 */
	@RequestMapping("updAdmin")
	public String updAdmin(Admin admin,Model model,HttpServletRequest request) {
		return adminserviceImpl.updAdmin(admin,model, request);
	}
	
	/**
	 * 根据管理员部分信息查询
	 * @param admin
	 * @return
	 */
	@RequestMapping("selAdmins")
	public ModelAndView selAdmins(Admin admin) {
		System.err.println("................................................................................");
		System.err.println("admin:"+admin);
		return adminserviceImpl.selAdmins(admin,null);
	}
	
	
	/**
	 * 修改管理员信息
	 * @param id
	 * @return
	 */
	@RequestMapping("addAdmin")
	public String addAdmin(Admin admin,Model model) {
		return adminserviceImpl.addAdmin(admin,model);
	}
	
	/**
	 * 删除管理员信息
	 * @param id
	 * @return
	 */
	@RequestMapping("delAdmin")
	public String delAdmin(Admin admin,Model model) {
		return adminserviceImpl.delAdmin(admin,model);
	}
	
	
	/**
	 * 社团----------------------------------------------------------------------------------------------
	 */
	
	/**
	 * 分页查询社团
	 * @param page
	 * @return
	 */
	@RequestMapping("selClubByPage")
	public String selClubByPage(Page page,@RequestParam(required=false) String result,Model model) {
		return adminserviceImpl.selClubByPage(page,null,model);
	}
	
	/**
	 * 准备添加社团
	 * @param club
	 * @param model
	 * @return
	 */
	@RequestMapping("preAddClub")
	public String preAddClub(Model model) {
		return adminserviceImpl.preAddClub(model);
	}
	
	/**
	 * 准备添加社团
	 * @param club
	 * @param model
	 * @return
	 */
	@RequestMapping("addClub")
	public String addClub(Club club,Model model) {
		return adminserviceImpl.addClub(club,model);
	}
	
	/**
	 * 准备修改社团
	 * @param club
	 * @param model
	 * @return
	 */
	@RequestMapping("preUpdClub")
	public String preUpdClub(Club club,String active,Model model,HttpServletRequest request) {
		return adminserviceImpl.preUpdClub(club,active,model,request);
	}
	
	/**
	 * 修改社团
	 * @param club
	 * @param model
	 * @return
	 */
	@RequestMapping("updClub")
	public String updClub(Club club,Model model) {
		return adminserviceImpl.updClub(club,model);
	}
	/**
	 * 修改社团
	 * @param club
	 * @param model
	 * @return
	 */
	@RequestMapping("updClubPr")
	public String updClubPr(Club club,Model model,HttpServletRequest request) {
		return adminserviceImpl.updClubPr(club,model,request);
	}
	
	/**
	 * 删除社团
	 * @param club
	 * @param model
	 * @return
	 */
	/*@RequestMapping("delClub")
	public String delClub(Club club,Model model) {
		System.err.println("club:"+club);
		return adminserviceImpl.delClub(club,model);
	}*/
	
	/**
	 * 根据条件查询社团信息
	 * @param club
	 * @return
	 */
	@RequestMapping("selClubs")
	public String selClubs(Club club,Model model) {
		return adminserviceImpl.selClubs(club,model);
	}
	
	/**
	 * 改变社团状态，恢复或者暂停
	 * @return
	 */
	@RequestMapping("updStatus")
	public String updStatus(Club club,Model model) {
		return adminserviceImpl.updStatus(club, model);
	}
	
	
	
	
	/*
	 *社团类型---------------------------------------------------------------------------------------------
	 */
	/**
	 * 根据条件查询社团类型信息
	 * @param club
	 * @return
	 */
	@RequestMapping("selClubTypes")
	public String selClubTypes(ClubType clubType,Model model) {
		return adminserviceImpl.selClubTypes(clubType,model);
	}
	
	
	/**
	 * 查询社团类型信息
	 * @param club
	 * @return
	 */
	@RequestMapping("selClubTypeByPage")
	public String selClubTypeByPage(Page page,Model model) {
		return adminserviceImpl.selClubTypeByPage(null,page,model);
	}
	
	
	
	/**
	 * 准备添加社团类型
	 * @param club
	 * @param model
	 * @return
	 */
	@RequestMapping("addClubType")
	public String addClubType(ClubType clubType,Model model) {
		return adminserviceImpl.addClubType(clubType,model);
	}
	
	/**
	 * 准备修改社团类型
	 * @param club
	 * @param model
	 * @return
	 */
	@RequestMapping("preUpdClubType")
	public String preUpdClubType(ClubType clubType,Model model) {
		return adminserviceImpl.preUpdClubType(clubType,model);
	}
	
	/**
	 * 修改社团类型
	 * @param club
	 * @param model
	 * @return
	 */
	@RequestMapping("updclubType")
	public String updClubType(ClubType clubType,Model model) {
		return adminserviceImpl.updClubType(clubType,model);
	}
	
	/**
	 * 删除社团类型
	 * @param club
	 * @param model
	 * @return
	 */
	@RequestMapping("delClubType")
	public String delClubType(ClubType clubType,Model model) {
		System.err.println("clubType:"+clubType);
		return adminserviceImpl.delClubType(clubType,model);
	}
	
	/**
	 * club_apply-------------------------------------------------------------------------------------------
	 */
	
	/**
	 * 根据条件查询社团申请信息
	 * @param delClubApply
	 * @return
	 */
	@RequestMapping("selClubApplies")
	public String selClubApplies(ClubApply clubApply,Model model) {
		return adminserviceImpl.selClubApplies(clubApply,model);
	}
	
	
	/**
	 * 查询社团类型信息
	 * @param page
	 * @return
	 */
	@RequestMapping("selClubApplyByPage")
	public String selClubApplyByPage(Page page,Model model) {
		return adminserviceImpl.selClubApplyByPage(null,page,model);
	}
	
	
	/**
	 * 修改申请状态为通过
	 * @param delClubApply
	 * @param model
	 * @return
	 */
	@RequestMapping("updClubApplyStatus")
	public String updClubApplyStatus(ClubApply clubApply,Model model,HttpServletRequest request) {
		return adminserviceImpl.updClubApplyStatus(clubApply, model,request);
	}
	/**
	 * 修改申请状态为拒绝
	 * @param delClubApply
	 * @param model
	 * @return
	 */
	@RequestMapping("updClubApplyStatusR")
	public String updClubApplyStatusR(ClubApply clubApply,Model model,HttpServletRequest request) {
		return adminserviceImpl.updClubApplyStatusR(clubApply, model,request);
	}
	/**
	 * 删除
	 * @param delClubApply
	 * @param model
	 * @return
	 */
	@RequestMapping("delClubApply")
	public String delClubApply(ClubApply clubApply,Model model) {
		return adminserviceImpl.delClubApply(clubApply, model);
	}
	
	
	/**
	 * 查看详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("selClubApplyById")
	public String selClubApplyById(int id,Model model) {
		return adminserviceImpl.selClubApplyById(id, model);
	}
	
	
	/**
	 * academy学院-------------------------------------------------------------------------------------------
	 */
	
	/**
	 * 根据条件查询学院
	 * @param academy
	 * @return
	 */
	@RequestMapping("selAcademys")
	public String selAcademys(Academy academy,Model model) {
		return adminserviceImpl.selAcademys(academy,model);
	}
	/**
	 * 查询学院信息
	 * @param club
	 * @return
	 */
	@RequestMapping("selAcademyByPage")
	public String selAcademyByPage(Page page,Model model) {
		return adminserviceImpl.selAcademyByPage(null,page,model);
	}
	
	/**
	 * 查看详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("selAcademyById")
	public String selAcademyById(int id,Model model) {
		return adminserviceImpl.selAcademyById(id, model);
	}

	
	/**
	 * major专业-------------------------------------------------------------------------------------------
	 */
	
	/**
	 * 根据条件查询专业
	 * @param academy
	 * @return
	 */
	@RequestMapping("selMajors")
	public String selMajors(Major major,Model model) {
		return adminserviceImpl.selMajors(major,model);
	}
	/**
	 * 查询专业信息
	 * @param club
	 * @return
	 */
	@RequestMapping("selMajorByPage")
	public String selMajorByPage(Page page,Model model) {
		return adminserviceImpl.selMajorByPage(null,page,model);
	}
	
	/**
	 * 查看详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("selMajorById")
	public String selMajorById(int id,Model model) {
		return adminserviceImpl.selMajorById(id, model);
	}
	
	
	/**
	 * classes班级-------------------------------------------------------------------------------------------
	 */
	
	/**
	 * 根据条件查询班级
	 * @param academy
	 * @return
	 */
	@RequestMapping("selClasses")
	public String selClasses(Classes classes,Model model) {
		return adminserviceImpl.selClasses(classes,model);
	}
	/**
	 * 查询学院信息
	 * @param Page
	 * @return
	 */
	@RequestMapping("selClassesByPage")
	public String selClassesByPage(Page page,Model model) {
		return adminserviceImpl.selClassesByPage(null,page,model);
	}
	
	/**
	 * 查看详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("selClassesById")
	public String selClassesById(int id,Model model) {
		return adminserviceImpl.selClassesById(id, model);
	}
	
	/**
	 * student学生-------------------------------------------------------------------------------------------
	 */
	
	/**
	 * 根据条件查询学生
	 * @param academy
	 * @return
	 */
	@RequestMapping("selStudents")
	public String selStudents(Student student,Model model) {
		return adminserviceImpl.selStudents(student,model);
	}
	/**
	 * 查询学生信息
	 * @param Page
	 * @return
	 */
	@RequestMapping("selStudentByPage")
	public String selStudentByPage(Page page,Model model) {
		return adminserviceImpl.selStudentByPage(null,page,model);
	}
	
	/**
	 * 查询学生信息
	 * @param Page
	 * @return
	 */
	@RequestMapping("selStuClubByPage")
	public String selStuClubByPage(Page page,Model model) {
		return adminserviceImpl.selStuClubByPage(null,page,model);
	}
	
	
	/**
	 * 准备编辑学生信息详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("preUpdStudent")
	public String preUpdStudent(int id,Model model) {
		return adminserviceImpl.preUpdStudent(id, model);
	}
	/**
	 * 编辑学生信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("updStudent")
	public String updStudent(Student student,Model model) {
		return adminserviceImpl.updStudent(student, model);
	}
	/**
	 * 删除学生信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("delStudent")
	public String delStudent(int id,Model model) {
		return adminserviceImpl.delStudent(id, model);
	}
	
	/**
	 * 准备添加学生账号信息
	 * @param model
	 * @return
	 */
	@RequestMapping("preAddStudent")
	public String preAddStu(Model model) {
		return adminserviceImpl.preAddStu(model);
	}
	
	/**
	 * 添加学生信息
	 * @param student
	 * @param model
	 * @return
	 */
	@RequestMapping("addStudent")
	public String addStudent(Student student,Model model) {
		return adminserviceImpl.addStudent(student, model);
	}
	
	/**
	 * 社团成员club_member-------------------------------------------------------------------------------------------
	 */
	
	/**
	 * 根据条件查询学生
	 * @param academy
	 * @return
	 */
	@RequestMapping("selClubMembers")
	public String selClubMembers(ClubMember clubMember,String active,Model model,HttpServletRequest request) {
		return adminserviceImpl.selClubMembers(clubMember,active,model,request);
	}
	/**
	 * 查询学生信息
	 * @param Page
	 * @return
	 */
	@RequestMapping("selClubMemberByPage")
	public String selClubMemberByPage(Page page,String active,Model model,HttpServletRequest request) {
		return adminserviceImpl.selClubMemberByPage(null,page,active,model,request);
	}
	
	
	/**
	 * 准备编辑学生信息详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("preUpdClubMember")
	public String preUpdClubMember(int id,String active,Model model,HttpServletRequest request) {
		return adminserviceImpl.preUpdClubMember(id, active,model,request);
	}
	/**
	 * 编辑学生信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("updClubMember")
	public String updClubMember(ClubMember clubMember,Model model,HttpServletRequest request) {
		return adminserviceImpl.updClubMember(clubMember, model,request);
	}
	/**
	 * 编辑学生信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("updClubMemberPr")
	public String updClubMemberPr(ClubMember clubMember,Model model,HttpServletRequest request) {
		return adminserviceImpl.updClubMemberPr(clubMember,model,request);
	}
	/**
	 * 删除学生信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("delClubMember")
	public String delClubMember(int id,String active,Model model,HttpServletRequest request) {
		return adminserviceImpl.delClubMember(id,active, model,request);
	}
	
	/**
	 * 准备添加学生账号信息
	 * @param model
	 * @return
	 */
	@RequestMapping("preAddClubMember")
	public String preAddClubMember(Model model) {
		return adminserviceImpl.preAddClubMember(model);
	}
	
	/**
	 * 添加学生信息
	 * @param student
	 * @param model
	 * @return
	 */
	@RequestMapping("addClubMember")
	public String addClubMember(ClubMember clubMember,Model model,HttpServletRequest request) {
		return adminserviceImpl.addClubMember(clubMember,model,request);
	}
	/**
	 * 添加学生信息
	 * @param student
	 * @param model
	 * @return
	 */
	@RequestMapping("addClubMemberPr")
	public String addClubMemberPr(ClubMember clubMember,Model model,HttpServletRequest request) {
		return adminserviceImpl.addClubMemberPr(clubMember,model,request);
	}
	/**
	 * 退出社团
	 * @param student
	 * @param model
	 * @return
	 */
	@RequestMapping("quitClubMember")
	public String quitClubMember(ClubMember member,String active,Model model,HttpServletRequest request) {
		return adminserviceImpl.quitClubMember(member,active, model,request);
	}
	
	/**
	 * member_quit退出社团成员信息-------------------------------------------------------------------------------------------
	 */
	
	/**
	 * 根据条件查询学生
	 * @param academy
	 * @return
	 */
	@RequestMapping("selMemberQuits")
	public String selMemberQuits(MemberQuit member,String active,Model model,HttpServletRequest request) {
		return adminserviceImpl.selMemberQuits(member,active,model,request);
	}
	/**
	 * 查询学生信息
	 * @param Page
	 * @return
	 */
	@RequestMapping("selMemberQuitByPage")
	public String selMemberQuitByPage(Page page,String active,Model model,HttpServletRequest request) {
		return adminserviceImpl.selMemberQuitByPage(null,active,page,model,request);
	}
	/**
	 * 查看详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("selMemberQuitById")
	public String selMemberQuitById(int id,String active,Model model,HttpServletRequest request) {
		return adminserviceImpl.selMemberQuitById(id,active, model,request);
	}
	
	/**
	 * 删除
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("delMemberQuit")
	public String delMemberQuit(int id,String active,Model model,HttpServletRequest request) {
		return adminserviceImpl.delMemberQuit(id, active,model,request);
	}
	
	/**
	 * member_join------------------------------------------------------------------------------------------
	 */
	
	/**
	 * 根据条件查询社团申请信息
	 * @param MemberJoin
	 * @return
	 */
	@RequestMapping("selMemberJoins")
	public String selMemberJoins(MemberJoin member,String active,Model model,HttpServletRequest request) {
		return adminserviceImpl.selMemberJoins(member,active,model,request);
	}
	
	
	/**
	 * 查询社团类型信息
	 * @param page
	 * @return
	 */
	@RequestMapping("selMemberJoinByPage")
	public String selMemberJoinByPage(Page page,String active,Model model,HttpServletRequest request) {
		return adminserviceImpl.selMemberJoinByPage(null,active,page,model,request);
	}
	
	
	/**
	 * 修改申请状态为通过
	 * @param MemberJoin
	 * @param model
	 * @return
	 */
	@RequestMapping("updMemberJoinStatus")
	public String updMemberJoinStatus(MemberJoin memberJoin,String active,Model model,HttpServletRequest request) {
		return adminserviceImpl.updMemberJoinStatus(memberJoin,active, model,request);
	}
	/**
	 * 修改申请状态为拒绝
	 * @param MemberJoin
	 * @param model
	 * @return
	 */
	@RequestMapping("updMemberJoinStatusR")
	public String updMemberJoinStatusR(MemberJoin memberJoin,String active,Model model,HttpServletRequest request) {
		return adminserviceImpl.updMemberJoinStatusR(memberJoin,active, model,request);
	}
	/**
	 * 删除
	 * @param MemberJoin
	 * @param model
	 * @return
	 */
	@RequestMapping("delMemberJoin")
	public String delMemberJoin(MemberJoin memberJoin,String active,Model model,HttpServletRequest request) {
		return adminserviceImpl.delMemberJoin(memberJoin,active, model,request);
	}
	
	
	/**
	 * 查看详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("selMemberJoinById")
	public String selMemberJoinById(int id,String active,Model model,HttpServletRequest request) {
		return adminserviceImpl.selMemberJoinById(id, active,model,request);
	}
	
	
	
	/**
	 * 社团公告------------------------------------------------------------------------------------------------
	 */
	
	/**
	 * 根据条件查询社团申请信息
	 * @param MemberJoin
	 * @return
	 */
	@RequestMapping("selNotices")
	public String selNotices(Notice notice,String action,Model model,HttpServletRequest request) {
		return adminserviceImpl.selNotices(notice,action,model,request);
	}
	
	
	/**
	 * 查询社团公告信息
	 * @param page
	 * @return
	 */
	@RequestMapping("selNoticeByPage")
	public String selNoticeByPage(Page page,String action,Model model,HttpServletRequest request) {
		return adminserviceImpl.selNoticeByPage(null,action,page,model,request);
	}
	
	
	/**
	 * 准备修改
	 * @param updNotice
	 * @param model
	 * @return
	 */
	@RequestMapping("preUpdNotice")
	public String preUpdNotice(int id,String action,Model model,HttpServletRequest request) {
		return adminserviceImpl.preUpdNotice(id,action,model,request);
	}
	/**
	 * 修改
	 * @param updNotice
	 * @param model
	 * @return
	 */
	@RequestMapping("updNotice")
	public String updNotice(Notice updNotice,String action, Model model,HttpServletRequest request) {
		System.err.println("action:"+action);
		return adminserviceImpl.updNotice(updNotice,action, model,request);
	}
	/**
	 * 修改
	 * @param updNotice
	 * @param model
	 * @return
	 */
	@RequestMapping("updNoticePr")
	public String updNoticePr(Notice updNotice,String action, Model model,HttpServletRequest request) {
		System.err.println("action:"+action);
		return adminserviceImpl.updNoticePr(updNotice,action, model,request);
	}
	
	
	/**
	 * 准备添加
	 * @param updNotice
	 * @param model
	 * @return
	 */
	@RequestMapping("preAddNotice")
	public String preAddNotice(Notice notice,Model model,HttpServletRequest request) {
		return adminserviceImpl.preAddNotice(notice,model,request);
	}
	/**
	 * 添加
	 * @param updNotice
	 * @param model
	 * @return
	 */
	@RequestMapping("addNotice")
	public String addNotice(Notice notice,String action, Model model,HttpServletRequest request) {
		return adminserviceImpl.addNotice(notice,action, model,request);
	}
	/**
	 * 添加
	 * @param updNotice
	 * @param model
	 * @return
	 */
	@RequestMapping("addNoticePr")
	public String addNoticePr(Notice notice,String action, Model model,HttpServletRequest request) {
		return adminserviceImpl.addNoticePr(notice,action, model,request);
	}
	/**
	 * 删除
	 * @param updNotice
	 * @param model
	 * @return
	 */
	@RequestMapping("delNotice")
	public String delNotice(int id,String action, Model model,HttpServletRequest request) {
		return adminserviceImpl.delNotice(id,action, model,request);
	}
	
	
	/**
	 * 新闻动态news------------------------------------------------------------------------------------------------
	 */
	
	/**
	 * 根据条件新闻动态
	 * @param news
	 * @return
	 */
	@RequestMapping("selNews")
	public String selNews(News news,String action,Model model) {
		return adminserviceImpl.selNews(news,action,model);
	}
	
	
	/**
	 * 查询新闻动态
	 * @param page
	 * @return
	 */
	@RequestMapping("selNewsByPage")
	public String selNewsByPage(Page page,String action,Model model) {
		return adminserviceImpl.selNewsByPage(null,action,page,model);
	}
	
	
	/**
	 * 准备修改新闻动态
	 * @param News
	 * @param model
	 * @return
	 */
	@RequestMapping("preUpdNews")
	public String preUpdNews(int id,String action,Model model) {
		return adminserviceImpl.preUpdNews(id,action,model);
	}
	/**
	 * 修改
	 * @param News
	 * @param model
	 * @return
	 */
	@RequestMapping("updNews")
	public String updNews(News news,Model model) {
		return adminserviceImpl.updNews(news, model);
	}
	
	
	
	/**
	 * 添加
	 * @param News
	 * @param model
	 * @return
	 */
	@RequestMapping("addNews")
	public String addNews(News news,Model model,HttpServletRequest request) {
		return adminserviceImpl.addNews(news, model,request);
	}
	/**
	 * 删除
	 * @param updNotice
	 * @param model
	 * @return
	 */
	@RequestMapping("delNews")
	public String delNews(int id,Model model) {
		return adminserviceImpl.delNews(id, model);
	}
	
	
	/**
	 * 社团活动------------------------------------------------------------------------------------------------
	 */
	
	/**
	 * 根据条件查询社团活动
	 * @param news
	 * @return
	 */
	@RequestMapping("selEvents")
	public String selEvents(Event event,String action,Model model,HttpServletRequest request) {
		return adminserviceImpl.selEvents(event,action,model,request);
	}
	
	
	/**
	 * 查询活动
	 * @param page
	 * @return
	 */
	@RequestMapping("selEventByPage")
	public String selEventByPage(Page page,String action,Model model,HttpServletRequest request) {
		return adminserviceImpl.selEventByPage(null,action,page,model,request);
	}
	
	
	/**
	 * 准备修改活动
	 * @param Event
	 * @param model
	 * @return
	 */
	@RequestMapping("preUpdEvent")
	public String preUpdEvent(int id,String action,Model model,HttpServletRequest request) {
		return adminserviceImpl.preUpdEvent(id,action,model,request);
	}
	/**
	 * 修改
	 * @param 活动
	 * @param model
	 * @return
	 */
	@RequestMapping("updEvent")
	public String updEvent(Event event,Model model,HttpServletRequest request) {
		return adminserviceImpl.updEvent(event, model,request);
	}

	
	/**
	 * 修改
	 * @param 活动
	 * @param model
	 * @return
	 */
	@RequestMapping("updEventPr")
	public String updEventPr(Event event,Model model,HttpServletRequest request) {
		return adminserviceImpl.updEventPr(event, model,request);
	}
	
	
	
	
	/**
	 * 准备添加
	 * @param Event
	 * @param model
	 * @return
	 */
	@RequestMapping("preAddEvent")
	public String preAddEvent(Event event,Model model,HttpServletRequest request) {
		return adminserviceImpl.preAddEvent(event, model,request);
	}
	/**
	 * 添加
	 * @param event
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("addEvent")
	public String addEvent(Event event,Model model,HttpServletRequest request) {
		return adminserviceImpl.addEvent(event,model,request);
	}
	/**
	 * 添加
	 * @param Event
	 * @param model
	 * @return
	 */
	@RequestMapping("addEventPr")
	public String addEventPr(Event event,Model model,HttpServletRequest request) {
		return adminserviceImpl.addEventPr(event, model,request);
	}
	/**
	 * 删除
	 * @param Event
	 * @param model
	 * @return
	 */
	@RequestMapping("delEvent")
	public String delEvent(Event event,String action,Model model,HttpServletRequest request) {
		return adminserviceImpl.delEvent(event,action, model,request);
	}
	
	/**
	 * 修改申请状态为通过
	 * @param delClubApply
	 * @param model
	 * @return
	 */
	@RequestMapping("updEventStatus")
	public String updEventStatus(Event event,Model model,HttpServletRequest request) {
		return adminserviceImpl.updEventStatus(event, model,request);
	}
	/**
	 * 修改申请状态为拒绝
	 * @param delClubApply
	 * @param model
	 * @return
	 */
	@RequestMapping("updEventStatusR")
	public String updEventStatusR(Event event,Model model,HttpServletRequest request) {
		return adminserviceImpl.updEventStatusR(event, model,request);
	}
	
	
	
	//审核——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
	/**
	 * 根据条件查询社团待审核信息
	 * @param delClubApply
	 * @return
	 */
	@RequestMapping("selAppClubs")
	public String selAppClubs(ClubApply clubApply,Model model) {
		return adminserviceImpl.selAppClubs(clubApply,model);
	}
	
	/**
	 * 查询社团待审核信息
	 * @param page
	 * @return
	 */
	@RequestMapping("selAppClubByPage")
	public String selAppClubByPage(Page page,Model model) {
		return adminserviceImpl.selAppClubByPage(null,page,model);
	}
	
	@RequestMapping("updAppClubStatus")
	public String updAppClubStatus(ClubApply clubApply, Model model, HttpServletRequest request) {
		return adminserviceImpl.updAppClubStatus(clubApply, model, request);
	}
	/**
	 * 修改状态为拒绝
	 */
	@RequestMapping("updAppClubStatusR")
	public String updAppClubStatusR(ClubApply clubApply, Model model, HttpServletRequest request) {
		return adminserviceImpl.updAppClubStatusR(clubApply, model, request);
	}

	/**
	 * 查看详情
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("selAppClubById")
	public String selAppClubById(int id,Model model) {
		return adminserviceImpl.selAppClubById(id,model);
	}
	
	
	/**
	 * 待审核的member_join_______________________________________________________________________
	 */
	/**
	 * 根据条件查询社团申请信息
	 * 
	 * @param club
	 * @return
	 */
	@RequestMapping("selAppMembers")
	public String selAppMembers(MemberJoin memberJoin, Model model) {
		return adminserviceImpl.selAppMembers(memberJoin, model);
	}

	/**
	 * 查询社团类型信息
	 * 
	 * @param page2
	 * @param club
	 * @return
	 */
	@RequestMapping("selAppMemberByPage")
	public String selAppMemberByPage(Page page, Model model) {
		return adminserviceImpl.selAppMemberByPage(null, page, model);
	}

	/**
	 * 修改申请状态为通过
	 * @param memberJoin
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("updAppMemberStatus")
	public String updAppMemberStatus(MemberJoin memberJoin, Model model, HttpServletRequest request) {
		return adminserviceImpl.updAppMemberStatus(memberJoin, model, request);
	}

	/**
	 * 修改申请状态为拒绝
	 * @param memberJoin
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("updAppMemberStatusR")
	public String updAppMemberStatusR(MemberJoin memberJoin, Model model, HttpServletRequest request) {
		return adminserviceImpl.updAppMemberStatusR(memberJoin, model, request);
	}

	/**
	 * 查看详情
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("selAppMemberById")
	public String selAppMemberById(int id, Model model) {
		return adminserviceImpl.selAppMemberById(id, model);
	}

	
	
	/**
	 * 待审核状态的活动event________________________________________________________________________________________
	 */
	/**
	 * 根据条件查询社团活动
	 * @param event
	 * @return
	 */
	@RequestMapping("selAppEvents")
	public String selAppEvents(Event event,Model model) {
		return adminserviceImpl.selAppEvents(event, model);
	}
	
	
	/**
	 * 查询活动
	 * @param page
	 * @return
	 */
	@RequestMapping("selAppEventByPage")
	public String selAppEventByPage(Page page,Model model) {
		return adminserviceImpl.selAppEventByPage(null, page, model);
	}
	
	/**
	 * 修改申请状态为通过
	 * @param event
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("updAppEventStatus")
	public String updAppEventStatus(Event event, Model model, HttpServletRequest request) {
		return adminserviceImpl.updAppEventStatus(event, model, request);
	}

	/**
	 * 修改申请状态为拒绝
	 * @param event
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("updAppEventStatusR")
	public String updAppEventStatusR(Event event, Model model, HttpServletRequest request) {
		return adminserviceImpl.updAppEventStatusR(event, model, request);
	}

	
	/**
	 * 删除活动
	 * 
	 * @param event
	 * @param model
	 * @return
	 */
	@RequestMapping("selAppEventById")
	public String selAppEventById(Event event, Model model) {
		return adminserviceImpl.selAppEventById(event, model);
	}
	
	
	//在线人数————————————————————————————————————————————————————————————————————————————————————————
	@RequestMapping("inline")
	public String inline(Model model) {
		return adminserviceImpl.inline(model);
	}
	
	//社团管理员管理###################################################################################################################################################
	
	/**
	 * 社团管理员登录
	 * @param admin
	 * @param request
	 * @return
	 */
	@RequestMapping(value="prLogin")
	public String prLogin(ClubMember president,Model model,HttpServletRequest request) {
		return adminserviceImpl.prLogin(president,model, request);
	}
	
	/**
	 * 管理员注销登录
	 * @param request
	 * @return
	 */
	@RequestMapping("prLogout")
	public String prLogout(Model model,HttpServletRequest request) {
		return adminserviceImpl.prLogout(model,request);
	}
	
	
	
	//修改会长用户个人信息
	@RequestMapping("updPrStudent")
	public String updPrStudent(Student student,Model model,HttpServletRequest request) {
		return adminserviceImpl.updPrStudent(student, model, request);
	}
	
	/**
	 * 查询某个学生加入社团的历史
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("joinClubHistory")
	public String joinClubHistory(Model model,HttpServletRequest request) {
		return adminserviceImpl.joinClubHistory(model, request);
	}
	
	
	/**
	 * 撤销加入该社团
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("cancelJoin")
	public String cancelJoin(MemberJoin memberJoin,Model model,HttpServletRequest request) {
		return adminserviceImpl.cancelJoin(memberJoin,model, request);
	}
	
	/**
	 * 进入会长个人中心
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("goStuCenterLeftOfMessage")
	public String goStuCenterLeftOfMessage(Model model,HttpServletRequest request) {
		return adminserviceImpl.goStuCenterLeftOfMessage(model, request);
	}
	
	/**
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("selMessage")
	public String selMessage(Model model,HttpServletRequest request) {
		return adminserviceImpl.selMessage(model, request);
	}
	
	/**
	 * 会长拒绝模板
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("selModelRejectPr")
	public String selModelRejectPr(Model model,HttpServletRequest request) {
		return adminserviceImpl.selModelRejectPr(model, request);
	}
	
	
	/**
	 * 会长通过模板
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("selModelPassPr")
	public String selModelPassPr(Model model,HttpServletRequest request) {
		return adminserviceImpl.selModelPassPr(model, request);
	}
	
	/**
	 * 修改拒绝模板
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("updRejectModelPr")
	public String updRejectModelPr(PrModel prModel,Model model,HttpServletRequest request) {
		return adminserviceImpl.updRejectModelPr(prModel,model, request);
	}
	/**
	 * 修改通过模板
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("updPassModelPr")
	public String updPassModelPr(PrModel prModel,Model model,HttpServletRequest request) {
		return adminserviceImpl.updPassModelPr(prModel,model, request);
	}
	
	/**
	 * 管理员拒绝模板
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("selModelReject")
	public String selModelReject(Model model,HttpServletRequest request) {
		return adminserviceImpl.selModelReject(model, request);
	}
	
	
	/**
	 * 管理员通过模板
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("selModelPass")
	public String selModelPass(Model model,HttpServletRequest request) {
		return adminserviceImpl.selModelPass(model, request);
	}
	
	
	/**
	 * 某个指定社团的图片----------------------------------------------------------------------------------------
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("selClubImage")
	public String selClubImage(Model model,HttpServletRequest request) {
		return adminserviceImpl.selClubImage(model,request);
	}
	
	@RequestMapping("addImage")
	public String addClubImage(Image image,Model model,HttpServletRequest request) {
		return adminserviceImpl.addClubImage(image,model,request);
	}
	
	
	
	/*@RequestMapping("load")
	public String load(MultipartFile file,String name,String url,Model model,HttpServletRequest request) throws IllegalStateException, IOException{
		System.err.println("image:"+name+","+url);
		return adminserviceImpl.load(file,name,url, model,request);
	}
	*/
	
	@RequestMapping("delImage")
	public String delClubImage(int id,Model model,HttpServletRequest request) {
		return adminserviceImpl.delClubImage(id,model,request);
	}
	
	
}
