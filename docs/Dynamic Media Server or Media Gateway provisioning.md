### Actors

Alice and Bob are [Identified Consumer](https://github.com/reTHINK-project/use-cases/blob/master/docs/D1.1/business-models/business-roles.md#identified-service-consumer) that are [Conversation Participants](https://github.com/reTHINK-project/use-cases/blob/master/docs/D1.1/business-models/business-roles.md#conversation--communication-participant)

Alice and Bob are subscribers of different and incompatible [Communication Service Providers](https://github.com/reTHINK-project/use-cases/blob/master/docs/D1.1/business-models/business-roles.md#communication-service-provider)  e.g. WebRTC based and PSTN telephony.

**Inter** is a [Communication Service Provider)](https://github.com/reTHINK-project/use-cases/blob/master/docs/D1.1/business-models/business-roles.md#communication-service-provider) providing different Telephony Gateway services.
### Pre-conditions:
- #81  #83 Alice and Bob are registered in different CSP.
- Alice knows Bob identifier
- **Inter** service is discoverable _TODO: link to use cases about services publish_
- Alice's CSP is able to pay **Inter** for its communication interoperability services _TODO: link to use cases about (wholesale?) adhoc payments/settlements?_
### Description

Similar to #1 but in this case each participant uses different NSPs that implies the usage of a Media Gateway service, 
1. Alice pushes the button to invite Bob for an audio/video conversation.
2. Different Interoperability Services with a summary of price and quality are recommended to Alice
3. Alice selects **Inter** service, the call is notified to Bob and accepted by him.

Remaining procedures are similar to #1
#### Media Server Variant

The same approach can be followed for other network side Communication services e.g. MCU services to support large Multiparty Conversations.
### Differentiation – market relevance

Currently, not possible.

![Uploading Dynamic NE Provisioning.png . . .]()

#13 H2H Communication UC
