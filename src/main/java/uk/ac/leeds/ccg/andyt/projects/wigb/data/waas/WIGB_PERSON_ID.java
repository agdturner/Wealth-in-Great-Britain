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

import java.io.Serializable;

/**
 *
 * @author geoagdt
 */
public class WIGB_PERSON_ID implements Serializable {

    //private int wave;
    private int CASEID;
    private int PERSONID;

    public WIGB_PERSON_ID() {
    }

    public WIGB_PERSON_ID(int wave, Integer CASEID, Integer PERSONID) {
        //this.wave = wave;
        this.CASEID = CASEID;
        this.PERSONID = PERSONID;
    }

//    /**
//     *
//     * @return the CASEID
//     */
//    public int getWave() {
//        return wave;
//    }

    /**
     *
     * @return the CASEID
     */
    public int getCASEID() {
        return CASEID;
    }

    /**
     *
     * @return the PERSONID
     */
    public int getPERSONID() {
        return PERSONID;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof WIGB_PERSON_ID) {
            WIGB_PERSON_ID o;
            o = (WIGB_PERSON_ID) obj;
            //if (hashCode() == o.hashCode()) {
            if (CASEID == o.CASEID) {
                if (PERSONID == o.PERSONID) {
                    //if (wave == o.wave) {
                        return true;
                    //}
                }
            }
            //}
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.CASEID;
        hash = 23 * hash + this.PERSONID;
        return hash;
    }
}
