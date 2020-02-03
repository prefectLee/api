package com.lee.api.service;

import com.lee.api.router.RedisRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class RouteService implements ApplicationEventPublisherAware {
    @Autowired
    private RedisRouteRepository routeDefinitionWriter;

    private ApplicationEventPublisher publisher;


    public void notifyChanged() {
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }

    public String add(RouteDefinition definition) {
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        notifyChanged();
        return "success";
    }

    public String update(RouteDefinition definition) {
        this.routeDefinitionWriter.delete(Mono.just(definition.getId()));
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        notifyChanged();
        return "success";
    }

    public String delete(String id) {
        this.routeDefinitionWriter.delete(Mono.just(id)).subscribe();
        notifyChanged();
        return "delete success";
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
