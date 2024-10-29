package nextstep.session.service;

import nextstep.session.domain.PaymentType;
import nextstep.session.domain.Session;
import nextstep.session.domain.SubscribeStatus;
import nextstep.session.domain.image.Image;
import nextstep.session.service.request.*;
import nextstep.support.TestSupport;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

class SubscriberServiceTest extends TestSupport {

    @Autowired
    private SubscriberService subscriberService;
    @Autowired
    private SessionService sessionService;

    @DisplayName("강의 신청자를 저장한다.")
    @Test
    void subscribeTest() {
        LocalDateTime startDate = LocalDateTime.parse("2023-04-05T00:00:00");
        LocalDateTime endDate = LocalDateTime.parse("2023-05-05T00:00:00");

        ImageRequest imageRequest = new ImageRequest("테스트이미지.jpg", 300, 200, 1);
        SessionRequest sessionRequest = new SessionRequest("테스트강의", PaymentType.FREE, startDate, endDate, 800000, 1, imageRequest);

        sessionService.save(sessionRequest);

        sessionService.changeSubscribeStatus(new SessionStatusRequest(1L, SubscribeStatus.WAIT));

        SubscriberRequest subscriberRequest = new SubscriberRequest(1L, 1L);
        subscriberService.subscribe(subscriberRequest);

        Session findSession = sessionService.findById(new SessionFindRequest(1L));
        assertThat(findSession.getSubscribers().getSubscribeUsers())
                .extracting("nsUser.id", "nsUser.userId", "nsUser.password", "nsUser.name", "nsUser.email")
                .contains(tuple(1L, "javajigi", "test", "자바지기", "javajigi@slipp.net"));
    }
}
