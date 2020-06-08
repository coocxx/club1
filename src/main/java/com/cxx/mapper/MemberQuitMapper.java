package com.cxx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.cxx.pojo.MemberJoin;
import com.cxx.pojo.MemberQuit;

public interface MemberQuitMapper {
	
	/**
	 * 动态查询
	 * @return
	 */
	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "student_id", property = "studentId"), 
			@Result(column = "club_id", property = "clubId"),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")),
			@Result(column = "club_id", property = "club", one = @One(select = "com.cxx.mapper.ClubMapper.selById")) })
	@Select("select * from member_quit")
	List<MemberQuit> selAllMemberQuit();
	
	
	
	/**
	 * 分页查询
	 * @return
	 */
	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "student_id", property = "studentId"), 
			@Result(column = "club_id", property = "clubId"),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")),
			@Result(column = "club_id", property = "club", one = @One(select = "com.cxx.mapper.ClubMapper.selById")) })
	@Select("select * from member_quit limit #{arg0},#{arg1}")
	List<MemberQuit> selMemberQuitByPage(int startPage, int pageSize);
	
	
	/**
	 * 分页查询指定社团的
	 * @return
	 */
	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "student_id", property = "studentId"), 
			@Result(column = "club_id", property = "clubId"),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")),
			@Result(column = "club_id", property = "club", one = @One(select = "com.cxx.mapper.ClubMapper.selById")) })
	@Select("select * from member_quit where club_id=#{arg2} limit #{arg0},#{arg1}")
	List<MemberQuit> selMemberQuitOfClubByPage(int startPage, int pageSize, int clubId);
	
	
	@Select("select count(*) from member_quit")
	int selMemberQuitCount();
	
	@Select("select count(*) from member_quit where club_id=#{arg0}")
	int selCountOfClub(int clubId);
	
	
	
	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "student_id", property = "studentId"), 
			@Result(column = "club_id", property = "clubId"),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")),
			@Result(column = "club_id", property = "club", one = @One(select = "com.cxx.mapper.ClubMapper.selById")) })
	@SelectProvider(type = com.cxx.sql.AdminDynSqlProvider.class, method = "selMemberQuits")
	List<MemberQuit> selMemberQuits(@Param("active") String active, @Param("id") int id, @Param("studentId") int studentId,
                                    @Param("studentName") String studentName, @Param("clubId") int clubId, @Param("clubName") String clubName,
                                    @Param("time") String time);
	
	
	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "student_id", property = "studentId"), 
			@Result(column = "club_id", property = "clubId"),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")),
			@Result(column = "club_id", property = "club", one = @One(select = "com.cxx.mapper.ClubMapper.selById")) })
	@Select("select * from member_quit where id=#{arg0}")
	MemberQuit selById(int id);
	
	
	@Delete("delete from member_quit where id=#{arg0}")
	int delMemberQuitById(int id);
	
	@Insert("insert into member_quit values(default,#{arg0},#{arg1},sysdate())")
	int insMemberQuit(int studentId, int clubId);
	
	
}
