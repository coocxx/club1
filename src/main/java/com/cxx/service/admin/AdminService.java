package com.cxx.service.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
import com.cxx.util.Page;

public interface AdminService {
	/**
	 * 管理员登录
	 * 
	 * @param admin
	 * @param request
	 * @return
	 */
	public String adminLogin(Admin admin, Model model, HttpServletRequest request);

	/**
	 * 管理员注销登录
	 * 
	 * @param admin
	 * @param request
	 * @return
	 */
	public String adminLogout(HttpServletRequest request);

	/**
	 * 查询全部管理员账号信息
	 * 
	 * @param request
	 * @return
	 */
	public String adminSelectAll(Page page, Model model);

	/**
	 * 准备修改管理员信息
	 * 
	 * @param id
	 * @return
	 */
	public ModelAndView preUpdAdmin(int id);

	/**
	 * 修改管理员信息
	 * 
	 * @param id
	 * @return
	 */
	public String updAdmin(Admin admin, Model model, HttpServletRequest request);

	/**
	 * 修改某个指定管理员的信息
	 * @param admin
	 * @param model
	 * @param request
	 * @return
	 */
	public String updOneAdmin(Admin admin, Model model, HttpServletRequest request);
	/**
	 * 添加管理员信息
	 * 
	 * @param id
	 * @return
	 */
	public String addAdmin(Admin admin, Model model);

	/**
	 * 删除管理员信息
	 * 
	 * @param id
	 * @return
	 */
	public String delAdmin(Admin admin, Model model);

	/**
	 * 根据管理员部分信息查询
	 * 
	 * @param admin
	 * @return
	 */
	public ModelAndView selAdmins(Admin admin, Page page);

	/**
	 * 社团--------------------------------------------------------------------------------------------
	 */
	public String selClubByPage(Page page, String result, Model model);

	/**
	 * 分页查询
	 * 
	 * @param url
	 * @param result
	 * @param page
	 * @return
	 */
	public String selByPage(String url, String result, Page page, Model model);

	/**
	 * 准备添加社团,从数据库里面取出社团类型
	 * 
	 * @param model
	 * @return
	 */
	public String preAddClub(Model model);

	/**
	 * 添加社团
	 * 
	 * @param model
	 * @return
	 */
	public String addClub(Club club, Model model);

	/**
	 * 准备修改社团
	 * 
	 * @param club
	 * @param model
	 * @return
	 */
	public String preUpdClub(Club club, String active, Model model, HttpServletRequest request);

	/**
	 * 修改社团
	 * 
	 * @param club
	 * @param model
	 * @return
	 */
	public String updClub(Club club, Model model);
	
	/**
	 * 修改社团
	 * 
	 * @param club
	 * @param model
	 * @return
	 */
	public String updClubPr(Club club, Model model, HttpServletRequest request);

	/**
	 * 删除社团
	 * 
	 * @param club
	 * @param model
	 * @return
	 */
	// public String delClub(Club club,Model model) ;

	/**
	 * 根据条件查询社团信息
	 * 
	 * @param club
	 * @return
	 */
	public String selClubs(Club club, Model model);

	/**
	 * 改变社团状态，恢复或者暂停
	 * 
	 * @return
	 */
	public String updStatus(Club club, Model model);

	/*
	 * 社团类型-------------------------------------------------------------------------
	 * --------------------
	 */
	/**
	 * 根据条件查询社团类型信息
	 * 
	 * @param club
	 * @return
	 */
	public String selClubTypes(ClubType clubType, Model model);

	/**
	 * 分页查询社团类型
	 * 
	 * @param url
	 * @param page
	 * @param clubType
	 * @return
	 */
	public String selClubTypeByPage(String result, Page page, Model model);

	/**
	 * 准备添加社团类型
	 * 
	 * @param club
	 * @param model
	 * @return
	 */
	public String addClubType(ClubType clubType, Model model);

	/**
	 * 准备修改社团类型
	 * 
	 * @param club
	 * @param model
	 * @return
	 */
	public String preUpdClubType(ClubType clubType, Model model);

	/**
	 * 修改社团类型
	 * 
	 * @param club
	 * @param model
	 * @return
	 */
	public String updClubType(ClubType clubType, Model model);

	/**
	 * 删除社团类型
	 * 
	 * @param club
	 * @param model
	 * @return
	 */
	public String delClubType(ClubType clubType, Model model);

