package com.cxx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.cxx.pojo.News;

public interface NewsMapper {
	
	
	@SelectProvider(type = com.cxx.sql.AdminDynSqlProvider.class, method = "selNews")
	List<News> selNews(@Param("id") int id, @Param("people") String people,
                       @Param("time") String time, @Param("title") String title);
	@Select("select * from news limit #{arg0},#{arg1}")
	List<News> selNewsByPage(int starPage, int pageSize);
	
	@Select("select * from news")
	List<News> selAllNews();
	
	@Select("select count(*) from news")
	int selNewsCount();
	
	@Select("select * from news where id=#{arg0}")
	News selById(int id);
	
	@Insert("insert into news values(default,#{title},#{content},#{people},sysdate(),0)")
	int insNews(News news);
	
	@Delete("delete from news where id=#{arg0}")
	int delNewsById(int id);
	
	@Update("update news set title=#{title},content=#{content},people=#{people},time=#{time} where id=#{id}")
	int updnews(News news);
	
	@Select("select * from news where title=#{arg0}")
	News selBytitle(String title);
	
	@Select("select count(*) from news where title=#{arg0} and id != #{arg1}")
	int selIfSameTitleNewsExit(String title, int id);
	
}
