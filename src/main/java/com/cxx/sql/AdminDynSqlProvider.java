package com.cxx.sql;

import java.util.Map;

import org.apache.commons.lang.ObjectUtils.Null;
import org.apache.ibatis.jdbc.SQL;

import com.cxx.pojo.Admin;

public class AdminDynSqlProvider {

	/**
	 * 动态查询管理员信息
	 * 
	 * @param map
	 * @return
	 */
	public String selAdmins(final Map map) {
		// 方法中的关键字是区分大小写的 SQL SELECT WHERE
		// 该方法会根据传递过来的map中的参数内容 动态构建sql语句
		if (map == null || map.size() == 0) {
			return "select * from admin";
		}
		// 用来判断是否是全空
		boolean isAllNull = false;
		String sql = "select * from admin where 1=1";
		if (!map.get("id").toString().equals("0") && (map.get("id") != null) && (!map.get("id").equals(""))) {
			sql += " and id like \'%" + map.get("id") + "%\'";
			isAllNull = true;
		}
		if (map.get("name") != null && !map.get("name").equals("")) {
			sql += " and name like \'%" + map.get("name") + "%\'";
			isAllNull = true;
		}
		if (map.get("email") != null && !map.get("email").equals("")) {
			sql += " and email like \'%" + map.get("email") + "%\'";
			isAllNull = true;
		}
		if (map.get("phone") != null && !map.get("phone").equals("")) {
			sql += " and phone like \'%" + map.get("phone") + "%\'";
			isAllNull = true;
		}
		if (map.get("time") != null && !map.get("time").equals("")) {
			sql += " and time like \'%" + map.get("time") + "%\'";
			isAllNull = true;
		}
		/*
		 * if (map.get("typeName") != null && !map.get("typeName").equals("")) { sql+=;
		 * isAllNull = true; }
		 */

		if (!isAllNull) {
			return "select * from admin ";
		}

		return sql;
	}

	/**
	 * 动态查询数据库管理员数据条数
	 * 
	 * @param admin
	 * @return
	 */
	public String selCountAdmins(final Admin admin) {
		// 方法中的关键字是区分大小写的 SQL SELECT WHERE
		// 该方法会根据传递过来的map中的参数内容 动态构建sql语句
		if (admin == null) {
			return null;
		}
		return new SQL() {
			{
				SELECT("count(*)");
				FROM("admin");
				if (admin.getId() != 0) {
					WHERE("id like concat('%', #{id}, '%')");
				}
				if (admin.getName() != null && admin.getName() != "") {
					WHERE("name like concat('%', #{name}, '%')");
				}
				if (admin.getEmail() != null && admin.getEmail() != "") {
					WHERE("email like concat('%', #{email}, '%')");
				}
				if (admin.getPhone() != null && admin.getPhone() != "") {
					WHERE("phone like concat('%', #{phone}, '%')");
				}
				if (admin.getTime() != null && admin.getTime() != "") {
					WHERE("time like concat('%', #{time}, '%')");
				}
			}

		}.toString();
	}

	/**
	 * 动态查询社团数据
	 * 
	 * @param map
	 * @return
	 */
	public String selClubs(final Map map) {
		// 方法中的关键字是区分大小写的 SQL SELECT WHERE
		// 该方法会根据传递过来的map中的参数内容 动态构建sql语句
		if (map == null || map.size() == 0) {
			return "select * from club";
		}
		// 用来判断是否是全空
		boolean isAllNull = false;
		String sql = "select * from club where 1=1";
		if (!map.get("id").toString().equals("0") && (map.get("id") != null) && (!map.get("id").equals(""))) {
			sql += " and id like \'%" + map.get("id") + "%\'";
			isAllNull = true;
		}
		if (map.get("studentName") != null && !map.get("studentName").equals("")) {
			sql += " and student_id in(select id from student where name like \'%" + map.get("studentName") + "%\')";
			isAllNull = true;
		}
		if (map.get("clubTypeName") != null && !map.get("clubTypeName").equals("")) {
			sql += " and club_type_id in(select id from club_type where name like \'%" + map.get("clubTypeName")
					+ "%\')";
			isAllNull = true;
		}
		if (map.get("place") != null && !map.get("place").equals("")) {
			sql += " and place like \'%" + map.get("place") + "%\'";
			isAllNull = true;
		}
		if (map.get("name") != null && !map.get("name").equals("")) {
			sql += " and name like \'%" + map.get("name") + "%\'";
			isAllNull = true;
		}
		if (map.get("status").toString().equals("0") || map.get("status").toString().equals("1")) {
			sql += " and status like \'%" + map.get("status") + "%\'";
			isAllNull = true;
		}

		if (map.get("time") != null && !map.get("time").equals("")) {
			sql += " and time like \'%" + map.get("time") + "%\'";
			isAllNull = true;
		}

		if (!isAllNull) {
			return "select * from club ";
		}

		return sql;
	}

