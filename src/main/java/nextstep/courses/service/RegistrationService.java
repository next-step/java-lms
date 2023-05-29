package nextstep.courses.service;

import nextstep.courses.domain.ragistration.Registration;
import nextstep.courses.domain.ragistration.RegistrationRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final RegistrationRepository registrationRepository;


    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public int save(Registration registration) {
        return registrationRepository.save(registration);
    }

    public Registration findByCourseIdAndSessionIdAndUserId(long courseId, long sessionId, long userId) {
        return this.registrationRepository.findByCourseIdAndSessionIdAndUserId(courseId, sessionId, userId);
    }

    public int update(Registration registration) {
        return this.registrationRepository.update(registration);
    }
}
