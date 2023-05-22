package nextstep.qna.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component("eventPublisher")
public class QuestionDeletePublisher implements ApplicationEventPublisher {

    @Override
    public void publishEvent(ApplicationEvent event) {
        ApplicationEventPublisher.super.publishEvent(event);
    }

    @Override
    public void publishEvent(Object event) {
    }
}
