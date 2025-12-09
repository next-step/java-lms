package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Answer {
    private Long id;

    private NsUser writer;
    private Long writerId;

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

    public Answer(long writerId, Question question, String contents) {
        this(null, writerId, question, contents);
    }

    public Answer(Long id, NsUser writer, Question question, String contents) {
        this.id = id;
        if(writer == null) {
            throw new UnAuthorizedException();
        }

        if(question == null) {
            throw new NotFoundException();
        }

        this.writer = writer;
        this.question = question;
        this.contents = contents;
    }

    public Answer(Long id, long writerId, Question question, String contents) {
        this.id = id;
        if(writerId <= 0L) {
            throw new UnAuthorizedException();
        }

        if(question == null) {
            throw new NotFoundException();
        }

        this.writerId = writerId;
        this.question = question;
        this.contents = contents;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void putOnDelete(long requesterId) {
//        if (!isOwner(requesterId)) {
//            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
//        }

        this.deleted = true;
    }

    public boolean isOwner(NsUser writer) {
        return this.writer.equals(writer);
    }

    public boolean isOwner(long writerId) {
        return this.writerId == writerId;
    }

    public Long getId() {
        return id;
    }

    public Answer setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public NsUser getWriter() {
        return writer;
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }
}
