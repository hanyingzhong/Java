package com.mybatis.test;

/**
 * @author turbo
 *
 *         2016Äê10ÔÂ23ÈÕ
 */
public interface NoteMapper {
	Note queryNote(int id);

	void insertNote(Note note);
}