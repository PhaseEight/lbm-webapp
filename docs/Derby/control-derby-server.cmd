@ECHO off

SET CONTROL=%1

IF "%CONTROL%" == "" (
	ECHO "Command not supplied"
	SET /P CONTROL_COMMAND="Do you want to "S"top or "R"un Derby?"
	IF "%CONTROL_COMMAND%" == "R" (
		SET CONTROL=start
	) ELSE (
		SET CONTROL=stop
	)
)

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

-h localhost -p 501527 -noSecurityManager -ssl https

SETX DERBY_HOME "%DERBY_HOME%" /M	
SETX DERBY_BIN "%DERBY_BIN%" /M 
SETX DERBY_DATABASES "%DERBY_DATABASES%" /M 
SETX DERBY_LIB "%DERBY_LIB%" /M

ECHO DERBY_HOME=%DERBY_HOME%
ECHO DERBY_BIN=%DERBY_BIN%
ECHO DERBY_DATABASES=%DERBY_DATABASES%
ECHO DERBY_LIB=%DERBY_LIB%

SET CLASSPATH=.;%DERBY_HOME%\lib\derby.jar;%DERBY_HOME%\lib\derbytools.jar;.
CALL %~dp0derby-common.cmd

REM Run Derby as a server using Default Configurations
ECHO ################################################################################################ 
ECHO java %IJ_ARGS% -jar "%DERBY_HOME%\lib\derbyrun.jar" server start
ECHO ################################################################################################

ECHO %IJ_ARGS%
SET COMMAND=java %IJ_ARGS% -jar "%DERBY_LIB%\derbyrun.jar" server %CONTROL%
ECHO Calling: %COMMAND%
CALL %COMMAND%

@ECHO ON