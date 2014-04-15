if (db.accesstoken.count() == 0) {
	db.accesstoken.save({
		"_id" : "e8f5170c-877b-416f-b70f-4b09772f8e2d",
		"application" : "adminconsole",
		"tenantPermissions" : [
			{
                "allowedActions" : [ "*" ],
				"tenant" : "*"
			}
		],
		"expiresOn" : ISODate("2024-01-01T06:00:00Z")
	});
}
if (db.tenant.count() == 0) {
	db.tenant.save({
		"name" : "default",
		"verifyNewProfiles" : false,
		"roles" : [ "ADMIN" ],
		"attributeDefinitions" : [
            {
                "name" : "firstName",
                "label" : "First Name",
                "order" : 0,
                "type" : "java.lang.String",
                "constraint" : "",
                "required" : false,
                "owner" : "adminconsole",
                "permissions" : [
                    {
                        "application" : "*",
                        "actions" : "*"
                    }
                ]
            },
            {
                "name" : "lastName",
                "label" : "Last Name",
                "order" : 0,
                "type" : "java.lang.String",
                "constraint" : "",
                "required" : false,
                "owner" : "adminconsole",
                "permissions" : [
                    {
                        "application" : "*",
                        "actions" : "*"
                    }
                ]
            }
        ]
	});
}