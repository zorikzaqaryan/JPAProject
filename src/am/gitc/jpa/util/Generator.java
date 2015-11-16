package am.gitc.jpa.util;

import java.util.*;

/**
 * Created by Garik on 12/6/14.
 */
public class Generator {

	public static String doToken() {
		String token = UUID.randomUUID().toString();
		return token.replace("-", "");
	}

	public static void main(String[] args) {
		System.out.println(doToken());
	}
}
