package org.wdcode.back.security;

import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.wdcode.common.crypto.Digest; 

/**
 * 密码验证
 * @author WD
 * @since JDK6
 * @version 1.0 2012-08-24
 */
@Component
public final class Password implements PasswordEncoder {
	@Override
	public String encodePassword(String rawPass, Object salt) {
		return Digest.absolute(rawPass);
	}

	@Override
	public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
		return encodePassword(rawPass, salt).equals(encPass);
	}
}
