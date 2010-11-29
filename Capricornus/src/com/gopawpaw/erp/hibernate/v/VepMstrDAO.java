package com.gopawpaw.erp.hibernate.v;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * VepMstr entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.gopawpaw.erp.hibernate.v.VepMstr
 * @author MyEclipse Persistence Tools
 */

public class VepMstrDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(VepMstrDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(VepMstr transientInstance) {
		log.debug("saving VepMstr instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(VepMstr persistentInstance) {
		log.debug("deleting VepMstr instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VepMstr findById(com.gopawpaw.erp.hibernate.v.VepMstrId id) {
		log.debug("getting VepMstr instance with id: " + id);
		try {
			VepMstr instance = (VepMstr) getHibernateTemplate().get(
					"com.gopawpaw.erp.hibernate.v.VepMstr", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(VepMstr instance) {
		log.debug("finding VepMstr instance by example");
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
		log.debug("finding VepMstr instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from VepMstr as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all VepMstr instances");
		try {
			String queryString = "from VepMstr";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public VepMstr merge(VepMstr detachedInstance) {
		log.debug("merging VepMstr instance");
		try {
			VepMstr result = (VepMstr) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(VepMstr instance) {
		log.debug("attaching dirty VepMstr instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VepMstr instance) {
		log.debug("attaching clean VepMstr instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static VepMstrDAO getFromApplicationContext(ApplicationContext ctx) {
		return (VepMstrDAO) ctx.getBean("VepMstrDAO");
	}
}