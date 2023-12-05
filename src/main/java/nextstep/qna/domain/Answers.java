package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {

    private final List<Answer> value;

    public Answers(List<Answer> value){
        this.value = value;
    }

    public void add(Answer answer){
        value.add(answer);
    }

    public List<DeleteHistory> deleteAll(NsUser user) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : value) {
            deleteHistories.add(answer.delete(user));
        }
        return deleteHistories;
    }
}
