package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.List;

public class QnADomainService {

    // TODO : 피드백 주신 부분 잘보았습니다.
    //  저는 개인적으로 도메인 로직은 순수하게 유지하는게 가장 좋다고 생각합니다만.
    //  스프링을 활용한 의존성주입이 필요한 예외적인 상황(불가피한 외부API 호출, 이메일 등등..)에서는 허용하고 사용해주는것도 좋다고 생각합니다.
    //  다만 단계를 거쳐볼것 같아요. 1. 순수 객체 -> 2. 클래스 메서드(static) 집합 객체(=외부 bean 필요없이 캐싱 작업이 필요힌 경우) -> 3. 외부 bean이 불가피하게 필요한경우 bean & di
    public List<DeleteHistory> deleteQuestion(
            long requesterId, Question question, LocalDateTime fixedDeletedDateTime
    ) {
        question.putOnDelete(requesterId);

        return question.bringAllDeleteHistories(fixedDeletedDateTime);
    }
}
