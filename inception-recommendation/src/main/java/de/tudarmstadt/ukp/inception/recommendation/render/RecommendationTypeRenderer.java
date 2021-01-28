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

import org.apache.uima.cas.CAS;

import de.tudarmstadt.ukp.clarin.webanno.api.AnnotationSchemaService;
import de.tudarmstadt.ukp.clarin.webanno.api.DocumentService;
import de.tudarmstadt.ukp.clarin.webanno.api.annotation.feature.FeatureSupportRegistry;
import de.tudarmstadt.ukp.clarin.webanno.api.annotation.model.AnnotatorState;
import de.tudarmstadt.ukp.clarin.webanno.api.annotation.rendering.model.VDocument;
import de.tudarmstadt.ukp.clarin.webanno.model.AnnotationLayer;
import de.tudarmstadt.ukp.inception.recommendation.api.LearningRecordService;
import de.tudarmstadt.ukp.inception.recommendation.api.RecommendationService;

/**
 * Type Adapters for span, arc, and chain annotations
 *
 */
public interface RecommendationTypeRenderer
{
    /**
     * Add annotations from the CAS, which is controlled by the window size, to the intermediate
     * rendering representation {@link VDocument}.
     *
     * @param aCas
     *            The CAS object containing annotations
     * @param aVdoc
     *            A VDocument containing annotations for the given layer
     * @param aBratAnnotatorModel
     *            Data model for brat annotations
     */
    void render(CAS aCas, VDocument aVdoc, AnnotatorState aBratAnnotatorModel,
            AnnotationLayer aLayer, RecommendationService aRecService,
            LearningRecordService aLearningRecordService,
            AnnotationSchemaService aAnnotationService, FeatureSupportRegistry aFsRegistry,
            DocumentService aDocumentService, int aWindowBeginOffset, int aWindowEndOffset);
}
