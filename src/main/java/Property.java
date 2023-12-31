/**
 * This class is used to represent a property.
 * A common property in Norway has a municipality number, a municipality name, a lot number,
 * a section number, a name, an area, and a name of the owner.
 * @author Ingrid Midtmoen Døvre
 * @version 07.11.2023 - 13.11.2023
 */
public class Property {
    private final int municipalityNumber;
    private final String municipalityName;
    private final int lotNumber;
    private final int sectionNumber;
    private String name;
    private double area;
    private String nameOfOwner;

    /**
     * The constructor initializes a Property object with the following attributes:
     * @param lotNumber the lot number of the property. This is a final Integer attribute, because the lot number will not change.
     * @param sectionNumber the section number of the property. This is a final Integer attribute, because the section number will not change.
     * @param name the name of the property. Not all properties have a name, therefore this String is not set as final, and can be changed later.
     *             If the property is renamed for any reason, it can be changed with a mutator method. If the property does not have a name, the String will be empty.
     * @param area the area of the property. Property area is measured in square meters, and can be changed later, therefore it is not set as a final Double attribute.
     *             If the area of the property is measured incorrectly, or if the owners choose to buy more property, I use a mutator method to change it.
     * @param nameOfOwner The name of the owner of the property. Property owners can change, therefore this is not set as a final String, and
     *                    that is also why I have made a mutator method for this attribute.
     */

    public Property(int lotNumber, int sectionNumber, String name, double area, String nameOfOwner) {
        this.municipalityNumber = 1445;
        this.municipalityName = "Gloppen";
        this.lotNumber = lotNumber;
        this.sectionNumber = sectionNumber;
        this.name = name;
        this.area = area;
        this.nameOfOwner = nameOfOwner;
    }

    /**
     * This method is used to get the property ID of the property.
     * The property ID is set as: municipalityNumber-lotNumber/sectionNumber.
     * The nameOfOwner attribute is used to identify which property we want to get the ID of.
     * @return the property ID.
     */
    public String PropertyID() {
        return municipalityNumber + "-" + lotNumber + "/" + sectionNumber;
    }

    /**
     * This method is used to get the municipality number of the property.
     * @return the municipality number of the property.
     */
    public int getMunicipalityNumber() {
        return municipalityNumber;
    }

    /**
     * This method is used to get the name of the municipality.
     * @return the name of the municipality.
     */
    public String getMunicipalityName() {
        return municipalityName;
    }

    /**
     * This method is used to get the lot number of the property.
     * @return the lot number of the property.
     */
    public int getLotNumber() {
        return lotNumber;
    }

    /**
     * This method is used to get the section number of the property.
     * @return the section number of the property.
     */
    public int getSectionNumber() {
        return sectionNumber;
    }

    /**
     * This method is used to get the name of the property.
     * Not all properties have a name, therefore this method can return an empty String.
     * Or, the name can be set later with a mutator method.
     * @return the name of the property, or an empty string if the property does not have a name.
     */
    public String getName() {
        return name;
    }

    /**
     * This method is used to get the area of the property.
     * @return the area of the property.
     */
    public double getArea() {
        return area;
    }

    /**
     * This method is used to get the name of the owner of the property.
     * @return the name of the owner.
     */
    public String getNameOfOwner() {
        return nameOfOwner;
    }

    /**
     * This method is used to set the name of the property.
     * Not all properties have a name, therefore this method can be used to set the name later.
     * For example if the property is renamed, or does not have a name from before.
     * @param name the name of the property.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method is used to set the area of the property.
     * The area of the property is measured in square meters.
     * In some scenarios, properties expand, or decrease, and therefore the area can change.
     * @param area the area of the property.
     */
    public void setArea(double area) {
        this.area = area;
    }

    /**
     * This method is used to set the name of the owner of the property.
     * The owner of the property can change, therefore this method can be used to set the name of the owner later.
     * @param nameOfOwner the name of the owner of the property.
     */
    public void setNameOfOwner(String nameOfOwner) {
        this.nameOfOwner = nameOfOwner;
    }

    /**
     * This method creates a String representation of a specific property.
     * @return a String representation of the property.
     */
    @Override
    public String toString() {
        return "Municipality number: " + municipalityNumber
            + "\nMunicipality name: " + municipalityName
            + "\nLot number: " + lotNumber
            + "\nSection number: " + sectionNumber
            + "\nName: " + name
            + "\nArea: " + area
            + "\nName of owner: " + nameOfOwner;

    }
}