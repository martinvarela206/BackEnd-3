Launching GlassFish on Felix platform
OSGI framework packages:
org.glassfish.main.jul;uses:="org.glassfish.main.jul.cfg,org.glassfish.main.jul.handler";version="7.0.15",org.glassfish.main.jul.cfg;version="7.0.15",org.glassfish.main.jul.env;version="7.0.15",org.glassfish.main.jul.formatter;uses:="org.glassfish.main.jul.cfg,org.glassfish.main.jul.record";version="7.0.15",org.glassfish.main.jul.handler;uses:="org.glassfish.main.jul.cfg,org.glassfish.main.jul.formatter,org.glassfish.main.jul.record";version="7.0.15",org.glassfish.main.jul.record;version="7.0.15",org.glassfish.main.jul.rotation;version="7.0.15",org.glassfish.main.jul.tracing;version="7.0.15", null, org.osgi.dto;version="1.1.1",org.osgi.framework;version="1.10",org.osgi.framework.connect;uses:="org.osgi.framework,org.osgi.framework.launch";version="1.0",org.osgi.framework.dto;uses:="org.osgi.dto";version="1.8",org.osgi.framework.hooks.bundle;uses:="org.osgi.framework";version="1.1",org.osgi.framework.hooks.resolver;uses:="org.osgi.framework.wiring";version="1.0",org.osgi.framework.hooks.service;uses:="org.osgi.framework";version="1.1",org.osgi.framework.hooks.weaving;uses:="org.osgi.framework.wiring";version="1.1",org.osgi.framework.launch;uses:="org.osgi.framework";version="1.2",org.osgi.framework.namespace;uses:="org.osgi.resource";version="1.2",org.osgi.framework.startlevel;uses:="org.osgi.framework";version="1.0",org.osgi.framework.startlevel.dto;uses:="org.osgi.dto";version="1.0",org.osgi.framework.wiring;uses:="org.osgi.framework,org.osgi.resource";version="1.2",org.osgi.framework.wiring.dto;uses:="org.osgi.dto,org.osgi.resource.dto";version="1.3",org.osgi.resource;version="1.0.1",org.osgi.resource.dto;uses:="org.osgi.dto";version="1.0.1",org.osgi.service.condition;version="1.0",org.osgi.service.packageadmin;uses:="org.osgi.framework";version="1.2.1",org.osgi.service.resolver;uses:="org.osgi.resource";version="1.1.1",org.osgi.service.startlevel;uses:="org.osgi.framework";version="1.1.1",org.osgi.service.url;version="1.0.1",org.osgi.util.tracker;uses:="org.osgi.framework";version="1.5.3", org.glassfish.embeddable;version="7.0.15",org.glassfish.embeddable.spi;uses:="org.glassfish.embeddable";version="7.0.15", org.glassfish.main.jul;uses:="org.glassfish.main.jul.cfg,org.glassfish.main.jul.handler";version="7.0.15",org.glassfish.main.jul.cfg;version="7.0.15",org.glassfish.main.jul.env;version="7.0.15",org.glassfish.main.jul.formatter;uses:="org.glassfish.main.jul.cfg,org.glassfish.main.jul.record";version="7.0.15",org.glassfish.main.jul.handler;uses:="org.glassfish.main.jul.cfg,org.glassfish.main.jul.formatter,org.glassfish.main.jul.record";version="7.0.15",org.glassfish.main.jul.record;version="7.0.15",org.glassfish.main.jul.rotation;version="7.0.15",org.glassfish.main.jul.tracing;version="7.0.15"
JDK provided packages:
com.sun.jarsigner, com.sun.java.accessibility.util, com.sun.javadoc, com.sun.jdi, com.sun.jdi.connect, com.sun.jdi.connect.spi, com.sun.jdi.event, com.sun.jdi.request, com.sun.jndi.ldap.spi, com.sun.management, com.sun.net.httpserver, com.sun.net.httpserver.spi, com.sun.nio.file, com.sun.nio.sctp, com.sun.security.auth, com.sun.security.auth.callback, com.sun.security.auth.login, com.sun.security.auth.module, com.sun.security.jgss, com.sun.source.doctree, com.sun.source.tree, com.sun.source.util, com.sun.tools.attach, com.sun.tools.attach.spi, com.sun.tools.javac, com.sun.tools.javadoc, com.sun.tools.jconsole, java.applet, java.awt, java.awt.color, java.awt.datatransfer, java.awt.desktop, java.awt.dnd, java.awt.event, java.awt.font, java.awt.geom, java.awt.im, java.awt.im.spi, java.awt.image, java.awt.image.renderable, java.awt.print, java.beans, java.beans.beancontext, java.io, java.lang, java.lang.annotation, java.lang.instrument, java.lang.invoke, java.lang.management, java.lang.module, java.lang.ref, java.lang.reflect, java.math, java.net, java.net.http, java.net.spi, java.nio, java.nio.channels, java.nio.channels.spi, java.nio.charset, java.nio.charset.spi, java.nio.file, java.nio.file.attribute, java.nio.file.spi, java.rmi, java.rmi.activation, java.rmi.dgc, java.rmi.registry, java.rmi.server, java.security, java.security.acl, java.security.cert, java.security.interfaces, java.security.spec, java.sql, java.text, java.text.spi, java.time, java.time.chrono, java.time.format, java.time.temporal, java.time.zone, java.util, java.util.concurrent, java.util.concurrent.atomic, java.util.concurrent.locks, java.util.function, java.util.jar, java.util.logging, java.util.prefs, java.util.regex, java.util.spi, java.util.stream, java.util.zip, javax.accessibility, javax.annotation.processing, javax.crypto, javax.crypto.interfaces, javax.crypto.spec, javax.imageio, javax.imageio.event, javax.imageio.metadata, javax.imageio.plugins.bmp, javax.imageio.plugins.jpeg, javax.imageio.plugins.tiff, javax.imageio.spi, javax.imageio.stream, javax.lang.model, javax.lang.model.element, javax.lang.model.type, javax.lang.model.util, javax.management, javax.management.loading, javax.management.modelmbean, javax.management.monitor, javax.management.openmbean, javax.management.relation, javax.management.remote, javax.management.remote.rmi, javax.management.timer, javax.naming, javax.naming.directory, javax.naming.event, javax.naming.ldap, javax.naming.spi, javax.net, javax.net.ssl, javax.print, javax.print.attribute, javax.print.attribute.standard, javax.print.event, javax.rmi.ssl, javax.script, javax.security.auth, javax.security.auth.callback, javax.security.auth.kerberos, javax.security.auth.login, javax.security.auth.spi, javax.security.auth.x500, javax.security.cert, javax.security.sasl, javax.smartcardio, javax.sound.midi, javax.sound.midi.spi, javax.sound.sampled, javax.sound.sampled.spi, javax.sql, javax.sql.rowset, javax.sql.rowset.serial, javax.sql.rowset.spi, javax.swing, javax.swing.border, javax.swing.colorchooser, javax.swing.event, javax.swing.filechooser, javax.swing.plaf, javax.swing.plaf.basic, javax.swing.plaf.metal, javax.swing.plaf.multi, javax.swing.plaf.nimbus, javax.swing.plaf.synth, javax.swing.table, javax.swing.text, javax.swing.text.html, javax.swing.text.html.parser, javax.swing.text.rtf, javax.swing.tree, javax.swing.undo, javax.tools, javax.transaction.xa, javax.xml, javax.xml.catalog, javax.xml.crypto, javax.xml.crypto.dom, javax.xml.crypto.dsig, javax.xml.crypto.dsig.dom, javax.xml.crypto.dsig.keyinfo, javax.xml.crypto.dsig.spec, javax.xml.datatype, javax.xml.namespace, javax.xml.parsers, javax.xml.stream, javax.xml.stream.events, javax.xml.stream.util, javax.xml.transform, javax.xml.transform.dom, javax.xml.transform.sax, javax.xml.transform.stax, javax.xml.transform.stream, javax.xml.validation, javax.xml.xpath, jdk.dynalink, jdk.dynalink.beans, jdk.dynalink.linker, jdk.dynalink.linker.support, jdk.dynalink.support, jdk.javadoc.doclet, jdk.jfr, jdk.jfr.consumer, jdk.jshell, jdk.jshell.execution, jdk.jshell.spi, jdk.jshell.tool, jdk.management.jfr, jdk.nashorn.api.scripting, jdk.nashorn.api.tree, jdk.net, jdk.nio, jdk.security.jarsigner, jdk.swing.interop, netscape.javascript, org.ietf.jgss, org.w3c.dom, org.w3c.dom.bootstrap, org.w3c.dom.css, org.w3c.dom.events, org.w3c.dom.html, org.w3c.dom.ls, org.w3c.dom.ranges, org.w3c.dom.stylesheets, org.w3c.dom.traversal, org.w3c.dom.views, org.w3c.dom.xpath, org.xml.sax, org.xml.sax.ext, org.xml.sax.helpers, sun.misc, sun.reflect
  Using property file: /home/martin/glassfish/glassfish/domains/domain1/config/logging.properties|#]
  Running GlassFish Version: Eclipse GlassFish 7.0.15 (commit: 6f2f4c26b0f825d36b45ef39f6f83ac6b56d438c)|#]
  Grizzly Framework 4.0.2 started in: 137 ms - bound to [/0.0.0.0:8080]|#]
  Grizzly Framework 4.0.2 started in: 31 ms - bound to [/0.0.0.0:8181]|#]
  Grizzly Framework 4.0.2 started in: 34 ms - bound to [/0.0.0.0:4848]|#]
  Authorization Service has successfully initialized.|#]
  Realm [admin-realm] of classtype [com.sun.enterprise.security.auth.realm.file.FileRealm] successfully created.|#]
  Realm [file] of classtype [com.sun.enterprise.security.auth.realm.file.FileRealm] successfully created.|#]
  Realm [certificate] of classtype [com.sun.enterprise.security.auth.realm.certificate.CertificateRealm] successfully created.|#]
  Grizzly Framework 4.0.2 started in: 87 ms - bound to [/0.0.0.0:8080]|#]
  Grizzly Framework 4.0.2 started in: 26 ms - bound to [/0.0.0.0:8181]|#]
  Java security manager is disabled.|#]
  Entering Security Startup Service.|#]
  Loading policy provider org.glassfish.exousia.modules.locked.SimplePolicyProvider.|#]
  Security Service(s) started successfully.|#]
  Created HTTP listener http-listener-1 on host/port 0.0.0.0:8080|#]
  Created HTTP listener http-listener-2 on host/port 0.0.0.0:8181|#]
  Created HTTP listener admin-listener on host/port 0.0.0.0:4848|#]
  Created virtual server server|#]
  Created virtual server __asadmin|#]
  Setting JAAS app name glassfish-web|#]
  Virtual server server loaded default web module |#]
  Exception while deploying the app [Alumnos]|#]
  Exception during lifecycle processing
