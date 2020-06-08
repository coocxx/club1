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

import com.cxx.pojo.Club;
import com.cxx.pojo.ClubApply;

public interface ClubApplyMapper {

	@Results(value = { @Result(id = true, column = "id", property = "id"),
			@Result(column = "student_id", property = "studentId"),
			@Result(column = "club_type_id", property = "clubTypeId"),
			@Result(column = "admin_id", property = "adminId"),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")),
			@Result(column = "club_type_id", property = "clubType", one = @One(select = "com.cxx.mapper.ClubTypeMapper.selById")),
			@Result(column = "admin_id", property = "admin", one = @One(select = "com.cxx.mapper.AdminMapper.selById")) })
	@Select("select * from club_apply limit #{arg0},#{arg1}")
	List<ClubApply> selClubApplyByPage(int starPage, int pageSize);

	@Results(value = { @Result(id = true, column = "id", property = "id"),
			@Result(column = "student_id", property = "studentId"),
			@Result(column = "club_type_id", property = "clubTypeId"),
			@Result(column = "admin_id", property = "adminId"),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")),
			@Result(column = "club_type_id", property = "clubType", one = @One(select = "com.cxx.mapper.ClubTypeMapper.selById")),
			@Result(column = "admin_id", property = "admin", one = @One(select = "com.cxx.mapper.AdminMapper.selById")) })
	@Select("select * from club_apply where id=#{arg0}")
	ClubApply selById(int id);

	@Update("update club_apply set status=#{arg0},admin_id=#{arg2},time=sysdate() where id=#{arg1}")
	int updStatus(int status, int id, int adminId);

	/**
	 * 动态查询全部申请信息
	 * 
	 * @return
	 */
	@Results(value = { @Result(id = true, column = "id", property = "id"),
			@Result(column = "student_id", property = "studentId"),
			@Result(column = "club_type_id", property = "clubTypeId"),
			@Result(column = "admin_id", property = "adminId"),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")),
			@Result(column = "club_type_id", property = "clubType", one = @One(select = "com.cxx.mapper.ClubTypeMapper.selById")),
			@Result(column = "admin_id", property = "admin", one = @One(select = "com.cxx.mapper.AdminMapper.selById")) })
	@SelectProvider(type = com.cxx.sql.AdminDynSqlProvider.class, method = "selClubApplies")
	List<ClubApply> selClubApplies(@Param("id") int id, @Param("studentId") int studentId,
                                   @Param("studentName") String studentName, @Param("clubTypeName") String clubTypeName,
                                   @Param("adminId") int adminId, @Param("name") String name, @Param("status") int status,
                                   @Param("time") String time);

	@Select("select count(*) from club_apply")
	int selClubApplyCount();

	@Delete("delete from club_apply where id=#{arg0}")
	int delClubApplyById(int id);

	@Results(value = { @Result(id = true, column = "id", property = "id"),
			@Result(column = "student_id", property = "studentId"),
			@Result(column = "club_type_id", property = "clubTypeId"),
			@Result(column = "admin_id", property = "adminId"),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")),
			@Result(column = "club_type_id", property = "clubType", one = @One(select = "com.cxx.mapper.ClubTypeMapper.selById")),
			@Result(column = "admin_id", property = "admin", one = @One(select = "com.cxx.mapper.AdminMapper.selById")) })
	@Select("select * from club_apply where status=#{arg2} limit #{arg0},#{arg1}")
	List<ClubApply> selAppClubByPage(int startPage, int pageSize, int status);

	@Select("select count(*) from club_apply where status=#{arg0}")
	int selAppClubCount(int status);

	/**
	 * 动态查询待审核状态的社团信息
	 * @param id
	 * @param studentId
	 * @param studentName
	 * @param clubTypeName
	 * @param adminId
	 * @param name
	 * @return
	 */
	@Results(value = { @Result(id = true, column = "id", property = "id"),
			@Result(column = "student_id", property = "studentId"),
			@Result(column = "club_type_id", property = "clubTypeId"),
			@Result(column = "admin_id", property = "adminId"),
			@Result(column = "student_id", property = "student", 
			one = @One(select = "com.cxx.mapper.StudentMapper.selById")),
			@Result(column = "club_type_id", property = "clubType", 
			one = @One(select = "com.cxx.mapper.ClubTypeMapper.selById")),
			@Result(column = "admin_id", property = "admin", 
			one = @One(select = "com.cxx.mapper.AdminMapper.selById")) })
	@SelectProvider(type = com.cxx.sql.AdminDynSqlProvider.class, method = "selAppClubs")
	List<ClubApply> selAppClubs(@Param("id") int id, @Param("studentId") int studentId,
                                @Param("studentName") String studentName, @Param("clubTypeName") String clubTypeName,
                                @Param("adminId") int adminId, @Param("name") String name);

	@Insert("insert into club_apply values(default,#{arg0},#{arg1},null,#{arg2},#{arg3},2,null)")
	int insClubApply(int studentId, int clubTypeId, String name, String reason);

	@Results(value = { @Result(id = true, column = "id", property = "id"),
			@Result(column = "student_id", property = "studentId"),
			@Result(column = "club_type_id", property = "clubTypeId"),
			@Result(column = "admin_id", property = "adminId"),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")),
			@Result(column = "club_type_id", property = "clubType", one = @One(select = "com.cxx.mapper.ClubTypeMapper.selById")),
			@Result(column = "admin_id", property = "admin", one = @One(select = "com.cxx.mapper.AdminMapper.selById")) })
	@Select("select * from club_apply where student_id=#{arg0}")
	List<ClubApply> selByStudentId(int studentId);

	@Results(value = { @Result(id = true, column = "id", property = "id"),
			@Result(column = "student_id", property = "studentId"),
			@Result(column = "club_type_id", property = "clubTypeId"),
			@Result(column = "admin_id", property = "adminId"),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")),
			@Result(column = "club_type_id", property = "clubType", one = @One(select = "com.cxx.mapper.ClubTypeMapper.selById")),
			@Result(column = "admin_id", property = "admin", one = @One(select = "com.cxx.mapper.AdminMapper.selById")) })
	@Select("select * from club_apply where student_id=#{arg0} and status=#{arg1}")
	List<ClubApply> selByStudentIdStatus(int studentId, int status);

}
