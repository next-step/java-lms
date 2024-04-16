package nextstep.qna.domain;

import java.time.LocalDateTime;
import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

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
            throw new UnAuthorizedException("답변 생성을 위해서는 유효한 작성자 정보가 필요합니다.");
        }

        if (question == null) {
            throw new NotFoundException("답변 생성을 위해서는 유효한 질문 정보가 필요합니다.");
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


    public boolean isOwner(NsUser writer) {
        return this.writer.equals(writer);
    }

    public NsUser getWriter() {
        return writer;
    }


    public void toQuestion(Question question) {
        this.question = question;
    }

    public DeleteHistory delete() throws CannotDeleteException {
        if (!isOwner(question.getWriter())) {
            throw new CannotDeleteException("질문자와 답변자가 다른경우 답변을 삭제할 수 없습니다.");
        }
        this.deleted = true;
        return createDeleteHistory();

    }

    private DeleteHistory createDeleteHistory() {
        return DeleteHistory.createAnswerDeleteHistory(id, writer);
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }
}
