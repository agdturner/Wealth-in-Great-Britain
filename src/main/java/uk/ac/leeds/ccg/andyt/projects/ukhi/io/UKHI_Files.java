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
package uk.ac.leeds.ccg.andyt.projects.ukhi.io;

import java.io.File;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_Files;

/**
 *
 * @author geoagdt
 */
public class UKHI_Files extends Generic_Files {

    public UKHI_Files(){
        super();
    }

    /**
     * @param dataDir
     */
    public UKHI_Files(File dataDir) {
        super(dataDir);
    }

    public File getWaASInputDir() {
        File r = new File(getInputDir(), "WaAS");
        r = new File(r, "UKDA-7215-tab");
        r = new File(r, "tab");
        return r;
    }

    public File getTIDataFile() {
        File d = new File(getInputDir(), "TransparencyInternational");
        return new File(d, "Selection.csv"); 
    }

    public File getGeneratedWaASDir() {
        File d  = getGeneratedDir();
        File f  = new File(d, "WaAS");
        f.mkdirs();
        return f;
    }
    
    public File getGeneratedWaASSubsetsDir() {
        File d  = getGeneratedWaASDir();
        File f  = new File(d, "Subsets");
        f.mkdirs();
        return f;
    }

    public File getEnvDataFile() {
        return new File(getGeneratedDir(), "Env.dat");
    }
}
