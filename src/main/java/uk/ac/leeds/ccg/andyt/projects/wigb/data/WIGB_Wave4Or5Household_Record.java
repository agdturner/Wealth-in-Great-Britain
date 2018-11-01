/*
 * Copyright 2018 geoagdt.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.ac.leeds.ccg.andyt.projects.wigb.data;

/**
 *
 * @author geoagdt
 */
public class WIGB_Wave4Or5Household_Record {
    
    private Double YearW1;
    private Double MonthW1;
    private Double YearW2;
    private Double MonthW2;
    private Double YearW3;
    private Double MonthW3;
    private Double Yearw4;
    private Double MonthW4;
protected int n;
protected String[] split;
        
    public WIGB_Wave4Or5Household_Record(String line){
        init(line);
    }
    
    private void init(String line) {
        split = line.split(",");
        n = 0;
        if (split[n].trim().isEmpty()) {
            YearW1 = null;
        } else {
            YearW1 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MonthW1 = null;
        } else {
            MonthW1 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            YearW2 = null;
        } else {
            YearW2 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MonthW2 = null;
        } else {
            MonthW2 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            YearW3 = null;
        } else {
            YearW3 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MonthW3 = null;
        } else {
            MonthW3 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            Yearw4 = null;
        } else {
            Yearw4 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MonthW4 = null;
        } else {
            MonthW4 = new Double(split[n]);
        }
        n++;
    }
    
    /**
     * @return the YearW1
     */
    public Double getYearW1() {
        return YearW1;
    }

    /**
     * @return the MonthW1
     */
    public Double getMonthW1() {
        return MonthW1;
    }

    /**
     * @return the YearW2
     */
    public Double getYearW2() {
        return YearW2;
    }

    /**
     * @return the MonthW2
     */
    public Double getMonthW2() {
        return MonthW2;
    }

    /**
     * @return the YearW3
     */
    public Double getYearW3() {
        return YearW3;
    }

    /**
     * @return the MonthW3
     */
    public Double getMonthW3() {
        return MonthW3;
    }

    /**
     * @return the Yearw4
     */
    public Double getYearw4() {
        return Yearw4;
    }

    /**
     * @return the MonthW4
     */
    public Double getMonthW4() {
        return MonthW4;
    }

    /**
     * @param YearW1 the YearW1 to set
     */
    public void setYearW1(Double YearW1) {
        this.YearW1 = YearW1;
    }

    /**
     * @param MonthW1 the MonthW1 to set
     */
    public void setMonthW1(Double MonthW1) {
        this.MonthW1 = MonthW1;
    }

    /**
     * @param YearW2 the YearW2 to set
     */
    protected void setYearW2(Double YearW2) {
        this.YearW2 = YearW2;
    }

    /**
     * @param MonthW2 the MonthW2 to set
     */
    protected void setMonthW2(Double MonthW2) {
        this.MonthW2 = MonthW2;
    }

    /**
     * @param YearW3 the YearW3 to set
     */
    protected void setYearW3(Double YearW3) {
        this.YearW3 = YearW3;
    }

    /**
     * @param MonthW3 the MonthW3 to set
     */
    protected void setMonthW3(Double MonthW3) {
        this.MonthW3 = MonthW3;
    }

    /**
     * @param Yearw4 the Yearw4 to set
     */
    protected void setYearw4(Double Yearw4) {
        this.Yearw4 = Yearw4;
    }

    /**
     * @param MonthW4 the MonthW4 to set
     */
    protected void setMonthW4(Double MonthW4) {
        this.MonthW4 = MonthW4;
    }

    
}
