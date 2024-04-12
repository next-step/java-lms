package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private final List<Answer> answers;

    public Answers() {
        this(new ArrayList<>());
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<DeleteHistory> deleteByUser(NsUser user) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        for (Answer answer : answers) {
            deleteHistories.add(answer.deleteByUser(user));
        }

        return deleteHistories;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public boolean isDeletableByWriter(NsUser writerOfQuestion) {
        return answers.isEmpty() || answers.stream().allMatch(answer -> answer.isOwner(writerOfQuestion));
    }
}
