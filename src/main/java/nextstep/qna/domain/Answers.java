package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Answers implements Iterable<Answer> {
    private final List<Answer> answers;

    public Answers() {
        this(new ArrayList<>());
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answers delete(NsUser loginUser) {
        List<Answer> deletedAnswers = new ArrayList<>();
        for (Answer answer : answers) {
            deletedAnswers.add(answer.delete(loginUser));
        }

        return new Answers(deletedAnswers);
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public DeleteHistories toDeleteHistories(LocalDateTime time) {
        DeleteHistories deleteHistories = new DeleteHistories();
        for (Answer answer : answers) {
            deleteHistories.add(answer.toDeleteHistory(time));
        }
        return deleteHistories;
    }

    @Override
    public Iterator<Answer> iterator() {
        return this.answers.iterator();
    }
}
