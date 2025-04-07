package nextstep.qna.service;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.domain.*;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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
    public void deleteQuestion(NsUser loginUser, long questionId) throws CannotDeleteException {
        // question을 가지고 온다.
        Question question = questionRepository.findById(questionId).orElseThrow(NotFoundException::new);
        // question이 로그인 유저와 동일한지 체크한다.
        if (!question.isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }

        // answer를 가지고 온다.
        List<Answer> answers = question.getAnswers();
        for (Answer answer : answers) {
            if (!answer.isOwner(loginUser)) {   // answer가 로그인 유저와 동일한지 체크한다.
                throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
            }
        }

        // deleteHistories를 생성한다.
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        question.setDeleted(true);  // 질문을 삭제한다.
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, questionId, question.getWriter(), LocalDateTime.now())); // 히스토리에 추가한다.
        for (Answer answer : answers) {
            answer.setDeleted(true);    // 답변을 삭제한다.
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now())); // 히스토리에 추가한다.
        }
        deleteHistoryService.saveAll(deleteHistories); // 히스토리를 저장한다.
    }
}
