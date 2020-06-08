package com.cxx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import com.cxx.pojo.Classes;
import com.cxx.pojo.StuClub;
import com.cxx.pojo.Student;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

public interface StudentMapper {
	/**
	 * 用户登录
	 * 
	 * @param
	 * @return
	 */
	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "classes_id", property = "classesId"),
			@Result(column = "classes_id", property = "classes", one = @One(select = "com.cxx.mapper.ClassesMapper.selById")) })
	@Select("select * from student where id=#{arg0} and password=#{arg1}")
	Student selByStudent(int id, String password);

	/**
	 * 通过邮件查询用户信息
	 * 
	 * @param email
	 * @return
	 */
	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "classes_id", property = "classesId"),
			@Result(column = "classes_id", property = "classes", one = @One(select = "com.cxx.mapper.ClassesMapper.selById")) })
	@Select("select * from student where email=#{arg0}")
	Student selByEmail(String email);

	/**
	 * 修改用户上线状态
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	@Update("update student set status=#{arg1} where id=#{arg0}")
	int updStuOnlineStatus(int id, int status);

	/**
	 * 注册账号
	 * 
	 * @param student
	 * @return
	 */
	@Insert("insert into student values(#{id},#{name},#{password},#{sex},#{classesId},#{email},#{phone},1,sysdate(),#{hobby})")
	int insStudent(Student student);

	/**
	 * 通过id查询用户信息
	 * 
	 * @param id
	 * @return
	 */
	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "classes_id", property = "classesId"),
			@Result(column = "classes_id", property = "classes", one = @One(select = "com.cxx.mapper.ClassesMapper.selById")) })
	@Select("select * from student where id=#{arg0}")
	Student selById(int id);

	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "classes_id", property = "classesId"),
			@Result(column = "classes_id", property = "classes", one = @One(select = "com.cxx.mapper.ClassesMapper.selById")) })
	@Select("select * from student where name=#{arg0}")
	Student selByName(int name);

	@Select("select count(*) from student")
	int selStudentsCount();

	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "classes_id", property = "classesId"),
			@Result(column = "classes_id", property = "classes", one = @One(select = "com.cxx.mapper.ClassesMapper.selById")) })
	@Select("select * from student limit #{arg0},#{arg1}")
	List<Student> selStudentByPage(int starPage, int pageSize);

	/**
	 * 动态查询所有满足条件的数据
	 * 
	 * @return
	 */
	@Results(value = { @Result(id = true, column = "id", property = "id"),
			@Result(column = "classes_id", property = "classesId"),
			@Result(column = "classes_id", property = "classes", one = @One(select = "com.cxx.mapper.ClassesMapper.selById")) })
	@SelectProvider(type = com.cxx.sql.AdminDynSqlProvider.class, method = "selStudents")
	List<Student> selStudents(@Param("id") int id, @Param("name") String name, @Param("academyId") int academyId, @Param("academyName") String academyName,
                              @Param("majorId") int majorId, @Param("majorName") String majorName, @Param("classesId") int classesId, @Param("classesName") String classesName, @Param("sex") String sex,
                              @Param("email") String email, @Param("phone") String phone, @Param("time") String time,
                              @Param("status") int status);

	
	@Update("update student set email=#{email},phone=#{phone},sex=#{sex},hobby=#{hobby} where id=#{id} ")
	int updStudent(Student student);
	
	@Update("update student set password=#{arg0} where id=#{arg1} ")
	int updStudentPwd(String password, int id);
	
	
	@Delete("delete from student where id=#{arg0}")
	int delStudent(int id);
	
	
	
	
	
	
	@Results(value = { @Result(id = true, column = "id", property = "id"),
			@Result(column = "classes_id", property = "classesId"),
			@Result(column = "classes_id", property = "classes", one = @One(select = "com.cxx.mapper.ClassesMapper.selById")) })
	@Select("select * from student where phone=#{arg0}")
	List<Student> selStudentByPhone(String phone);
	
	
	
	
	@Select("select count(*) from student where email=#{arg0} and id != #{arg1}")
	int selIfSameEmail(String email, int id);
	
	
	@Select("select count(*) from student where phone=#{arg0} and id != #{arg1}")
	int selIfSamePhone(String phone, int id);
	
	
	//查询在线或不在线状态人数
	@Select("select count(*) from student where status=#{arg0}")
	int selStuInlineOrNotCount(int status);
	
	
	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column="student_id",property="studentId"),
			@Result(column = "student_id", property = "clubMembers", many=@Many(select = "com.cxx.mapper.ClubMemberMapper.selByStudentId"))})
	@Select("select count(cm.club_id) as count,s.id student_id from student s  inner join club_member cm on student_id = s.id group by s.id ")
	List<StuClub> selStudentClub();
	
	
	
}
