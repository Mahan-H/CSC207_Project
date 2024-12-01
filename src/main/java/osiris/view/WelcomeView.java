package osiris.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jetbrains.annotations.NotNull;

import osiris.interface_adapter.welcome.WelcomeController;
import osiris.interface_adapter.welcome.WelcomeViewModel;

/**
 * The View for the Signup Use Case.
 */
public class WelcomeView extends JPanel implements ActionListener {
    public static final String TIMES_NEW_ROMAN = "Times New Roman";
    public static final int TITTLE_SIZE = 60;
    public static final int SLOGAN_SIZE = 30;
    public static final Color COLOR = new Color(239, 221, 206);
    public static final int LOGIN_BUTTON_FONT_SIZE = 20;
    public static final Dimension PREFERRED_SIZE_LOGIN_BUTTON = new Dimension(200, 40);
    private final String viewName = "welcome";

    private final WelcomeViewModel welcomeViewModel;
    private WelcomeController welcomeController;

    private final JButton toLogin;
    private final JButton createAccount;
    private final JButton emptyButton;

    public WelcomeView(WelcomeViewModel welcomeViewModel) {
        this.welcomeViewModel = welcomeViewModel;

        final JLabel title = getTitleLabel(WelcomeViewModel.TITLE_LABEL, Font.BOLD, TITTLE_SIZE);

        final JLabel slogan = getTitleLabel(WelcomeViewModel.SLOGAN_LABEL, Font.PLAIN, SLOGAN_SIZE);

        final JPanel titles = new JPanel();
        titles.add(title);
        titles.add(slogan);

        final JPanel buttons = new JPanel();

        toLogin = new JButton(welcomeViewModel.LOGIN_BUTTON_LABEL);
        toLogin.setForeground(Color.BLACK);
        toLogin.setBackground(COLOR);
        toLogin.setFont(new Font(TIMES_NEW_ROMAN, Font.BOLD, LOGIN_BUTTON_FONT_SIZE));
        toLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        toLogin.setPreferredSize(PREFERRED_SIZE_LOGIN_BUTTON);

        emptyButton = new JButton(" ");
        emptyButton.setOpaque(false);
        emptyButton.setContentAreaFilled(false);
        emptyButton.setBorderPainted(false);
        emptyButton.setFocusPainted(false);

        createAccount = new JButton(welcomeViewModel.CREATE_ACCOUNT_BUTTON_LABEL);
        createAccount.setForeground(Color.BLACK);
        createAccount.setBackground(COLOR);
        createAccount.setFont(new Font(TIMES_NEW_ROMAN, Font.BOLD, LOGIN_BUTTON_FONT_SIZE));
        createAccount.setAlignmentX(Component.CENTER_ALIGNMENT);
        createAccount.setPreferredSize(PREFERRED_SIZE_LOGIN_BUTTON);

        addButtons(buttons);

        addLoginButton();

        addCreateAccountButton();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(titles);
        this.add(buttons);
    }

    private void addButtons(JPanel buttons) {
        buttons.add(emptyButton);
        buttons.add(toLogin);
        buttons.add(createAccount);
    }

    @NotNull
    private JLabel getTitleLabel(String titleLabel, int bold, int tittleSize) {
        final JLabel title = new JLabel(titleLabel);
        title.setFont(new Font(TIMES_NEW_ROMAN, bold, tittleSize));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        return title;
    }

    private void addCreateAccountButton() {
        createAccount.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        welcomeController.switchToSignUpView();
                    }
                }
        );
    }

    private void addLoginButton() {
        toLogin.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        welcomeController.switchToLoginView();
                    }
                }
        );
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
