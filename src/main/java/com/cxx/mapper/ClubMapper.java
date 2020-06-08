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

public interface ClubMapper {
	/**
	 * club社团
	 */

	/**
	 * 根据社团id查询
	 * 
	 * @param
	 * @param
	 * @return
	 */
	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "notice", property = "notice"), @Result(column = "student_id", property = "studentId"),
			@Result(column = "club_type_id", property = "clubTypeId"),
			@Result(column = "club_type_id", property = "clubType", one = @One(select = "com.cxx.mapper.ClubTypeMapper.selById")),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")) })
	@Select("select * from club where id=#{arg0}")
	Club selById(int id);

	/**
	 * 分页查询
	 * 
	 * @param starPage
	 * @param pageSize
	 * @return
	 */
	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "student_id", property = "studentId"),
			@Result(column = "club_type_id", property = "clubTypeId"),
			@Result(column = "club_type_id", property = "clubType", one = @One(select = "com.cxx.mapper.ClubTypeMapper.selById")),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")) })
	@Select("select * from club limit #{arg0},#{arg1}")
	List<Club> selClubByPage(int starPage, int pageSize);

	/**
	 * 查询社团总数据数
	 * 
	 * @return
	 */
	@Select("select count(*) from club")
	int selClubCount();

	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "student_id", property = "studentId"),
			@Result(column = "club_type_id", property = "clubTypeId"),
			@Result(column = "club_type_id", property = "clubType", one = @One(select = "com.cxx.mapper.ClubTypeMapper.selById")),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")) })
	@Select("select * from club")
	List<Club> selAllClubs();

	/**
	 * 查询是否含有指定名称的社团
	 * 
	 * @param club
	 * @return
	 */
	@Select("select * from club where name=#{name} and id not in (#{id})")
	Club selClubSameName(Club club);

	/**
	 * 删除
	 * 
	 * @param club
	 * @return
	 */
	@Delete("delete from club where id=#{id}")
	int delClub(Club club);

	/**
	 * 添加
	 * 
	 * @param club
	 * @return
	 */
	@Insert("insert into club values(default,#{clubTypeId},#{name},#{studentId},#{place},#{introduce},#{notice},sysdate(),#{status})")
	int insClub(Club club);

	/**
	 * 修改
	 * 
	 * @param club
	 * @return
	 */
	@Update("update club set place=#{place},introduce=#{introduce},notice=#{notice},status=#{status} where id=#{id}")
	int updClub(Club club);

	/**
	 * 按照条件查询
	 */
	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "student_id", property = "studentId"),
			@Result(column = "club_type_id", property = "clubTypeId"),
			@Result(column = "club_type_id", property = "clubType",
			one = @One(select = "com.cxx.mapper.ClubTypeMapper.selById")),
			@Result(column = "student_id", property = "student", 
			one = @One(select = "com.cxx.mapper.StudentMapper.selById")) })
	@SelectProvider(type = com.cxx.sql.AdminDynSqlProvider.class, method = "selClubs")
	List<Club> selClubs(@Param("id") int id, @Param("studentName") String studentName,
                        @Param("clubTypeName") String clubTypeName, @Param("place") String place,
                        @Param("name") String name, @Param("status") int status, @Param("time") String time);


	/**
	 * 更新社团状态
	 */
	@Update("update club set status=#{arg0} where id=#{arg1}")
	int updStatus(int status, int id);

	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "student_id", property = "studentId"),
			@Result(column = "club_type_id", property = "clubTypeId"),
			@Result(column = "club_type_id", property = "clubType", one = @One(select = "com.cxx.mapper.ClubTypeMapper.selById")),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")) })
	@Select("select * from club where name=#{arg0}")
	Club selByName(String name);

	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "student_id", property = "studentId"),
			@Result(column = "club_type_id", property = "clubTypeId"),
			@Result(column = "club_type_id", property = "clubType", one = @One(select = "com.cxx.mapper.ClubTypeMapper.selById")),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")) })
	@Select("select * from club where student_id=#{arg0}")
	Club selByStudentId(int studentId);

	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "student_id", property = "studentId"),
			@Result(column = "club_type_id", property = "clubTypeId"),
			@Result(column = "club_type_id", property = "clubType", one = @One(select = "com.cxx.mapper.ClubTypeMapper.selById")),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")) })
	@Select("select * from club where club_type_id=#{arg0}")
	List<Club> selClubsByClubTypeId(int clubTypeId);

	/**
	 * 
	 * @param clubTypeId
	 * @return
	 */
	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "student_id", property = "studentId"),
			@Result(column = "club_type_id", property = "clubTypeId"),
			@Result(column = "club_type_id", property = "clubType", one = @One(select = "com.cxx.mapper.ClubTypeMapper.selById")),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")) })
	@Select("select * from club where club_type_id=#{arg0} and status=#{arg1}")
	List<Club> selClubsByClubTypeIdAndStatus(int clubTypeId, int status);

	/**
	 * 查询正在运行或已经停止的社团
	 * 
	 * @param status
	 * @return
	 */
	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "student_id", property = "studentId"),
			@Result(column = "club_type_id", property = "clubTypeId"),
			@Result(column = "club_type_id", property = "clubType", one = @One(select = "com.cxx.mapper.ClubTypeMapper.selById")),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")) })
	@Select("select * from club where status=#{arg0}")
	List<Club> selAllRunOrNotClub(int status);

	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "student_id", property = "studentId"),
			@Result(column = "club_type_id", property = "clubTypeId"),
			@Result(column = "club_type_id", property = "clubType", one = @One(select = "com.cxx.mapper.ClubTypeMapper.selById")),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")) })
	@Select("select * from club group by student_id")
	List<Club> selOneStudentMoreClubs();

}
