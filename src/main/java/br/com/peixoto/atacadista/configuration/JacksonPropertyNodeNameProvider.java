package br.com.peixoto.atacadista.configuration;


import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import org.hibernate.validator.spi.nodenameprovider.JavaBeanProperty;
import org.hibernate.validator.spi.nodenameprovider.Property;
import org.hibernate.validator.spi.nodenameprovider.PropertyNodeNameProvider;

public class JacksonPropertyNodeNameProvider implements PropertyNodeNameProvider {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String getName(Property property) {
        if (property instanceof JavaBeanProperty) {
            final JavaBeanProperty javaBeanProperty = (JavaBeanProperty) property;
            return getJavaBeanPropertyName(javaBeanProperty);
        }

        return getDefaultName(property);
    }

    private String getJavaBeanPropertyName(JavaBeanProperty property) {
        final JavaType type = objectMapper.constructType(property.getDeclaringClass());
        final BeanDescription desc = objectMapper.getSerializationConfig().introspect(type);

        return desc.findProperties()
                .stream()
                .filter(prop -> prop.getInternalName().equals(property.getName()))
                .map(BeanPropertyDefinition::getName)
                .findFirst()
                .orElseGet(property::getName);
    }

    private String getDefaultName(Property property) {
        return property.getName();
    }
}
