lbm-webapp
==========

Logbook Manager Web App


Set jsf ProjectStage

@see javax.faces.application.ProjectStage for valid ProjectStatge values
    Development,
    UnitTest,
    SystemTest,
    Production;


You can use the JNDI Location java:comp/env/jsf/ProjectStage or set javax.faces.PROJECT_STAGE in web.xml.

1.) JNDI configuration
Tomcat or Spring tcServer
Edit the context.xml in your CATALINE_BASE (Servers folder in Eclipse if you're using the deveault workspace metadata) and create a "JNDI Environment" Configuration

context.xml
  <Environment name="jsf/ProjectStage" override="false" type="java.lang.String" value="Development"/>
  
web.xml
	<resource-ref>
		<res-ref-name>jsf/ProjectStage</res-ref-name>
		<res-type>java.lang.String</res-type>
	</resource-ref>


2.) If you are building a war per environment, then you may want to set the ProjectStage using the web.xml and 
setting teh context-param - javax.faces.PROJECT_STAGE

Again, refer to the Enumeration javax.faces.application.ProjectStage for valid ProjectStage values

web.xml
  <context-param>
    <param-name>javax.faces.PROJECT_STAGE</param-name>
    <param-value>Production</param-value>
  </context-param>

remove the resource-ref
	<resource-ref>
		<res-ref-name>jsf/ProjectStage</res-ref-name>
		<res-type>java.lang.String</res-type>
	</resource-ref>

 
