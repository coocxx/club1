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

import com.cxx.pojo.Event;
import com.cxx.pojo.Student;

public interface EventMapper {

	/**
	 * 动态查询所有满足条件的数据
	 * 
	 * @return
	 */
	@Results(value = { @Result(id = true, column = "id", property = "id"),
			@Result(column = "start_time", property = "startTime"),
			@Result(column = "end_time", property = "endTime"),
			@Result(column = "app_time", property = "appTime"),
			@Result(column = "sub_time", property = "subTime"),
			@Result(column = "student_id", property = "studentId"), @Result(column = "club_id", property = "clubId"),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")),
			@Result(column = "club_id", property = "club", one = @One(select = "com.cxx.mapper.ClubMapper.selById")) })
	@SelectProvider(type = com.cxx.sql.AdminDynSqlProvider.class, method = "selEvents")
	List<Event> selEvents(@Param("action") String action, @Param("id") int id, @Param("name") String name, @Param("clubId") int clubId,
                          @Param("clubName") String clubName, @Param("studentId") int studentId,
                          @Param("studentName") String studentName, @Param("place") String place,
                          @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("appTime") String appTime,
                          @Param("subTime") String subTime, @Param("status") int status);

	
	@Results(value = { @Result(id = true, column = "id", property = "id"),
			@Result(column = "start_time", property = "startTime"),
			@Result(column = "end_time", property = "endTime"),
			@Result(column = "app_time", property = "appTime"),
			@Result(column = "sub_time", property = "subTime"),
			@Result(column = "student_id", property = "studentId"), @Result(column = "club_id", property = "clubId"),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")),
			@Result(column = "club_id", property = "club", one = @One(select = "com.cxx.mapper.ClubMapper.selById")) })
	@Select("select * from event")
	List<Event> selAllEvents();
	
	
	@Results(value = { @Result(id = true, column = "id", property = "id"),
			@Result(column = "start_time", property = "startTime"),
			@Result(column = "end_time", property = "endTime"),
			@Result(column = "app_time", property = "appTime"),
			@Result(column = "sub_time", property = "subTime"),
			@Result(column = "student_id", property = "studentId"), @Result(column = "club_id", property = "clubId"),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")),
			@Result(column = "club_id", property = "club", one = @One(select = "com.cxx.mapper.ClubMapper.selById")) })
	@Select("select * from event limit #{arg0},#{arg1}")
	List<Event> selAllEventByPage(int startPage, int pageSize);
	
	@Results(value = { @Result(id = true, column = "id", property = "id"),
			@Result(column = "start_time", property = "startTime"),
			@Result(column = "end_time", property = "endTime"),
			@Result(column = "app_time", property = "appTime"),
			@Result(column = "sub_time", property = "subTime"),
			@Result(column = "student_id", property = "studentId"), @Result(column = "club_id", property = "clubId"),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")),
			@Result(column = "club_id", property = "club", one = @One(select = "com.cxx.mapper.ClubMapper.selById")) })
	@Select("select * from event where club_id=#{arg2} limit #{arg0},#{arg1}")
	List<Event> selAllEventByPageOfClub(int startPage, int pageSize, int clubId);
	
	
	@Select("select count(*) from event")
	int selEventCount();
	
	@Select("select count(*) from event where club_id=#{arg0}")
	int selEventCountOfClub(int clubId);
	
	
	@Results(value = { @Result(id = true, column = "id", property = "id"),
			@Result(column = "start_time", property = "startTime"),
			@Result(column = "end_time", property = "endTime"),
			@Result(column = "app_time", property = "appTime"),
			@Result(column = "sub_time", property = "subTime"),
			@Result(column = "student_id", property = "studentId"), @Result(column = "club_id", property = "clubId"),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")),
			@Result(column = "club_id", property = "club", one = @One(select = "com.cxx.mapper.ClubMapper.selById")) })
	@Select("select * from event where id=#{arg0}")
	Event selById(int id);
	
	@Update("update event set status=#{arg0},app_time=sysdate() where id=#{arg1}")
	int updEventStatus(int status, int id);
	
	@Delete("delete from event where id=#{arg0}")
	int delEventById(int id);
	
