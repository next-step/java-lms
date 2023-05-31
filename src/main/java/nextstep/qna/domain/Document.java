package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Document {
    Long id;
    ContentType contentType;
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

    public Document setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }
}
