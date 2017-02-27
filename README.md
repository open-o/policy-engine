##policy engine
Within the OPEN-O architecture, there are a lot of microservices, e.g., Catalog, Res Mgr., LCM Mgr., Drivers. These microservices are distributed on multiple hosts, that make the communicate between them complex because the consumers need to know the addresses of all the service providers. Besides, some services may have multiple instances, which make the consumer even harder to locate the service provider. Microservice bus provides a service registration/ discovery and routing mechanism to simply the communications between services. The consumers only need to talk with microservice bus without any address information of individual service providers. 
##Runtime Requirements
* Java 8

##Run

1. Install 1.8
1. export JAVA_HOME= "$JAVA_INSTALLATION_PATH" , please replace $JAVA_INSTALLATION_PATH with the JDK installation directory on your host
1. Compile form source or download the latest snapshot from https://nexus.open-o.org/content/repositories/snapshots/org/openo/common-services/microservice-bus/msb-core-standalone/1.0.0-SNAPSHOT/


## License
The Policy-engine is released under version 2.0 of the [Apache License][].

[Apache License]: http://www.apache.org/licenses/LICENSE-2.0

 