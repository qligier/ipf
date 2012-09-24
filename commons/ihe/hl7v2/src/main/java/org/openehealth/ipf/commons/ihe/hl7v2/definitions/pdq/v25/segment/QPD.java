/*
 * Copyright 2009 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openehealth.ipf.commons.ihe.hl7v2.definitions.pdq.v25.segment;

import org.openehealth.ipf.modules.hl7.HL7v2Exception;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.AbstractSegment;
import ca.uhn.hl7v2.model.Group;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.Type;
import ca.uhn.hl7v2.model.Varies;
import ca.uhn.hl7v2.model.v25.datatype.CE;
import ca.uhn.hl7v2.model.v25.datatype.CX;
import ca.uhn.hl7v2.model.v25.datatype.QIP;
import ca.uhn.hl7v2.model.v25.datatype.ST;
import ca.uhn.hl7v2.parser.ModelClassFactory;


/**
 * <p>Represents an HL7 QPD message segment.
 * The fields contained in this segment:</p><p>
 * QPD-1: Message Query Name (CE)<br>
 * QPD-2: Query Tag (ST)<br>
 * QPD-3: Query Input Parameter List (QIP)<br>
 * QPD-4: Parameters for Fuzzy Search (QIP)<br>
 * QPD-5,6,7: User Parameters (in successive fields) (varies)<br>
 * QPD-8: Extended Composite ID with Check Digit (CX)<br>
 */
@SuppressWarnings("serial")
public class QPD extends AbstractSegment {

    /**
     * Creates a QPD (Query Parameter Definition) segment object
     * which belongs to the given message
     */
    public QPD(Group parentGroup, ModelClassFactory modelFactory) {
        super(parentGroup, modelFactory);
        Message msg = getMessage();
        try {
            add(CE.class, true, 1, 250, new Object[]{msg}, "Message Query Name");
            add(ST.class, true, 1, 32, new Object[]{msg}, "Query Tag");
            add(QIP.class, true, 0, 256, new Object[]{msg}, "Demographics Fields");
            add(QIP.class, false, 0, 256, new Object[]{msg}, "Fuzzy Search Parameters");
            add(Varies.class, false, 0, 256, new Object[]{msg}, null);
            add(Varies.class, false, 0, 256, new Object[]{msg}, null);
            add(Varies.class, false, 0, 256, new Object[]{msg}, null);
            add(CX.class, false, 0, 256, new Object[]{msg}, "What domains returned");
        } catch (HL7Exception e) {
            throw new HL7v2Exception(e);
        }
    }

    /**
     * Returns Message Query Name (QPD-1).
     */
    public CE getMessageQueryName() {
        return getTypedField(1, 0);
    }

    /**
     * Returns Message Query Name (QPD-1).
     */
    public CE getQpd1_MessageQueryName() {
        return getTypedField(1, 0);
    }

    /**
     * Returns Query Tag (QPD-2).
     */
    public ST getQueryTag() {
        return getTypedField(2, 0);
    }

    /**
     * Returns Query Tag (QPD-2).
     */
    public ST getQpd2_QueryTag() {
        return getTypedField(2, 0);
    }

    /**
     * Returns Demographics Fields (QPD-3).
     */
    public QIP getDemographicsFields(int rep) {
        return getTypedField(3, rep);
    }

    /**
     * Returns Demographics Fields (QPD-3).
     */
    public QIP getQpd3_DemographicsFields(int rep) {
        return getTypedField(3, rep);
    }

    /**
     * Returns Demographics Fields (QPD-3).
     */
    public QIP[] getDemographicsFields() {
        return getTypedField(3, new QIP[0]);
    }

    /**
     * Returns Fuzzy Search parameters (QPD-4).
     */
    public QIP getFuzzySearchParameters() {
        return getTypedField(4, 0);
    }

    /**
     * Returns Fuzzy Search parameters (QPD-4).
     */
    public QIP getQpd4_FuzzySearchParameters() {
        return getTypedField(4, 0);
    }

    /**
     * Returns What Domains to be returned (QPD-8).
     */
    public CX getWhatDomainsReturned(int rep) {
        return getTypedField(8, rep);
    }

    /**
     * Returns What Domains to be returned (QPD-8).
     */
    public CX getQpd8_WhatDomainsReturned(int rep) {
        return getTypedField(8, rep);
    }

    /**
     * Returns What Domains to be returned (QPD-8).
     *
     * @return movement IDs
     */
    public CX[] getWhatDomainsReturned() {
        return getTypedField(8, new CX[0]);
    }

    /** {@inheritDoc} */
    protected Type createNewTypeWithoutReflection(int field) {
        switch (field) {
            case 0: return new CE(getMessage());
            case 1: return new ST(getMessage());
            case 2: return new QIP(getMessage());
            case 3: return new QIP(getMessage());
            case 4: return new Varies(getMessage());
            case 5: return new Varies(getMessage());
            case 6: return new Varies(getMessage());
            case 7: return new CX(getMessage());
            default: return null;
        }
    }

}