	@Insert("insert into event values(default,#{club.id},#{student.id},#{name},#{startTime},#{endTime},#{introduce},#{place},#{appTime},sysdate(),2)")
	int insEvent(Event event);
	
	@Update("update event set name=#{name},start_time=#{startTime},end_time=#{endTime},introduce=#{introduce},place=#{place},app_time=#{appTime},status=#{status} where id=#{id}")
	int updEvent(Event event);
	
	@Select("select * from event where name=#{arg0}")
	Event selByName(String name);
	
	@Select("select count(*) from event where name=#{arg0} and id != #{arg1}")
	int selIfSameNameExit(String name, int id);
	
	/**
	 * 动态查询待审核的活动
	 * @param id
	 * @param name
	 * @param clubId
	 * @param clubName
	 * @param studentId
	 * @param studentName
	 * @param place
	 * @param startTime
	 * @param endTime
	 * @param appTime
	 * @param subTime
	 * @param
	 * @return
	 */
	@Results(value = { @Result(id = true, column = "id", property = "id"),
			@Result(column = "start_time", property = "startTime"),
			@Result(column = "end_time", property = "endTime"),
			@Result(column = "app_time", property = "appTime"),
			@Result(column = "sub_time", property = "subTime"),
			@Result(column = "student_id", property = "studentId"), @Result(column = "club_id", property = "clubId"),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")),
			@Result(column = "club_id", property = "club", one = @One(select = "com.cxx.mapper.ClubMapper.selById")) })
	@SelectProvider(type = com.cxx.sql.AdminDynSqlProvider.class, method = "selAppEvents")
	List<Event> selAppEvents(@Param("id") int id, @Param("name") String name, @Param("clubId") int clubId,
                             @Param("clubName") String clubName, @Param("studentId") int studentId,
                             @Param("studentName") String studentName, @Param("place") String place,
                             @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("appTime") String appTime,
                             @Param("subTime") String subTime);

	@Results(value = { @Result(id = true, column = "id", property = "id"),
			@Result(column = "start_time", property = "startTime"),
			@Result(column = "end_time", property = "endTime"),
			@Result(column = "app_time", property = "appTime"),
			@Result(column = "sub_time", property = "subTime"),
			@Result(column = "student_id", property = "studentId"), @Result(column = "club_id", property = "clubId"),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")),
			@Result(column = "club_id", property = "club", one = @One(select = "com.cxx.mapper.ClubMapper.selById")) })
	@Select("select * from event where status=#{2} limit #{arg0},#{arg1}")
	List<Event> selAppEventByPage(int starPage, int pageSize, int status);
	
	
	 /**
	  * 查询未通过或已通过或拒绝的活动
	  * @param status
	  * @return
	  */
	@Select("select count(*) from event where status=#{arg0}")
	int selAppEventCounts(int status);
	
	
	
	@Results(value = { @Result(id = true, column = "id", property = "id"),
			@Result(column = "start_time", property = "startTime"),
			@Result(column = "end_time", property = "endTime"),
			@Result(column = "app_time", property = "appTime"),
			@Result(column = "sub_time", property = "subTime"),
			@Result(column = "student_id", property = "studentId"), @Result(column = "club_id", property = "clubId"),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")),
			@Result(column = "club_id", property = "club", one = @One(select = "com.cxx.mapper.ClubMapper.selById")) })
	@Select("select * from event where status=#{arg0}")
	List<Event> selAllEventByStatus(int status);
	
	
	@Results(value = { @Result(id = true, column = "id", property = "id"),
			@Result(column = "start_time", property = "startTime"),
			@Result(column = "end_time", property = "endTime"),
			@Result(column = "app_time", property = "appTime"),
			@Result(column = "sub_time", property = "subTime"),
			@Result(column = "student_id", property = "studentId"), @Result(column = "club_id", property = "clubId"),
			@Result(column = "student_id", property = "student", one = @One(select = "com.cxx.mapper.StudentMapper.selById")),
			@Result(column = "club_id", property = "club", one = @One(select = "com.cxx.mapper.ClubMapper.selById")) })
	@Select("select * from event where club_id=#{arg0} and status=#{arg1}")
	List<Event> selEventByClubIdAndStatus(int clubId, int status);
	
}
