/*
 * Copyright 2015 i-net software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.inet.gradle.setup.deb;

import java.io.File;

import org.gradle.api.internal.project.ProjectInternal;

import com.inet.gradle.setup.AbstractSetupTask;

/**
 * The deb Gradle task. It build a deb package for Debian / Ubuntu.
 * 
 * @author Stefan Heidrich
 */
public class Deb extends AbstractSetupTask {	

    private String                 section;

    private String                 priority;

    private String                 architecture;

    private String                 installSize;
    
    private String                 recommends;
    
    private String                 depends;
    
    private String                 homepage;
    
    private String                 checkPackage;
    
    private String				   maintainerEmail;
    
    private String				   changes;
    
    private String				   installationRoot;
    
    private String				   daemonUser = "root";

	private String 				   pamConfigurationFile;
	
	private Object 				   defaultServiceFile;
	
    public Deb() {
        super( "deb" );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void build() {
        ProjectInternal project = (ProjectInternal)getProject();
        new DebBuilder( this, getSetupBuilder(), project.getFileResolver() ).build();
    }

	public String getSection() {
		return section;
	}


	public void setSection(String section) {
		this.section = section;
	}


	public String getPriority() {
		return priority;
	}


	public void setPriority(String priority) {
		this.priority = priority;
	}


	public String getArchitecture() {
		return architecture;
	}


	public void setArchitecture(String architecture) {
		this.architecture = architecture;
	}


	public String getInstallSize() {
		return installSize;
	}


	public void setInstallSize(String installSize) {
		this.installSize = installSize;
	}


	public String getRecommends() {
		return recommends;
	}


	public void setRecommends(String recommends) {
		this.recommends = recommends;
	}


	public String getDepends() {
		return depends;
	}


	public void setDepends(String depends) {
		this.depends = depends;
	}


	public String getHomepage() {
		return homepage;
	}


	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}


	public String getCheckPackage() {
		return checkPackage;
	}


	public void setCheckPackage(String checkPackage) {
		this.checkPackage = checkPackage;
	}


	public String getMaintainerEmail() {
		return maintainerEmail;
	}


	public void setMaintainerEmail(String maintainerEmail) {
		this.maintainerEmail = maintainerEmail;
	}


	public String getChanges() {
		return changes;
	}


	public void setChanges(String changes) {
		this.changes = changes;
	}
	
	/**
	 * Returns the installation root where the program directory should be located. Default is /usr/share +  basename
	 * @return the installation root directory
	 */
	public String getInstallationRoot() {
		if(installationRoot == null) {
			return "/usr/share/" + getSetupBuilder().getApplication();
		} else {
			return installationRoot;
		}
	}

	/**
	 * Sets the installation root directory where the main files should be located.
	 * If the directory ends with a / it will be removed. 
	 * @param installationRoot the installation root directory
	 */
	public void setInstallationRoot(String installationRoot) {		
		this.installationRoot = installationRoot.endsWith("/") ? installationRoot.substring(0, installationRoot.length()-1) : installationRoot;
	}
	
	
	/**
	 * Returns the user with which the daemon should be running. If no user was specified the default root user will be used. 
	 * @return the user for the daemon
	 */
	public String getDaemonUser() {
		if(daemonUser.trim().isEmpty()) {
			return "root";
		} else {
			return daemonUser;
		}
	}

	/**
	 * Sets the user with which the daemon should be running.
	 */
	public void setDaemonUser(String daemonUser) {
		this.daemonUser = daemonUser;
	}
	
	/**
	 * Returns the name with path of the PAM configuration file that should be installed 
	 * @return the name with path of the PAM configuration file
	 */
	public String getPamConfigurationFile() {
		return pamConfigurationFile;
	}

	/**
	 * Sets the name of the PAM configuration file
	 * @param pamConfigurationFile the name of the PAM configuration file
	 */
	public void setPamConfigurationFile(String pamConfigurationFile) {
		this.pamConfigurationFile = pamConfigurationFile;
	}

	/**
	 * Returns the a default service configuration file
	 * This will be included in the service starter
	 * @return the default service file
	 */
	public File getDefaultServiceFile() {
		if ( defaultServiceFile != null ) {
			return getProject().file( defaultServiceFile );
		}
		return null;
	}

	/**
	 * Set the default service configuration file
	 * The file will be put at the "/etc/default/$service$" location
	 * @param defaultServiceFile
	 */
	public void setDefaultServiceFile(Object defaultServiceFile) {
		this.defaultServiceFile = defaultServiceFile;
	}
}
