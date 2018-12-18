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

    public WIGB_WaAS_Wave1_Record w1Record;

    /**
     * Keys are CASEW2
     */
    public HashMap<Short, WIGB_WaAS_Wave2_Record> w2Records;

    /**
     * Keys are CASEW2, values keys are CASEW3.
     */
    public HashMap<Short, HashMap<Short, WIGB_WaAS_Wave3_Record>> w3Records;

    /**
     * Keys are CASEW2, values keys are CASEW3, next value keys are CASEW4.
     */
    public HashMap<Short, HashMap<Short, HashMap<Short, WIGB_WaAS_Wave4_Record>>> w4Records;

    /**
     * Keys are CASEW2, values keys are CASEW3, next value keys are CASEW4, next
     * value keys are CASEW5.
     */
    public HashMap<Short, HashMap<Short, HashMap<Short, HashMap<Short, WIGB_WaAS_Wave5_Record>>>> w5Records;

    public WIGB_WaAS_Combined_Record(short CASEW1) {
        super(CASEW1);
        w1Record = new WIGB_WaAS_Wave1_Record(CASEW1);
        w2Records = new HashMap<>();
        w3Records = new HashMap<>();
        w4Records = new HashMap<>();
        w5Records = new HashMap<>();
    }
}