	/**
	 * 动态查询社团类型数据
	 * 
	 * @param map
	 * @return
	 */
	public String selClubTypes(final Map map) {
		// 方法中的关键字是区分大小写的 SQL SELECT WHERE
		// 该方法会根据传递过来的map中的参数内容 动态构建sql语句
		if (map == null || map.size() == 0) {
			return "select * from club_type";
		}
		// 用来判断是否是全空
		boolean isAllNull = false;
		String sql = "select * from club_type where 1=1";
		if (!map.get("id").toString().equals("0") && (map.get("id") != null) && (!map.get("id").equals(""))) {
			sql += " and id like \'%" + map.get("id") + "%\'";
			isAllNull = true;
		}

		if (map.get("name") != null && !map.get("name").equals("")) {
			sql += " and name like \'%" + map.get("name") + "%\'";
			isAllNull = true;
		}
		if (!isAllNull) {
			return "select * from club_type ";
		}

		return sql;
	}

	public String selClubApplies(final Map map) {
		// 方法中的关键字是区分大小写的 SQL SELECT WHERE
		// 该方法会根据传递过来的map中的参数内容 动态构建sql语句
		if (map == null || map.size() == 0) {
			return "select * from club_apply";
		}
		// 用来判断是否是全空
		boolean isAllNull = false;
		String sql = "select * from club_apply where 1=1";
		if (!map.get("id").toString().equals("0") && (map.get("id") != null) && (!map.get("id").equals(""))) {
			sql += " and id like \'%" + map.get("id") + "%\'";
			isAllNull = true;
		}
		if (!map.get("adminId").toString().equals("0") && (map.get("adminId") != null)
				&& (!map.get("adminId").equals(""))) {
			sql += " and admin_id like \'%" + map.get("adminId") + "%\'";
			isAllNull = true;
		}
		if (!map.get("studentId").toString().equals("0") && (map.get("studentId") != null)
				&& (!map.get("studentId").equals(""))) {
			sql += " and student_id like \'%" + map.get("studentId") + "%\'";
			isAllNull = true;
		}

		if (map.get("studentName") != null && !map.get("studentName").equals("")) {
			sql += " and student_id in(select id from student where name like \'%" + map.get("studentName") + "%\')";
			isAllNull = true;
		}
		if (map.get("clubTypeName") != null && !map.get("clubTypeName").equals("")) {
			sql += " and club_type_id in(select id from club_type where name like \'%" + map.get("clubTypeName")
					+ "%\')";
			isAllNull = true;
		}

		if (map.get("name") != null && !map.get("name").equals("")) {
			sql += " and name like \'%" + map.get("name") + "%\'";
			isAllNull = true;
		}

		if (map.get("time") != null && !map.get("time").equals("")) {
			sql += " and time like \'%" + map.get("time") + "%\'";
			isAllNull = true;
		}
		if (map.get("status").toString().equals("0") || map.get("status").toString().equals("1")
				|| map.get("status").toString().equals("2")) {
			sql += " and status like \'%" + map.get("status") + "%\'";
			isAllNull = true;
		}
		if (!isAllNull) {
			return "select * from club_apply";
		}
		return sql;
	}

	public String selAcademys(final Map map) {
		// 方法中的关键字是区分大小写的 SQL SELECT WHERE
		// 该方法会根据传递过来的map中的参数内容 动态构建sql语句
		if (map == null || map.size() == 0) {
			return "select * from academy";
		}
		// 用来判断是否是全空
		boolean isAllNull = false;
		String sql = "select * from academy where 1=1";
		if (!map.get("id").toString().equals("0") && (map.get("id") != null) && (!map.get("id").equals(""))) {
			sql += " and id like \'%" + map.get("id") + "%\'";
			isAllNull = true;
		}

		if (map.get("name") != null && !map.get("name").equals("")) {
			sql += " and name like \'%" + map.get("name") + "%\'";
			isAllNull = true;
		}
		if (!isAllNull) {
			return "select * from academy";
		}
		return sql;
	}

	public String selMajors(final Map map) {
		// 方法中的关键字是区分大小写的 SQL SELECT WHERE
		// 该方法会根据传递过来的map中的参数内容 动态构建sql语句
		if (map == null || map.size() == 0) {
			return "select * from major";
		}
		// 用来判断是否是全空
		boolean isAllNull = false;
		String sql = "select * from major where 1=1";
		if (!map.get("id").toString().equals("0") && (map.get("id") != null) && (!map.get("id").equals(""))) {
			sql += " and id like \'%" + map.get("id") + "%\'";
			isAllNull = true;
		}
		if (!map.get("academyId").toString().equals("0") && (map.get("academyId") != null)
				&& (!map.get("academyId").equals(""))) {
			sql += " and academy_id like \'%" + map.get("academyId") + "%\'";
			isAllNull = true;
		}
		if (map.get("academyName") != null && !map.get("academyName").equals("")) {
			sql += " and academy_id in(select id from academy where name like \'%" + map.get("academyName") + "%\')";
			isAllNull = true;
		}
		if (map.get("name") != null && !map.get("name").equals("")) {
			sql += " and name like \'%" + map.get("name") + "%\'";
			isAllNull = true;
		}
		if (!isAllNull) {
			return "select * from major";
		}
		return sql;
	}

