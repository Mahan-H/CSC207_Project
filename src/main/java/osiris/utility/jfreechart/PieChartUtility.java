package osiris.utility.jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.util.Map;

public class PieChartUtility {

    public static void displayPieChart(double essential, double nonEssential) {
        // Create dataset
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Essential", essential);
        dataset.setValue("Non-Essential", nonEssential);

        // Create chart
        JFreeChart chart = ChartFactory.createPieChart(
                "Expense Breakdown",
                dataset,
                true,
                true,
                false
        );

        // Display chart in JFrame
        JFrame frame = new JFrame("Pie Chart");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new ChartPanel(chart));
        frame.pack();
        frame.setVisible(true);
    }

}