org.glassfish.deployment.common.DeploymentException: JNDI lookup failed for the resource: Name: AlumnosPU, Lookup: java:app/DSAlumno, Type: javax.sql.DataSource -- Lookup failed for java:app/DSAlumno in SerialContext[myEnv={java.naming.factory.state=com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl, java.naming.factory.url.pkgs=com.sun.enterprise.naming}]
	at com.sun.enterprise.naming.impl.SerialContext.lookup(SerialContext.java:836)
	at com.sun.enterprise.naming.impl.SerialContext.lookup(SerialContext.java:257)
	at java.naming/javax.naming.InitialContext.lookup(InitialContext.java:409)
	at java.naming/javax.naming.InitialContext.lookup(InitialContext.java:409)
	at java.naming/javax.naming.InitialContext.doLookup(InitialContext.java:282)
	at com.sun.enterprise.deployment.util.ResourceValidator.validateJNDIRefs(ResourceValidator.java:882)
	at com.sun.enterprise.deployment.util.ResourceValidator.validateResources(ResourceValidator.java:748)
	at com.sun.enterprise.deployment.util.ResourceValidator.event(ResourceValidator.java:173)
	at org.glassfish.kernel.event.EventsImpl.send(EventsImpl.java:115)
	at com.sun.enterprise.v3.server.ApplicationLifecycle.deploy(ApplicationLifecycle.java:456)
	at com.sun.enterprise.v3.server.ApplicationLoaderService.processApplication(ApplicationLoaderService.java:405)
	at com.sun.enterprise.v3.server.ApplicationLoaderService.postConstruct(ApplicationLoaderService.java:243)
	at org.jvnet.hk2.internal.ClazzCreator.postConstructMe(ClazzCreator.java:356)
	at org.jvnet.hk2.internal.ClazzCreator.create(ClazzCreator.java:410)
	at org.jvnet.hk2.internal.SystemDescriptor.create(SystemDescriptor.java:479)
	at org.glassfish.hk2.runlevel.internal.AsyncRunLevelContext.findOrCreate(AsyncRunLevelContext.java:288)
	at org.glassfish.hk2.runlevel.RunLevelContext.findOrCreate(RunLevelContext.java:65)
	at org.jvnet.hk2.internal.Utilities.createService(Utilities.java:2111)
	at org.jvnet.hk2.internal.ServiceHandleImpl.getService(ServiceHandleImpl.java:98)
	at org.jvnet.hk2.internal.ServiceHandleImpl.getService(ServiceHandleImpl.java:68)
	at org.glassfish.hk2.runlevel.internal.CurrentTaskFuture$QueueRunner.oneJob(CurrentTaskFuture.java:1366)
	at org.glassfish.hk2.runlevel.internal.CurrentTaskFuture$QueueRunner.run(CurrentTaskFuture.java:1294)
	at org.glassfish.hk2.runlevel.internal.CurrentTaskFuture$UpOneLevel.run(CurrentTaskFuture.java:848)
	at org.glassfish.hk2.runlevel.internal.CurrentTaskFuture$UpAllTheWay.go(CurrentTaskFuture.java:623)
	at org.glassfish.hk2.runlevel.internal.CurrentTaskFuture.go(CurrentTaskFuture.java:140)
	at org.glassfish.hk2.runlevel.internal.CurrentTaskFuture.go(CurrentTaskFuture.java:135)
	at org.glassfish.hk2.runlevel.internal.AsyncRunLevelContext.proceedTo(AsyncRunLevelContext.java:571)
	at org.glassfish.hk2.runlevel.internal.RunLevelControllerImpl.proceedTo(RunLevelControllerImpl.java:48)
	at com.sun.enterprise.v3.server.AppServerStartup.proceedTo(AppServerStartup.java:498)
	at com.sun.enterprise.v3.server.AppServerStartup.run(AppServerStartup.java:302)
	at com.sun.enterprise.v3.server.AppServerStartup.doStart(AppServerStartup.java:209)
	at com.sun.enterprise.v3.server.AppServerStartup.start(AppServerStartup.java:200)
	at com.sun.enterprise.glassfish.bootstrap.GlassFishImpl.start(GlassFishImpl.java:55)
	at com.sun.enterprise.glassfish.bootstrap.GlassFishDecorator.start(GlassFishDecorator.java:38)
	at com.sun.enterprise.glassfish.bootstrap.osgi.EmbeddedOSGiGlassFishImpl.start(EmbeddedOSGiGlassFishImpl.java:50)
	at com.sun.enterprise.glassfish.bootstrap.GlassFishDecorator.start(GlassFishDecorator.java:38)
	at com.sun.enterprise.glassfish.bootstrap.osgi.OSGiGlassFishImpl.start(OSGiGlassFishImpl.java:46)
	at com.sun.enterprise.glassfish.bootstrap.GlassFishMain$Launcher.launch(GlassFishMain.java:144)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at com.sun.enterprise.glassfish.bootstrap.GlassFishMain.main(GlassFishMain.java:93)
	at com.sun.enterprise.glassfish.bootstrap.ASMain.main(ASMain.java:31)
