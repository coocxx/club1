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

import com.cxx.pojo.MemberJoin;
import com.cxx.pojo.Notice;

public interface NoticeMapper {

	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "club_id", property = "clubId"),
			@Result(column = "club_id", property = "club", one = @One(select = "com.cxx.mapper.ClubMapper.selById")) })
	@SelectProvider(type = com.cxx.sql.AdminDynSqlProvider.class, method = "selNotices")
	List<Notice> selNotices(@Param("action") String action, @Param("id") int id, @Param("clubId") int clubId, @Param("clubName") String clubName,
                            @Param("time") String time, @Param("title") String title);
	
	
	
	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "club_id", property = "clubId"),
			@Result(column = "club_id", property = "club", one = @One(select = "com.cxx.mapper.ClubMapper.selById")) })
	@Select("select * from notice")
	List<Notice> selAllNotices();
	
	
	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "club_id", property = "clubId"),
			@Result(column = "club_id", property = "club", one = @One(select = "com.cxx.mapper.ClubMapper.selById")) })
	@Select("select * from notice limit #{arg0},#{arg1}")
	List<Notice> selNoticeByPage(int startPage, int pageSize);
	
	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "club_id", property = "clubId"),
			@Result(column = "club_id", property = "club", one = @One(select = "com.cxx.mapper.ClubMapper.selById")) })
	@Select("select * from notice where id=#{arg0}")
	Notice selById(int id);
	
	
	
	@Select("select count(*) from notice")
	int selNoticeCounts();
	
	
	@Update("update notice set title=#{title},content=#{content},time=#{time} where id=#{id}")
	int updNotice(Notice notice);
	
	@Insert("insert into notice values(default,#{club.id},#{title},#{content},sysdate())")
	int insNotice(Notice notice);
	
	@Delete("delete from notice where id=#{arg0}")
	int delNoticeById(int id);
	
	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "club_id", property = "clubId"),
			@Result(column = "club_id", property = "club", one = @One(select = "com.cxx.mapper.ClubMapper.selById")) })
	
	@Select("select * from notice where title=#{arg0}")
	Notice selByTitle(String title);
	
	@Select("select count(*) from notice where title=#{arg0} and id != #{arg1}")
	int selIfSameTitleExit(String title, int id);
	
	
	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "club_id", property = "clubId"),
			@Result(column = "club_id", property = "club", one = @One(select = "com.cxx.mapper.ClubMapper.selById")) })
	@Select("select * from notice where club_id=#{arg2} limit #{arg0},#{arg1}")
	List<Notice> selNoticeByPageofClub(int startPage, int pageSize, int clubId);
	
	@Select("select count(*) from notice where club_id=#{arg0}")
	int selNoticeCountOfClub(int clubId);
	
	
	
	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "club_id", property = "clubId"),
			@Result(column = "club_id", property = "club", one = @One(select = "com.cxx.mapper.ClubMapper.selById")) })
	@Select("select * from notice where club_id=#{arg0}")
	List<Notice> selNoticeByClubId(int clubId);
	
	
	
}
