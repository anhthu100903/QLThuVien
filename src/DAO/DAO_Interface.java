package DAO;

import java.util.List;

public interface DAO_Interface<T>{

    public int add(T t);
    public int update(T t);
    public int delete (String id);
    public List<T> selectAll ();
    public T selectById (String id);
}
