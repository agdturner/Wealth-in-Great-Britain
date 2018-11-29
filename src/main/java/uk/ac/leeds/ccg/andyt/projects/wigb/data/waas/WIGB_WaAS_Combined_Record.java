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

import java.util.HashMap;

/**
 *
 * @author geoagdt
 */
public class WIGB_WaAS_Combined_Record extends WIGB_WaAS_Record {
    
    public HashMap<Byte, HashMap<Short, WIGB_WaAS_Record>> data;
    
    public WIGB_WaAS_Combined_Record(short CASEW1, byte nwaves) {
        super(CASEW1);
        data = new HashMap<>();
        for (byte b = 0; b < nwaves; b ++) {
            data.put(b, new HashMap<>());
        }
    }
    
    public void add(WIGB_WaAS_Wave1_Record r) {
        data.get((byte)1).put(r.getID(), r);
    }
    
    public void add(WIGB_WaAS_Wave2_Record r) {
        data.get((byte)2).put(r.getID(), r);
    }
    
    public void add(WIGB_WaAS_Wave3_Record r) {
       data.get((byte)3).put(r.getID(), r);
    }
    
    public void add(WIGB_WaAS_Wave4_Record r) {
        data.get((byte)4).put(r.getID(), r);
    }
    
    public void add(WIGB_WaAS_Wave5_Record r) {
        data.get((byte)5).put(r.getID(), r);
    }
    
}
