package nextstep.sessions.service;

import java.util.List;
import java.util.stream.Collectors;

import nextstep.sessions.domain.data.coverimage.CoverImage;
import nextstep.sessions.domain.data.session.Session;
import nextstep.sessions.domain.data.dto.request.CoverImageCreateRequestDto;
import nextstep.sessions.domain.data.dto.request.SessionCreateRequestDto;
import nextstep.sessions.repository.CoverImageRepository;
import nextstep.sessions.repository.SessionRepository;

public class SessionService {

    private final SessionRepository sessionRepository;
    private final CoverImageRepository coverImageRepository;

    public SessionService(SessionRepository sessionRepository, CoverImageRepository coverImageRepository) {
        this.sessionRepository = sessionRepository;
        this.coverImageRepository = coverImageRepository;
    }

    public void establishSession(SessionCreateRequestDto requestDto) {
        Session session = requestDto.toSession();
        sessionRepository.save(session);
    }

    public void saveCoverImage(int sessionId, CoverImageCreateRequestDto requestDto) {
        saveCoverImages(sessionId, List.of(requestDto));
    }

    public void saveCoverImages(int sessionId, List<CoverImageCreateRequestDto> requestDtos) {
        List<CoverImage> coverImages = requestDtos.stream()
            .map(CoverImageCreateRequestDto::toCoverImage)
            .collect(Collectors.toList());
        coverImageRepository.saveAll(sessionId, coverImages);
    }

}
