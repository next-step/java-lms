package nextstep.lms.repository;

import nextstep.lms.domain.LmsUser;

import java.util.Optional;

public interface LmsUserRepository {
    LmsUser findByUserId(String userId);

    int save(LmsUser lmsUser);

    LmsUser findById(Long creatorId);
}
