package repository;

import java.util.List;

public interface CrudRepository<T> {
    T create(T t);
    T getById(int id);
    List<T> getAll();
    boolean update(int id, T t);
    boolean delete(int id);
}
