package nextstep.qna.domain;

import static java.time.LocalDateTime.now;
import static nextstep.qna.domain.ContentType.QUESTION;

import java.util.ArrayList;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Question {

    private static final String NO_AUTH_MSG = "질문을 삭제할 권한이 없습니다.";

    private static final String OTHER_USER_ANSWER_MSG = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";

    private Long id;

    private String title;

    private String contents;

    private NsUser writer;

    private Answers answers = new Answers();

    private boolean deleted = false;

    private LocalDateTime createdDate = now();

    private LocalDateTime updatedDate;

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    public NsUser getWriter() {
        return writer;
    }

    public void addAnswer(Answer answer) {
        this.answers = answers.add(answer);
    }

    private boolean isNotOwner(NsUser loginUser) {
        return !this.writer.equals(loginUser);
    }

    public void deleteQuestion(NsUser loginUser) {
        checkIsNotOwner(loginUser);
        checkHasOthersAnswer(loginUser);
        deleteQuestion();
        deleteAnswers();
    }

    private void checkIsNotOwner(NsUser loginUser) {
        if(isNotOwner(loginUser)) {
            throw new CannotDeleteException(NO_AUTH_MSG);
        }
    }

    private void checkHasOthersAnswer(NsUser loginUser) {
        if(this.answers.hasOtherUserAnswer(loginUser)) {
            throw new CannotDeleteException(OTHER_USER_ANSWER_MSG);
        }
    }

    public List<DeleteHistory> deleteHistories() {
        if(!isQuestionDeleted()) return new ArrayList<>();
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(QUESTION, this.id, this.writer, now()));
        deleteHistories.addAll(this.answers.deleteHistories());
        return deleteHistories;
    }


    private void deleteQuestion() {
        this.deleted = true;
    }

    private void deleteAnswers() {
        this.answers.deleteAll();
    }

    public boolean isQuestionDeleted() {
        return deleted && this.answers.immutableGet()
                .stream().allMatch(Answer::isDeleted);
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
