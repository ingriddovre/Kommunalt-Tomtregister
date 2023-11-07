public class testClass {
    public static void main(String[] args) {
        PropertyRegister register = new PropertyRegister();
        register.RegisterNewProperty(0165, "Oslo", 1, 1, "Hjem", 100, "Ola Nordmann");

        int choice = 1;
        switch (choice) {
            case 1:
                System.out.println(Property.PropertyID("Ola Nordmann"));
                break;
        }

    }
}
