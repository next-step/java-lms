package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

abstract public class Content {
    protected Long id;
    protected NsUser writer;
    protected String contents;
    protected boolean deleted = false;
    protected LocalDateTime createdDate = LocalDateTime.now();
    protected LocalDateTime updatedDate;

    public Content() {
    }

    public Content(Long id, NsUser writer, String contents) {
        this.id = id;
        this.writer = writer;
        this.contents = contents;
    }

    public Content setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public NsUser getWriter() {
        return writer;
    }

    public Long getId() {
        return id;
    }

    public String getContents() {
        return contents;
    }
}
