package info.novatec.inspectit.rcp.dialog;

import info.novatec.inspectit.communication.data.cmr.Role;
import info.novatec.inspectit.communication.data.cmr.User;
import info.novatec.inspectit.rcp.repository.CmrRepositoryDefinition;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Shell;

/**
 * Dialog for editing users.
 * 
 * @author Mario Rose
 *
 */

public class EditUserDialog extends TitleAreaDialog {
	/**
	 * CmrRepositoryDefinition.
	 */
	private CmrRepositoryDefinition cmrRepositoryDefinition;
	/**
	 * Mail address text box.
	 */
	private Text mailBox;
	
	/**
	 * password text box.
	 */
	private Text passwordBox;

	/**
	 * Edit button.
	 */
	private Button editButton;
	
	/**
	 * Delete user button.
	 */
	private Button deleteUserButton;
	
	/**
	 * Dropdown menu for roles.
	 */
	private Combo roles;
	
	/**
	 * List of all Roles.
	 */
	private List<Role> rolesList;

	/**
	 * The user to edit.
	 */
	private User userOld;
	/**
	 * Reset button id.
	 */
	private static final int EDIT_ID = 0; //IDialogConstants.OK_ID;
	
	/**
	 * Delete user button id.
	 */
	private static final int DELETE_USER_ID = 2;

	/**
	 * Default constructor.
	 * 
	 * @param parentShell
	 *            Parent {@link Shell} to create Dialog on
	 * @param cmrRepositoryDefinition
	 * CmrRepositoryDefinition for easy access to security services.
	 * @param user
	 * the user to edit.
	 */
	public EditUserDialog(Shell parentShell, CmrRepositoryDefinition cmrRepositoryDefinition, User user) {
		super(parentShell);
		this.cmrRepositoryDefinition = cmrRepositoryDefinition;
		rolesList = cmrRepositoryDefinition.getSecurityService().getAllRoles();
		userOld = user;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void create() {
		super.create();
		this.setTitle("Edit User");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		final Composite main = new Composite(parent, SWT.NONE);
		main.setLayout(new GridLayout(2, false));
		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd.widthHint = 400;
		gd.heightHint = 100;
		main.setLayoutData(gd);

		Label mailLabel = new Label(main, SWT.NONE);
		mailLabel.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
		mailLabel.setText("E-Mail:");
		mailBox = new Text(main, SWT.BORDER);
		mailBox.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		mailBox.setText(userOld.getEmail());
		
		Label passwordLabel = new Label(main, SWT.NONE);
		passwordLabel.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
		passwordLabel.setText("Password:");
		passwordBox = new Text(main, SWT.BORDER | SWT.PASSWORD);
		passwordBox.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		passwordBox.setText("");
		
		Label rolesLabel = new Label(main, SWT.NONE);
		rolesLabel.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
		rolesLabel.setText("Role:");
		roles = new Combo(main, SWT.READ_ONLY);
	    for (Role role : rolesList) {
	    	roles.add(role.getTitle());
	    }
	    for (Role role : rolesList) {
	    	if (role.getId() == userOld.getRoleId()) {
	    		roles.select(roles.indexOf(role.getTitle()));
	    	}
	    }

		return main;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		deleteUserButton = createButton(parent, DELETE_USER_ID, "Delete User", true);
		deleteUserButton.setEnabled(true);
		editButton = createButton(parent, EDIT_ID, "Edit", true);
		editButton.setEnabled(true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CLOSE_LABEL, false);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void buttonPressed(int buttonId) {
		if (EDIT_ID == buttonId) {
			editPressed();
		} else if (DELETE_USER_ID == buttonId) {
			deletePressed();
		} else if (IDialogConstants.CANCEL_ID == buttonId) {
			cancelPressed();
		}
	}
	
	/**
	 * Notifies that the edit button has been pressed.
	 */
	private void editPressed() {
		if (userOld.getEmail().equals("guest")) {
			MessageDialog.openWarning(null, "Warning", "This user is required for guest access and can not be edited.");
			return;
		}
		long id = 0;
		boolean passwordChanged = true;
		int index = roles.getSelectionIndex();
	    String mail = mailBox.getText();
	    String password = passwordBox.getText();
	    String role = roles.getItem(index);
	    for (Role r : rolesList) {
	    	if (r.getTitle().equals(role)) {
	    		id = r.getId();
	    	}
	    }
	    if (passwordBox.getText().isEmpty()) {
	    	passwordChanged = false;
	    }
	    cmrRepositoryDefinition.getSecurityService().changeUserAttribute(userOld, mail, password, id, passwordChanged, cmrRepositoryDefinition.getSessionId());
		okPressed();
	}
	
	/**
	 * Notifies that the delete user button has been pressed.
	 */
	private void deletePressed() {
		cmrRepositoryDefinition.getSecurityService().deleteUser(userOld);
		okPressed();
	}
}
