import java.util.ArrayList;

public class PropertyRegister {
    ArrayList<Property> allProperties;

    public PropertyRegister() {
        allProperties = new ArrayList<Property>();
    }

    public void RegisterNewProperty(int municipalityNumber, String municipalityName, int lotNumber, int sectionNumber, String name, double area, String nameOfOwner) {
        Property newProperty = new Property(municipalityNumber, municipalityName, lotNumber, sectionNumber, name, area, nameOfOwner);
        allProperties.add(newProperty);
    }
}
