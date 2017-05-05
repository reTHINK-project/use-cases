### Actors

Consumer Alice is [Identified](https://github.com/reTHINK-project/use-cases/blob/master/docs/D1.1/business-models/business-roles.md#identified-service-consumer) by a [Service Provider](https://github.com/reTHINK-project/use-cases/blob/master/docs/D1.1/business-models/business-roles.md#service-provider)
### Pre-condition
- #84 Alice has a valid credentials / account provided by the Service Provider
### Description

Alice uses her App or browser to authenticate against the Service provider. After a successful authentication, relevant information for the service delivery about the device (e.g. capabilities) and surrounded context (e.g. user presence) is published. 
The registration session in the service is valid until the App, browser, the browser tab is closed or explicitly unregisters from the service e.g., by clicking in a “Exit” button.
#### Variant

Consumer Alice uses an Identity from a [Identity Service Provider](https://github.com/reTHINK-project/use-cases/blob/master/docs/D1.1/business-models/business-roles.md#identity-service-provider) to register and authenticate against a different [Service Provider](https://github.com/reTHINK-project/use-cases/blob/master/docs/D1.1/business-models/business-roles.md#service-provider)
### Pos-condition
### Differentiation - market relevance

basic
### Flows
- [Basic Registration](https://github.com/reTHINK-project/architecture/blob/master/docs/uml/identity%20management/UC2-Registration.md)
- [Registration with External Id](https://github.com/reTHINK-project/architecture/blob/master/docs/uml/identity%20management/UC5-LoginWithExternalId.md)

#81 Identity Management
