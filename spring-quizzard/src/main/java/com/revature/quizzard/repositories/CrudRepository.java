package com.revature.quizzard.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {

    List<T> findAll();
    Optional<T> findById(int id);
    Optional<T> save(T newObj);
    boolean update(T updatedObj);
    boolean deleteById(int id);

}
