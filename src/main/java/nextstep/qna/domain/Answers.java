package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;

public class Answers {
    private List<Answer> answers = new ArrayList<>();

    public Answers(){
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public List<DeleteHistory> deleteAll(NsUser loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        for (Answer answer : answers) {
            deleteHistories.add(answer.delete(loginUser));
        }

        return deleteHistories;
    }

    protected boolean isAnswersDeleted(){
        boolean status = FALSE;
        for(Answer answer : answers){
            status = status || answer.isDeleted();
        }
        return status;
    }
}
