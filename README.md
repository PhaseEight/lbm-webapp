#lbm-webapp


Logbook Manager Web App


##Setup

create a workspace directory e.g.

    open Git shell
    cd to d:\development\GitHub
    md lbm-webapp
    cd lbm-webapp

###clone this repository with all Branches

    git clone -o github https://github.com/pedroneil/lbm-webapp.git lbm-webapp

###clone spring-webflow

	change to your workspace
	cd D:\Development\GitHub\lbm-webapp
	git clone git://github.com/SpringSource/spring-webflow.git spring-webflow
	
	
##pulling/pushing from/to guidelines using remote/github repository:

    git stash - to make sure your changes are stash and your copy is reverted to the last commit that is in-sync with the remote.
    git pull - pull changes from remote
    git stash pop - to merge your changes to latest source code
    git mergetool - if there are conflicts, you need to do this before the changes are merged.
    git commit - to commit your changes in your local repo
    git push - to push your changes to remote.


## Configuration

Set jsf ProjectStage

see **javax.faces.application.ProjectStage** for valid ProjectStatge values    
	
    Development,
    UnitTest,
    SystemTest,
    Production;


You can use the JNDI Location java:comp/env/jsf/ProjectStage or set javax.faces.PROJECT_STAGE in web.xml.

##JNDI configuration

Tomcat or Spring tc Server

Edit the context.xml in your CATALINE_BASE (Servers folder in Eclipse if you're using the deveault workspace metadata) and create a "JNDI Environment" Configuration

    context.xml
        <Environment name="jsf/ProjectStage" override="false" type="java.lang.String" value="Development"/>
  
###web.xml

    <resource-ref>
        <res-ref-name>jsf/ProjectStage</res-ref-name>
        <res-type>java.lang.String</res-type>
    </resource-ref>


If you are building a war per environment, then you may want to set the ProjectStage using the web.xml and 
setting the context-param - javax.faces.PROJECT_STAGE.

Supported Project Stage Values
    Development,
    UnitTest,
    SystemTest,
    Production
     
    web.xml
    <context-param>
        <param-name>javax.faces.PROJECT_STAGE</param-name>
        <param-value>Production</param-value>
     </context-param>
         

###Remove the JNDI resource-ref.

    <resource-ref>
        <res-ref-name>jsf/ProjectStage</res-ref-name>
	<res-type>java.lang.String</res-type>
    </resource-ref>
    
 
##Third Party Libraries used by Logbook Manager
 
1. Install PrimeFaces into your Local Maven Repository 
	
	@see http://jeff.langcode.com/archives/27

	mvn install:install-file -Dfile=D:\development\resources\primefaces-4.0.13\primefaces-4.0.13.jar -DgroupId=org.primefaces -DartifactId=primefaces -Dversion=4.0.13 -Dpackaging=jar -DgeneratePom=true

	mvn install:install-file -Dfile=-Dfile=D:\development\resources\primefaces-4.0.13\primefaces-4.0.13-sources.jar -DgroupId=org.primefaces -DartifactId=primefaces -Dversion=4.0.13 -Dpackaging=source
