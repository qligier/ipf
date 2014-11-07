/*
 * Copyright 2014 the original author or authors.
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
package org.openehealth.ipf.platform.camel.ihe.mllp.dispatch

import org.apache.camel.spring.SpringRouteBuilder
import org.openehealth.ipf.modules.hl7.message.MessageUtils
import org.openehealth.ipf.platform.camel.ihe.mllp.PixPdqCamelValidators

import static org.openehealth.ipf.platform.camel.ihe.mllp.PixPdqCamelValidators.itiValidator

import static org.openehealth.ipf.platform.camel.core.util.Exchanges.resultMessage

/**
 * @author Dmytro Rud
 */
class DispatchRouteBuilder extends SpringRouteBuilder {

    @Override
    void configure() throws Exception {
        from('mllp-dispatch://0.0.0.0:18500?routes=pixfeed,xadpid').process {}

        from('pix-iti8://0.0.0.0:18501')
                .routeId('pixfeed')
                .process(itiValidator())
                .ack()
                .process(itiValidator())

        from('xpid-iti64://0.0.0.0:18502')
                .routeId('xadpid')
                .process(itiValidator())
                .ack()
                .process(itiValidator())

    }

}