package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
    private List<DeleteHistory> deleteHistories = new ArrayList<>();

    public List<DeleteHistory> addDeleteHistory(ContentType contentType, long questionId, NsUser writer) {
        deleteHistories.add(new DeleteHistory(contentType, questionId, writer, LocalDateTime.now()));
        return deleteHistories;
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }
}
