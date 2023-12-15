package nextstep.qna.domain;

import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Answer {
    private Long id;

    private NsUser writer;

    private Question question;

    private String contents;

    private boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    public Answer() {
    }

    public Answer(NsUser writer, Question question, String contents) {
        this(null, writer, question, contents);
    }

    public Answer(Long id, NsUser writer, Question question, String contents) {
        this.id = id;
        if (writer == null) {
            throw new UnAuthorizedException();
        }

        if (question == null) {
            throw new NotFoundException();
        }

        this.writer = writer;
        this.question = question;
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public NsUser getWriter() {
        return writer;
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer [getId=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }

    public boolean isDeletable(NsUser nsUser) {
        return writer.matchUser(nsUser);
    }

    public DeleteHistory delete(NsUser nsUser) {
        if (!isDeletable(nsUser)) {
            throw new UnAuthorizedException("질문을 삭제할 권한이 없습니다.");
        }
        System.out.println("a");
        this.deleted = true;

        return new DeleteHistory(ContentType.ANSWER, id, writer, LocalDateTime.now());
    }
}
