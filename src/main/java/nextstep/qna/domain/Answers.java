package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {

    private List<Answer> answerList = new ArrayList<>();

    public Answers() {
    }

    public Answers(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public List<Answer> answers(){
        return Collections.unmodifiableList(answerList);
    }

    public void add(Answer answer){
        answerList.add(answer);
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistoryList = new ArrayList<>();
        for (Answer answer : answerList){
            deleteHistoryList.add(answer.delete(loginUser));
        }
        return deleteHistoryList;
    }
}
