package com.yc.cinema.dao;

import java.util.List;

public interface BaseDao<E> {

	default int insert(E e) {
		return 0;
	}

	default int update(E e) {
		return 0;
	}

	default int delete(E e) {
		return 0;
	}

	default List<E> selectByObject(E e) {
		return null;
	}

	default E selectById(Object id) {
		return null;
	}
}
