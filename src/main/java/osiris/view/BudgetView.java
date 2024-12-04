package osiris.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import osiris.interface_adapter.budget.BudgetController;
import osiris.interface_adapter.budget.BudgetViewModel;
import osiris.interface_adapter.grabtransaction.GrabTransactionController;
import osiris.interface_adapter.budget.BudgetState;
import osiris.interface_adapter.budget.BudgetViewModel;
import osiris.interface_adapter.viewexpenses.ViewExpensesController;
import osiris.use_case.grabtransactions.GrabTransactionOutputData;
import osiris.utility.exceptions.PlaidUseCaseException;

/**
 * The View for when the user viewing the budget.
 */
public class BudgetView extends JPanel implements PropertyChangeListener {
    private final String viewName = "budget";
    private final BudgetViewModel budgetViewModel;
    private BudgetController controller;
    private final JButton goBack;
    private final JButton budgetButton;
    private GrabTransactionController grabTransactionController;

    public BudgetView(BudgetViewModel budgetViewModel) {
        this.budgetViewModel = budgetViewModel;

        final JLabel title = new JLabel(BudgetViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        final JPanel tablePanel = new JPanel();
        final JPanel buttons = new JPanel();
        budgetButton = new JButton(BudgetViewModel.BUTTON_LABEL);
        goBack = new JButton(BudgetViewModel.BACK_LABEL);

        buttons.add(budgetButton);
        buttons.add(goBack);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        budgetButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        try {
                            final GrabTransactionOutputData transactions = grabTransactionController.createTransactions(
                                    "divnoor");
                            controller.execute(String.valueOf(transactions));
                            final BudgetState currentState = budgetViewModel.getState();
                            ArrayList<Double> monthlyTotals = currentState.getMonthlyTotals();
                            String[][] tableData = {
                                    {"January", String.valueOf(monthlyTotals.get(0))},
                                    {"February", String.valueOf(monthlyTotals.get(1))},
                                    {"March", String.valueOf(monthlyTotals.get(2))},
                                    {"April", String.valueOf(monthlyTotals.get(3))},
                                    {"May", String.valueOf(monthlyTotals.get(4))},
                                    {"June", String.valueOf(monthlyTotals.get(5))},
                                    {"July", String.valueOf(monthlyTotals.get(6))},
                                    {"August", String.valueOf(monthlyTotals.get(7))},
                                    {"September", String.valueOf(monthlyTotals.get(8))},
                                    {"October", String.valueOf(monthlyTotals.get(9))},
                                    {"November", String.valueOf(monthlyTotals.get(10))},
                                    {"December", String.valueOf(monthlyTotals.get(11))}
                            };
                            String[] columnNames = {"Months", "Total Expenses" };
                            JTable table = new JTable(tableData, columnNames);
                            JScrollPane scrollPane = new JScrollPane(table);
                            tablePanel.add(scrollPane);
                        }
                        catch (PlaidUseCaseException ex) {
                            throw new RuntimeException(ex);
                        }
                        catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });

        goBack.addActionListener(evt -> controller.switchToHomeView());

        this.add(title);
        this.add(buttons);
        this.add(tablePanel);

    }

    public void setBudgetController(BudgetController budgetController) {
        this.controller = budgetController;
    }

    public void setGrabTransactionController(GrabTransactionController grabTransactionController) {
        this.grabTransactionController = grabTransactionController;
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }

}
