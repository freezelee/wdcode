package org.wdcode.web.socket.message;

import java.lang.reflect.Field;
import java.util.List;

import org.wdcode.common.interfaces.BytesBean;
import org.wdcode.common.lang.Bytes;
import org.wdcode.common.util.BeanUtil;

/**
 * Socket 传递消息实体
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-19
 */
public class Message implements BytesBean {
	// 通信ID
	protected int	id;

	@Override
	public byte[] toBytes() {
		return Bytes.toBytes(BeanUtil.getFieldValues(this));
	}

	@Override
	public BytesBean toBean(byte[] b) {
		// 获得全部字段
		List<Field> fields = BeanUtil.getFields(this.getClass());
		// 偏移
		int offset = 0;
		// 循环设置字段值
		for (Field field : fields) {
			// 获得字段类型
			Class<?> type = field.getType();
			// 声明字段值
			Object value = null;
			// 转换字节值
			if (type.equals(Integer.class) || type.equals(int.class)) {
				BeanUtil.setFieldValue(this, field, Bytes.toInt(b, offset));
				offset += 4;
			} else if (type.equals(Long.class) || type.equals(long.class)) {
				BeanUtil.setFieldValue(this, field, Bytes.toLong(b, offset));
				offset += 8;
			} else if (type.equals(Double.class) || type.equals(double.class)) {
				BeanUtil.setFieldValue(this, field, Bytes.toDouble(b, offset));
				offset += 8;
			} else if (type.equals(Float.class) || type.equals(float.class)) {
				BeanUtil.setFieldValue(this, field, Bytes.toFloat(b, offset));
				offset += 4;
			} else if (type.equals(Short.class) || type.equals(short.class)) {
				BeanUtil.setFieldValue(this, field, Bytes.toShort(b, offset));
				offset += 2;
			} else if (type.equals(Byte.class) || type.equals(byte.class)) {
				BeanUtil.setFieldValue(this, field, Bytes.toByte(b, offset));
				offset += 1;
			} else if (type.equals(Boolean.class) || type.equals(boolean.class)) {
				BeanUtil.setFieldValue(this, field, Bytes.toBoolean(b, offset));
				offset += 1;
			} else if (type.equals(String.class)) {
				String s = Bytes.toString(b, offset);
				BeanUtil.setFieldValue(this, field, s);
				offset += s.length();
			}
			// 设置字段值
			BeanUtil.setFieldValue(this, field, value);
		}
		// 返回本身
		return this;
	}

	/**
	 * 获得通信ID
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * 设置通信ID
	 * @param id 通信ID
	 */
	public void setId(int id) {
		this.id = id;
	}
}
