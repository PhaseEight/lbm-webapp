@ECHO off

REM Show Device; Path; Name; Extension of %0
ECHO Running as: %~dpnx0
ECHO Checking for DERBY_BIN: "%DERBY_BIN%"
IF EXIST "%DERBY_BIN%" (
	ECHO Found DERBY_BIN: "%DERBY_BIN%"
	CD "%DERBY_BIN%"
) ELSE (
	ECHO Location of DERBY_BIN does not exist: "%DERBY_BIN%" 
)

:END

@ECHO on