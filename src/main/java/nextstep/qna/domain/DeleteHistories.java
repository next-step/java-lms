package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {
    private final List<DeleteHistory> histories;

    public DeleteHistories(){
        this.histories = new ArrayList<>();
    }

    public DeleteHistories(List<DeleteHistory> histories){
        this.histories = new ArrayList<>(histories);
    }

    public void add(ContentType contentType, Long contentId, NsUser deletedBy, LocalDateTime createdDate){
        this.histories.add(new DeleteHistory(contentType, contentId, deletedBy, createdDate));
    }

    public void add(Answers answers, LocalDateTime createdDate){
        for(Answer answer : answers.getAnswers()){
            this.add(ContentType.ANSWER, answer.getId(), answer.getWriter(), createdDate);
        }
    }

    public List<DeleteHistory> getDeleteHistories(){
        return Collections.unmodifiableList(histories);
    }

}
