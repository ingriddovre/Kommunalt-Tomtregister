import java.util.Arrays;
import java.util.Scanner;

public class UserInterface {
  Scanner scanner = new Scanner(System.in);
  PropertyRegister propReg = new PropertyRegister();
  public void menu() {
    System.out.println("""
        Welcome to the Property Register Application
        Please choose one of the following options:
        1. Add property
        2. List all Properties
        3. Search for property
        4. Calculate average area
        5. Delete property
        6. Search for properties with a specific lot number
        7. Get number of properties in the register
        8. Get property ID from an owner
        9. Quit application
        """);
  }

  public void start() {
    Property prop1 = new Property( 77, 631, "", 1017.6, "Jens Olsen");
    Property prop2 = new Property( 77, 131, "Syningom", 661.3, "Nicolay Madsen");
    Property prop3 = new Property( 75, 19, "Fugletun", 650.6, "Evilyn Jensen");
    Property prop4 = new Property( 74, 188, "", 1457.2, "Karl Ove Bråten");
    Property prop5 = new Property( 69, 47, "Høiberg", 1339.4, "Elsa Indregård");
    propReg.allProperties.add(prop1);
    propReg.allProperties.add(prop2);
    propReg.allProperties.add(prop3);
    propReg.allProperties.add(prop4);
    propReg.allProperties.add(prop5);

    int choice = scanner.nextInt();
    boolean exit = false;
    while (!exit) {
      switch (choice) {
        case 1:
          System.out.println(uiNewProperty());
          break;
        case 2:
          System.out.println(propReg.showAllProperties());
          break;
        case 3:
          uiSearchForProperty();
          break;
        case 4:
          System.out.println(propReg.calculateAvgSizeOfArea());
          break;
        case 5:
          uiDeleteProperty();
          break;
        case 6:
          uiPropertiesWithLotNumber();
          break;
        case 7:
          System.out.println("There are " + propReg.numberOfProperties() + " properties in the register");
          break;
        case 8:
          System.out.println(uiPropertyID());
          break;
        case 9:
          System.out.println("Thank you for using the Property Register Application");
          exit = true;
          break;
        default: System.out.println("Please choose a number between 1 and 6");
          break;
      }
    }
  }

  private String uiNewProperty() {
    System.out.println("Please enter the lot number of the property");
    int lotNumber = scanner.nextInt();
    while (lotNumber < 1) {
      System.out.println("Please enter a valid lot number");
      lotNumber = scanner.nextInt();
    }
    System.out.println("Please enter the section number of the property");
    int sectionNumber = scanner.nextInt();
    while (sectionNumber < 1) {
      System.out.println("Please enter a valid section number");
      sectionNumber = scanner.nextInt();
    }
    //propReg.checkIfPropertyExists(1445, lotNumber, sectionNumber);

    if (propReg.checkIfPropertyExists(1445, lotNumber, sectionNumber) == 1) {
      return "This property already exists";

    } else {
      System.out.println(
          "Please enter the name of the property.\nIf the property does not have a name, press Enter.");
      String name = scanner.nextLine();
      if (name == null || name.isEmpty()) {
        name = "";
      }
      System.out.println("Please enter the area of the property");
      double area = scanner.nextDouble();
      while (area < 1) {
        System.out.println("Please enter a valid area");
        area = scanner.nextDouble();
      }
      System.out.println("Please enter the name of the owner of the property");
      String nameOfOwner = scanner.nextLine();
      while (nameOfOwner == null || nameOfOwner.isEmpty()) {
        System.out.println("Please enter a name");
        nameOfOwner = scanner.nextLine();
      }
      propReg.RegisterNewProperty(lotNumber, sectionNumber, name, area, nameOfOwner);
      return "Property added successfully";
    }
  }

  private void uiSearchForProperty() {
    System.out.println("Enter the municipality number of the property.");
    int municipalityNumber = scanner.nextInt();
    while (municipalityNumber < 101 || municipalityNumber > 5054) { // hvorfor ikke bare sette municipality number til 1445?
      System.out.println("Please enter a valid municipality number");
      municipalityNumber = scanner.nextInt();
    }
    System.out.println("Enter the lot number of the property.");
    int lotNumber = scanner.nextInt();
    while (lotNumber < 1) {
      System.out.println("Please enter a valid lot number");
      lotNumber = scanner.nextInt();
    }
    System.out.println("Enter the section number of the property");
    int sectionNumber = scanner.nextInt();
    while (sectionNumber < 1) {
      System.out.println("Please enter a valid section number");
      sectionNumber = scanner.nextInt();
    }
    if (propReg.searchForProperty(municipalityNumber, lotNumber, sectionNumber) == null) {
      System.out.println("This property does not exist");
    } else {
      System.out.println(propReg.searchForProperty(municipalityNumber, lotNumber, sectionNumber));
    }
  }

  private void uiDeleteProperty() {
    System.out.println("Enter the name of the owner");
    String nameOfOwner = scanner.nextLine();
    while (nameOfOwner == null || nameOfOwner.isEmpty()) {
      System.out.println("Please enter a name");
      nameOfOwner = scanner.nextLine();
    }
    System.out.println("Enter the lot number of the property");
    int lotNumber = scanner.nextInt();
    while (lotNumber < 1) {
      System.out.println("Please enter a valid lot number");
      lotNumber = scanner.nextInt();
    }
    System.out.println("Enter the section number of the property");
    int sectionNumber = scanner.nextInt();
    while (sectionNumber < 1) {
      System.out.println("Please enter a valid section number");
      sectionNumber = scanner.nextInt();
    }
    if (propReg.deleteProperty(nameOfOwner, lotNumber, sectionNumber) == 1) {
      System.out.println("Property deleted successfully from the register.");
    } else {
      System.out.println("This property does not exist");
    }
  }

  private void uiPropertiesWithLotNumber() {
    System.out.println("Enter the lot number you want to check.");
    int lotNumber = scanner.nextInt();
    while (lotNumber < 1) {
      System.out.println("Please enter a valid lot number");
      lotNumber = scanner.nextInt();
    }
    if(propReg.propertiesWithLotNumber(lotNumber) == null) {
      System.out.println("There are no properties with this lot number");
    } else {
      System.out.println(Arrays.deepToString(propReg.propertiesWithLotNumber(lotNumber)));
    }
  }

  private String uiPropertyID() {
    System.out.println("Enter the name of the owner");
    String nameOfOwner = scanner.nextLine();
    while (nameOfOwner == null || nameOfOwner.isEmpty()) {
      System.out.println("Please enter a name");
      nameOfOwner = scanner.nextLine();
    }
    String ID = "";
    for (Property property : propReg.getAllProperties()) {
      if (property.getNameOfOwner().equals(nameOfOwner)) {
        ID += property.PropertyID() + "\n";
      }
    }
    return ID;
  }
}
