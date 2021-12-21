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
package de.tudarmstadt.ukp.clarin.webanno.api.annotation.feature.editor;

import java.util.List;
import java.util.Locale;

import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;

import com.googlecode.wicket.jquery.core.JQueryBehavior;
import com.googlecode.wicket.jquery.core.Options;
import com.googlecode.wicket.jquery.core.template.IJQueryTemplate;
import com.googlecode.wicket.kendo.ui.form.autocomplete.AutoCompleteTextField;

import de.tudarmstadt.ukp.clarin.webanno.model.ReorderableTag;
import de.tudarmstadt.ukp.clarin.webanno.model.Tag;

public abstract class ReorderableTagAutoCompleteField
    extends AutoCompleteTextField<ReorderableTag>
{
    private static final long serialVersionUID = 311286735004237737L;

    protected ReorderableTagAutoCompleteField(String aId)
    {
        super(aId);
    }

    public ReorderableTagAutoCompleteField(String aId, IModel<ReorderableTag> aModel)
    {
        super(aId, aModel);
    }

    @Override
    public void onConfigure(JQueryBehavior behavior)
    {
        super.onConfigure(behavior);

        behavior.setOption("delay", 500);
        behavior.setOption("animation", false);
        behavior.setOption("footerTemplate",
                Options.asString("#: instance.dataSource.total() # items found"));
        // Prevent scrolling action from closing the dropdown while the focus is on the
        // input field
        behavior.setOption("close",
                String.join(" ", "function(e) {",
                        "  if (document.activeElement == e.sender.element[0]) {",
                        "    e.preventDefault();" + "  }", "}"));
        behavior.setOption("select", " function (e) { this.trigger('change'); }");
    }

    @Override
    protected abstract List<ReorderableTag> getChoices(String aTerm);

    /*
     * Below is a hack which is required because all the text feature editors are expected to write
     * a plain string into the feature state. However, we cannot have an {@code
     * AutoCompleteTextField<String>} field because then we would loose easy access to the tag
     * description which we show in the tooltips. So we hack the converter to return strings on the
     * way out into the model. This is a very evil hack and we need to avoid declaring generic types
     * because we work against them!
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public <C> IConverter<C> getConverter(Class<C> aType)
    {
        IConverter originalConverter = super.getConverter(aType);

        return new IConverter()
        {
            private static final long serialVersionUID = -6505569244789767066L;

            @Override
            public Object convertToObject(String aValue, Locale aLocale) throws ConversionException
            {
                Object value = originalConverter.convertToObject(aValue, aLocale);
                if (value instanceof String) {
                    return value;
                }
                else if (value instanceof Tag) {
                    return ((Tag) value).getName();
                }
                else if (value instanceof ReorderableTag) {
                    return ((ReorderableTag) value).getName();
                }
                else {
                    return null;
                }
            }

            @Override
            public String convertToString(Object aValue, Locale aLocale)
            {
                return originalConverter.convertToString(aValue, aLocale);
            }
        };
    }

    @Override
    protected IJQueryTemplate newTemplate()
    {
        return KendoChoiceDescriptionScriptReference.templateReorderable();
    }
}
