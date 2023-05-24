package nextstep.courses.domain;

public interface CartRepository {

    Session findById(Long id);

    void save(Session session);
}
