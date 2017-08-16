package com.mybatis.test;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class MyDateTypeHandler extends BaseTypeHandler<Date> {

	/**
	 * ע��������Date���ڵİ�ʱ����java.util.Date��������java.sql.Date����һ�㲻Ҫ���
	 * 
	 * @author turbo
	 *
	 *         2016��10��23��
	 */

	/*
	 * ������������ȡ����Ϊ�յĽ��
	 * 
	 * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.
	 * ResultSet, java.lang.String)
	 */
	@Override
	public Date getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String sqlTimetamp = rs.getString(columnName);
		if (null != sqlTimetamp) {
			return new Date(Long.valueOf(sqlTimetamp));
		}
		return null;
	}

	/*
	 * ��������������ȡ����Ϊ�յĽ��
	 * 
	 * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.
	 * ResultSet, int)
	 */
	@Override
	public Date getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		String sqlTimetamp = rs.getString(columnIndex);
		if (null != sqlTimetamp) {
			return new Date(Long.valueOf(sqlTimetamp));
		}
		return null;
	}

	/*
	 * 47 * @see
	 * org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.
	 * CallableStatement, int)
	 */
	@Override
	public Date getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String sqlTimetamp = cs.getString(columnIndex);
		if (null != sqlTimetamp) {
			return new Date(Long.valueOf(sqlTimetamp));
		}
		return null;
	}

	/*
	 * ���÷ǿղ���
	 * 
	 * @see org.apache.ibatis.type.BaseTypeHandler#setNonNullParameter(java.sql.
	 * PreparedStatement, int, java.lang.Object,
	 * org.apache.ibatis.type.JdbcType)
	 */
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setString(i, String.valueOf(parameter.getTime()));
	}
}