	// club_apply-------------------------------------------------------------------------------------------------------
	/**
	 * 根据条件查询社团申请信息
	 * 
	 * @param club
	 * @return
	 */
	public String selClubApplies(ClubApply clubApply, Model model);

	/**
	 * 查询社团类型信息
	 * 
	 * @param page2
	 * @param club
	 * @return
	 */
	public String selClubApplyByPage(String result, Page page, Model model);

	/**
	 * 修改申请状态为通过
	 * 
	 * @param clubType
	 * @param model
	 * @return
	 */
	public String updClubApplyStatus(ClubApply clubApply, Model model, HttpServletRequest request);

	/**
	 * 修改申请状态为拒绝
	 * 
	 * @param clubType
	 * @param model
	 * @return
	 */
	public String updClubApplyStatusR(ClubApply clubApply, Model model, HttpServletRequest request);

	/**
	 * 查看详情
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	public String selClubApplyById(int id, Model model);

	/**
	 * 删除
	 * 
	 * @param delClubApply
	 * @param model
	 * @return
	 */
	public String delClubApply(ClubApply clubApply, Model model);

	/**
	 * academy学院-------------------------------------------------------------------------------------------
	 */

	/**
	 * 根据条件查询学院
	 * 
	 * @param academy
	 * @return
	 */
	public String selAcademys(Academy academy, Model model);

	/**
	 * 查询学院信息
	 * 
	 * @param club
	 * @return
	 */
	public String selAcademyByPage(String result, Page page, Model model);

	/**
	 * 查看详情
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	public String selAcademyById(int id, Model model);

	/**
	 * major专业-------------------------------------------------------------------------------------------
	 */

	/**
	 * 根据条件查询专业
	 * 
	 * @param academy
	 * @return
	 */
	public String selMajors(Major major, Model model);

	/**
	 * 查询专业信息
	 * 
	 * @param club
	 * @return
	 */
	public String selMajorByPage(String result, Page page, Model model);

	/**
	 * 查看详情
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("selMajorById")
	public String selMajorById(int id, Model model);

	/**
	 * classes班级-------------------------------------------------------------------------------------------
	 */

	/**
	 * 根据条件查询班级
	 * 
	 * @param classes
	 * @return
	 */
	public String selClasses(Classes classes, Model model);

	/**
	 * 查询班级信息
	 * 
	 * @param Page
	 * @return
	 */
	public String selClassesByPage(String result, Page page, Model model);

	/**
	 * 查看详情
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	public String selClassesById(int id, Model model);

	/**
	 * student学生-------------------------------------------------------------------------------------------
	 */

	/**
	 * 根据条件查询班级
	 * 
	 * @param classes
	 * @return
	 */
	public String selStudents(Student student, Model model);

	/**
	 * 查询学生信息
	 * 
	 * @param Page
	 * @return
	 */
	public String selStudentByPage(String result, Page page, Model model);
	/**
	 * 查询学生信息
	 * 
	 * @param Page
	 * @return
	 */
	public String selStuClubByPage(String result, Page page, Model model);

	/**
	 * 准备编辑学生信息详情
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	public String preUpdStudent(int id, Model model);

	/**
	 * 编辑学生信息详情
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	public String updStudent(Student student, Model model);

	/**
	 * 删除学生信息
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	public String delStudent(int id, Model model);

	/**
	 * 添加学生信息
	 * 
	 * @param student
	 * @param model
	 * @return
	 */
	public String addStudent(Student student, Model model);

	/**
	 * 准备添加学生账号信息
	 * 
	 * @param model
	 * @return
	 */
	public String preAddStu(Model model);

	/**
	 * 社团成员club_member-------------------------------------------------------------------------------------------
	 */

	/**
	 * 根据条件查询学生
	 * 
	 * @param academy
	 * @return
	 */
	public String selClubMembers(ClubMember clubMember, String active, Model model, HttpServletRequest request);

	/**
	 * 查询学生信息
	 * 
	 * @param Page
	 * @return
	 */
	public String selClubMemberByPage(String result, Page page, String active, Model model, HttpServletRequest request);

	/**
	 * 准备编辑学生信息详情
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	public String preUpdClubMember(int id, String active, Model model, HttpServletRequest request);

	/**
	 * 编辑学生信息
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	public String updClubMember(ClubMember clubMember, Model model, HttpServletRequest request);
	/**
	 * 编辑学生信息
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	public String updClubMemberPr(ClubMember clubMember, Model model, HttpServletRequest request);

	/**
	 * 删除学生信息
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	public String delClubMember(int id, String active, Model model, HttpServletRequest request);

	/**
	 * 准备添加学生账号信息
	 * 
	 * @param model
	 * @return
	 */
	public String preAddClubMember(Model model);

