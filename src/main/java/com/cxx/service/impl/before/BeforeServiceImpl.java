package com.cxx.service.impl.before;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
import com.cxx.pojo.AdminModel;
import com.cxx.pojo.Classes;
import com.cxx.pojo.Club;
import com.cxx.pojo.ClubApply;
import com.cxx.pojo.ClubMember;
import com.cxx.pojo.ClubType;
import com.cxx.pojo.Event;
import com.cxx.pojo.MemberJoin;
import com.cxx.pojo.News;
import com.cxx.pojo.Notice;
import com.cxx.pojo.Receive;
import com.cxx.pojo.Student;
import com.cxx.service.before.BeforeService;
import com.cxx.util.EmailCodeUtil;

@Service
public class BeforeServiceImpl implements BeforeService {
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
	private ReceiveMapper recieveMapper;
	@Resource
	private AdminModelMapper adminModelMapper;
	@Resource
	private PrModelMapper prModelMapper;
	@Resource
	private ImageMapper imageMapper;

	public String login(Student student, Model model, HttpServletRequest request) throws IOException {
		// 用户登录
		if (student != null) {
			Student student2 = studentMapper.selByStudent(student.getId(), student.getPassword());
			System.out.println("student2:" + student2);
			if (student2 != null) {
				// 数据库存在这个用户
				// 更新在线状态
				int updSatus = studentMapper.updStuOnlineStatus(student.getId(), 1);
				if (updSatus == 1) {
					// 更新成功
					request.getSession().setAttribute("student", studentMapper.selById(student2.getId()));
					System.err.println("login student:" + request.getSession().getAttribute("student"));
					System.err.println("login president:" + request.getSession().getAttribute("president"));
					return goIndex(model, "登录成功！", request);
				} else {
					// 更新失败
					System.out.println("登陆失败");
					model.addAttribute("result", "登录失败");
					model.addAttribute("loginStudent", student);
					return "pwd_login.jsp";
				}
			}
		}
		// 数据库不存在这个用户（账号或密码错误）
		model.addAttribute("result", "账号或密码错误");
		return "pwd_login.jsp";
	}

	/**
	 * 发送验证码
	 * 
	 * @param email
	 * @param request
	 *
	 * @throws IOException
	 */
	public String preparedEmailLogin(String email, Model model, HttpServletRequest request) throws IOException {
		Student student = studentMapper.selByEmail(email);
		System.err.println("......................................");
		System.out.println("email:" + email);
		System.out.println("student:" + student);
		if (student != null) {
			// 邮箱存在
			String code = EmailCodeUtil.smsCode();
			System.out.println("code" + code);
			//EmailCodeUtil.sendLoginEmail(student.getName(), email, code); 
			request.getSession().setAttribute("code", code);
			request.getSession().setAttribute("login", "验证码发送成功");
			System.err.println("验证码发送成功");
			request.getSession().setAttribute("email", email);
			request.getSession().setAttribute("result", "success");
		} else {
			// 邮箱还没有注册
			request.getSession().setAttribute("login", "这个邮箱还没有注册");
		}
		return "redirect:sms_login.jsp";
	}

	/**
	 * 邮箱验证码登录
	 * 
	 *
	 * @param request
	 * @return
	 */
	public String emailLogin(String email, String code, Model model, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		String realCode = (String) session.getAttribute("code");
		if (realCode != null) {
			session.removeAttribute("code");
		}
		if (session.getAttribute("result") != null) {
			session.removeAttribute("result");
		}
		String oldEmail = (String) session.getAttribute("email");
		System.out.println("oldemail:" + oldEmail);
		// 验证发送验证码之前与之后的验证码是否匹配
		if (!email.equals(oldEmail)) {
			// 不是同一个
			session.setAttribute("login", "验证码已过期");
			return "redirect:sms_login.jsp";
		}
		// 如果realCode为空，也就是没有获取验证码就直接填上一个验证码
		if (realCode != null && code != null) {
			if (realCode.equals(code)) {
				// 验证码正确
				Student student = studentMapper.selByEmail(email);
				// 更新在线状态
				int updSatus = studentMapper.updStuOnlineStatus(student.getId(), 1);
				if (updSatus == 1) {
					// 更新成功
					session.setAttribute("student", studentMapper.selById(student.getId()));
					session.setAttribute("login", "登陆成功");
					if (session.getAttribute("email") != null) {
						session.removeAttribute("email");
					}
					return "redirect:/index.jsp";
				} else {
					// 更新失败
					System.out.println("登陆失败");
					session.setAttribute("login", "登录失败");
					return "redirect:sms_login.jsp";
				}
			} else {
				// 验证码不对
				// request.getSession().removeAttribute("code");
				session.setAttribute("login", "验证码错误");
				return "redirect:sms_login.jsp";
			}
		} else if (realCode == null) {
			// 没有发送验证码就直接填上一个验证码登录
			session.setAttribute("login", "验证码已过期，请重新获取");
			return "redirect:sms_login.jsp";
		} else {
			// 验证码为空
			session.setAttribute("login", "验证码为空");
			return "redirect:sms_login.jsp";
		}
	}