	public String selClasses(final Map map) {
		// 方法中的关键字是区分大小写的 SQL SELECT WHERE
		// 该方法会根据传递过来的map中的参数内容 动态构建sql语句
		if (map == null || map.size() == 0) {
			return "select * from classes";
		}
		// 用来判断是否是全空
		boolean isAllNull = false;
		String sql = "select * from classes where 1=1";
		if (!map.get("id").toString().equals("0") && (map.get("id") != null) && (!map.get("id").equals(""))) {
			sql += " and id like \'%" + map.get("id") + "%\'";
			isAllNull = true;
		}
		if (!map.get("academyId").toString().equals("0") && (map.get("academyId") != null)
				&& (!map.get("academyId").equals(""))) {
			sql += " and major_id in(select id from major where academy_id like \'%" + map.get("academyId") + "%\')";
			isAllNull = true;
		}
		if (!map.get("majorId").toString().equals("0") && (map.get("majorId") != null)
				&& (!map.get("majorId").equals(""))) {
			sql += " and major_id like \'%" + map.get("majorId") + "%\'";
			isAllNull = true;
		}

		if (map.get("academyName") != null && !map.get("academyName").equals("")) {
			sql += " and major_id in(select id from major where academy_id in(select id from academy where name like \'%"
					+ map.get("academyName") + "%\'))";
			isAllNull = true;
		}
		if (map.get("majorName") != null && !map.get("majorName").equals("")) {
			sql += " and major_id in(select id from major where name like \'%" + map.get("majorName") + "%\')";
			isAllNull = true;
		}
		if (map.get("name") != null && !map.get("name").equals("")) {
			sql += " and name like \'%" + map.get("name") + "%\'";
			isAllNull = true;
		}
		if (!isAllNull) {
			return "select * from classes";
		}
		return sql;

	}

	public String selStudents(final Map map) {
		// 方法中的关键字是区分大小写的 SQL SELECT WHERE
		// 该方法会根据传递过来的map中的参数内容 动态构建sql语句
		if (map == null || map.size() == 0) {
			return "select * from student";
		}
		// 用来判断是否是全空
		boolean isAllNull = false;
		String sql = "select * from student where 1=1";
		if (!map.get("id").toString().equals("0") && (map.get("id") != null) && (!map.get("id").equals(""))) {
			sql += " and id like \'%" + map.get("id") + "%\'";
			isAllNull = true;
		}

		if (!map.get("academyId").toString().equals("0") && (map.get("academyId") != null)
				&& (!map.get("academyId").equals(""))) {
			sql += " and classes_id in(select id from classes where major_id in(select id from major where academy_id like \'%"
					+ map.get("academyId") + "%\'))";
			isAllNull = true;
		}
		if (!map.get("majorId").toString().equals("0") && (map.get("majorId") != null)
				&& (!map.get("majorId").equals(""))) {
			sql += " and classes_id in(select id from classes where major_id like \'%" + map.get("majorId") + "%\')";
			isAllNull = true;
		}
		if (!map.get("classesId").toString().equals("0") && (map.get("classesId") != null)
				&& (!map.get("classesId").equals(""))) {
			sql += " and classes_id like \'%" + map.get("classesId") + "%\'";
			isAllNull = true;
		}
		if (map.get("academyName") != null && !map.get("academyName").equals("")) {
			sql += " and classes_id in(select id from classes where major_id in(select id from major where academy_id in(select id from academy where name like \'%"
					+ map.get("academyName") + "%\')))";
			isAllNull = true;
		}
		if (map.get("majorName") != null && !map.get("majorName").equals("")) {
			sql += " and classes_id in(select id from classes where major_id in(select id from major where name like \'%"
					+ map.get("majorName") + "%\'))";
			isAllNull = true;
		}
		if (map.get("classesName") != null && !map.get("classesName").equals("")) {
			sql += " and classes_id in(select id from classes where name like \'%" + map.get("classesName") + "%\')";
			isAllNull = true;
		}
		if (map.get("name") != null && !map.get("name").equals("")) {
			sql += " and name like \'%" + map.get("name") + "%\'";
			isAllNull = true;
		}
		if (map.get("sex").toString().equals("0") || map.get("sex").toString().equals("1")) {
			sql += " and sex like \'%" + map.get("sex") + "%\'";
			isAllNull = true;
		}
		if (map.get("email") != null && !map.get("email").equals("")) {
			sql += " and email like \'%" + map.get("email") + "%\'";
			isAllNull = true;
		}
		if (map.get("phone") != null && !map.get("phone").equals("")) {
			sql += " and phone like \'%" + map.get("phone") + "%\'";
			isAllNull = true;
		}

		if (map.get("time") != null && !map.get("time").equals("")) {
			sql += " and time like \'%" + map.get("time") + "%\'";
			isAllNull = true;
		}
		if (map.get("status").toString().equals("0") || map.get("status").toString().equals("1")) {
			sql += " and status like \'%" + map.get("status") + "%\'";
			isAllNull = true;
		}
		if (!isAllNull) {
			return "select * from student";
		}
		return sql;

	}

