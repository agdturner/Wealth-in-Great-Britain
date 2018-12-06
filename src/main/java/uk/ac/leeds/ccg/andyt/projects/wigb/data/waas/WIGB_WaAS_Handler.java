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
package uk.ac.leeds.ccg.andyt.projects.wigb.data.waas;

import java.io.File;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_StaticIO;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Strings;
import uk.ac.leeds.ccg.andyt.projects.wigb.io.WIGB_Files;

/**
 *
 * @author geoagdt
 */
public abstract class WIGB_WaAS_Handler {

    protected final WIGB_Files Files;
    protected final WIGB_Strings Strings;
    protected String TYPE;
    protected File INDIR;

    public WIGB_WaAS_Handler(WIGB_Files Files, WIGB_Strings Strings) {
        this.Files = Files;
        this.Strings = Strings;
    }

    public File getInputFile(byte wave) {
        File f;
        String filename;
        filename = "was_wave_" + wave + "_" + TYPE + "_eul_final";
        if (wave < 4) {
            filename += "_v2";
        }
        filename += ".tab";
        f = new File(INDIR, filename);
        return f;
    }

    protected Object cache(byte wave, File cf) {
        Object r;
        System.out.println("<load wave " + wave + " " + TYPE + " WaAS "
                + "from file " + cf + ">");
        r = Generic_StaticIO.readObject(cf);
        System.out.println("</load wave " + wave + " " + TYPE + " WaAS "
                + "from file " + cf + ">");
        return r;
    }

    protected void cache(byte wave, File cf, Object o) {
        System.out.println("<store wave " + wave + " " + TYPE + " WaAS "
                + "in file " + cf + ">");
        Generic_StaticIO.writeObject(o, cf);
        System.out.println("</store wave " + wave + " " + TYPE + " WaAS "
                + "in file " + cf + ">");
    }
    
    public void cacheSubset(byte wave, Object o) {
        File dir;
        dir = Files.getGeneratedWaASDirectory();
        dir = new File(dir, "Subsets");
        File cf;
        cf = new File(dir, TYPE + wave + "." + Strings.S_dat);
        cache(wave, cf, o);
    }
    
    public void cacheSubsetCollection(short collectionID, byte wave, Object o) {
        File dir;
        dir = Files.getGeneratedWaASDirectory();
        dir = new File(dir, "Subsets");
        File cf;
        cf = new File(dir, TYPE + wave + "_" + collectionID + "." + Strings.S_dat);
        WIGB_WaAS_Handler.this.cache(wave, cf, o);
    }
    
    public Object loadSubsetCollection(short collectionID, byte wave) {
        Object r;
        File dir;
        dir = Files.getGeneratedWaASDirectory();
        dir = new File(dir, "Subsets");
        File cf;
        cf = new File(dir, TYPE + wave + "_" + collectionID + "." + Strings.S_dat);
        r = cache(wave, cf);
        return r;
    }
}
