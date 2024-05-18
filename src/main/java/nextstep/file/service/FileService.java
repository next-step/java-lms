package nextstep.file.service;

import nextstep.file.domain.ImageFile;
import nextstep.file.domain.ImageFileRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("fileService")
public class FileService {
	@Resource(name = "imageFileRepository")
	ImageFileRepository imageFileRepository;

	public Long createImageFile(ImageFile imageFile) {
		return imageFileRepository.save(imageFile);
	}
}
