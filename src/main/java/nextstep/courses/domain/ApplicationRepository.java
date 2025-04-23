package nextstep.courses.domain;

public interface ApplicationRepository {
    int save(Application application);

    Application findById(Long id);
}