	/**
	 * 班级信息
	 * 
	 * @param request
	 * @return
	 */
	public String regClass(HttpServletRequest request) {
		List<Classes> classes = classesMapper.selAllClasses();
		System.out.println("classes:" + classes);
		request.getSession().setAttribute("classes", classes);
		return "redirect:reg.jsp";
	}

	/**
	 * 发送验证码
	 * 
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void preparedReg(Student regStudent, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		System.err.println("prePReg student:" + regStudent);
		;
		Student student = studentMapper.selByEmail(regStudent.getEmail());
		// 控制台
		System.err.println("......................................");
		System.out.println("student:" + student);
		// 设置响应头格式
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();

		regStudent.setClasses(classesMapper.selById(regStudent.getClassesId()));
		request.getSession().setAttribute("regStudent", regStudent);
		System.err.println("regStudent:" + regStudent);
		if (student == null) {
			// 邮箱还没有注册
			String code = EmailCodeUtil.smsCode();
			System.err.println("code" + code);
			/*
			 * EmailCodeUtil.sendLoginEmail(student.getRealname(), user.getEmail(), code);
			 */
			request.getSession().setAttribute("code", code);
			System.err.println("验证码发送成功");
			request.getSession().setAttribute("reg", "验证码发送成功");

			// out.println("history.back()");
			// window.location.href='/club/before/sms_login.jsp'

