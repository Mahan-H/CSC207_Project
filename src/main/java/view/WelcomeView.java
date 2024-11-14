package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import interface_adapter.welcome.WelcomeController;
import interface_adapter.welcome.WelcomeState;
import interface_adapter.welcome.WelcomeViewModel;

/**
 * The View for the Signup Use Case.
 */
public class WelcomeView extends JPanel implements ActionListener {
    private final String viewName = "welcome";

    private final WelcomeViewModel welcomeupViewModel;
    private WelcomeController welcomeController;

    private final JButton toLogin;
    private final JButton createAccount;
    private final JButton emptyButton;

    public WelcomeView(WelcomeViewModel welcomeupViewModel) {
        this.welcomeupViewModel = welcomeupViewModel;

        final JLabel title = new JLabel(WelcomeViewModel.TITLE_LABEL);
        title.setFont(new Font("Times New Roman", Font.BOLD, 60));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel slogan = new JLabel(WelcomeViewModel.SLOGAN_LABEL);
        slogan.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        slogan.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel titles = new JPanel();
        titles.add(title);
        titles.add(slogan);

        final JPanel buttons = new JPanel();

        toLogin = new JButton(welcomeupViewModel.LOGIN_BUTTON_LABEL);
        toLogin.setForeground(Color.BLACK);
        toLogin.setBackground(new Color(234, 210, 137));
        toLogin.setFont(new Font("Times New Roman", Font.BOLD, 20));
        toLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        toLogin.setPreferredSize(new Dimension(200, 40));

        emptyButton = new JButton(" ");
        emptyButton.setOpaque(false);
        emptyButton.setContentAreaFilled(false);
        emptyButton.setBorderPainted(false);
        emptyButton.setFocusPainted(false);

        createAccount = new JButton(welcomeupViewModel.CREATE_ACCOUNT_BUTTON_LABEL);
        createAccount.setForeground(Color.BLACK);
        createAccount.setBackground(new Color(234, 210, 137));
        createAccount.setFont(new Font("Times New Roman", Font.BOLD, 20));
        createAccount.setAlignmentX(Component.CENTER_ALIGNMENT);
        createAccount.setPreferredSize(new Dimension(200, 40));

        buttons.add(emptyButton);
        buttons.add(toLogin);
        buttons.add(createAccount);

        toLogin.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        welcomeController.switchToLoginView();
                    }
                }
        );

        createAccount.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        welcomeController.switchToSignUpView();
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(titles);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(this, "Cancel not implemented yet.");
    }

    public String getViewName() {
        return viewName;
    }

    public void setWelcomeController(WelcomeController controller) {
        this.welcomeController = controller;
    }
}
