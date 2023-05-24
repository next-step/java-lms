package nextstep.courses.service;

import nextstep.courses.domain.CartRepository;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;

public class CourseRegisterService {

    private SessionRepository sessionRepository;
    private CartRepository cartRepository;

    public CourseRegisterService(SessionRepository sessionRepository, CartRepository cartRepository) {
        this.sessionRepository = sessionRepository;
        this.cartRepository = cartRepository;
    }

    public void registerSession(Long id) {
        Session session = sessionRepository.findById(id);
        session.isRegistrable();
        cartRepository.save(session);
    }

}
