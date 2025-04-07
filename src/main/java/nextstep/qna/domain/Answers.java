package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Answers {
    private final List<Answer> answers;

    public Answers(){
        this.answers = new ArrayList<>();
    }

    public Answers(List<Answer> answerList){
        this.answers = new ArrayList<>(answerList);
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public List<Answer> getAnswers(){
        return Collections.unmodifiableList(answers);
    }
}
