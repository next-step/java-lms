package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Content {
    private static final String ERR_DELETE_PERMISSION = "%s을 삭제할 권한이 없습니다.";

    ContentType type;
    Long id;
    String contents;
    NsUser writer;
    boolean deleted = false;
    LocalDateTime createdDate = LocalDateTime.now();
    LocalDateTime updatedDate;

    public Long getId() {
        return id;
    }

    public NsUser getWriter() {
        return writer;
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public boolean isDeleted() {
        return deleted;
    }

    protected void validateDeletePermission(NsUser user) throws CannotDeleteException {
        if (!this.isOwner(user)) {
            throw new CannotDeleteException(String.format(ERR_DELETE_PERMISSION, type.getName()));
        }
    }


}
