package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.List;
import nextstep.qna.UnAuthenticationException;
import nextstep.qna.exception.QnAExceptionCode;
import nextstep.qna.exception.QnAException;
import nextstep.users.domain.NsUser;

public abstract class AbstractQnA {

    protected NsUser writer;

    protected boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    public abstract List<DeleteHistory> delete(NsUser loginUser) throws UnAuthenticationException;

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void changeDeleteStatus(boolean deleteYN) {
        this.deleted = deleteYN;
    }

    public void validateWriter(NsUser loginUser) throws UnAuthenticationException {
        if (!isOwner(loginUser)) {
            throw new QnAException(QnAExceptionCode.NOT_EXIST_AUTHENTICATION);
        }
    }
}
