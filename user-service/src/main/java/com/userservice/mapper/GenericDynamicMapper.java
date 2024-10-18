package com.userservice.mapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AllArgsConstructor
public class GenericDynamicMapper<S, T> implements GenericMapper<S, T> {

	private Class<S> sourceClass;

	private Class<T> targetClass;

	private static final Logger LOGGER = LoggerFactory.getLogger(GenericDynamicMapper.class);

	@Override
	public T toDto(S entity) {

		try {

			T dto = targetClass.getDeclaredConstructor().newInstance();

			copyProperties(entity, dto);

			return dto;

		}
		catch (Exception e) {
			LOGGER.warn("Some exception occurred. Error: {}", e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public S toEntity(T dto) {

		try {

			S entity = sourceClass.getDeclaredConstructor().newInstance();

			copyProperties(dto, entity);

			return entity;

		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			LOGGER.warn("Some error occurred. Error: {}", e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}

	private void copyProperties(Object source, Object target) {

		Field[] fields = source.getClass().getDeclaredFields();

		for (Field field : fields) {
			field.setAccessible(true);

			try {

				Object value = field.get(source);

				if (value != null) {

					Field targetField = target.getClass().getDeclaredField(field.getName());
					targetField.setAccessible(true);

					if (isCustomObject(value)) {

						Object targetValue = targetField.getType().getDeclaredConstructor().newInstance();

						copyProperties(value, targetValue);

						targetField.set(target, targetValue);

					}
					else {

						targetField.set(target, value);
					}
				}

			}
			catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException
					| InstantiationException | InvocationTargetException | NoSuchMethodException e) {
				LOGGER.warn("Error occurred. Error: {}", e.getMessage());
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	private boolean isCustomObject(Object obj) {

		return !(obj.getClass().isPrimitive() || obj instanceof String || obj instanceof Number
				|| obj instanceof Boolean || obj instanceof Character || obj instanceof Enum<?>
				|| obj instanceof Collection<?> || obj instanceof Map<?, ?>);
	}

}
