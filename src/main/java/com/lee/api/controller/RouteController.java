package com.lee.api.controller;

import com.lee.api.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("route")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @PostMapping("/update")
    public String update(@RequestBody RouteDefinition definition) {
        return routeService.update(definition);
    }

    @PostMapping("/add")
    public String add(@RequestBody RouteDefinition definition) {
        return routeService.add(definition);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable(required = true) String id) {
        return routeService.delete(id);
    }

    @RequestMapping("/refresh")
    public String refresh() {
        routeService.notifyChanged();
        return "success";
    }
}
