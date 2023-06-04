package nextstep.qna.domain;

import nextstep.common.BaseEntity;
import nextstep.qna.exception.CannotDeleteException;
import nextstep.qna.exception.NotFoundException;
import nextstep.qna.exception.UnAuthorizedException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Answer extends BaseEntity {
    private final Long id;

    private final NsUser writer;

    private Question question;

    private AnswerContents answerContents;

    private boolean deleted = false;

    public Answer(Long id, NsUser writer, Question question, AnswerContents answerContents) {
        validateWriter(writer);
        validateQuestion(question);
        this.id = id;
        this.writer = writer;
        this.question = question;
        this.answerContents = answerContents;
    }

    private void validateQuestion(Question question) {
        if(question == null) {
            throw new NotFoundException();
        }
    }

    private void validateWriter(NsUser writer) {
        if(writer == null) {
            throw new UnAuthorizedException();
        }
    }

    public DeleteHistory toHistory() {
        return new DeleteHistory(ContentType.ANSWER, id, writer, LocalDateTime.now());
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    public void delete() throws CannotDeleteException {
        validateIfQuestionerIsSameAsAnswerer();
        this.deleted = true;
    }

    private void validateIfQuestionerIsSameAsAnswerer() throws CannotDeleteException {
        if (!isOwner(question.getWriterId())) {
            throw new CannotDeleteException("질문자와 답변자가 다른 경우 답변을 삭제할 수 없습니다.");
        }
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isOwner(Long writerId) {
        return this.writer.getId().equals(writerId);
    }

    public Long getId() {
        return id;
    }

    public NsUser getWriter() {
        return writer;
    }
}
