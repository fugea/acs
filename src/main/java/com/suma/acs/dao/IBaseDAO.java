package com.suma.acs.dao;

import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IBaseDAO<T, PK> {

    public void save(T entity);

    public void deleteByPK(PK pk);

    public T findByPK(PK pk);

    public List<T> find(Query query);

    public List<T> findByProperties(T t);

    public T get(String id, String collectionName);

    public void update(String id, T entity);
}
