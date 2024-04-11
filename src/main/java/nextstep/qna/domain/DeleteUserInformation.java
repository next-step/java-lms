package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class DeleteUserInformation {
    private long id;
    private NsUser writer;
    private LocalDateTime createdDate;;

    public DeleteUserInformation(Question question) {
        this.id = question.getId();
        this.writer = question.getWriter();
        this.createdDate = LocalDateTime.now();
    }

    public DeleteUserInformation(Answer answer) {
        this.id = answer.getId();
        this.writer = answer.getWriter();
    }

    public long getId() {
        return id;
    }

    public NsUser getWriter() {
        return writer;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
}
