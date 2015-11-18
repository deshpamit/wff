package com.webfirmframework.wffweb.tag.html.attribute.core;

import java.nio.charset.Charset;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.webfirmframework.wffweb.tag.core.AbstractTagBase;
import com.webfirmframework.wffweb.tag.html.AbstractHtml;
import com.webfirmframework.wffweb.util.StringBuilderUtil;

public abstract class AbstractAttribute extends AbstractTagBase {

    private static final long serialVersionUID = 2129619684296404972L;

    private String attributeName;
    private String attributeValue;
    private Map<String, String> attributeValueMap;
    private Set<String> attributeValueSet;

    private AbstractHtml ownerTag;

    private StringBuilder tagBuilder;

    private transient Charset charset = Charset.defaultCharset();

    {
        init();
    }

    private void init() {
        tagBuilder = new StringBuilder();
        setRebuild(true);
    }

    /**
     * @return {@code String} equalent to the html string of the tag including
     *         the child tags.
     * @since 1.0.0
     * @author WFF
     */
    protected String getPrintStructure() {
        String printStructure = null;
        if (isRebuild() || isModified()) {
            printStructure = getPrintStructure(isRebuild());
            setRebuild(false);
        } else {
            printStructure = tagBuilder.toString();
        }
        return printStructure;
    }

    protected String getPrintStructure(final boolean rebuild) {
        String result = "";
        if (rebuild || isRebuild() || isModified()) {
            beforePrintStructure();
            tagBuilder.delete(0, tagBuilder.length());
            // tagBuildzer.append(" ");
            tagBuilder.append(getAttributeName());
            if (getAttributeValue() != null) {
                tagBuilder.append("=\"");
                tagBuilder.append(getAttributeValue());
                result = StringBuilderUtil.getTrimmedString(tagBuilder) + "\"";
                tagBuilder.append("\"");
            } else if (attributeValueMap != null
                    && attributeValueMap.size() > 0) {
                tagBuilder.append("=\"");
                final Set<Entry<String, String>> entrySet = getAttributeValueMap()
                        .entrySet();
                for (final Entry<String, String> entry : entrySet) {
                    tagBuilder.append(entry.getKey());
                    tagBuilder.append(": ");
                    tagBuilder.append(entry.getValue());
                    tagBuilder.append("; ");
                }

                result = StringBuilderUtil.getTrimmedString(tagBuilder) + "\"";
                tagBuilder.append("\"");
            } else if (attributeValueSet != null
                    && attributeValueSet.size() > 0) {
                tagBuilder.append("=\"");
                for (final String each : getAttributeValueSet()) {
                    tagBuilder.append(each);
                    tagBuilder.append(" ");
                }
                result = StringBuilderUtil.getTrimmedString(tagBuilder) + "\"";
                tagBuilder.append("\"");
            } else {
                result = tagBuilder.toString();
            }
            /*
             * int lastIndex = tagBuilder.length() - 1;
             *
             * should be analyzed for optimization (as the deleteCharAt method
             * might compromise performance).
             *
             * if (Character.isWhitespace(tagBuilder.charAt(lastIndex))) {
             * tagBuilder.deleteCharAt(lastIndex); }
             */
            // result = StringBuilderUtil.getTrimmedString(tagBuilder) + "\"";
            // tagBuilder.append("\"");
            setRebuild(false);
        }
        tagBuilder.trimToSize();
        return result;
    }

    /**
     * @return the attributeName set by
     *         {@code AbstractAttribute#setAttributeName(String)}
     * @since 1.0.0
     * @author WFF
     */
    public String getAttributeName() {
        return attributeName;
    }

