package info.novatec.inspectit.rcp.security;

import info.novatec.inspectit.cmr.service.ISecurityService;

import java.util.Collection;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;


/**
 * SecurityManager for InspectIT implements the shiro-security-manager.
 * 
 * @author Andreas Herzog
 *
 */
public class CmrSecurityManager implements SecurityManager {
	
	/**
	 * The only realm of this Manager to connect with the Cmr Security Service.
	 */
	private ISecurityService securityService;
	
	/**
	 * The Manager is constructed via the CmrRepositoryDefinition.
	 * 
	 * @param securityService .
	 */
	public CmrSecurityManager(ISecurityService securityService) {
		this.securityService = securityService;
	}

	@Override
	public AuthenticationInfo authenticate(AuthenticationToken arg0) throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void checkPermission(PrincipalCollection arg0, String arg1) throws AuthorizationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkPermission(PrincipalCollection arg0, Permission arg1) throws AuthorizationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkPermissions(PrincipalCollection arg0, String... arg1) throws AuthorizationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkPermissions(PrincipalCollection arg0, Collection<Permission> arg1) throws AuthorizationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkRole(PrincipalCollection arg0, String arg1) throws AuthorizationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkRoles(PrincipalCollection arg0, Collection<String> arg1) throws AuthorizationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkRoles(PrincipalCollection arg0, String... arg1) throws AuthorizationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasAllRoles(PrincipalCollection arg0, Collection<String> arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasRole(PrincipalCollection arg0, String arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean[] hasRoles(PrincipalCollection arg0, List<String> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPermitted(PrincipalCollection arg0, String arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPermitted(PrincipalCollection arg0, Permission arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean[] isPermitted(PrincipalCollection arg0, String... arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean[] isPermitted(PrincipalCollection arg0, List<Permission> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPermittedAll(PrincipalCollection arg0, String... arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPermittedAll(PrincipalCollection arg0, Collection<Permission> arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Session getSession(SessionKey arg0) throws SessionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Session start(SessionContext arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subject createSubject(SubjectContext arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subject login(Subject arg0, AuthenticationToken arg1) throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void logout(Subject arg0) {
		// TODO Auto-generated method stub
		
	}

	public ISecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(ISecurityService securityService) {
		this.securityService = securityService;
	}

}
