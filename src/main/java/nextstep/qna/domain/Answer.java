package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
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

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }

    /**
     * 이 질문을 삭제합니다.
     *
     * @param writer 질문을 작성한 사람과 writer가 일치할 경우에만 삭제합니다.
     * @param deleteTime 질문 삭제 시각
     *
     * @return 삭제 정보. 삭제되지 않았다면 null을 반환합니다.
     */
    public DeleteHistory deleteIfWriter(NsUser writer, LocalDateTime deleteTime) throws CannotDeleteException {
        if (!this.writer.equals(writer)) {
            throw new CannotDeleteException("답변을 삭제할 권한이 없습니다.");
        }

        this.deleted = true;

        return new DeleteHistory(
                ContentType.ANSWER,
                this.id,
                writer,
                deleteTime
        );
    }
}