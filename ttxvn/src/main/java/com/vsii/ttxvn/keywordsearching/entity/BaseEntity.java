/*
 * FILENAME
 *     BaseEntity.java
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

package com.vsii.ttxvn.keywordsearching.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.LazyInitializationException;
import org.hibernate.proxy.HibernateProxy;

/**
 * <p>
 * Base Entity that must be extended by all Model class.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
@MappedSuperclass
public abstract class BaseEntity implements Serializable
{

    private static final long serialVersionUID = 4262860514835918845L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    /**
     * <p>
     * Implementation of object equality for persistent entity classes.
     * </p>
     * <p>
     * This implementation will test for equality base on the following rules:
     * </p>
     * <ul>
     * <li>Objects are never equal to <code>null</code>.</li>
     * <li>Objects are only equal if they are of exactly the same class.</li>
     * <li>
     * An object with a <code>null</code> <code>id</code> property can not be equal to one with a non-<code>null</code>
     * <code>id</code> property.</li>
     * <li>
     * Two objects with {@code null} {@code id} properties are only equal if they are both the same object.</li>
     * <li>
     * If both objects have non-<code>null</code> <code>id</code> properties, they are equal if the value of the
     * <code>id</code> properties are equal.</li>
     * </ul>
     * 
     * @param right
     *            Object to compare against <code>this</code>.
     * @return <code>true</code> if <code>this</code> is equal to <code>right</code>.
     */
    @Override
    public boolean equals(final Object right)
    {
        // Never Equal to Null
        if (right == null)
            return false;

        if (this == right)
            return true;

        // Check for Exact Class Match
        //
        // NOTES
        // With the use of ORMs and containers which intervene through the use
        // of multiple class loaders and proxy classes, it is unclear whether
        // class equality can be expressed as (class1 == class2).
        // TODO Check if class1 == class2 is a valid test with all ORMs/containers.
        if (this.getClass() != right.getClass())
        {
            // try to check against Hibernate implemenation
            if (!(right instanceof HibernateProxy))
                return false;

            if (!impliesSameClass(this.getClass(), right.getClass().getSuperclass()))
                return false;
        }
        BaseEntity rightEntity = (BaseEntity) right;

        // Objects with Null IDs are Only Equal if They are the Same Object
        //
        // NOTES
        // There is a subtle point here: logically, two tests need to be done:
        // 1. If the objects both have null IDs, then check that they are
        //    same object.
        // 2. If only one has a null ID, they are not "equal."
        // However, in the case of (2), the test below will fail the
        // references do not refer to the same object (which can not have
        // different ID values for different references).
        if (getId() == null || rightEntity.getId() == null)
            return false;

        // Compare IDs
        if (getId().equals(rightEntity.getId()))
            return true;

        return false;
    }

    static boolean impliesSameClass(final Class<?> left, final Class<?> right)
    {
        // should not use is() method as it may throw LazyInitializationException

        if (left.equals(right))
            return true;

        if (!right.isAssignableFrom(left))
            return false; // right class is from different class hierarchy

        Class<?> clazz = left;
        
        while (clazz != Object.class)
        {
            if (clazz.isAnnotationPresent(Inheritance.class))
                return InheritanceType.SINGLE_TABLE.equals(clazz.getAnnotation(Inheritance.class).strategy());
            clazz = clazz.getSuperclass();
        }

        return false;
    }

    /**
     * <p>
     * Implementation of hashing function for persistent entity classes.
     * </p>
     * <p>
     * This is a hashing function based on the object persistent ID i.e. the <code>id</code> property. Zero is returned
     * if the <code>id</code> property is <code>null</code> i.e. object has not been persisted.
     * </p>
     * 
     * @return The hash value for the object.
     */
    @Override
    public int hashCode()
    {
        if (getId() == null)
            return 0;
        else
            return getId().intValue();
    }

    /**
     * <p>
     * Get the persistent primary key.
     * </p>
     * 
     * @return Persistent primary key or <code>null</code> if the object has not been written to persistent storage.
     */
    public Long getId() 
    {
        return id;
    }
    
    /**
     * <p>
     * Setting value for id.
     * </p>
     * 
     * @param id the id to set
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * <p>
     * A convenient method for checking instance type against a given type. This is especially useful to be used on an
     * entity proxy.
     * </p>
     * <p>
     * Please note that calling this method on a proxied object may cause {@link LazyInitializationException}.
     * </p>
     * 
     * @since 1.2
     * @param clazz
     *            type to check against
     * @return true if this entity represents an instance of the same (or subclass of) given type, false otherwise
     * @throws LazyInitializationException
     *             if called on a detached proxied object
     **/
    public boolean isInstanceOf(final Class<?> clazz) throws LazyInitializationException
    {
        return clazz != null && clazz.isAssignableFrom(this.getClass());
    }

    /**
     * <p>
     * A convenient method for getting actual type of this entity. This is especially useful to be used on an entity
     * proxy.
     * </p>
     * <p>
     * Please note that calling this method on a proxied object may cause {@link LazyInitializationException}.
     * </p>
     * 
     * @since 1.2
     * @return The {@code Class} object that represents the runtime class of this object.
     * @throws LazyInitializationException
     *             if called on a detached proxied object
     **/
    public Class<? extends BaseEntity> getActualClass() throws LazyInitializationException
    {
        return this.getClass();
    }

    /**
     * <p>
     * A convenient method for getting actual implementation of this entity. This is especially useful to be used on an
     * entity proxy.
     * </p>
     * <p>
     * Please note that calling this method on a proxied object may cause {@link LazyInitializationException}.
     * </p>
     * <p>
     * <b style="color:#a00">WARNING</b>: use this api wisely, any abuse would lead to heavy consequences on
     * performance.
     * </p>
     * 
     * @since 1.5
     * @param <E>
     *            actual entity type
     * @return The actual instance object if this is a proxy, otherwise the same instance as the owner will be returned.
     * @throws LazyInitializationException
     *             if called on a detached proxied object
     **/
    @SuppressWarnings("unchecked")
    public final <E> E getTarget() throws LazyInitializationException
    {
        if (this instanceof HibernateProxy)
            return (E) ((HibernateProxy) this).getHibernateLazyInitializer().getImplementation();
        return (E) this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

}
