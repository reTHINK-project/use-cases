## Hyperty Life-cycle

### Product Conception
The Product Manager specifies the purpose, functionalities and requirements to be fulfilled by the new Hyperty.

The product concept is described by reusing as much as possible existing requirements and product concepts e.g. directories, tags, etc

According to feedback received from technical designers the Product Manager may have to update the product concept description. (this workflow is not illustrate)

<!-- 1.	Product Manager accesses the SCE with his credentials that are associated to a certain Product Management role 
2.	Product Manager is able to discover the product Catalogue to check if the requested product is a new one and to receive recommendations about similar products to help him/her in the product conception
3.	The product is specified by using as much as possible tools that facilitate reuse of product concepts e.g. directories, tags, etc and it should define at least the following information and requirements types (more information is needed for business reasons but they are not relevant for the project purposes):
a.	Unique product name.
b.	Product description.
c.	Tangible Entities to be associated with.
d.	Data needed. 
e.	Processes needed. 

4.	Existing and new Tangible Entities needed are identified. 

5.	After successful validation, the Product Catalogue Manager registers the Product Concept into the Product Catalogue.
-->

**Input**

The following input is needed:
•	Product Catalogue
•	Tangible Entities Catalogue

**Output**

Business requirements are defined.

**Automation analysis**

Semi-automation may be possible with assisting tools providing advanced search features on existing concepts and requirements.

### Product Design

The Product Manager designs the Hyperty offer by specifying Business Policies to be applied to the Hyperty.

The product business policies are designed by reusing as much as possible existing business policies.  
The following types of policies should be considered:
*	Access Control
*	Performance (QoS, QoE) - Definition of SLAs per Hyperty (e.g. latency). Definition of Rules to be used to generate KPIs reports to measure SLAs fulfillment
*	Billing - revenue model that Provider wants to put in place, with or without partners (consolidate with Partnership Model). Definition of the value chain. Who will pay resources like sensors, or network side resources like media servers or big data services? Who will pay for the service: the end-user, some advertiser, the government?


**Input**

* Applicable Business Reference Data (e.g. product access constraints, performance, customer care, revenue sharing among partners, Draft Pricing / Charging Matrix and rules etc...)
* Business Policies Catalogue.

**Output**

Hyperty Business Policies to Support Hyperties delivery are defined and Product Catalogue is Updated

**Automation analysis**

Semi-automation may be possible with assisting tools providing advanced search features on existing business policies.

### Hyperty Design

Developer or Designer validates and specify in detail how the Hyperty will comply with the Business requirements. 

This specification should reuse as much as possible existing Hyperties specifications, including unit tests, that can be cloned or extended as a design starting point.

After successful validation, the Hyperty Design Metadata is published into the Service Catalogue.

**Note**

During this activity it should be identified the need to use new Hyperty Message types or to extend existing Message Types with new data fields.
In case there is a business requirement to ensure cross domain interoperability, this would imply to perform a Hyperty Message Standardisation activity involving some Standardisation authority. In this initial version, such activity is not specified.

**Input**

The following input is needed:
•	Product Requirements defined by Product Analysts
•	Hyperty Catalogue

**Output**

•	Hyperty Design Metadata with detailed specification of the Hyperty and associated tests

**Automation analysis**

Semi-automation may be possible with assisting tools providing advanced search features on existing Hyperties Metadata design catalogue. 
Full automation is possible in case there is no need to develop a new Hyperty.

### Hyperty Implementation

The Developer validates Hyperty Design Metadata and develops the Hyperty by reusing as much as possible existing Hyperties source code that may be extended or cloned as a development starting point.

**Input**

•	Hyperty Design Metadata
•	Hyperty Catalogue

**Output**

•	Hyperty Artifacts including source code, test cases and hyperty configuration data
•	Updated Hyperty Catalogue

**Automation analysis**

Semi-automation may be possible with assisting tools providing advanced search features on existing Hyperty catalogue. 
Full automation may also be possible depending on the Hyperty complexity by reusing reusable Hyperty components (to be validated in T3.4).

### Hyperty Testing

