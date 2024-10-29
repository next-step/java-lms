package nextstep.session.service;

import nextstep.session.domain.PaymentType;
import nextstep.session.domain.SubscribeStatus;
import nextstep.session.service.request.ImageRequest;
import nextstep.session.service.request.SessionFindRequest;
import nextstep.session.service.request.SessionRequest;
import nextstep.session.service.request.SessionStatusRequest;
import nextstep.support.TestSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class SessionServiceTest extends TestSupport {

    @Autowired
    private SessionService sessionService;

    private final LocalDateTime startDate = LocalDateTime.parse("2023-04-05T00:00:00");
    private final LocalDateTime endDate = LocalDateTime.parse("2023-05-05T00:00:00");

    @BeforeEach
    void setUp() {
        ImageRequest imageRequest = new ImageRequest("테스트이미지.jpg", 300, 200, 1);
        SessionRequest sessionRequest = new SessionRequest("테스트강의", PaymentType.PAID, startDate, endDate, 800000, 1, imageRequest);

        sessionService.save(sessionRequest);
    }

    @DisplayName("강의를 저장한다 후 조회한다.")
    @Test
    void saveTest() {
        SessionFindRequest sessionFindRequest = new SessionFindRequest(1L);

        assertThat(sessionService.findById(sessionFindRequest))
                .extracting("id", "title", "paymentType", "subscribeStatus", "subscribeMax", "price", "dateRange.startDate", "dateRange.endDate", "image.name", "image.size.width.width", "image.size.height.height", "image.capacity.capacity")
                .contains(1L, "테스트강의", PaymentType.PAID, SubscribeStatus.READY, 1, 800000, startDate, endDate, "테스트이미지.jpg", 300, 200, 1);
    }

    @DisplayName("강의의 상태를 변경한다.")
    @Test
    void changeSubscribeStatusTest() {
        SessionStatusRequest sessionStatusRequest = new SessionStatusRequest(1L, SubscribeStatus.WAIT);
        sessionService.changeSubscribeStatus(sessionStatusRequest);

        assertThat(sessionService.findById(new SessionFindRequest(1L)))
                .extracting("id", "title", "paymentType", "subscribeStatus", "subscribeMax", "price", "dateRange.startDate", "dateRange.endDate", "image.name", "image.size.width.width", "image.size.height.height", "image.capacity.capacity")
                .contains(1L, "테스트강의", PaymentType.PAID, SubscribeStatus.WAIT, 1, 800000, startDate, endDate, "테스트이미지.jpg", 300, 200, 1);
    }

}