	/**
	 * 添加学生信息
	 * 
	 * @param student
	 * @param model
	 * @return
	 */
	public String addClubMember(ClubMember clubMember, Model model, HttpServletRequest request);
	/**
	 * 添加学生信息
	 * 
	 * @param student
	 * @param model
	 * @return
	 */
	public String addClubMemberPr(ClubMember clubMember, Model model, HttpServletRequest request);

	/**
	 * 退出社团
	 * 
	 * @param student
	 * @param model
	 * @return
	 */
	public String quitClubMember(ClubMember member, String active, Model model, HttpServletRequest request);
	
	/**
	 * 退出社团
	 * 
	 * @param student
	 * @param model
	 * @return
	 *//*
	public String quitClubMember(ClubMember member,String active, Model model,HttpServletRequest request);
*/
	/**
	 * 退出成员信息member_quit-------------------------------------------------------------------------------------------
	 */

	/**
	 * 根据条件查询退出社员信息
	 * 
	 * @param academy
	 * @return
	 */
	public String selMemberQuits(MemberQuit member, String active, Model model, HttpServletRequest request);

	/**
	 * 查询退出社员信息
	 * 
	 * @param Page
	 * @return
	 */
	public String selMemberQuitByPage(String result, String active, Page page, Model model, HttpServletRequest request);

	/**
	 * 查看详情
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	public String selMemberQuitById(int id, String active, Model model, HttpServletRequest request);

	/**
	 * 删除
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	public String delMemberQuit(int id, String active, Model model, HttpServletRequest request);

	// member_join-------------------------------------------------------------------------------------------------------
	/**
	 * 根据条件查询社团申请信息
	 * 
	 * @param club
	 * @return
	 */
	public String selMemberJoins(MemberJoin memberJoin, String active, Model model, HttpServletRequest request);

	/**
	 * 查询社团类型信息
	 * 
	 * @param page2
	 * @param club
	 * @return
	 */
	public String selMemberJoinByPage(String result, String active, Page page, Model model, HttpServletRequest request);

	/**
	 * 修改申请状态为通过
	 * 
	 * @param clubType
	 * @param model
	 * @return
	 */
	public String updMemberJoinStatus(MemberJoin memberJoin, String active, Model model, HttpServletRequest request);

	/**
	 * 修改申请状态为拒绝
	 * 
	 * @param clubType
	 * @param model
	 * @return
	 */
	public String updMemberJoinStatusR(MemberJoin memberJoin, String active, Model model, HttpServletRequest request);

	/**
	 * 查看详情
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	public String selMemberJoinById(int id, String active, Model model, HttpServletRequest request);

	/**
	 * 删除
	 * 
	 * @param delClubApply
	 * @param model
	 * @return
	 */
	public String delMemberJoin(MemberJoin member, String active, Model model, HttpServletRequest request);

	// notice-------------------------------------------------------------------------------------------------------
	/**
	 * 动态查询社团公告
	 * 
	 * @param notice
	 * @return
	 */
	public String selNotices(Notice notice, String action, Model model, HttpServletRequest request);

	/**
	 * 分页查询社团公告
	 * 
	 * @param page2
	 * @param notice
	 * @return
	 */
	public String selNoticeByPage(String result, String action, Page page, Model model, HttpServletRequest request);

	/**
	 * 准备修改社团公告信息
	 * 
	 * @param clubType
	 * @param model
	 * @return
	 */
	public String preUpdNotice(int id, String action, Model model, HttpServletRequest request);

	/**
	 * 修改社团公告信息
	 * 
	 * @param clubType
	 * @param model
	 * @return
	 */
	public String updNotice(Notice notice, String action, Model model, HttpServletRequest request);

	/**
	 * 修改
	 * @param updNotice
	 * @param model
	 * @return
	 */
	public String updNoticePr(Notice updNotice, String action, Model model, HttpServletRequest request);
	
	/**
	 * 修改社团公告信息
	 * 
	 * @param clubType
	 * @param model
	 * @return
	 */
	public String preAddNotice(Notice notice, Model model, HttpServletRequest request);

