package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AnswerList implements Iterable<Answer> {
    
    private final List<Answer> answers;

    public AnswerList() {
        this.answers = new ArrayList<>();
    }

    public AnswerList(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }
    
    @Override
    public Iterator<Answer> iterator() {
        return answers.iterator();
    }
}
