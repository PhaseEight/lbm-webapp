@ECHO off

SET CONTROL=ij

IF "%JAVA_HOME%" == "" (
	SET /P JAVA_HOME="where is your Java Home? "
	SETX JAVA_HOME "%JAVA_HOME%" /M
)

IF "%DERBY_HOME%" == "" (
	SET /P DERBY_HOME="where is your Derby Home? "	
) 

SET DERBY_BIN=%DERBY_HOME%\bin
SET DERBY_DATABASES=%DERBY_HOME%\databases
SET DERBY_LIB=%DERBY_HOME%\lib

SETX DERBY_HOME "%DERBY_HOME%" /M	
SETX DERBY_BIN "%DERBY_BIN%" /M 
SETX DERBY_DATABASES "%DERBY_DATABASES%" /M 
SETX DERBY_LIB "%DERBY_LIB%" /M

ECHO DERBY_HOME=%DERBY_HOME%
ECHO DERBY_BIN=%DERBY_BIN%
ECHO DERBY_DATABASES=%DERBY_DATABASES%
ECHO DERBY_LIB=%DERBY_LIB%

CALL %~dp0derby-common.cmd

ECHO %IJ_ARGS%
SET CLASSPATH=%CLASSPATH%;%DERBY_LIB%\derbyclient.jar
PUSHD .
CALL %~dp0derby-bin.cmd

SET IJ_ARGS=-Dij.protocol=jdbc:derby://localhost:1527/Logbook
SET IJ_ARGS=%IJ_ARGS% -Dij.driver=org.apache.derby.jdbc.ClientDriver
SET IJ_ARGS=%IJ_ARGS% -Dderby.system.home=%DERBY_DATABASES%
SET IJ_ARGS=%IJ_ARGS% -Dij.user=admin
SET IJ_ARGS=%IJ_ARGS% -Dij.password=P$$w0rd


POPD

ECHO Use the following command to Connect to the Network Server
ECHO IJ_ARGS=%IJ_ARGS%
java %IJ_ARGS% org.apache.derby.tools.ij

@ECHO ON