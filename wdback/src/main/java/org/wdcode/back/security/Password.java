package org.wdcode.back.security;

import org.springframework.security.crypto.password.PasswordEncoder;
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
	public String encode(CharSequence rawPassword) {
		return Digest.absolute(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return encode(rawPassword.toString()).equals(encodedPassword);
	}
}
