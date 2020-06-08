package com.cxx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.cxx.pojo.Academy;
import com.cxx.pojo.Classes;

public interface ClassesMapper {

	/**
	 * 查询全部班级信息
	 * @return
	 */
	@Results(value= {
			@Result(id=true,column="id",property="id"),
			@Result(column="major_id",property="majorId"),
			@Result(column="major_id",property="major",one=@One(select="com.cxx.mapper.MajorMapper.selById"))
	})
	@Select("select * from classes where id=#{arg0}")
	Classes selById(int id);
	
	
	@Results(value= {
			@Result(id=true,column="id",property="id"),
			@Result(column="major_id",property="majorId"),
			@Result(column="major_id",property="major",one=@One(select="com.cxx.mapper.MajorMapper.selById"))
	})
	@Select("select * from classes")
	List<Classes> selAllClasses();
	

	@Results(value= {
			@Result(id=true,column="id",property="id"),
			@Result(column="major_id",property="majorId"),
			@Result(column="major_id",property="major",one=@One(select="com.cxx.mapper.MajorMapper.selById"))
	})
	@Select("select * from classes limit #{arg0},#{arg1}")
	List<Classes> selClassesByPage(int starPage, int pageSize);
	
	@Select("select count(*) from classes")
	int selCLassesCount();
	/**
	 * 动态查询所有满足条件的数据
	 * @return
	 */
	@Results(value= {
			@Result(id=true,column="id",property="id"),
			@Result(column="major_id",property="majorId"),
			@Result(column="major_id",property="major",one=@One(select="com.cxx.mapper.MajorMapper.selById"))
	})
	@SelectProvider(type=com.cxx.sql.AdminDynSqlProvider.class,method="selClasses")
	List<Classes> selClasses(@Param("id") int id, @Param("name") String name, @Param("academyId") int academyId, @Param("academyName") String academyName, @Param("majorId") int majorId, @Param("majorName") String majorName);
	
}
