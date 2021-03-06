/*
 * Copyright 2018 the original author or authors.
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

package org.openehealth.ipf.modules.hl7.kotlin

import ca.uhn.hl7v2.Location
import ca.uhn.hl7v2.model.Group
import ca.uhn.hl7v2.model.MessageVisitorSupport
import ca.uhn.hl7v2.model.Segment
import ca.uhn.hl7v2.model.Structure

internal class EachStructureLocationVisitor(private val consume: (Structure, String) -> Unit) : MessageVisitorSupport() {

    override fun end(group: Group, location: Location): Boolean {
        consume(group, terserToDsl(location))
        return super.end(group, location)
    }

    override fun end(segment: Segment, location: Location): Boolean {
        consume(segment, terserToDsl(location))
        return super.end(segment, location)
    }
}