package nextstep.qna.domain.answer;

import lombok.Getter;
import lombok.SneakyThrows;
import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.qna.domain.enums.ContentType;
import nextstep.qna.domain.history.DeleteHistory;
import nextstep.qna.domain.question.Question;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

@Getter
public class Answer {

    private Long id;

    private NsUser writer;

    private Question question;

    private String contents;

    private boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

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

    public boolean isDeleted() {
        return deleted;
    }

    public boolean hasWriter(NsUser writer) {
        return this.writer.equals(writer);
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }

    @SneakyThrows
    public DeleteHistory delete(final NsUser loginUser) {
        if (!hasWriter(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }

        this.deleted = true;

        return new DeleteHistory(ContentType.ANSWER, id, writer, LocalDateTime.now());
    }

}
