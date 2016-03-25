/*
 * FILENAME
 *     ServiceResolver.java
 *
 * FILE LOCATION
 *     $Source$
 *
 * VERSION
 *     $Id$
 *         @version       $Revision$
 *         Check-Out Tag: $Name$
 *         Locked By:     $Lockers$
 *
 * FORMATTING NOTES
 *     * Lines should be limited to 78 characters.
 *     * Files should contain no tabs.
 *     * Indent code using four-character increments.
 *
 * COPYRIGHT
 *     Copyright (C) 2005 vietsoftware international Inc. All rights reserved.
 *     This software is the confidential and proprietary information of
 *     VSII ("Confidential Information"). You shall not
 *     disclose such Confidential Information and shall use it only in
 *     accordance with the terms of the license agreement you entered into
 *     with VSII.
 */
package com.vsii.ttxvn.keywordsearching.utils;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * The class for get the correct implementation of any services in application.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
public class ServiceResolver
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceResolver.class);

    private static ServiceResolver instance;

    /**
     * The service container that contains services has a key is service interface class name map with the implemented
     * service object
     */
    private Map<String, Object> services = new HashMap<>();

    private ServiceResolver()
    {
    }

    public static ServiceResolver createServiceResolver(Map<String, Object> registedServices)
    {
        if (instance != null)
        {
            throw new IllegalStateException(ServiceResolver.class.getName() + " has been initialied");
        }

        instance = new ServiceResolver();

        if ((registedServices == null) || (registedServices.size() == 0))
        {
            return instance;
        }

        try
        {
            Class<?> serviceInterface;

            for (Map.Entry<String, Object> service : registedServices.entrySet())
            {
                serviceInterface = Class.forName(service.getKey());

                if (!serviceInterface.isInterface())
                {
                    throw new IllegalArgumentException("Wrong service interface was registed! "
                        + serviceInterface.getName() + " is not an interface");
                }

                if (!serviceInterface.isAssignableFrom(service.getValue().getClass()))
                {
                    throw new IllegalArgumentException("Class " + service.getValue().getClass().getName()
                        + " is not service that implement " + serviceInterface.getName());
                }

                if (instance.services.containsKey(serviceInterface.getName()))
                {
                    throw new IllegalStateException("Duplicate interface defined: " + serviceInterface.getName());
                }

                instance.services.put(serviceInterface.getName(), service.getValue());
                LOGGER.info("Loaded service: " + serviceInterface.getName());
            }
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return instance;
    }

    @SuppressWarnings("unchecked")
    public static <T> T findService(Class<T> serviceInterface)
    {
        if (serviceInterface == null || !serviceInterface.isInterface())
        {
            return null;
        }

        T service = (T) getInstance().services.get(serviceInterface.getName());

        return service;
    }

    private static ServiceResolver getInstance()
    {
        if (instance == null)
        {
            throw new IllegalStateException(ServiceResolver.class.getName() + " was not initialied");
        }

        return instance;
    }
}
