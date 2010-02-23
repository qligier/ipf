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
package org.openehealth.ipf.platform.camel.core.model;

import groovy.lang.Closure;

import org.apache.camel.Expression;
import org.apache.camel.model.ProcessorDefinition;
import org.openehealth.ipf.platform.camel.core.closures.DelegatingExpression;

/**
 * Models IPF DSL extensions that conflict with Camel DSL elements. Usage
 * example:
 * 
 * <pre>
 * from('direct:input').ipf().split()...
 * </pre>
 * 
 * This selects the IPF splitter rather than the Camel splitter.
 * 
 * @author Martin Krasser
 * @dsl platform-camel-core
 */
public class IpfDefinition {

	private final ProcessorDefinition<ProcessorDefinition> processorDefinition;
	
	public IpfDefinition(ProcessorDefinition<ProcessorDefinition> processorDefinition) {
		this.processorDefinition = processorDefinition;
	}
	
	public ProcessorDefinition<ProcessorDefinition> getProcessorDefinition() {
		return processorDefinition;
	}

    /**
     * Splits an exchange by evaluating the expression
     * @param splitExpression
     *          the expression that returns the collection of sub exchanges
     * @ipfdoc Core features#split-expression
     * @dsl platform-camel-core
     */
	public SplitterDefinition split(Expression splitExpression) {
        SplitterDefinition answer = new SplitterDefinition(splitExpression);        
        processorDefinition.addOutput(answer);
        return answer;
    }

    /**
     * Splits an exchange by evaluating the expression defined by a bean reference
     * @param splitExpressionBeanName
     *          the name of an expression bean that returns the collection of sub exchanges
     * @ipfdoc Core features#split-bean
     * @dsl platform-camel-core
     */
	public SplitterDefinition split(String splitExpressionBeanName) {
        SplitterDefinition answer = new SplitterDefinition(splitExpressionBeanName);        
        processorDefinition.addOutput(answer);
        return answer;
    }
	
    /**
     * Splits an exchange by evaluating the split logic
     * @param splitLogic
     *          a closure implementing the split logic that returns the collection of sub exchanges
     * @ipfdoc Core features#split-closure
     * @dsl platform-camel-core
     */
	public SplitterDefinition split(Closure splitLogic) {
    	return split(new DelegatingExpression(splitLogic));
    }
	
}
