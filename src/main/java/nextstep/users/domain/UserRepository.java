package nextstep.users.domain;

public interface UserRepository {
    long save(NsUser nsUser);

    NsUser findByUserId(String userId);
}
