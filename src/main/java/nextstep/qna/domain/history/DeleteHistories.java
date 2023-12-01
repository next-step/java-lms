package nextstep.qna.domain.history;

import nextstep.qna.domain.answer.Answers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {

    private List<DeleteHistory> deleteHistoryList = new ArrayList<>();

    public void add(DeleteHistory deleteHistory) {
        deleteHistoryList.add(deleteHistory);
    }

    public void addAnswers(Answers answers) {
        answers.getAnswerList()
                .stream()
                .forEach(answer -> deleteHistoryList.add(new DeleteHistory().has(answer)));
    }

    public final List<DeleteHistory> getDeleteHistoryList() {
        return Collections.unmodifiableList(deleteHistoryList);
    }
}
