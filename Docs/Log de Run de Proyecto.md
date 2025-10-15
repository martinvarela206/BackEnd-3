cd /home/martin/NetBeansProjects/AlumnosMaven; JAVA_HOME=/usr/lib/jvm/jdk-25-oracle-x64 /usr/lib/apache-netbeans/java/maven/bin/mvn -Dnetbeans.deploy=true -Dexec.vmArgs= -Dexec.appArgs= --no-transfer-progress package
WARNING: A restricted method in java.lang.System has been called
WARNING: java.lang.System::load has been called by org.fusesource.jansi.internal.JansiLoader in an unnamed module (file:/usr/lib/apache-netbeans/java/maven/lib/jansi-2.4.1.jar)
WARNING: Use --enable-native-access=ALL-UNNAMED to avoid a warning for callers in this module
WARNING: Restricted methods will be blocked in a future release unless native access is enabled

WARNING: A terminally deprecated method in sun.misc.Unsafe has been called
WARNING: sun.misc.Unsafe::objectFieldOffset has been called by com.google.common.util.concurrent.AbstractFuture$UnsafeAtomicHelper (file:/usr/lib/apache-netbeans/java/maven/lib/guava-33.2.1-jre.jar)
WARNING: Please consider reporting this to the maintainers of class com.google.common.util.concurrent.AbstractFuture$UnsafeAtomicHelper
WARNING: sun.misc.Unsafe::objectFieldOffset will be removed in a future release
Scanning for projects...

---------------------< com.mycompany:AlumnosMaven >---------------------
Building AlumnosMaven 1.0-SNAPSHOT
  from pom.xml
--------------------------------[ war ]---------------------------------

--- resources:3.3.1:resources (default-resources) @ AlumnosMaven ---
Copying 2 resources from src/main/resources to target/classes

--- compiler:3.8.1:compile (default-compile) @ AlumnosMaven ---
Nothing to compile - all classes are up to date

--- resources:3.3.1:testResources (default-testResources) @ AlumnosMaven ---
skip non existing resourceDirectory /home/martin/NetBeansProjects/AlumnosMaven/src/test/resources

--- compiler:3.8.1:testCompile (default-testCompile) @ AlumnosMaven ---
No sources to compile

--- surefire:3.2.5:test (default-test) @ AlumnosMaven ---
No tests to run.

--- war:3.3.2:war (default-war) @ AlumnosMaven ---
Packaging webapp
Assembling webapp [AlumnosMaven] in [/home/martin/NetBeansProjects/AlumnosMaven/target/AlumnosMaven-1.0-SNAPSHOT]
Processing war project
Copying webapp resources [/home/martin/NetBeansProjects/AlumnosMaven/src/main/webapp]
Building war: /home/martin/NetBeansProjects/AlumnosMaven/target/AlumnosMaven-1.0-SNAPSHOT.war
------------------------------------------------------------------------
BUILD SUCCESS
------------------------------------------------------------------------
Total time:  5.907 s
Finished at: 2025-10-15T15:54:50-03:00
------------------------------------------------------------------------
Deploying on GlassFish Server
    profile mode: false
    debug mode: false
    force redeploy: true
Starting GlassFish Server

Opcion 1:

En NetBeans: editar la configuración de NetBeans para Maven (NetBeans > Tools > Options > Java > Maven o editar el archivo netbeans.conf) y pasar esa opción al JVM de Maven que usa NetBeans.