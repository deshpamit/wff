package com.webfirmframework.wffweb.tag.html;

import java.util.logging.Logger;

import com.webfirmframework.wffweb.settings.WffConfiguration;
import com.webfirmframework.wffweb.tag.html.attribute.core.AbstractAttribute;
import com.webfirmframework.wffweb.tag.html.identifier.GlobalAttribute;
import com.webfirmframework.wffweb.tag.html.identifier.LinkAttribute;

/**
 * @author WFF
 * @since 1.0.0
 * @version 1.0.0
 *
 */
public class Link extends AbstractHtml {

    private static final long serialVersionUID = 7364825383842623208L;

    public static final Logger LOGGER = Logger.getLogger(Link.class.getName());

    private static TagType tagType = TagType.SELF_CLOSING;

    {
        init();
    }

    /**
     * Represents the root of an HTML or XHTML document. All other elements must
     * be descendants of this element.
     *
     * @param base
     *            i.e. parent tag of this tag
     * @param attributes
     *            An array of {@code AbstractAttribute}
     *
     * @since 1.0.0
     */
    public Link(final AbstractHtml base,
            final AbstractAttribute... attributes) {
        super(tagType, TagNameConstants.LINK, base, attributes);
        if (WffConfiguration.isDirectionWarningOn()) {
            for (final AbstractAttribute abstractAttribute : attributes) {
                if (!(abstractAttribute != null
                        && (abstractAttribute instanceof LinkAttribute
                                || abstractAttribute instanceof GlobalAttribute))) {
                    LOGGER.warning(abstractAttribute
                            + " is not an instance of LinkAttribute");
                }
            }
        }
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
     * @param selfClosing
     *            <code>true</code> to set as self closing tag and
     *            <code>false</code> for not to set as self closing tag. The
     *            default value is <code>true</code>.
     * @since 1.0.0
     * @author WFF
     */
    public static void setSelfClosing(final boolean selfClosing) {
        Link.tagType = selfClosing ? TagType.SELF_CLOSING : TagType.NON_CLOSING;
    }

    /**
     * @param nonClosing
     *            <code>true</code> to set as self closing tag and
     *            <code>false</code> for not to set as self closing tag. The
     *            default value is <code>true</code>.
     * @since 1.0.0
     * @author WFF
     */
    public static void setNonClosing(final boolean nonClosing) {
        Link.tagType = nonClosing ? TagType.NON_CLOSING : TagType.SELF_CLOSING;
    }

    /**
     * @return
     * @since 1.0.0
     * @author WFF
     */
    public static boolean isSelfClosing() {
        return Link.tagType == TagType.SELF_CLOSING;
    }

    /**
     * @return
     * @since 1.0.0
     * @author WFF
     */
    public static boolean isNonClosing() {
        return Link.tagType == TagType.NON_CLOSING;
    }

}
