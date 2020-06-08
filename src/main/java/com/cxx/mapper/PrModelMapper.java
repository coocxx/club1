package com.cxx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cxx.pojo.PrModel;
import com.cxx.pojo.Receive;

public interface PrModelMapper {

	@Results(value= {
			@Result(id=true,column="id",property="id"),
			@Result(column="student_id",property="studentId"),
			@Result(column="student_id",property="student",one=@One(select="com.cxx.mapper.StudentMapper.selById"))
	})
	@Select("select * from pr_model where student_id=#{arg0} and status=#{arg1}")
	PrModel selByStudentIdStatus(int studentId, int status);
	
	
	@Update("update pr_model set content=#{content} where status=#{status} and student_id=#{studentId}")
	int updPrModel(PrModel prModel);
	
	@Insert("insert into pr_model values(default,#{content},#{studentId},#{status})")
	int insPrModel(PrModel prModel);
	
	@Select("select content from pr_model where student_id=#{arg0} and status=#{arg1}")
	String selContentByStudentId(int studentId, int status);
	
}
