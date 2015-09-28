package info.novatec.inspectit.rcp.wizard.page;


import info.novatec.inspectit.rcp.dialog.ForgotPasswordDialog;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Listener;
import org.apache.shiro.crypto.hash.Sha256Hash;

/**
 * Wizard Page for logging into a CMR.
 * 
 * @author Clemens Geibel
 *
 */
public class CmrLoginWizardPage extends WizardPage {

	/**
	 * Default page message.
	 */
	private static final String DEFAULT_MESSAGE = "CMR Login with your e-mail and password";

	/**
	 * mail address text box.
	 */
	private Text mailBox;

	/**
	 * password text box.
	 */
	private Text passwordBox;

	/**
	 * {@link ForgotPasswordDialog}.
	 */
	private ForgotPasswordDialog forgotPasswordDialog;

	/**
	 * Default constructor.
	 * 
	 * @param title
	 *            title for the login wizard page
	 */
	public CmrLoginWizardPage(String title) {
		super(title);
		this.setTitle(title);
		this.setMessage(DEFAULT_MESSAGE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createControl(Composite parent) {
		final Composite main = new Composite(parent, SWT.NONE);
		main.setLayout(new GridLayout(2, false));

		Label mailLabel = new Label(main, SWT.LEFT);
		mailLabel.setText("E-Mail: ");
		mailBox = new Text(main, SWT.BORDER);
		mailBox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		Label passwordLabel = new Label(main, SWT.LEFT);
		passwordLabel.setText("Password: ");
		passwordBox = new Text(main, SWT.BORDER | SWT.PASSWORD);
		passwordBox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		Button forgotPassword = new Button(main, SWT.PUSH);
		forgotPassword.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
		forgotPassword.setText("Forgot password?");
		forgotPassword.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				resetPasswordDialog(main.getShell());
			}
		});

		Listener pageModifyListener = new Listener() {

			@Override
			public void handleEvent(Event event) {
				setPageComplete(isPageComplete());
				setPageMessage();
			}
		};

		mailBox.addListener(SWT.Modify, pageModifyListener);
		passwordBox.addListener(SWT.Modify, pageModifyListener);

		setControl(main);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isPageComplete() {
		if (mailBox.getText().isEmpty()) {
			return false;
		}
		if (passwordBox.getText().isEmpty()) {
			return false;
		}

		return true;
	}

	/**
	 * Sets the message based on the page selections.
	 */
	private void setPageMessage() {
		if (mailBox.getText().isEmpty()) {
			setMessage("No e-mail address entered", ERROR);
			return;
		}
		if (passwordBox.getText().trim().isEmpty()) {
			setMessage("No password entered", ERROR);
			return;
		}

		setMessage(DEFAULT_MESSAGE);
	}

	/**
	 * Dialog in case "forgot Password" Button is pressed.
	 * 
	 * @param parentShell
	 *            parent shell for the {@link ForgotPasswordDialog}
	 */
	private void resetPasswordDialog(Shell parentShell) {
		forgotPasswordDialog = new ForgotPasswordDialog(parentShell);
		forgotPasswordDialog.open();
	}
	
	/**
	 * Hashed getter for mail.
	 * @return mail
	 */
	public Sha256Hash getMail() {
		return new Sha256Hash(mailBox.getText(), "salt", 1);
		//TODO: Change salt
	}
	
	/**
	 * Hashed getter for password.
	 * @return password
	 */
	public Sha256Hash getPW() {
		return new Sha256Hash(passwordBox.getText(), "salt", 1);
		//TODO: Change salt
	}

}
