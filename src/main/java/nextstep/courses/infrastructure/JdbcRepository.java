package nextstep.courses.infrastructure;

import org.springframework.stereotype.Repository;

@Repository
public interface JdbcRepository<T> {
    int save(T entity);

    T findById(Long id);
}
