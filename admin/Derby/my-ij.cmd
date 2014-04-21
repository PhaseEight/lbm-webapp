SET DERBY_HOME=d:\development\apache\db-derby-10.8.3.0
SET DERBY_BIN=d:\development\apache\db-derby-10.8.3.0\bin
SET DERBY_DATABASES=d:\development\apache\db-derby-10.8.3.0\databases
SET DERBY_LIB=d:\development\apache\db-derby-10.8.3.0\lib

SET IJ_ARGS=-Dij.protocol=jdbc:derby: -Dij.driver=org.apache.derby.jdbc.ClientDriver -Dderby.system.home=d:\development\apache\db-derby-10.8.3.0\databases -Dij.user=admin -Dij.password=P$$w0rd

java %IJ_ARGS% org.apache.derby.tools.ij
