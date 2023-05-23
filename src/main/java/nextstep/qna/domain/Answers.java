package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    
    private List<Answer> values = new ArrayList<>();

    public Answers() {
    }

    public void addAnswer(Answer answer) {
        values.add(answer);
    }
    
    public List<DeleteHistory> delete(NsUser writer) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : values) {
            deleteHistories.add(answer.delete(writer));
        }
        return deleteHistories;
    }
    
}
