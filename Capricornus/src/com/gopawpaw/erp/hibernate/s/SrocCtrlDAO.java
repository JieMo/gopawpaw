package com.gopawpaw.erp.hibernate.s;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * SrocCtrl entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.gopawpaw.erp.hibernate.s.SrocCtrl
 * @author MyEclipse Persistence Tools
 */

public class SrocCtrlDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(SrocCtrlDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(SrocCtrl transientInstance) {
		log.debug("saving SrocCtrl instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SrocCtrl persistentInstance) {
		log.debug("deleting SrocCtrl instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SrocCtrl findById(java.lang.String id) {
		log.debug("getting SrocCtrl instance with id: " + id);
		try {
			SrocCtrl instance = (SrocCtrl) getHibernateTemplate().get(
					"com.gopawpaw.erp.hibernate.s.SrocCtrl", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SrocCtrl instance) {
		log.debug("finding SrocCtrl instance by example");
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
		log.debug("finding SrocCtrl instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SrocCtrl as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all SrocCtrl instances");
		try {
			String queryString = "from SrocCtrl";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SrocCtrl merge(SrocCtrl detachedInstance) {
		log.debug("merging SrocCtrl instance");
		try {
			SrocCtrl result = (SrocCtrl) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SrocCtrl instance) {
		log.debug("attaching dirty SrocCtrl instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SrocCtrl instance) {
		log.debug("attaching clean SrocCtrl instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SrocCtrlDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SrocCtrlDAO) ctx.getBean("SrocCtrlDAO");
	}
}