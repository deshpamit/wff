package com.webfirmframework.wffweb.tag.html;

import java.util.logging.Logger;

import com.webfirmframework.wffweb.settings.WffConfiguration;
import com.webfirmframework.wffweb.tag.html.attribute.core.AbstractAttribute;
import com.webfirmframework.wffweb.tag.html.identifier.DelAttribute;
import com.webfirmframework.wffweb.tag.html.identifier.GlobalAttribute;

/**
 * @author WFF
 * @since 1.0.0
 * @version 1.0.0
 *
 */
public class Del extends AbstractHtml {

    private static final long serialVersionUID = -8173178025744777479L;

    public static final Logger LOGGER = Logger.getLogger(Del.class.getName());

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
    public Del(final AbstractHtml base, final AbstractAttribute... attributes) {
        super(Del.class.getSimpleName().toLowerCase(), base, attributes);
        if (WffConfiguration.isDirectionWarningOn()) {
            for (final AbstractAttribute abstractAttribute : attributes) {
                if (!(abstractAttribute != null && (abstractAttribute instanceof DelAttribute || abstractAttribute instanceof GlobalAttribute))) {
                    LOGGER.warning(abstractAttribute
                            + " is not an instance of DelAttribute");
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

}