|#]
  close(), this:
org.glassfish.web.loader.WebappClassLoader@1129829c[name=unknown], urls=[
file:/home/martin/NetBeansProjects/Alumnos/build/web/WEB-INF/classes/
file:/home/martin/glassfish/glassfish/domains/domain1/generated/ejb/Alumnos//
][delegate=true, context=unknown, status=RUNNING, antiJARLocking=false, securityManager=false, packageDefinitionSecurityEnabled=false, repositories=RepositoryManager[WEB-INF/classes/], notFound.size=45, pathTimestamps.size=1, resourceEntryCache.size=1]|#]
  close(), this:
org.glassfish.web.loader.WebappClassLoader@3c4ad54[name=unknown], urls=[
file:/home/martin/NetBeansProjects/Alumnos/build/web/WEB-INF/classes/
file:/home/martin/glassfish/glassfish/domains/domain1/generated/ejb/Alumnos//
][delegate=true, context=unknown, status=RUNNING, antiJARLocking=false, securityManager=false, packageDefinitionSecurityEnabled=false, repositories=RepositoryManager[WEB-INF/classes/], notFound.size=0, pathTimestamps.size=0, resourceEntryCache.size=0]|#]
  Application deployment failed: Exception while deploying the app [Alumnos]|#]
  Exception while deploying the app [AlumnosMaven]|#]
  Exception during lifecycle processing
