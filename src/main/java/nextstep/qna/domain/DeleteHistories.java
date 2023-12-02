package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {
    private final List<DeleteHistory> deleteHistories = new ArrayList<>();

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories.addAll(deleteHistories);
    }

    public DeleteHistories(DeleteHistory deleteHistory) {
        this.deleteHistories.add(deleteHistory);
    }

    public void addDeleteHistory(ContentType contentType, Long contentId, NsUser deletedBy) {
        deleteHistories.add(new DeleteHistory(contentType, contentId, deletedBy, LocalDateTime.now()));
    }

    public void addAllDeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories.addAll(deleteHistories);
    }

    public List<DeleteHistory> getDeleteHistories(){
        return deleteHistories;
    }
}
