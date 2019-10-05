/**
 * 
 */
package org.ness.com;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * @author P7108899
 *
 */
public class BecryptPasswordEncoderExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println("BCryptPasswordEncoder otuput: " + passwordEncoder.encode("majid"));
		
		String noOpOutput = NoOpPasswordEncoder.getInstance().encode("majid");
		System.out.println("NoOpPasswordEncoder otuput: " + noOpOutput);
	}
}
