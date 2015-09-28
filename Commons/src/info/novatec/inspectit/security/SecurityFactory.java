package info.novatec.inspectit.security;

import org.apache.shiro.crypto.hash.Sha256Hash;
/**
 * Factory for all security services.
 * @author Lucca Hellriegel
 *
 */
public final class SecurityFactory {
	
	/**
	 * Default-Constructor.
	 */
	private SecurityFactory() {
		
	}
	
	/**
	 * Initialize the security.
	 * @param mail 
	 * 			  hashed mail. 
	 * @param password
	 * 				  hashed password.
	 */
	public static void startSecurityFactory(Sha256Hash mail, Sha256Hash password) {
		
	}

}
