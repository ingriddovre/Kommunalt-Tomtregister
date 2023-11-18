import java.util.ArrayList;
/**
 * This class is used as a register for all properties.
 * The register is implemented as an ArrayList, and it is initialized in the constructor.
 * The ArrayList is not fixed in size, therefor, this register can be used to register as many
 * properties as needed. It would be more relevant to use a fixed size array if the municipality
 * only has area for a certain amount of properties. Which in this case, it does not have.
 * The PropertyRegister class has a method for adding new properties, delete a property from the
 * register, return all properties registered, search for a specific property using the municipality
 * number, the lot number and the section number. It also has a method for checking if a property
 * already exists in the register, for cases where we want to add a new property.

 * @author Ingrid Midtmoen Døvre
 * @version 07.11.2023 - 18.11.2023
 */
public class PropertyRegister {
    ArrayList<Property> allProperties;

    /**
     * Constructor for the PropertyRegister class initializes the ArrayList allProperties.
     * The allProperties list is a dynamic list that contains all properties in the register. I
     * choose to use an ArrayList because it is not fixed in size, and in this case, that is the
     * most relevant so the register can hold as many properties as needed. The municipality does
     * not have a fixed amount of properties. All properties will be added to this ArrayList
     * when they are created.
     * @param allProperties an ArrayList of all properties registered in the register.
     */
    public PropertyRegister() {
        allProperties = new ArrayList<>();
    }

    /**
     * This public accessor method returns the ArrayList allProperties. It is used to access the
     * ArrayList outside the class.
     * @return allProperties an ArrayList of all properties registered in the register.
     */
    public ArrayList<Property> getAllProperties() {
        return allProperties;
    }

    /**
     * This method is used to register a new property in the register. It takes in the parameters:
     * @param lotNumber the lot number of the property.
     * @param sectionNumber the section number of the property.
     * @param name the name of the property. Not all properties have a name.
     * @param area the area of the property.
     * @param nameOfOwner the name of the owner of the property.
     * After creating a new property, it is added to the ArrayList allProperties.
     */
    public void RegisterNewProperty(int lotNumber, int sectionNumber, String name, double area, String nameOfOwner) {
        Property newProperty = new Property(lotNumber, sectionNumber, name, area, nameOfOwner);
        allProperties.add(newProperty);
    }

    /**
     * This method is used to search for a specific property in the register. It takes in the
     * following parameters to identify the correct property:
     * @param municipalityNumber the municipality number of the property.
     * @param lotNumber the lot number of the property.
     * @param sectionNumber the section number of the property.
     * @return foundProperty a String containing the property that was searched for.
     */
    public String searchForProperty(int municipalityNumber, int lotNumber, int sectionNumber) {
        String foundProperty = "";
        int howManyProperties = 0;
        for (Property property : allProperties) {
            if (property.getMunicipalityNumber() == municipalityNumber &&
                property.getLotNumber() == lotNumber &&
                property.getSectionNumber() == sectionNumber) {
                howManyProperties++;
                foundProperty = property + "\n";
            }
        }
        if (foundProperty.isEmpty() && howManyProperties == 0) {
            return null;
        }
        return foundProperty;
    }

