package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.List;
import nextstep.qna.UnAuthenticationException;
import nextstep.users.domain.NsUser;

public abstract class BaseDomainImpl implements BaseDomain {

    protected NsUser writer;

    protected boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    public abstract List<DeleteHistory> delete(NsUser loginUser) throws UnAuthenticationException;

    @Override
    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    @Override
    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public void changeDeleteStatus(YN deleteYN) {
        this.deleted = deleteYN.toBoolean();
    }

    @Override
    public void validateWriter(NsUser loginUser) throws UnAuthenticationException {
        BaseDomain.super.validateWriter(loginUser);
    }
}
