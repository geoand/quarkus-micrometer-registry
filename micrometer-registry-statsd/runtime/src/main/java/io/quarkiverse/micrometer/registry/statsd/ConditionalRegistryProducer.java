package io.quarkiverse.micrometer.registry.statsd;

import jakarta.enterprise.inject.Alternative;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import jakarta.interceptor.Interceptor;

import io.micrometer.core.instrument.Clock;
import io.micrometer.statsd.StatsdConfig;
import io.micrometer.statsd.StatsdMeterRegistry;
import io.quarkus.arc.Priority;

@Singleton
public class ConditionalRegistryProducer {
    /**
     * This producer is added as a bean by the Processor IFF the default registry
     * instance has been enabled.
     */
    @Produces
    @Singleton
    @Alternative
    @Priority(Interceptor.Priority.APPLICATION + 100)
    public StatsdMeterRegistry registry(StatsdConfig config, Clock clock) {
        return new StatsdMeterRegistry(config, clock);
    }
}
