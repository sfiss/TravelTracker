package de.sfiss.tt.persistence.jpa.util;

import java.lang.reflect.Method;
import java.util.Collection;

import javax.inject.Named;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import de.sfiss.tt.persistence.jpa.EntityDAOBase;

@Named
public class FinderAdvisor extends AbstractPointcutAdvisor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3614407950831593984L;

	private Advice advice = new MethodInterceptor() {

		@Override
		public Object invoke(MethodInvocation methodInvocation)
				throws Throwable {
			Method method = methodInvocation.getMethod();
			Object proxy = methodInvocation.getThis();
			QueryFinder finder = method.getAnnotation(QueryFinder.class);

			String namedQuery = finder.value();

			boolean single = method.getReturnType() != Collection.class;

			return ((EntityDAOBase<?>) proxy).findByQuery(namedQuery, single);
		}
	};

	private Pointcut pointcut = new StaticMethodMatcherPointcut() {

		@Override
		public boolean matches(Method method, Class<?> clazz) {
			return clazz.getSuperclass() == EntityDAOBase.class
					&& method.isAnnotationPresent(QueryFinder.class);
		}
	};

	@Override
	public Pointcut getPointcut() {
		return this.pointcut;
	}

	@Override
	public Advice getAdvice() {
		return this.advice;
	}

}
