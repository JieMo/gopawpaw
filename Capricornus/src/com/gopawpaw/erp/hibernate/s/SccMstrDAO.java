package com.gopawpaw.erp.hibernate.s;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * SccMstr entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.gopawpaw.erp.hibernate.s.SccMstr
 * @author MyEclipse Persistence Tools
 */

public class SccMstrDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(SccMstrDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(SccMstr transientInstance) {
		log.debug("saving SccMstr instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SccMstr persistentInstance) {
		log.debug("deleting SccMstr instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SccMstr findById(com.gopawpaw.erp.hibernate.s.SccMstrId id) {
		log.debug("getting SccMstr instance with id: " + id);
		try {
			SccMstr instance = (SccMstr) getHibernateTemplate().get(
					"com.gopawpaw.erp.hibernate.s.SccMstr", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SccMstr instance) {
		log.debug("finding SccMstr instance by example");
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
		log.debug("finding SccMstr instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SccMstr as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all SccMstr instances");
		try {
			String queryString = "from SccMstr";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SccMstr merge(SccMstr detachedInstance) {
		log.debug("merging SccMstr instance");
		try {
			SccMstr result = (SccMstr) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SccMstr instance) {
		log.debug("attaching dirty SccMstr instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SccMstr instance) {
		log.debug("attaching clean SccMstr instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SccMstrDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SccMstrDAO) ctx.getBean("SccMstrDAO");
	}
}