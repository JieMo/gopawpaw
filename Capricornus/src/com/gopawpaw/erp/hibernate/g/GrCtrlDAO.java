package com.gopawpaw.erp.hibernate.g;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * GrCtrl entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.gopawpaw.erp.hibernate.g.GrCtrl
 * @author MyEclipse Persistence Tools
 */

public class GrCtrlDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(GrCtrlDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(GrCtrl transientInstance) {
		log.debug("saving GrCtrl instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(GrCtrl persistentInstance) {
		log.debug("deleting GrCtrl instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public GrCtrl findById(java.lang.String id) {
		log.debug("getting GrCtrl instance with id: " + id);
		try {
			GrCtrl instance = (GrCtrl) getHibernateTemplate().get(
					"com.gopawpaw.erp.hibernate.g.GrCtrl", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(GrCtrl instance) {
		log.debug("finding GrCtrl instance by example");
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
		log.debug("finding GrCtrl instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from GrCtrl as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all GrCtrl instances");
		try {
			String queryString = "from GrCtrl";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public GrCtrl merge(GrCtrl detachedInstance) {
		log.debug("merging GrCtrl instance");
		try {
			GrCtrl result = (GrCtrl) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(GrCtrl instance) {
		log.debug("attaching dirty GrCtrl instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(GrCtrl instance) {
		log.debug("attaching clean GrCtrl instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static GrCtrlDAO getFromApplicationContext(ApplicationContext ctx) {
		return (GrCtrlDAO) ctx.getBean("GrCtrlDAO");
	}
}