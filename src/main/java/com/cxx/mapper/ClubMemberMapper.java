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

import com.cxx.pojo.ClubMember;
import com.cxx.pojo.StuClub;
import com.cxx.pojo.Student;

public interface ClubMemberMapper {

	/**
	 * 用户登录
	 * 
	 * @param
	 * @return
	 */
	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "student_id", property = "studentId"), @Result(column = "club_id", property = "clubId"),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")),
			@Result(column = "club_id", property = "club", one = @One(select = "com.cxx.mapper.ClubMapper.selById")) })
	@Select("select * from club_member limit #{arg0},#{arg1}")
	List<ClubMember> selClubMembersByPage(int startPage, int pageSize);

	
	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "student_id", property = "studentId"), @Result(column = "club_id", property = "clubId"),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")),
			@Result(column = "club_id", property = "club", one = @One(select = "com.cxx.mapper.ClubMapper.selById")) })
	@Select("select * from club_member where id=#{arg0}")
	ClubMember selById(int id);
	
	
	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "student_id", property = "studentId"), @Result(column = "club_id", property = "clubId"),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")),
			@Result(column = "club_id", property = "club", one = @One(select = "com.cxx.mapper.ClubMapper.selById")) })
	@Select("select * from club_member where student_id=#{arg0}")
	ClubMember selByStudentId(int studentId);
	
	
	@Select("select count(*) from club_member where student_id = #{arg0} and club_id=#{arg1}")
	int selifStudentIdExit(int studentId, int clubId);
	
	@Select("select count(*) from club_member where  club_id=#{arg0} and status=#{arg1}")
	int selIfPresidentExit(int clubId, int status);
	
	
	
	@Select("select count(*) from club_member")
	int selAllCounts();

	@Delete("delete from club_member where id=#{arg0}")
	int delClubMember(int id);
	
	@Update("update club_member set time=#{time},status=#{status} where id=#{id}")
	int updClubMember(ClubMember clubMember);
	
	@Insert("insert into club_member values(default,#{student.id},#{club.id},sysdate(),#{status})")
	int insClubMember(ClubMember clubMember);
	
	

	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "student_id", property = "studentId"), @Result(column = "club_id", property = "clubId"),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")),
			@Result(column = "club_id", property = "club", one = @One(select = "com.cxx.mapper.ClubMapper.selById")) })
	@SelectProvider(type = com.cxx.sql.AdminDynSqlProvider.class, method = "selClubMembers")
	List<ClubMember> selClubMembers(@Param("active") String active, @Param("id") int id, @Param("studentId") int studentId,
                                    @Param("studentName") String studentName, @Param("clubId") int clubId, @Param("clubName") String clubName,
                                    @Param("time") String time, @Param("status") int status);

	
	
	//查询是否有这个学号存在，并且status=(1，及是会长)
	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "student_id", property = "studentId"), @Result(column = "club_id", property = "clubId"),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")),
			@Result(column = "club_id", property = "club", one = @One(select = "com.cxx.mapper.ClubMapper.selById")) })
	@Select("select * from club_member where student_id=#{arg0} and status=#{arg1}")
	ClubMember selPrsdtBySidStatus(int studendId, int status);
	
	
	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "student_id", property = "studentId"), @Result(column = "club_id", property = "clubId"),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")),
			@Result(column = "club_id", property = "club", one = @One(select = "com.cxx.mapper.ClubMapper.selById")) })
	@Select("select * from club_member where club_id=#{arg2} limit #{arg0},#{arg1}")
	List<ClubMember> selClubMemberOfClubByPage(int startPage, int pageSize, int clubId);
	
	@Select("select count(*) from club_member where club_id=#{arg0}")
	int selCountOfClub(int clubId);
	
	@Select("select count(*) from club_member where student_id=#{arg0} and club_id=#{arg1}")
	int ifClubMember(int studentId, int clubId);
	
	
}
