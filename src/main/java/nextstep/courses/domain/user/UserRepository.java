package nextstep.courses.domain.user;

public interface UserRepository {

    int save(User user);

    User findById(Long id);

    int update(User user);

    int delete(User user);

}
