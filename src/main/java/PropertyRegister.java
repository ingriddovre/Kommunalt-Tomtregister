/**
 * This class is used as a register for all properties.
 * The register is implemented as an ArrayList, and it is initialized in the constructor. The ArrayList is not fixed in size, therefor, this register
 * can be used to register as many properties as needed. It would be more relevant to use a fixed size array if the municipality
 * only has area for a certain amount of properties. Which in this case, it does not have.
 * The PropertyRegister class has a method for adding new properties, delete a property from the register, return all properties registered,
 * search for a specific property using the municipality number, the lot number and the section number.
 * It also has a method for checking if a property already exists in the register, for cases where we want to add a new property.
 *
 * The PropertyRegister class uses the following attribute:
 * @param allProperties an ArrayList of all the properties registered. All properties will be added into this ArrayList when they are created.
 *                      The ArrayList is initialized in the constructor, and it is not fixed in size, therefore the register can
 *                      hold as many properties as needed.
 *
 * @author Ingrid Midtmoen Døvre
 * @version 07.11.2023 - 13.11.2023
 */

import java.util.ArrayList;
import java.util.Arrays;

public class PropertyRegister {
    ArrayList<Property> allProperties;
    public PropertyRegister() {
        allProperties = new ArrayList<>();
    }
    public ArrayList<Property> getAllProperties() {
        return allProperties;
    }
    public void RegisterNewProperty(int lotNumber, int sectionNumber, String name, double area, String nameOfOwner) {
        Property newProperty = new Property(lotNumber, sectionNumber, name, area, nameOfOwner);
        allProperties.add(newProperty);
    }
    public String searchForProperty(int municipalityNumber, int lotNumber, int sectionNumber) {
        String foundProperty = "";
        for (Property property : allProperties) {
            if (property.getMunicipalityNumber() == municipalityNumber &&
                property.getLotNumber() == lotNumber &&
                property.getSectionNumber() == sectionNumber) {
                foundProperty = property.toString();
            }break;
        }
        if (foundProperty.isEmpty()) {
            return null;
        }
        return foundProperty;
    }

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

    public int deleteProperty(String nameOfOwner, int lotNumber, int sectionNumber) {
        boolean exists = false;
        for (Property property : allProperties) {
            String propertyName = property.getNameOfOwner();
            int propertyLotNumber = property.getLotNumber();
            int propertySectionNumber = property.getSectionNumber();
            if (propertyName.equals(nameOfOwner) && propertyLotNumber == lotNumber && propertySectionNumber == sectionNumber) {
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

    public String showAllProperties() { // TODO: denne er tydeligvis en uendelig løkke så dette må fikses på begge prosjekter
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

    public Double calculateAvgSizeOfArea() {
        double totalArea = 0;
        int howManyProperties = numberOfProperties();
        for (Property property : allProperties) {
            totalArea += property.getArea();
        }
        return totalArea / howManyProperties;
    }
    public int numberOfProperties() {
        return allProperties.size();
    }

    public String [][] propertiesWithLotNumber(int lotNumber) {
        int howManyProperties = 0;
        for (Property property : allProperties) {
            if (property.getLotNumber() == lotNumber) {
                howManyProperties++;
            }
        }
        if (howManyProperties == 0) {
            return null;
        } else {
            String [][] propertiesWithLotNumber = new String[howManyProperties][7];
            int i = 0;
            for (Property property : allProperties) {
                if (property.getLotNumber() == lotNumber) {
                    propertiesWithLotNumber[i][0] = String.valueOf(property.getMunicipalityNumber());
                    propertiesWithLotNumber[i][1] = property.getMunicipalityName();
                    propertiesWithLotNumber[i][2] = String.valueOf(property.getLotNumber());
                    propertiesWithLotNumber[i][3] = String.valueOf(property.getSectionNumber());
                    propertiesWithLotNumber[i][4] = property.getName();
                    propertiesWithLotNumber[i][5] = String.valueOf(property.getArea());
                    propertiesWithLotNumber[i][6] = property.getNameOfOwner();
                    i++;
                }
            }
            return propertiesWithLotNumber;
        }
    }
}
