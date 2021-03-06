/*
 * Copyright 2017 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.openehealth.ipf.commons.ihe.hl7v2.audit.iti21;

import org.openehealth.ipf.commons.ihe.hl7v2.audit.codes.MllpEventTypeCode;
import org.openehealth.ipf.commons.ihe.hl7v2.audit.codes.MllpParticipantObjectIdTypeCode;
import org.openehealth.ipf.commons.ihe.hl7v2.audit.pdqcore.PdqAuditStrategy;

/**
 * Client audit strategy for ITI-21 (PDQ).
 * 
 * @author Dmytro Rud
 */
public class Iti21AuditStrategy extends PdqAuditStrategy {

    public Iti21AuditStrategy(boolean serverSide) {
        super(serverSide,
                MllpEventTypeCode.PatientDemographicsQuery,
                MllpParticipantObjectIdTypeCode.PatientDemographicsQuery);
    }

}
