package nextstep.qna.domain;

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

    protected Answer() {
    }

    public Answer(NsUser writer, Question question, String contents) {
        this(null, writer, question, contents);
    }

    public Answer(Long id, NsUser writer, Question question, String contents) {
        validateRequiredField(writer, question);
        question.addAnswer(this);

        this.id = id;
        this.writer = writer;
        this.question = question;
        this.contents = contents;
    }

    private static void validateRequiredField(NsUser writer, Question question) {
        if (writer == null) {
            throw new IllegalArgumentException("writer is required to create answer");
        }
        if (question == null) {
            throw new IllegalArgumentException("question is required to create answer");
        }
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isOwner(NsUser writer) {
        return this.writer.equals(writer);
    }

    public DeleteHistory delete() {
        this.deleted = true;
        return new DeleteHistory(ContentType.ANSWER, id, writer, LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "Answer [id=" + id + ", writer=" + writer + ", contents=" + contents + "]";
    }
}
