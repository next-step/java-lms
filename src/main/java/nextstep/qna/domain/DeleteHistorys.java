package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistorys {
    private List<DeleteHistory> deleteHistoryList = new ArrayList<>();

    public DeleteHistorys(DeleteHistory questionDeleteHistory, DeleteHistorys answerDeleteHistory) {
        this(answerDeleteHistory.addDeleteHistory(questionDeleteHistory));
    }

    public DeleteHistorys(DeleteHistorys deleteHistorys) {
        this.deleteHistoryList = deleteHistorys.deleteHistoryList;
    }

    public DeleteHistorys(List<DeleteHistory> deleteHistoryList) {
        this.deleteHistoryList = deleteHistoryList;
    }

    private DeleteHistorys addDeleteHistory(DeleteHistory questionDeleteHistory){
        deleteHistoryList.add(questionDeleteHistory);
        return this;
    }


    public List<DeleteHistory> toList() {
        return Collections.unmodifiableList(deleteHistoryList);
    }
}
