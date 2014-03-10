package org.craftercms.profile.api;


import java.util.ArrayList;
import java.util.List;

import org.craftercms.commons.security.permissions.SecuredObjectBase;

/**
 * Represents the definition of an attribute in a tenant.
 *
 * @author avasquez
 */
public class AttributeDefinition extends SecuredObjectBase<AttributePermission> {

    private String name;
    private String label;
    private int order;
    private String type;
    private String constraint;
    private boolean required;
    private String owner;
    private List<AttributeDefinition> subAttributeDefinitions;

    /**
     * Returns the name of the attribute.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the attribute name.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Returns the label that's displayed for the attribute on applications like Admin Consoles.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets the label that's displayed for the attribute on applications like Admin Consoles.
     */
    public void setLabel(final String label) {
        this.label = label;
    }

    /**
     * Returns the display order of the attribute on applications like Admin Console.
     */
    public int getOrder() {
        return order;
    }

    /**
     * Sets the display order of the attribute on applications like Admin Console.
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * Returns the type (class) of the attribute to use for convert.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the attribute (class) to use for convert.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns the constraint used to validate the field (regex).
     */
    public String getConstraint() {
        return constraint;
    }

    /**
     * Sets the constraint used to validate the field (regex).
     */
    public void setConstraint(final String constraint) {
        this.constraint = constraint;
    }

    /**
     * Returns true if the attribute is required, false otherwise.
     */
    public boolean isRequired() {
        return required;
    }

    /**
     * Sets if the attribute is required or not.
     */
    public void setRequired(final boolean required) {
        this.required = required;
    }

    /**
     * Returns the owner (application) of the attribute.
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the owner (application) of the attribute
     */
    public void setOwner(final String owner) {
        this.owner = owner;
    }

    /**
     * Returns the sub-attributes of this attribute.
     */
    public List<AttributeDefinition> getSubAttributeDefinitions() {
        if (subAttributeDefinitions == null) {
            subAttributeDefinitions = new ArrayList<>();
        }

        return subAttributeDefinitions;
    }

    /**
     * Sets the sub-attributes of this attribute.
     */
    public void setSubAttributeDefinitions(final List<AttributeDefinition> subAttributeDefinitions) {
        this.subAttributeDefinitions = subAttributeDefinitions;
    }

}