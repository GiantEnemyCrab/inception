/*
 * Licensed to the Technische Universität Darmstadt under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The Technische Universität Darmstadt 
 * licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.
 *  
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.tudarmstadt.ukp.inception.recommendation.render;

import de.tudarmstadt.ukp.inception.rendering.request.RenderRequest;
import de.tudarmstadt.ukp.inception.rendering.vmodel.VDocument;
import de.tudarmstadt.ukp.inception.schema.adapter.TypeAdapter;

/**
 * Type Adapters for span, arc, and chain annotations
 *
 */
public interface RecommendationTypeRenderer<T extends TypeAdapter>
{
    String COLOR = "#cccccc";

    /**
     * Add annotations from the CAS, which is controlled by the window size, to the intermediate
     * rendering representation {@link VDocument}.
     *
     * @param aVdoc
     *            a {@link VDocument} containing annotations for the given layer
     * @param aRequest
     *            a render request
     */
    void render(VDocument aVdoc, RenderRequest aRequest, T aAdapter);
}
