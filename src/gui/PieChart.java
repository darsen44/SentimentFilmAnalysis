package gui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieItemLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.text.NumberFormat;

public class PieChart {
    public PieChart(String item,String resultLine) {
        resultLine = resultLine.replace(item,"");
        int[] values = values(resultLine);
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        pieDataset.setValue("Позитивні", values[0]);
        pieDataset.setValue("Негативні", values[1]);
        pieDataset.setValue("Нейтральні",values[2]);
        JFreeChart freeChart = ChartFactory.createPieChart3D(item, pieDataset, false, true, false);
        PiePlot3D p = (PiePlot3D) freeChart.getPlot();
        p.setSectionPaint(0,Color.GREEN);
        p.setSectionPaint(1,Color.RED);
        p.setSectionPaint(2,Color.BLUE);
        p.setForegroundAlpha(0.5f);
        p.setLabelFont(Font.decode(""));
        p.setLabelGenerator(new StandardPieItemLabelGenerator(
                "{0} {2}", NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance()
        ));
        ChartFrame frame = new ChartFrame("Діаграма тональності", freeChart);
        frame.setBounds(50, 270, 600, 300);
        frame.setVisible(true);
        frame.setSize(500, 380);
    }

    private int[] values(String str) {
        str = str.replaceAll("[^-?0-9]+", " ");
        String[] strArray = str.trim().split(" ");
        int[] integers = new int[strArray.length];
        for (int i = 0; i < integers.length; i++) {
            integers[i] = Integer.parseInt(strArray[i]);
        }
        return integers;
    }
}
