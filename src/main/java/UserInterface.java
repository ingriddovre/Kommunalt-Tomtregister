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
        6. Quit application
        """);
  }

  public void start() {
    int choice = scanner.nextInt();
    boolean exit = false;
    while (!exit) {
      switch (choice) {
        case 1: uiNewProperty();
        case 2: System.out.println(Arrays.deepToString(propReg.showAllProperties())); //spør om denne arrays tingen om du ikke finner det ut selv
        case 3: uiSearchForProperty();
        case 4: System.out.println(propReg.calculateAvgSizeOfArea());
        case 5: uiDeleteProperty();
        case 6: System.out.println("Thank you for using the Property Register Application");
          exit = true;
        default: System.out.println("Please choose a number between 1 and 6");
      }
    }
  }

  public void uiNewProperty() {
    System.out.println("Please enter the municipality number of the property");
    int municipalityNumber = scanner.nextInt();
    while (municipalityNumber < 101 || municipalityNumber > 5054) {
      System.out.println("Please enter a valid municipality number");
      municipalityNumber = scanner.nextInt();
    }
    System.out.println("Please enter the name of the municipality");
    String municipalityName = scanner.next();
    while (municipalityName == null || municipalityName.isEmpty()) {
      System.out.println("Please enter a valid municipality name");
      municipalityName = scanner.next();
    }
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
    System.out.println("Please enter the name of the property.\nIf the property does not have a name, press Enter.");
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
    propReg.RegisterNewProperty(municipalityNumber, municipalityName, lotNumber, sectionNumber, name, area, nameOfOwner);
    System.out.println("Property added successfully");
  }

  public void uiSearchForProperty() {
    System.out.println("Enter the municipality number of the property.");
    int municipalityNumber = scanner.nextInt();
    while (municipalityNumber < 101 || municipalityNumber > 5054) {
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
    if (propReg.checkIfPropertyExists(municipalityNumber, lotNumber, sectionNumber) == 1) {
      System.out.println(propReg.searchForProperty(municipalityNumber, lotNumber, sectionNumber));
    } else {
      System.out.println("This property does not exist");
    }
  }

  public void uiDeleteProperty() {
    boolean deleted = false;
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
    for (Property property : propReg.getAllProperties()) {
      if (property.getNameOfOwner().equals(nameOfOwner) && property.getLotNumber() == lotNumber &&
          property.getSectionNumber() == sectionNumber) {
        propReg.getAllProperties().remove(property);
        deleted = true;
        System.out.println("Property deleted successfully from the register.");
      }
    }
    if (!deleted) {
      System.out.println("This property does not exist");
    }
  }
}
