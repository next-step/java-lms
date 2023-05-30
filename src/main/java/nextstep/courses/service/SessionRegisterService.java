package nextstep.courses.service;

import nextstep.courses.domain.SessionRepository;

public class SessionRegisterService {

    private SessionRepository sessionRepository;

    public SessionRegisterService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

//    public void registerSession(Long id, NsUser loginUser) {
//        Session session = sessionRepository.findById(id).orElseThrow(() -> new NotFoundException("존재하지 않는 강의입니다."));
//        session.register(loginUser);
//    }

}
