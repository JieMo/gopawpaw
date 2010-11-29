package com.gopawpaw.erp.hibernate.c;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * CsilMstr entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.gopawpaw.erp.hibernate.c.CsilMstr
 * @author MyEclipse Persistence Tools
 */

public class CsilMstrDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(CsilMstrDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(CsilMstr transientInstance) {
		log.debug("saving CsilMstr instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CsilMstr persistentInstance) {
		log.debug("deleting CsilMstr instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CsilMstr findById(com.gopawpaw.erp.hibernate.c.CsilMstrId id) {
		log.debug("getting CsilMstr instance with id: " + id);
		try {
			CsilMstr instance = (CsilMstr) getHibernateTemplate().get(
					"com.gopawpaw.erp.hibernate.c.CsilMstr", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CsilMstr instance) {
		log.debug("finding CsilMstr instance by example");
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
		log.debug("finding CsilMstr instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from CsilMstr as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all CsilMstr instances");
		try {
			String queryString = "from CsilMstr";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CsilMstr merge(CsilMstr detachedInstance) {
		log.debug("merging CsilMstr instance");
		try {
			CsilMstr result = (CsilMstr) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CsilMstr instance) {
		log.debug("attaching dirty CsilMstr instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CsilMstr instance) {
		log.debug("attaching clean CsilMstr instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CsilMstrDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CsilMstrDAO) ctx.getBean("CsilMstrDAO");
	}
}