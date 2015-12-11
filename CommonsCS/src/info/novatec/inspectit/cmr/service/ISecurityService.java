package info.novatec.inspectit.cmr.service;

import java.io.Serializable;
import java.util.List;

import info.novatec.inspectit.communication.data.cmr.Permission;
import info.novatec.inspectit.communication.data.cmr.Role;
import info.novatec.inspectit.communication.data.cmr.User;

/**
 * Provides general security operations for client<->cmr interaction.
 * 
 * @author Andreas Herzog
 * @author Clemens Geibel
 */
@ServiceInterface(exporter = ServiceExporterType.HTTP)
public interface ISecurityService {
	/**
	 * Authentication via the CmrSecurityManager.
	 * 
	 * @param pw
	 *            users password
	 * @param email
	 *            email
	 * @return sessionId if the user was authenticated
	 */
	Serializable authenticate(String pw, String email);

	/**
	 * Ends the session.
	 * 
	 * @param sessionId
	 *            Session id from the session to end
	 */
	void logout(Serializable sessionId);

	/**
	 * Checks whether session of a specific sessionId exists.
	 * 
	 * @param sessionId
	 *            The id to check.
	 * @return Boolean whether the session exists.
	 */
	boolean existsSession(Serializable sessionId);

	/**
	 * Returns titles of permissions as Strings.
	 * 
	 * @param sessionId
	 *            sessionId
	 * @return List with the users permissions.
	 */
	List<String> getPermissions(Serializable sessionId);

	/**
	 * Returns a Role object with given Email.
	 * 
	 * @param email
	 *            email
	 * @return a Role object with given Email.
	 * @throws AuthenticationException
	 *             if the email was not found.
	 */
	Role retrieveRole(String email);

	/**
	 * Searches for the Role matching a given ID.
	 * 
	 * @param id
	 *            RoleID.
	 * @return Role with this ID.
	 */
	Role getRoleByID(long id);

	/**
	 * Adds a new User to the Database. Throws an exception, if there is an existing registered User
	 * with the given email-address. Throws an exception, if the given role-id does not exist.
	 * 
	 * @param user
	 *            user
	 */
	void addUser(User user);

	/**
	 * Deletes the given User Object from the Database.
	 * 
	 * @param user
	 *            user
	 */
	void deleteUser(User user);

	/**
	 * Change any attribute of a User. Email cannot be changed.
	 * 
	 * @param user
	 *            user
	 */
	void changeUserAttribute(User user);

	/**
	 * Change the description of a Permission. Other changes should not be possible.
	 * 
	 * @param permission
	 *            permission
	 */
	void changePermissionDescription(Permission permission);
}
