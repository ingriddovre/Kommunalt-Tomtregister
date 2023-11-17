import java.util.Scanner;

public class UserInterface {
  Scanner scanner = new Scanner(System.in);
  PropertyRegister propReg = new PropertyRegister();
  public void menu() {
    System.out.println("""
        
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
    Property prop1 = new Property(77, 631, "", 1017.6, "Jens Olsen");
    Property prop2 = new Property(77, 131, "Syningom", 661.3, "Nicolay Madsen");
    Property prop3 = new Property(75, 19, "Fugletun", 650.6, "Evilyn Jensen");
    Property prop4 = new Property(74, 188, "", 1457.2, "Karl Ove Bråten");
    Property prop5 = new Property(69, 47, "Høiberg", 1339.4, "Elsa Indregård");
    propReg.allProperties.add(prop1);
    propReg.allProperties.add(prop2);
    propReg.allProperties.add(prop3);
    propReg.allProperties.add(prop4);
    propReg.allProperties.add(prop5);

    System.out.println("Welcome to the Property Register Application");
    boolean exit = false;
    while (!exit) {
      menu();
      int choice = scanner.nextInt();
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
          System.out.println(
              "There are " + propReg.numberOfProperties() + " properties in the register");
          break;

        case 8:
          System.out.println(uiPropertyID());
          break;

        case 9:
          System.out.println("Thank you for using the Property Register Application");
          exit = true;
          break;

        default:
          System.out.println("Please choose a number between 1 and 9");
          break;

      }
    }
  }
  private String uiNewProperty() {
    int lotNumber = 0;
    int sectionNumber = 0;
    String name = "";
    Double area = 0.0;
    String nameOfOwner = "";
    boolean correctLotNumber = false;
    while (!correctLotNumber) {
    try {
      System.out.println("Please enter the lot number of the property:");
      lotNumber = scanner.nextInt();
      if (lotNumber < 1) {
        throw new IllegalArgumentException("Incorrect lot number: " + lotNumber);
      } else {
        correctLotNumber = true;
      }
    } catch (IllegalArgumentException e) {
      System.out.println("Please enter a valid lot number.");
    }
  }
    boolean correctSectionNumber = false;
    while (!correctSectionNumber) {
      try {
        System.out.println("Please enter the section number of the property:");
        sectionNumber = scanner.nextInt();
        if (sectionNumber < 1) {
          throw new IllegalArgumentException("Incorrect section number: " + sectionNumber);
        } else {
          correctSectionNumber = true;
        }
      } catch (IllegalArgumentException e) {
        System.out.println("Please enter a valid section number.");
      }
    }
    if (propReg.checkIfPropertyExists(1445, lotNumber, sectionNumber) == 1) {
      return "This property already exists.";

    } else {
      boolean correctName = false;
      while (!correctName) {
        try {
          System.out.println(
              "Please enter the name of the property.\n"
                  + "If the property does not have a name, write 0:");
          scanner.nextLine();
          name = scanner.nextLine();
          if (name.equals("0")) {
            name = "";
          } else if (name.length() > 20 || name.isEmpty()) {
            throw new IllegalArgumentException("Incorrect name:" + name);
          } else {
            correctName = true;
          }
        } catch (IllegalArgumentException e) {
          System.out.println("The name cannot be longer than 20 characters, or empty. "
              + "Please try again.");
        }
      }
      boolean correctArea = false;
      while (!correctArea) {
        try {
          System.out.println("Please enter the area of the property:");
          area = scanner.nextDouble();
          if (area < 1) {
            throw new IllegalArgumentException("Incorrect area: " + area);
          } else {
            correctArea = true;
          }
        } catch (IllegalArgumentException e) {
          System.out.println("Please enter a valid size of the area.");
        }
      }
      boolean correctNameOfOwner = false;
      while (!correctNameOfOwner) {
        try {
          System.out.println("Please enter the name of the owner of the property:");
          scanner.nextLine();
          nameOfOwner = scanner.nextLine();
          if (nameOfOwner.isEmpty()) {
            throw new NullPointerException("Incorrect name of owner: " + nameOfOwner);
          } else {
            correctNameOfOwner = true;
          }
        } catch (NullPointerException e) {
          System.out.println("Please enter a name");
        }
      }
      propReg.RegisterNewProperty(lotNumber, sectionNumber, name, area, nameOfOwner);
      System.out.println("Property added successfully to the register.\n");
      return propReg.showAllProperties();
    }
  }

  private void uiSearchForProperty() {
    int municipalityNumber = 0;
    int lotNumber = 0;
    int sectionNumber = 0;
    boolean correctMunicipalityNumber = false;
    boolean correctLotNumber = false;
    boolean correctSectionNumber = false;
    while (!correctMunicipalityNumber) {
      try {
        System.out.println("Enter the municipality number of the property:");
        municipalityNumber = scanner.nextInt();
        if (municipalityNumber < 101 || municipalityNumber > 5054) {
          throw new IllegalArgumentException("Incorrect municipality number: " + municipalityNumber);
        } else {
          correctMunicipalityNumber = true;
        }
      } catch (IllegalArgumentException e) {
        System.out.println("Please enter the correct municipality number.");
      }
    }
    while (!correctLotNumber) {
      try {
        System.out.println("Enter the lot number of the property:");
        lotNumber = scanner.nextInt();
        if (lotNumber < 1) {
          throw new IllegalArgumentException("Incorrect lot number: " + lotNumber);
        } else {
          correctLotNumber = true;
        }
      } catch (IllegalArgumentException e) {
        System.out.println("Please enter a valid lot number.");
      }
    }
    while (!correctSectionNumber) {
      try {
        System.out.println("Enter the section number of the property:");
        sectionNumber = scanner.nextInt();
        if (sectionNumber < 1) {
          throw new IllegalArgumentException("Incorrect section number: " + sectionNumber);
        } else {
          correctSectionNumber = true;
        }
      } catch (IllegalArgumentException e) {
        System.out.println("Please enter a valid section number.");
      }
    }
    if (propReg.searchForProperty(municipalityNumber, lotNumber, sectionNumber) == null) {
      System.out.println("This property does not exist");
    } else {
      System.out.println(propReg.searchForProperty(municipalityNumber, lotNumber, sectionNumber));
    }
  }

  private void uiDeleteProperty() {
    String nameOfOwner = "";
    int lotNumber = 0;
    int sectionNumber = 0;
    boolean correctNameOfOwner = false;
    while (!correctNameOfOwner) {
      try {
        System.out.println("Enter the name of the owner:");
        scanner.nextLine();
        nameOfOwner = scanner.nextLine();
        if (nameOfOwner.isEmpty()) {
          throw new NullPointerException("Incorrect name of owner: " + nameOfOwner);
        } else {
          correctNameOfOwner = true;
        }
      } catch (NullPointerException e) {
        System.out.println("You can not enter an empty name. Please enter a valid name");
      }
    }

    boolean correctLotNumber = false;
    while (!correctLotNumber) {
      try {
        System.out.println("Enter the lot number of the property");
        lotNumber += scanner.nextInt();
        if (lotNumber < 1) {
          throw new IllegalArgumentException();
        } else {
          correctLotNumber = true;
        }
      } catch (IllegalArgumentException e) {
        System.out.println("Please enter a valid lot number");
      }
    }

    boolean correctSectionNumber = false;
    while (!correctSectionNumber) {
      try {
        System.out.println("Enter the section number of the property:");
        sectionNumber += scanner.nextInt();
        if (sectionNumber < 1) {
          throw new IllegalArgumentException();
        } else {
            correctSectionNumber = true;
        }
      } catch (IllegalArgumentException e) {
        System.out.println("Please enter a valid section number");
      }
    }
    if (propReg.deleteProperty(nameOfOwner, lotNumber, sectionNumber) == 1) {
      System.out.println("Property deleted successfully from the register.");
      propReg.showAllProperties();
    } else {
      System.out.println("This property does not exist");
    }
  }

  private void uiPropertiesWithLotNumber() {
    int lotNumber = 0;
    boolean correctLotNumber = false;
    while (!correctLotNumber) {
      try {
        System.out.println("Enter the lot number you want to check:");
        lotNumber = scanner.nextInt();
        if (lotNumber < 1) {
          throw new IllegalArgumentException("Incorrect lot number: " + lotNumber);
        } else {
          correctLotNumber = true;
        }
      } catch (IllegalArgumentException e) {
        System.out.println("Please enter a valid lot number");
      }
    }
    if(propReg.propertiesWithLotNumber(lotNumber) == null) {
      System.out.println("There are no properties with this lot number");
    } else {
      System.out.println(propReg.propertiesWithLotNumber(lotNumber));
    }
  }

  private String uiPropertyID() {
    String nameOfOwner = "";
    boolean correctNameOfOwner = false;
    while (!correctNameOfOwner) {
      try {
        System.out.println("Enter the name of the owner");
        scanner.nextLine();
        nameOfOwner = scanner.nextLine();
        if (nameOfOwner.isEmpty()) {
          throw new NullPointerException("Incorrect name of owner: " + nameOfOwner);
        } else {
          correctNameOfOwner = true;
        }
      } catch (NullPointerException e) {
        System.out.println("You can not enter an empty name. Please enter a valid name");
      }
    }
    StringBuilder ID = new StringBuilder();
    for (Property property : propReg.getAllProperties()) {
      if (property.getNameOfOwner().equalsIgnoreCase(nameOfOwner)) {
        ID.append(property.PropertyID()).append("\n");
      }
    }
    if (ID.isEmpty()) {
        return "This owner does not exist in the register.";
    } else {
      return ID.toString();
    }
  }
}
