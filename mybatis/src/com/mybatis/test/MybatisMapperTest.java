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
	 * �ͻ���
	 * @author turbo
	 *
	 * 2016��9��11��
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
	        noteMapper.insertNote(note);    //����
	        session.commit();    //ע����Ҫ�ֶ��ύ����
	        
	        note = noteMapper.queryNote(1);    //��ѯ
	        System.out.println(note.getDate());

	        note = noteMapper.queryNote(100);    //��ѯ
	        if(note == null){
		        System.out.println("no record..");	        	
	        }
	        session.close();
	    }

}
