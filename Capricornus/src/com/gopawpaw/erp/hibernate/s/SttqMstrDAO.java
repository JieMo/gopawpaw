package com.gopawpaw.erp.hibernate.s;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * SttqMstr entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.gopawpaw.erp.hibernate.s.SttqMstr
 * @author MyEclipse Persistence Tools
 */

public class SttqMstrDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(SttqMstrDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(SttqMstr transientInstance) {
		log.debug("saving SttqMstr instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SttqMstr persistentInstance) {
		log.debug("deleting SttqMstr instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SttqMstr findById(com.gopawpaw.erp.hibernate.s.SttqMstrId id) {
		log.debug("getting SttqMstr instance with id: " + id);
		try {
			SttqMstr instance = (SttqMstr) getHibernateTemplate().get(
					"com.gopawpaw.erp.hibernate.s.SttqMstr", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SttqMstr instance) {
		log.debug("finding SttqMstr instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding SttqMstr instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SttqMstr as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all SttqMstr instances");
		try {
			String queryString = "from SttqMstr";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SttqMstr merge(SttqMstr detachedInstance) {
		log.debug("merging SttqMstr instance");
		try {
			SttqMstr result = (SttqMstr) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SttqMstr instance) {
		log.debug("attaching dirty SttqMstr instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SttqMstr instance) {
		log.debug("attaching clean SttqMstr instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SttqMstrDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SttqMstrDAO) ctx.getBean("SttqMstrDAO");
	}
}