/*
 * Copyright 2012 the original author or authors.
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
package org.openehealth.ipf.commons.ihe.xds.core.audit;

import org.openehealth.ipf.commons.audit.AuditContext;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.RetrieveDocumentSetResponseType;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.RetrieveDocumentSetResponseType.DocumentResponse;
import org.openehealth.ipf.commons.ihe.xds.core.audit.XdsNonconstructiveDocumentSetRequestAuditDataset.Status;

/**
 * Basis for Strategy pattern implementation for ATNA Auditing
 * in ebXML 3.0-based retrieval-related XDS transactions.
 *
 * @author Dmytro Rud
 */
public abstract class XdsRetrieveAuditStrategy30 extends XdsNonconstructiveDocumentSetRequestAuditStrategy30 {

    public XdsRetrieveAuditStrategy30(boolean serverSide) {
        super(serverSide);
    }

    /**
     * These transactions defines the source user NOT as being the requestor. This could be a
     * specification mistake.
     *
     * @return audit dataset
     */
    @Override
    public XdsNonconstructiveDocumentSetRequestAuditDataset createAuditDataset() {
        XdsNonconstructiveDocumentSetRequestAuditDataset auditDataset = super.createAuditDataset();
        auditDataset.setSourceUserIsRequestor(false);
        return auditDataset;
    }

    @Override
    public boolean enrichAuditDatasetFromResponse(XdsNonconstructiveDocumentSetRequestAuditDataset auditDataset, Object pojo, AuditContext auditContext) {
        RetrieveDocumentSetResponseType response = (RetrieveDocumentSetResponseType) pojo;
        if (response.getDocumentResponse() != null) {
            for (DocumentResponse documentResponse : response.getDocumentResponse()) {
                auditDataset.registerProcessedDocument(
                        documentResponse.getDocumentUniqueId(),
                        documentResponse.getRepositoryUniqueId(),
                        documentResponse.getHomeCommunityId());
            }
        }

        // These transactions define source and destination userID the inverted way. This could be a
        // specification mistake.
        String sourceUserId = auditDataset.getSourceUserId();
        auditDataset.setSourceUserId(auditDataset.getDestinationUserId());
        auditDataset.setDestinationUserId(sourceUserId);
        return true;
    }

    @Override
    public Status getDefaultDocumentStatus() {
        return Status.NOT_SUCCESSFUL;
    }

}