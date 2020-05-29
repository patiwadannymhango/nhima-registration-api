package zm.co.nhima.nrapi.util;

public class AppConstants {
	
	public static final String SUCCESS_MSG = "Record created successfully";
	public static final String SUBMIT_MSG = "Record submitted successfully";
	public static final String EDIT_MSG = "Record updated successfully";
	public static final String PASSWORD_CHANGED_MSG = "Password successfully changed";
	
	public static final String SUCCESS_MSG_BULK = "Bulk csv registration successfully done";
	public static final String TOKEN_EXPIRED = "Token has expired please reset password again";
	public static final String EMAIL_SENT_MSG = "Please check your email a password Reset Link has been sent to your email.";
	
	public static final String NO_FILE_ATTACHED_ERROR ="No File Attached";
	public static final String FILE_UPLOAD_ERROR="File Upload Error";
	public static final String SUBMIT_ERROR ="Submit Error";
	public static final String AGE_ERROR="Age Error";
	public static final String DELETE_ERROR = "Delete Error";
	
	public static final String FILE_SEPERATOR = "_";
	public static final String DEFAULT_CONTENT_TYPE = "application/octet-stream";
	public static final String FILE_DOWNLOAD_HTTP_HEADER = "inline; filename=\"%s\"";
	public static final String FILE_PROPERTIES_PREFIX = "file";
	
	public static final String DOWNLOAD_PATH_DASHBOARD = "/dashboard/downloadFile/";
	public static final String DOWNLOAD_PATH_ADMIN = "/admin/downloadFile/";
	public static final String DOWNLOAD_URI = "/downloadFile/{fileName:.+}";
	public static final String DOWNLOAD_URI_ADMIN = "/admin/downloadFile/{fileName:.+}";
	
	public static final String FILE_STORAGE_EXCEPTION_PATH_NOT_FOUND = "Could not create the directory where the uploaded files will be stored";
	public static final String INVALID_FILE_PATH_NAME = "Sorry! Filename contains invalid path sequence";
	public static final String FILE_NOT_FOUND = "File not found ";
	public static final String FILE_STORAGE_EXCEPTION = "Could not store file %s !! Please try again!";
	public static final CharSequence INVALID_FILE_DELIMITER = "..";
	public static final String INDEX_PAGE_URI = "/index";
	public static final String INDEX_PAGE = "index";
	public static final String INVALID_FILE_DIMENSIONS = "Invalid file dimensions.";
	public static final String INVALID_FILE_FORMAT = "Only PDF,DOC, PNG, JPEG and JPG formats are allowed";
	public static final String PNG_FILE_FORMAT = ".png";
	public static final String JPEG_FILE_FORMAT = ".jpeg";
	public static final String JPG_FILE_FORMAT = ".jpg";
	public static final String PDF_FILE_FORMAT = ".pdf";
	public static final String DOC_FILE_FORMAT = ".doc";
	public static final String DOCX_FILE_FORMAT = ".docx";

	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	
	public static final String DEFAULT_PASSWORD = "Pass123@#$";
	public static String TEMP_DIR;
	

	// statuses	      
	public static final String INCOMPLETE = "INCOMPLETE";
	public static final String COMPLETED = "COMPLETED";
	
	

	public AppConstants() {
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			TEMP_DIR = "C://TEMP//";
		} else {
			TEMP_DIR = "/tmp/";
		}
	}
}
