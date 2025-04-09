package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;
import nextstep.users.domain.NsUser;

public class Answers {

    private final List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public boolean canBeDeletedBy(NsUser user) {
        return answers.stream().allMatch(answer -> answer.isOwner(user));
    }

    public List<DeleteHistory> deleteAll(NsUser user) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            if (answer.isOwner(user)) {
                answer.setDeleted(true);
                deleteHistories.add(DeleteHistory.ofAnswer(answer));
            }
        }
        return deleteHistories;
    }
}
