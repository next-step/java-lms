package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public abstract class Post {

    protected Long id;

    protected NsUser writer;

    protected String contents;

    protected boolean deleted = false;

    protected LocalDateTime createdDate = LocalDateTime.now();

    protected LocalDateTime updatedDate;

    public abstract DeleteHistory makeDeleteHistory();

    public Long getId() {
        return id;
    }

    public NsUser getWriter() {
        return writer;
    }
}
