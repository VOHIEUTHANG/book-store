package app.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.web.multipart.MultipartFile;

public class Upload {
	public String getFileExtension(String fileName) {
		String filelEx;
		int dotIndex = fileName.lastIndexOf(".");
		return fileName.substring(dotIndex);
	}

	public String writeFile(MultipartFile fileParam,ServletContext context) throws IOException {
		UUID uniqueKey = UUID.randomUUID();
		byte[] bytes = fileParam.getBytes();
		String fileExtension = getFileExtension(fileParam.getOriginalFilename());
		Path path = Paths.get(context.getRealPath("//resources//uploads//") + uniqueKey + fileExtension);
		Files.write(path, bytes);
		return "resources/uploads/" + uniqueKey + fileExtension;
	}
	
}
