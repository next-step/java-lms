package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Post {
    protected Long id;
    protected NsUser writer;
    protected String contents;
    protected boolean deleted = false;
    protected LocalDateTime createdDate = LocalDateTime.now();
    protected LocalDateTime updatedDate;

    public Post() {
    }

    public Post(Long id, NsUser writer, String contents) {
        this(id, writer, contents, false, LocalDateTime.now(), null);
    }

    public Post(Long id, NsUser writer, String contents, boolean deleted, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.writer = writer;
        this.contents = contents;
        this.deleted = deleted;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Long getId() {
        return id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isOwner(NsUser writer) {
        return this.writer.equals(writer);
    }

    public NsUser getWriter() {
        return writer;
    }
}
