package osiris.utility.jfreechart;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 * The Class that helps generate pie chart for ViewExpenses UseCase.
 */
public class PieChartUtility {

    /**
     * The Class that helps displays pie chart for ViewExpenses UseCase.
     * @param essential a double of essential value
     * @param nonEssential a double of essential value
     */
    public static void displayPieChart(double essential, double nonEssential) {
        // Create dataset
        final DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Essential", essential);
        dataset.setValue("Non-Essential", nonEssential);

        // Create chart
        final JFreeChart chart = ChartFactory.createPieChart(
                "Expense Breakdown",
                dataset,
                true,
                true,
                false
        );

        // Display chart in JFrame
        final JFrame frame = new JFrame("Pie Chart");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new ChartPanel(chart));
        frame.pack();
        frame.setVisible(true);
    }

}