org.glassfish.deployment.common.DeploymentException: JNDI lookup failed for the resource: Name: AlumnosPU, Lookup: java:app/DBAlumno, Type: javax.sql.DataSource -- Lookup failed for java:app/DBAlumno in SerialContext[myEnv={java.naming.factory.state=com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl, java.naming.factory.url.pkgs=com.sun.enterprise.naming}]
	at com.sun.enterprise.naming.impl.SerialContext.lookup(SerialContext.java:836)
	at com.sun.enterprise.naming.impl.SerialContext.lookup(SerialContext.java:257)
	at java.naming/javax.naming.InitialContext.lookup(InitialContext.java:409)
	at java.naming/javax.naming.InitialContext.lookup(InitialContext.java:409)
	at java.naming/javax.naming.InitialContext.doLookup(InitialContext.java:282)
	at com.sun.enterprise.deployment.util.ResourceValidator.validateJNDIRefs(ResourceValidator.java:882)
	at com.sun.enterprise.deployment.util.ResourceValidator.validateResources(ResourceValidator.java:748)
	at com.sun.enterprise.deployment.util.ResourceValidator.event(ResourceValidator.java:173)
	at org.glassfish.kernel.event.EventsImpl.send(EventsImpl.java:115)
	at com.sun.enterprise.v3.server.ApplicationLifecycle.deploy(ApplicationLifecycle.java:456)
	at com.sun.enterprise.v3.server.ApplicationLoaderService.processApplication(ApplicationLoaderService.java:405)
	at com.sun.enterprise.v3.server.ApplicationLoaderService.postConstruct(ApplicationLoaderService.java:243)
	at org.jvnet.hk2.internal.ClazzCreator.postConstructMe(ClazzCreator.java:356)
	at org.jvnet.hk2.internal.ClazzCreator.create(ClazzCreator.java:410)
	at org.jvnet.hk2.internal.SystemDescriptor.create(SystemDescriptor.java:479)
	at org.glassfish.hk2.runlevel.internal.AsyncRunLevelContext.findOrCreate(AsyncRunLevelContext.java:288)
	at org.glassfish.hk2.runlevel.RunLevelContext.findOrCreate(RunLevelContext.java:65)
	at org.jvnet.hk2.internal.Utilities.createService(Utilities.java:2111)
	at org.jvnet.hk2.internal.ServiceHandleImpl.getService(ServiceHandleImpl.java:98)
	at org.jvnet.hk2.internal.ServiceHandleImpl.getService(ServiceHandleImpl.java:68)
	at org.glassfish.hk2.runlevel.internal.CurrentTaskFuture$QueueRunner.oneJob(CurrentTaskFuture.java:1366)
	at org.glassfish.hk2.runlevel.internal.CurrentTaskFuture$QueueRunner.run(CurrentTaskFuture.java:1294)
	at org.glassfish.hk2.runlevel.internal.CurrentTaskFuture$UpOneLevel.run(CurrentTaskFuture.java:848)
	at org.glassfish.hk2.runlevel.internal.CurrentTaskFuture$UpAllTheWay.go(CurrentTaskFuture.java:623)
	at org.glassfish.hk2.runlevel.internal.CurrentTaskFuture.go(CurrentTaskFuture.java:140)
	at org.glassfish.hk2.runlevel.internal.CurrentTaskFuture.go(CurrentTaskFuture.java:135)
	at org.glassfish.hk2.runlevel.internal.AsyncRunLevelContext.proceedTo(AsyncRunLevelContext.java:571)
	at org.glassfish.hk2.runlevel.internal.RunLevelControllerImpl.proceedTo(RunLevelControllerImpl.java:48)
	at com.sun.enterprise.v3.server.AppServerStartup.proceedTo(AppServerStartup.java:498)
	at com.sun.enterprise.v3.server.AppServerStartup.run(AppServerStartup.java:302)
	at com.sun.enterprise.v3.server.AppServerStartup.doStart(AppServerStartup.java:209)
	at com.sun.enterprise.v3.server.AppServerStartup.start(AppServerStartup.java:200)
	at com.sun.enterprise.glassfish.bootstrap.GlassFishImpl.start(GlassFishImpl.java:55)
	at com.sun.enterprise.glassfish.bootstrap.GlassFishDecorator.start(GlassFishDecorator.java:38)
	at com.sun.enterprise.glassfish.bootstrap.osgi.EmbeddedOSGiGlassFishImpl.start(EmbeddedOSGiGlassFishImpl.java:50)
	at com.sun.enterprise.glassfish.bootstrap.GlassFishDecorator.start(GlassFishDecorator.java:38)
	at com.sun.enterprise.glassfish.bootstrap.osgi.OSGiGlassFishImpl.start(OSGiGlassFishImpl.java:46)
	at com.sun.enterprise.glassfish.bootstrap.GlassFishMain$Launcher.launch(GlassFishMain.java:144)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at com.sun.enterprise.glassfish.bootstrap.GlassFishMain.main(GlassFishMain.java:93)
	at com.sun.enterprise.glassfish.bootstrap.ASMain.main(ASMain.java:31)
