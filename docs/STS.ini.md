# Eclipse eclipse.ini file configuration 
These settings are a guide to get eclipse to start fast, compile and run servers fast.

## Garbage Collection Options (CPU intensive)***

http://www.fasterj.com/articles/oraclecollectors1.shtml

	-startup
	plugins/org.eclipse.equinox.launcher_1.3.0.v20130327-1440.jar
	--launcher.library
	plugins/org.eclipse.equinox.launcher.win32.win32.x86_64_1.1.200.v20130521-0416
	-product
	org.springsource.sts.ide
	--launcher.defaultAction
	openFile
	-vm
	C:/Program Files/Java/jdk1.7.0_25/bin/javaw.exe
	--launcher.XXMaxPermSize
	128m
	-vmargs
	-server
	-Dosgi.requiredJavaVersion=1.7
	-Declipse.p2.unsignedPolicy=allow
	-Xverify:none
	-Xss16m
	-Xms128m
	-Xmx384m
	-XX:PermSize=128m
	-XX:MaxPermSize=256m
	-XX:MaxHeapFreeRatio=70
	-XX:ReservedCodeCacheSize=128m
	-XX:+UnlockExperimentalVMOptions
	-XX:+DoEscapeAnalysis
	-XX:+UseG1GC
	-XX:+UseFastAccessorMethods
	-XX:+AggressiveOpts
	-XX:+CMSClassUnloadingEnabled
	-Dcom.sun.management.jmxremote
	-Dorg.eclipse.equinox.p2.reconciler.dropins.directory=d:/development/springsource/    sts-3.3.0.RELEASE/dropins
