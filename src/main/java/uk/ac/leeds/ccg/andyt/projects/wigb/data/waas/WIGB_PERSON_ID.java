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
import java.util.Objects;

/**
 *
 * @author geoagdt
 */
public class WIGB_PERSON_ID implements Serializable {

    private Integer CASEID;
    private Integer PERSONID;

    public WIGB_PERSON_ID() {
    }

    public WIGB_PERSON_ID(Integer CASEID, Integer PERSONID) {
        this.CASEID = CASEID;
        this.PERSONID = PERSONID;
    }

    /**
     *
     * @return the CASEID
     */
    public Integer getCASEID() {
        return CASEID;
    }

    /**
     *
     * @return the PERSONID
     */
    public Integer getPERSONID() {
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
            if (this.CASEID.equals(o.CASEID)) {
                if (PERSONID.equals(o.PERSONID)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.CASEID);
        hash = 97 * hash + Objects.hashCode(this.PERSONID);
        return hash;
    }
}