|#]
  close(), this:
org.glassfish.web.loader.WebappClassLoader@3bc6c10f[name=unknown], urls=[
file:/home/martin/NetBeansProjects/AlumnosMaven/target/AlumnosMaven-1.0-SNAPSHOT/WEB-INF/classes/
file:/home/martin/glassfish/glassfish/domains/domain1/generated/ejb/AlumnosMaven//
][delegate=true, context=unknown, status=RUNNING, antiJARLocking=false, securityManager=false, packageDefinitionSecurityEnabled=false, repositories=RepositoryManager[WEB-INF/classes/], notFound.size=2, pathTimestamps.size=1, resourceEntryCache.size=1]|#]
  close(), this:
org.glassfish.web.loader.WebappClassLoader@2a504ea7[name=unknown], urls=[
file:/home/martin/NetBeansProjects/AlumnosMaven/target/AlumnosMaven-1.0-SNAPSHOT/WEB-INF/classes/
file:/home/martin/glassfish/glassfish/domains/domain1/generated/ejb/AlumnosMaven//
][delegate=true, context=unknown, status=RUNNING, antiJARLocking=false, securityManager=false, packageDefinitionSecurityEnabled=false, repositories=RepositoryManager[WEB-INF/classes/], notFound.size=0, pathTimestamps.size=0, resourceEntryCache.size=0]|#]
  Application deployment failed: Exception while deploying the app [AlumnosMaven]|#]
  Registered org.glassfish.ha.store.adapter.cache.ShoalBackingStoreProxy for persistence-type = replicated in BackingStoreFactoryRegistry|#]
  Grizzly Framework 4.0.2 started in: 15 ms - bound to [/0.0.0.0:3700]|#]
  Eclipse GlassFish 7.0.15 (commit: 6f2f4c26b0f825d36b45ef39f6f83ac6b56d438c) startup time: Embedded (11,916 ms), startup services (31,222 ms), total (43,138 ms)|#]
  Grizzly Framework 4.0.2 started in: 76 ms - bound to [/0.0.0.0:7676]|#]
  JMXStartupService has started JMXConnector on JMXService URL service:jmx:rmi://martin-dv7:8686/jndi/rmi://martin-dv7:8686/jmxrmi|#]
  Created HTTP listener http-listener-2 on host/port 0.0.0.0:8181|#]
  Grizzly Framework 4.0.2 started in: 23 ms - bound to [/0.0.0.0:8181]|#]
  Created HTTP listener http-listener-1 on host/port 0.0.0.0:8080|#]
  Grizzly Framework 4.0.2 started in: 102 ms - bound to [/0.0.0.0:8080]|#]
  close(), this:
