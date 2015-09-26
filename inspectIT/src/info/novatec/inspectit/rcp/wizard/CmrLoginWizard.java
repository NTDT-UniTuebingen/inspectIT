package info.novatec.inspectit.rcp.wizard;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import info.novatec.inspectit.rcp.wizard.page.CmrLoginWizardPage;
import info.novatec.inspectit.security.SecurityFactory;

/**
 * Wizard for logging into a CMR.
 * 
 * @author Clemens Geibel
 *
 */

public class CmrLoginWizard extends Wizard implements INewWizard {

	/**
	 * {@link CmrLoginWizardPage}.
	 */
	private CmrLoginWizardPage cmrLoginWizardPage;

	/**
	 * Default constructor.
	 */
	public CmrLoginWizard() {
		this.setWindowTitle("CMR Login");
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
	 * {@inheritDoc} Calls SecurityFactory for further authentication.
	 * 
	 */
	@Override
	public boolean performFinish() {
		SecurityFactory.startSecurityFactory(cmrLoginWizardPage.getMail(), cmrLoginWizardPage.getPW());
		
		
		return false;
	}

}
