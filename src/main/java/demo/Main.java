package demo;

import org.infinispan.configuration.parsing.ConfigurationBuilderHolder;
import org.infinispan.configuration.parsing.ParserRegistry;
import org.infinispan.manager.DefaultCacheManager;

import java.io.InputStream;

/**
 * Fails with:
 * <pre>
 * May 18, 2020 9:08:52 AM org.infinispan.factories.GlobalComponentRegistry preStart
 * INFO: ISPN000128: Infinispan version: Infinispan 'Infinity Minus ONE +2' 9.4.19.Final
 * May 18, 2020 9:08:52 AM org.infinispan.remoting.transport.jgroups.JGroupsTransport start
 * INFO: ISPN000078: Starting JGroups channel ISPN
 * Exception in thread "main" org.infinispan.manager.EmbeddedCacheManagerStartupException: org.infinispan.commons.CacheException: Unable to invoke method public void org.infinispan.remoting.transport.jgroups.JGroupsTransport.start() on object of type JGroupsTransport
 * at org.infinispan.manager.DefaultCacheManager.internalStart(DefaultCacheManager.java:715)
 * at org.infinispan.manager.DefaultCacheManager.start(DefaultCacheManager.java:680)
 * at demo.Main.main(Main.java:16)
 * Caused by: org.infinispan.commons.CacheException: Unable to invoke method public void org.infinispan.remoting.transport.jgroups.JGroupsTransport.start() on object of type JGroupsTransport
 * at org.infinispan.commons.util.ReflectionUtil.invokeMethod(ReflectionUtil.java:193)
 * at org.infinispan.factories.impl.BasicComponentRegistryImpl.startWrapper(BasicComponentRegistryImpl.java:520)
 * at org.infinispan.factories.impl.BasicComponentRegistryImpl$ComponentWrapper.running(BasicComponentRegistryImpl.java:711)
 * at org.infinispan.factories.impl.BasicComponentRegistryImpl.startDependencies(BasicComponentRegistryImpl.java:552)
 * at org.infinispan.factories.impl.BasicComponentRegistryImpl.startWrapper(BasicComponentRegistryImpl.java:505)
 * at org.infinispan.factories.impl.BasicComponentRegistryImpl$ComponentWrapper.running(BasicComponentRegistryImpl.java:711)
 * at org.infinispan.factories.impl.BasicComponentRegistryImpl.startDependencies(BasicComponentRegistryImpl.java:552)
 * at org.infinispan.factories.impl.BasicComponentRegistryImpl.startWrapper(BasicComponentRegistryImpl.java:505)
 * at org.infinispan.factories.impl.BasicComponentRegistryImpl$ComponentWrapper.running(BasicComponentRegistryImpl.java:711)
 * at org.infinispan.factories.AbstractComponentRegistry.internalStart(AbstractComponentRegistry.java:428)
 * at org.infinispan.factories.AbstractComponentRegistry.start(AbstractComponentRegistry.java:325)
 * at org.infinispan.manager.DefaultCacheManager.internalStart(DefaultCacheManager.java:712)
 * ... 2 more
 * Caused by: java.lang.NoSuchMethodError: 'void org.jgroups.JChannel.<init>(java.net.URL)'
 * at org.infinispan.remoting.transport.jgroups.JGroupsTransport.buildChannel(JGroupsTransport.java:586)
 * at org.infinispan.remoting.transport.jgroups.JGroupsTransport.initChannel(JGroupsTransport.java:422)
 * at org.infinispan.remoting.transport.jgroups.JGroupsTransport.start(JGroupsTransport.java:407)
 * at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
 * at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
 * at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
 * at java.base/java.lang.reflect.Method.invoke(Method.java:566)
 * at org.infinispan.commons.util.ReflectionUtil.invokeMethod(ReflectionUtil.java:188)
 * ... 13 more
 * </pre>
 */
public class Main {

    public static void main(String[] args) throws Exception {

        try (InputStream in = Main.class.getClassLoader().getResourceAsStream("infinispan.xml")) {
            ConfigurationBuilderHolder configBuilder = new ParserRegistry().parse(in);
            DefaultCacheManager defaultCacheManager = new DefaultCacheManager(configBuilder, false);
            defaultCacheManager.start();
            defaultCacheManager.stop();
        }
    }
}
