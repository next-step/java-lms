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

        private Answers answers = new Answers(new ArrayList<>());

        private boolean deleted = false;

        private LocalDateTime createdDate = LocalDateTime.now();

        private LocalDateTime updatedDate;

        private Question() {
        }

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

        public String getTitle() {
                return title;
        }

        public String getContents() {
                return contents;
        }

        public NsUser getWriter() {
                return writer;
        }

        public void addAnswer(Answer answer) {
                answer.toQuestion(this);
                answers.add(answer);
        }

        private boolean isOwner(NsUser loginUser) {
                return writer.equals(loginUser);
        }

        public boolean isDeleted() {
                return deleted;
        }

        public List<DeleteHistory> deleteQuestion(NsUser loginUser) throws CannotDeleteException {
                validateDeleteQuestionPermission(loginUser);
                List<DeleteHistory> historiesOfDeletedQuestionAndAnswers = answers.deleteAnswers(loginUser);
                historiesOfDeletedQuestionAndAnswers.add(deleteQuestionHistory());

                return historiesOfDeletedQuestionAndAnswers;
        }

        private void validateDeleteQuestionPermission(NsUser loginUser) throws CannotDeleteException {
                if (!isOwner(loginUser)) {
                        throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
                }
        }

        private DeleteHistory deleteQuestionHistory() {
                this.deleted = true;
                return new DeleteHistory(ContentType.QUESTION, id, getWriter(), LocalDateTime.now());
        }

        @Override
        public String toString() {
                return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer
                    + "]";
        }
}
