package de.codecentric.workshops.jpaworkshop.jpa.datatypes;

import de.codecentric.workshops.jpaworkshop.jpa.datatypes.zipcode.Zipcode;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.util.StringUtils;

@Converter(autoApply = true)
public class ZipcodeConverter implements AttributeConverter<Zipcode, String> {
	@Override
	public String convertToDatabaseColumn(Zipcode zipcode) {
		if (zipcode == null) {
			return null;
		} else {
			return zipcode.getValue();
		}
	}

	@Override
	public Zipcode convertToEntityAttribute(String fromDb) {
		if (StringUtils.hasText(fromDb)) {
			return Zipcode.of(fromDb);
		} else {
			return null;
		}
	}
}
