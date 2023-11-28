package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = new ArrayList(answers);
    }

    public boolean isAllSameBy(NsUser questionWriter) {
        if (hasNoAnswers()) {
            return false;
        }
        return answers.stream()
                .allMatch(answer -> answer.isOwner(questionWriter));
    }

    private boolean hasNoAnswers() {
        return answers.isEmpty();
    }

    public void deleteAll(){
        answers.forEach(Answer::delete);
    }

    public List<Answer> answers() {
        return Collections.unmodifiableList(answers);
    }
}

