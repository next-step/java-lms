package nextstep.qna.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeleteHistorys {
    private List<DeleteHistory> deleteHistoryList;

    public DeleteHistorys(DeleteHistory questionDeleteHistory, List<DeleteHistory> answerDeleteHistory) {
        this(Stream.concat(Stream.of(questionDeleteHistory), answerDeleteHistory.stream())
                .collect(Collectors.toList()));
    }

    public DeleteHistorys(List<DeleteHistory> deleteHistoryList) {
        this.deleteHistoryList = deleteHistoryList;
    }

    public List<DeleteHistory> toList() {
        return Collections.unmodifiableList(deleteHistoryList);
    }
}