	public String selClubMembers(final Map map) {
		if ("pr".equals(map.get("active").toString())) {
			if (map == null || map.size() == 0) {
				return "select * from club_member where club_id=" + map.get("clubId");
			}
			// 用来判断是否是全空
			boolean isAllNull = false;
			String sql = "select * from club_member where 1=1";
			if (!map.get("id").toString().equals("0") && (map.get("id") != null) && (!map.get("id").equals(""))) {
				sql += " and id like \'%" + map.get("id") + "%\'";
				isAllNull = true;
			}

			if (!map.get("studentId").toString().equals("0") && (map.get("studentId") != null)
					&& (!map.get("studentId").equals(""))) {
				sql += " and student_id like \'%" + map.get("studentId") + "%\'";
				isAllNull = true;
			}

			if (map.get("studentName") != null && !map.get("studentName").equals("")) {
				sql += " and student_id in(select id from student where name like \'%" + map.get("studentName")
						+ "%\')";
				isAllNull = true;
			}

			if (map.get("time") != null && !map.get("time").equals("")) {
				sql += " and time like \'%" + map.get("time") + "%\'";
				isAllNull = true;
			}
			if (map.get("status").toString().equals("0") || map.get("status").toString().equals("1")
					|| map.get("status").toString().equals("2") || map.get("status").toString().equals("3")) {
				sql += " and status like \'%" + map.get("status") + "%\'";
				isAllNull = true;
			}
			if (!isAllNull) {
				return "select * from club_member where club_id=" + map.get("clubId");
			}

			sql += " and club_id=" + map.get("clubId");
			return sql;
		}
		// 方法中的关键字是区分大小写的 SQL SELECT WHERE
		// 该方法会根据传递过来的map中的参数内容 动态构建sql语句
		if (map == null || map.size() == 0) {
			return "select * from club_member";
		}
		// 用来判断是否是全空
		boolean isAllNull = false;
		String sql = "select * from club_member where 1=1";
		if (!map.get("id").toString().equals("0") && (map.get("id") != null) && (!map.get("id").equals(""))) {
			sql += " and id like \'%" + map.get("id") + "%\'";
			isAllNull = true;
		}

		if (!map.get("studentId").toString().equals("0") && (map.get("studentId") != null)
				&& (!map.get("studentId").equals(""))) {
			sql += " and student_id like \'%" + map.get("studentId") + "%\'";
			isAllNull = true;
		}

		if (map.get("studentName") != null && !map.get("studentName").equals("")) {
			sql += " and student_id in(select id from student where name like \'%" + map.get("studentName") + "%\')";
			isAllNull = true;
		}

		if (!map.get("clubId").toString().equals("0") && (map.get("clubId") != null)
				&& (!map.get("clubId").equals(""))) {
			sql += " and club_id like \'%" + map.get("clubId") + "%\'";
			isAllNull = true;
		}
		if (map.get("clubName") != null && !map.get("clubName").equals("")) {
			sql += " and club_id in(select id from club where name like \'%" + map.get("clubName") + "%\')";
			isAllNull = true;
		}

		if (map.get("time") != null && !map.get("time").equals("")) {
			sql += " and time like \'%" + map.get("time") + "%\'";
			isAllNull = true;
		}
		if (map.get("status").toString().equals("0") || map.get("status").toString().equals("1")
				|| map.get("status").toString().equals("2") || map.get("status").toString().equals("3")) {
			sql += " and status like \'%" + map.get("status") + "%\'";
			isAllNull = true;
		}
		if (!isAllNull) {
			return "select * from club_member";
		}
		return sql;
	}

	public String selMemberQuits(final Map map) {
		if ("pr".equals(map.get("active").toString())) {
			if (map == null || map.size() == 0) {
				return "select * from member_quit where club_id=" + map.get("clubId");
			}
			// 用来判断是否是全空
			boolean isAllNull = false;
			String sql = "select * from member_quit where 1=1";
			if (!map.get("id").toString().equals("0") && (map.get("id") != null) && (!map.get("id").equals(""))) {
				sql += " and id like \'%" + map.get("id") + "%\'";
				isAllNull = true;
			}

			if (!map.get("studentId").toString().equals("0") && (map.get("studentId") != null)
					&& (!map.get("studentId").equals(""))) {
				sql += " and student_id like \'%" + map.get("studentId") + "%\'";
				isAllNull = true;
			}

			if (map.get("studentName") != null && !map.get("studentName").equals("")) {
				sql += " and student_id in(select id from student where name like \'%" + map.get("studentName")
						+ "%\')";
				isAllNull = true;
			}

			if (map.get("time") != null && !map.get("time").equals("")) {
				sql += " and time like \'%" + map.get("time") + "%\'";
				isAllNull = true;
			}
			if (!isAllNull) {
				return "select * from member_quit where club_id=" + map.get("clubId");
			}
			sql += " and club_id=" + map.get("clubId");
			return sql;
		}

		// 方法中的关键字是区分大小写的 SQL SELECT WHERE
		// 该方法会根据传递过来的map中的参数内容 动态构建sql语句
		if (map == null || map.size() == 0)

		{
			return "select * from member_quit";
		}
		// 用来判断是否是全空
		boolean isAllNull = false;
		String sql = "select * from member_quit where 1=1";
		if (!map.get("id").toString().equals("0") && (map.get("id") != null) && (!map.get("id").equals(""))) {
			sql += " and id like \'%" + map.get("id") + "%\'";
			isAllNull = true;
		}

		if (!map.get("studentId").toString().equals("0") && (map.get("studentId") != null)
				&& (!map.get("studentId").equals(""))) {
			sql += " and student_id like \'%" + map.get("studentId") + "%\'";
			isAllNull = true;
		}

		if (map.get("studentName") != null && !map.get("studentName").equals("")) {
			sql += " and student_id in(select id from student where name like \'%" + map.get("studentName") + "%\')";
			isAllNull = true;
		}

		if (!map.get("clubId").toString().equals("0") && (map.get("clubId") != null)
				&& (!map.get("clubId").equals(""))) {
			sql += " and club_id like \'%" + map.get("clubId") + "%\'";
			isAllNull = true;
		}
		if (map.get("clubName") != null && !map.get("clubName").equals("")) {
			sql += " and club_id in(select id from club where name like \'%" + map.get("clubName") + "%\')";
			isAllNull = true;
		}

		if (map.get("time") != null && !map.get("time").equals("")) {
			sql += " and time like \'%" + map.get("time") + "%\'";
			isAllNull = true;
		}
		if (!isAllNull) {
			return "select * from member_quit";
		}
		return sql;
	}