Hyperty is tested according to executable test cases provided during Hyperty implementation. As soon as all test cases are successful, the Hyperty is submitted to be approved by the Service Provider.

**input**

* Hyperty artifacts including executable test cases

**output**

* testing reports

**Automation analysis**

Full automation of Hyperty testing should be possible.

### Hyperty Provisioning

Service Provider validates submitted Hyperty by performing end-to-end tests including Non-functional tests according to associated business policies notably Security policies and SLAs to be signed between Service Providers and the Consumer.
Therefore, the Service Provider has to assure that he can provide a specified level of robustness and QoS under different behaviour conditions. 

As soon as the Hyperty is succesfuly tested by the Service Provider, it will be certified as ready to be provisioned by creating the different deployable packages targeting different runtime environments as defined in the Business Requirments.

The Service binding is defined in terms of protocols, APIs, addresses, etc. notably for its components. The service is packaged and the runtime environment type is chosen according to Non-functional/business policies requirements (e.g. load, performance, etc.) and availability of dependencies on Service Capabilities and Service Support.

Support Services and Systems required by the Hyperty operation will be provisioned and configured according to technical dependencies (e.g. data storage) and business constraints (e.g. network and computing load forecast, charging and identity management policies).

Finaly, Hyperty is published in the Hyperty Catalogue as ready to be consumed.

**Input**

* Design Metadata
* Developed artifacts (e.g. source, binary files)
* Business Policies

**Output**

* end-to-end reports
* Hyperty Certification
* Deployable Packages
* Resources configured
* Operation Metadata
* Product and Hyperty catalogue updated

**Automation analysis**

Hyperty provisioning should be as much as possible automated. However, it is recommended to have the Hyperty certification activity approved and signed by an authorised Human.

### Access

As soon as the Hyperty is published in the Hyperty Catalogue it is discoverable to be consumed by end-users applications.

**Note:** According to Hyperty concept (see ?), Hyperties are services and not applications. I.e. Hyperties are not directly consumed by End-user Consumers but by Applications (i.e. other software programs). The life-cycle of Applications that consume Hyperties are out-scope of this task. However, the need to consider in the Hyperty life-cycle activities to support the registration of Applications consuming Hyperties should be further studied.

Access to Hyperties should be subject to authorisation mechanism according to associated access control policies defined by the service provider.

If access is granted, Hyperty is deployed into user device runtime, instantiated and securely associated to a Tangible Entity (in some cases this might not be needed eg for network side hyperties. To be further studied).




<!--
@startuml "hyperty-life-cycle.png"

|Service Provider|
start
:Catalog]

partition ProductConception {
    :conceive product;
    :business requirements]
}
    fork
partition ProductDesign {
	    :design product;
	    :business policies]
	    :Catalog]
}
	fork again

|#AntiqueWhite|Developer|
partition HypertyDesign {
	:analyse requirements;
    :design Hyperty;
    :design tests;
    :Design Metadata]
}

partition HypertyImplement {
    :implement Hyperty;
    :define Hyperty configuration;
    :Hyperty artifacts]
}

partition HypertyTest {
    :test Hyperty;
    :submit Hyperty;
}

|Service Provider|
    end fork
partition HypertyProvisioning {
	:end-to-end testing;
	:certify;
	:Hyperty Metadata]
	:resources configuration;
	:publish Hyperty;
	}

|#LightRed|Consumer|

partition Access {
	:Catalog]
	:discover;

	:access request;

	|Service Provider|
	:Product Policies]
	:authorise access;
	|Consumer|
	:deploy Hyperty;
	:associate to\nTangible Entity;
	}

partition Usage {
	:register Hyperty Instance;
	:use;
	:unregister Hyperty;
	:usage event]
}

|Service Provider|

while (active?)

partition Monitor {
	:Policies]
	:monitor;
	:KPIs]
}

if (remove service) then (yes)
partition Removal {
	:Policies]
	:remove Hyperty;
}

stop

elseif (update service?) then (yes)
partition Update {
	:Policies]
	:update Hyperty;
	detach
}


endif

endwhile

stop


@enduml
-->

![Hyperty Life-cycle](hyperty-life-cycle.png)
