package osiris.interface_adapter.viewexpenses;


import osiris.use_case.viewexpenses.ViewExpensesOutputBoundary;
import osiris.use_case.viewexpenses.ViewExpensesOutputData;
import osiris.utility.jfreechart.PieChartUtility;

import java.util.HashMap;
import java.util.Map;

public class ViewExpensesPresenter implements ViewExpensesOutputBoundary {

    @Override
    public void present(ViewExpensesOutputData outputData) {
        // Prepare data for the pie chart

        // Generate pie chart
        PieChartUtility.displayPieChart(outputData.getEssentialTotal(), outputData.getNonEssentialTotal());
    }


}
