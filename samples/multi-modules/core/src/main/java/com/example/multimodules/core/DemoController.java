package com.example.multimodules.core;

import org.hibernate.validator.ValidatorHints;
import org.springframework.boot.actuate.autoconfigure.endpoint.EndpointAutoConfigurationHints;
import org.springframework.cloud.config.server.ConfigServerHints;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.nativex.hint.NativeHint;
import org.springframework.nativex.hint.TypeHint;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityHints;
import org.springframework.security.config.annotation.web.CommonSecurityResources;
import org.springframework.security.config.annotation.web.CommonSecurityTypes;
import org.springframework.security.config.annotation.web.configuration.WebMvcSecurityHints;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.WebAnnotationHints;

import static org.springframework.nativex.hint.TypeAccess.DECLARED_CONSTRUCTORS;
import static org.springframework.nativex.hint.TypeAccess.DECLARED_FIELDS;
import static org.springframework.nativex.hint.TypeAccess.DECLARED_METHODS;
import static org.springframework.nativex.hint.TypeAccess.PUBLIC_CLASSES;
import static org.springframework.nativex.hint.TypeAccess.PUBLIC_CONSTRUCTORS;
import static org.springframework.nativex.hint.TypeAccess.PUBLIC_FIELDS;
import static org.springframework.nativex.hint.TypeAccess.PUBLIC_METHODS;
import static org.springframework.nativex.hint.TypeAccess.QUERY_DECLARED_CONSTRUCTORS;
import static org.springframework.nativex.hint.TypeAccess.QUERY_DECLARED_METHODS;
import static org.springframework.nativex.hint.TypeAccess.QUERY_PUBLIC_CONSTRUCTORS;
import static org.springframework.nativex.hint.TypeAccess.QUERY_PUBLIC_METHODS;

@RestController
@RequestMapping ("/")
@NativeHint (trigger = DemoController.class,
             imports = { ValidatorHints.class, GlobalMethodSecurityHints.class, WebMvcSecurityHints.class,
                     CommonSecurityTypes.class, CommonSecurityResources.class, ConfigServerHints.class,
                     WebAnnotationHints.class, EndpointAutoConfigurationHints.class }, types = {
        @TypeHint (types = { DemoController.class },
                   access = { DECLARED_CONSTRUCTORS, DECLARED_FIELDS, DECLARED_METHODS, PUBLIC_CLASSES,
                           PUBLIC_CONSTRUCTORS, PUBLIC_FIELDS, PUBLIC_METHODS, QUERY_DECLARED_CONSTRUCTORS,
                           QUERY_DECLARED_METHODS, QUERY_PUBLIC_CONSTRUCTORS, QUERY_PUBLIC_METHODS }) })
public class DemoController
{
    @GetMapping(path = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission(#name,'name','something')")
    public ResponseEntity<String> helloWorld(
            @PathVariable (value = "name")
                    String name,
            @RequestParam (value = "age", required = false)
                    Integer age)
    {
        return ResponseEntity.ok("Hello " + name + " with age of " + age);
    }
}
