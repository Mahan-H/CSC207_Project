package osiris.view;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import osiris.interface_adapter.verify.VerifyController;
import osiris.interface_adapter.verify.VerifyState;
import osiris.interface_adapter.verify.VerifyViewModel;

/**
 * The View for CAPTCHA Verification.
 */

public class VerifyView extends JPanel implements PropertyChangeListener {

    private final String viewName = "verify";
    private final VerifyViewModel verifyViewModel;
    private final JLabel captchaDisplay;
    private final JTextField captchaField = new JTextField(6);
    private JLabel captchaLabel;
    private VerifyController verifyController;
    private final JButton verifyButton;
    private final JButton refreshCaptchaButton;

    public VerifyView(VerifyViewModel verifyViewModel) {
        this.verifyViewModel = verifyViewModel;
        this.verifyViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("CAPTCHA Verification");
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel captchaLabel = new JLabel("Enter CAPTCHA:");
        captchaField.setPreferredSize(new Dimension(150, 30)); // Width: 150px, Height: 30px
        captchaField.setMaximumSize(new Dimension(150, 30));
        this.verifyButton = new JButton("Verify");
        verifyButton.addActionListener(evt -> handleVerify());

        // Add the button to the panel
        this.add(verifyButton);

        captchaDisplay = new JLabel("CAPTCHA Placeholder");
        refreshCaptchaButton = new JButton("Refresh CAPTCHA");

        // Add action listener to refresh button
        refreshCaptchaButton.addActionListener(evt -> refreshCaptcha());

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.add(captchaLabel);
        formPanel.add(captchaField);
        formPanel.add(captchaDisplay);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(refreshCaptchaButton);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(formPanel);
        this.add(buttonPanel);
    }

    private void handleVerify() {
        // Get the entered CAPTCHA response from the text field
        String enteredCaptcha = captchaField.getText();
        String currentCaptcha = verifyViewModel.getState().getCaptchaCode();

        // Check if the entered CAPTCHA matches the generated CAPTCHA
        if (enteredCaptcha == null || enteredCaptcha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the CAPTCHA.");
        } else if (!enteredCaptcha.equalsIgnoreCase(currentCaptcha)) {
            JOptionPane.showMessageDialog(this, "CAPTCHA verification failed. Please try again.");
        } else {
            JOptionPane.showMessageDialog(this, "CAPTCHA verified successfully!");
            verifyController.execute(enteredCaptcha);
        }
    }

    // Refreshes the displayed captcha using the controller
    public void refreshCaptcha() {
        if (verifyController != null) {
            String captcha = verifyController.generateCaptcha();
            captchaDisplay.setText(captcha);
            verifyViewModel.getState().setCaptchaCode(captcha);
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
            // Update captcha display when captcha changes
            String newCaptcha = (String) evt.getNewValue();
            captchaDisplay.setText(newCaptcha);
        }
        else if ("captchaValidation".equals(evt.getPropertyName())) {
            // Handle captcha validation result
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