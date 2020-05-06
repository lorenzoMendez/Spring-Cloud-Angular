package microservice.springcloud.student.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailUtils {
	
	private static final Pattern EMAIL_PATTERN = Pattern.compile( "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$" );
	
	public static boolean validateEmail( String email ) {
		Matcher matcher = EMAIL_PATTERN.matcher( email );
		
		return matcher.find();
	}
	
}
