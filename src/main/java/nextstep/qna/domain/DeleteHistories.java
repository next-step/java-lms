package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
    private List<DeleteHistory> deleteHistories;

    public DeleteHistories(){
        this.deleteHistories = new ArrayList<>();
    }

    public DeleteHistories(List<DeleteHistory> deleteHistories){
        this.deleteHistories = deleteHistories;
    }

    private void addDeleteHistory(DeleteHistory deleteHistory){
        this.deleteHistories.add(deleteHistory);
    }

    public void addQuestion(Question question){
        DeleteHistory deleteHistory = new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now());
        this.addDeleteHistory(deleteHistory);
    }

    public void addAnswer(Answer answer){
        DeleteHistory deleteHistory = new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now());
        this.addDeleteHistory(deleteHistory);
    }

    public void addAnswers(Answers answers){
        for(Answer answer : answers.getAll()){
            this.addAnswer(answer);
        }
    }

    public List<DeleteHistory> getAll(){
        return this.deleteHistories;
    }
}
