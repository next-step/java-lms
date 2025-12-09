package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Question {

    private Long id;

    private String title;

    private String contents;

    private NsUser writer;
    private Long writerId;

    private List<Answer> answers = new ArrayList<>();

    private boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    public Question() {
    }

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Question(long writerId, String title, String contents) {
        this(0L, writerId, title, contents);
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public Question(Long id, long writerId, String title, String contents) {
        this.id = id;
        this.writerId = writerId;
        this.title = title;
        this.contents = contents;
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public boolean isOwner(long requesterId) {
        return writerId == requesterId;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public boolean isDeleted() {
        return deleted;
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

//        if (!isOwner(requesterId)) {
//            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
//        }
//
//        if (hasAnswers() || !isAllSameContentsWriter()) {
//            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
//        }

        this.deleted = true;
//        putOnAllAnswersDelete(requesterId);
    }

    public void putOnAllAnswersDelete(long requesterId) {
        this.answers.forEach(answer -> answer.putOnDelete(requesterId));
    }

    public Long getId() {
        return id;
    }

    public NsUser getWriter() {
        return writer;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public Question setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents
                + ", writer=" + writer + "]";
    }
}
