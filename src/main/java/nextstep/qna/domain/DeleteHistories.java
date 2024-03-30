package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {

    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories() {
        this.deleteHistories = new ArrayList<>();
    }

    public void addQuestionDeleteHistory(Question question) {
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()));
    }

    public void addAnswersDeletedHistories(Answers answers) {
        for (Answer answer : answers) {
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }


}
