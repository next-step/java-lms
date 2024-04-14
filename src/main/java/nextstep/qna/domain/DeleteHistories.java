package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {
    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories() {
        this.deleteHistories = new ArrayList<>();
    }

    public DeleteHistories(Answers answers) {
        this();
        add(answers);
    }

    public DeleteHistories(Question question) {
        this();
        add(question);
    }

    public void add(DeleteHistory deleteHistory) {
        deleteHistories.add(deleteHistory);
    }

    public void add(List<DeleteHistory> deleteHistories) {
        for (DeleteHistory deleteHistory : deleteHistories) {
            add(deleteHistory);
        }
    }

    public void add(Answers answers) {
        for (Answer answer : answers.getAnswers()) {
            add(new DeleteHistory(answer));
        }
    }

    public void add(Question question) {
        add(new DeleteHistory(question));
    }

    public List<DeleteHistory> getDeleteHistoryList() {
        return Collections.unmodifiableList(deleteHistories);
    }

    public void add(DeleteHistories deleteHistories) {
        add(deleteHistories.getDeleteHistoryList());
    }
}
