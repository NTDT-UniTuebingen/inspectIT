package info.novatec.inspectit.rcp.wizard;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import info.novatec.inspectit.communication.data.cmr.Role;
import info.novatec.inspectit.rcp.repository.CmrRepositoryDefinition;
import info.novatec.inspectit.rcp.wizard.page.CmrLoginWizardPage;

/**
 * Wizard for logging into a CMR.
 * 
 * @author Clemens Geibel
 * @author Andreas Herzog
 */

public class CmrLoginWizard extends Wizard implements INewWizard {

	/**
	 * test.
	 */
	private CmrRepositoryDefinition cmrRepositoryDefinition;

	/**
	 * {@link CmrLoginWizardPage}.
	 */
	private CmrLoginWizardPage cmrLoginWizardPage;

	/**
	 * Default constructor.
	 * 
	 * @param cmrRepositoryDefinition
	 *            .
	 */
	public CmrLoginWizard(CmrRepositoryDefinition cmrRepositoryDefinition) {
		this.setWindowTitle("CMR Login");
		this.cmrRepositoryDefinition = cmrRepositoryDefinition;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addPages() {
		cmrLoginWizardPage = new CmrLoginWizardPage("CMR Login");
		addPage(cmrLoginWizardPage);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
	}

	/**
	 * {@inheritDoc} Tries to log into the CMR.
	 */
	@Override
	public boolean performFinish() {
		if (cmrRepositoryDefinition.getSecurityService().authenticate(cmrLoginWizardPage.getPasswordBox().getText(),
				cmrLoginWizardPage.getMailBox().getText())) {
			MessageDialog.openInformation(null, "Successfully authenticated at selected CMR", "You are now logged in.");
			return true;

		} else {
			MessageDialog.openError(null, "Login failed", "E-Mail or Password is incorrect!");
		}
		return false;
	}

}
