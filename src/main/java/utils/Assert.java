package utils;

import org.assertj.core.api.Assertions;

public class Assert {
	
	public static void isValueEqual(String expected, String actual) {
		Assertions.assertThat(actual).isEqualTo(expected);
	}
	
	public static void isValueEqual(int expected, int actual) {
		Assertions.assertThat(actual).isEqualTo(expected);
	}
	
	public static void isTrue(boolean condition) {
		Assertions.assertThat(true).isEqualTo(condition);
	}
	
	public static void isNotNullOrEmpty(String expected) throws Exception {
		Assertions.assertThat(expected).isNotNull();
		if(expected == "") {
			throw new Exception("Access Token is empty");
		}
	}
}
