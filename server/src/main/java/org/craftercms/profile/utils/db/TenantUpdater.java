package org.craftercms.profile.utils.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.Predicate;
import org.craftercms.commons.mongo.MongoDataException;
import org.craftercms.commons.mongo.UpdateHelper;
import org.craftercms.profile.api.AttributeDefinition;
import org.craftercms.profile.api.Tenant;
import org.craftercms.profile.repositories.TenantRepository;

/**
 * Created by alfonsovasquez on 20/6/16.
 */
public class TenantUpdater {

    protected Tenant tenant;
    protected UpdateHelper updateHelper;
    protected TenantRepository tenantRepository;

    public TenantUpdater(Tenant tenant, UpdateHelper updateHelper, TenantRepository tenantRepository) {
        this.tenant = tenant;
        this.updateHelper = updateHelper;
        this.tenantRepository = tenantRepository;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setVerifyNewProfiles(boolean verifyNewProfiles) {
        tenant.setVerifyNewProfiles(verifyNewProfiles);
        updateHelper.set("verifyNewProfiles", verifyNewProfiles);
    }

    public void setAvailableRoles(Set<String> availableRoles) {
        tenant.setAvailableRoles(availableRoles);
        updateHelper.set("availableRoles", availableRoles);
    }

    public void addAvailableRoles(Collection<String> availableRoles) {
        Set<String> allAvailableRoles = tenant.getAvailableRoles();
        List<String> pushValues = new ArrayList<>();

        for (String role : availableRoles) {
            if (allAvailableRoles.add(role)) {
                pushValues.add(role);
            }
        }

        updateHelper.pushAll("availableRoles", pushValues);
    }

    public void removeAvailableRoles(Collection<String> availableRoles) {
        Set<String> allAvailableRoles = tenant.getAvailableRoles();
        List<String> pullValues = new ArrayList<>();

        for (String role : availableRoles) {
            if (allAvailableRoles.remove(role)) {
                pullValues.add(role);
            }
        }

        updateHelper.pullAll("availableRoles", pullValues);
    }

    public void setSsoEnabled(boolean ssoEnabled) {
        tenant.setSsoEnabled(ssoEnabled);
        updateHelper.set("ssoEnabled", ssoEnabled);
    }

    public void setAttributeDefinitions(List<AttributeDefinition> attributeDefinitions) {
        tenant.setAttributeDefinitions(attributeDefinitions);
        updateHelper.set("attributeDefinitions", attributeDefinitions);
    }

    public void addAttributeDefinitions(Collection<AttributeDefinition> attributeDefinitions) {
        List<AttributeDefinition> allDefinitions = tenant.getAttributeDefinitions();
        List<AttributeDefinition> pushValues = new ArrayList<>();

        for (AttributeDefinition definition : attributeDefinitions) {
            if (!allDefinitions.contains(definition)) {
                allDefinitions.add(definition);
                pushValues.add(definition);
            }
        }

        updateHelper.pushAll("attributeDefinitions", pushValues);
    }

    public void updateAttributeDefinitions(Collection<AttributeDefinition> attributeDefinitions) {
        List<AttributeDefinition> allDefinitions = tenant.getAttributeDefinitions();

        for (AttributeDefinition definition : attributeDefinitions) {
            int idx = indexOfAttributeDefinition(definition.getName(), allDefinitions);
            if (idx >= 0) {
                allDefinitions.set(idx, definition);

                updateHelper.set("attributeDefinitions." + idx, definition);
            }
        }
    }

    public void removeAttributeDefinitions(Collection<String> attributeNames) {
        List<AttributeDefinition> attributeDefinitions = tenant.getAttributeDefinitions();
        List<Map<String, String>> pullValues = new ArrayList<>();

        for (String attributeName : attributeNames) {
            for (Iterator<AttributeDefinition> iter = attributeDefinitions.iterator(); iter.hasNext();) {
                AttributeDefinition definition = iter.next();
                if (definition.getName().equals(attributeName)) {
                    iter.remove();

                    pullValues.add(Collections.singletonMap("name", attributeName));

                    break;
                }
            }
        }

        updateHelper.pullAll("attributeDefinitions", pullValues);
    }

    public void update() throws MongoDataException {
        updateHelper.executeUpdate(tenant.getId().toString(), tenantRepository);
    }

    protected int indexOfAttributeDefinition(final String name, List<AttributeDefinition> definitions) {
        return ListUtils.indexOf(definitions, new Predicate<AttributeDefinition>() {

            @Override
            public boolean evaluate(AttributeDefinition definition) {
                return definition.getName().equals(name);
            }

        });
    }

}