	/**
	 * 修改社团公告信息
	 * 
	 * @param clubType
	 * @param model
	 * @return
	 */
	public String addNotice(Notice notice, String action, Model model, HttpServletRequest request);
	public String addNoticePr(Notice notice, String action, Model model, HttpServletRequest request);
	/**
	 * 修改社团公告信息
	 * 
	 * @param clubType
	 * @param model
	 * @return
	 */
	public String delNotice(int id, String action, Model model, HttpServletRequest request);
	// news新闻动态-------------------------------------------------------------------------------------------------------
	/**
	 * 动态查询新闻动态
	 * 
	 * @param notice
	 * @return
	 */
	public String selNews(News news, String action, Model model);
	
	/**
	 * 分页查询新闻动态
	 * 
	 * @param page2
	 * @param News
	 * @return
	 */
	public String selNewsByPage(String result, String action, Page page, Model model);
	
	/**
	 * 准备修改新闻动态
	 * 
	 * @param News
	 * @param model
	 * @return
	 */
	public String preUpdNews(int id, String action, Model model);
	
	/**
	 * 修改社团公告信息
	 * 
	 * @param News
	 * @param model
	 * @return
	 */
	public String updNews(News news, Model model);
	
	
	/**
	 * 修改新闻动态
	 * 
	 * @param News
	 * @param model
	 * @return
	 */
	public String addNews(News news, Model model, HttpServletRequest request);
	
	/**
	 * 修改新闻动态
	 * 
	 * @param News
	 * @param model
	 * @return
	 */
	public String delNews(int id, Model model);

	
	
	/**
	 * 社团活动------------------------------------------------------------------------------------------------
	 */
	
	/**
	 * 根据条件查询社团活动
	 * @param news
	 * @return
	 */
	public String selEvents(Event event, String action, Model model, HttpServletRequest request);
	
	
	/**
	 * 查询活动
	 * @param page
	 * @return
	 */
	public String selEventByPage(String result, String action, Page page, Model model, HttpServletRequest request);
	
	/**
	 * 修改申请状态为通过
	 * 
	 * @param clubType
	 * @param model
	 * @return
	 */
	public String updEventStatus(Event event, Model model, HttpServletRequest request);

	/**
	 * 修改申请状态为拒绝
	 * 
	 * @param Event
	 * @param model
	 * @return
	 */
	public String updEventStatusR(Event event, Model model, HttpServletRequest request);

	
	/**
	 * 删除活动
	 * 
	 * @param Event
	 * @param model
	 * @return
	 */
	public String delEvent(Event event, String action, Model model, HttpServletRequest request);
	
	
	/**
	 * 准备修改活动
	 * 
	 * @param Event
	 * @param model
	 * @return
	 */
	public String preUpdEvent(int id, String action, Model model, HttpServletRequest request);
	
	
	
	/**
	 * 修改活动
	 * 
	 * @param Event
	 * @param model
	 * @return
	 */
	public String updEvent(Event event, Model model, HttpServletRequest request);
	
	
	
	
	/**
	 * 修改活动
	 * 
	 * @param Event
	 * @param model
	 * @return
	 */
	public String updEventPr(Event event, Model model, HttpServletRequest request);
	
	
	
	
	/**
	 * 添加活动
	 * 
	 * @param Event
	 * @param model
	 * @return
	 */
	public String addEvent(Event event, Model model, HttpServletRequest request);
	
	
	/**
	 * 指定社团添加活动
	 * 
	 * @param Event
	 * @param model
	 * @return
	 */
	public String addEventPr(Event event, Model model, HttpServletRequest request);
	
	
	
	/**
	 * 准备添加活动
	 * 
	 * @param Event
	 * @param model
	 * @return
	 */
	public String preAddEvent(Event event, Model model, HttpServletRequest request);
	
	
	
	//审核——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
		/**
		 * 根据条件查询社团待审核信息
		 * @param delClubApply
		 * @return
		 */
		public String selAppClubs(ClubApply clubApply, Model model);
		
		/**
		 * 查询社团待审核信息
		 * @param page
		 * @return
		 */
		public String selAppClubByPage(String result, Page page, Model model);
		
		public String updAppClubStatus(ClubApply clubApply, Model model, HttpServletRequest request);
		/**
		 * 修改状态为拒绝
		 */
		public String updAppClubStatusR(ClubApply clubApply, Model model, HttpServletRequest request);

		/**
		 * 查看详情
		 * 
		 * @param id
		 * @param model
		 * @return
		 */
		public String selAppClubById(int id, Model model);


