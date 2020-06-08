package com.cxx.service.impl.admin;

import java.awt.Desktop.Action;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.mail.Session;
import javax.print.DocFlavor.STRING;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//import org.apache.catalina.connector.Request;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ObjectUtils.Null;
import org.apache.ibatis.annotations.Result;
import org.apache.logging.log4j.core.appender.rewrite.MapRewritePolicy.Mode;
import org.aspectj.weaver.NewMemberClassTypeMunger;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.cxx.mapper.AcademyMapper;
import com.cxx.mapper.AdminMapper;
import com.cxx.mapper.AdminModelMapper;
import com.cxx.mapper.ClassesMapper;
import com.cxx.mapper.ClubApplyMapper;
import com.cxx.mapper.ClubMapper;
import com.cxx.mapper.ClubMemberMapper;
import com.cxx.mapper.ClubTypeMapper;
import com.cxx.mapper.EventMapper;
import com.cxx.mapper.EventMemberMapper;
import com.cxx.mapper.ImageMapper;
import com.cxx.mapper.MajorMapper;
import com.cxx.mapper.MemberJoinMapper;
import com.cxx.mapper.MemberQuitMapper;
import com.cxx.mapper.NewsMapper;
import com.cxx.mapper.NoticeMapper;
import com.cxx.mapper.PrModelMapper;
import com.cxx.mapper.ReceiveMapper;
import com.cxx.mapper.StudentMapper;
import com.cxx.pojo.Academy;
import com.cxx.pojo.Admin;
import com.cxx.pojo.AdminModel;
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
import com.cxx.pojo.Receive;
import com.cxx.pojo.StuClub;
import com.cxx.pojo.Student;
import com.cxx.service.admin.AdminService;
import com.cxx.util.Page;

@Service
public class AdminServiceImpl implements AdminService {
	@Resource
	private AdminMapper adminMapper;
	@Resource
	private ClubMapper clubMapper;
	@Resource
	private ClubTypeMapper clubTypeMapper;
	@Resource
	private ClubApplyMapper clubApplyMapper;
	@Resource
	private ClubMemberMapper clubMemberMapper;
	@Resource
	private MemberQuitMapper memberQuitMapper;
	@Resource
	private MemberJoinMapper memberJoinMapper;
	@Resource
	private StudentMapper studentMapper;
	@Resource
	private ClassesMapper classesMapper;
	@Resource
	private MajorMapper majorMapper;
	@Resource
	private AcademyMapper academyMapper;
	@Resource
	private NoticeMapper noticeMapper;
	@Resource
	private NewsMapper newsMapper;
	@Resource
	private EventMapper eventMapper;
	@Resource
	private EventMemberMapper eventMemberMapper;
	@Resource
	private ReceiveMapper receiveMapper;
	@Resource
	private AdminModelMapper adminModelMapper;
	@Resource
	private PrModelMapper prModelMapper;
	@Resource
	private ImageMapper imageMapper;

	// 总
	/**
	 * 
	 * @param url
	 *            转向的页面地址
	 * @param page
	 *            page
	 * @param result
	 *            结果语句
	 * @return
	 */
	public String selByPage(String url, String result, Page page, Model model) {
		model.addAttribute("curPage", page);
		model.addAttribute("result", result);
		return url;
	}

	// 管理员-----------------------------------------------------------------------------------------------
	/**
	 * 管理员登录
	 */
	public String adminLogin(Admin admin, Model model, HttpServletRequest request) {
		System.err.println("admin:" + admin);
		Admin admin2 = adminMapper.selByAdmin(admin);
		HttpSession session = request.getSession();
		if (admin2 != null) {
			// 数据库存在该信息
			session.setAttribute("admin", admin2);
			System.err.println("admin:" + admin2);
			if (session.getAttribute("loginAdmin") != null) {
				session.removeAttribute("loginAdmin");
			}
			return "redirect:main.jsp";
		} else {
			// 数据库不存在该信息
			model.addAttribute("loginAdmin", admin);
			model.addAttribute("login", "登录失败");
			return "admin_login.jsp";
		}
	}

