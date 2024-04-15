package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DeleteHistories {
    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories() {
        this.deleteHistories = new ArrayList<>();
    }

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public DeleteHistories(Answers answers) {
        this(toDeleteHistory(answers));
    }

    public DeleteHistories(Question question) {
        this(List.of(new DeleteHistory(question)));
    }

    public void add(DeleteHistory deleteHistory) {
        deleteHistories.add(deleteHistory);
    }

    public void add(List<DeleteHistory> deleteHistories) {
        for (DeleteHistory deleteHistory : deleteHistories) {
            add(deleteHistory);
        }
    }

    private static List<DeleteHistory> toDeleteHistory(Answers answers) {
        return answers.getAnswers().stream()
                .map(DeleteHistory::new)
                .collect(Collectors.toList());
    }

    public List<DeleteHistory> getDeleteHistoryList() {
        return Collections.unmodifiableList(deleteHistories);
    }

    public void add(DeleteHistories deleteHistories) {
        add(deleteHistories.getDeleteHistoryList());
    }
}
