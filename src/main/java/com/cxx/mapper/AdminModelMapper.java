package com.cxx.mapper;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.cxx.pojo.AdminModel;

public interface AdminModelMapper {
	
	@Results(value= {
			@Result(id=true,column="id",property="id"),
			@Result(column="admin_id",property="adminId"),
			@Result(column="admin_id",property="admin",one=@One(select="com.cxx.mapper.AdminMapper.selById"))
	})
	@Select("select * from admin_model where admin_id=#{arg0} and status=#{arg1} and type=#{arg2}")
	AdminModel selByAdminIdStatusType(int adminId, int status, int type);
	
	
	
	
}
