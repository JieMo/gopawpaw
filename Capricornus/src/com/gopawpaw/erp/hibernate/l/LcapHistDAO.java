package com.gopawpaw.erp.hibernate.l;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * LcapHist entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.gopawpaw.erp.hibernate.l.LcapHist
 * @author MyEclipse Persistence Tools
 */

public class LcapHistDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(LcapHistDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(LcapHist transientInstance) {
		log.debug("saving LcapHist instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(LcapHist persistentInstance) {
		log.debug("deleting LcapHist instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LcapHist findById(com.gopawpaw.erp.hibernate.l.LcapHistId id) {
		log.debug("getting LcapHist instance with id: " + id);
		try {
			LcapHist instance = (LcapHist) getHibernateTemplate().get(
					"com.gopawpaw.erp.hibernate.l.LcapHist", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(LcapHist instance) {
		log.debug("finding LcapHist instance by example");
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
		log.debug("finding LcapHist instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from LcapHist as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all LcapHist instances");
		try {
			String queryString = "from LcapHist";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public LcapHist merge(LcapHist detachedInstance) {
		log.debug("merging LcapHist instance");
		try {
			LcapHist result = (LcapHist) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(LcapHist instance) {
		log.debug("attaching dirty LcapHist instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LcapHist instance) {
		log.debug("attaching clean LcapHist instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LcapHistDAO getFromApplicationContext(ApplicationContext ctx) {
		return (LcapHistDAO) ctx.getBean("LcapHistDAO");
	}
}