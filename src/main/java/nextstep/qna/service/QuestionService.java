package nextstep.qna.service;

import nextstep.qna.NotFoundException;
import nextstep.qna.domain.Question;
import nextstep.qna.domain.QuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question findById(long questionId) {
        return questionRepository.findById(questionId).orElseThrow(NotFoundException::new);
    }
}
