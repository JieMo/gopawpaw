package com.gopawpaw.erp.hibernate.r;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * RndMstr entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.gopawpaw.erp.hibernate.r.RndMstr
 * @author MyEclipse Persistence Tools
 */

public class RndMstrDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(RndMstrDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(RndMstr transientInstance) {
		log.debug("saving RndMstr instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RndMstr persistentInstance) {
		log.debug("deleting RndMstr instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RndMstr findById(com.gopawpaw.erp.hibernate.r.RndMstrId id) {
		log.debug("getting RndMstr instance with id: " + id);
		try {
			RndMstr instance = (RndMstr) getHibernateTemplate().get(
					"com.gopawpaw.erp.hibernate.r.RndMstr", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(RndMstr instance) {
		log.debug("finding RndMstr instance by example");
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
		log.debug("finding RndMstr instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from RndMstr as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all RndMstr instances");
		try {
			String queryString = "from RndMstr";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public RndMstr merge(RndMstr detachedInstance) {
		log.debug("merging RndMstr instance");
		try {
			RndMstr result = (RndMstr) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RndMstr instance) {
		log.debug("attaching dirty RndMstr instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RndMstr instance) {
		log.debug("attaching clean RndMstr instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RndMstrDAO getFromApplicationContext(ApplicationContext ctx) {
		return (RndMstrDAO) ctx.getBean("RndMstrDAO");
	}
}