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

import java.util.ArrayList;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.hhold.WIGB_WaAS_Wave1_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_WaAS_Wave1_PERSON_Record;

/**
 *
 * @author geoagdt
 */
public class WIGB_WaAS_Wave1_Record extends WIGB_WaAS_Record {
    
    private final WIGB_WaAS_Wave1_HHOLD_Record hhold;
    
    private final ArrayList<WIGB_WaAS_Wave1_PERSON_Record> people;
    
    public WIGB_WaAS_Wave1_Record(WIGB_WaAS_Wave1_HHOLD_Record hhold,
            ArrayList<WIGB_WaAS_Wave1_PERSON_Record> people){
        super(hhold.getCASEW1());
        this.hhold = hhold;
        this.people = people;
    }

    /**
     * @return the hhold
     */
    public WIGB_WaAS_Wave1_HHOLD_Record getHhold() {
        return hhold;
    }

    /**
     * @return the people
     */
    public ArrayList<WIGB_WaAS_Wave1_PERSON_Record> getPeople() {
        return people;
    }
}