org.glassfish.web.loader.WebappClassLoader@25e1c091[name=unknown], urls=[
file:/home/martin/glassfish/glassfish/lib/install/applications/__admingui/WEB-INF/classes/
file:/home/martin/glassfish/glassfish/lib/install/applications/__admingui/WEB-INF/lib/console-core-7.0.15.jar
file:/home/martin/glassfish/glassfish/domains/domain1/generated/ejb/__admingui//
file:/home/martin/glassfish/glassfish/lib/install/applications/__admingui/WEB-INF/extra/woodstock-webui-jsf-suntheme-6.0.1.jar
file:/home/martin/glassfish/glassfish/lib/install/applications/__admingui/WEB-INF/extra/dojo-ajax-nodemo-1.12.4.jar
file:/home/martin/glassfish/glassfish/lib/install/applications/__admingui/WEB-INF/extra/woodstock-webui-jsf-6.0.1.jar
file:/home/martin/glassfish/glassfish/lib/install/applications/__admingui/WEB-INF/extra/json-2.0.jar
file:/home/martin/glassfish/glassfish/lib/install/applications/__admingui/WEB-INF/extra/prototype-1.7.3.jar
file:/home/martin/glassfish/glassfish/lib/install/applications/__admingui/WEB-INF/extra/commons-io-2.16.1.jar
][delegate=true, context=unknown, status=RUNNING, antiJARLocking=false, securityManager=false, packageDefinitionSecurityEnabled=false, repositories=RepositoryManager[WEB-INF/classes/], notFound.size=0, pathTimestamps.size=1, resourceEntryCache.size=0]|#]
  WELD-000900: 5.1.2 (Final)|#]
  Removed extension bundle://34ff7605-57fe-4b86-b9d9-82c9403b2f23_238.0:0/META-INF/services/jakarta.enterprise.inject.spi.Extension incompatible with the current JDK 11.0.28+12-LTS-279.|#]
  WELD-000411: Observer method [BackedAnnotatedMethod] public org.glassfish.jersey.ext.cdi1x.internal.ProcessAllAnnotatedTypes.processAnnotatedType(@Observes ProcessAnnotatedType<?>, BeanManager) receives events for all annotated types. Consider restricting events using @WithAnnotations or a generic type with bounds.|#]
  Created connection pool and added it to PoolManager: Pool [org.glassfish.resourcebase.resources.api.PoolInfo@6f7b1e02[jndiName=java:app/derby_net_DBAlumno_appPool, applicationName=AlumnosMaven, moduleName=null]] PoolSize=0  FreeResources=0  QueueSize=0 matching=off validation=off|#]
  Listening to REST requests at context: /management/domain.|#]
  close(), this:
