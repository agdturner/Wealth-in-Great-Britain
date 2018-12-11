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
import uk.ac.leeds.ccg.andyt.generic.io.Generic_IO;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Strings;
import uk.ac.leeds.ccg.andyt.projects.wigb.io.WIGB_Files;
import uk.ac.leeds.ccg.andyt.projects.wigb.process.WIGB_Main_Process;

/**
 *
 * @author geoagdt
 */
public abstract class WIGB_WaAS_Handler {

    public transient WIGB_Files Files;
    public transient WIGB_Strings Strings;
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

    protected Object cache(byte wave, File f) {
        Object r;
        String m;
        m = "load " + getString0(wave, f);
        WIGB_Main_Process.log1("<" + m + ">");
        r = Generic_IO.readObject(f);
        WIGB_Main_Process.log1("</" + m + ">");
        return r;
    }

    protected String getString0(byte wave, File f) {
        return "wave " + wave + " " + TYPE + " WaAS file " + f;
    }

    protected void cache(byte wave, File f, Object o) {
        String m;
        m = "store " + getString0(wave, f);
        WIGB_Main_Process.log1("<" + m + ">");
        Generic_IO.writeObject(o, f);
        WIGB_Main_Process.log1("</" + m + ">");
    }

    public void cacheSubset(byte wave, Object o) {
        File f;
        f = new File(Files.getGeneratedWaASSubsetsDir(),
                TYPE + wave + "." + Strings.S_dat);
        cache(wave, f, o);
    }

    public void cacheSubsetCollection(short cID, byte wave, Object o) {
        File f;
        f = new File(Files.getGeneratedWaASSubsetsDir(),
                getString1(wave, cID) + "." + Strings.S_dat);
        WIGB_WaAS_Handler.this.cache(wave, f, o);
    }

    protected String getString1(byte wave, short cID) {
        return TYPE + wave + "_" + cID;
    }

    public Object loadSubsetCollection(short cID, byte wave) {
        Object r;
        File f;
        f = new File(Files.getGeneratedWaASSubsetsDir(),
                getString1(wave, cID) + "." + Strings.S_dat);
        r = cache(wave, f);
        return r;
    }
}
