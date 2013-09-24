REM Ensure that the Network Server CLASSPATH is up-to-date
call %DERBY_BIN%\setNetworkServerCP.bat

SET IJ_ARGS=-Dij.protocol=jdbc:derby:
SET IJ_ARGS=%IJ_ARGS% -Dderby.system.home=%DERBY_DATABASES%