org.glassfish.web.loader.WebappClassLoader@4c1b5bcf[name=unknown], urls=[
file:/home/martin/NetBeansProjects/AlumnosMaven/target/AlumnosMaven-1.0-SNAPSHOT/WEB-INF/classes/
file:/home/martin/glassfish/glassfish/domains/domain1/generated/ejb/AlumnosMaven/
][delegate=true, context=unknown, status=RUNNING, antiJARLocking=false, securityManager=false, packageDefinitionSecurityEnabled=false, repositories=RepositoryManager[WEB-INF/classes/], notFound.size=2, pathTimestamps.size=1, resourceEntryCache.size=1]|#]
  EclipseLink, version: Eclipse Persistence Services - 4.0.2.v202306161219|#]
  Initializing Soteria 3.0.3 for context ''|#]
  Inicializando Mojarra 4.0.7 para el contexto ''|#]
  Removed extension bundle://34ff7605-57fe-4b86-b9d9-82c9403b2f23_238.0:0/META-INF/services/jakarta.enterprise.inject.spi.Extension incompatible with the current JDK 11.0.28+12-LTS-279.|#]
  WELD-000411: Observer method [BackedAnnotatedMethod] public org.glassfish.jersey.ext.cdi1x.internal.ProcessAllAnnotatedTypes.processAnnotatedType(@Observes ProcessAnnotatedType<?>, BeanManager) receives events for all annotated types. Consider restricting events using @WithAnnotations or a generic type with bounds.|#]
  Eclipse Krazo version 3.0.1 started|#]
  Initializing Soteria 3.0.3 for context '/AlumnosMaven'|#]
  Loading application [__admingui] at [/]|#]
  Loading application __admingui done in 45,913 ms|#]
  Context path from ServletContext:  differs from path from bundle: /|#]
  Loading application [AlumnosMaven] at [/AlumnosMaven]|#]
  AlumnosMaven was successfully deployed in 40,855 milliseconds.|#]
