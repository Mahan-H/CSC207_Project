package osiris.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lombok.Getter;
import osiris.interface_adapter.grabtransaction.GrabTransactionController;
import osiris.interface_adapter.viewexpenses.ViewExpensesController;
import osiris.interface_adapter.viewexpenses.ViewExpensesState;
import osiris.interface_adapter.viewexpenses.ViewExpensesViewModel;
import osiris.use_case.grabtransactions.GrabTransactionOutputData;
import osiris.utility.exceptions.PlaidUseCaseException;
import osiris.utility.jfreechart.PieChartUtility;
// import osiris.use_case.viewexpenses.ViewExpensesInteractor;

/**
 * The View for when the user is logged into the program.
 */

public class ViewExpenses extends JPanel implements PropertyChangeListener {

    @Getter
    private final String viewName = "view expenses";
    private final ViewExpensesViewModel viewExpensesViewModel;
    private ViewExpensesController controller;
    private final JButton goBack;
    private final JButton expensesButton;
    private GrabTransactionController grabTransactionController;

    public ViewExpenses(ViewExpensesViewModel viewExpensesViewModel) {
        this.viewExpensesViewModel = viewExpensesViewModel;

        final JLabel title = new JLabel(ViewExpensesViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        final JPanel buttons = new JPanel();
        expensesButton = new JButton(ViewExpensesViewModel.BUTTON_LABEL);
        goBack = new JButton(ViewExpensesViewModel.BACK_LABEL);

        buttons.add(expensesButton);
        buttons.add(goBack);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        expensesButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        try {
                            final GrabTransactionOutputData transactions = grabTransactionController.createTransactions(
                                    "divnoor");
                            controller.execute(String.valueOf(transactions));
                            final ViewExpensesState currentState = viewExpensesViewModel.getState();
                            PieChartUtility.displayPieChart(currentState.getEssential(), currentState.getNonEssential()
                            );
                        }
                        catch (PlaidUseCaseException ex) {
                            throw new RuntimeException(ex);
                        }
                        catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });

        // expensesButton.addActionListener(
        // new ActionListener() {
        // public void actionPerformed(ActionEvent evt) {
        // PieChartUtility.displayPieChart(200.9, 300.45);
        // }
        // });

        goBack.addActionListener(evt -> controller.switchToHomeView());

        this.add(title);
        this.add(buttons);
    }

    public void setViewExpensesController(ViewExpensesController viewExpensesController) {
        this.controller = viewExpensesController;
    }

    public void setGrabTransactionController(GrabTransactionController grabTransactionController) {
        this.grabTransactionController = grabTransactionController;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }
}