    /**
     * This method is used to check if a property already exists in the register. It takes in the
     * following parameters to identify the correct property:
     * @param municipalityNumber the municipality number of the property.
     * @param lotNumber the lot number of the property.
     * @param sectionNumber the section number of the property.
     * @return 1 if the property exists, 0 if it does not exist. This is used in the user interface
     * to check if it is necessary to continue with the process of a method or not.
     */
    public int checkIfPropertyExists(int municipalityNumber, int lotNumber, int sectionNumber) {
        boolean exists = false;
        for (Property property : allProperties) {
            if (property.getMunicipalityNumber() == municipalityNumber && property.getLotNumber() == lotNumber && property.getSectionNumber() == sectionNumber) {
                exists = true;
                break;
            }
        }
        if (exists) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * This method is used to delete a property from the register. It takes in the following
     * parameters to identify the correct property:
     * @param nameOfOwner the name of the owner of the property.
     * @param lotNumber the lot number of the property.
     * @param sectionNumber the section number of the property.
     * @return 1 if the property was deleted, 0 if it was not deleted. This is used in the user
     * interface to identify if the property was deleted or not, and to print out the correct
     * message to the user.
     */
    public int deleteProperty(String nameOfOwner, int lotNumber, int sectionNumber) {
        boolean exists = false;
        for (Property property : allProperties) {
            String propertyName = property.getNameOfOwner();
            int propertyLotNumber = property.getLotNumber();
            int propertySectionNumber = property.getSectionNumber();
            if (propertyName.equalsIgnoreCase(nameOfOwner) && propertyLotNumber == lotNumber && propertySectionNumber == sectionNumber) {
                allProperties.remove(property);
                exists = true;
                break;
            }
        }
        if (exists) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * This method is used to return all properties in the register. It shows a list of all
     * registered properties.
     * @return mapOfProperties a String containing all properties in the register.
     */
    public String showAllProperties() {
    StringBuilder mapOfProperties = new StringBuilder();
    mapOfProperties.append("----------------------------------------------------------------------------------------------------------------------------------------------\n");
    mapOfProperties.append(String.format("| %-19s | %-17s | %-10s | %-14s | %-20s | %-10s | %-30s |\n",
            "Municipality number", "Municipality name", "Lot number", "Section number", "Name", "Area", "Name of owner"));
    mapOfProperties.append("----------------------------------------------------------------------------------------------------------------------------------------------\n");
    for (Property property : allProperties) { //
        mapOfProperties.append(String.format("| %-19s | %-17s | %-10s | %-14s | %-20s | %-10s | %-30s |\n",
                property.getMunicipalityNumber(), property.getMunicipalityName(), property.getLotNumber(),
                property.getSectionNumber(), property.getName(), property.getArea(), property.getNameOfOwner()));
    }
    mapOfProperties.append("----------------------------------------------------------------------------------------------------------------------------------------------");
    return mapOfProperties.toString();
    }

    /**
     * This method is used to calculate the average size of all properties in the register.
     * It gets the area of each property and adds it to the totalArea variable. It then divides
     * the totalArea with the number of properties in the register to get the average size of
     * all properties.
     * @return totalArea / howManyProperties, the average size of all properties in the register.
     */
    public Double calculateAvgSizeOfArea() {
        double totalArea = 0;
        int howManyProperties = numberOfProperties();
        for (Property property : allProperties) {
            totalArea += property.getArea();
        }
        return totalArea / howManyProperties;
    }

    /**
     * This method is used to calculate the total of properties in the register.
     * It counts how many property objects there are in the ArrayList allProperties.
     * @return allProperties.size(), the total number of properties in the register.
     */
    public int numberOfProperties() {
        return allProperties.size();
    }

    /**
     * This method is used to search for properties with a specific lot number.
     * It takes in the following parameter:
     * @param lotNumber the lot number of the property.
     * @return propertiesWithLotNumber a String containing all properties with the lot number
     * searched for. The String is similar to the list in the showAllProperties() method.
     */
    public String propertiesWithLotNumber(int lotNumber) {
        StringBuilder propertiesWithLotNumber = new StringBuilder();
        int howManyProperties = 0;
        propertiesWithLotNumber.append("----------------------------------------------------------------------------------------------------------------------------------------------\n");
        propertiesWithLotNumber.append(String.format("| %-19s | %-17s | %-10s | %-14s | %-20s | %-10s | %-30s |\n",
            "Municipality number", "Municipality name", "Lot number", "Section number", "Name", "Area", "Name of owner"));
        propertiesWithLotNumber.append("----------------------------------------------------------------------------------------------------------------------------------------------\n");
        for (Property property : allProperties) {
            if (lotNumber == property.getLotNumber()) {
                howManyProperties++;
                propertiesWithLotNumber.append(String.format("| %-19s | %-17s | %-10s | %-14s | %-20s | %-10s | %-30s |\n",
                    property.getMunicipalityNumber(), property.getMunicipalityName(), property.getLotNumber(),
                    property.getSectionNumber(), property.getName(), property.getArea(), property.getNameOfOwner()));
            }
        }
        propertiesWithLotNumber.append("----------------------------------------------------------------------------------------------------------------------------------------------\n");
        if (howManyProperties == 0) {
            return null;
        } else {
            return propertiesWithLotNumber.toString();
        }
    }
}
