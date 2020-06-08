package com.cxx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cxx.pojo.Receive;

public interface ReceiveMapper {
	
	@Select("select count(*) from receive where student_id=#{arg0} and status=#{arg1}")
	int selCountByStudentIdStatus(int studentId, int status);
	
	@Results(value= {
			@Result(id=true,column="id",property="id"),
			@Result(column="student_id",property="studentId"),
			@Result(column="student_id",property="student",one=@One(select="com.cxx.mapper.StudentMapper.selById"))
	})
	@Select("select * from receive where student_id=#{arg0} and status=#{arg1} order by time desc")
	List<Receive> selByStudentIdStatus(int studentId, int status);
	
	@Update("update receive set status=#{arg2} where student_id=#{arg0} and status=#{arg1}")
	int updRecieve(int studentId, int status, int newStatus);
	
	
	@Results(value= {
			@Result(id=true,column="id",property="id"),
			@Result(column="student_id",property="studentId"),
			@Result(column="student_id",property="student",one=@One(select="com.cxx.mapper.StudentMapper.selById"))
	})
	@Select("select * from receive where student_id=#{arg0} order by time desc")
	List<Receive> selByStudentId(int studentId);
	
	@Insert("insert into receive value(default,#{arg0},#{arg1},#{arg2},sysdate(),#{3})")
	int insRecieve(String content, int studentId, int status, int type);
	
}
