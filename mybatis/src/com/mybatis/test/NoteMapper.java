package com.mybatis.test;

/**
 * @author turbo
 *
 *         2016��10��23��
 */
public interface NoteMapper {
	Note queryNote(int id);

	void insertNote(Note note);
}