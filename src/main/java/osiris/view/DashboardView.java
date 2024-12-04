package osiris.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import osiris.interface_adapter.dashboard.DashboardController;
import osiris.interface_adapter.dashboard.DashboardViewModel;
import osiris.interface_adapter.welcome.WelcomeViewModel;


/**
 * The View for the Dashboard Use Case.
 */
public class DashboardView extends JPanel implements ActionListener {

    public static final String TIMES_NEW_ROMAN = "Times New Roman";
    public static final int TITTLE_SIZE = 60;
    public static final int SLOGAN_SIZE = 30;
    public static final Color COLOR = new Color(239, 221, 206);
    public static final int BUTTON_FONT_SIZE = 18;
    public static final Dimension PREFERRED_SIZE_BUTTON = new Dimension(150, 150);
    private final String viewName = "dashboard";

    private final DashboardViewModel dashboardViewModel;
    private DashboardController dashboardController;

    private final JButton viewTransactionHistory;
    private final JButton viewShahCase;
    private final JButton viewBudget;

    public DashboardView(DashboardViewModel dashboardViewModel) {
        this.dashboardViewModel = dashboardViewModel;

        final JLabel title = new JLabel(WelcomeViewModel.TITLE_LABEL);
        title.setFont(new Font(TIMES_NEW_ROMAN, Font.BOLD, TITTLE_SIZE));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel slogan = new JLabel(WelcomeViewModel.SLOGAN_LABEL);
        slogan.setFont(new Font(TIMES_NEW_ROMAN, Font.PLAIN, SLOGAN_SIZE));
        slogan.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel titles = new JPanel();
        titles.add(title);
        titles.add(slogan);

        final JPanel buttons = new JPanel();

        viewTransactionHistory = new JButton(dashboardViewModel.VIEW_TRANSACTION_HISTORY_BUTTON_LABEL);
        viewTransactionHistory.setForeground(Color.BLACK);
        viewTransactionHistory.setBackground(COLOR);
        viewTransactionHistory.setFont(new Font(TIMES_NEW_ROMAN, Font.BOLD, BUTTON_FONT_SIZE));
        viewTransactionHistory.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewTransactionHistory.setPreferredSize(PREFERRED_SIZE_BUTTON);

        viewBudget = new JButton(dashboardViewModel.VIEW_BUDGET_BUTTON_LABEL);
        viewBudget.setForeground(Color.BLACK);
        viewBudget.setBackground(COLOR);
        viewBudget.setFont(new Font(TIMES_NEW_ROMAN, Font.BOLD, BUTTON_FONT_SIZE));
        viewBudget.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewBudget.setPreferredSize(PREFERRED_SIZE_BUTTON);

        viewShahCase = new JButton(dashboardViewModel.VIEW_SHAHCASE_BUTTON_LABEL);
        viewShahCase.setForeground(Color.BLACK);
        viewShahCase.setBackground(COLOR);
        viewShahCase.setFont(new Font(TIMES_NEW_ROMAN, Font.BOLD, BUTTON_FONT_SIZE));
        viewShahCase.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewShahCase.setPreferredSize(PREFERRED_SIZE_BUTTON);

        buttons.add(viewShahCase);
        buttons.add(viewTransactionHistory);
        buttons.add(viewBudget);

        viewBudget.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        dashboardController.switchToBudgetView();
                    }
                }
        );

        viewTransactionHistory.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        dashboardController.switchToViewExpenses();
                    }
                }
        );

        viewShahCase.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        dashboardController.switchToLoginView();
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

    public void setDashboardController(DashboardController controller) {
        this.dashboardController = controller;
    }
}
