package nextstep.courses.dto;

public class SessionDTO {
    private final Long id;
    private final CoverImageDTO coverImageDTO;
    private final DurationDTO durationDTO;
    private final SessionPaymentDTO sessionPaymentDTO;

    public SessionDTO(Long id, CoverImageDTO coverImageDTO, DurationDTO durationDTO,
                      SessionPaymentDTO sessionPaymentDTO) {
        this.id = id;
        this.coverImageDTO = coverImageDTO;
        this.durationDTO = durationDTO;
        this.sessionPaymentDTO = sessionPaymentDTO;
    }

    public Long getId() {
        return id;
    }

    public CoverImageDTO getCoverImageDTO() {
        return coverImageDTO;
    }

    public DurationDTO getDurationDTO() {
        return durationDTO;
    }

    public SessionPaymentDTO getSessionPaymentDTO() {
        return sessionPaymentDTO;
    }

}