		/**
		 * 待审核的member_join
		 */
		// club_apply-------------------------------------------------------------------------------------------------------
		/**
		 * 根据条件查询社团申请信息
		 * 
		 * @param club
		 * @return
		 */
		public String selAppMembers(MemberJoin memberJoin, Model model);

		/**
		 * 查询社团类型信息
		 * 
		 * @param page2
		 * @param club
		 * @return
		 */
		public String selAppMemberByPage(String result, Page page, Model model);

		/**
		 * 修改申请状态为通过
		 * 
		 * @param clubType
		 * @param model
		 * @return
		 */
		public String updAppMemberStatus(MemberJoin memberJoin, Model model, HttpServletRequest request);

		/**
		 * 修改申请状态为拒绝
		 * 
		 * @param clubType
		 * @param model
		 * @return
		 */
		public String updAppMemberStatusR(MemberJoin memberJoin, Model model, HttpServletRequest request);

		/**
		 * 查看详情
		 * 
		 * @param id
		 * @param model
		 * @return
		 */
		public String selAppMemberById(int id, Model model);

		
		
		/**
		 * 待审核状态的活动event________________________________________________________________________________________
		 */
		/**
		 * 根据条件查询社团活动
		 * @param event
		 * @return
		 */
		public String selAppEvents(Event event, Model model);
		
		
		/**
		 * 查询活动
		 * @param page
		 * @return
		 */
		public String selAppEventByPage(String result, Page page, Model model);
		
		/**
		 * 修改申请状态为通过
		 * 
		 * @param event
		 * @param model
		 * @return
		 */
		public String updAppEventStatus(Event event, Model model, HttpServletRequest request);

		/**
		 * 修改申请状态为拒绝
		 * 
		 * @param event
		 * @param model
		 * @return
		 */
		public String updAppEventStatusR(Event event, Model model, HttpServletRequest request);

		
		/**
		 * 删除活动
		 * 
		 * @param event
		 * @param model
		 * @return
		 */
		public String selAppEventById(Event event, Model model);
		
		
		//在线人数————————————————————————————————————————————————————————————————————————————————————————————————————————————————
		public String inline(Model model);
		
		
		
		
		//社团管理员管理###################################################################################################################################################
		
		/**
		 * 社团管理员登录
		 * @param admin
		 * @param request
		 * @return
		 */
		public String prLogin(ClubMember president, Model model, HttpServletRequest request);
		
		/**
		 * 管理员注销登录
		 * @param request
		 * @return
		 */
		public String prLogout(Model model, HttpServletRequest request);
		
		
		
		
		
		/**
		 * 修改会长管理员个人信息
		 * @param student
		 * @param model
		 * @param request
		 * @return
		 */
		public String updPrStudent(Student student, Model model, HttpServletRequest request);
		
		
		
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
		String cancelJoin(MemberJoin memberJoin, Model model, HttpServletRequest request);
		
		
		
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
		 * 会长拒绝模板
		 * @param model
		 * @param request
		 * @return
		 */
		String selModelRejectPr(Model model, HttpServletRequest request);
		/**
		 * 修改拒绝模板
		 * @param model
		 * @param request
		 * @return
		 */
		String updRejectModelPr(PrModel prModel, Model model, HttpServletRequest request);
		
		/**
		 * 修改通过模板
		 * @param model
		 * @param request
		 * @return
		 */
		public String updPassModelPr(PrModel prModel, Model model, HttpServletRequest request);
		
		/**
		 * 会长通过模板
		 * @param model
		 * @param request
		 * @return
		 */
		String selModelPassPr(Model model, HttpServletRequest request);
		
		/**
		 * 管理员拒绝模板
		 * @param model
		 * @param request
		 * @return
		 */
		String selModelReject(Model model, HttpServletRequest request);
		
		
		/**
		 * 管理员通过模板
		 * @param model
		 * @param request
		 * @return
		 */
		String selModelPass(Model model, HttpServletRequest request);
		
		/**
		 * 某个指定社团的图片---------------------------------------------
		 * @param model
		 * @param request
		 * @return
		 */
		String selClubImage(Model model, HttpServletRequest request);
		
		
		
		String addClubImage(Image image, Model model, HttpServletRequest request);
		
		
		
		
		/*String load(MultipartFile file,String name,String url,Model model,HttpServletRequest request)throws IllegalStateException, IOException;
		*/
		
		
		String delClubImage(int id, Model model, HttpServletRequest request);
		
		
		
}