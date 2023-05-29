package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import nextstep.users.domain.NsUser;

public class Answers {

    private final List<Answer> answers;

    public Answers() {
        this(new ArrayList<>());
    }

    public Answers(List<Answer> answers) {
        this.answers = new ArrayList<>(answers);
    }

    public boolean hasOtherUserAnswer(NsUser loginUser) {
        return this.answers.stream()
                .anyMatch(answer -> answer.isNotOwner(loginUser));
    }

    public Answers add(Answer answer) {
        this.answers.add(answer);
        return new Answers(this.answers);
    }

    public void deleteAll() {
        this.answers.forEach(Answer::deleteSelf);
    }

    public List<DeleteHistory> deleteHistories() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        this.answers.forEach(answer -> {
            DeleteHistory answerDeletedHistory = answer.deleteHistory();
            if (!answerDeletedHistory.isEmpty()) {
                deleteHistories.add(answer.deleteHistory());
            }
        });
        return deleteHistories;
    }

    public List<Answer> immutableGet() {
        return Collections.unmodifiableList(this.answers);
    }
}
