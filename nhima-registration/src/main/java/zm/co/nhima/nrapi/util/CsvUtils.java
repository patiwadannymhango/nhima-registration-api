package zm.co.nhima.nrapi.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class CsvUtils {
	
	 private static final CsvMapper mapper = new CsvMapper();
	 
	    public static <T> List<T> readDependent(Class<T> clazz, InputStream stream) throws IOException {
	        CsvSchema schema = mapper.schemaFor(clazz).withHeader()
	        		.sortedBy("principalNhimaNumber","firstName","lastName","gender","dob",
	        				"relationship","email","phone","relationship","email","phone",
	        				"identificationNumber","residentialAddress","province","town","dependentType"); 
	        ObjectReader reader = mapper.readerFor(clazz).with(schema);
	        return reader.<T>readValues(stream).readAll();
	    }
	    
	    public static <T> List<T> readSpouse(Class<T> clazz, InputStream stream) throws IOException {
	        CsvSchema schema = mapper.schemaFor(clazz).withHeader().sortedBy("username","phoneNumber","address");
	        ObjectReader reader = mapper.readerFor(clazz).with(schema);
	        return reader.<T>readValues(stream).readAll();
	    }
	    
	    public static <T> List<T> readPrincipal(Class<T> clazz, InputStream stream) throws IOException {
	        CsvSchema schema = mapper.schemaFor(clazz).withHeader().sortedBy("username","phoneNumber","address");
	        ObjectReader reader = mapper.readerFor(clazz).with(schema);
	        return reader.<T>readValues(stream).readAll();
	    }
}
