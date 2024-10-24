package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories() {
        this.deleteHistories = new ArrayList<>();
    }

    public void addDeleteHistory(DeleteHistory deleteHistory){
        deleteHistories.add(deleteHistory);
    }

    public List<DeleteHistory> getDeleteHistories(){
        return deleteHistories;
    }
}
