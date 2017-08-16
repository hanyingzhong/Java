package com.mybatis.test;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.hibernate.SessionFactory;

public class MybatisMapperTest {
	/**
	 * 客户端
	 * @author turbo
	 *
	 * 2016年9月11日
	 */
	    /**
	     * @param args
	     * https://github.com/lenve/JavaEETest/blob/master/Test27-mybatis6/src/main/resources/userMapper.xml
	     * @throws IOException 
	     */
	    public static void main(String[] args) throws Exception {
			String resource = "mybatis-config.xml";

			Reader reader = Resources.getResourceAsReader(resource);

			SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

			SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(reader);

			SqlSession session = sqlSessionFactory.openSession();
	        
	        NoteMapper noteMapper = session.getMapper(NoteMapper.class);
	        	        
	        String str = String.valueOf((new Date()).getTime());
	        
	        Note note = new Note();
	        note.setId(1001);
	        note.setDate(new Date());
	        noteMapper.insertNote(note);    //插入
	        session.commit();    //注意需要手动提交事务
	        
	        note = noteMapper.queryNote(1);    //查询
	        System.out.println(note.getDate());

	        note = noteMapper.queryNote(100);    //查询
	        if(note == null){
		        System.out.println("no record..");	        	
	        }
	        session.close();
	    }

}
