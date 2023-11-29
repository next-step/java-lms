package nextstep.qna.service;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.domain.*;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("qnaService")
public class QnAService {
    @Resource(name = "questionRepository")
    private QuestionRepository questionRepository;

    @Resource(name = "answerRepository")
    private AnswerRepository answerRepository;

    @Resource(name = "deleteHistoryService")
    private DeleteHistoryService deleteHistoryService;

    @Transactional
    public void deleteQuestion(NsUser loginUser, long questionId) {
        Question question = questionToBeDeleted(loginUser, questionId);
        questionRepository.update(questionId, question);

        List<Answer> answers = answersToBeDeleted(loginUser, questionId);
        for (Answer answer : answers) {
            answerRepository.update(answer.getId(), answer);
        }

        List<DeleteHistory> deleteHistories = deleteHistoriesOf(question, answers);
        deleteHistoryService.saveAll(deleteHistories);
    }

    private Question questionToBeDeleted(NsUser loginUser, long questionId) {
        Question question = getQuestion(questionId);
        question = question.delete(loginUser);
        return question;
    }

    private Question getQuestion(long questionId) throws CannotDeleteException {
        return questionRepository.findById(questionId).orElseThrow(NotFoundException::new);
    }

    private List<Answer> answersToBeDeleted(NsUser loginUser, long questionId) {
        List<Answer> deletedAnswers = new ArrayList<>();
        for (Answer answer : getAnswers(questionId)) {
            answer = answer.delete(loginUser);
            deletedAnswers.add(answer);
        }

        return deletedAnswers;
    }

    private List<Answer> getAnswers(long questionId) {
        return answerRepository.findByQuestion(questionId);
    }

    private List<DeleteHistory> deleteHistoriesOf(Question question, List<Answer> answers) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(DeleteHistory.from(question));
        deleteHistories.addAll(DeleteHistory.from(answers));
        return deleteHistories;
    }
}
