package com.cxx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.cxx.pojo.ClubType;

public interface ClubTypeMapper {
	/**
	 * 根据社团类型id查询
	 */
	@Select("select * from club_type where id=#{arg0}")
	ClubType selById(int id);
	
	/**
	 * 查询全部社团类型信息
	 * @return
	 */
	@Select("select * from club_type")
	List<ClubType> selAllClubTypes();
	
	/**
	 * 查询社团类型数据总数
	 * @return
	 */
	@Select("select count(*) from club_type")
	int selClubTyAllCounts();
	
	/**
	 * 分页查询社团类型信息
	 * @param starPage
	 * @param psgeSize
	 * @return
	 */
	@Select("select * from club_type limit #{arg0},#{arg1}")
	List<ClubType> selClubTysByPage(int starPage, int psgeSize);
	
	@SelectProvider(type=com.cxx.sql.AdminDynSqlProvider.class,method="selClubTypes")
	List<ClubType> selClubTypes(@Param("id") int id, @Param("name") String name);
	
	/*@SelectProvider(type=com.cxx.sql.AdminDynSqlProvider.class,method="selCountClubTypes")
	List<ClubType> selCountClubTypes(@Param("id") int id,@Param("name") String name);*/
	
	@Insert("insert into club_type values(default,#{arg0})")
	int addClubType(String name);
	
	@Delete("delete from club_type where id=#{id}")
	int delClubType(ClubType clubType);
	
	
	@Update("update club_type set name=#{name} where id=#{id}")
	int updClubType(ClubType clubType);
	
	@Select("select * from club_type where name=#{arg0}")
	ClubType selByName(String name);
	
	
	@Select("select * from club_type where name=#{arg0} and id not in (#{arg1})")
	ClubType selIfSameName(String name, int id);
	
	
	
	
}
