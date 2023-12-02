package nextstep.qna.infrastructure.entity;

import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class AnswerEntity {
    private Long id;

    private NsUser writer;

    private QuestionEntity questionEntity;

    private String contents;

    private boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    public AnswerEntity() {
    }

    public AnswerEntity(NsUser writer, QuestionEntity questionEntity, String contents) {
        this(null, writer, questionEntity, contents);
    }

    public AnswerEntity(Long id, NsUser writer, QuestionEntity questionEntity, String contents) {
        this.id = id;
        if(writer == null) {
            throw new UnAuthorizedException();
        }

        if(questionEntity == null) {
            throw new NotFoundException();
        }

        this.writer = writer;
        this.questionEntity = questionEntity;
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    public AnswerEntity setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
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

    public String getContents() {
        return contents;
    }

    public QuestionEntity getQuestionEntity() {
        return questionEntity;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void toQuestion(QuestionEntity questionEntity) {
        this.questionEntity = questionEntity;
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }
}
