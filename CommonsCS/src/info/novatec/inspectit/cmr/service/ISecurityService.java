package info.novatec.inspectit.cmr.service;

import java.util.List;

import info.novatec.inspectit.communication.data.cmr.Permission;
import info.novatec.inspectit.communication.data.cmr.Role;
import info.novatec.inspectit.communication.data.cmr.User;

/**
 * Provides general security operations for client<->cmr interaction.
 * 
 * @author Andreas Herzog
 * @author Clemens Geibel
 * @author Lucca Hellriegel
 * @author Joshua Hartmann
 */
@ServiceInterface(exporter = ServiceExporterType.HTTP)
public interface ISecurityService {	
	/**
	 * Searches for a User by the email-address and returns the User Object, if the password is correct.
	 * @param email Email.
	 * @param pw password in plain text. to be edited.
	 * @return User by Email.
	 */
	List<String> authenticate(String pw, String email);
	
	// | ROLE | --------------
	/**
	 * Returns a Role object with given Email of the user.
	 * @param email email
	 * @return a Role object with given Email of the user.
	 * @throws AuthenticationException if the email was not found.
	 */
	Role getRoleOfUser(String email);	
	/**
	 * Searches for the Role matching a given ID.
	 * @param id RoleID.
	 * @return Role with this ID.
	 */
	Role getRoleByID(long id);
	/**
	 * Retrieves all existing roles.
	 * @return An Array containing all Roles
	 */
	Role[] getAllRoles();
	
	
	// | USER |---------------
	/**
	 * We only want to send the user emails to the client. If a user is about to be modified, other data will be retrieved.
	 * @return An Array containing all user emails
	 */
	String[] getAllUsers();
	/**
	 * Adds a new User to the Database.
	 * Throws an exception, if there is an existing registered User with the given email-address.
	 * Throws an exception, if the given role-id does not exist.
	 * @param user user
	 */
	void addUser(User user);
	/**
	 * Returns the user object with the given email.
	 * @param email Email address of the user.
	 * @return The user object.
	 */
	User getUser(String email);
	
	/**
	 * Deletes the given User Object from the Database.
	 * @param user user
	 */
	void deleteUser(User user);
	
	/**
	 * Change any attribute of a User.
	 * Email cannot be changed.
	 * @param user user
	 */
	void changeUserAttribute(User user);
	
	
	// | PERMISSION |---------
	/**
	 * Change the description of a Permission.
	 * Other changes should not be possible.
	 * @param permission permission
	 */
	void changePermissionDescription(Permission permission);
	
	/**
	 * Retrieves all existing permissions.
	 * @return An Array containing all Roles
	 */
	Permission[] getAllPermissions();
}
