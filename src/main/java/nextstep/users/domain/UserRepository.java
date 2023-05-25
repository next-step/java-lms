package nextstep.users.domain;

public interface UserRepository {
    NsUser findByUserId(String userId);
}
