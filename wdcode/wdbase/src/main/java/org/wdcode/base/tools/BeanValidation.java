package org.wdcode.base.tools;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 * Bean Validation 验证框架 验证方法
 * @author WD
 * @since JDK6
 * @version 1.0 2013-9-9
 */
public final class BeanValidation {
	// 验证器
	private final static Validator	VALIDATOR;
	static {
		VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();
	}

	/**
	 * 验证方法 返回验证结果
	 * @param e 验证实体
	 * @return 验证结果
	 */
	public static <E> Set<ConstraintViolation<E>> validate(E e) {
		return VALIDATOR.validate(e);
	}
}
