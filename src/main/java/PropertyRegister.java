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
    public String checkIfPropertyExists(int municipalityNumber, int lotNumber, int sectionNumber) {
        boolean exists = false;
        for (Property property : allProperties) {
            if (property.getMunicipalityNumber() == 0165 && property.getLotNumber() == 1 && property.getSectionNumber() == 1) {
                exists = true;
                break;
            }
        }
        if (exists) {
            return "Property already exists";
        } else {
            return null;
        }
    }
}
