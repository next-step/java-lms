package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
    private final List<DeleteHistory> deleteHistories;


    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }


    public void addQuestionDeleteHistory(Question question){
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()));
    }

    public void addDeleteHistory(ContentType contentType, Long contentId, NsUser deletedBy) {
        deleteHistories.add(new DeleteHistory(contentType, contentId, deletedBy, LocalDateTime.now()));
    }


    public List<DeleteHistory> getDeleteHistories(){
        return deleteHistories;
    }
}