    /**
     * Set attribute name, eg: width, height, name, type etc..
     *
     * @param attributeName
     *            the attributeName to set
     * @since 1.0.0
     * @author WFF
     */
    protected void setAttributeName(final String attributeName) {
        this.attributeName = attributeName;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.webfirmframework.wffweb.tag.Base#toHtmlString()
     *
     * @since 1.0.0
     *
     * @author WFF
     */
    @Override
    public String toHtmlString() {
        return getPrintStructure(true);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.webfirmframework.wffweb.tag.core.TagBase#toHtmlString(java.nio.
     * charset.Charset)
     */
    @Override
    public String toHtmlString(final Charset charset) {
        final Charset previousCharset = this.charset;
        try {
            this.charset = charset;
            return toHtmlString();
        } finally {
            this.charset = previousCharset;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.webfirmframework.wffweb.tag.core.TagBase#toHtmlString(java.lang.
     * String)
     */
    @Override
    public String toHtmlString(final String charset) {
        final Charset previousCharset = this.charset;
        try {
            this.charset = Charset.forName(charset);
            return toHtmlString();
        } finally {
            this.charset = previousCharset;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.webfirmframework.wffweb.tag.Base#toHtmlString(boolean)
     *
     * @since 1.0.0
     *
     * @author WFF
     */
    @Override
    public String toHtmlString(final boolean rebuild) {
        return getPrintStructure(rebuild);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.webfirmframework.wffweb.tag.core.TagBase#toHtmlString(boolean,
     * java.nio.charset.Charset)
     */
    @Override
    public String toHtmlString(final boolean rebuild, final Charset charset) {
        final Charset previousCharset = this.charset;
        try {
            this.charset = charset;
            return toHtmlString(rebuild);
        } finally {
            this.charset = previousCharset;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.webfirmframework.wffweb.tag.core.TagBase#toHtmlString(boolean,
     * java.lang.String)
     */
    @Override
    public String toHtmlString(final boolean rebuild, final String charset) {
        final Charset previousCharset = this.charset;
        try {
            this.charset = Charset.forName(charset);
            return toHtmlString(rebuild);
        } finally {
            this.charset = previousCharset;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getPrintStructure();
    }

    /**
     * @return the attributeValueMap
     * @since 1.0.0
     * @author WFF
     */
    protected Map<String, String> getAttributeValueMap() {
        if (attributeValueMap == null) {
            setAttributeValueMap(new LinkedHashMap<String, String>());
        }
        return attributeValueMap;
    }

    /**
     * @param attributeValueMap
     *            the attributeValueMap to set
     * @since 1.0.0
     * @author WFF
     */
    protected void setAttributeValueMap(
            final Map<String, String> attributeValueMap) {
        if (this.attributeValueMap != null
                && !this.attributeValueMap.equals(attributeValueMap)) {
            setModified(true);
        } else if (this.attributeValueMap == null
                && attributeValueMap != null) {
            setModified(true);
        }
        this.attributeValueMap = attributeValueMap;
    }

    /**
     * adds the given key value.
     *
     * @param key
     * @param value
     * @return true if it is modified
     * @since 1.0.0
     * @author WFF
     */
    protected boolean addToAttributeValueMap(final String key,
            final String value) {

        final Map<String, String> attributeValueMap = getAttributeValueMap();

        if (!attributeValueMap.containsKey(key)
                || !value.equals(attributeValueMap.get(key))) {
            attributeValueMap.put(key, value);
            setModified(true);
            return true;
        }
        return false;
    }

    /**
     * adds all to the attribute value map.
     *
     * @param map
     * @since 1.0.0
     * @author WFF
     * @return true if it is modified
     */
    protected boolean addAllToAttributeValueMap(final Map<String, String> map) {
        if (map != null && map.size() > 0) {
            getAttributeValueMap().putAll(map);
            setModified(true);
            return true;
        }
        return false;
    }

    /**
     * removes the key value for the input key.
     *
     * @param key
     * @since 1.0.0
     * @author WFF
     * @return true if the given key (as well as value contained corresponding
     *         to it) has been removed.
     */
    protected boolean removeFromAttributeValueMap(final String key) {
        boolean result = false;
        if (getAttributeValueMap().containsKey(key)) {
            setModified(true);
            result = true;
            getAttributeValueMap().remove(key);
        }
        return result;
    }

    /**
     * removes only if the key and value matches in the map for any particular
     * entry.
     *
     * @param key
     * @param value
     * @since 1.0.0
     * @author WFF
     * @return true if it is modified
     */
    protected boolean removeFromAttributeValueMap(final String key,
            final String value) {
        if (value == getAttributeValueMap().get(key) || (value != null
                && value.equals(getAttributeValueMap().get(key)))) {
            getAttributeValueMap().remove(key);
            setModified(true);
            return true;
        }
        return false;
    }

    protected void removeAllFromAttributeValueMap() {
        if (attributeValueMap != null && getAttributeValueMap().size() > 0) {
            getAttributeValueMap().clear();
            setModified(true);
        }
    }

    /**
     * @return the attributeValue
     * @since 1.0.0
     * @author WFF
     */
    public String getAttributeValue() {
        return attributeValue;
    }

    /**
     * @param attributeValue
     *            the attributeValue to set
     * @since 1.0.0
     * @author WFF
     */
    protected void setAttributeValue(final String attributeValue) {
        if (this.attributeValue != null
                && !this.attributeValue.equals(attributeValue)) {
            setModified(true);
        } else if (this.attributeValue == null && attributeValue != null) {
            setModified(true);
        }
        this.attributeValue = attributeValue;

    }

    /**
     * @return the ownerTag
     * @since 1.0.0
     * @author WFF
     */
    public AbstractHtml getOwnerTag() {
        return ownerTag;
    }

    /**
     * @param ownerTag
     *            the ownerTag to set
     * @since 1.0.0
     * @author WFF
     */
    public void setOwnerTag(final AbstractHtml ownerTag) {
        this.ownerTag = ownerTag;
    }

    @Override
    public void setModified(final boolean modified) {
        super.setModified(modified);
        if (getOwnerTag() != null && getOwnerTag().getSharedObject() != null) {
            getOwnerTag().getSharedObject().setChildModified(modified);
        }
    }

    /**
     * @return the attributeValueSet
     * @since 1.0.0
     * @author WFF
     */
    protected Set<String> getAttributeValueSet() {
        if (attributeValueSet == null) {
            attributeValueSet = new LinkedHashSet<String>();
        }
        return attributeValueSet;
    }

    /**
     * @param attributeValueSet
     *            the attributeValueSet to set
     * @since 1.0.0
     * @author WFF
     */
    protected void setAttributeValueSet(final Set<String> attributeValueSet) {
        if (this.attributeValueSet != null
                && !this.attributeValueSet.equals(attributeValueSet)) {
            setModified(true);
        } else if (this.attributeValueSet == null
                && attributeValueSet != null) {
            setModified(true);
        }
        this.attributeValueSet = attributeValueSet;
    }

    /**
     * adds to the attribute value set.
     *
     * @param value
     * @since 1.0.0
     * @author WFF
     */
    protected void addToAttributeValueSet(final String value) {
        getAttributeValueSet().add(value);
        setModified(true);
    }

    /**
     * adds all to the attribute value set.
     *
     * @param value
     * @since 1.0.0
     * @author WFF
     */
    protected void addAllToAttributeValueSet(final Collection<String> values) {
        if (values != null && !getAttributeValueSet().containsAll(values)) {
            getAttributeValueSet().addAll(values);
            setModified(true);
        }
    }

    /**
     * removes the value from the the attribute set.
     *
     * @param value
     * @since 1.0.0
     * @author WFF
     */
    protected void removeFromAttributeValueSet(final String value) {
        getAttributeValueSet().remove(value);
        setModified(true);
    }

    /**
     * removes the value from the the attribute set.
     *
     * @param values
     * @since 1.0.0
     * @author WFF
     */
    protected void removeAllFromAttributeValueSet(
            final Collection<String> values) {
        getAttributeValueSet().removeAll(values);
        setModified(true);
    }

    /**
     * clears all values from the value set.
     *
     * @since 1.0.0
     * @author WFF
     */
    protected void removeAllFromAttributeValueSet() {
        getAttributeValueSet().clear();
    }

    /**
     * invokes just before {@code getPrintStructure(final boolean} method and
     * only if the getPrintStructure(final boolean} rebuilds the structure.
     *
     * @since 1.0.0
     * @author WFF
     */
    protected void beforePrintStructure() {
        // TODO override and use
    }

    /**
     * @return the charset
     */
    public Charset getCharset() {
        return charset;
    }

    /**
     * @param charset
     *            the charset to set
     */
    public void setCharset(final Charset charset) {
        this.charset = charset;
    }
}
