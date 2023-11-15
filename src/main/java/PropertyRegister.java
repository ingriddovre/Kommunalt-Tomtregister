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

public class PropertyRegister {
    ArrayList<Property> allProperties;
    public PropertyRegister() {
        allProperties = new ArrayList<Property>();
    }
    public ArrayList<Property> getAllProperties() {
        return allProperties;
    }
    public void RegisterNewProperty(int municipalityNumber, String municipalityName, int lotNumber, int sectionNumber, String name, double area, String nameOfOwner) {
        Property newProperty = new Property(municipalityNumber, municipalityName, lotNumber, sectionNumber, name, area, nameOfOwner);
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

    public void deleteProperty(String nameOfOwner, int lotNumber, int sectionNumber) {
        for (Property property : allProperties) {
            String propertyName = property.getNameOfOwner();
            int propertyLotNumber = property.getLotNumber();
            int propertySectionNumber = property.getSectionNumber();
            boolean exists = false;
            if (propertyName.equals(nameOfOwner) && propertyLotNumber == lotNumber && propertySectionNumber == sectionNumber) {
                allProperties.remove(property);
                exists = true;
                break;
            }
            if (exists) {
                System.out.println("This property does not exist");
            }
        }
    }
    public String[][] showAllProperties() {
        String[][] mapOfProperties = new String[allProperties.size()][7];
        for (int i = 0 ; i < allProperties.size() ; i++) {
            mapOfProperties[i][0] = String.valueOf(allProperties.get(i).getMunicipalityNumber());
            mapOfProperties[i][1] = allProperties.get(i).getMunicipalityName();
            mapOfProperties[i][2] = String.valueOf(allProperties.get(i).getLotNumber());
            mapOfProperties[i][3] = String.valueOf(allProperties.get(i).getSectionNumber());
            mapOfProperties[i][4] = allProperties.get(i).getName();
            mapOfProperties[i][5] = String.valueOf(allProperties.get(i).getArea());
            mapOfProperties[i][6] = allProperties.get(i).getNameOfOwner();
        }
        return mapOfProperties;
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

    public String [][] propertiesWithLotNumber(int lotNumber) { // søke etter eiendommer med samme gårdsnummer
        int howManyProperties = 0;
        for (Property property : allProperties) {
            if (property.getLotNumber() == lotNumber) {
                howManyProperties++;
            }
        }
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
