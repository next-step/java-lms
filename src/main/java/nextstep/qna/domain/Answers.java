package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Answers implements Iterable<Answer> {

    private final List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<DeleteHistory> delete(NsUser loginUser) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        for (Answer answer : answers) {
            deleteHistories.add(answer.delete(loginUser));
        }
        return deleteHistories;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    @Override
    public Iterator<Answer> iterator() {
        return answers.iterator();
    }
}
