/*
 * $Id$
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.tiles.definition.pattern;

import java.util.List;
import java.util.Map;

import org.apache.tiles.Definition;

/**
 * A pattern definition resolver that stores {@link DefinitionPatternMatcher}
 * separated by customization key. <br>
 * It delegates creation of definition pattern matchers to a
 * {@link DefinitionPatternMatcherFactory} and recgnizes patterns through the
 * use of a {@link PatternRecognizer}.
 *
 * @param <T> The type of the customization key.
 * @version $Rev$ $Date$
 * @since 2.2.0
 */
public class BasicPatternDefinitionResolver<T> extends
        AbstractPatternDefinitionResolver<T> {

    /**
     * The factory of pattern matchers.
     */
    private DefinitionPatternMatcherFactory definitionPatternMatcherFactory;

    /**
     * The pattern recognizer.
     */
    private PatternRecognizer patternRecognizer;

    /**
     * Constructor.
     *
     * @param definitionPatternMatcherFactory The definition pattern matcher factory.
     * @param patternRecognizer The pattern recognizer.
     */
    public BasicPatternDefinitionResolver(DefinitionPatternMatcherFactory definitionPatternMatcherFactory,
            PatternRecognizer patternRecognizer) {
        this.definitionPatternMatcherFactory = definitionPatternMatcherFactory;
        this.patternRecognizer = patternRecognizer;
    }

    /**
     * Adds definitions, filtering and adding them to the list of definition
     * pattern matchers. Only a subset of definitions will be transformed into
     * definition pattern matchers.
     *
     * @param matchers The list containing the currently stored definition pattern
     * matchers.
     * @param defsMap The definition map to parse.
     * @since 2.2.0
     */
    protected void addDefinitionsAsPatternMatchers(List<DefinitionPatternMatcher> matchers,
            Map<String, Definition> defsMap) {
        for (Map.Entry<String, Definition> de : defsMap.entrySet()) {
            if (patternRecognizer.isPatternRecognized(de.getKey())) {
                matchers.add(definitionPatternMatcherFactory
                        .createDefinitionPatternMatcher(de.getKey(), de
                                .getValue()));
            }
        }
    }
}
