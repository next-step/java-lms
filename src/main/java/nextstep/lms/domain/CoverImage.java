package nextstep.lms.domain;

import java.time.LocalDateTime;

public class CoverImage {
    private final Long id;
    private final FileNameStructure fileNameStructure;
    private final FileMetadata fileMetadata;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CoverImage(FileNameStructure fileNameStructure, FileMetadata fileMetadata) {
        this(0L, fileNameStructure, fileMetadata, LocalDateTime.now(), null);
    }

    public CoverImage(Long id, FileNameStructure fileNameStructure, FileMetadata fileMetadata, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.fileNameStructure = fileNameStructure;
        this.fileMetadata = fileMetadata;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return fileNameStructure.getName();
    }

    public String getExtension() {
        return fileNameStructure.getExtension();
    }

    public long getFileVolume() {
        return fileMetadata.getFileVolume();
    }

    public int getWidth() {
        return fileMetadata.getWidth();
    }

    public int getHeight() {
        return fileMetadata.getHeight();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}
