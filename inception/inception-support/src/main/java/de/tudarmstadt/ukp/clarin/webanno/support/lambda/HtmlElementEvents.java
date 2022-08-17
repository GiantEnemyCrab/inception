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
package de.tudarmstadt.ukp.clarin.webanno.support.lambda;

public interface HtmlElementEvents
{
    /**
     * @see <a href=
     *      "https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement/change_event">HTMLElement:
     *      change event</a>
     */
    public static final String CHANGE_EVENT = "change";

    /**
     * @see <a href=
     *      "https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement/input_event">HTMLElement:
     *      input event</a>
     */
    public static final String INPUT_EVENT = "input";

    public static final String KEYDOWN_EVENT = "keydown";

    public static final String KEYUP_EVENT = "keyup";

    public static final String KEYPRESS_EVENT = "keypress";
}
