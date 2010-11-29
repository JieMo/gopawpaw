package com.gopawpaw.erp.hibernate.a;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * AtdcMstr entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.gopawpaw.erp.hibernate.a.AtdcMstr
 * @author MyEclipse Persistence Tools
 */

public class AtdcMstrDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(AtdcMstrDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(AtdcMstr transientInstance) {
		log.debug("saving AtdcMstr instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AtdcMstr persistentInstance) {
		log.debug("deleting AtdcMstr instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AtdcMstr findById(java.lang.String id) {
		log.debug("getting AtdcMstr instance with id: " + id);
		try {
			AtdcMstr instance = (AtdcMstr) getHibernateTemplate().get(
					"com.gopawpaw.erp.hibernate.a.AtdcMstr", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(AtdcMstr instance) {
		log.debug("finding AtdcMstr instance by example");
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
		log.debug("finding AtdcMstr instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from AtdcMstr as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all AtdcMstr instances");
		try {
			String queryString = "from AtdcMstr";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AtdcMstr merge(AtdcMstr detachedInstance) {
		log.debug("merging AtdcMstr instance");
		try {
			AtdcMstr result = (AtdcMstr) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AtdcMstr instance) {
		log.debug("attaching dirty AtdcMstr instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AtdcMstr instance) {
		log.debug("attaching clean AtdcMstr instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AtdcMstrDAO getFromApplicationContext(ApplicationContext ctx) {
		return (AtdcMstrDAO) ctx.getBean("AtdcMstrDAO");
	}
}