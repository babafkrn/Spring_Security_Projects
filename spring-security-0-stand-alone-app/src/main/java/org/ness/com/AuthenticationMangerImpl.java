/**
 * 
 */
package org.ness.com;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author localadmin
 *
 */
public class AuthenticationMangerImpl implements AuthenticationManager {

	public UserDetailsService userDetailsService() {
		
		InMemoryUserDetailsManager detailsManager = new InMemoryUserDetailsManager();
		detailsManager.createUser(User.withUsername("vasi").password("password1").roles("ADMIN").build());
		detailsManager.createUser(User.withUsername("sasi").password("password1").roles("USER").build());
		return detailsManager;
	}
	 
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		System.out.println("Incoming user details");
		System.out.println("Authorities: " + authentication.getAuthorities());
		System.out.println("Principal: " + authentication.getPrincipal());
		System.out.println("Credentials: " + authentication.getCredentials());
		System.out.println("Is Authenticated: " + authentication.isAuthenticated());
		System.out.println("Details: " + authentication.getDetails());
		
		UserDetails userDetails =userDetailsService().loadUserByUsername(authentication.getName());
		
		// check whether user details are found
		if(userDetails != null) {
			
			// check password matches
			if(userDetails.getPassword().equals(authentication.getCredentials())) {
				
				if(userDetails.getUsername().equals("vasi")) {
					
					return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), new ArrayList<GrantedAuthority>() {
						{
							add(new SimpleGrantedAuthority("ROLE_ADMIN"));
						}
					});
				}
			} else {
				System.out.println("User is: " + userDetails.getUsername());
				
				return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), new ArrayList<GrantedAuthority>() {
					{
						add(new SimpleGrantedAuthority("ROLE_USER"));
					}
				});
			}
		} else {
			
			// no user found
			throw new BadCredentialsException("Bad Credentials");
		}
		
		return null;
	}

}
