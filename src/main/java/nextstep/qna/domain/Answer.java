package nextstep.qna.domain;

import java.time.LocalDateTime;
import nextstep.common.domain.SoftDeletableBaseEntity;
import nextstep.qna.exception.unchecked.CannotDeleteException;
import nextstep.qna.exception.unchecked.NotFoundException;
import nextstep.qna.exception.unchecked.UnAuthorizedException;
import nextstep.qna.exception.unchecked.WrongRequestException;

public class Answer extends SoftDeletableBaseEntity {
    private Long writerId;
    private Question question;
    private BoardContent boardContent;

    public Answer(long writerId, Question question, String contents) {
        this(null, writerId, question, contents);
    }

    public Answer(Long id, long writerId, Question question, String contents) {
        super(id);
        if (writerId <= 0L) {
            throw new UnAuthorizedException();
        }

        if (question == null) {
            throw new NotFoundException();
        }

        this.writerId = writerId;
        this.question = question;
        this.boardContent = new BoardContent("" , contents);
    }

    public boolean isDeleted() {
        return super.isDeleted();
    }

    public void putOnDelete(long requesterId) {
        if (!isOwner(requesterId)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }

        super.updateDeleted();
    }

    public boolean isOwner(long writerId) {
        return this.writerId == writerId;
    }

    public DeleteHistory createAnswerDeleteHistory(LocalDateTime deletedDateTime) {
        if (!isDeleted()) {
            throw new WrongRequestException("삭제되지 않은 답변은 삭제이력을 생성할 수 없습니다.");
        }

        return new DeleteHistory(ContentType.ANSWER, super.getId(), this.writerId, deletedDateTime);
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer [id=" + super.getId()
                + ", writerId=" + writerId
                + ", contents=" + boardContent
                + "]";
    }
}
