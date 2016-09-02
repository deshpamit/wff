/*
 * Copyright 2014-2016 Web Firm Framework
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
package com.webfirmframework.wffweb.page;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import com.webfirmframework.wffweb.tag.html.AbstractHtml;
import com.webfirmframework.wffweb.tag.html.attribute.core.AbstractAttribute;
import com.webfirmframework.wffweb.tag.html.listener.InnerHtmlAddListener;
import com.webfirmframework.wffweb.util.WffBinaryMessageUtil;
import com.webfirmframework.wffweb.util.data.NameValue;

class InnerHtmlAddListenerImpl implements InnerHtmlAddListener {

    private static final long serialVersionUID = 1L;

    public static final Logger LOGGER = Logger
            .getLogger(InnerHtmlAddListenerImpl.class.getName());

    private BrowserPage browserPage;

    @SuppressWarnings("unused")
    private InnerHtmlAddListenerImpl() {
        throw new AssertionError();
    }

    InnerHtmlAddListenerImpl(final BrowserPage browserPage) {
        this.browserPage = browserPage;
    }

    @Override
    public void innerHtmlAdded(final Event event) {

        // should always be taken from browserPage as it could be changed
        final WebSocketPushListener wsListener = browserPage.wsListener;

        final AbstractHtml parentTag = event.getParentTag();
        final AbstractHtml innerHtmlTag = event.getInnerHtmlTag();

        //@formatter:off
        // removed all children tags task format :-
        // { "name": task_byte, "values" : [ADDED_INNER_HTML_byte_from_Task_enum]}, { "name": data-wff-id, "values" : [ parent_tag_name, html_string ]}
        // { "name": 2, "values" : [[3]]}, { "name":"C55", "values" : ["div", "<span></span>]}
        //@formatter:on

        try {
            final NameValue task = Task.ADDED_INNER_HTML.getTaskNameValue();

            final List<NameValue> nameValues = new LinkedList<NameValue>();
            nameValues.add(task);

            final AbstractAttribute attribute = parentTag
                    .getAttributeByName("data-wff-id");

            if (attribute != null) {

                final NameValue nameValue = new NameValue();

                final byte[][] tagNameAndWffId = DataWffIdUtil
                        .getTagNameAndWffId(parentTag);

                final byte[] parentWffIdBytes = tagNameAndWffId[1];

                nameValue.setName(parentWffIdBytes);

                final byte[] parentTagName = tagNameAndWffId[0];

                nameValue.setValues(parentTagName,
                        innerHtmlTag.toHtmlString("UTF-8").getBytes("UTF-8"));

                final byte[] wffBMBytes = WffBinaryMessageUtil.VERSION_1
                        .getWffBinaryMessageBytes(task, nameValue);

                wsListener.push(wffBMBytes);
            } else {
                LOGGER.severe("Could not find data-wff-id from owner tag");
            }
        } catch (final UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}