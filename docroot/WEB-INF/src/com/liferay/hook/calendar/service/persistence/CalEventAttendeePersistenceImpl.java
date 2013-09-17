/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.hook.calendar.service.persistence;

import com.liferay.hook.calendar.NoSuchCalEventAttendeeException;
import com.liferay.hook.calendar.model.CalEventAttendee;
import com.liferay.hook.calendar.model.impl.CalEventAttendeeImpl;
import com.liferay.hook.calendar.model.impl.CalEventAttendeeModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the cal event attendee service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Joaquin Cabal
 * @see CalEventAttendeePersistence
 * @see CalEventAttendeeUtil
 * @generated
 */
public class CalEventAttendeePersistenceImpl extends BasePersistenceImpl<CalEventAttendee>
	implements CalEventAttendeePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CalEventAttendeeUtil} to access the cal event attendee persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CalEventAttendeeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_EVENTUSERASSIST =
		new FinderPath(CalEventAttendeeModelImpl.ENTITY_CACHE_ENABLED,
			CalEventAttendeeModelImpl.FINDER_CACHE_ENABLED,
			CalEventAttendeeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByEventUserAssist",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTUSERASSIST =
		new FinderPath(CalEventAttendeeModelImpl.ENTITY_CACHE_ENABLED,
			CalEventAttendeeModelImpl.FINDER_CACHE_ENABLED,
			CalEventAttendeeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByEventUserAssist",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			},
			CalEventAttendeeModelImpl.USERID_COLUMN_BITMASK |
			CalEventAttendeeModelImpl.EVENTID_COLUMN_BITMASK |
			CalEventAttendeeModelImpl.ASSIST_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_EVENTUSERASSIST = new FinderPath(CalEventAttendeeModelImpl.ENTITY_CACHE_ENABLED,
			CalEventAttendeeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByEventUserAssist",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_EVENTASSIST =
		new FinderPath(CalEventAttendeeModelImpl.ENTITY_CACHE_ENABLED,
			CalEventAttendeeModelImpl.FINDER_CACHE_ENABLED,
			CalEventAttendeeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByEventAssist",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTASSIST =
		new FinderPath(CalEventAttendeeModelImpl.ENTITY_CACHE_ENABLED,
			CalEventAttendeeModelImpl.FINDER_CACHE_ENABLED,
			CalEventAttendeeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByEventAssist",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			CalEventAttendeeModelImpl.EVENTID_COLUMN_BITMASK |
			CalEventAttendeeModelImpl.ASSIST_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_EVENTASSIST = new FinderPath(CalEventAttendeeModelImpl.ENTITY_CACHE_ENABLED,
			CalEventAttendeeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByEventAssist",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERASSIST =
		new FinderPath(CalEventAttendeeModelImpl.ENTITY_CACHE_ENABLED,
			CalEventAttendeeModelImpl.FINDER_CACHE_ENABLED,
			CalEventAttendeeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUserAssist",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERASSIST =
		new FinderPath(CalEventAttendeeModelImpl.ENTITY_CACHE_ENABLED,
			CalEventAttendeeModelImpl.FINDER_CACHE_ENABLED,
			CalEventAttendeeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserAssist",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			CalEventAttendeeModelImpl.USERID_COLUMN_BITMASK |
			CalEventAttendeeModelImpl.ASSIST_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERASSIST = new FinderPath(CalEventAttendeeModelImpl.ENTITY_CACHE_ENABLED,
			CalEventAttendeeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserAssist",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USER = new FinderPath(CalEventAttendeeModelImpl.ENTITY_CACHE_ENABLED,
			CalEventAttendeeModelImpl.FINDER_CACHE_ENABLED,
			CalEventAttendeeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUser",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USER = new FinderPath(CalEventAttendeeModelImpl.ENTITY_CACHE_ENABLED,
			CalEventAttendeeModelImpl.FINDER_CACHE_ENABLED,
			CalEventAttendeeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUser",
			new String[] { Long.class.getName() },
			CalEventAttendeeModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USER = new FinderPath(CalEventAttendeeModelImpl.ENTITY_CACHE_ENABLED,
			CalEventAttendeeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUser",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_EVENT = new FinderPath(CalEventAttendeeModelImpl.ENTITY_CACHE_ENABLED,
			CalEventAttendeeModelImpl.FINDER_CACHE_ENABLED,
			CalEventAttendeeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByEvent",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENT = new FinderPath(CalEventAttendeeModelImpl.ENTITY_CACHE_ENABLED,
			CalEventAttendeeModelImpl.FINDER_CACHE_ENABLED,
			CalEventAttendeeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByEvent",
			new String[] { Long.class.getName() },
			CalEventAttendeeModelImpl.EVENTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_EVENT = new FinderPath(CalEventAttendeeModelImpl.ENTITY_CACHE_ENABLED,
			CalEventAttendeeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByEvent",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CalEventAttendeeModelImpl.ENTITY_CACHE_ENABLED,
			CalEventAttendeeModelImpl.FINDER_CACHE_ENABLED,
			CalEventAttendeeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CalEventAttendeeModelImpl.ENTITY_CACHE_ENABLED,
			CalEventAttendeeModelImpl.FINDER_CACHE_ENABLED,
			CalEventAttendeeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CalEventAttendeeModelImpl.ENTITY_CACHE_ENABLED,
			CalEventAttendeeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the cal event attendee in the entity cache if it is enabled.
	 *
	 * @param calEventAttendee the cal event attendee
	 */
	public void cacheResult(CalEventAttendee calEventAttendee) {
		EntityCacheUtil.putResult(CalEventAttendeeModelImpl.ENTITY_CACHE_ENABLED,
			CalEventAttendeeImpl.class, calEventAttendee.getPrimaryKey(),
			calEventAttendee);

		calEventAttendee.resetOriginalValues();
	}

	/**
	 * Caches the cal event attendees in the entity cache if it is enabled.
	 *
	 * @param calEventAttendees the cal event attendees
	 */
	public void cacheResult(List<CalEventAttendee> calEventAttendees) {
		for (CalEventAttendee calEventAttendee : calEventAttendees) {
			if (EntityCacheUtil.getResult(
						CalEventAttendeeModelImpl.ENTITY_CACHE_ENABLED,
						CalEventAttendeeImpl.class,
						calEventAttendee.getPrimaryKey()) == null) {
				cacheResult(calEventAttendee);
			}
			else {
				calEventAttendee.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all cal event attendees.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(CalEventAttendeeImpl.class.getName());
		}

		EntityCacheUtil.clearCache(CalEventAttendeeImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cal event attendee.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CalEventAttendee calEventAttendee) {
		EntityCacheUtil.removeResult(CalEventAttendeeModelImpl.ENTITY_CACHE_ENABLED,
			CalEventAttendeeImpl.class, calEventAttendee.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CalEventAttendee> calEventAttendees) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CalEventAttendee calEventAttendee : calEventAttendees) {
			EntityCacheUtil.removeResult(CalEventAttendeeModelImpl.ENTITY_CACHE_ENABLED,
				CalEventAttendeeImpl.class, calEventAttendee.getPrimaryKey());
		}
	}

	/**
	 * Creates a new cal event attendee with the primary key. Does not add the cal event attendee to the database.
	 *
	 * @param calEventAttendeeId the primary key for the new cal event attendee
	 * @return the new cal event attendee
	 */
	public CalEventAttendee create(long calEventAttendeeId) {
		CalEventAttendee calEventAttendee = new CalEventAttendeeImpl();

		calEventAttendee.setNew(true);
		calEventAttendee.setPrimaryKey(calEventAttendeeId);

		return calEventAttendee;
	}

	/**
	 * Removes the cal event attendee with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param calEventAttendeeId the primary key of the cal event attendee
	 * @return the cal event attendee that was removed
	 * @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a cal event attendee with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalEventAttendee remove(long calEventAttendeeId)
		throws NoSuchCalEventAttendeeException, SystemException {
		return remove(Long.valueOf(calEventAttendeeId));
	}

	/**
	 * Removes the cal event attendee with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cal event attendee
	 * @return the cal event attendee that was removed
	 * @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a cal event attendee with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CalEventAttendee remove(Serializable primaryKey)
		throws NoSuchCalEventAttendeeException, SystemException {
		Session session = null;

		try {
			session = openSession();

			CalEventAttendee calEventAttendee = (CalEventAttendee)session.get(CalEventAttendeeImpl.class,
					primaryKey);

			if (calEventAttendee == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCalEventAttendeeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(calEventAttendee);
		}
		catch (NoSuchCalEventAttendeeException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected CalEventAttendee removeImpl(CalEventAttendee calEventAttendee)
		throws SystemException {
		calEventAttendee = toUnwrappedModel(calEventAttendee);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, calEventAttendee);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(calEventAttendee);

		return calEventAttendee;
	}

	@Override
	public CalEventAttendee updateImpl(
		com.liferay.hook.calendar.model.CalEventAttendee calEventAttendee,
		boolean merge) throws SystemException {
		calEventAttendee = toUnwrappedModel(calEventAttendee);

		boolean isNew = calEventAttendee.isNew();

		CalEventAttendeeModelImpl calEventAttendeeModelImpl = (CalEventAttendeeModelImpl)calEventAttendee;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, calEventAttendee, merge);

			calEventAttendee.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !CalEventAttendeeModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((calEventAttendeeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTUSERASSIST.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(calEventAttendeeModelImpl.getOriginalUserId()),
						Long.valueOf(calEventAttendeeModelImpl.getOriginalEventId()),
						Boolean.valueOf(calEventAttendeeModelImpl.getOriginalAssist())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_EVENTUSERASSIST,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTUSERASSIST,
					args);

				args = new Object[] {
						Long.valueOf(calEventAttendeeModelImpl.getUserId()),
						Long.valueOf(calEventAttendeeModelImpl.getEventId()),
						Boolean.valueOf(calEventAttendeeModelImpl.getAssist())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_EVENTUSERASSIST,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTUSERASSIST,
					args);
			}

			if ((calEventAttendeeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTASSIST.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(calEventAttendeeModelImpl.getOriginalEventId()),
						Boolean.valueOf(calEventAttendeeModelImpl.getOriginalAssist())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_EVENTASSIST,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTASSIST,
					args);

				args = new Object[] {
						Long.valueOf(calEventAttendeeModelImpl.getEventId()),
						Boolean.valueOf(calEventAttendeeModelImpl.getAssist())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_EVENTASSIST,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTASSIST,
					args);
			}

			if ((calEventAttendeeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERASSIST.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(calEventAttendeeModelImpl.getOriginalUserId()),
						Boolean.valueOf(calEventAttendeeModelImpl.getOriginalAssist())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERASSIST,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERASSIST,
					args);

				args = new Object[] {
						Long.valueOf(calEventAttendeeModelImpl.getUserId()),
						Boolean.valueOf(calEventAttendeeModelImpl.getAssist())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERASSIST,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERASSIST,
					args);
			}

			if ((calEventAttendeeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USER.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(calEventAttendeeModelImpl.getOriginalUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USER, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USER,
					args);

				args = new Object[] {
						Long.valueOf(calEventAttendeeModelImpl.getUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USER, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USER,
					args);
			}

			if ((calEventAttendeeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(calEventAttendeeModelImpl.getOriginalEventId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_EVENT, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENT,
					args);

				args = new Object[] {
						Long.valueOf(calEventAttendeeModelImpl.getEventId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_EVENT, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENT,
					args);
			}
		}

		EntityCacheUtil.putResult(CalEventAttendeeModelImpl.ENTITY_CACHE_ENABLED,
			CalEventAttendeeImpl.class, calEventAttendee.getPrimaryKey(),
			calEventAttendee);

		return calEventAttendee;
	}

	protected CalEventAttendee toUnwrappedModel(
		CalEventAttendee calEventAttendee) {
		if (calEventAttendee instanceof CalEventAttendeeImpl) {
			return calEventAttendee;
		}

		CalEventAttendeeImpl calEventAttendeeImpl = new CalEventAttendeeImpl();

		calEventAttendeeImpl.setNew(calEventAttendee.isNew());
		calEventAttendeeImpl.setPrimaryKey(calEventAttendee.getPrimaryKey());

		calEventAttendeeImpl.setCalEventAttendeeId(calEventAttendee.getCalEventAttendeeId());
		calEventAttendeeImpl.setEventId(calEventAttendee.getEventId());
		calEventAttendeeImpl.setUserId(calEventAttendee.getUserId());
		calEventAttendeeImpl.setAssist(calEventAttendee.isAssist());

		return calEventAttendeeImpl;
	}

	/**
	 * Returns the cal event attendee with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the cal event attendee
	 * @return the cal event attendee
	 * @throws com.liferay.portal.NoSuchModelException if a cal event attendee with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CalEventAttendee findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the cal event attendee with the primary key or throws a {@link com.liferay.hook.calendar.NoSuchCalEventAttendeeException} if it could not be found.
	 *
	 * @param calEventAttendeeId the primary key of the cal event attendee
	 * @return the cal event attendee
	 * @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a cal event attendee with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalEventAttendee findByPrimaryKey(long calEventAttendeeId)
		throws NoSuchCalEventAttendeeException, SystemException {
		CalEventAttendee calEventAttendee = fetchByPrimaryKey(calEventAttendeeId);

		if (calEventAttendee == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					calEventAttendeeId);
			}

			throw new NoSuchCalEventAttendeeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				calEventAttendeeId);
		}

		return calEventAttendee;
	}

	/**
	 * Returns the cal event attendee with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cal event attendee
	 * @return the cal event attendee, or <code>null</code> if a cal event attendee with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CalEventAttendee fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the cal event attendee with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param calEventAttendeeId the primary key of the cal event attendee
	 * @return the cal event attendee, or <code>null</code> if a cal event attendee with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalEventAttendee fetchByPrimaryKey(long calEventAttendeeId)
		throws SystemException {
		CalEventAttendee calEventAttendee = (CalEventAttendee)EntityCacheUtil.getResult(CalEventAttendeeModelImpl.ENTITY_CACHE_ENABLED,
				CalEventAttendeeImpl.class, calEventAttendeeId);

		if (calEventAttendee == _nullCalEventAttendee) {
			return null;
		}

		if (calEventAttendee == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				calEventAttendee = (CalEventAttendee)session.get(CalEventAttendeeImpl.class,
						Long.valueOf(calEventAttendeeId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (calEventAttendee != null) {
					cacheResult(calEventAttendee);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(CalEventAttendeeModelImpl.ENTITY_CACHE_ENABLED,
						CalEventAttendeeImpl.class, calEventAttendeeId,
						_nullCalEventAttendee);
				}

				closeSession(session);
			}
		}

		return calEventAttendee;
	}

	/**
	 * Returns all the cal event attendees where userId = &#63; and eventId = &#63; and assist = &#63;.
	 *
	 * @param userId the user ID
	 * @param eventId the event ID
	 * @param assist the assist
	 * @return the matching cal event attendees
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalEventAttendee> findByEventUserAssist(long userId,
		long eventId, boolean assist) throws SystemException {
		return findByEventUserAssist(userId, eventId, assist,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cal event attendees where userId = &#63; and eventId = &#63; and assist = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param eventId the event ID
	 * @param assist the assist
	 * @param start the lower bound of the range of cal event attendees
	 * @param end the upper bound of the range of cal event attendees (not inclusive)
	 * @return the range of matching cal event attendees
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalEventAttendee> findByEventUserAssist(long userId,
		long eventId, boolean assist, int start, int end)
		throws SystemException {
		return findByEventUserAssist(userId, eventId, assist, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cal event attendees where userId = &#63; and eventId = &#63; and assist = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param eventId the event ID
	 * @param assist the assist
	 * @param start the lower bound of the range of cal event attendees
	 * @param end the upper bound of the range of cal event attendees (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cal event attendees
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalEventAttendee> findByEventUserAssist(long userId,
		long eventId, boolean assist, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTUSERASSIST;
			finderArgs = new Object[] { userId, eventId, assist };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_EVENTUSERASSIST;
			finderArgs = new Object[] {
					userId, eventId, assist,
					
					start, end, orderByComparator
				};
		}

		List<CalEventAttendee> list = (List<CalEventAttendee>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (CalEventAttendee calEventAttendee : list) {
				if ((userId != calEventAttendee.getUserId()) ||
						(eventId != calEventAttendee.getEventId()) ||
						(assist != calEventAttendee.getAssist())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_CALEVENTATTENDEE_WHERE);

			query.append(_FINDER_COLUMN_EVENTUSERASSIST_USERID_2);

			query.append(_FINDER_COLUMN_EVENTUSERASSIST_EVENTID_2);

			query.append(_FINDER_COLUMN_EVENTUSERASSIST_ASSIST_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(eventId);

				qPos.add(assist);

				list = (List<CalEventAttendee>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first cal event attendee in the ordered set where userId = &#63; and eventId = &#63; and assist = &#63;.
	 *
	 * @param userId the user ID
	 * @param eventId the event ID
	 * @param assist the assist
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cal event attendee
	 * @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a matching cal event attendee could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalEventAttendee findByEventUserAssist_First(long userId,
		long eventId, boolean assist, OrderByComparator orderByComparator)
		throws NoSuchCalEventAttendeeException, SystemException {
		CalEventAttendee calEventAttendee = fetchByEventUserAssist_First(userId,
				eventId, assist, orderByComparator);

		if (calEventAttendee != null) {
			return calEventAttendee;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", eventId=");
		msg.append(eventId);

		msg.append(", assist=");
		msg.append(assist);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCalEventAttendeeException(msg.toString());
	}

	/**
	 * Returns the first cal event attendee in the ordered set where userId = &#63; and eventId = &#63; and assist = &#63;.
	 *
	 * @param userId the user ID
	 * @param eventId the event ID
	 * @param assist the assist
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cal event attendee, or <code>null</code> if a matching cal event attendee could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalEventAttendee fetchByEventUserAssist_First(long userId,
		long eventId, boolean assist, OrderByComparator orderByComparator)
		throws SystemException {
		List<CalEventAttendee> list = findByEventUserAssist(userId, eventId,
				assist, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cal event attendee in the ordered set where userId = &#63; and eventId = &#63; and assist = &#63;.
	 *
	 * @param userId the user ID
	 * @param eventId the event ID
	 * @param assist the assist
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cal event attendee
	 * @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a matching cal event attendee could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalEventAttendee findByEventUserAssist_Last(long userId,
		long eventId, boolean assist, OrderByComparator orderByComparator)
		throws NoSuchCalEventAttendeeException, SystemException {
		CalEventAttendee calEventAttendee = fetchByEventUserAssist_Last(userId,
				eventId, assist, orderByComparator);

		if (calEventAttendee != null) {
			return calEventAttendee;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", eventId=");
		msg.append(eventId);

		msg.append(", assist=");
		msg.append(assist);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCalEventAttendeeException(msg.toString());
	}

	/**
	 * Returns the last cal event attendee in the ordered set where userId = &#63; and eventId = &#63; and assist = &#63;.
	 *
	 * @param userId the user ID
	 * @param eventId the event ID
	 * @param assist the assist
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cal event attendee, or <code>null</code> if a matching cal event attendee could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalEventAttendee fetchByEventUserAssist_Last(long userId,
		long eventId, boolean assist, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByEventUserAssist(userId, eventId, assist);

		List<CalEventAttendee> list = findByEventUserAssist(userId, eventId,
				assist, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cal event attendees before and after the current cal event attendee in the ordered set where userId = &#63; and eventId = &#63; and assist = &#63;.
	 *
	 * @param calEventAttendeeId the primary key of the current cal event attendee
	 * @param userId the user ID
	 * @param eventId the event ID
	 * @param assist the assist
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cal event attendee
	 * @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a cal event attendee with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalEventAttendee[] findByEventUserAssist_PrevAndNext(
		long calEventAttendeeId, long userId, long eventId, boolean assist,
		OrderByComparator orderByComparator)
		throws NoSuchCalEventAttendeeException, SystemException {
		CalEventAttendee calEventAttendee = findByPrimaryKey(calEventAttendeeId);

		Session session = null;

		try {
			session = openSession();

			CalEventAttendee[] array = new CalEventAttendeeImpl[3];

			array[0] = getByEventUserAssist_PrevAndNext(session,
					calEventAttendee, userId, eventId, assist,
					orderByComparator, true);

			array[1] = calEventAttendee;

			array[2] = getByEventUserAssist_PrevAndNext(session,
					calEventAttendee, userId, eventId, assist,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CalEventAttendee getByEventUserAssist_PrevAndNext(
		Session session, CalEventAttendee calEventAttendee, long userId,
		long eventId, boolean assist, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALEVENTATTENDEE_WHERE);

		query.append(_FINDER_COLUMN_EVENTUSERASSIST_USERID_2);

		query.append(_FINDER_COLUMN_EVENTUSERASSIST_EVENTID_2);

		query.append(_FINDER_COLUMN_EVENTUSERASSIST_ASSIST_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(eventId);

		qPos.add(assist);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(calEventAttendee);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CalEventAttendee> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the cal event attendees where eventId = &#63; and assist = &#63;.
	 *
	 * @param eventId the event ID
	 * @param assist the assist
	 * @return the matching cal event attendees
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalEventAttendee> findByEventAssist(long eventId, boolean assist)
		throws SystemException {
		return findByEventAssist(eventId, assist, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cal event attendees where eventId = &#63; and assist = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param assist the assist
	 * @param start the lower bound of the range of cal event attendees
	 * @param end the upper bound of the range of cal event attendees (not inclusive)
	 * @return the range of matching cal event attendees
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalEventAttendee> findByEventAssist(long eventId,
		boolean assist, int start, int end) throws SystemException {
		return findByEventAssist(eventId, assist, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cal event attendees where eventId = &#63; and assist = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param assist the assist
	 * @param start the lower bound of the range of cal event attendees
	 * @param end the upper bound of the range of cal event attendees (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cal event attendees
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalEventAttendee> findByEventAssist(long eventId,
		boolean assist, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTASSIST;
			finderArgs = new Object[] { eventId, assist };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_EVENTASSIST;
			finderArgs = new Object[] {
					eventId, assist,
					
					start, end, orderByComparator
				};
		}

		List<CalEventAttendee> list = (List<CalEventAttendee>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (CalEventAttendee calEventAttendee : list) {
				if ((eventId != calEventAttendee.getEventId()) ||
						(assist != calEventAttendee.getAssist())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_CALEVENTATTENDEE_WHERE);

			query.append(_FINDER_COLUMN_EVENTASSIST_EVENTID_2);

			query.append(_FINDER_COLUMN_EVENTASSIST_ASSIST_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(eventId);

				qPos.add(assist);

				list = (List<CalEventAttendee>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first cal event attendee in the ordered set where eventId = &#63; and assist = &#63;.
	 *
	 * @param eventId the event ID
	 * @param assist the assist
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cal event attendee
	 * @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a matching cal event attendee could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalEventAttendee findByEventAssist_First(long eventId,
		boolean assist, OrderByComparator orderByComparator)
		throws NoSuchCalEventAttendeeException, SystemException {
		CalEventAttendee calEventAttendee = fetchByEventAssist_First(eventId,
				assist, orderByComparator);

		if (calEventAttendee != null) {
			return calEventAttendee;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("eventId=");
		msg.append(eventId);

		msg.append(", assist=");
		msg.append(assist);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCalEventAttendeeException(msg.toString());
	}

	/**
	 * Returns the first cal event attendee in the ordered set where eventId = &#63; and assist = &#63;.
	 *
	 * @param eventId the event ID
	 * @param assist the assist
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cal event attendee, or <code>null</code> if a matching cal event attendee could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalEventAttendee fetchByEventAssist_First(long eventId,
		boolean assist, OrderByComparator orderByComparator)
		throws SystemException {
		List<CalEventAttendee> list = findByEventAssist(eventId, assist, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cal event attendee in the ordered set where eventId = &#63; and assist = &#63;.
	 *
	 * @param eventId the event ID
	 * @param assist the assist
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cal event attendee
	 * @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a matching cal event attendee could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalEventAttendee findByEventAssist_Last(long eventId,
		boolean assist, OrderByComparator orderByComparator)
		throws NoSuchCalEventAttendeeException, SystemException {
		CalEventAttendee calEventAttendee = fetchByEventAssist_Last(eventId,
				assist, orderByComparator);

		if (calEventAttendee != null) {
			return calEventAttendee;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("eventId=");
		msg.append(eventId);

		msg.append(", assist=");
		msg.append(assist);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCalEventAttendeeException(msg.toString());
	}

	/**
	 * Returns the last cal event attendee in the ordered set where eventId = &#63; and assist = &#63;.
	 *
	 * @param eventId the event ID
	 * @param assist the assist
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cal event attendee, or <code>null</code> if a matching cal event attendee could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalEventAttendee fetchByEventAssist_Last(long eventId,
		boolean assist, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByEventAssist(eventId, assist);

		List<CalEventAttendee> list = findByEventAssist(eventId, assist,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cal event attendees before and after the current cal event attendee in the ordered set where eventId = &#63; and assist = &#63;.
	 *
	 * @param calEventAttendeeId the primary key of the current cal event attendee
	 * @param eventId the event ID
	 * @param assist the assist
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cal event attendee
	 * @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a cal event attendee with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalEventAttendee[] findByEventAssist_PrevAndNext(
		long calEventAttendeeId, long eventId, boolean assist,
		OrderByComparator orderByComparator)
		throws NoSuchCalEventAttendeeException, SystemException {
		CalEventAttendee calEventAttendee = findByPrimaryKey(calEventAttendeeId);

		Session session = null;

		try {
			session = openSession();

			CalEventAttendee[] array = new CalEventAttendeeImpl[3];

			array[0] = getByEventAssist_PrevAndNext(session, calEventAttendee,
					eventId, assist, orderByComparator, true);

			array[1] = calEventAttendee;

			array[2] = getByEventAssist_PrevAndNext(session, calEventAttendee,
					eventId, assist, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CalEventAttendee getByEventAssist_PrevAndNext(Session session,
		CalEventAttendee calEventAttendee, long eventId, boolean assist,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALEVENTATTENDEE_WHERE);

		query.append(_FINDER_COLUMN_EVENTASSIST_EVENTID_2);

		query.append(_FINDER_COLUMN_EVENTASSIST_ASSIST_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(eventId);

		qPos.add(assist);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(calEventAttendee);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CalEventAttendee> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the cal event attendees where userId = &#63; and assist = &#63;.
	 *
	 * @param userId the user ID
	 * @param assist the assist
	 * @return the matching cal event attendees
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalEventAttendee> findByUserAssist(long userId, boolean assist)
		throws SystemException {
		return findByUserAssist(userId, assist, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cal event attendees where userId = &#63; and assist = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param assist the assist
	 * @param start the lower bound of the range of cal event attendees
	 * @param end the upper bound of the range of cal event attendees (not inclusive)
	 * @return the range of matching cal event attendees
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalEventAttendee> findByUserAssist(long userId, boolean assist,
		int start, int end) throws SystemException {
		return findByUserAssist(userId, assist, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cal event attendees where userId = &#63; and assist = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param assist the assist
	 * @param start the lower bound of the range of cal event attendees
	 * @param end the upper bound of the range of cal event attendees (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cal event attendees
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalEventAttendee> findByUserAssist(long userId, boolean assist,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERASSIST;
			finderArgs = new Object[] { userId, assist };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERASSIST;
			finderArgs = new Object[] {
					userId, assist,
					
					start, end, orderByComparator
				};
		}

		List<CalEventAttendee> list = (List<CalEventAttendee>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (CalEventAttendee calEventAttendee : list) {
				if ((userId != calEventAttendee.getUserId()) ||
						(assist != calEventAttendee.getAssist())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_CALEVENTATTENDEE_WHERE);

			query.append(_FINDER_COLUMN_USERASSIST_USERID_2);

			query.append(_FINDER_COLUMN_USERASSIST_ASSIST_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(assist);

				list = (List<CalEventAttendee>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first cal event attendee in the ordered set where userId = &#63; and assist = &#63;.
	 *
	 * @param userId the user ID
	 * @param assist the assist
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cal event attendee
	 * @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a matching cal event attendee could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalEventAttendee findByUserAssist_First(long userId, boolean assist,
		OrderByComparator orderByComparator)
		throws NoSuchCalEventAttendeeException, SystemException {
		CalEventAttendee calEventAttendee = fetchByUserAssist_First(userId,
				assist, orderByComparator);

		if (calEventAttendee != null) {
			return calEventAttendee;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", assist=");
		msg.append(assist);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCalEventAttendeeException(msg.toString());
	}

	/**
	 * Returns the first cal event attendee in the ordered set where userId = &#63; and assist = &#63;.
	 *
	 * @param userId the user ID
	 * @param assist the assist
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cal event attendee, or <code>null</code> if a matching cal event attendee could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalEventAttendee fetchByUserAssist_First(long userId,
		boolean assist, OrderByComparator orderByComparator)
		throws SystemException {
		List<CalEventAttendee> list = findByUserAssist(userId, assist, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cal event attendee in the ordered set where userId = &#63; and assist = &#63;.
	 *
	 * @param userId the user ID
	 * @param assist the assist
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cal event attendee
	 * @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a matching cal event attendee could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalEventAttendee findByUserAssist_Last(long userId, boolean assist,
		OrderByComparator orderByComparator)
		throws NoSuchCalEventAttendeeException, SystemException {
		CalEventAttendee calEventAttendee = fetchByUserAssist_Last(userId,
				assist, orderByComparator);

		if (calEventAttendee != null) {
			return calEventAttendee;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", assist=");
		msg.append(assist);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCalEventAttendeeException(msg.toString());
	}

	/**
	 * Returns the last cal event attendee in the ordered set where userId = &#63; and assist = &#63;.
	 *
	 * @param userId the user ID
	 * @param assist the assist
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cal event attendee, or <code>null</code> if a matching cal event attendee could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalEventAttendee fetchByUserAssist_Last(long userId, boolean assist,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUserAssist(userId, assist);

		List<CalEventAttendee> list = findByUserAssist(userId, assist,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cal event attendees before and after the current cal event attendee in the ordered set where userId = &#63; and assist = &#63;.
	 *
	 * @param calEventAttendeeId the primary key of the current cal event attendee
	 * @param userId the user ID
	 * @param assist the assist
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cal event attendee
	 * @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a cal event attendee with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalEventAttendee[] findByUserAssist_PrevAndNext(
		long calEventAttendeeId, long userId, boolean assist,
		OrderByComparator orderByComparator)
		throws NoSuchCalEventAttendeeException, SystemException {
		CalEventAttendee calEventAttendee = findByPrimaryKey(calEventAttendeeId);

		Session session = null;

		try {
			session = openSession();

			CalEventAttendee[] array = new CalEventAttendeeImpl[3];

			array[0] = getByUserAssist_PrevAndNext(session, calEventAttendee,
					userId, assist, orderByComparator, true);

			array[1] = calEventAttendee;

			array[2] = getByUserAssist_PrevAndNext(session, calEventAttendee,
					userId, assist, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CalEventAttendee getByUserAssist_PrevAndNext(Session session,
		CalEventAttendee calEventAttendee, long userId, boolean assist,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALEVENTATTENDEE_WHERE);

		query.append(_FINDER_COLUMN_USERASSIST_USERID_2);

		query.append(_FINDER_COLUMN_USERASSIST_ASSIST_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(assist);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(calEventAttendee);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CalEventAttendee> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the cal event attendees where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching cal event attendees
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalEventAttendee> findByUser(long userId)
		throws SystemException {
		return findByUser(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cal event attendees where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of cal event attendees
	 * @param end the upper bound of the range of cal event attendees (not inclusive)
	 * @return the range of matching cal event attendees
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalEventAttendee> findByUser(long userId, int start, int end)
		throws SystemException {
		return findByUser(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cal event attendees where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of cal event attendees
	 * @param end the upper bound of the range of cal event attendees (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cal event attendees
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalEventAttendee> findByUser(long userId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USER;
			finderArgs = new Object[] { userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USER;
			finderArgs = new Object[] { userId, start, end, orderByComparator };
		}

		List<CalEventAttendee> list = (List<CalEventAttendee>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (CalEventAttendee calEventAttendee : list) {
				if ((userId != calEventAttendee.getUserId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_CALEVENTATTENDEE_WHERE);

			query.append(_FINDER_COLUMN_USER_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				list = (List<CalEventAttendee>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first cal event attendee in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cal event attendee
	 * @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a matching cal event attendee could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalEventAttendee findByUser_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchCalEventAttendeeException, SystemException {
		CalEventAttendee calEventAttendee = fetchByUser_First(userId,
				orderByComparator);

		if (calEventAttendee != null) {
			return calEventAttendee;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCalEventAttendeeException(msg.toString());
	}

	/**
	 * Returns the first cal event attendee in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cal event attendee, or <code>null</code> if a matching cal event attendee could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalEventAttendee fetchByUser_First(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<CalEventAttendee> list = findByUser(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cal event attendee in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cal event attendee
	 * @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a matching cal event attendee could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalEventAttendee findByUser_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchCalEventAttendeeException, SystemException {
		CalEventAttendee calEventAttendee = fetchByUser_Last(userId,
				orderByComparator);

		if (calEventAttendee != null) {
			return calEventAttendee;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCalEventAttendeeException(msg.toString());
	}

	/**
	 * Returns the last cal event attendee in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cal event attendee, or <code>null</code> if a matching cal event attendee could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalEventAttendee fetchByUser_Last(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUser(userId);

		List<CalEventAttendee> list = findByUser(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cal event attendees before and after the current cal event attendee in the ordered set where userId = &#63;.
	 *
	 * @param calEventAttendeeId the primary key of the current cal event attendee
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cal event attendee
	 * @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a cal event attendee with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalEventAttendee[] findByUser_PrevAndNext(long calEventAttendeeId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchCalEventAttendeeException, SystemException {
		CalEventAttendee calEventAttendee = findByPrimaryKey(calEventAttendeeId);

		Session session = null;

		try {
			session = openSession();

			CalEventAttendee[] array = new CalEventAttendeeImpl[3];

			array[0] = getByUser_PrevAndNext(session, calEventAttendee, userId,
					orderByComparator, true);

			array[1] = calEventAttendee;

			array[2] = getByUser_PrevAndNext(session, calEventAttendee, userId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CalEventAttendee getByUser_PrevAndNext(Session session,
		CalEventAttendee calEventAttendee, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALEVENTATTENDEE_WHERE);

		query.append(_FINDER_COLUMN_USER_USERID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(calEventAttendee);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CalEventAttendee> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the cal event attendees where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @return the matching cal event attendees
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalEventAttendee> findByEvent(long eventId)
		throws SystemException {
		return findByEvent(eventId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cal event attendees where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param start the lower bound of the range of cal event attendees
	 * @param end the upper bound of the range of cal event attendees (not inclusive)
	 * @return the range of matching cal event attendees
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalEventAttendee> findByEvent(long eventId, int start, int end)
		throws SystemException {
		return findByEvent(eventId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cal event attendees where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param start the lower bound of the range of cal event attendees
	 * @param end the upper bound of the range of cal event attendees (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cal event attendees
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalEventAttendee> findByEvent(long eventId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENT;
			finderArgs = new Object[] { eventId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_EVENT;
			finderArgs = new Object[] { eventId, start, end, orderByComparator };
		}

		List<CalEventAttendee> list = (List<CalEventAttendee>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (CalEventAttendee calEventAttendee : list) {
				if ((eventId != calEventAttendee.getEventId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_CALEVENTATTENDEE_WHERE);

			query.append(_FINDER_COLUMN_EVENT_EVENTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(eventId);

				list = (List<CalEventAttendee>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first cal event attendee in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cal event attendee
	 * @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a matching cal event attendee could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalEventAttendee findByEvent_First(long eventId,
		OrderByComparator orderByComparator)
		throws NoSuchCalEventAttendeeException, SystemException {
		CalEventAttendee calEventAttendee = fetchByEvent_First(eventId,
				orderByComparator);

		if (calEventAttendee != null) {
			return calEventAttendee;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("eventId=");
		msg.append(eventId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCalEventAttendeeException(msg.toString());
	}

	/**
	 * Returns the first cal event attendee in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cal event attendee, or <code>null</code> if a matching cal event attendee could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalEventAttendee fetchByEvent_First(long eventId,
		OrderByComparator orderByComparator) throws SystemException {
		List<CalEventAttendee> list = findByEvent(eventId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cal event attendee in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cal event attendee
	 * @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a matching cal event attendee could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalEventAttendee findByEvent_Last(long eventId,
		OrderByComparator orderByComparator)
		throws NoSuchCalEventAttendeeException, SystemException {
		CalEventAttendee calEventAttendee = fetchByEvent_Last(eventId,
				orderByComparator);

		if (calEventAttendee != null) {
			return calEventAttendee;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("eventId=");
		msg.append(eventId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCalEventAttendeeException(msg.toString());
	}

	/**
	 * Returns the last cal event attendee in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cal event attendee, or <code>null</code> if a matching cal event attendee could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalEventAttendee fetchByEvent_Last(long eventId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByEvent(eventId);

		List<CalEventAttendee> list = findByEvent(eventId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cal event attendees before and after the current cal event attendee in the ordered set where eventId = &#63;.
	 *
	 * @param calEventAttendeeId the primary key of the current cal event attendee
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cal event attendee
	 * @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a cal event attendee with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalEventAttendee[] findByEvent_PrevAndNext(long calEventAttendeeId,
		long eventId, OrderByComparator orderByComparator)
		throws NoSuchCalEventAttendeeException, SystemException {
		CalEventAttendee calEventAttendee = findByPrimaryKey(calEventAttendeeId);

		Session session = null;

		try {
			session = openSession();

			CalEventAttendee[] array = new CalEventAttendeeImpl[3];

			array[0] = getByEvent_PrevAndNext(session, calEventAttendee,
					eventId, orderByComparator, true);

			array[1] = calEventAttendee;

			array[2] = getByEvent_PrevAndNext(session, calEventAttendee,
					eventId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CalEventAttendee getByEvent_PrevAndNext(Session session,
		CalEventAttendee calEventAttendee, long eventId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALEVENTATTENDEE_WHERE);

		query.append(_FINDER_COLUMN_EVENT_EVENTID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(eventId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(calEventAttendee);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CalEventAttendee> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the cal event attendees.
	 *
	 * @return the cal event attendees
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalEventAttendee> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cal event attendees.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of cal event attendees
	 * @param end the upper bound of the range of cal event attendees (not inclusive)
	 * @return the range of cal event attendees
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalEventAttendee> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cal event attendees.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of cal event attendees
	 * @param end the upper bound of the range of cal event attendees (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cal event attendees
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalEventAttendee> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<CalEventAttendee> list = (List<CalEventAttendee>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_CALEVENTATTENDEE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CALEVENTATTENDEE;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<CalEventAttendee>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<CalEventAttendee>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the cal event attendees where userId = &#63; and eventId = &#63; and assist = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param eventId the event ID
	 * @param assist the assist
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByEventUserAssist(long userId, long eventId,
		boolean assist) throws SystemException {
		for (CalEventAttendee calEventAttendee : findByEventUserAssist(userId,
				eventId, assist)) {
			remove(calEventAttendee);
		}
	}

	/**
	 * Removes all the cal event attendees where eventId = &#63; and assist = &#63; from the database.
	 *
	 * @param eventId the event ID
	 * @param assist the assist
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByEventAssist(long eventId, boolean assist)
		throws SystemException {
		for (CalEventAttendee calEventAttendee : findByEventAssist(eventId,
				assist)) {
			remove(calEventAttendee);
		}
	}

	/**
	 * Removes all the cal event attendees where userId = &#63; and assist = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param assist the assist
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserAssist(long userId, boolean assist)
		throws SystemException {
		for (CalEventAttendee calEventAttendee : findByUserAssist(userId, assist)) {
			remove(calEventAttendee);
		}
	}

	/**
	 * Removes all the cal event attendees where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUser(long userId) throws SystemException {
		for (CalEventAttendee calEventAttendee : findByUser(userId)) {
			remove(calEventAttendee);
		}
	}

	/**
	 * Removes all the cal event attendees where eventId = &#63; from the database.
	 *
	 * @param eventId the event ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByEvent(long eventId) throws SystemException {
		for (CalEventAttendee calEventAttendee : findByEvent(eventId)) {
			remove(calEventAttendee);
		}
	}

	/**
	 * Removes all the cal event attendees from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (CalEventAttendee calEventAttendee : findAll()) {
			remove(calEventAttendee);
		}
	}

	/**
	 * Returns the number of cal event attendees where userId = &#63; and eventId = &#63; and assist = &#63;.
	 *
	 * @param userId the user ID
	 * @param eventId the event ID
	 * @param assist the assist
	 * @return the number of matching cal event attendees
	 * @throws SystemException if a system exception occurred
	 */
	public int countByEventUserAssist(long userId, long eventId, boolean assist)
		throws SystemException {
		Object[] finderArgs = new Object[] { userId, eventId, assist };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_EVENTUSERASSIST,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_CALEVENTATTENDEE_WHERE);

			query.append(_FINDER_COLUMN_EVENTUSERASSIST_USERID_2);

			query.append(_FINDER_COLUMN_EVENTUSERASSIST_EVENTID_2);

			query.append(_FINDER_COLUMN_EVENTUSERASSIST_ASSIST_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(eventId);

				qPos.add(assist);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_EVENTUSERASSIST,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of cal event attendees where eventId = &#63; and assist = &#63;.
	 *
	 * @param eventId the event ID
	 * @param assist the assist
	 * @return the number of matching cal event attendees
	 * @throws SystemException if a system exception occurred
	 */
	public int countByEventAssist(long eventId, boolean assist)
		throws SystemException {
		Object[] finderArgs = new Object[] { eventId, assist };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_EVENTASSIST,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CALEVENTATTENDEE_WHERE);

			query.append(_FINDER_COLUMN_EVENTASSIST_EVENTID_2);

			query.append(_FINDER_COLUMN_EVENTASSIST_ASSIST_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(eventId);

				qPos.add(assist);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_EVENTASSIST,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of cal event attendees where userId = &#63; and assist = &#63;.
	 *
	 * @param userId the user ID
	 * @param assist the assist
	 * @return the number of matching cal event attendees
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserAssist(long userId, boolean assist)
		throws SystemException {
		Object[] finderArgs = new Object[] { userId, assist };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERASSIST,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CALEVENTATTENDEE_WHERE);

			query.append(_FINDER_COLUMN_USERASSIST_USERID_2);

			query.append(_FINDER_COLUMN_USERASSIST_ASSIST_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(assist);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERASSIST,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of cal event attendees where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching cal event attendees
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUser(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USER,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CALEVENTATTENDEE_WHERE);

			query.append(_FINDER_COLUMN_USER_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USER,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of cal event attendees where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @return the number of matching cal event attendees
	 * @throws SystemException if a system exception occurred
	 */
	public int countByEvent(long eventId) throws SystemException {
		Object[] finderArgs = new Object[] { eventId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_EVENT,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CALEVENTATTENDEE_WHERE);

			query.append(_FINDER_COLUMN_EVENT_EVENTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(eventId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_EVENT,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of cal event attendees.
	 *
	 * @return the number of cal event attendees
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CALEVENTATTENDEE);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the cal event attendee persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hook.calendar.model.CalEventAttendee")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<CalEventAttendee>> listenersList = new ArrayList<ModelListener<CalEventAttendee>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<CalEventAttendee>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(CalEventAttendeeImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = CalEventAttendeePersistence.class)
	protected CalEventAttendeePersistence calEventAttendeePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_CALEVENTATTENDEE = "SELECT calEventAttendee FROM CalEventAttendee calEventAttendee";
	private static final String _SQL_SELECT_CALEVENTATTENDEE_WHERE = "SELECT calEventAttendee FROM CalEventAttendee calEventAttendee WHERE ";
	private static final String _SQL_COUNT_CALEVENTATTENDEE = "SELECT COUNT(calEventAttendee) FROM CalEventAttendee calEventAttendee";
	private static final String _SQL_COUNT_CALEVENTATTENDEE_WHERE = "SELECT COUNT(calEventAttendee) FROM CalEventAttendee calEventAttendee WHERE ";
	private static final String _FINDER_COLUMN_EVENTUSERASSIST_USERID_2 = "calEventAttendee.userId = ? AND ";
	private static final String _FINDER_COLUMN_EVENTUSERASSIST_EVENTID_2 = "calEventAttendee.eventId = ? AND ";
	private static final String _FINDER_COLUMN_EVENTUSERASSIST_ASSIST_2 = "calEventAttendee.assist = ?";
	private static final String _FINDER_COLUMN_EVENTASSIST_EVENTID_2 = "calEventAttendee.eventId = ? AND ";
	private static final String _FINDER_COLUMN_EVENTASSIST_ASSIST_2 = "calEventAttendee.assist = ?";
	private static final String _FINDER_COLUMN_USERASSIST_USERID_2 = "calEventAttendee.userId = ? AND ";
	private static final String _FINDER_COLUMN_USERASSIST_ASSIST_2 = "calEventAttendee.assist = ?";
	private static final String _FINDER_COLUMN_USER_USERID_2 = "calEventAttendee.userId = ?";
	private static final String _FINDER_COLUMN_EVENT_EVENTID_2 = "calEventAttendee.eventId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "calEventAttendee.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CalEventAttendee exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CalEventAttendee exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(CalEventAttendeePersistenceImpl.class);
	private static CalEventAttendee _nullCalEventAttendee = new CalEventAttendeeImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<CalEventAttendee> toCacheModel() {
				return _nullCalEventAttendeeCacheModel;
			}
		};

	private static CacheModel<CalEventAttendee> _nullCalEventAttendeeCacheModel = new CacheModel<CalEventAttendee>() {
			public CalEventAttendee toEntityModel() {
				return _nullCalEventAttendee;
			}
		};
}