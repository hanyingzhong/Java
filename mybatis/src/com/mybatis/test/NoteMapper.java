package com.mybatis.test;

import java.util.List;

/**
 * @author turbo
 *
 *         2016年10月23日
 */
public interface NoteMapper {
	Note queryNote(int id);

	void insertNote(Note note);
	
	List<Note> getAll();
}
