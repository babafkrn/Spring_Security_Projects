/**
 * 
 */
package org.ness.com;

import java.util.Scanner;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author localadmin
 *
 */
public class AuthenticationSample {

	private static AuthenticationManager authenticationManager = new AuthenticationMangerImpl();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter user name...");
		String userName = sc.nextLine();
		
		System.out.println("Please enter password...");
		String password = sc.nextLine();
		
		try {
			
			Authentication request = new UsernamePasswordAuthenticationToken(userName, password);
			Authentication result = authenticationManager.authenticate(request);
			
			System.out.println("Response authentication object");
			System.out.println("Authorities: " + result.getAuthorities());
			System.out.println("Principal: " + result.getPrincipal());
			System.out.println("Credentials: " + result.getCredentials());
			System.out.println("Is Authenticated: " + result.isAuthenticated());
			System.out.println("Details: " + result.getDetails());
			
			// this code will set the authentication object into thread local
			SecurityContextHolder.getContext().setAuthentication(result);
			
		} catch (AuthenticationException aEx) {
			System.out.println("Authentication failed: " + aEx.getMessage());
			System.exit(1);
		}
		
		System.out.println("Succesfully authenticated: Security context contains: " + SecurityContextHolder.getContext().getAuthentication());
	}
}
