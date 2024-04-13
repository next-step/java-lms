package nextstep.session.domain;

public interface CoverRepository {

    long save(Cover cover);

    Cover findById(long coverID);

    Cover findBySessionId(long sessionId);

    int updateDeleteStatus(long coverId, boolean deleteStatus);
}
