package info.novatec.inspectit.rcp.dialog;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import info.novatec.inspectit.rcp.dialog.ShowAllRolesDialog;
import info.novatec.inspectit.rcp.dialog.ShowAllUsersDialog;
import info.novatec.inspectit.rcp.dialog.ShowEditablePermissionsDialog;
import info.novatec.inspectit.rcp.repository.CmrRepositoryDefinition;
import info.novatec.inspectit.rcp.dialog.AddRoleDialog;
import info.novatec.inspectit.rcp.dialog.AddUserDialog;
import info.novatec.inspectit.rcp.dialog.SearchUsersDialog;

/**
 * Dialog for managing users on the CMR.
 * 
 * @author Lucca Hellriegel
 * @author Thomas Sachs
 */
public class CmrAdministrationDialog extends TitleAreaDialog {
	/**
	 * Default page message.
	 */
	private static final String DEFAULT_MESSAGE = "Managing users, roles and permissions.";
	/**
	 * CmrRepositoryDefinition for easy access to security services.
	 */
	private CmrRepositoryDefinition cmrRepositoryDefinition;

	/**
	 * The dialog to show available roles.
	 */
	private ShowAllRolesDialog showAllRolesDialog;

	/**
	 * The dialog to show available users.
	 */
	private ShowAllUsersDialog showAllUsersDialog;

	/**
	 * The dialog to search available users.
	 */
	private SearchUsersDialog searchUsersDialog;

	/**
	 * The dialog to add new roles.
	 */
	private AddRoleDialog addRoleDialog;

	/**
	 * The dialog to add new users.
	 */
	private AddUserDialog addUserDialog;
	
	/**
	 * The dialog to show permissions.
	 */
	private ShowEditablePermissionsDialog showEditablePermissionsDialog;
	
	/**
	 * Finish Dialog button.
	 */
	private Button closeButton;
	
	/**
	 * Finish button id.
	 */
	private static final int CLOSE_ID = 0;

	/**
	 * Default constructor.
	 * 
	 * @param title
	 *            title for the CMR Administration Page
	 * @param cmrRepositoryDefinition
	 *            the CmrRepositoryDefinition
	 */
	public CmrAdministrationDialog(Shell parentShell, CmrRepositoryDefinition cmrRepositoryDefinition) {
		super(parentShell);
		this.cmrRepositoryDefinition = cmrRepositoryDefinition;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void create() {
		super.create();
		this.setTitle("CMR Administration");
		this.setMessage(DEFAULT_MESSAGE);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		final Composite main = new Composite(parent, SWT.NONE);
		main.setLayout(new GridLayout(3, true));
		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd.widthHint = 400;
		gd.heightHint = 100;
		main.setLayoutData(gd);

		Button adduser = new Button(main, SWT.CENTER);
		adduser.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
		adduser.setText("Add User");
		adduser.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addUserDialog = new AddUserDialog(main.getShell(), cmrRepositoryDefinition);
				addUserDialog.open();
			}
		});
		
		Button showUsers = new Button(main, SWT.CENTER);
		showUsers.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
		showUsers.setText("Show All Users");
		showUsers.setSize(200, 200);
		showUsers.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showAllUsersDialog = new ShowAllUsersDialog(main.getShell(), cmrRepositoryDefinition);
				showAllUsersDialog.open();
			}
		});

		Button searchUsers = new Button(main, SWT.CENTER);
		searchUsers.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
		searchUsers.setText("Search Users");
		searchUsers.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				searchUsersDialog = new SearchUsersDialog(main.getShell(), cmrRepositoryDefinition);
				searchUsersDialog.open();
			}
		});

		Button addRole = new Button(main, SWT.CENTER);
		addRole.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
		addRole.setText("Add Role");
		addRole.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addRoleDialog = new AddRoleDialog(main.getShell(), cmrRepositoryDefinition);
				addRoleDialog.open();
			}
		});
		
		Button showRoles = new Button(main, SWT.CENTER);
		showRoles.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
		showRoles.setText("Show All Roles");
		showRoles.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showAllRolesDialog = new ShowAllRolesDialog(main.getShell(), cmrRepositoryDefinition);
				showAllRolesDialog.open();
			}
		});

		Button editPermissions = new Button(main, SWT.CENTER);
		editPermissions.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
		editPermissions.setText("Edit Permissions");
		editPermissions.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showEditablePermissionsDialog = new ShowEditablePermissionsDialog(main.getShell(), cmrRepositoryDefinition);
				showEditablePermissionsDialog.open();
			}
		});
		
		return main;
	}
	protected void createButtonsForButtonBar(Composite parent) {
		closeButton = createButton(parent, CLOSE_ID, "Close", true);
		closeButton.setEnabled(true);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void buttonPressed(int buttonId) {
		if (CLOSE_ID == buttonId) {
			cancelPressed();
		}
	}
}
