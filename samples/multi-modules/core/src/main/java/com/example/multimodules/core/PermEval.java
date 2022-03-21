package com.example.multimodules.core;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


@Component
public class PermEval implements PermissionEvaluator
{
    private static final Logger logger = LoggerFactory.getLogger(PermEval.class);
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission)
    {
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
            Object permission)
    {
        logger.info("The auth is " + authentication);
        logger.info("The targetId is " + targetId);
        logger.info("The targetType is " + targetType);
        logger.info("The permission is " + permission);

        return targetId != null;
    }
}
