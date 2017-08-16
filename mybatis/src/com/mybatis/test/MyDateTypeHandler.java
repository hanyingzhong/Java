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
	 * 注意在引入Date所在的包时，是java.util.Date，而不是java.sql.Date，这一点不要搞错。
	 * 
	 * @author turbo
	 *
	 *         2016年10月23日
	 */

	/*
	 * 根据列名，获取可以为空的结果
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
	 * 根据列索引，获取可以为空的结果
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
	 * 设置非空参数
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