	public String selMemberJoins(final Map map) {
		if ("pr".equals(map.get("active").toString())) {
			if (map == null || map.size() == 0) {
				return "select * from member_join where club_id="+map.get("clubId");
			}
			// 用来判断是否是全空
			boolean isAllNull = false;
			String sql = "select * from member_join where 1=1";
			if (!map.get("id").toString().equals("0") && (map.get("id") != null) && (!map.get("id").equals(""))) {
				sql += " and id like \'%" + map.get("id") + "%\'";
				isAllNull = true;
			}

			if (!map.get("studentId").toString().equals("0") && (map.get("studentId") != null)
					&& (!map.get("studentId").equals(""))) {
				sql += " and student_id like \'%" + map.get("studentId") + "%\'";
				isAllNull = true;
			}

			if (map.get("studentName") != null && !map.get("studentName").equals("")) {
				sql += " and student_id in(select id from student where name like \'%" + map.get("studentName") + "%\')";
				isAllNull = true;
			}

			if (map.get("time") != null && !map.get("time").equals("")) {
				sql += " and time like \'%" + map.get("time") + "%\'";
				isAllNull = true;
			}
			if (map.get("status").toString().equals("0") || map.get("status").toString().equals("1")
					|| map.get("status").toString().equals("2")) {
				sql += " and status like \'%" + map.get("status") + "%\'";
				isAllNull = true;
			}
			if (!isAllNull) {
				return "select * from member_join where club_id="+map.get("clubId");
			}
			sql+=" and club_id="+map.get("clubId");
			return sql;
		}
		// 方法中的关键字是区分大小写的 SQL SELECT WHERE
		// 该方法会根据传递过来的map中的参数内容 动态构建sql语句
		if (map == null || map.size() == 0) {
			return "select * from member_join";
		}
		// 用来判断是否是全空
		boolean isAllNull = false;
		String sql = "select * from member_join where 1=1";
		if (!map.get("id").toString().equals("0") && (map.get("id") != null) && (!map.get("id").equals(""))) {
			sql += " and id like \'%" + map.get("id") + "%\'";
			isAllNull = true;
		}

		if (!map.get("studentId").toString().equals("0") && (map.get("studentId") != null)
				&& (!map.get("studentId").equals(""))) {
			sql += " and student_id like \'%" + map.get("studentId") + "%\'";
			isAllNull = true;
		}

		if (map.get("studentName") != null && !map.get("studentName").equals("")) {
			sql += " and student_id in(select id from student where name like \'%" + map.get("studentName") + "%\')";
			isAllNull = true;
		}

		if (!map.get("clubId").toString().equals("0") && (map.get("clubId") != null)
				&& (!map.get("clubId").equals(""))) {
			sql += " and club_id like \'%" + map.get("clubId") + "%\'";
			isAllNull = true;
		}
		if (map.get("clubName") != null && !map.get("clubName").equals("")) {
			sql += " and club_id in(select id from club where name like \'%" + map.get("clubName") + "%\')";
			isAllNull = true;
		}

		if (map.get("time") != null && !map.get("time").equals("")) {
			sql += " and time like \'%" + map.get("time") + "%\'";
			isAllNull = true;
		}
		if (map.get("status").toString().equals("0") || map.get("status").toString().equals("1")
				|| map.get("status").toString().equals("2")) {
			sql += " and status like \'%" + map.get("status") + "%\'";
			isAllNull = true;
		}
		if (!isAllNull) {
			return "select * from member_join";
		}
		return sql;
	}