	public String adminLogout(HttpServletRequest request) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (admin != null) {
			request.getSession().removeAttribute("admin");
		}
		return "redirect:admin_login.jsp";
	}

	public String adminSelectAll(Page page, Model model) {
		if (page == null) {
			page = new Page();
		}
		if (page.getCurPage() == 0) {
			page.setCurPage(1);
		}
		if (page.getPageSize() == 0) {
			page.setPageSize(10);
		}
		int startPage = (page.getCurPage() - 1) * page.getPageSize();
		List<Admin> admins = adminMapper.selALLAdmins(startPage, page.getPageSize());
		if (admins != null && admins.size() != 0) {
			page.setData(admins);
			page.setSumDatas(adminMapper.selALLAdminCounts());
			page.setTotalPage((page.getSumDatas() - 1) / page.getPageSize() + 1);
			model.addAttribute("curPage", page);
		}
		System.out.println("admins:" + admins);
		System.err.println("curPage:" + page.getCurPage());
		System.err.println("pageSize:" + page.getPageSize());
		System.err.println("totalPage:" + page.getTotalPage());
		System.err.println("page:" + page);
		return "forward:admin_select.jsp";
	}

	public ModelAndView preUpdAdmin(int id) {
		ModelAndView mav = new ModelAndView("admin_update.jsp");
		Admin admin = adminMapper.selById(id);
		mav.addObject("admin", admin);
		return mav;
	}

	public String updAdmin(Admin admin, Model model, HttpServletRequest request) {
		int updAdmin = adminMapper.updAdmin(admin);
		if (updAdmin == 1) {
			request.getSession().setAttribute("update", "修改成功！");
			return adminSelectAll(null, model);
		} else {
			request.getSession().setAttribute("update", "修改失败！");
			return "redirect:admin_update.jsp";
		}
	}

	/**
	 * 修改某个特定管理员的信息
	 */
	public String updOneAdmin(Admin admin, Model model, HttpServletRequest request) {
		// 邮箱是否已经存在
		int samEmail = adminMapper.selIfExitSameEmail(admin.getEmail(), admin.getId());
		if (samEmail > 0) {
			// 邮箱已经存在
			model.addAttribute("result", "邮箱已经存在");
			return "admin_center.jsp";
		}
		// 电话是否已经存在
		int samPhone = adminMapper.selIfExitSamePhone(admin.getPhone(), admin.getId());
		if (samPhone > 0) {
			// 电话已经存在
			model.addAttribute("result", "电话已经存在");
			return "admin_center.jsp";
		}
		// 新的管理员名字是否已经存在、
		int sameName = adminMapper.selifSameName(admin);
		if (sameName > 0) {
			// 这个名字已经存在了
			model.addAttribute("result", "这个名字已经存在");
			return "admin_center.jsp";
		}
		// 修改管理员信息
		int updAdmin = adminMapper.updAdmin(admin);
		if (updAdmin != 1) {
			// 更新失败
			model.addAttribute("result", "更新失败");
			return "admin_center.jsp";
		}
		model.addAttribute("result", "更新成功");
		request.getSession().setAttribute("admin", adminMapper.selById(admin.getId()));
		return "admin_center.jsp";
	}

	/**
	 * 根据管理员信息查询满足条件的用户信息
	 */
	public ModelAndView selAdmins(Admin admin, Page page) {
		ModelAndView modelAndView = new ModelAndView("admin_select.jsp");
		if (page == null) {
			page = new Page(1, 100000, 0, 0, null);
		}
		if (page.getCurPage() == 0) {
			page.setCurPage(1);
		}
		if (page.getPageSize() == 0) {
			page.setPageSize(1000000);
		}

		// 用于分页查找
		int startPage = (page.getCurPage() - 1) * page.getPageSize();
		System.err.println("条件startPage:" + startPage);

		List<Admin> admins = adminMapper.selAdmins(admin.getId(), admin.getName(), admin.getEmail(), admin.getPhone(),
				admin.getTime());

		if (admins.size() != 0 && admins != null) {
			page.setData(admins);
			// 总数据数
			page.setSumDatas(adminMapper.selCountAdmins(admin));
			System.err.println("总数据数：" + page.getSumDatas());
			// 总页数
			page.setTotalPage((page.getSumDatas() - 1) / page.getPageSize() + 1);
			System.err.println("id:" + admin.getId());
			modelAndView.addObject("curPage", page);
			modelAndView.addObject("result", "查询成功");
		} else {

			modelAndView.addObject("result", "该信息不存在");
		}
		System.out.println("admins:" + admins);
		System.err.println("curPage:" + page.getCurPage());
		System.err.println("pageSize:" + page.getPageSize());
		System.err.println("totalPage:" + page.getTotalPage());
		System.err.println("page:" + page);
		return modelAndView;
	}

	public String addAdmin(Admin admin, Model model) {
		// 查询数据库是否存在相同名字的用户（除了本身以外）
		int ifExiteName = adminMapper.selAdminSameName(admin);
		model.addAttribute("addAdmin", admin);
		if (ifExiteName > 0) {
			System.out.println("admin2:" + ifExiteName);
			model.addAttribute("result", "用户名已存在");
			return "forward:admin_add.jsp";
		}
		// 邮箱是否已经存在
		if (adminMapper.selByEmail(admin.getEmail()).size() > 0) {
			// 邮箱已经存在
			model.addAttribute("result", "邮箱已存在");
			return "forward:admin_add.jsp";
		}
		// 手机号是否已经存在
		if (adminMapper.selbyPhone(admin.getPhone()).size() > 0) {
			// 手机号已经存在
			model.addAttribute("result", "手机号已存在");
			return "forward:admin_add.jsp";
		}
		// 数据库不存在，则添加用户
		if (adminMapper.insAdmin(admin) == 1) {
			// 添加成功
			System.out.println("添加成功");
			model.addAttribute("result", "添加成功");
			return adminSelectAll(null, model);
		} else {
			// 添加失败
			System.out.println("添加失败");
			model.addAttribute("result", "添加失败");
			return "forward:admin_add.jsp";
		}
	}

	public String delAdmin(Admin admin, Model model) {
		System.out.println("del admin:" + admin);
		int delAdmin = adminMapper.delAdmin(admin.getId());
		if (delAdmin == 1) {
			// 删除成功
			model.addAttribute("result", "删除成功");
		} else {
			// 删除失败
			model.addAttribute("result", "删除失败");
		}
		return adminSelectAll(null, model);
	}

	/**
	 * 社团------------------------------------------------------------------------------------------
	 */
	public String selClubByPage(Page page, String result, Model model) {
		if (page == null) {
			page = new Page(1, 10, 0, 0, null);
		}
		if (page.getCurPage() == 0) {
			page.setCurPage(1);
		}
		if (page.getPageSize() == 0) {
			page.setPageSize(10);
		}

		int starPage = (page.getCurPage() - 1) * page.getPageSize();
		List<Club> clubs = clubMapper.selClubByPage(starPage, page.getPageSize());
		System.err.println("clubs:" + clubs);
		if (clubs != null && clubs.size() != 0) {
			page.setData(clubs);
			page.setSumDatas(clubMapper.selClubCount());
			// 总页数
			page.setTotalPage((page.getSumDatas() - 1) / page.getPageSize() + 1);
			return selByPage("club_select.jsp", result, page, model);
		} else {
			return selByPage("club_select.jsp", "无数据", page, model);
		}
	}

	public String preAddClub(Model model) {
		List<ClubType> clubTypes = clubTypeMapper.selAllClubTypes();
		model.addAttribute("clubTypes", clubTypes);
		return "forward:club_add.jsp";
	}

	/**
	 * 添加社团
	 * 
	 * @param model
	 * @return
	 */
	public String addClub(Club club, Model model) {
		Club sameNameClub = clubMapper.selClubSameName(club);
		if (sameNameClub != null) {
			// 该社团已经存在，返回
			model.addAttribute("result", "该社团已经存在");
			model.addAttribute("addClub", club);
			return preAddClub(model);
		}
		Student student = studentMapper.selById(club.getStudentId());
		if (student == null) {
			// 该学生不存在，返回
			model.addAttribute("result", "该学生不存在");
			model.addAttribute("addClub", club);
			return preAddClub(model);
		}
		// 添加
		int addClub = clubMapper.insClub(club);
		if (addClub == 1) {
			// 添加成功
			return selClubByPage(null, "添加社团成功", model);
		} else {
			model.addAttribute("result", "添加社团失败");
			model.addAttribute("addClub", club);
			return "club_add.jsp";
		}
	}

	public String preUpdClub(Club club, String active, Model model, HttpServletRequest request) {
		if ("pr".equals(active)) {
			ClubMember president = (ClubMember) request.getSession().getAttribute("president");
			Club club2 = clubMapper.selById(president.getClubId());
			model.addAttribute("club", club2);
			System.out.println("preUpdClub club:" + club2);
			return "pr_club.jsp";
		}
		Club club2 = clubMapper.selById(club.getId());
		model.addAttribute("club", club2);
		System.out.println("preUpdClub club:" + club2);
		return "club_update.jsp";
	}

	public String updClub(Club club, Model model) {
		int updClub = clubMapper.updClub(club);
		if (updClub == 1) {
			// 更新成功
			model.addAttribute("result", "更新成功");
			System.out.println("updClub club:" + club);
			return selClubByPage(null, "保存成功", model);
		} else {
			// 更新失败
			model.addAttribute("result", "更新失败");
			model.addAttribute("club", club);
			System.out.println("updClub club:" + club);
			return "club_update.jsp";
		}
	}

	public String updClubPr(Club club, Model model, HttpServletRequest request) {
		System.err.println("updClubPr club:" + club);
		int updClub = clubMapper.updClub(club);
		if (updClub == 1) {
			// 更新成功
			model.addAttribute("result", "更新成功");
			System.out.println("updClub club:" + club);
			ClubMember president = clubMemberMapper.selPrsdtBySidStatus(club.getStudentId(), 1);
			request.getSession().setAttribute("president", president);
			return "pr_club.jsp";
		} else {
			// 更新失败
			model.addAttribute("result", "更新失败");
			model.addAttribute("club", club);
			System.out.println("updClub club:" + club);
			return "pr_club.jsp";
		}
	}

	public String delClub(Club club, Model model) {
		int delClub = clubMapper.delClub(club);
		System.err.println("delClub club:" + club);
		if (delClub == 1) {
			// 删除成功
			System.out.println("删除成功");
			return selClubByPage(null, "删除成功", model);
		} else {
			// 删除失败
			System.out.println("删除失败");
			model.addAttribute("result", "删除失败");

			return selClubByPage(null, "删除失败", model);
		}

	}

	/**
	 * 根据条件查询社团信息
	 * 
	 * @param club
	 * @return
	 */
	@RequestMapping("selClubs")
	public String selClubs(Club club, Model model) {
		System.err.println("club:" + club);
		Page page = new Page(1, 10000, 1, 0, null);

		List<Club> clubs = clubMapper.selClubs(club.getId(), club.getStudent().getName(), club.getClubType().getName(),
				club.getPlace(), club.getName(), club.getStatus(), club.getTime());
		if (clubs != null && clubs.size() != 0) {
			page.setData(clubs);
			page.setSumDatas(clubMapper.selClubCount());
			// 总页数
			model.addAttribute("result", "查询成功");
		} else {
			model.addAttribute("result", "查询失败");
		}
		System.err.println("page:" + page);
		model.addAttribute("curPage", page);
		return "club_select.jsp";
	}

	public String updStatus(Club club, Model model) {
		int updStatus = clubMapper.updStatus(club.getStatus(), club.getId());
		if (updStatus == 1) {
			// 更新成功
			System.err.println("更新成功");
			return selClubByPage(null, "更新成功", model);
		} else {
			// 更新失败
			System.err.println("更新失败");
			return selClubByPage(null, "更新失败", model);
		}

	}

	// 社团类型------------------------------------------------------------------------------------------------------------

	public String selClubTypeByPage(String result, Page page, Model model) {
		if (page == null) {
			page = new Page(1, 10, 0, 0, null);
		}
		if (page.getCurPage() == 0) {
			page.setCurPage(1);
		}
		if (page.getPageSize() == 0) {
			page.setPageSize(10);
		}

		int starPage = (page.getCurPage() - 1) * page.getPageSize();
		List<ClubType> clubTypes = clubTypeMapper.selClubTysByPage(starPage, page.getPageSize());
		System.err.println("clubTypes:" + clubTypes);
		if (clubTypes != null && clubTypes.size() != 0) {
			page.setData(clubTypes);
			page.setSumDatas(clubTypes.size());
			// 总页数
			page.setTotalPage((page.getSumDatas() - 1) / page.getPageSize() + 1);
			return selByPage("clubType_select.jsp", result, page, model);
		} else {
			return selByPage("clubType_select.jsp", "无数据", page, model);
		}
	}

	/**
	 * 根据条件查询社团类型信息
	 * 
	 * @param
	 * @return
	 */
	public String selClubTypes(ClubType clubType, Model model) {
		System.err.println("clubType:" + clubType);
		Page page = new Page(1, 10000, 1, 0, null);

		List<ClubType> clubTypes = clubTypeMapper.selClubTypes(clubType.getId(), clubType.getName());
		if (clubTypes != null && clubTypes.size() != 0) {
			page.setData(clubTypes);
			page.setSumDatas(clubTypes.size());
			// 总页数
			model.addAttribute("result", "查询成功");
		} else {
			model.addAttribute("result", "查询失败");
		}
		System.err.println("page:" + page);
		model.addAttribute("curPage", page);
		return "clubType_select.jsp";
	}

	public String addClubType(ClubType clubType, Model model) {
		ClubType clubType2 = clubTypeMapper.selByName(clubType.getName());
		System.err.println("clubType:" + clubType);
		if (clubType2 == null) {
			// 社团类型名称不存在
			// 添加
			int addClubType = clubTypeMapper.addClubType(clubType.getName());
			if (addClubType == 1) {
				// 添加成功
				return selClubTypeByPage("添加成功", null, model);
			} else {
				// 添加失败
				model.addAttribute("result", "添加失败");
				model.addAttribute("addClubType", clubType);
				return "clubType_add.jsp";
			}
		} else {
			// 社团类型名称存在
			// 退出
			model.addAttribute("result", "该社团类型已经存在");
			model.addAttribute("addClubType", clubType);
			return "clubType_add.jsp";
		}

	}

	public String preUpdClubType(ClubType clubType, Model model) {
		ClubType clubType2 = clubTypeMapper.selById(clubType.getId());
		model.addAttribute("clubType", clubType2);
		return "clubType_update.jsp";
	}

	public String updClubType(ClubType clubType, Model model) {
		ClubType updClubType = clubTypeMapper.selIfSameName(clubType.getName(), clubType.getId());
		if (updClubType == null) {
			// 不存在
			int updClubT = clubTypeMapper.updClubType(clubType);
			if (updClubT == 1) {
				// 修改成功
				System.err.println("修改成功");
				return selClubTypeByPage("修改成功", null, model);
			} else {
				// 修改失败
				System.err.println("修改失败");
				model.addAttribute("result", "修改失败");
				return "clubType_update.jsp";
			}
		} else {
			// 已经存在了
			System.err.println("修改失败");
			model.addAttribute("result", "该社团类型名字已经存在失败");
			return "clubType_update.jsp";
		}
	}

	public String delClubType(ClubType clubType, Model model) {
		int delClubType = clubTypeMapper.delClubType(clubType);
		if (delClubType == 1) {
			// 删除成功
			return selClubTypeByPage("删除成功", null, model);
		} else {
			// 删除失败
			return selClubTypeByPage("删除失败", null, model);
		}
	}

	// club_apply-----------------------------------------------------------------------------------------------------
	/**
	 * 动态查询
	 */
	public String selClubApplies(ClubApply clubApply, Model model) {
		System.err.println("clubApply:" + clubApply);
		Page page = new Page(1, 10000, 1, 0, null);

		List<ClubApply> clubApplies = clubApplyMapper.selClubApplies(clubApply.getId(), clubApply.getStudent().getId(),
				clubApply.getStudent().getName(), clubApply.getClubType().getName(), clubApply.getAdminId(),
				clubApply.getName(), clubApply.getStatus(), clubApply.getTime());
		if (clubApplies != null && clubApplies.size() != 0) {
			page.setData(clubApplies);
			page.setSumDatas(clubApplies.size());
			// 总页数
			model.addAttribute("result", "查询成功");
		} else {
			model.addAttribute("result", "没有符合条件的数据");
		}
		System.err.println("page:" + page);
		model.addAttribute("curPage", page);
		return "club_apply.jsp";
	}

	/**
	 * 分页查询
	 */
	public String selClubApplyByPage(String result, Page page, Model model) {
		if (page == null) {
			page = new Page(1, 10, 0, 0, null);
		}
		if (page.getCurPage() == 0) {
			page.setCurPage(1);
		}
		if (page.getPageSize() == 0) {
			page.setPageSize(10);
		}

		int starPage = (page.getCurPage() - 1) * page.getPageSize();
		List<ClubApply> clubApplies = clubApplyMapper.selClubApplyByPage(starPage, page.getPageSize());
		System.err.println("clubApplies:" + clubApplies);
		if (clubApplies != null && clubApplies.size() != 0) {
			page.setData(clubApplies);
			page.setSumDatas(clubApplyMapper.selClubApplyCount());
			// 总页数
			page.setTotalPage((page.getSumDatas() - 1) / page.getPageSize() + 1);
			return selByPage("club_apply.jsp", result, page, model);
		} else {
			return selByPage("club_apply.jsp", "无数据", page, model);
		}
	}

	/**
	 * 修改状态为通过
	 */
	public String updClubApplyStatus(ClubApply clubApply, Model model, HttpServletRequest request) {
		System.err.println("clubApply:" + clubApply);
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (admin == null) {
			System.out.println("您还没有登录，请登录");
			model.addAttribute("result", "请登录");
			return "club_apply.jsp";
		}
		// 社团是否已经存在
		Club clubSame = clubMapper.selByName(clubApply.getName());
		if (clubSame != null) {
			System.err.println("该社团名已存在");
			return selClubApplyByPage("该社团名已存在，拒绝通过，审核失败", null, model);
		}

		// 添加到club里面
		Club club = new Club();
		club.setStudentId(clubApply.getStudentId());
		club.setClubTypeId(clubApply.getClubTypeId());
		club.setName(clubApply.getName());
		club.setStatus(0);
		int addClub = clubMapper.insClub(club);
		if (addClub != 1) {
			// 添加失败
			return selClubApplyByPage("审核失败", null, model);
		}
		// 社团添加成功之后

		// 添加该申请人为会长
		// 添加到club_member里面
		ClubMember clubMember = new ClubMember();
		Student student = new Student();
		student.setId(clubApply.getStudentId());
		clubMember.setStudent(student);
		Club club2 = clubMapper.selByName(clubApply.getName());
		clubMember.setClub(club2);
		clubMember.setStatus(1);
		int addClubMember = clubMemberMapper.insClubMember(clubMember);
		if (addClubMember != 1) {
			// 添加失败
			return selClubApplyByPage("审核失败", null, model);
		}
		// 成员添加成功

		int updStatus = clubApplyMapper.updStatus(clubApply.getStatus(), clubApply.getId(), admin.getId());
		if (updStatus != 1) {
			// 添加失败
			return selClubApplyByPage("审核失败", null, model);
		}
		// 修改成功,审核通过
		// 给社长发送申请成功通知
		AdminModel adminModel = adminModelMapper.selByAdminIdStatusType(admin.getId(), 1, 1);
		if (receiveMapper.insRecieve(adminModel.getContent(), clubApply.getStudentId(), 1, 1) != 1) {
			// 添加失败
			return selClubApplyByPage("拒绝失败", null, model);
		}

		return selClubApplyByPage("审核成功", null, model);
	}

	/**
	 * 修改状态为拒绝
	 */
	public String updClubApplyStatusR(ClubApply clubApply, Model model, HttpServletRequest request) {
		System.err.println("clubApply:" + clubApply);

		// 社团是否已经存在

		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (admin == null) {
			System.out.println("您还没有登录，请登录");
			model.addAttribute("result", "请登录");
			return "club_apply.jsp";
		}
		// 添加到club里面
		int updStatus = clubApplyMapper.updStatus(0, clubApply.getId(), admin.getId());
		if (updStatus != 1) {
			//
			return selClubApplyByPage("拒绝失败", null, model);
		}
		// 修改成功,拒绝成功
		// 给社长发送申请失败通知
		AdminModel adminModel = adminModelMapper.selByAdminIdStatusType(admin.getId(), 2, 1);
		if (receiveMapper.insRecieve(adminModel.getContent(), clubApply.getStudentId(), 1, 1) != 1) {
			// 添加失败
			return selClubApplyByPage("拒绝失败", null, model);
		}

		return selClubApplyByPage("拒绝成功", null, model);
	}

	/**
	 * 查看详情
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	public String selClubApplyById(int id, Model model) {
		ClubApply clubApply = clubApplyMapper.selById(id);
		if (clubApply != null) {
			model.addAttribute("clubApply", clubApply);
			return "club_apply_sel.jsp";
		} else {
			model.addAttribute("result", "查看失败");
			return "club_apply.jsp";
		}
	}

	/**
	 * 删除
	 * 
	 * @param delClubApply
	 * @param model
	 * @return
	 */
	public String delClubApply(ClubApply clubApply, Model model) {
		int del = clubApplyMapper.delClubApplyById(clubApply.getId());
		if (del != 1) {
			return selClubApplyByPage("删除失败", null, model);
		}
		return selClubApplyByPage("删除成功", null, model);
	}

	// academy学院--------------------------------------------------------------------------------------------------------------

	public String selAcademys(Academy academy, Model model) {
		System.err.println("academy:" + academy);
		Page page = new Page(1, 10000, 1, 0, null);

		List<Academy> academys = academyMapper.selAcademys(academy.getId(), academy.getName());
		if (academys != null && academys.size() != 0) {
			page.setData(academys);
			page.setSumDatas(academys.size());
			// 总页数
			model.addAttribute("result", "查询成功");
		} else {
			model.addAttribute("result", "没有符合条件的数据");
		}
		System.err.println("page:" + page);
		model.addAttribute("curPage", page);
		return "academy.jsp";
	}

	public String selAcademyByPage(String result, Page page, Model model) {
		if (page == null) {
			page = new Page(1, 10, 0, 0, null);
		}
		if (page.getCurPage() == 0) {
			page.setCurPage(1);
		}
		if (page.getPageSize() == 0) {
			page.setPageSize(10);
		}

		int starPage = (page.getCurPage() - 1) * page.getPageSize();
		List<Academy> academies = academyMapper.selAcademyByPage(starPage, page.getPageSize());
		System.err.println("academies:" + academies);
		if (academies != null && academies.size() != 0) {
			page.setData(academies);
			page.setSumDatas(academyMapper.selAcademyCount());
			// 总页数
			page.setTotalPage((page.getSumDatas() - 1) / page.getPageSize() + 1);
			return selByPage("academy.jsp", result, page, model);
		} else {
			return selByPage("academy.jsp", "无数据", page, model);
		}
	}

	public String selAcademyById(int id, Model model) {
		Academy academy = academyMapper.selById(id);
		if (academy != null) {
			model.addAttribute("academy", academy);
			return "academy_sel.jsp";
		} else {
			model.addAttribute("result", "查看失败");
			return "academy.jsp";
		}
	}

	// major专业--------------------------------------------------------------------------------------------------------------

	public String selMajors(Major major, Model model) {
		System.err.println("major:" + major);
		Page page = new Page(1, 10000, 1, 0, null);

		List<Major> majors = majorMapper.selMajors(major.getId(), major.getName(), major.getAcademy().getId(),
				major.getAcademy().getName());
		if (majors != null && majors.size() != 0) {
			page.setData(majors);
			page.setSumDatas(majors.size());
			// 总页数
			model.addAttribute("result", "查询成功");
		} else {
			model.addAttribute("result", "没有符合条件的数据");
		}
		System.err.println("page:" + page);
		model.addAttribute("curPage", page);
		return "major.jsp";
	}

	public String selMajorByPage(String result, Page page, Model model) {
		if (page == null) {
			page = new Page(1, 10, 0, 0, null);
		}
		if (page.getCurPage() == 0) {
			page.setCurPage(1);
		}
		if (page.getPageSize() == 0) {
			page.setPageSize(10);
		}

		int starPage = (page.getCurPage() - 1) * page.getPageSize();
		List<Major> majors = majorMapper.selMajorByPage(starPage, page.getPageSize());
		System.err.println("majors:" + majors);
		if (majors != null && majors.size() != 0) {
			page.setData(majors);
			page.setSumDatas(majorMapper.selMajorCount());
			// 总页数
			page.setTotalPage((page.getSumDatas() - 1) / page.getPageSize() + 1);
			return selByPage("major.jsp", result, page, model);
		} else {
			return selByPage("major.jsp", "无数据", page, model);
		}
	}

	public String selMajorById(int id, Model model) {
		Major major = majorMapper.selById(id);
		if (major != null) {
			model.addAttribute("major", major);
			return "major_sel.jsp";
		} else {
			model.addAttribute("result", "查看失败");
			return "major.jsp";
		}
	}

	// classes班级--------------------------------------------------------------------------------------------------------------

	public String selClasses(Classes classes, Model model) {
		System.err.println("classes:" + classes);
		Page page = new Page(1, 10000, 1, 0, null);

		List<Classes> classes1 = classesMapper.selClasses(classes.getId(), classes.getName(),
				classes.getMajor().getAcademy().getId(), classes.getMajor().getAcademy().getName(),
				classes.getMajor().getId(), classes.getMajor().getName());
		if (classes1 != null && classes1.size() != 0) {
			page.setData(classes1);
			page.setSumDatas(classes1.size());
			// 总页数
			model.addAttribute("result", "查询成功");
		} else {
			model.addAttribute("result", "没有符合条件的数据");
		}
		System.err.println("page:" + page);
		model.addAttribute("curPage", page);
		return "classes.jsp";
	}

	public String selClassesByPage(String result, Page page, Model model) {
		if (page == null) {
			page = new Page(1, 10, 0, 0, null);
		}
		if (page.getCurPage() == 0) {
			page.setCurPage(1);
		}
		if (page.getPageSize() == 0) {
			page.setPageSize(10);
		}

		int starPage = (page.getCurPage() - 1) * page.getPageSize();
		List<Classes> classes = classesMapper.selClassesByPage(starPage, page.getPageSize());
		System.err.println("classes:" + classes);
		if (classes != null && classes.size() != 0) {
			page.setData(classes);
			page.setSumDatas(classesMapper.selCLassesCount());
			// 总页数
			page.setTotalPage((page.getSumDatas() - 1) / page.getPageSize() + 1);
			return selByPage("classes.jsp", result, page, model);
		} else {
			return selByPage("classes.jsp", "无数据", page, model);
		}
	}

	public String selClassesById(int id, Model model) {
		Classes classes = classesMapper.selById(id);
		if (classes != null) {
			model.addAttribute("classes", classes);
			return "classes_sel.jsp";
		} else {
			model.addAttribute("result", "查看失败");
			return "classes.jsp";
		}
	}

	// student学生--------------------------------------------------------------------------------------------------------------

	public String selStudents(Student student, Model model) {
		System.err.println("student:" + student);
		Page page = new Page(1, 10000, 1, 0, null);

		List<Student> students = studentMapper.selStudents(student.getId(), student.getName(),
				student.getClasses().getMajor().getAcademy().getId(),
				student.getClasses().getMajor().getAcademy().getName(), student.getClasses().getMajor().getId(),
				student.getClasses().getMajor().getName(), student.getClassesId(), student.getClasses().getName(),
				student.getSex(), student.getEmail(), student.getPhone(), student.getTime(), student.getStatus());
		if (students != null && students.size() != 0) {
			page.setData(students);
			page.setSumDatas(students.size());
			// 总页数
			model.addAttribute("result", "查询成功");
		} else {
			model.addAttribute("result", "没有符合条件的数据");
		}
		System.err.println("page:" + page);
		model.addAttribute("curPage", page);
		return "student.jsp";
	}

	public String selStudentByPage(String result, Page page, Model model) {
		System.err.println("selStudentByPage");
		if (page == null) {
			page = new Page(1, 10, 0, 0, null);
		}
		if (page.getCurPage() == 0) {
			page.setCurPage(1);
		}
		if (page.getPageSize() == 0) {
			page.setPageSize(10);
		}

		int starPage = (page.getCurPage() - 1) * page.getPageSize();
		List<Student> students = studentMapper.selStudentByPage(starPage, page.getPageSize());
		// List<Club> clubs = clubMapper.
		System.err.println("students:" + students);
		if (students != null && students.size() != 0) {
			page.setData(students);
			page.setSumDatas(studentMapper.selStudentsCount());
			// 总页数
			page.setTotalPage((page.getSumDatas() - 1) / page.getPageSize() + 1);
			return selByPage("student.jsp", result, page, model);
		} else {
			return selByPage("student.jsp", "无数据", page, model);
		}
	}

	public String selStuClubByPage(String result, Page page, Model model) {
		System.err.println("selStuClubByPage");
		if (page == null) {
			page = new Page(1, 10, 0, 0, null);
		}
		if (page.getCurPage() == 0) {
			page.setCurPage(1);
		}
		if (page.getPageSize() == 0) {
			page.setPageSize(10);
		}

		int starPage = (page.getCurPage() - 1) * page.getPageSize();
		List<Student> students = studentMapper.selStudentByPage(starPage, page.getPageSize());
		// List<Club> clubs = clubMapper.
		List<StuClub> stuClubs = studentMapper.selStudentClub();
		System.err.println("students:" + students);
		System.err.println("stuClubs:" + stuClubs);
		if (students != null && students.size() != 0) {
			page.setData(students);
			page.setSumDatas(studentMapper.selStudentsCount());
			// 总页数
			page.setTotalPage((page.getSumDatas() - 1) / page.getPageSize() + 1);
			model.addAttribute("stuClubs", stuClubs);
			return selByPage("student.jsp", result, page, model);
		} else {
			return selByPage("student.jsp", "无数据", page, model);
		}
	}

	public String preUpdStudent(int id, Model model) {
		Student student = studentMapper.selById(id);
		model.addAttribute("updStudent", student);
		if (student != null) {
			return "student_update.jsp";
		} else {
			model.addAttribute("result", "查看失败");
			return selStuClubByPage("查看失败", null, model);
		}
	}

	public String updStudent(Student student, Model model) {
		System.err.println("updStudent:" + student);
		// 修改之前判断是否存在除本身以外一样的邮箱电话
		int ifEmailSame = studentMapper.selIfSameEmail(student.getEmail(), student.getId());
		if (ifEmailSame > 0) {
			model.addAttribute("updStudent", student);
			model.addAttribute("result", "邮箱已经存在");
			return "student_update.jsp";
		}
		int ifSamePhone = studentMapper.selIfSamePhone(student.getPhone(), student.getId());
		if (ifSamePhone > 0) {
			model.addAttribute("updStudent", student);
			model.addAttribute("result", "该电话已经存在");
			return "student_update.jsp";
		}
		int update = studentMapper.updStudent(student);
		if (update == 1) {
			// 成功
			System.err.println("修改成功");
			return selStuClubByPage("保存成功", null, model);
		} else {
			System.err.println("修改失败");
			model.addAttribute("updStudent", student);
			model.addAttribute("result", "保存失败");

			return "student_update.jsp";
		}

	}

	public String delStudent(int id, Model model) {
		int delStudent = studentMapper.delStudent(id);
		if (delStudent == 1) {
			// 删除成功
			return selStuClubByPage("删除成功", null, model);
		} else {
			// 删除失败
			return selStuClubByPage("删除失败", null, model);
		}
	}

	public String addStudent(Student student, Model model) {
		// 添加之前确认学号，电话号码跟邮箱是否已经注册
		Student ifExitSameId = studentMapper.selById(student.getId());
		if (ifExitSameId != null) {
			model.addAttribute("student", student);
			model.addAttribute("classes", classesMapper.selAllClasses());
			model.addAttribute("result", "该学生学号对应账户已经存在");
			return "student_add.jsp";
		}

		Student ifEmail = studentMapper.selByEmail(student.getEmail());
		if (ifEmail != null) {
			model.addAttribute("student", student);
			model.addAttribute("result", "邮箱已经存在");
			model.addAttribute("classes", classesMapper.selAllClasses());
			return "student_add.jsp";
		}
		List<Student> ifphone = studentMapper.selStudentByPhone(student.getPhone());
		if (ifphone != null && ifphone.size() != 0) {
			model.addAttribute("student", student);
			model.addAttribute("result", "该电话已经存在");
			model.addAttribute("classes", classesMapper.selAllClasses());
			return "student_add.jsp";
		}

		int addStu = studentMapper.insStudent(student);
		if (addStu == 1) {
			System.err.println("添加成功");
			return selStuClubByPage("添加成功", null, model);
		} else {
			System.err.println("添加失败");
			model.addAttribute("student", student);
			model.addAttribute("classes", classesMapper.selAllClasses());
			return "student_add.jsp";
		}
	}

	public String preAddStu(Model model) {
		List<Classes> classes = classesMapper.selAllClasses();
		model.addAttribute("classes", classes);
		return "student_add.jsp";
	}

	// club_member----------------------------------------------------------------------------------------------------------------
	public String quitClubMember(ClubMember member, String active, Model model, HttpServletRequest request) {
		System.err.println("quitClubMember:" + member);
		int delStudent = clubMemberMapper.delClubMember(member.getId());
		if (delStudent == 1) {
			// 删除成功
			// 加入社员退出表
			int addMemberQuit = memberQuitMapper.insMemberQuit(member.getStudentId(), member.getClubId());
			if (addMemberQuit == 1) {
				// 添加成功
				if ("pr".equals(active)) {
					return selClubMemberByPage("申请退出成功", null, "pr", model, request);
				} else {
					return selClubMemberByPage("申请退出成功", null, null, model, request);
				}
			} else {
				if ("pr".equals(active)) {
					return selClubMemberByPage("申请退出失败", null, "pr", model, request);
				} else {
					return selClubMemberByPage("申请退出失败", null, null, model, request);
				}

			}
		} else {
			// 删除失败
			if ("pr".equals(active)) {
				return selClubMemberByPage("申请退出失败", null, "pr", model, request);
			}
			return selClubMemberByPage("申请退出失败", null, null, model, request);
		}

	}

	public String selClubMembers(ClubMember clubMember, String active, Model model, HttpServletRequest request) {
		System.err.println("clubMember:" + clubMember);
		Page page = new Page(1, 10000, 1, 0, null);

		List<ClubMember> clubMembers = new ArrayList<ClubMember>();
		if ("pr".equals(active)) {
			ClubMember president = (ClubMember) request.getSession().getAttribute("president");
			if (president != null) {
				clubMembers = clubMemberMapper.selClubMembers("pr", clubMember.getId(), clubMember.getStudent().getId(),
						clubMember.getStudent().getName(), president.getClubId(), null, clubMember.getTime(),
						clubMember.getStatus());
				page.setSumDatas(clubMemberMapper.selCountOfClub(president.getClubId()));
			}
		} else {
			clubMembers = clubMemberMapper.selClubMembers("normal", clubMember.getId(), clubMember.getStudent().getId(),
					clubMember.getStudent().getName(), clubMember.getClub().getId(), clubMember.getClub().getName(),
					clubMember.getTime(), clubMember.getStatus());
			page.setSumDatas(clubMembers.size());
			// 总页数
		}
		if (clubMembers != null && clubMembers.size() != 0) {
			page.setData(clubMembers);

			model.addAttribute("result", "查询成功");
		} else {
			model.addAttribute("result", "没有符合条件的数据");
		}
		System.err.println("page:" + page);
		model.addAttribute("curPage", page);
		if ("pr".equals(active)) {
			return "pr_member.jsp";
		} else {
			return "club_member.jsp";
		}

	}

	public String selClubMemberByPage(String result, Page page, String active, Model model,
			HttpServletRequest request) {
		if (page == null) {
			page = new Page(1, 10, 0, 0, null);
		}
		if (page.getCurPage() == 0) {
			page.setCurPage(1);
		}
		if (page.getPageSize() == 0) {
			page.setPageSize(10);
		}

		int starPage = (page.getCurPage() - 1) * page.getPageSize();
		List<ClubMember> clubMembers = new ArrayList<ClubMember>();
		if ("pr".equals(active)) {
			ClubMember president = (ClubMember) request.getSession().getAttribute("president");
			if (president != null) {
				clubMembers = clubMemberMapper.selClubMemberOfClubByPage(starPage, page.getPageSize(),
						president.getClubId());
				page.setSumDatas(clubMemberMapper.selCountOfClub(president.getClubId()));
			}
		} else {
			clubMembers = clubMemberMapper.selClubMembersByPage(starPage, page.getPageSize());
			page.setSumDatas(clubMemberMapper.selAllCounts());
			// 总页数
		}
		System.err.println("ClubMembers:" + clubMembers);
		if (clubMembers != null && clubMembers.size() != 0) {
			page.setData(clubMembers);

			// 总页数
			page.setTotalPage((page.getSumDatas() - 1) / page.getPageSize() + 1);
			if ("pr".equals(active)) {
				return selByPage("pr_member.jsp", result, page, model);
			} else {
				return selByPage("club_member.jsp", result, page, model);
			}

		} else {
			if ("pr".equals(active)) {
				return selByPage("pr_member.jsp", "无数据", page, model);
			} else {
				return selByPage("club_member.jsp", "无数据", page, model);
			}

		}
	}

	public String preUpdClubMember(int id, String active, Model model, HttpServletRequest request) {
		ClubMember clubMember = clubMemberMapper.selById(id);
		model.addAttribute("clubMember", clubMember);
		if ("pr".equals(active)) {
			return "pr_member_update.jsp";
		} else {
			return "club_member_update.jsp";
		}

	}

	public String updClubMember(ClubMember clubMember, Model model, HttpServletRequest request) {
		int upd = clubMemberMapper.updClubMember(clubMember);
		if (upd == 1) {
			// 修改成功
			return selClubMemberByPage("保存成功", null, null, model, request);
		} else {
			// 修改失败
			model.addAttribute("result", "保存失败");
			return "club_member_update.jsp";
		}

	}

	public String updClubMemberPr(ClubMember clubMember, Model model, HttpServletRequest request) {
		int upd = clubMemberMapper.updClubMember(clubMember);
		if (upd == 1) {
			// 修改成功
			return selClubMemberByPage("保存成功", null, "pr", model, request);
		} else {
			// 修改失败
			model.addAttribute("result", "保存失败");
			return "pr_member_update.jsp";
		}

	}

	public String delClubMember(int id, String active, Model model, HttpServletRequest request) {
		int delClubMember = clubMemberMapper.delClubMember(id);
		if (delClubMember == 1) {
			// 删除成功
			if ("pr".equals(active)) {
				return selClubMemberByPage("删除成功", null, "pr", model, request);
			}
			return selClubMemberByPage("删除成功", null, null, model, request);
		} else {
			// 删除失败
			if ("pr".equals(active)) {
				return selClubMemberByPage("删除失败", null, "pr", model, request);
			}
			return selClubMemberByPage("删除失败", null, null, model, request);
		}
	}

	public String preAddClubMember(Model model) {
		List<Club> clubs = clubMapper.selAllClubs();
		model.addAttribute("clubs", clubs);
		return "club_member_add.jsp";
	}

	public String addClubMember(ClubMember clubMember, Model model, HttpServletRequest request) {
		System.err.println("clubM:" + clubMember);
		Student student = studentMapper.selById(clubMember.getStudent().getId());
		if (student == null) {
			// 学号不存在
			model.addAttribute("result", "学号不存在");
			model.addAttribute("clubMember", clubMember);
			model.addAttribute("clubs", clubMapper.selAllClubs());
			return "club_member_add.jsp";
		}
		// 学号存在

		// 该社团是否已经存在该成员
		System.err.println("addClubMember:" + clubMember);
		int exit = clubMemberMapper.selifStudentIdExit(clubMember.getStudent().getId(), clubMember.getClub().getId());
		if (exit > 0) {
			// 该社团成员已经存在
			model.addAttribute("clubMember", clubMember);
			model.addAttribute("clubs", clubMapper.selAllClubs());
			model.addAttribute("result", "该社团成员已经存在");
			return "club_member_add.jsp";
		}
		// 该成员还没有存在
		// 判断是不是会长
		if (clubMember.getStatus() == 1) {
			// 是会长
			// 判断这个社团会长是不是已经存在了，存在添加失败
			int ifPresident = clubMemberMapper.selIfPresidentExit(clubMember.getClub().getId(), 1);
			if (ifPresident > 0) {
				// 已经存在
				model.addAttribute("result", "这个社团会长已经存在");
				model.addAttribute("clubMember", clubMember);
				model.addAttribute("clubs", clubMapper.selAllClubs());
				return "club_member_add.jsp";
			}
		}
		int add = clubMemberMapper.insClubMember(clubMember);
		if (add == 1) {
			// 添加成功
			return selClubMemberByPage("添加成功", null, null, model, request);
		} else {
			// 添加失败
			model.addAttribute("clubMember", clubMember);
			model.addAttribute("clubs", clubMapper.selAllClubs());
			model.addAttribute("result", "添加失败");
			return "club_member_add.jsp";
		}
	}

	public String addClubMemberPr(ClubMember clubMember, Model model, HttpServletRequest request) {
		System.err.println("clubM:" + clubMember);
		Student student = studentMapper.selById(clubMember.getStudent().getId());
		if (student == null) {
			// 学号不存在
			model.addAttribute("result", "学号不存在");
			model.addAttribute("clubMember", clubMember);
			model.addAttribute("clubs", clubMapper.selAllClubs());
			return "pr_member_add.jsp";
		}
		// 学号存在

		// 该社团是否已经存在该成员
		ClubMember president = (ClubMember) request.getSession().getAttribute("president");

		if (president != null) {
			int exit = clubMemberMapper.selifStudentIdExit(clubMember.getStudent().getId(), president.getClubId());
			if (exit > 0) {
				// 该社团成员已经存在
				model.addAttribute("clubMember", clubMember);
				model.addAttribute("clubs", clubMapper.selAllClubs());
				model.addAttribute("result", "该社团成员已经存在");
				return "pr_member_add.jsp";
			}
			// 判断是不是会长
			if (clubMember.getStatus() == 1) {
				// 是会长
				// 判断这个社团会长是不是已经存在了，存在添加失败
				int ifPresident = clubMemberMapper.selIfPresidentExit(clubMember.getClub().getId(), 1);
				if (ifPresident > 0) {
					// 已经存在
					model.addAttribute("result", "这个社团会长已经存在");
					model.addAttribute("clubMember", clubMember);
					model.addAttribute("clubs", clubMapper.selAllClubs());
					return "club_member_add.jsp";
				}
			}
			int add = clubMemberMapper.insClubMember(clubMember);
			if (add == 1) {
				// 添加成功
				return selClubMemberByPage("添加成功", null, "pr", model, request);
			} else {
				// 添加失败
				model.addAttribute("clubMember", clubMember);
				model.addAttribute("clubs", clubMapper.selAllClubs());
				model.addAttribute("result", "添加失败");
				return "pr_member_add.jsp";
			}
		} else {
			model.addAttribute("result", "请登录");
			return "pr_member_add.jsp";
		}

	}

	// member_join----------------------------------------------------------------------------------------------------------------

	public String selMemberQuits(MemberQuit member, String active, Model model, HttpServletRequest request) {
		System.err.println("active:" + active);
		System.err.println("member:" + member);
		Page page = new Page(1, 10000, 1, 0, null);

		List<MemberQuit> members = new ArrayList<MemberQuit>();
		if ("pr".equals(active)) {
			ClubMember president = (ClubMember) request.getSession().getAttribute("president");
			if (president != null) {
				members = memberQuitMapper.selMemberQuits("pr", member.getId(), member.getStudent().getId(),
						member.getStudent().getName(), president.getClubId(), null, member.getTime());
			} else {
				model.addAttribute("result", "请登录");
				return "pr_member_join.jsp";
			}
		} else {
			members = memberQuitMapper.selMemberQuits("normal", member.getId(), member.getStudent().getId(),
					member.getStudent().getName(), member.getClub().getId(), member.getClub().getName(),
					member.getTime());

		}
		if (members != null && members.size() != 0) {
			page.setData(members);
			page.setSumDatas(members.size());
			// 总页数
			model.addAttribute("result", "查询成功");
		} else {
			model.addAttribute("result", "没有符合条件的数据");
		}
		System.err.println("page:" + page);
		model.addAttribute("curPage", page);
		if ("pr".equals(active)) {
			return "pr_member_quit.jsp";
		}
		return "member_quit.jsp";
	}

	public String selMemberQuitByPage(String result, String active, Page page, Model model,
			HttpServletRequest request) {
		if (page == null) {
			page = new Page(1, 10, 0, 0, null);
		}
		if (page.getCurPage() == 0) {
			page.setCurPage(1);
		}
		if (page.getPageSize() == 0) {
			page.setPageSize(10);
		}

		int starPage = (page.getCurPage() - 1) * page.getPageSize();
		List<MemberQuit> members = new ArrayList<MemberQuit>();
		if ("pr".equals(active)) {
			ClubMember president = (ClubMember) request.getSession().getAttribute("president");
			if (president != null) {
				members = memberQuitMapper.selMemberQuitOfClubByPage(starPage, page.getPageSize(),
						president.getClubId());
				page.setSumDatas(memberQuitMapper.selCountOfClub(president.getClubId()));
				// 总页数
			} else {
				model.addAttribute("result", "请登录");
				if ("pr".equals(active)) {
					return selByPage("pr_member_quit.jsp", result, page, model);
				}
				return selByPage("member_quit.jsp", result, page, model);
			}
		} else {
			members = memberQuitMapper.selMemberQuitByPage(starPage, page.getPageSize());
			page.setSumDatas(memberQuitMapper.selMemberQuitCount());
			// 总页数
		}
		System.err.println("members:" + members);
		if (members != null && members.size() != 0) {
			page.setData(members);
			page.setTotalPage((page.getSumDatas() - 1) / page.getPageSize() + 1);
			System.err.println("page:" + page);
			if ("pr".equals(active)) {
				return selByPage("pr_member_quit.jsp", result, page, model);
			}
			return selByPage("member_quit.jsp", result, page, model);
		} else {
			System.err.println("page:" + page);
			if ("pr".equals(active)) {
				return selByPage("pr_member_quit.jsp", "无数据", page, model);
			}
			return selByPage("member_quit.jsp", "无数据", page, model);
		}
	}

	public String selMemberQuitById(int id, String active, Model model, HttpServletRequest request) {
		MemberQuit memberQuit = memberQuitMapper.selById(id);
		model.addAttribute("member", memberQuit);
		if ("pr".equals(active)) {
			return "pr_member_quit_sel.jsp";
		}
		return "member_quit_sel.jsp";
	}

	public String delMemberQuit(int id, String active, Model model, HttpServletRequest request) {
		int del = memberQuitMapper.delMemberQuitById(id);
		if (del == 1) {
			// 删除成功
			if ("pr".equals(active)) {
				return selMemberQuitByPage("删除成功", "pr", null, model, request);
			}
			return selMemberQuitByPage("删除成功", null, null, model, request);
		} else {
			if ("pr".equals(active)) {
				return selMemberQuitByPage("删除失败", "pr", null, model, request);
			}
			return selMemberQuitByPage("删除失败", null, null, model, request);
		}
	}
	// member_join----------------------------------------------------------------------------------------------------------------

	public String selMemberJoins(MemberJoin member, String active, Model model, HttpServletRequest request) {
		System.err.println("member:" + member);
		Page page = new Page(1, 10000, 1, 0, null);

		List<MemberJoin> members = new ArrayList<MemberJoin>();
		if ("pr".equals(active)) {
			ClubMember president = (ClubMember) request.getSession().getAttribute("president");
			if (president == null) {
				model.addAttribute("result", "请登录");
				return "pr_member_join.jsp";
			}
			members = memberJoinMapper.selMemberJoins("pr", member.getId(), member.getStudent().getId(),
					member.getStudent().getName(), president.getClubId(), null, member.getTime(), member.getStatus());
		} else {
			members = memberJoinMapper.selMemberJoins("normal", member.getId(), member.getStudent().getId(),
					member.getStudent().getName(), member.getClub().getId(), member.getClub().getName(),
					member.getTime(), member.getStatus());
		}
		if (members != null && members.size() != 0) {
			page.setData(members);
			page.setSumDatas(members.size());
			// 总页数
			model.addAttribute("result", "查询成功");
		} else {
			model.addAttribute("result", "没有符合条件的数据");
		}
		System.err.println("page:" + page);
		model.addAttribute("curPage", page);
		if ("pr".equals(active)) {
			return "pr_member_join.jsp";
		}
		return "member_join.jsp";
	}

	public String selMemberJoinByPage(String result, String active, Page page, Model model,
			HttpServletRequest request) {
		if (page == null) {
			page = new Page(1, 10, 0, 0, null);
		}
		if (page.getCurPage() == 0) {
			page.setCurPage(1);
		}
		if (page.getPageSize() == 0) {
			page.setPageSize(10);
		}

		int starPage = (page.getCurPage() - 1) * page.getPageSize();

		List<MemberJoin> members = new ArrayList<MemberJoin>();
		if ("pr".equals(active)) {
			ClubMember president = (ClubMember) request.getSession().getAttribute("president");
			if (president == null) {
				model.addAttribute("result", "请登录");
				return "pr_member_join.jsp";
			}
			members = memberJoinMapper.selMemberJoinofClubByPage(starPage, page.getPageSize(), president.getClubId());
			page.setSumDatas(memberJoinMapper.selAllCountsOfClub(president.getClubId()));
			// 总页数
		} else {
			members = memberJoinMapper.selMemberJoinByPage(starPage, page.getPageSize());
			page.setSumDatas(memberJoinMapper.selAllCounts());
			// 总页数
		}
		System.err.println("members:" + members);
		if (members != null && members.size() != 0) {
			page.setData(members);

			page.setTotalPage((page.getSumDatas() - 1) / page.getPageSize() + 1);
			System.err.println("page:" + page);
			if ("pr".equals(active)) {
				return selByPage("pr_member_join.jsp", result, page, model);
			}
			return selByPage("member_join.jsp", result, page, model);
		} else {
			System.err.println("page:" + page);
			if ("pr".equals(active)) {
				return selByPage("pr_member_join.jsp", "无数据", page, model);
			}
			return selByPage("member_join.jsp", "无数据", page, model);
		}
	}

	public String updMemberJoinStatus(MemberJoin memberJoin, String active, Model model, HttpServletRequest request) {
		System.err.println("memberJoin:" + memberJoin);
		System.err.println("active:" + active);
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		ClubMember president = (ClubMember) request.getSession().getAttribute("president");
		if (admin == null && president == null) {
			System.out.println("您还没有登录，请登录");
			model.addAttribute("result", "请登录");
			if ("pr".equals(active)) {
				return "pr_member_join.jsp";
			}
			return "member_join.jsp";
		}
		// 社员是否已经存在
		int ifSamStudenId = clubMemberMapper.selifStudentIdExit(memberJoin.getStudentId(), memberJoin.getClubId());
		if (ifSamStudenId > 0) {
			System.err.println("该社团成员已存在");
			if ("pr".equals(active)) {
				return selMemberJoinByPage("该社团成员已存在，审核失败", "pr", null, model, request);
			}
			return selMemberJoinByPage("该社团成员已存在，审核失败", null, null, model, request);
		}

		// 添加到club_member里面
		ClubMember clubMember = new ClubMember();
		Student student = new Student();
		student.setId(memberJoin.getStudentId());
		clubMember.setStudent(student);
		Club club = new Club();
		club.setId(memberJoin.getClubId());
		clubMember.setClub(club);
		clubMember.setStatus(0);
		int addClubMember = clubMemberMapper.insClubMember(clubMember);
		if (addClubMember != 1) {
			// 添加失败
			if ("pr".equals(active)) {
				return selMemberJoinByPage("审核失败", "pr", null, model, request);
			}
			return selMemberJoinByPage("审核失败", null, null, model, request);
		}
		// 添加成功
		int updStatus = memberJoinMapper.updateStatus(1, memberJoin.getId());
		if (updStatus != 1) {
			// 添加失败
			if ("pr".equals(active)) {
				return selMemberJoinByPage("审核失败", "pr", null, model, request);
			}
			return selMemberJoinByPage("审核失败", null, null, model, request);
		}
		// 修改成功,审核通过
		if ("pr".equals(active)) {
			// 给审核的用户发送申请成功的消息
			int add = receiveMapper.insRecieve(prModelMapper.selContentByStudentId(president.getStudentId(), 1),
					memberJoin.getStudentId(), 1, 3);
			if (add != 1) {
				return selMemberJoinByPage("审核失败", "pr", null, model, request);
			} else {
				return selMemberJoinByPage("审核成功", "pr", null, model, request);
			}

		} else {
			// 给学生发送申请失败通知
			AdminModel adminModel = adminModelMapper.selByAdminIdStatusType(admin.getId(), 1, 3);
			if (receiveMapper.insRecieve(adminModel.getContent(), memberJoin.getStudentId(), 1, 3) != 1) {
				// 添加失败
				return selMemberJoinByPage("拒绝成功", null, null, model, request);
			}

		}
		return selMemberJoinByPage("审核成功", null, null, model, request);
	}

	/**
	 * 拒绝通过
	 */
	public String updMemberJoinStatusR(MemberJoin memberJoin, String active, Model model, HttpServletRequest request) {
		System.err.println("memberJoin:" + memberJoin);

		Admin admin = (Admin) request.getSession().getAttribute("admin");
		ClubMember president = (ClubMember) request.getSession().getAttribute("president");
		if (admin == null && president == null) {
			System.out.println("您还没有登录，请登录");
			model.addAttribute("result", "请登录");
			if ("pr".equals(active)) {
				return "pr_member_join.jsp";
			}
			return "member_join.jsp";
		}
		//
		int updStatus = memberJoinMapper.updateStatus(memberJoin.getStatus(), memberJoin.getId());
		if (updStatus != 1) {
			//
			if ("pr".equals(active)) {
				return selMemberJoinByPage("拒绝失败", "pr", null, model, request);
			}
			return selMemberJoinByPage("拒绝失败", null, null, model, request);
		}
		// 修改成功,拒绝成功
		if ("pr".equals(active)) {
			// 给审核的用户发送申请成功的消息
			int add = receiveMapper.insRecieve(prModelMapper.selContentByStudentId(president.getStudentId(), 2),
					memberJoin.getStudentId(), 1, 3);
			if (add != 1) {
				return selMemberJoinByPage("拒绝失败", "pr", null, model, request);
			} else {
				return selMemberJoinByPage("拒绝成功", "pr", null, model, request);
			}

		} else {
			// 给学生发送申请失败通知
			AdminModel adminModel = adminModelMapper.selByAdminIdStatusType(admin.getId(), 2, 3);
			if (receiveMapper.insRecieve(adminModel.getContent(), memberJoin.getStudentId(), 1, 3) != 1) {
				// 添加失败
				return selMemberJoinByPage("拒绝成功", null, null, model, request);
			}
		}

		return selMemberJoinByPage("拒绝成功", null, null, model, request);
	}

	public String selMemberJoinById(int id, String active, Model model, HttpServletRequest request) {
		MemberJoin memberjoin = memberJoinMapper.selById(id);
		model.addAttribute("member", memberjoin);
		if ("pr".equals(active)) {
			return "pr_member_join_update.jsp";
		}
		return "member_join_update.jsp";
	}

	public String delMemberJoin(MemberJoin memberJoin, String active, Model model, HttpServletRequest request) {
		int del = memberJoinMapper.delMemberJoinById(memberJoin.getId());
		if (del == 1) {
			// 删除成功
			if ("pr".equals(active)) {
				return selMemberJoinByPage("删除成功", "pr", null, model, request);
			}
			return selMemberJoinByPage("删除成功", null, null, model, request);
		} else {
			if ("pr".equals(active)) {
				return selMemberJoinByPage("删除失败", "pr", null, model, request);
			}
			return selMemberJoinByPage("删除失败", null, null, model, request);
		}
	}

	// notice公告————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

	/**
	 * 动态查询
	 */
	public String selNotices(Notice notice, String action, Model model, HttpServletRequest request) {
		System.err.println("notice:" + notice);
		Page page = new Page(1, 10000, 1, 0, null);
		List<Notice> notices;
		if ("pr".equals(action)) {
			ClubMember president = (ClubMember) request.getSession().getAttribute("president");
			notices = noticeMapper.selNotices("pr", notice.getId(), president.getClubId(), null, notice.getTime(),
					notice.getTitle());
		} else {
			notices = noticeMapper.selNotices("normal", notice.getId(), notice.getClub().getId(),
					notice.getClub().getName(), notice.getTime(), notice.getTitle());
		}
		if (notices != null && notices.size() != 0) {
			page.setData(notices);
			page.setSumDatas(notices.size());
			// 总页数
			model.addAttribute("result", "查询成功");
		} else {
			model.addAttribute("result", "没有符合条件的数据");
		}
		System.err.println("page:" + page);
		model.addAttribute("curPage", page);
		if ("pr".equals(action)) {
			return "pr_notice.jsp";
		} else {
			return "notice.jsp";
		}
	}

	public String selNoticeByPage(String result, String action, Page page, Model model, HttpServletRequest request) {
		if (page == null) {
			page = new Page(1, 10, 0, 0, null);
		}
		if (page.getCurPage() == 0) {
			page.setCurPage(1);
		}
		if (page.getPageSize() == 0) {
			page.setPageSize(10);
		}

		int starPage = (page.getCurPage() - 1) * page.getPageSize();
		List<Notice> notices = new ArrayList<Notice>();
		if ("pr".equals(action)) {
			ClubMember president = (ClubMember) request.getSession().getAttribute("president");
			if (president != null) {
				notices = noticeMapper.selNoticeByPageofClub(starPage, page.getPageSize(), president.getClubId());
				page.setSumDatas(noticeMapper.selNoticeCountOfClub(president.getClubId()));
			}
		} else {
			notices = noticeMapper.selNoticeByPage(starPage, page.getPageSize());
			page.setSumDatas(noticeMapper.selNoticeCounts());
		}
		System.err.println("notices:" + notices);
		if (notices != null && notices.size() != 0) {
			page.setData(notices);
			// 总页数
			page.setTotalPage((page.getSumDatas() - 1) / page.getPageSize() + 1);
			System.err.println("page:" + page);
			if ("pr".equals(action)) {
				return selByPage("pr_notice.jsp", result, page, model);
			} else {
				return selByPage("notice.jsp", result, page, model);
			}

		} else {
			System.err.println("page:" + page);
			if ("pr".equals(action)) {
				return selByPage("pr_notice.jsp", "无数据", page, model);
			} else {
				return selByPage("notice.jsp", "无数据", page, model);
			}
		}
	}

	public String preUpdNotice(int id, String action, Model model, HttpServletRequest request) {
		Notice notice = noticeMapper.selById(id);
		model.addAttribute("notice", notice);
		model.addAttribute("clubs", clubMapper.selAllClubs());
		if ("pr".equals(action)) {
			return "pr_notice_update.jsp";
		} else {
			return "notice_update.jsp";
		}

	}

	public String updNoticePr(Notice notice, String action, Model model, HttpServletRequest request) {
		System.err.println("updNotice:" + notice);
		System.err.println("action:" + action);
		if (noticeMapper.selIfSameTitleExit(notice.getTitle(), notice.getId()) > 0) {
			// 已经存在该公告
			model.addAttribute("reslut", notice);
			model.addAttribute("clubs", clubMapper.selAllClubs());
			model.addAttribute("result", "该公告标题已经存在");
			return "pr_notice_update.jsp";
		}
		ClubMember president = (ClubMember) request.getSession().getAttribute("president");
		if (president != null) {
			notice.setClubId(president.getClubId());
		}
		int upd = noticeMapper.updNotice(notice);

		if (upd != 1) {
			model.addAttribute("reslut", notice);
			model.addAttribute("clubs", clubMapper.selAllClubs());
			model.addAttribute("result", "保存失败");
			return "pr_notice_update.jsp";
		}
		return selNoticeByPage("保存成功", "pr", null, model, request);
	}

	public String updNotice(Notice notice, String action, Model model, HttpServletRequest request) {
		System.err.println("updNotice:" + notice);
		System.err.println("action:" + action);
		if (noticeMapper.selIfSameTitleExit(notice.getTitle(), notice.getId()) > 0) {
			// 已经存在该公告
			model.addAttribute("reslut", notice);
			model.addAttribute("clubs", clubMapper.selAllClubs());
			model.addAttribute("result", "该公告标题已经存在");
			if ("pr".equals(action)) {
				return "pr_notice_update.jsp";
			} else {
				return "notice_update.jsp";
			}
		}

		if ("pr".equals(action)) {
			ClubMember president = (ClubMember) request.getSession().getAttribute("president");
			if (president != null) {
				notice.setClubId(president.getClubId());
			}
		}
		int upd = noticeMapper.updNotice(notice);

		if (upd != 1) {
			model.addAttribute("reslut", notice);
			model.addAttribute("clubs", clubMapper.selAllClubs());
			model.addAttribute("result", "保存失败");
			if ("pr".equals(action)) {
				return "pr_notice_update.jsp";
			} else {
				return "notice_update.jsp";
			}
		}
		if ("pr".equals(action)) {
			return selNoticeByPage("保存成功", "pr", null, model, request);
		} else {
			return selNoticeByPage("保存成功", null, null, model, request);
		}

	}

	public String preAddNotice(Notice notice, Model model, HttpServletRequest request) {
		List<Club> clubs = clubMapper.selAllClubs();
		model.addAttribute("clubs", clubs);
		return "notice_add.jsp";
	}

	public String addNoticePr(Notice notice, String action, Model model, HttpServletRequest request) {
		System.err.println("addNotice:" + notice);
		System.err.println("action:" + action);
		if (noticeMapper.selByTitle(notice.getTitle()) != null) {
			//
			model.addAttribute("notice", notice);
			model.addAttribute("result", "该公告已经存在");
			model.addAttribute("clubs", clubMapper.selAllClubs());
			return "pr_notice_add.jsp";
		}
		ClubMember president = (ClubMember) request.getSession().getAttribute("president");
		if (president != null) {
			notice.setClubId(president.getClubId());
		}
		int addNotice = noticeMapper.insNotice(notice);
		if (addNotice != 1) {
			model.addAttribute("notice", notice);
			model.addAttribute("clubs", clubMapper.selAllClubs());
			model.addAttribute("result", "添加失败");
			return "pr_notice_add.jsp";
		}
		return selNoticeByPage("保存成功", "pr", null, model, request);
	}

	public String addNotice(Notice notice, String action, Model model, HttpServletRequest request) {
		System.err.println("addNotice:" + notice);
		System.err.println("action:" + action);
		if (noticeMapper.selByTitle(notice.getTitle()) != null) {
			//
			model.addAttribute("notice", notice);
			model.addAttribute("result", "该公告已经存在");
			model.addAttribute("clubs", clubMapper.selAllClubs());
			return "notice_add.jsp";
		}
		int addNotice = noticeMapper.insNotice(notice);
		if (addNotice != 1) {
			model.addAttribute("notice", notice);
			model.addAttribute("clubs", clubMapper.selAllClubs());
			model.addAttribute("result", "添加失败");
			return "notice_add.jsp";
		}

		return selNoticeByPage("保存成功", null, null, model, request);
	}

	public String delNotice(int id, String action, Model model, HttpServletRequest request) {
		int delNotice = noticeMapper.delNoticeById(id);
		if (delNotice != 1) {
			if ("pr".equals(action)) {
				return selNoticeByPage("删除失败", action, null, model, request);
			} else {
				return selNoticeByPage("删除失败", null, null, model, request);
			}

		}
		if ("pr".equals(action)) {
			return selNoticeByPage("删除", action, null, model, request);
		} else {
			return selNoticeByPage("删除成功", null, null, model, request);
		}

	}

	// news新闻动态————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

	/**
	 * 动态查询
	 */

	public String selNews(News news, String action, Model model) {
		System.err.println("news:" + news);
		Page page = new Page(1, 10000, 1, 0, null);

		List<News> newss = newsMapper.selNews(news.getId(), news.getPeople(), news.getTime(), news.getTitle());
		if (newss != null && newss.size() != 0) {
			page.setData(newss);
			page.setSumDatas(newss.size());
			// 总页数
			model.addAttribute("result", "查询成功");
		} else {
			model.addAttribute("result", "没有符合条件的数据");
		}
		System.err.println("page:" + page);
		model.addAttribute("curPage", page);
		if ("pr".equals(action)) {
			return "pr_news.jsp";
		} else {
			return "news.jsp";
		}

	}

	public String selNewsByPage(String result, String action, Page page, Model model) {
		if (page == null) {
			page = new Page(1, 10, 0, 0, null);
		}
		if (page.getCurPage() == 0) {
			page.setCurPage(1);
		}
		if (page.getPageSize() == 0) {
			page.setPageSize(10);
		}

		int starPage = (page.getCurPage() - 1) * page.getPageSize();
		List<News> news = newsMapper.selNewsByPage(starPage, page.getPageSize());
		System.err.println("news:" + news);
		if (news != null && news.size() != 0) {
			page.setData(news);
			page.setSumDatas(newsMapper.selNewsCount());
			// 总页数
			page.setTotalPage((page.getSumDatas() - 1) / page.getPageSize() + 1);
			System.err.println("page:" + page);
			if ("pr".equals(action)) {
				return selByPage("pr_news.jsp", result, page, model);
			}
			return selByPage("news.jsp", result, page, model);
		} else {
			System.err.println("page:" + page);
			if ("pr".equals(action)) {
				return selByPage("pr_news.jsp", "无数据", page, model);
			}
			return selByPage("news.jsp", "无数据", page, model);
		}
	}

	public String preUpdNews(int id, String action, Model model) {
		model.addAttribute("news", newsMapper.selById(id));
		if ("pr".equals(action)) {
			return "pr_news_sel.jsp";
		} else {
			return "news_update.jsp";
		}

	}

	public String updNews(News news, Model model) {
		System.err.println("upnews:" + news);
		int ifSameTitle = newsMapper.selIfSameTitleNewsExit(news.getTitle(), news.getId());
		if (ifSameTitle != 1) {
			model.addAttribute("news", news);
			model.addAttribute("result", "保存失败");

			return "news_update.jsp";
		}

		int upd = newsMapper.updnews(news);
		if (upd != 1) {
			model.addAttribute("news", news);
			model.addAttribute("result", "保存失败");
			return "news_update.jsp";
		}
		return selNewsByPage("保存成功", null, null, model);
	}

	public String addNews(News news, Model model, HttpServletRequest request) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (admin == null) {
			model.addAttribute("result", "请先登录");
			return "news_add.jsp";
		}

		News news2 = newsMapper.selBytitle(news.getTitle());
		if (news2 != null) {
			model.addAttribute("news", news);
			model.addAttribute("result", "该新闻标题已经存在");
			return "news_add.jsp";
		}

		int add = newsMapper.insNews(news);
		if (add != 1) {
			model.addAttribute("news", news);
			model.addAttribute("result", "添加失败");
			return "news_add.jsp";
		}
		return selNewsByPage("添加成功", null, null, model);
	}

	public String delNews(int id, Model model) {
		int del = newsMapper.delNewsById(id);
		if (del != 1) {
			return selNewsByPage("删除失败", null, null, model);
		}
		return selNewsByPage("删除成功", null, null, model);
	}

	// event___________________________________________________________________________________________________________________________
	/**
	 * 动态查询社团活动
	 */
	public String selEvents(Event event, String action, Model model, HttpServletRequest request) {
		System.err.println("event:" + event);
		Page page = new Page(1, 10000, 1, 0, null);

		List<Event> events = new ArrayList<Event>();
		if ("pr".equals(action)) {
			ClubMember president = (ClubMember) request.getSession().getAttribute("president");
			if (president != null) {
				events = eventMapper.selEvents("pr", event.getId(), event.getName(), president.getClubId(), null,
						event.getStudent().getId(), event.getStudent().getName(), event.getPlace(),
						event.getStartTime(), event.getEndTime(), event.getAppTime(), event.getSubTime(),
						event.getStatus());

			}

		} else {
			events = eventMapper.selEvents("normal", event.getId(), event.getName(), event.getClub().getId(),
					event.getClub().getName(), event.getStudent().getId(), event.getStudent().getName(),
					event.getPlace(), event.getStartTime(), event.getEndTime(), event.getAppTime(), event.getSubTime(),
					event.getStatus());
		}
		if (events != null && events.size() != 0) {
			page.setData(events);
			page.setSumDatas(events.size());
			// 总页数
			model.addAttribute("result", "查询成功");
		} else {
			model.addAttribute("result", "没有符合条件的数据");
		}
		System.err.println("page:" + page);
		model.addAttribute("curPage", page);
		if ("pr".equals(action)) {
			return "pr_event.jsp";
		} else {
			return "event.jsp";
		}
	}

	public String selEventByPage(String result, String action, Page page, Model model, HttpServletRequest request) {
		if (page == null) {
			page = new Page(1, 10, 0, 0, null);
		}
		if (page.getCurPage() == 0) {
			page.setCurPage(1);
		}
		if (page.getPageSize() == 0) {
			page.setPageSize(10);
		}

		int starPage = (page.getCurPage() - 1) * page.getPageSize();
		List<Event> events = new ArrayList<Event>();
		if ("pr".equals(action)) {
			ClubMember president = (ClubMember) request.getSession().getAttribute("president");
			if (president != null) {
				events = eventMapper.selAllEventByPageOfClub(starPage, page.getPageSize(), president.getClubId());
				page.setSumDatas(eventMapper.selEventCountOfClub(president.getClubId()));
			}
		} else {
			events = eventMapper.selAllEventByPage(starPage, page.getPageSize());
			page.setSumDatas(eventMapper.selEventCount());
			// 总页数
		}

		System.err.println("events:" + events);
		if (events != null && events.size() != 0) {
			page.setData(events);
			page.setTotalPage((page.getSumDatas() - 1) / page.getPageSize() + 1);
			System.err.println("page:" + page);
			if ("pr".equals(action)) {
				return selByPage("pr_event.jsp", result, page, model);
			} else {
				return selByPage("event.jsp", result, page, model);
			}

		} else {
			System.err.println("page:" + page);
			if ("pr".equals(action)) {
				return selByPage("pr_event.jsp", "无数据", page, model);
			} else {
				return selByPage("event.jsp", "无数据", page, model);
			}

		}
	}

	public String updEventStatusR(Event event, Model model, HttpServletRequest request) {
		System.err.println("event:" + event);
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (admin == null) {
			return "club_apply.jsp";
		}

		int updStatus = eventMapper.updEventStatus(event.getStatus(), event.getId());
		if (updStatus != 1) {
			//
			return selEventByPage("拒绝失败", null, null, model, request);
		}
		// 修改成功,拒绝成功
		// 给社长发送消息
		// 给社长发送申请失败通知
		AdminModel adminModel = adminModelMapper.selByAdminIdStatusType(admin.getId(), 2, 2);
		if (receiveMapper.insRecieve(adminModel.getContent(), event.getStudent().getId(), 1, 2) != 1) {
			// 添加失败
			return selEventByPage("拒绝失败", null, null, model, request);
		}

		return selEventByPage("拒绝成功", null, null, model, request);
	}

	//审核通过
	public String updEventStatus(Event event, Model model, HttpServletRequest request) {
		System.err.println("event:" + event);

		// 社团是否已经存在

		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (admin == null) {
			System.out.println("您还没有登录，请登录");
			model.addAttribute("result", "请登录");
			return "club_apply.jsp";
		}
		System.err.println("event:" + event);
		int updStatus = eventMapper.updEventStatus(event.getStatus(), event.getId());
		if (updStatus != 1) {
			//
			return selEventByPage("审核通过失败", null, null, model, request);
		}
		//
		// 给社长发送消息
		// 给社长发送申请失败通知
		AdminModel adminModel = adminModelMapper.selByAdminIdStatusType(admin.getId(), 1, 2);
		if (receiveMapper.insRecieve(adminModel.getContent(), event.getStudent().getId(), 1, 2) != 1) {
			// 添加失败
			return selEventByPage("审核失败", null, null, model, request);
		}
		return selEventByPage("审核通过成功", null, null, model, request);
	}

	public String delEvent(Event event, String action, Model model, HttpServletRequest request) {
		System.err.println("del event:" + event);
		int del = eventMapper.delEventById(event.getId());
		if (del != 1) {
			if ("pr".equals(action)) {
				return selEventByPage("删除失败", "pr", null, model, request);
			} else {
				return selEventByPage("删除失败", null, null, model, request);
			}
		}
		if ("pr".equals(action)) {
			return selEventByPage("删除成功", "pr", null, model, request);
		} else {
			return selEventByPage("删除成功", null, null, model, request);
		}

	}

	public String preUpdEvent(int id, String action, Model model, HttpServletRequest request) {
		System.err.println("id:" + id);
		model.addAttribute("event", eventMapper.selById(id));
		model.addAttribute("clubs", clubMapper.selAllClubs());
		if ("pr".equals(action)) {
			return "pr_event_update.jsp";
		} else {
			return "event_update.jsp";
		}
	}

	public String updEvent(Event event, Model model, HttpServletRequest request) {
		System.err.println("updEvent:" + event);
		// 学号是否存在
		Student student = studentMapper.selById(event.getStudent().getId());
		if (student == null) {
			model.addAttribute("clubs", clubMapper.selAllClubs());
			model.addAttribute("event", event);
			model.addAttribute("result", "该学号不存在");
			return "event_update.jsp";

		}
		// 学号存在，
		// 活动名称是否已经存在
		if (eventMapper.selIfSameNameExit(event.getName(), event.getId()) > 0) {
			// 已经存在
			model.addAttribute("clubs", clubMapper.selAllClubs());
			model.addAttribute("event", event);
			model.addAttribute("result", "该活动名已存在");
			return "event_update.jsp";
		}
		// 进行修改操作
		int upd = eventMapper.updEvent(event);
		if (upd != 1) {
			// 编辑失败
			model.addAttribute("clubs", clubMapper.selAllClubs());
			model.addAttribute("event", event);
			model.addAttribute("reslut", "保存失败");
			return "event_update.jsp";
		}
		return selEventByPage("保存成功", null, null, model, request);

	}

	public String updEventPr(Event event, Model model, HttpServletRequest request) {
		System.err.println("updEvent:" + event);
		// 学号是否存在
		Student student = studentMapper.selById(event.getStudent().getId());
		if (student == null) {
			model.addAttribute("clubs", clubMapper.selAllClubs());
			model.addAttribute("event", event);
			model.addAttribute("result", "该学号不存在");
			return "pr_event_update.jsp";

		}
		// 学号存在，
		// 活动名称是否已经存在
		if (eventMapper.selIfSameNameExit(event.getName(), event.getId()) > 0) {
			// 已经存在
			model.addAttribute("clubs", clubMapper.selAllClubs());
			model.addAttribute("event", event);
			model.addAttribute("result", "该活动名已存在");
			return "pr_event_update.jsp";
		}
		// 进行修改操作
		int upd = eventMapper.updEvent(event);
		if (upd != 1) {
			// 编辑失败
			model.addAttribute("clubs", clubMapper.selAllClubs());
			model.addAttribute("event", event);
			model.addAttribute("reslut", "保存失败");
			return "pr_event_update.jsp";
		}
		return selEventByPage("保存成功", "pr", null, model, request);

	}

	public String addEvent(Event event, Model model, HttpServletRequest request) {
		System.err.println("addEvent:" + event);

		Student student = studentMapper.selById(event.getStudent().getId());
		if (student == null) {
			model.addAttribute("clubs", clubMapper.selAllClubs());
			model.addAttribute("event", event);
			model.addAttribute("result", "该负责人的学号不存在");
			return "event_add.jsp";
		}

		Event event2 = eventMapper.selByName(event.getName());
		if (event2 == null) {
			int add = eventMapper.insEvent(event);
			if (add == 1) {
				return selEventByPage("添加申请成功", null, null, model, request);
			} else {
				model.addAttribute("clubs", clubMapper.selAllClubs());
				model.addAttribute("event", event);
				model.addAttribute("result", "添加申请失败");
				return "event_add.jsp";
			}
		} else {
			model.addAttribute("clubs", clubMapper.selAllClubs());
			model.addAttribute("event", event);
			model.addAttribute("result", "该活动名已存在");
			return "event_add.jsp";
		}
	}

	public String addEventPr(Event event, Model model, HttpServletRequest request) {
		System.err.println("addEvent:" + event);

		Student student = studentMapper.selById(event.getStudent().getId());
		if (student == null) {
			model.addAttribute("clubs", clubMapper.selAllClubs());
			model.addAttribute("event", event);
			model.addAttribute("result", "该负责人的学号不存在");
			return "pr_event_add.jsp";
		}

		Event event2 = eventMapper.selByName(event.getName());
		if (event2 == null) {
			int add = eventMapper.insEvent(event);
			if (add == 1) {
				return selEventByPage("添加成功", "pr", null, model, request);
			} else {
				model.addAttribute("clubs", clubMapper.selAllClubs());
				model.addAttribute("event", event);
				model.addAttribute("result", "添加失败");
				return "pr_event_add.jsp";
			}
		} else {
			model.addAttribute("clubs", clubMapper.selAllClubs());
			model.addAttribute("event", event);
			model.addAttribute("result", "该活动名已存在");
			return "pr_event_add.jsp";
		}

	}

	public String preAddEvent(Event event, Model model, HttpServletRequest request) {
		model.addAttribute("clubs", clubMapper.selAllClubs());
		return "event_add.jsp";
	}

	// 待审核——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————-
	public String selAppClubs(ClubApply clubApply, Model model) {
		System.err.println("clubApply:" + clubApply);
		Page page = new Page(1, 10000, 1, 0, null);

		List<ClubApply> clubApplies = clubApplyMapper.selAppClubs(clubApply.getId(), clubApply.getStudent().getId(),
				clubApply.getStudent().getName(), clubApply.getClubType().getName(), clubApply.getAdminId(),
				clubApply.getName());
		if (clubApplies != null && clubApplies.size() != 0) {
			page.setData(clubApplies);
			page.setSumDatas(clubApplies.size());
			// 总页数
			model.addAttribute("result", "查询成功");
		} else {
			model.addAttribute("result", "没有符合条件的数据");
		}
		System.err.println("page:" + page);
		model.addAttribute("curPage", page);
		return "app_club.jsp";
	}

	public String selAppClubByPage(String result, Page page, Model model) {
		if (page == null) {
			page = new Page(1, 10, 0, 0, null);
		}
		if (page.getCurPage() == 0) {
			page.setCurPage(1);
		}
		if (page.getPageSize() == 0) {
			page.setPageSize(10);
		}

		int starPage = (page.getCurPage() - 1) * page.getPageSize();
		List<ClubApply> clubApplies = clubApplyMapper.selAppClubByPage(starPage, page.getPageSize(), 2);
		System.err.println("clubApplies:" + clubApplies);
		if (clubApplies != null && clubApplies.size() != 0) {
			page.setData(clubApplies);
			page.setSumDatas(clubApplyMapper.selAppClubCount(2));
			// 总页数
			page.setTotalPage((page.getSumDatas() - 1) / page.getPageSize() + 1);
			return selByPage("app_club.jsp", result, page, model);
		} else {
			return selByPage("app_club.jsp", "无数据", page, model);
		}
	}

	/**
	 * 修改状态为通过
	 */
	public String updAppClubStatus(ClubApply clubApply, Model model, HttpServletRequest request) {
		System.err.println("clubApply:" + clubApply);
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (admin == null) {
			System.out.println("您还没有登录，请登录");
			model.addAttribute("result", "请登录");
			return "app_club.jsp";
		}
		// 社团是否已经存在
		Club clubSame = clubMapper.selByName(clubApply.getName());
		if (clubSame != null) {
			System.err.println("该社团名已存在");
			return selAppClubByPage("该社团名已存在，拒绝通过，审核失败", null, model);
		}

		// 添加到club里面
		Club club = new Club();
		club.setStudentId(clubApply.getStudentId());
		club.setClubTypeId(clubApply.getClubTypeId());
		club.setName(clubApply.getName());
		club.setStatus(0);
		System.err.println("add club:" + club);
		int addClub = clubMapper.insClub(club);
		if (addClub != 1) {
			// 添加失败
			return selAppClubByPage("审核失败", null, model);
		}
		// 社团添加成功之后

		// 给社长发送已经成功通知
		AdminModel adminModel = adminModelMapper.selByAdminIdStatusType(admin.getId(), 1, 1);
		if (receiveMapper.insRecieve(adminModel.getContent(), clubApply.getStudentId(), 1, 1) != 1) {
			// 添加失败
			return selAppClubByPage("审核失败", null, model);
		}
		// 添加该申请人为会长
		// 添加到club_member里面
		ClubMember clubMember = new ClubMember();
		Student student = new Student();
		student.setId(clubApply.getStudentId());
		clubMember.setStudent(student);
		Club club2 = clubMapper.selByName(clubApply.getName());
		clubMember.setClub(club2);
		clubMember.setStatus(1);
		int addClubMember = clubMemberMapper.insClubMember(clubMember);
		if (addClubMember != 1) {
			// 添加失败
			return selAppClubByPage("审核失败", null, model);
		}
		// 成员添加成功

		int updStatus = clubApplyMapper.updStatus(1, clubApply.getId(), admin.getId());
		if (updStatus != 1) {
			// 添加失败
			return selClubApplyByPage("审核失败", null, model);
		}
		// 修改成功,审核通过
		return selAppClubByPage("审核成功", null, model);
	}

	/**
	 * 修改状态为拒绝
	 */
	public String updAppClubStatusR(ClubApply clubApply, Model model, HttpServletRequest request) {
		System.err.println("AppClub clubApply:" + clubApply);

		// 社团是否已经存在

		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (admin == null) {
			System.out.println("您还没有登录，请登录");
			model.addAttribute("result", "请登录");
			return "app_club.jsp";
		}
		//
		int updStatus = clubApplyMapper.updStatus(0, clubApply.getId(), admin.getId());
		if (updStatus != 1) {
			//
			return selAppClubByPage("拒绝失败", null, model);
		}
		// 给社长发送申请失败通知
		AdminModel adminModel = adminModelMapper.selByAdminIdStatusType(admin.getId(), 2, 1);
		if (receiveMapper.insRecieve(adminModel.getContent(), clubApply.getStudentId(), 1, 1) != 1) {
			// 添加失败
			return selAppClubByPage("审核失败", null, model);
		}
		// 修改成功,拒绝成功
		return selAppClubByPage("拒绝成功", null, model);
	}

	/**
	 * 查看详情
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	public String selAppClubById(int id, Model model) {
		ClubApply clubApply = clubApplyMapper.selById(id);
		if (clubApply != null) {
			model.addAttribute("clubApply", clubApply);
			return "app_club_sel.jsp";
		} else {
			model.addAttribute("result", "查看失败");
			return "app_club.jsp";
		}
	}

	/// App_member————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

	public String selAppMembers(MemberJoin member, Model model) {
		System.err.println("member:" + member);
		Page page = new Page(1, 10000, 1, 0, null);

		List<MemberJoin> members = memberJoinMapper.selAppMembers(member.getId(), member.getStudent().getId(),
				member.getStudent().getName(), member.getClub().getId(), member.getClub().getName(), member.getTime());
		if (members != null && members.size() != 0) {
			page.setData(members);
			page.setSumDatas(members.size());
			// 总页数
			model.addAttribute("result", "查询成功");
		} else {
			model.addAttribute("result", "没有符合条件的数据");
		}
		System.err.println("page:" + page);
		model.addAttribute("curPage", page);
		return "app_member.jsp";
	}

	public String selAppMemberByPage(String result, Page page, Model model) {
		if (page == null) {
			page = new Page(1, 10, 0, 0, null);
		}
		if (page.getCurPage() == 0) {
			page.setCurPage(1);
		}
		if (page.getPageSize() == 0) {
			page.setPageSize(10);
		}

		int starPage = (page.getCurPage() - 1) * page.getPageSize();
		List<MemberJoin> members = memberJoinMapper.selAppMemberByPage(starPage, page.getPageSize());
		System.err.println("members:" + members);
		if (members != null && members.size() != 0) {
			page.setData(members);
			page.setSumDatas(memberJoinMapper.selAppMemberCounts());
			// 总页数
			page.setTotalPage((page.getSumDatas() - 1) / page.getPageSize() + 1);
			System.err.println("page:" + page);
			return selByPage("app_member.jsp", result, page, model);
		} else {
			System.err.println("page:" + page);
			return selByPage("app_member.jsp", "无数据", page, model);
		}
	}

	public String updAppMemberStatus(MemberJoin memberJoin, Model model, HttpServletRequest request) {
		System.err.println("memberJoin:" + memberJoin);
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		Student student = (Student) request.getSession().getAttribute("student");
		System.err.println("admin:" + admin);
		System.err.println("student:" + student);
		if (admin == null && student == null) {
			System.out.println("您还没有登录，请登录");
			model.addAttribute("result", "请登录");
			return "app_member.jsp";
		}
		// 社员是否已经存在
		int ifSamStudenId = clubMemberMapper.selifStudentIdExit(memberJoin.getStudentId(), memberJoin.getClubId());
		if (ifSamStudenId > 0) {
			System.err.println("该社团成员已存在");
			return selAppMemberByPage("该社团成员已经存在，通过失败", new Page(), model);
		}

		ClubMember clubMember = new ClubMember();
		Student student1 = new Student();
		student1.setId(memberJoin.getStudentId());
		clubMember.setStudent(student1);
		Club club = new Club();
		club.setId(memberJoin.getClubId());
		clubMember.setClub(club);
		clubMember.setStatus(0);
		int addClubMember = clubMemberMapper.insClubMember(clubMember);
		if (addClubMember != 1) {
			// 添加失败
			return selAppMemberByPage("审核失败", null, model);
		}
		// 添加成功
		int updStatus = memberJoinMapper.updateStatus(1, memberJoin.getId());
		if (updStatus != 1) {
			// 添加失败
			return selAppMemberByPage("审核失败", null, model);
		}
		// 给社长发送申请成功通知
		AdminModel adminModel = adminModelMapper.selByAdminIdStatusType(admin.getId(), 1, 3);
		if (receiveMapper.insRecieve(adminModel.getContent(), memberJoin.getStudentId(), 1, 3) != 1) {
			// 添加失败
			return selAppMemberByPage("拒绝失败", null, model);
		}
		// 修改成功,审核通过
		return selAppMemberByPage("审核成功", null, model);
	}

	/**
	 * 拒绝通过
	 */
	public String updAppMemberStatusR(MemberJoin memberJoin, Model model, HttpServletRequest request) {
		System.err.println("memberJoin:" + memberJoin);

		// 社团是否已经存在

		Admin admin = (Admin) request.getSession().getAttribute("admin");
		Student student = (Student) request.getSession().getAttribute("student");
		System.err.println("admin:" + admin);
		System.err.println("student:" + student);
		if (admin == null && student == null) {
			System.out.println("您还没有登录，请登录");
			model.addAttribute("result", "请登录");
			return "app_member.jsp";
		}
		// 添加到club里面
		int updStatus = memberJoinMapper.updateStatus(memberJoin.getStatus(), memberJoin.getId());
		if (updStatus != 1) {
			//
			return selAppMemberByPage("拒绝失败", null, model);
		}
		// 给社长发送申请失败通知
		AdminModel adminModel = adminModelMapper.selByAdminIdStatusType(admin.getId(), 2, 3);
		if (receiveMapper.insRecieve(adminModel.getContent(), memberJoin.getStudentId(), 1, 3) != 1) {
			// 添加失败
			return selAppMemberByPage("拒绝失败", null, model);
		}

		// 修改成功,拒绝成功
		return selAppMemberByPage("拒绝成功", null, model);
	}

	public String selAppMemberById(int id, Model model) {
		MemberJoin memberjoin = memberJoinMapper.selById(id);
		model.addAttribute("member", memberjoin);
		return "app_member_sel.jsp";
	}

	/// appevent待审核的event————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

	/**
	 * 动态查询社团活动
	 */
	public String selAppEvents(Event event, Model model) {
		System.err.println("event:" + event);
		Page page = new Page(1, 10000, 1, 0, null);

		List<Event> events = eventMapper.selAppEvents(event.getId(), event.getName(), event.getClub().getId(),
				event.getClub().getName(), event.getStudent().getId(), event.getStudent().getName(), event.getPlace(),
				event.getStartTime(), event.getEndTime(), event.getAppTime(), event.getSubTime());
		if (events != null && events.size() != 0) {
			page.setData(events);
			page.setSumDatas(events.size());
			// 总页数
			model.addAttribute("result", "查询成功");
		} else {
			model.addAttribute("result", "没有符合条件的数据");
		}
		System.err.println("page:" + page);
		model.addAttribute("curPage", page);
		return "app_event.jsp";
	}

	public String selAppEventByPage(String result, Page page, Model model) {
		if (page == null) {
			page = new Page(1, 10, 0, 0, null);
		}
		if (page.getCurPage() == 0) {
			page.setCurPage(1);
		}
		if (page.getPageSize() == 0) {
			page.setPageSize(10);
		}

		int starPage = (page.getCurPage() - 1) * page.getPageSize();
		List<Event> events = eventMapper.selAppEventByPage(starPage, page.getPageSize(), 2);
		System.err.println("events:" + events);
		if (events != null && events.size() != 0) {
			page.setData(events);
			page.setSumDatas(eventMapper.selAppEventCounts(2));
			// 总页数
			page.setTotalPage((page.getSumDatas() - 1) / page.getPageSize() + 1);
			System.err.println("page:" + page);
			return selByPage("app_event.jsp", result, page, model);
		} else {
			System.err.println("page:" + page);
			return selByPage("app_event.jsp", "无数据", page, model);
		}
	}

	public String updAppEventStatusR(Event event, Model model, HttpServletRequest request) {
		System.err.println("updAppEventStatusR:" + event);
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (admin == null) {
			System.out.println("您还没有登录，请登录");
			model.addAttribute("result", "请登录");
			return "app_event.jsp";
		}

		int updStatus = eventMapper.updEventStatus(0, event.getId());
		if (updStatus != 1) {
			//
			return selAppEventByPage("拒绝失败", null, model);
		}
		// 给社长发送消息
		// 给社长发送申请失败通知
		AdminModel adminModel = adminModelMapper.selByAdminIdStatusType(admin.getId(), 2, 2);
		if (receiveMapper.insRecieve(adminModel.getContent(), event.getStudent().getId(), 1, 2) != 1) {
			// 添加失败
			return selEventByPage("拒绝失败", null, null, model, request);
		}
		// 修改成功,拒绝成功
		return selAppEventByPage("拒绝成功", null, model);
	}

	public String updAppEventStatus(Event event, Model model, HttpServletRequest request) {
		System.err.println("updAppEventStatus:" + event);

		// 社团是否已经存在

		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (admin == null) {
			System.out.println("您还没有登录，请登录");
			model.addAttribute("result", "请登录");
			return "app_event.jsp";
		}
		System.err.println("event:" + event);
		int updStatus = eventMapper.updEventStatus(1, event.getId());
		if (updStatus != 1) {
			//
			return selAppEventByPage("审核通过失败", null, model);
		}
		//
		// 给社长发送申请失败通知
		AdminModel adminModel = adminModelMapper.selByAdminIdStatusType(admin.getId(), 1, 2);
		if (receiveMapper.insRecieve(adminModel.getContent(), event.getStudent().getId(), 1, 2) != 1) {
			// 添加失败
			return selEventByPage("拒绝失败", null, null, model, request);
		}
		return selAppEventByPage("审核通过成功", null, model);
	}

	public String selAppEventById(Event event, Model model) {
		Event event2 = eventMapper.selById(event.getId());
		model.addAttribute("event", event2);
		return "app_event_sel.jsp";
	}

	// 在线人数————————————————————————————————————————————————————————————————————————————————————————————————————————————————
	public String inline(Model model) {
		int studentInline = studentMapper.selStuInlineOrNotCount(1);
		model.addAttribute("inline", studentInline);
		return "main_right.jsp";
	}

	/**
	 * 社团管理员
	 */
	public String prLogin(ClubMember president, Model model, HttpServletRequest request) {
		System.err.println("login president:" + president);
		// 是不是会长
		ClubMember prsdtBy = clubMemberMapper.selPrsdtBySidStatus(president.getStudent().getId(), 1);
		if (prsdtBy == null) {
			// 不存在
			model.addAttribute("result", "该会长账号不存在");
			model.addAttribute("president", president);
			return "prsdt_login.jsp";
		}
		// 存在
		// 该学号密码是否正确
		Student student = studentMapper.selByStudent(president.getStudent().getId(),
				president.getStudent().getPassword());
		HttpSession session = request.getSession();
		if (student != null) {
			// 数据库存在该信息
			// 更新在线状态
			int upd = studentMapper.updStuOnlineStatus(president.getStudent().getId(), 1);
			if (upd == 1) {
				// 成功
				session.setAttribute("president", prsdtBy);
				// session.setAttribute("student", studentMapper.selById(student.getId()));
				System.err.println("president:" + prsdtBy);
				return "redirect:pr_main.jsp";
			} else {
				model.addAttribute("president", president);
				model.addAttribute("result", "账号或密码错误");
				return "prsdt_login.jsp";
			}
		} else {
			// 数据库不存在该信息
			model.addAttribute("president", president);
			model.addAttribute("result", "登录失败");
			return "prsdt_login.jsp";
		}
	}

	public String prLogout(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		ClubMember president = (ClubMember) session.getAttribute("president");
		// 更新在线状态
		if (president != null) {
			int upd = studentMapper.updStuOnlineStatus(president.getStudent().getId(), 0);
			if (upd != 1) {
				// 失败
				model.addAttribute("result", "注销失败");
				return "redirect:pr_main.jsp";
			}
		}
		if (president != null) {
			session.removeAttribute("president");
		}
		if (session.getAttribute("student") != null) {
			session.removeAttribute("student");
		}
		model.addAttribute("result", "注销成功");
		return "redirect:prsdt_login.jsp";
	}

	public String updPrStudent(Student student, Model model, HttpServletRequest request) {
		// 邮箱是否已经存在
		int sameEmail = studentMapper.selIfSameEmail(student.getEmail(), student.getId());
		if (sameEmail > 0) {
			// 邮箱已经存在
			model.addAttribute("result", "邮箱已经存在");
			return "pr_student.jsp";
		}
		// 手机号是否已经存在
		int samePhone = studentMapper.selIfSamePhone(student.getPhone(), student.getId());
		if (samePhone > 0) {
			// 手机号已经存在
			model.addAttribute("result", "手机号已经存在");
			return "pr_student.jsp";
		}
		// 修改用户信息
		int updPreStu = studentMapper.updStudent(student);
		if (updPreStu != 1) {
			// 更新用户信息失败
			model.addAttribute("result", "保存失败");
			return "pr_student.jsp";
		}
		if (studentMapper.updStudentPwd(student.getPassword(), student.getId()) != 1) {
			// 更新用户信息失败
			model.addAttribute("result", "密码更新失败");
			return "pr_student.jsp";
		}
		ClubMember president = clubMemberMapper.selPrsdtBySidStatus(student.getId(), 1);
		request.getSession().setAttribute("president", president);
		model.addAttribute("result", "保存成功");
		return "pr_student.jsp";
	}

	/**
	 * 查询某个学生加入社团的历史
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	public String joinClubHistory(Model model, HttpServletRequest request) {
		ClubMember president = (ClubMember) request.getSession().getAttribute("president");
		System.err.println("president:" + president);
		if (president == null) {
			// 还没有登陆
			model.addAttribute("result", "您还没有登录");
			return "pr_center_club_join.jsp";
		}

		List<MemberJoin> clubs = memberJoinMapper.selMemberJoinByStudentId(president.getStudentId());
		if (clubs == null) {
			model.addAttribute("result", "暂无申请记录");
			return "pr_center_club_join.jsp";
		}
		model.addAttribute("clubs", clubs);
		return "pr_center_club_join.jsp";
	}

	/**
	 * 撤销加入该社团
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	public String cancelJoin(MemberJoin memberJoin, Model model, HttpServletRequest request) {
		int del = memberJoinMapper.delMemberJoinById(memberJoin.getId());
		if (del != 1) {
			model.addAttribute("result", "撤销失败");
		}
		model.addAttribute("result", "撤销成功");
		return joinClubHistory(model, request);
	}

	/**
	 * 进入个人中心
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	public String goStuCenterLeftOfMessage(Model model, HttpServletRequest request) {
		Student student = (Student) request.getSession().getAttribute("student");
		if (student == null) {
			model.addAttribute("result", "请先登录");
			return "pr_main_left.jsp";
		}
		int count = receiveMapper.selCountByStudentIdStatus(student.getId(), 1);
		System.err.println("未读信息：" + count);
		model.addAttribute("count", count);
		return "pr_main_left.jsp";
	}

	/**
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	public String selMessage(Model model, HttpServletRequest request) {
		// Student student = (Student) request.getSession().getAttribute("student");
		ClubMember president = (ClubMember) request.getSession().getAttribute("president");
		if (president == null) {
			model.addAttribute("result", "请先登录");
			System.err.println("请先登录");
			return "pr_message.jsp";
		}
		// 未读信息
		List<Receive> noMessages = receiveMapper.selByStudentIdStatus(president.getStudentId(), 1);
		// 已读信息
		List<Receive> yesMessage = receiveMapper.selByStudentIdStatus(president.getStudentId(), 0);
		model.addAttribute("nom", noMessages);
		model.addAttribute("yesm", yesMessage);
		// 修改该学生应该接受的信息状态为0已接收
		// 本来未读信息条数
		int count = receiveMapper.selCountByStudentIdStatus(president.getStudentId(), 1);
		model.addAttribute("count", count);
		if (count != 0) {
			int update = receiveMapper.updRecieve(president.getStudentId(), 1, 0);
			if (update != count) {
				return "pr_message.jsp";
			}
		}
		System.err.println("pr_message.jsp");
		return "pr_message.jsp";
	}

	public String selModelRejectPr(Model model, HttpServletRequest request) {
		// Student student = (Student) request.getSession().getAttribute("student");
		ClubMember president = (ClubMember) request.getSession().getAttribute("president");
		if (president == null) {
			model.addAttribute("result", "请先登录");
			System.err.println("请先登录");
			return "pr_reject.jsp";
		}
		PrModel prmodel = prModelMapper.selByStudentIdStatus(president.getStudentId(), 2);
		model.addAttribute("prmodel", prmodel);
		return "pr_reject.jsp";
	}

	/**
	 * 修改拒绝模板
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	public String updRejectModelPr(PrModel prModel, Model model, HttpServletRequest request) {
		System.err.println("upd prmodel:" + prModel);

		// Student student = (Student) request.getSession().getAttribute("student");
		ClubMember president = (ClubMember) request.getSession().getAttribute("president");
		if (president == null) {
			model.addAttribute("result", "请先登录");
			System.err.println("请先登录");
			return "pr_reject.jsp";
		}
		prModel.setStudent(president.getStudent());
		prModel.setStudentId(president.getStudentId());
		prModel.setStatus(2);
		int upd = prModelMapper.updPrModel(prModel);
		if (upd != 1) {
			model.addAttribute("result", "设置失败");
			model.addAttribute("prmodel", prModel);
		} else {
			model.addAttribute("result", "设置成功");
			model.addAttribute("prmodel", prModelMapper.selByStudentIdStatus(president.getStudentId(), 2));
		}

		return selModelRejectPr(model, request);
	}

	/**
	 * 修改通过模板
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	public String updPassModelPr(PrModel prModel, Model model, HttpServletRequest request) {
		System.err.println("updPass prmodel:" + prModel);

		// Student student = (Student) request.getSession().getAttribute("student");
		ClubMember president = (ClubMember) request.getSession().getAttribute("president");
		if (president == null) {
			model.addAttribute("result", "请先登录");
			System.err.println("请先登录");
			return "pr_pass.jsp";
		}
		prModel.setStudent(president.getStudent());
		prModel.setStudentId(president.getStudentId());
		prModel.setStatus(1);
		int upd = prModelMapper.updPrModel(prModel);
		if (upd != 1) {
			model.addAttribute("result", "设置失败");
			model.addAttribute("prmodel", prModel);
		} else {
			model.addAttribute("result", "设置成功");
			model.addAttribute("prmodel", prModelMapper.selByStudentIdStatus(president.getStudentId(), 1));
		}

		return selModelPassPr(model, request);

	}

	public String selModelPassPr(Model model, HttpServletRequest request) {
		// Student student = (Student) request.getSession().getAttribute("student");
		ClubMember president = (ClubMember) request.getSession().getAttribute("president");
		if (president == null) {
			model.addAttribute("result", "请先登录");
			System.err.println("请先登录");
			return "pr_pass.jsp";
		}
		PrModel prmodel = prModelMapper.selByStudentIdStatus(president.getStudentId(), 1);
		model.addAttribute("prmonjdel", prmodel);
		return "pr_pass.jsp";
	}

	public String selModelReject(Model model, HttpServletRequest request) {

		return null;
	}

	public String selModelPass(Model model, HttpServletRequest request) {

		return null;
	}

	/**
	 * 某个指定社团的图片---------------------------------------------
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	/*public String selClubImage(Model model, HttpServletRequest request) {
		ClubMember president = (ClubMember) request.getSession().getAttribute("president");
		if (president == null) {
			return "pr_image.jsp";
		}
		List<Image> images = imageMapper.selByClubId(president.getClubId());
		if (images == null || images.size() == 0) {
			model.addAttribute("result", "暂无社团照片，请添加");
			return "pr_image.jsp";
		}
		model.addAttribute("images", images);
		return "pr_image.jsp";
	}*/
	public String selClubImage(Model model, HttpServletRequest request) {
		ClubMember president = (ClubMember) request.getSession().getAttribute("president");
		if (president == null) {
			return "pr_image.jsp";
		}
		List<Image> images = imageMapper.selByClubId(president.getClubId());
		if (images == null || images.size() == 0) {
			model.addAttribute("result", "暂无社团照片，请添加");
			return "pr_image.jsp";
		}
		model.addAttribute("images", images);
		return "pr_image.jsp";
	}

	public String addClubImage(Image image, Model model, HttpServletRequest request) {
		ClubMember president = (ClubMember) request.getSession().getAttribute("president");
		if (president == null) {
			return "pr_image.jsp";
		}
		if (imageMapper.insImage(image.getName(), "img/" + image.getUrl(), president.getClubId()) != 1) {
			// 添加失败
			model.addAttribute("result", "添加失败");
			return selClubImage(model, request);
		}
		model.addAttribute("result", "添加成功");
		return selClubImage(model, request);
	}

	public String delClubImage(int id, Model model, HttpServletRequest request) {
		ClubMember president = (ClubMember) request.getSession().getAttribute("president");
		if (president == null) {
			return "pr_image.jsp";
		}
		if (imageMapper.delImageById(id) != 1) {
			// 删除失败
			System.err.println("删除失败");
			model.addAttribute("result", "删除失败");
			return selClubImage(model, request);
		}
		model.addAttribute("result", "删除成功");
		return selClubImage(model, request);
	}
	
	

	
	public String load(MultipartFile file,String name,String url,Model model, HttpServletRequest request) throws IllegalStateException, IOException {
		System.err.println("image:"+name+","+url);
		ClubMember president = (ClubMember) request.getSession().getAttribute("president");
		if (president == null) {
			return "image.jsp";
		}
		/**
		 * 上传图片
		 */
		/*//图片上传成功后，将图片的地址写到数据库
        String filePath = "E:\\upload";//保存图片的路径
        System.err.println(filePath);
        //获取原始图片的拓展名
        String originalFilename = file.getOriginalFilename();
        System.err.println("拓展:"+originalFilename);
        //新的文件名字
        String newFileName = UUID.randomUUID()+originalFilename;
        //封装上传文件位置的全路径
        File targetFile = new File(filePath,newFileName); 
        //把本地文件上传到封装上传文件位置的全路径
        file.transferTo(targetFile);
        image.setName(newFileName);*/
		
		//获取原始图片的拓展名
		String originalFilename = file.getOriginalFilename();
		String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
		//新的文件名字
		String newFileName = UUID.randomUUID()+originalFilename;
		//上传到本地
		FileUtils.copyInputStreamToFile(file.getInputStream(), new File("E:\\upload"+newFileName+substring));
		
		if (imageMapper.insImage(name,url, president.getClubId()) != 1) {
			// 添加失败
			model.addAttribute("result", "添加失败");
			return selClubImage(model, request);
		}
		model.addAttribute("result", "添加成功");
		return selClubImage(model, request);
	}

}
