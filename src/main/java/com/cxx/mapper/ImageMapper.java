package com.cxx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.cxx.pojo.Image;

public interface ImageMapper {
	
	@Results(value= {
			@Result(column="id",property="id",id=true),
			@Result(column="club_id",property="clubId"),
			@Result(column="club_id",property="club",one=@One(select="com.cxx.mapper.ClubMapper.selById"))
	})
	@Select("select * from image where id=#{arg0}")
	Image selById(int id);
	
	@Results(value= {
			@Result(column="id",property="id",id=true),
			@Result(column="club_id",property="clubId"),
			@Result(column="club_id",property="club",one=@One(select="com.cxx.mapper.ClubMapper.selById"))
	})
	@Select("select * from image where club_id=#{arg0}")
	List<Image> selByClubId(int clubId);
	

	
	@Insert("insert into image values(default,#{arg0},#{arg1},#{arg2})")
	int insImage(String name, String url, int clubId);
	
	@Delete("delete from image where id=#{arg0}")
	int delImageById(int id);
}