	public String selNotices(final Map map) {
		if ("pr".equals(map.get("action").toString())) {
			// 社团管理员
			// 方法中的关键字是区分大小写的 SQL SELECT WHERE
			// 该方法会根据传递过来的map中的参数内容 动态构建sql语句
			if (map == null || map.size() == 0) {
				return "select * from notice where club_id=" + map.get("clubId");
			}
			// 用来判断是否是全空
			boolean isAllNull = false;
			String sql = "select * from notice where 1=1";
			if (!map.get("id").toString().equals("0") && (map.get("id") != null) && (!map.get("id").equals(""))) {
				sql += " and id like \'%" + map.get("id") + "%\'";
				isAllNull = true;
			}

			if (map.get("title") != null && !map.get("title").equals("")) {
				sql += " and title like \'%" + map.get("title") + "%\'";
				isAllNull = true;
			}
			if (map.get("time") != null && !map.get("time").equals("")) {
				sql += " and time like \'%" + map.get("time") + "%\'";
				isAllNull = true;
			}
			sql += " and club_id=" + map.get("clubId");
			if (!isAllNull) {
				return "select * from notice where club_id=" + map.get("clubId");
			}
			return sql;
		}
		// 方法中的关键字是区分大小写的 SQL SELECT WHERE
		// 该方法会根据传递过来的map中的参数内容 动态构建sql语句
		if (map == null || map.size() == 0) {
			return "select * from notice";
		}
		// 用来判断是否是全空
		boolean isAllNull = false;
		String sql = "select * from notice where 1=1";
		if (!map.get("id").toString().equals("0") && (map.get("id") != null) && (!map.get("id").equals(""))) {
			sql += " and id like \'%" + map.get("id") + "%\'";
			isAllNull = true;
		}

		if (!map.get("clubId").toString().equals("0") && (map.get("clubId") != null)
				&& (!map.get("clubId").equals(""))) {
			sql += " and club_id like \'%" + map.get("clubId") + "%\'";
			isAllNull = true;
		}
		if (map.get("clubName") != null && !map.get("clubName").equals("")) {
			sql += " and club_id in(select id from club where name like \'%" + map.get("clubName") + "%\')";
			isAllNull = true;
		}

		if (map.get("title") != null && !map.get("title").equals("")) {
			sql += " and title like \'%" + map.get("title") + "%\'";
			isAllNull = true;
		}
		if (map.get("time") != null && !map.get("time").equals("")) {
			sql += " and time like \'%" + map.get("time") + "%\'";
			isAllNull = true;
		}

		if (!isAllNull) {
			return "select * from notice";
		}
		return sql;
	}

	public String selNews(final Map map) {
		// 方法中的关键字是区分大小写的 SQL SELECT WHERE
		// 该方法会根据传递过来的map中的参数内容 动态构建sql语句
		if (map == null || map.size() == 0) {
			return "select * from news";
		}
		// 用来判断是否是全空
		boolean isAllNull = false;
		String sql = "select * from news where 1=1";
		if (!map.get("id").toString().equals("0") && (map.get("id") != null) && (!map.get("id").equals(""))) {
			sql += " and id like \'%" + map.get("id") + "%\'";
			isAllNull = true;
		}

		if (map.get("title") != null && !map.get("title").equals("")) {
			sql += " and title like \'%" + map.get("title") + "%\'";
			isAllNull = true;
		}
		if (map.get("people") != null && !map.get("people").equals("")) {
			sql += " and people like \'%" + map.get("people") + "%\'";
			isAllNull = true;
		}
		if (map.get("time") != null && !map.get("time").equals("")) {
			sql += " and time like \'%" + map.get("time") + "%\'";
			isAllNull = true;
		}

		if (!isAllNull) {
			return "select * from news";
		}
		return sql;
	}

