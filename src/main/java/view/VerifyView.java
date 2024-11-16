package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.signup.SignupViewModel;
import interface_adapter.verify.VerifyController;
import interface_adapter.verify.VerifyState;
import interface_adapter.verify.VerifyViewModel;

/**
 * The View for when the user is logged into the program.
 */

public class VerifyView extends JPanel implements PropertyChangeListener {

    private final String viewName = "verify";
    private final VerifyViewModel verifyViewModel;
    private final JTextField verifyCodeField = new JTextField(15);
    private VerifyController verifyController;
    private final JButton goBack;
    private final JButton verifyButton;

    public VerifyView(VerifyViewModel verifyViewModel) {
        this.verifyViewModel = verifyViewModel;
        this.verifyViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(VerifyViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        final LabelTextPanel verifyCodeInfo = new LabelTextPanel(
                new JLabel(verifyViewModel.VERIFY_LABEL), verifyCodeField);

        final JPanel buttons = new JPanel();
        goBack = new JButton(VerifyViewModel.RETURN_BUTTON_LABEL);

        verifyButton = new JButton(VerifyViewModel.VERIFY_BUTTON_LABEL);
        buttons.add(verifyButton);
        buttons.add(goBack);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        verifyButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(verifyButton)) {
                        final VerifyState currentState = verifyViewModel.getState();
                        this.verifyController.execute(
                                currentState.getUsername()
                        );
                    }
                }
        );
        goBack.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        verifyController.switchToSignUpView();
                    }
                }
        );

        this.add(title);
        this.add(verifyCodeInfo);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final VerifyState state = (VerifyState) evt.getNewValue();
        }
        else if (evt.getPropertyName().equals("password")) {
            final VerifyState state = (VerifyState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "password updated for " + state.getUsername());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setVerifyController(VerifyController verifyController) {
        this.verifyController = verifyController;
    }
}
