package br.edu.ifpb.dac.projeto.util.jpa;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * Outra implementação XXX:
 * 
 * <pre>
 * http://github.com/matzew/hbase-jpa-jsf/blob/master/src/main/java/net/wessendorf/cdi/transactional/TransactionalInterceptor.java
 * </pre>
 * 
 * (taken from the Apache OpenWebBeans project's reservation example)
 * 
 * @author jaindsonvs
 */
@Interceptor
@TransacionalCdi
public class TransacionalInterceptor {

	@Inject
	private EntityManager em;

	@AroundInvoke
	public Object intercept(InvocationContext ctx) throws Exception {

		Object resultado = null;

		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		try {
			resultado = ctx.proceed();
			transaction.commit();
		} catch (Exception pe) {
			transaction.rollback();
			throw pe;
		}

		return resultado;
	}

}