	public String selEvents(final Map map) {
		if ("pr".equals(map.get("action").toString())) {
			if (map == null || map.size() == 0) {
				return "select * from event where club_id=" + map.get("clubId");
			}
			// 用来判断是否是全空
			boolean isAllNull = false;
			String sql = "select * from event where 1=1";
			if (!map.get("id").toString().equals("0") && (map.get("id") != null) && (!map.get("id").equals(""))) {
				sql += " and id like \'%" + map.get("id") + "%\'";
				isAllNull = true;
			}
			if (!map.get("studentId").toString().equals("0") && (map.get("studentId") != null)
					&& (!map.get("studentId").equals(""))) {
				sql += " and student_id like \'%" + map.get("studentId") + "%\'";
				isAllNull = true;
			}
			if (map.get("studentName") != null && !map.get("studentName").equals("")) {
				sql += " and student_id in(select id from student where name like \'%" + map.get("studentName")
						+ "%\')";
				isAllNull = true;
			}

			if (map.get("place") != null && !map.get("place").equals("")) {
				sql += " and place like \'%" + map.get("place") + "%\'";
				isAllNull = true;
			}
			if (map.get("name") != null && !map.get("name").equals("")) {
				sql += " and name like \'%" + map.get("name") + "%\'";
				isAllNull = true;
			}
			if (map.get("status").toString().equals("0") || map.get("status").toString().equals("1")
					|| map.get("status").toString().equals("2")) {
				sql += " and status like \'%" + map.get("status") + "%\'";
				isAllNull = true;
			}

			if (map.get("startTime") != null && !map.get("startTime").equals("")) {
				sql += " and start_time like \'%" + map.get("startTime") + "%\'";
				isAllNull = true;
			}
			if (map.get("endTime") != null && !map.get("endTime").equals("")) {
				sql += " and end_time like \'%" + map.get("endTime") + "%\'";
				isAllNull = true;
			}
			if (map.get("appTime") != null && !map.get("appTime").equals("")) {
				sql += " and app_time like \'%" + map.get("appTime") + "%\'";
				isAllNull = true;
			}
			if (map.get("subTime") != null && !map.get("subTime").equals("")) {
				sql += " and sub_time like \'%" + map.get("subTime") + "%\'";
				isAllNull = true;
			}

			if (!isAllNull) {
				return "select * from event where club_id=" + map.get("clubId");
			}
			sql += " and club_id=" + map.get("clubId");
			return sql;
		}

		// 方法中的关键字是区分大小写的 SQL SELECT WHERE
		// 该方法会根据传递过来的map中的参数内容 动态构建sql语句
		if (map == null || map.size() == 0) {
			return "select * from event";
		}
		// 用来判断是否是全空
		boolean isAllNull = false;
		String sql = "select * from event where 1=1";
		if (!map.get("id").toString().equals("0") && (map.get("id") != null) && (!map.get("id").equals(""))) {
			sql += " and id like \'%" + map.get("id") + "%\'";
			isAllNull = true;
		}
		if (!map.get("studentId").toString().equals("0") && (map.get("studentId") != null)
				&& (!map.get("studentId").equals(""))) {
			sql += " and student_id like \'%" + map.get("studentId") + "%\'";
			isAllNull = true;
		}
		if (!map.get("clubId").toString().equals("0") && (map.get("clubId") != null)
				&& (!map.get("clubId").equals(""))) {
			sql += " and club_id like \'%" + map.get("clubId") + "%\'";
			isAllNull = true;
		}
		if (map.get("studentName") != null && !map.get("studentName").equals("")) {
			sql += " and student_id in(select id from student where name like \'%" + map.get("studentName") + "%\')";
			isAllNull = true;
		}
		if (map.get("clubName") != null && !map.get("clubName").equals("")) {
			sql += " and club_id in(select id from club where name like \'%" + map.get("clubName") + "%\')";
			isAllNull = true;
		}
		if (map.get("place") != null && !map.get("place").equals("")) {
			sql += " and place like \'%" + map.get("place") + "%\'";
			isAllNull = true;
		}
		if (map.get("name") != null && !map.get("name").equals("")) {
			sql += " and name like \'%" + map.get("name") + "%\'";
			isAllNull = true;
		}
		if (map.get("status").toString().equals("0") || map.get("status").toString().equals("1")
				|| map.get("status").toString().equals("2")) {
			sql += " and status like \'%" + map.get("status") + "%\'";
			isAllNull = true;
		}

		if (map.get("startTime") != null && !map.get("startTime").equals("")) {
			sql += " and start_time like \'%" + map.get("startTime") + "%\'";
			isAllNull = true;
		}
		if (map.get("endTime") != null && !map.get("endTime").equals("")) {
			sql += " and end_time like \'%" + map.get("endTime") + "%\'";
			isAllNull = true;
		}
		if (map.get("appTime") != null && !map.get("appTime").equals("")) {
			sql += " and app_time like \'%" + map.get("appTime") + "%\'";
			isAllNull = true;
		}
		if (map.get("subTime") != null && !map.get("subTime").equals("")) {
			sql += " and sub_time like \'%" + map.get("subTime") + "%\'";
			isAllNull = true;
		}

		if (!isAllNull) {
			return "select * from event ";
		}

		return sql;
	}

	public String selAppClubs(final Map map) {
		// 方法中的关键字是区分大小写的 SQL SELECT WHERE
		// 该方法会根据传递过来的map中的参数内容 动态构建sql语句
		if (map == null || map.size() == 0) {
			return "select * from club_apply";
		}
		// 用来判断是否是全空
		boolean isAllNull = false;
		String sql = "select * from club_apply where 1=1";
		if (!map.get("id").toString().equals("0") && (map.get("id") != null) && (!map.get("id").equals(""))) {
			sql += " and id like \'%" + map.get("id") + "%\'";
			isAllNull = true;
		}
		if (!map.get("adminId").toString().equals("0") && (map.get("adminId") != null)
				&& (!map.get("adminId").equals(""))) {
			sql += " and admin_id like \'%" + map.get("adminId") + "%\'";
			isAllNull = true;
		}
		if (!map.get("studentId").toString().equals("0") && (map.get("studentId") != null)
				&& (!map.get("studentId").equals(""))) {
			sql += " and student_id like \'%" + map.get("studentId") + "%\'";
			isAllNull = true;
		}

		if (map.get("studentName") != null && !map.get("studentName").equals("")) {
			sql += " and student_id in(select id from student where name like \'%" + map.get("studentName") + "%\')";
			isAllNull = true;
		}
		if (map.get("clubTypeName") != null && !map.get("clubTypeName").equals("")) {
			sql += " and club_type_id in(select id from club_type where name like \'%" + map.get("clubTypeName")
					+ "%\')";
			isAllNull = true;
		}

		if (map.get("name") != null && !map.get("name").equals("")) {
			sql += " and name like \'%" + map.get("name") + "%\'";
			isAllNull = true;
		}
		sql += " and status=2";

		if (!isAllNull) {
			return "select * from club_apply where status=2";
		}
		return sql;
	}

