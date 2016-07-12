@startuml "user-life-cycle.png"

|User |
start

partition UserCreation {
    :GUIDcreation;
    :GlobalRegistryProvisioned]
}

partition IdentitiesAssociation {
    :IdentityAssociation;
    :GlobalRegistryProvisioned]
}

partition UserPoliciesCreation {
    :policiesCreation;
    :policiesSet]
}

if () then

while (active)

partition UserPoliciesUpdate {
    :policiesUpdate;
    :policiesSet]
}
endwhile

stop


else

|#AntiqueWhite|ApplicationProvider|

if (AccountRequired?) then (no)

:UsageWithoutSubscription;

else (yes)
		:createApplicationAccount;
		:associateWithIdentitiesOrIDP;

|#AntiqueRed|HypertyProvider|

partition HypertyAccountCreation{		
		:createAccount;
    :associateWithGUID;
		:associateWithIdentitiesOrIDP;
		:hypertyAccountProvisioned]
	}
|User |

while (active)
partition AccountUpdate{		
		:updatePreferences;
		:updatePolicies;
		:changeIdentitiesOrIDP;
		:hypertyAccountUpdated;
	}
endwhile (unsubscribe)

partition AccountRemoved{
	:userUnsubscribe;
	}


end

@enduml
