package nextstep.session.domain;

public interface SessionImageRepository {
    void save(Session session);

    SessionImage findById(Long id);

}
