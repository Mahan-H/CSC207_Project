package osiris.use_case.viewexpenses;

import osiris.use_case.viewexpenses.ViewExpensesOutputData;

public interface ViewExpensesOutputBoundary {
    /**
     * Handles the output data for viewing expenses.
     *
     * @param outputData Output data containing the result of the use case.
     */
    void present(ViewExpensesOutputData outputData);
}
