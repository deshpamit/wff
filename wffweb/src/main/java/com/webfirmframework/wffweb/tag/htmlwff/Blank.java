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
 * @author WFF
 */
package com.webfirmframework.wffweb.tag.htmlwff;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.webfirmframework.wffweb.tag.html.AbstractHtml;

/**
 * This class needs to be tested properly
 *
 * @author WFF
 * @since 1.0.0
 */
public class Blank extends AbstractHtml {
    /**
     *
     */
    private static final long serialVersionUID = 6048758894943757808L;

    {
        init();
    }

    /**
     * Represents the root of an HTML or XHTML document. All other elements must
     * be descendants of this element.
     *
     * @param base
     *            i.e. parent tag of this tag
     * @param children
     *            An array of {@code AbstractHtml}
     *
     * @since 1.0.0
     */
    public Blank(final AbstractHtml base, final AbstractHtml... children) {
        super(base, Arrays.asList(children));
    }

    /**
     * Represents the root of an HTML or XHTML document. All other elements must
     * be descendants of this element.
     *
     * @param base
     *            i.e. parent tag of this tag
     * @param children
     *            An array of {@code AbstractHtml}
     *
     * @since 1.0.0
     */
    public Blank(final AbstractHtml base,
            final Collection<AbstractHtml> children) {
        super(base, children);
    }

    /**
     * Represents the root of an HTML or XHTML document. All other elements must
     * be descendants of this element.
     *
     * @param base
     *            i.e. parent tag of this tag
     * @param childContent
     *
     * @since 1.0.0
     */
    public Blank(final AbstractHtml base, final String childContent) {
        super(base, childContent);
    }

    /**
     * invokes only once per object
     *
     * @author WFF
     * @since 1.0.0
     */
    protected void init() {
    }

    /**
     * adds {@code AbstractHtml}s as children.
     *
     * @param children
     * @since 1.0.0
     * @author WFF
     */
    public void addChildren(final List<AbstractHtml> children) {
        super.getChildren().addAll(children);
    }

    /**
     * adds {@code AbstractHtml}s as children.
     *
     * @param child
     * @since 1.0.0
     * @author WFF
     */
    public void addChild(final AbstractHtml child) {
        super.getChildren().add(child);
    }

    /**
     * adds {@code AbstractHtml}s as children.
     *
     * @param children
     * @since 1.0.0
     * @author WFF
     */
    public void removeChildren(final List<AbstractHtml> children) {
        super.getChildren().removeAll(children);
    }

    /**
     * adds {@code AbstractHtml}s as children.
     *
     * @param child
     * @since 1.0.0
     * @author WFF
     */
    public void removeChild(final AbstractHtml child) {
        super.getChildren().remove(child);
    }

    /**
     * removes the the child content.
     *
     * @param child
     * @since 1.0.0
     * @author WFF
     */
    public void removeChild(final String child) {
        final StringBuilder htmlMiddleSB = getHtmlMiddleSB();
        final String sb = htmlMiddleSB.toString();
        final String replaced = sb.replace(child, "");
        final int lastIndex = htmlMiddleSB.length() - 1;
        htmlMiddleSB.delete(0, lastIndex);
        htmlMiddleSB.append(replaced);
    }

    /**
     * adds {@code AbstractHtml}s as children.
     *
     * @param child
     * @since 1.0.0
     * @author WFF
     */
    public void addChild(final String child) {
        super.getChildren().add(new Blank(this, child));
    }

    /**
     * adds {@code AbstractHtml}s as children.
     *
     * @return
     * @since 1.0.0
     * @author WFF
     */
    public String getChildContent() {
        return getHtmlMiddleSB().toString();
    }
}