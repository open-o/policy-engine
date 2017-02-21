/**
 * Copyright 2017 ZTE Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openo.policy.engine.pdp.drools;

import org.drools.compiler.kie.builder.impl.InternalKieModule;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.ReleaseId;
import org.kie.api.builder.model.KieBaseModel;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.builder.model.KieSessionModel;
import org.kie.api.conf.EqualityBehaviorOption;
import org.kie.api.conf.EventProcessingOption;

public class KieJarFactory {
	  
  
    public static InternalKieModule createKieJar(KieServices ks, ReleaseId releaseId, ResourceWrapper resourceWrapper) {  
        KieFileSystem kfs = createKieFileSystemWithKProject(ks, true);  
        kfs.writePomXML(getPom(releaseId));  
        kfs.write("src/main/resources/" + resourceWrapper.getTargetResourceName(), resourceWrapper.getResource());  
        KieBuilder kieBuilder = ks.newKieBuilder(kfs);  
        if (!kieBuilder.getResults().getMessages().isEmpty()) {  
            System.out.println(kieBuilder.getResults().getMessages());  
            throw new IllegalStateException("Error creating KieBuilder.");  
        }  
        return (InternalKieModule) kieBuilder.getKieModule();  
    }  
  
    /** 
     * 创建默认的kbase和stateful的kiesession 
     * 
     * @param ks 
     * @param isdefault 
     * @return 
     */  
    private static KieFileSystem createKieFileSystemWithKProject(KieServices ks, boolean isdefault) {  
        KieModuleModel kproj = ks.newKieModuleModel();  
        KieBaseModel kieBaseModel1 = kproj.newKieBaseModel("KBase").setDefault(isdefault)  
                .setEqualsBehavior(EqualityBehaviorOption.EQUALITY).setEventProcessingMode(EventProcessingOption.STREAM);  
        // Configure the KieSession.  
        kieBaseModel1.newKieSessionModel("KSession").setDefault(isdefault)  
                .setType(KieSessionModel.KieSessionType.STATEFUL);  
        KieFileSystem kfs = ks.newKieFileSystem();  
        kfs.writeKModuleXML(kproj.toXML());  
        return kfs;  
    }  
  
    /** 
     * 创建kjar的pom 
     * 
     * @param releaseId 
     * @param dependencies 
     * @return 
     */  
    private static String getPom(ReleaseId releaseId, ReleaseId... dependencies) {  
        String pom = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"  
                + "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"  
                + "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd\">\n"  
                + "  <modelVersion>4.0.0</modelVersion>\n" + "\n" + "  <groupId>" + releaseId.getGroupId()  
                + "</groupId>\n" + "  <artifactId>" + releaseId.getArtifactId() + "</artifactId>\n" + "  <version>"  
                + releaseId.getVersion() + "</version>\n" + "\n";  
        if (dependencies != null && dependencies.length > 0) {  
            pom += "<dependencies>\n";  
            for (ReleaseId dep : dependencies) {  
                pom += "<dependency>\n";  
                pom += "  <groupId>" + dep.getGroupId() + "</groupId>\n";  
                pom += "  <artifactId>" + dep.getArtifactId() + "</artifactId>\n";  
                pom += "  <version>" + dep.getVersion() + "</version>\n";  
                pom += "</dependency>\n";  
            }  
            pom += "</dependencies>\n";  
        }  
        pom += "</project>";  
        return pom;  
    } 

}
