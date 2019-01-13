/**
 * Copyright 2018 Andy Turner, The University of Leeds, UK.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package uk.ac.leeds.ccg.andyt.projects.wigb.chart;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import uk.ac.leeds.ccg.andyt.chart.Generic_LineGraph;
import uk.ac.leeds.ccg.andyt.generic.core.Generic_Strings;
import uk.ac.leeds.ccg.andyt.generic.execution.Generic_Execution;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_Files;
import uk.ac.leeds.ccg.andyt.generic.util.Generic_Collections;
import uk.ac.leeds.ccg.andyt.generic.visualisation.Generic_Visualisation;

/**
 * An implementation of <code>Generic_AbstractLineGraph</code> to generate a
 * Line Chart Visualization of some default data and write it out to file as a
 * PNG.
 */
public class WIGB_LineGraph extends Generic_LineGraph {

    public WIGB_LineGraph() {
    }

    /**
     *
     * @param es
     * @param file
     * @param format
     * @param title
     * @param dataWidth
     * @param dataHeight
     * @param xAxisLabel
     * @param yAxisLabel
     * @param yMax
     * @param yPin
     * @param yIncrement
     * @param numberOfYAxisTicks
     * @param decimalPlacePrecisionForCalculations
     * @param decimalPlacePrecisionForDisplay
     * @param r
     */
    public WIGB_LineGraph(
            ExecutorService es, File file, String format, String title,
            int dataWidth, int dataHeight,
            String xAxisLabel, String yAxisLabel,
            BigDecimal yMax,
            BigDecimal yPin,
            BigDecimal yIncrement,
            int numberOfYAxisTicks,
            int decimalPlacePrecisionForCalculations,
            int decimalPlacePrecisionForDisplay,
            RoundingMode r) {
        setyMax(yMax);
        setyPin(yPin);
        setyIncrement(yIncrement);
        setNumberOfYAxisTicks(numberOfYAxisTicks);
        init(es, file, format, title, dataWidth, dataHeight, xAxisLabel,
                yAxisLabel, false, decimalPlacePrecisionForCalculations,
                decimalPlacePrecisionForDisplay, r);
    }

    public static void main(String[] args) {
        Generic_Visualisation.getHeadlessEnvironment();
        /*
         * Initialise title and File to write image to
         */
        String title;
        File file;
        String format = "PNG";
        if (args.length != 2) {
            System.out.println(
                    "Expected 2 args:"
                    + " args[0] title;"
                    + " args[1] File."
                    + " Recieved " + args.length + " args.");
            // Use defaults
            title = "Example Line Graph";
            System.out.println("Use default title: " + title);
            Generic_Strings s = new Generic_Strings();
            Generic_Files files = new Generic_Files(s);
            File outdir;
            outdir = files.getOutputDataDir();
            file = new File(outdir,
                    title.replace(" ", "_") + "." + format);
            System.out.println("Use default File: " + file.toString());
        } else {
            title = args[0];
            file = new File(args[1]);
        }
        int dataWidth = 500;
        int dataHeight = 250;
        String xAxisLabel = "X";
        String yAxisLabel = "Y";
        boolean drawOriginLinesOnPlot = true;
        int barGap = 1;
        int xIncrement = 1;
        int numberOfYAxisTicks = 11;
        BigDecimal yMax;
        yMax = null;
        BigDecimal yPin = BigDecimal.ZERO;
        BigDecimal yIncrement = BigDecimal.ONE;
        //int yAxisStartOfEndInterval = 60;
        int decimalPlacePrecisionForCalculations = 10;
        int decimalPlacePrecisionForDisplay = 3;
        RoundingMode roundingMode = RoundingMode.HALF_UP;
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        WIGB_LineGraph chart = new WIGB_LineGraph(
                executorService,
                file,
                format,
                title,
                dataWidth,
                dataHeight,
                xAxisLabel,
                yAxisLabel,
                yMax,
                yPin,
                yIncrement,
                numberOfYAxisTicks,
                decimalPlacePrecisionForCalculations,
                decimalPlacePrecisionForDisplay,
                roundingMode);
        chart.setData(chart.getDefaultData());
        chart.run();
        Future future = chart.future;
        Generic_Execution.shutdownExecutorService(
                executorService, future, chart);
    }

    public void setData(ArrayList<Byte> gors, 
            TreeMap<Byte, String> GORNameLookup,
            TreeMap<Byte, Double> changeHPROPWSubset,
            TreeMap<Byte, Double> changeHPROPWAll) {
        Object[] data;
        data = new Object[7];

        TreeMap<String, TreeMap<BigDecimal, BigDecimal>> maps;
        maps = new TreeMap<>();
        
        TreeMap<BigDecimal, BigDecimal> map;
        map = new TreeMap<>();
        int x;
        x = 1;
        Iterator<Byte> ite;
        ite = gors.iterator();
        while (ite.hasNext()) {
            byte gor = ite.next();
            map.put(new BigDecimal(x), new BigDecimal(changeHPROPWSubset.get(gor)));
            maps.put("Change in HPROPW Subset", map);
            x ++;
        }
        TreeMap<BigDecimal, BigDecimal> map2;
        map2 = new TreeMap<>();
        x = 1;
        ite = gors.iterator();
        while (ite.hasNext()) {
            byte gor = ite.next();
            map2.put(new BigDecimal(x), new BigDecimal(changeHPROPWAll.get(gor)));
            maps.put("Change in HPROPW All", map2);
            x ++;
        }
        BigDecimal[] minMaxBigDecimal;
        minMaxBigDecimal = Generic_Collections.getMinMaxBigDecimal(map);
        BigDecimal minY = minMaxBigDecimal[0];
        BigDecimal maxY = minMaxBigDecimal[1];
        BigDecimal minX = map.firstKey();
        BigDecimal maxX = map.lastKey();
        minMaxBigDecimal = Generic_Collections.getMinMaxBigDecimal(map2);
        if (minY.compareTo(minMaxBigDecimal[0]) == 1) {
            minY = minMaxBigDecimal[0];
        }
        if (maxY.compareTo(minMaxBigDecimal[1]) == -1) {
            maxY = minMaxBigDecimal[1];
        }
        if (minX.compareTo(map2.firstKey()) == 1) {
            minX = map2.firstKey();
        }
        if (maxX.compareTo(map2.lastKey()) == -1) {
            maxX = map2.lastKey();
        }
        data[0] = maps;
        data[1] = minY;
        data[2] = maxY;
        data[3] = minX;
        data[4] = maxX;
        ArrayList<String> labels;
        labels = new ArrayList<>();
        labels.addAll(maps.keySet());
        data[5] = labels;

        // Comment out the following section to have a normal axis instead of labels.
        TreeMap<BigDecimal, String> xAxisLabels;
        xAxisLabels = new TreeMap<>();
        x = 1;
        ite = gors.iterator();
        while (ite.hasNext()) {
            byte gor = ite.next();
            xAxisLabels.put(new BigDecimal(x), GORNameLookup.get(gor));
            x ++;
        }
        data[6] = xAxisLabels;
        setData(data);
    }
}
