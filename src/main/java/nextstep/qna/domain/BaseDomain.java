package nextstep.qna.domain;

import nextstep.qna.UnAuthenticationException;
import nextstep.qna.exception.Exceptions;
import nextstep.users.domain.NsUser;

public interface BaseDomain {

    boolean isOwner(NsUser loginUser);

    boolean isDeleted();

    void changeDeleteStatus(YN deleteYN);

    default void validateWriter(NsUser loginUser) throws UnAuthenticationException {
        if (!isOwner(loginUser)) {
            throw new UnAuthenticationException(Exceptions.NOT_EXIST_AUTHENTICATION.message());
        }
    }
}
