package osiris.view;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import osiris.interface_adapter.verify.VerifyController;
import osiris.interface_adapter.verify.VerifyViewModel;

/**
 * The View for CAPTCHA Verification.
 */
public class VerifyView extends JPanel implements PropertyChangeListener {

    private final String viewName = "verify";
    private final VerifyViewModel verifyViewModel;
    private final JLabel captchaDisplay;
    private final JTextField captchaField = new JTextField(6);
    private VerifyController verifyController;

    public VerifyView(VerifyViewModel verifyViewModel) {
        this.verifyViewModel = verifyViewModel;
        this.verifyViewModel.addPropertyChangeListener(this);

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("CAPTCHA Verification");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(title, gbc);

        final JLabel captchaLabel = new JLabel("Enter CAPTCHA:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(captchaLabel, gbc);

        captchaField.setPreferredSize(new Dimension(150, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(captchaField, gbc);

        captchaDisplay = new JLabel("CAPTCHA Placeholder");
        captchaDisplay.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(captchaDisplay, gbc);

        JButton verifyButton = new JButton("Verify");
        verifyButton.addActionListener(evt -> handleVerify());
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(verifyButton, gbc);

        final JButton refreshCaptchaButton = new JButton("Refresh CAPTCHA");
        refreshCaptchaButton.addActionListener(evt -> refreshCaptcha());
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(refreshCaptchaButton, gbc);
    }

    private void handleVerify() {
        final String enteredCaptcha = captchaField.getText();
        final String currentCaptcha = verifyViewModel.getState().getCaptchaCode();

        System.out.println("Entered CAPTCHA: " + enteredCaptcha);
        System.out.println("Current CAPTCHA: " + currentCaptcha);

        if (enteredCaptcha == null || enteredCaptcha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the CAPTCHA.");
        }
        else if (!enteredCaptcha.equalsIgnoreCase(currentCaptcha)) {
            JOptionPane.showMessageDialog(this, "CAPTCHA verification failed. Please try again.");
        }
        else {
            JOptionPane.showMessageDialog(this, "CAPTCHA verified successfully!");
            verifyController.execute(enteredCaptcha);
            verifyController.switchToDashboardView();
        }
    }

    public void refreshCaptcha() {
        if (verifyController != null) {
            String captcha = verifyController.generateCaptcha();
            captchaDisplay.setText(captcha);
        }
        else {
            throw new IllegalStateException("VerifyController not set. Unable to refresh captcha.");
        }
    }

    public void setVerifyController(VerifyController verifyController) {
        this.verifyController = verifyController;
        refreshCaptcha();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("captcha".equals(evt.getPropertyName())) {
            String newCaptcha = (String) evt.getNewValue();
            captchaDisplay.setText(newCaptcha);
        }
        else if ("captchaValidation".equals(evt.getPropertyName())) {
            boolean isValid = (boolean) evt.getNewValue();
            if (isValid) {
                JOptionPane.showMessageDialog(this, "Captcha verified successfully!");
            }
            else {
                JOptionPane.showMessageDialog(this, "Invalid captcha. Please try again.");
                refreshCaptcha();
            }
        }
    }

    public String getViewName() {
        return viewName;
    }
}
