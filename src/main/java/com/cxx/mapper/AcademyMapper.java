package com.cxx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.cxx.pojo.Academy;
/**
 * 学生学院
 * @author Cool
 *
 */
public interface AcademyMapper {
	
	@Results(value= {
			@Result(id=true,column="id",property="id"),
			@Result(column="name",property="name")
	})
	@Select("select * from academy where id=#{arg0}")
	Academy selById(int id);
	
	@Select("select * from academy limit #{arg0},#{arg1}")
	List<Academy> selAcademyByPage(int starPage, int pageSize);
	
	@Select("select count(*) from academy")
	int selAcademyCount();
	
	/**
	 * 动态查询所有满足条件的数据
	 * @return
	 */
	@SelectProvider(type=com.cxx.sql.AdminDynSqlProvider.class,method="selAcademys")
	List<Academy> selAcademys(@Param("id") int id, @Param("name") String name);
	
	
}
