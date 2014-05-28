package de.sfiss.tt.persistence.exception;

import javax.inject.Named;
import javax.persistence.PersistenceException;

import lombok.extern.java.Log;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.dao.DataAccessException;

@Named
@Log
public class ExceptionTranslationAdvisor extends AbstractPointcutAdvisor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3614407950831593984L;

	private Advice advice = new PersistenceThrowsAdvice();

	public class PersistenceThrowsAdvice implements ThrowsAdvice {

		public void afterThrowing(PersistenceException ex) throws Throwable {
			log.info("DataStorageException " + ex.getClass());
			throw new DataStorageException();
		}

		public void afterThrowing(DataAccessException ex) throws Throwable {
			log.info("DataStorageException " + ex.getClass());
			throw new DataStorageException();
		}
		
		public void afterThrowing(NullPointerException ex) throws Throwable {
			log.info("DataStorageException " + ex.getClass());
			throw new DataStorageException();
		}
		
		public void afterThrowing(IllegalArgumentException ex) throws Throwable {
			log.info("DataStorageException " + ex.getClass());
			throw new DataStorageException();
		}
	};

	private Pointcut pointcut = new AspectJExpressionPointcut();

	{
		((AspectJExpressionPointcut) pointcut)
				.setExpression("execution(* de.sfiss.tt.persistence..*(..))");
	}

	@Override
	public Pointcut getPointcut() {
		return this.pointcut;
	}

	@Override
	public Advice getAdvice() {
		return this.advice;
	}

}
