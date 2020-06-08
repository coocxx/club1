package com.cxx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import com.cxx.pojo.Admin;
import com.cxx.pojo.Club;
import com.cxx.sql.*;

public interface AdminMapper {
	
	/**
	 * 根据账号跟密码查询数据库是否存在该管理员信息
	 * @param admin
	 * @return
	 */
	@Select("select * from admin where name=#{name} and password=#{password}")
	Admin selByAdmin(Admin admin);
	
	/**
	 * 根据邮箱和管理员ID查找是否已经存在，除了本身以外的邮箱
	 * @param email
	 * @return
	 */
	@Select("select count(*) from admin where email=#{arg0} and id != #{arg1}")
	int selIfExitSameEmail(String email, int adminId);
	
	
	/**
	 * 根据电话，管理员ID查找是否已经存在，除了本身以外的电话
	 * @param phone
	 * @return
	 */
	@Select("select count(*) from admin where phone=#{arg0} and id != #{arg1}")
	int selIfExitSamePhone(String phone, int adminId);
	
	
	/**
	 * 根据邮箱和管理员ID查找是否已经存在
	 * @param email
	 * @return
	 */
	@Select("select * from admin where email=#{arg0}")
	List<Admin> selByEmail(String email);
	
	
	/**
	 * 根据电话，管理员ID查找是否已经存在
	 * @param phone
	 * @return
	 */
	@Select("select * from admin where phone=#{arg0}")
	List<Admin> selbyPhone(String phone);
	
	
	
	
	/**
	 * 根据账号id查询数据库是否存在该管理员信息
	 * @param
	 * @return
	 */
	@Select("select * from admin where id=#{arg0}")
	Admin selById(int id);
	
	
	/**
	 * 查询全部管理员账号
	 *
	 * @return
	 */
	@Select("select * from admin limit #{arg0},#{arg1}")
	List<Admin> selALLAdmins(int startPage, int pageSize);
	
	/**
	 * 查询有几条管理员账号信息
	 *
	 * @return
	 */
	@Select("select count(*) from admin")
	int selALLAdminCounts();
	
	
	/**
	 * 修改管理员信息
	 */
	@Update("update admin set password=#{password},sex=#{sex},phone=#{phone},email=#{email},name=#{name} where id=#{id}")
	int updAdmin(Admin admin);
	
	/**
	 * 添加管理员之前要判断名字是不是唯一的
	 */
	@Select("select count(*) from admin where name=#{name}")
	int selAdminSameName(Admin admin);
	
	
	/**
	 * 添加管理员之前要判断名字是不是唯一的
	 */
	@Select("select count(*) from admin where name=#{name} and id != #{id}")
	int selifSameName(Admin admin);
	
	
	/**
	 * 添加管理员
	 * @param admin
	 * @return
	 */
	@Insert("insert into admin values(default,#{name},#{password},#{sex},#{phone},#{email},sysdate())")
	int insAdmin(Admin admin);
	
	/**
	 * 删除管理员
	 * @param
	 * @return
	 */
	@Insert("delete from admin where id=#{arg0}")
	int delAdmin(int id);
	
	/**
	 * 根据条件查找管理员账户
	 */
	@SelectProvider(type=AdminDynSqlProvider.class,method="selAdmins")
	List<Admin> selAdmins(@Param("id") int id, @Param("name") String name, @Param("email") String email, @Param("phone") String phone, @Param("time") String time);
	
	/**
	 * 根据条件查找管理员账户数据一共有几条
	 */
	@SelectProvider(type=AdminDynSqlProvider.class,method="selCountAdmins")
	int selCountAdmins(Admin admin);
	
	

	
	
}
