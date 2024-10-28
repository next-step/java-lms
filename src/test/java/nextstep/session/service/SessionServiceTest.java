package nextstep.session.service;

import nextstep.session.domain.PaymentType;
import nextstep.session.domain.Session;
import nextstep.session.domain.image.Image;
import nextstep.session.service.request.ImageRequest;
import nextstep.session.service.request.SessionRequest;
import nextstep.support.TestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

class SessionServiceTest extends TestSupport {

    @Autowired
    private SessionService sessionService;

    @DisplayName("강의와 이미지를 함께 저장한다.")
    @Test
    void saveTest() {
        LocalDateTime startDate = LocalDateTime.parse("2023-04-05T00:00:00");
        LocalDateTime endDate = LocalDateTime.parse("2023-05-05T00:00:00");

        ImageRequest imageRequest = new ImageRequest("테스트이미지.jpg", 300, 200, 1);
        SessionRequest sessionRequest = new SessionRequest("테스트강의", PaymentType.PAID, startDate, endDate, 800000, 1, imageRequest);

        sessionService.save(sessionRequest);
    }

    @DisplayName("강의와 이미지를 함께 조회한다.")
    @Test
    void findByIdTest() {

    }
}
