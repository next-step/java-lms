package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.answer.Answer;
import nextstep.qna.domain.answer.Answers;
import nextstep.qna.domain.deleteHistory.DeleteHistories;
import nextstep.users.domain.NsUser;
import nextstep.utils.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question {
    private Long id;
    private Detail detail;
    private NsUser writer;
    private Answers answers = new Answers();

    private boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    public Question() {
    }

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        this.id = id;
        this.writer = writer;
        this.detail = new Detail(title, contents);
    }

    public Long getId() {
        return id;
    }

    public NsUser getWriter() {
        return writer;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer, this);
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void delete(NsUser user) throws CannotDeleteException {
        if (!isOwner(user)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }

        answers.delete(user);

        this.deleted = true;
    }


    public DeleteHistories getDeleteHistories() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        deleteHistories.add(new DeleteHistory(this));
        deleteHistories.addAll(answers.getDeleteHistories());

        return new DeleteHistories(deleteHistories);
    }

    private String title() {
        return this.detail.title;
    }

    private String contents() {
        return this.detail.contents;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title() + ", contents=" + contents() + ", writer=" + writer + "]";
    }

    public class Detail {
        private String title;

        private String contents;

        public Detail(String title, String contents) {
            if (StringUtils.isBlank(title)) {
                throw new IllegalArgumentException("질문의 제목이 비어있습니다.");
            }

            if (StringUtils.isBlank(contents)) {
                throw new IllegalArgumentException("질문의 제목이 비어있습니다.");
            }

            this.title = title;
            this.contents = contents;
        }
    }
}
