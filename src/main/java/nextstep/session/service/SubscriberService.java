package nextstep.session.service;

import nextstep.payments.domain.Payment;
import nextstep.session.domain.Session;
import nextstep.session.domain.Subscriber;
import nextstep.session.domain.SubscriberRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubscriberService {

    private final SessionService sessionService;
    private final UserRepository userRepository;
    private final SubscriberRepository subscriberRepository;

    @Autowired
    public SubscriberService(SessionService sessionService, UserRepository userRepository, SubscriberRepository subscriberRepository) {
        this.sessionService = sessionService;
        this.userRepository = userRepository;
        this.subscriberRepository = subscriberRepository;
    }

    public void subscribe(Long sessionId, Long userId) {
        Session session = sessionService.findById(sessionId);
        NsUser nsUser = userRepository.findById(userId);

        //무료
        if (session.checkFreePaid()) {
            Subscriber subscriber = session.subscribe(nsUser);
            subscriberRepository.save(subscriber);
            return;
        }

        //유료
        Payment payment = new Payment(1L, session.getId(), nsUser.getId(), 800000); //가상의 결제 이력
        Subscriber subscriber = session.subscribe(nsUser, payment);

        subscriberRepository.save(subscriber);
    }

}
