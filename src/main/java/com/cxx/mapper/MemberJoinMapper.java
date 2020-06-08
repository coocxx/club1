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
import org.apache.ibatis.annotations.Update;

import com.cxx.pojo.ClubMember;
import com.cxx.pojo.MemberJoin;
import com.cxx.pojo.MemberQuit;
import com.sun.mail.iap.Literal;

public interface MemberJoinMapper {
	
	/**查询全部学生申请加入社团信息
	 * 
	 * @return
	 */
	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "student_id", property = "studentId"), 
			@Result(column = "club_id", property = "clubId"),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")),
			@Result(column = "club_id", property = "club", one = @One(select = "com.cxx.mapper.ClubMapper.selById")) })
	@Select("select * from member_join")
	List<MemberJoin> selAllMemberJoin();
	
	
	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "student_id", property = "studentId"), 
			@Result(column = "club_id", property = "clubId"),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")),
			@Result(column = "club_id", property = "club", one = @One(select = "com.cxx.mapper.ClubMapper.selById")) })
	@Select("select * from member_join limit #{arg0},#{arg1}")
	List<MemberJoin> selMemberJoinByPage(int starPage, int pageSize);
	
	
	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "student_id", property = "studentId"), 
			@Result(column = "club_id", property = "clubId"),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")),
			@Result(column = "club_id", property = "club", one = @One(select = "com.cxx.mapper.ClubMapper.selById")) })
	@Select("select * from member_join where club_id=#{arg2} limit #{arg0},#{arg1}")
	List<MemberJoin> selMemberJoinofClubByPage(int starPage, int pageSize, int clubId);
	
	
	@Select("select count(*) from member_join")
	int selAllCounts();
	
	@Select("select count(*) from member_join where club_id=#{arg0}")
	int selAllCountsOfClub(int clubId);
	

	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "student_id", property = "studentId"), 
			@Result(column = "club_id", property = "clubId"),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")),
			@Result(column = "club_id", property = "club", one = @One(select = "com.cxx.mapper.ClubMapper.selById")) })
	@SelectProvider(type = com.cxx.sql.AdminDynSqlProvider.class, method = "selMemberJoins")
	List<MemberJoin> selMemberJoins(@Param("active") String active, @Param("id") int id, @Param("studentId") int studentId,
                                    @Param("studentName") String studentName, @Param("clubId") int clubId, @Param("clubName") String clubName,
                                    @Param("time") String time, @Param("status") int status);
	
	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "student_id", property = "studentId"), 
			@Result(column = "club_id", property = "clubId"),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")),
			@Result(column = "club_id", property = "club", one = @One(select = "com.cxx.mapper.ClubMapper.selById")) })
	@Select("select * from member_join where id=#{arg0}")
	MemberJoin selById(int id);
	
	
	@Delete("delete from member_join where id=#{arg0}")
	int delMemberJoinById(int id);
	
	@Update("update member_join set status=#{arg0} where id=#{arg1}")
	int updateStatus(int status, int id);
	
	
	
	
	
	
	/**
	 * 动态查询待审核状态下的申请成员信息
	 * @param id
	 * @param studentId
	 * @param studentName
	 * @param clubId
	 * @param clubName
	 * @param time
	 * @param
	 * @return
	 */
	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "student_id", property = "studentId"), 
			@Result(column = "club_id", property = "clubId"),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")),
			@Result(column = "club_id", property = "club", one = @One(select = "com.cxx.mapper.ClubMapper.selById")) })
	@SelectProvider(type = com.cxx.sql.AdminDynSqlProvider.class, method = "selAppMembers")
	List<MemberJoin> selAppMembers(@Param("id") int id, @Param("studentId") int studentId,
                                   @Param("studentName") String studentName, @Param("clubId") int clubId, @Param("clubName") String clubName,
                                   @Param("time") String time);
	
	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "student_id", property = "studentId"), 
			@Result(column = "club_id", property = "clubId"),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")),
			@Result(column = "club_id", property = "club", one = @One(select = "com.cxx.mapper.ClubMapper.selById")) })
	@Select("select * from member_join  where status=2 limit #{arg0},#{arg1}")
	List<MemberJoin> selAppMemberByPage(int starPage, int pageSize);
	
	
	@Select("select count(*) from member_join where status=2")
	int selAppMemberCounts();
	
	
	
	/**
	 * 加入社团申请
	 * @param studentId
	 * @param clubId
	 * @return
	 */
	@Insert("insert into member_join values(default,#{arg0},#{arg1},2,sysdate())")
	int insMemberJoin(int studentId, int clubId);
	
	@Select("select count(*) from member_join where student_id=#{arg0} and club_id=#{arg1} and status=#{arg2}")
	int selIfMemberExitByStatus(int studentId, int clubId, int status);
	
	
	
	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "student_id", property = "studentId"), 
			@Result(column = "club_id", property = "clubId"),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")),
			@Result(column = "club_id", property = "club", one = @One(select = "com.cxx.mapper.ClubMapper.selById")) })
	@Select("select * from member_join where student_id=#{arg0}")
	List<MemberJoin> selMemberJoinByStudentId(int studentId);
	
}
