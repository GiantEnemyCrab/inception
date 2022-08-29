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
package de.tudarmstadt.ukp.clarin.webanno.ui.annotation.actionbar.undo.actions;

import java.io.Serializable;
import java.util.Optional;

import org.apache.uima.cas.CAS;

import de.tudarmstadt.ukp.clarin.webanno.model.AnnotationFeature;
import de.tudarmstadt.ukp.clarin.webanno.ui.annotation.actionbar.undo.PostAction;
import de.tudarmstadt.ukp.clarin.webanno.ui.annotation.actionbar.undo.PostActionScrollToAndSelect;
import de.tudarmstadt.ukp.inception.annotation.events.FeatureValueUpdatedEvent;
import de.tudarmstadt.ukp.inception.rendering.vmodel.VID;
import de.tudarmstadt.ukp.inception.schema.AnnotationSchemaService;
import de.tudarmstadt.ukp.inception.schema.adapter.AnnotationException;

public class UpdateFeatureValueAnnotationAction
    extends AnnotationAction_ImplBase
    implements RedoableAnnotationAction, UndoableAnnotationAction
{
    private final AnnotationFeature feature;
    private final Serializable oldValue;
    private final Serializable newValue;

    public UpdateFeatureValueAnnotationAction(AnnotationSchemaService aSchemaService,
            FeatureValueUpdatedEvent aEvent)
    {
        super(aEvent, new VID(aEvent.getFS()));

        feature = aEvent.getFeature();

        Object eventOldValue = aEvent.getOldValue();
        if (eventOldValue == null || eventOldValue instanceof Serializable) {
            oldValue = (Serializable) eventOldValue;
        }
        else {
            oldValue = null;
        }

        Object eventNewValue = aEvent.getNewValue();
        if (eventNewValue == null || eventNewValue instanceof Serializable) {
            newValue = (Serializable) eventNewValue;
        }
        else {
            newValue = null;
        }
    }

    private static final long serialVersionUID = -1475379306317223468L;

    @Override
    public Optional<PostAction> undo(AnnotationSchemaService aSchemaService, CAS aCas)
        throws AnnotationException
    {
        var adapter = aSchemaService.getAdapter(getLayer());
        adapter.setFeatureValue(getDocument(), getUser(), aCas, getVid().getId(), feature,
                oldValue);
        return Optional
                .of(new PostActionScrollToAndSelect(getVid(), "[" + feature.getLayer().getUiName()
                        + " feature value of [" + feature.getUiName() + "] restored"));
    }

    @Override
    public Optional<PostAction> redo(AnnotationSchemaService aSchemaService, CAS aCas)
        throws AnnotationException
    {
        var adapter = aSchemaService.getAdapter(getLayer());
        adapter.setFeatureValue(getDocument(), getUser(), aCas, getVid().getId(), feature,
                newValue);
        return Optional
                .of(new PostActionScrollToAndSelect(getVid(), "[" + feature.getLayer().getUiName()
                        + "] feature value of [" + feature.getUiName() + "] set"));
    }

}