package zm.co.nhima.nrapi.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import zm.co.nhima.nrapi.exception.FileStorageException;
import zm.co.nhima.nrapi.exception.MyFileNotFoundException;

@Service
public class FileStorageServiceImpl implements FileStorageService {

	private final Path fileStorageLocation;

	@Autowired
	public FileStorageServiceImpl(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException(AppConstants.FILE_STORAGE_EXCEPTION_PATH_NOT_FOUND, ex);
		}
	}

	@Override
	public String storeFile(MultipartFile file) throws IOException {

		if (!(file.getOriginalFilename().endsWith(AppConstants.PNG_FILE_FORMAT)
				|| file.getOriginalFilename().endsWith(AppConstants.JPEG_FILE_FORMAT)
				|| file.getOriginalFilename().endsWith(AppConstants.JPG_FILE_FORMAT)
				|| file.getOriginalFilename().endsWith(AppConstants.PDF_FILE_FORMAT)
				|| file.getOriginalFilename().endsWith(AppConstants.DOC_FILE_FORMAT)
				|| file.getOriginalFilename().endsWith(AppConstants.DOCX_FILE_FORMAT)))
			throw new FileStorageException(AppConstants.INVALID_FILE_FORMAT);

		File f = new File(AppConstants.TEMP_DIR + file.getOriginalFilename());

		f.createNewFile();
		FileOutputStream fout = new FileOutputStream(f);
		fout.write(file.getBytes());
		fout.close();

		if (f.exists())
			f.delete();

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());		
		fileName = fileName.replaceAll("\\s+", "");
		
		try {
			if (fileName.contains(AppConstants.INVALID_FILE_DELIMITER)) {
				throw new FileStorageException(AppConstants.INVALID_FILE_PATH_NAME + fileName);
			}
			String newFileName = System.currentTimeMillis() + AppConstants.FILE_SEPERATOR + fileName;
			Path targetLocation = this.fileStorageLocation.resolve(newFileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			return newFileName;
		} catch (IOException ex) {
			throw new FileStorageException(String.format(AppConstants.FILE_STORAGE_EXCEPTION, fileName), ex);
		}

	}

	@Override
	public Resource loadFileAsResource(String fileName) {

		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new MyFileNotFoundException(AppConstants.FILE_NOT_FOUND + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new MyFileNotFoundException(AppConstants.FILE_NOT_FOUND + fileName, ex);
		}
	}

	@Override
	public void deleteFile(String fileName) throws IOException {

		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			File file = filePath.toFile();
			if (file.delete()) {
				System.out.println(file.getName() + " is deleted!");
			} else {
				System.out.println("Delete operation is failed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
