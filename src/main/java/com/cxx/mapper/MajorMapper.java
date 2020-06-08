package com.cxx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;

import com.cxx.pojo.Academy;
import com.cxx.pojo.Major;

public interface MajorMapper {
	
	
	@Results(value= {
			@Result(id=true,column="id",property="id"),
			@Result(column="name",property="name"),
			@Result(column="academy_id",property="academyId"),
			@Result(column="academy_id",property="academy",one=@One(select="com.cxx.mapper.AcademyMapper.selById"))
	})
	@Select("select * from major where id=#{arg0}")
	Major selById(int id);
	
	
	@Results(value= {
			@Result(id=true,column="id",property="id"),
			@Result(column="academy_id",property="academyId"),
			@Result(column="academy_id",property="academy",one=@One(select="com.cxx.mapper.AcademyMapper.selById"))
	})
	@Select("select * from major limit #{arg0},#{arg1}")
	List<Major> selMajorByPage(int starPage, int pageSize);
	
	@Select("select count(*) from major")
	int selMajorCount();
	
	/**
	 * 动态查询所有满足条件的数据
	 * @return
	 */
	@Results(value= {
			@Result(id=true,column="id",property="id"),
			@Result(column="academy_id",property="academyId"),
			@Result(column="academy_id",property="academy",one=@One(select="com.cxx.mapper.AcademyMapper.selById"))
	})
	@SelectProvider(type=com.cxx.sql.AdminDynSqlProvider.class,method="selMajors")
	List<Major> selMajors(@Param("id") int id, @Param("name") String name, @Param("academyId") int academyId, @Param("academyName") String academyName);
	
	
}
