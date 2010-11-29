package com.gopawpaw.erp.hibernate.m;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * MuMstr entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.gopawpaw.erp.hibernate.m.MuMstr
 * @author MyEclipse Persistence Tools
 */

public class MuMstrDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(MuMstrDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(MuMstr transientInstance) {
		log.debug("saving MuMstr instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MuMstr persistentInstance) {
		log.debug("deleting MuMstr instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MuMstr findById(com.gopawpaw.erp.hibernate.m.MuMstrId id) {
		log.debug("getting MuMstr instance with id: " + id);
		try {
			MuMstr instance = (MuMstr) getHibernateTemplate().get(
					"com.gopawpaw.erp.hibernate.m.MuMstr", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MuMstr instance) {
		log.debug("finding MuMstr instance by example");
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
		log.debug("finding MuMstr instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from MuMstr as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all MuMstr instances");
		try {
			String queryString = "from MuMstr";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MuMstr merge(MuMstr detachedInstance) {
		log.debug("merging MuMstr instance");
		try {
			MuMstr result = (MuMstr) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MuMstr instance) {
		log.debug("attaching dirty MuMstr instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MuMstr instance) {
		log.debug("attaching clean MuMstr instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MuMstrDAO getFromApplicationContext(ApplicationContext ctx) {
		return (MuMstrDAO) ctx.getBean("MuMstrDAO");
	}
}