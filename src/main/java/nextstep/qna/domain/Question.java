package nextstep.qna.domain;

import static java.util.Objects.isNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import nextstep.common.domain.SoftDeletableBaseEntity;
import nextstep.qna.exception.unchecked.CannotDeleteException;
import nextstep.qna.exception.unchecked.WrongRequestException;

public class Question extends SoftDeletableBaseEntity {
    private BoardContent boardContent;
    private Long writerId;
    private List<Answer> answers = new ArrayList<>();

    public Question(long writerId, String title, String contents) {
        this(0L, writerId, title, contents);
    }

    public Question(Long id, long writerId, String title, String contents) {
        super(id);
        this.writerId = writerId;
        this.boardContent = new BoardContent(title, contents);
    }

    public boolean isOwner(long requesterId) {
        return writerId == requesterId;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public boolean isDeleted() {
        return super.isDeleted();
    }

    public boolean hasAnswers() {
        return !this.answers.isEmpty();
    }

    public boolean isAllSameContentsWriter() {
        return this.answers.stream()
                .allMatch(answer -> answer.isOwner(this.writerId));
    }

    public void putOnDelete(long requesterId) {
        if (requesterId <= 0L) {
            throw new IllegalArgumentException("잘못된 요청자 정보 입니다.");
        }

        if (!isOwner(requesterId)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }

        if (hasAnswers() && !isAllSameContentsWriter()) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }

        super.updateDeleted();
        putOnAllAnswersDelete(requesterId);
    }

    public void putOnAllAnswersDelete(long requesterId) {
        this.answers.forEach(answer -> answer.putOnDelete(requesterId));
    }

    public DeleteHistory createQuestionDeleteHistory(LocalDateTime deletedDateTime) {
        if (isNull(deletedDateTime)) {
            throw new WrongRequestException("삭제시점은 필수 입니다.");
        }

        if (!super.isDeleted()) {
            throw new WrongRequestException("삭제되지 않은 질문은 삭제이력을 생성할 수 없습니다.");
        }

        return new DeleteHistory(ContentType.QUESTION, super.getId(), this.writerId, deletedDateTime);
    }

    public List<DeleteHistory> bringAllDeleteHistories(LocalDateTime deletedDateTime) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(createQuestionDeleteHistory(deletedDateTime));

        for (Answer answer : answers) {
            deleteHistories.add(answer.createAnswerDeleteHistory(deletedDateTime));
        }

        return deleteHistories;
    }

    @Override
    public String toString() {
        return "Question [id=" + super.getId()
                + ", boardContents=" + boardContent
                + ", writerId=" + writerId
                + "]";
    }
}
