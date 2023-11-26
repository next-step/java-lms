package nextstep.courses.domain;

public interface ThumbnailRepository {
    Thumbnail findBySessionId(long sessionId);
}