	public String selAppEvents(final Map map) {
		// 方法中的关键字是区分大小写的 SQL SELECT WHERE
		// 该方法会根据传递过来的map中的参数内容 动态构建sql语句
		if (map == null || map.size() == 0) {
			return "select * from event and status=2";
		}
		// 用来判断是否是全空
		boolean isAllNull = false;
		String sql = "select * from event where 1=1";
		if (!map.get("id").toString().equals("0") && (map.get("id") != null) && (!map.get("id").equals(""))) {
			sql += " and id like \'%" + map.get("id") + "%\'";
			isAllNull = true;
		}
		if (!map.get("studentId").toString().equals("0") && (map.get("studentId") != null)
				&& (!map.get("studentId").equals(""))) {
			sql += " and student_id like \'%" + map.get("studentId") + "%\'";
			isAllNull = true;
		}
		if (!map.get("clubId").toString().equals("0") && (map.get("clubId") != null)
				&& (!map.get("clubId").equals(""))) {
			sql += " and club_id like \'%" + map.get("clubId") + "%\'";
			isAllNull = true;
		}
		if (map.get("studentName") != null && !map.get("studentName").equals("")) {
			sql += " and student_id in(select id from student where name like \'%" + map.get("studentName") + "%\')";
			isAllNull = true;
		}
		if (map.get("clubName") != null && !map.get("clubName").equals("")) {
			sql += " and club_id in(select id from club where name like \'%" + map.get("clubName") + "%\')";
			isAllNull = true;
		}
		if (map.get("place") != null && !map.get("place").equals("")) {
			sql += " and place like \'%" + map.get("place") + "%\'";
			isAllNull = true;
		}
		if (map.get("name") != null && !map.get("name").equals("")) {
			sql += " and name like \'%" + map.get("name") + "%\'";
			isAllNull = true;
		}

		if (map.get("startTime") != null && !map.get("startTime").equals("")) {
			sql += " and start_time like \'%" + map.get("startTime") + "%\'";
			isAllNull = true;
		}
		if (map.get("endTime") != null && !map.get("endTime").equals("")) {
			sql += " and end_time like \'%" + map.get("endTime") + "%\'";
			isAllNull = true;
		}
		if (map.get("appTime") != null && !map.get("appTime").equals("")) {
			sql += " and app_time like \'%" + map.get("appTime") + "%\'";
			isAllNull = true;
		}
		if (map.get("subTime") != null && !map.get("subTime").equals("")) {
			sql += " and sub_time like \'%" + map.get("subTime") + "%\'";
			isAllNull = true;
		}

		if (!isAllNull) {
			return "select * from event where status=2";
		}
		sql += " and status=2";
		return sql;
	}

	public String selAppMembers(final Map map) {
		// 方法中的关键字是区分大小写的 SQL SELECT WHERE
		// 该方法会根据传递过来的map中的参数内容 动态构建sql语句
		if (map == null || map.size() == 0) {
			return "select * from member_join where status=2";
		}
		// 用来判断是否是全空
		boolean isAllNull = false;
		String sql = "select * from member_join where 1=1";
		if (!map.get("id").toString().equals("0") && (map.get("id") != null) && (!map.get("id").equals(""))) {
			sql += " and id like \'%" + map.get("id") + "%\'";
			isAllNull = true;
		}

		if (!map.get("studentId").toString().equals("0") && (map.get("studentId") != null)
				&& (!map.get("studentId").equals(""))) {
			sql += " and student_id like \'%" + map.get("studentId") + "%\'";
			isAllNull = true;
		}

		if (map.get("studentName") != null && !map.get("studentName").equals("")) {
			sql += " and student_id in(select id from student where name like \'%" + map.get("studentName") + "%\')";
			isAllNull = true;
		}

		if (!map.get("clubId").toString().equals("0") && (map.get("clubId") != null)
				&& (!map.get("clubId").equals(""))) {
			sql += " and club_id like \'%" + map.get("clubId") + "%\'";
			isAllNull = true;
		}
		if (map.get("clubName") != null && !map.get("clubName").equals("")) {
			sql += " and club_id in(select id from club where name like \'%" + map.get("clubName") + "%\')";
			isAllNull = true;
		}

		if (map.get("time") != null && !map.get("time").equals("")) {
			sql += " and time like \'%" + map.get("time") + "%\'";
			isAllNull = true;
		}

		if (!isAllNull) {
			return "select * from member_join where status=2";
		}

		sql += " and status=2";
		return sql;
	}

}
