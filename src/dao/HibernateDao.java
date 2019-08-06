package dao;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;

public class HibernateDao extends HibernateTemplate {

	public <T> T getByUK(final Class<T> type, final String field,
			final Object value) {
		return this.execute(new HibernateCallback<T>() {
			@Override
			public T doInHibernate(Session session) throws HibernateException {

				return (T) session
						.createQuery(
								"from " + type.getName() + " where " + field
										+ "=?").setParameter(0, value)
						.uniqueResult();
			}
		});

	}

	public <T> boolean exists(final Class<T> type, final String property,
			final Object value) {
		return this.execute(new HibernateCallback<Boolean>() {
			@Override
			public Boolean doInHibernate(Session session)
					throws HibernateException {
				Number result = (Number) session
						.createQuery(
								"select count(*) from " + type.getName()
										+ " where " + property + "=?")
						.setParameter(0, value).uniqueResult();
				return result.intValue() > 0;
			}
		});
	}

	public <T> T selectOne(final Object hql, final Object... param)
			throws Exception {
		return this.execute(new HibernateCallback<T>() {
			@Override
			public T doInHibernate(Session session) throws HibernateException {
				Query q = session.createQuery(hql.toString());
				for (int i = 0; i < param.length; i++) {
					q.setParameter(i, param[i]);
				}
				return (T) q.uniqueResult();
			}
		});
	}

	public <T> T selectOne(final Object hql, final Map<String, Object> param)
			throws Exception {
		return this.execute(new HibernateCallback<T>() {
			@Override
			public T doInHibernate(Session session) throws HibernateException {
				Query q = session.createQuery(hql.toString());
				for (String key : param.keySet()) {
					q.setParameter(key, param.get(key));
				}
				return (T) q.uniqueResult();
			}
		});
	}

	public <T> T select(final Object hql) throws Exception {
		return this.execute(new HibernateCallback<T>() {
			@Override
			public T doInHibernate(Session session) throws HibernateException {
				Query q = session.createQuery(hql.toString());
				return (T) q.uniqueResult();
			}
		});
	}

	public <T> List<T> selectAll(final Object hql,
			final Map<String, Object> param) throws Exception {

		return this.execute(new HibernateCallback<List<T>>() {
			@Override
			public List<T> doInHibernate(Session session)
					throws HibernateException {
				Query q = session.createQuery(hql.toString());
				for (String key : param.keySet()) {
					q.setParameter(key, param.get(key));
				}
				return q.list();
			}
		});
	}

	public <T> List<T> selectAll(final Object hql, final Object... param)
			throws Exception {

		return this.execute(new HibernateCallback<List<T>>() {
			@Override
			public List<T> doInHibernate(Session session)
					throws HibernateException {
				Query q = session.createQuery(hql.toString());
				for (int i = 0; i < param.length; i++) {
					q.setParameter(i, param[i]);
				}
				return q.list();
			}
		});
	}

	public <T> List<T> selectPagination(final int pageNo, final int pageSize,
			final Object hql, final Object... param) throws Exception {
		return this.execute(new HibernateCallback<List<T>>() {
			@Override
			public List<T> doInHibernate(Session session)
					throws HibernateException {

				Query q = session.createQuery(hql.toString());
				for (int i = 0; i < param.length; i++) {
					q.setParameter(i, param[i]);
				}
				q.setFirstResult((pageNo - 1) * pageSize);
				q.setMaxResults(pageSize);
				return q.list();
			}
		});
	}

	public <T> List<T> selectPagination(final int pageNo, final int pageSize,
			final Object hql, final Map<String, Object> param) throws Exception {
		return this.execute(new HibernateCallback<List<T>>() {
			@Override
			public List<T> doInHibernate(Session session)
					throws HibernateException {

				Query q = session.createQuery(hql.toString());
				for (String key : param.keySet()) {
					q.setParameter(key, param.get(key));
				}
				q.setFirstResult((pageNo - 1) * pageSize);
				q.setMaxResults(pageSize);
				return q.list();
			}
		});
	}
}