			request.getSession().setAttribute("result", "success");

		} else {
			// 邮箱注册过了
			request.getSession().setAttribute("reg", "这个邮箱已经注册,请登录");

		}
		writer.flush();
		// writer.write("<script>history.back();</script>");
		writer.write("<script>window.location.href='/club1/before/regClass'</script>");

	}

	/**
	 * 注册
	 * 
	 *
	 * @param code
	 * @param request
	 *
	 * @throws IOException
	 */
	@RequestMapping("reg")
	public String reg(Student student, String code, HttpServletRequest request) throws IOException {
		System.err.println("reg student:" + student);
		HttpSession session = request.getSession();
		Student regStudent = (Student) session.getAttribute("regStudent");
		if (regStudent == null) {
			request.getSession().setAttribute("reg", "请获取验证码后再注册");
			request.getSession().setAttribute("regStudent", student);
			return "redirect:regClass";
		}
		String oldEmail = regStudent.getEmail();

		// 真正的发给邮箱的验证码
		String realCode = (String) session.getAttribute("code");

		// 如果当前邮箱与发送验证码的邮箱不匹配
		if (!oldEmail.equals(student.getEmail())) {
			request.getSession().setAttribute("reg", "邮箱验证码已过期");
			request.getSession().setAttribute("regStudent", student);
			return "redirect:regClass";
		}
		// 如果realCode为空，也就是没有获取验证码就直接填上一个验证码
		if (realCode != null && code != null) {
			// 1.已经发送验证码，且提交上来的验证码也不为空
			if (realCode.equals(code)) {
				// 验证码正确

				// 验证学号是否已经存在
				Student student2 = studentMapper.selById(student.getId());
				if (student2 == null) {
					// 不存在则创建一个用户
					int insStudent = studentMapper.insStudent(student);
					if (insStudent == 1) {
						// 插入成功
						System.err.println("创建成功！！！");
						session.setAttribute("student", studentMapper.selById(student.getId()));
						session.removeAttribute("code");
						// 删除发送验证码之前的用户信息
						if (regStudent != null) {
							session.removeAttribute("regStudent");
						}
						request.getSession().setAttribute("reg", "注册成功！");
						return "redirect:index.jsp";
					} else {
						// 插入失败
						request.getSession().setAttribute("reg", "注册失败！");
						request.getSession().setAttribute("regStudent", student);
						return "redirect:regClass";
					}

				} else {
					// 存在则退出
					request.getSession().setAttribute("reg", "该用户已注册！");
					request.getSession().setAttribute("regStudent", student);
					return "redirect:regClass";
				}
			} else {
				// 验证码不对
				session.removeAttribute("code");
				request.getSession().setAttribute("reg", "验证码错误！");
				request.getSession().setAttribute("regStudent", student);
				return "redirect:regClass";
			}

		} else if (realCode == null) {
			// 2. 没有发送验证码就直接填上一个验证码登录
			request.getSession().setAttribute("reg", "验证码已过期,请重新获取！");
			request.getSession().setAttribute("regStudent", student);
			return "redirect:regClass";
		} else {
			// 3.验证码为空
			session.removeAttribute("code");
			request.getSession().setAttribute("reg", "验证码为空！");
			request.getSession().setAttribute("regStudent", student);
			return "redirect:regClass";
		}
	}

	/**
	 * 退出登录
	 * 
	 * @param request
	 * @return
	 */
	public String logout(Model model, HttpServletRequest request) {
		Student student = (Student) request.getSession().getAttribute("student");
		if (student != null) {
			// 将学生状态改为不在线
			int updSatus = studentMapper.updStuOnlineStatus(student.getId(), 0);
			if (updSatus == 1) {
				// 更新成功
				request.getSession().removeAttribute("student");
				if (request.getSession().getAttribute("president") != null) {
					request.getSession().removeAttribute("president");
				}
			} else {
				// 更新失败
				System.out.println("更新失败");
			}

		}
		return goIndex(model, null, request);
	}

	public String goIndex(Model model, String result, HttpServletRequest request) {
		List<ClubType> clubTypes = clubTypeMapper.selAllClubTypes();
		model.addAttribute("clubTypes", clubTypes);
		List<Notice> notices = noticeMapper.selAllNotices();
		model.addAttribute("notices", notices);
		List<Club> clubs = clubMapper.selAllRunOrNotClub(0);
		model.addAttribute("clubs", clubs);
		List<News> news = newsMapper.selAllNews();
		model.addAttribute("news", news);
		List<Event> events = eventMapper.selAllEventByStatus(1);
		model.addAttribute("events", events);
		model.addAttribute("result", result);

		System.err.println("index.jsp");
		return "index.jsp";
	}

	/**
	 * 个人中心，判断是普通用户还是会长
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	public String goCenter(Model model, HttpServletRequest request) {
		Student student = (Student) request.getSession().getAttribute("student");
		// ClubMember president = (ClubMember)
		// request.getSession().getAttribute("president");
		if (student == null) {
			return goIndex(model, "请先登录", request);
		}
		return "center.jsp";
		/*
		 * if (president==null) { return "center.jsp"; } if
		 * (student.getId()==president.getStudentId()) { //同一个人 return
		 * "/admin/pr_main.jsp"; }else { return "center.jsp"; }
		 */
		// 判断他是不是会长

		/*
		 * if (president == null) { // 判断他是不是会长 ClubMember newPresident =
		 * clubMemberMapper.selPrsdtBySidStatus(student.getId(), 1); if (newPresident !=
		 * null) { request.getSession().setAttribute("president", newPresident);
		 * System.err.println("president:" +
		 * request.getSession().getAttribute("newPresident")); return
		 * "/admin/pr_main.jsp"; } else { // 普通成员 return "center.jsp"; } } else { // 是会长
		 * return "/admin/pr_main.jsp"; }
		 */

	}

	public String updOneStudent(Student student, Model model, HttpServletRequest request) {
		// 邮箱是否已经存在
		int sameEmail = studentMapper.selIfSameEmail(student.getEmail(), student.getId());
		if (sameEmail > 0) {
			// 邮箱已经存在
			model.addAttribute("result", "邮箱已经存在");
			return "student.jsp";
		}
		// 手机号是否已经存在
		int samePhone = studentMapper.selIfSamePhone(student.getPhone(), student.getId());
		if (samePhone > 0) {
			// 手机号已经存在
			model.addAttribute("result", "手机号已经存在");
			return "student.jsp";
		}
		// 修改用户信息
		int updPreStu = studentMapper.updStudent(student);
		if (updPreStu != 1) {
			// 更新用户信息失败
			model.addAttribute("result", "保存失败");
			return "student.jsp";
		}

		request.getSession().setAttribute("student", studentMapper.selById(student.getId()));
		model.addAttribute("result", "保存成功");
		return "student.jsp";
	}

	public String updOneStudentPwd(Student student, String newPassword, Model model, HttpServletRequest request) {
		System.err.println("updpwdStudent:" + student);
		//是否登录
		Student student1 = (Student) request.getSession().getAttribute("student");
		if (student1 == null) {
			model.addAttribute("result", "请先登录");
			return "pwd_login.jsp";
		}
		//输入的旧密码是否正确
		if (!student1.getPassword().equals(student.getPassword())) {
			// 输入的旧密码错误
			model.addAttribute("result", "输入密码错误");
			model.addAttribute("studentPwd", student);
			return "student_updpwd.jsp";
		}
		// 更新密码
		int updPreStu = studentMapper.updStudentPwd(newPassword, student1.getId());
		if (updPreStu != 1) {
			// 更新密码失败
			model.addAttribute("result", "修改密码失败");
			model.addAttribute("studentPwd", student);
			return "student_updpwd.jsp";
		}
		//修改成功
		request.getSession().setAttribute("student", studentMapper.selById(student1.getId()));
		model.addAttribute("result", "保存成功");
		return "student_updpwd.jsp";
	}

	/**
	 * 某一个社团类型有哪些社团
	 * 
	 * @param clubType
	 * @param model
	 * @param request
	 * @return
	 */
	public String selClubOfOneType(ClubType clubType, Model model, HttpServletRequest request) {
		System.err.println(clubType);
		if (clubType == null) {
			clubType = new ClubType();
			clubType.setId(1);
			if (clubType.getId() == 0) {
				clubType.setId(1);
			}
		}
		List<Club> clubs = clubMapper.selClubsByClubTypeIdAndStatus(clubType.getId(), 0);
		List<ClubType> clubTypes = clubTypeMapper.selAllClubTypes();
		model.addAttribute("clubs", clubs);
		model.addAttribute("clubTypes", clubTypes);
		model.addAttribute("clubType", clubTypeMapper.selById(clubType.getId()));
		System.err.println("clubs:" + clubs);
		System.err.println("clubTypes:" + clubTypes);
		return "club.jsp";
	}

	/**
	 * 通过社团编号查询社团信息
	 * 
	 * @param club
	 * @param model
	 * @param request
	 * @return
	 */
	public String selClubById(Club club, Model model, HttpServletRequest request) {
		// 查询社团类型中，正常状态0下的社团
		model.addAttribute("clubs", clubMapper.selClubsByClubTypeIdAndStatus(club.getClubTypeId(), 0));
		model.addAttribute("club", clubMapper.selById(club.getId()));
		model.addAttribute("images", imageMapper.selByClubId(club.getId()));
		return "club_sel.jsp";
	}

	/**
	 * 根据社团公告
	 * 
	 * @param notice
	 * @param model
	 * @param request
	 * @return
	 */
	public String selNoticesById(Notice notice, Model model, HttpServletRequest request) {
		model.addAttribute("notices", noticeMapper.selNoticeByClubId(notice.getClubId()));
		model.addAttribute("clubs", clubMapper.selAllRunOrNotClub(0));
		model.addAttribute("club", clubMapper.selById(notice.getClubId()));
		return "notice.jsp";
	}

	/**
	 * 根据社团id查询公告
	 * 
	 * @param notice
	 * @param model
	 * @param request
	 * @return
	 */
	public String selNoticesOfClub(Notice notice, Model model, HttpServletRequest request) {
		model.addAttribute("notices", noticeMapper.selNoticeByClubId(notice.getClubId()));
		model.addAttribute("notice", noticeMapper.selById(notice.getId()));
		model.addAttribute("clubs", clubMapper.selAllRunOrNotClub(0));
		return "notice_sel.jsp";
	}

	/**
	 * 社团新闻
	 * 
	 *
	 * @param model
	 * @param request
	 * @return
	 */
	public String selNewsById(News news, Model model, HttpServletRequest request) {

		System.err.println("news:" + news);
		List<News> newss = newsMapper.selAllNews();
		if (news.getId() == 0) {
			news = newss.get(0);
		} else {
			news = newsMapper.selById(news.getId());
		}
		model.addAttribute("news", news);
		model.addAttribute("newss", newss);
		return "news_sel.jsp";
	}

	public String selEvents(Event event, Model model, HttpServletRequest request) {
		model.addAttribute("events", eventMapper.selEventByClubIdAndStatus(event.getClubId(), 1));
		model.addAttribute("clubs", clubMapper.selAllRunOrNotClub(0));
		model.addAttribute("club", clubMapper.selById(event.getClubId()));
		return "event.jsp";
	}

	public String selEventById(Event event, Model model, HttpServletRequest request) {
		model.addAttribute("events", eventMapper.selEventByClubIdAndStatus(event.getClubId(), 1));
		model.addAttribute("event", eventMapper.selById(event.getId()));
		return "event_sel.jsp";
	}

	/**
	 * 添加社团 申请加入社团
	 * 
	 * @param club
	 * @param model
	 * @param request
	 * @return
	 */
	public String addMemberJoin(Club club, String active, Model model, HttpServletRequest request) {
		System.err.println("active:" + active);
		Student student = (Student) request.getSession().getAttribute("student");
		if (student == null) {
			model.addAttribute("result", "请先登录");
			return "pwd_login.jsp";
		}
		// 已经登录

		// 是否已经申请过了
		int ifMemberJoin = memberJoinMapper.selIfMemberExitByStatus(student.getId(), club.getId(), 2);
		if (ifMemberJoin > 0) {
			// 已经申请过了
			model.addAttribute("result", "您已经申请过该社团了");
			if ("sel".equals(active)) {
				return selClubById(club, model, request);
			}
			return selClubOfOneType(club.getClubType(), model, request);
		}

		// 是否已经是该社团的成员了
		int ifClubMember = clubMemberMapper.ifClubMember(student.getId(), club.getId());
		if (ifClubMember > 0) {
			// 已经是该社团的成员了
			model.addAttribute("result", "您已经加入该社团了");
			if ("sel".equals(active)) {
				return selClubById(club, model, request);
			}
			return selClubOfOneType(club.getClubType(), model, request);
		}

		int addMemberJoin = memberJoinMapper.insMemberJoin(student.getId(), club.getId());
		if (addMemberJoin != 1) {
			// 申请失败
			model.addAttribute("result", "申请失败");
			if ("sel".equals(active)) {
				return selClubById(club, model, request);
			}
			return selClubOfOneType(club.getClubType(), model, request);
		}
		model.addAttribute("result", "申请成功，等待管理员审核");
		if ("sel".equals(active)) {
			return selClubById(club, model, request);
		}
		return selClubOfOneType(club.getClubType(), model, request);
	}

	/**
	 * 查询某个学生 申请加入社团历史
	 * 
	 * @param club
	 * @param model
	 * @param request
	 * @return
	 */
	public String selClubsByStudent(Club club, Model model, HttpServletRequest request) {
		Student student = (Student) request.getSession().getAttribute("student");
		if (student == null) {
			model.addAttribute("result", "请先登录");
			return "pwd_login.jsp";
		}
		// 已经登录
		// 查询已经加入的社团
		return "";
	}

	/**
	 * 查询某个学生加入社团的历史
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	public String joinClubHistory(Model model, HttpServletRequest request) {
		Student student = (Student) request.getSession().getAttribute("student");
		System.err.println("student:" + student);
		if (student == null) {
			// 还没有登陆
			model.addAttribute("result", "您还没有登录");
			return "student_center_club_join.jsp";
		}

		List<MemberJoin> clubs = memberJoinMapper.selMemberJoinByStudentId(student.getId());
		if (clubs == null || clubs.size() == 0) {
			model.addAttribute("result", "暂无申请记录");
			return "student_center_club_join.jsp";
		}
		model.addAttribute("clubs", clubs);
		return "student_center_club_join.jsp";
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

	/*
	 * //未读信息
	 * 
	 */
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
			return "center_left.jsp";
		}
		int count = recieveMapper.selCountByStudentIdStatus(student.getId(), 1);
		System.err.println("未读信息：" + count);
		model.addAttribute("count", count);
		return "center_left.jsp";
	}

	/**
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	public String selMessage(Model model, HttpServletRequest request) {
		Student student = (Student) request.getSession().getAttribute("student");
		if (student == null) {
			model.addAttribute("result", "请先登录");
			System.err.println("请先登录");
			return "message.jsp";
		}
		// 未读信息
		List<Receive> noMessages = recieveMapper.selByStudentIdStatus(student.getId(), 1);
		// 已读信息
		List<Receive> yesMessage = recieveMapper.selByStudentIdStatus(student.getId(), 0);
		model.addAttribute("nom", noMessages);
		model.addAttribute("yesm", yesMessage);
		// 修改该学生应该接受的信息状态为0已接收
		// 本来未读信息条数
		int count = recieveMapper.selCountByStudentIdStatus(student.getId(), 1);
		if (count != 0) {
			int update = recieveMapper.updRecieve(student.getId(), 1, 0);
			System.err.println("update:" + update);
			if (update != count) {
				model.addAttribute("result", "更新失败");
				System.err.println("更新失败");
				return "message.jsp";
			}
		}
		System.err.println("message.jsp");
		model.addAttribute("count", count);
		return "message.jsp";
	}

	/**
	 * 学生新建社团
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	public String addClubApply(ClubApply clubApply, Model model, HttpServletRequest request) {
		System.err.println("clubApply:" + clubApply);
		Student student = (Student) request.getSession().getAttribute("student");
		if (student == null) {
			// 没有登陆
			model.addAttribute("result", "先登录");
			return "club_add_apply.jsp";
		}

		/*
		 * //是否已经申请过了 if (clubApplyMapper.selByStudentIdStatus(student.getId(),
		 * 2)!=null) { //已经申请过了，待审核 model.addAttribute("result", "您已经申请过了,待审核"); return
		 * selStuClubApp(model,request); } //是否已经是社长 if
		 * (clubApplyMapper.selByStudentIdStatus(student.getId(), 1)!=null) {
		 * //已经申请过了，而且已经通过 model.addAttribute("result", "您已经申请通过了"); return
		 * selStuClubApp(model,request); }
		 */
		// 这个社团是否已经存在
		if (clubMapper.selByName(clubApply.getName()) != null) {
			// 已经存在
			model.addAttribute("result", "这个社团已经存在");
			model.addAttribute("clubApply", clubApply);
			model.addAttribute("clubTypes", clubTypeMapper.selAllClubTypes());
			return "club_add_apply.jsp";
		}
		int add = clubApplyMapper.insClubApply(student.getId(), clubApply.getClubTypeId(), clubApply.getName(),
				clubApply.getReason());
		if (add != 1) {
			model.addAttribute("result", "申请失败");
			model.addAttribute("clubApply", clubApply);
			model.addAttribute("clubTypes", clubTypeMapper.selAllClubTypes());
			return "club_add_apply.jsp";
		}
		model.addAttribute("result", "申请成功");
		return selStuClubApp(model, request);
	}

	/**
	 * 准备学生新建社团
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	public String preStuClubApply(Model model, HttpServletRequest request) {
		Student student = (Student) request.getSession().getAttribute("student");
		if (student == null) {
			// 没有登陆
			model.addAttribute("result", "先登录");
			return "club_add_apply.jsp";
		}

		// 是否已经申请过了
		List<ClubApply> clubApplies = clubApplyMapper.selByStudentIdStatus(student.getId(), 2);
		if (clubApplies != null && clubApplies.size() != 0) {
			// 已经申请过了，待审核
			System.err.println("已经申请过了");
			model.addAttribute("result", "您已经申请过了,待审核");
			return selStuClubApp(model, request);
		}
		// 是否已经是社长
		ClubMember prisident = clubMemberMapper.selPrsdtBySidStatus(student.getId(), 1);
		if (prisident != null) {
			// 是社长
			System.err.println("您已经是社长");
			model.addAttribute("result", "您已经是社长");
			return "club_apply_sel.jsp";
		}
		model.addAttribute("clubTypes", clubTypeMapper.selAllClubTypes());
		return "club_add_apply.jsp";
	}

	/**
	 * 查看新建社团历史信息
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	public String selStuClubApp(Model model, HttpServletRequest request) {
		Student student = (Student) request.getSession().getAttribute("student");
		if (student == null) {
			// 没有登陆
			model.addAttribute("result", "先登录");
			return "club_apply_sel.jsp";
		}
		List<ClubApply> clubApplies = clubApplyMapper.selByStudentId(student.getId());
		if (clubApplies != null && clubApplies.size() != 0) {
			model.addAttribute("clubApply", clubApplies);
		} else {
			model.addAttribute("result", "暂无申请历史");
		}
		return "club_apply_sel.jsp";
	}

	/**
	 * 取消申请的社团
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	public String delClubApply(int id, Model model, HttpServletRequest request) {
		if (clubApplyMapper.delClubApplyById(id) != 1) {
			model.addAttribute("result", "取消申请失败!");
		}
		model.addAttribute("result", "取消申请成功!");
		return selStuClubApp(model, request);
	}

	/**
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	public String selImageOfClub(Model model, HttpServletRequest request) {

		return "";
	}
}
