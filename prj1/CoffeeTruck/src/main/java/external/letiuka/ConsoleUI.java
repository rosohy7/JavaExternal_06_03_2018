package external.letiuka;

import java.io.*;
import java.text.ParseException;

public class ConsoleUI {
    BufferedReader reader;
    BufferedWriter writer;
    Controller contr;
    boolean quit = false;

    public ConsoleUI() {
        contr = new Controller();
    }

    public void loop() throws IOException {
        try {
            reader = new BufferedReader(
                    new InputStreamReader(System.in));
            writer = new BufferedWriter(
                    new OutputStreamWriter(System.out));
            write("Use 'q' to quit, 'help' to get a list of commands.\n");
            while (!quit) {
                try {
                    writeln("\nPlease type in a new command:");
                    String com = reader.readLine();
                    writeln();
                    execute(com);
                } catch (IllegalArgumentException e) {
                    writeln("Failed to execute command. Illegal user input");
                }
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
    }

    private void createTruck() throws IOException {
        write("Enter truck max volume in m^3\n");
        double vol = Double.parseDouble(reader.readLine());
        contr.createTruck(vol);
        write("Truck is created and active\n");
    }

    private void importTruck() throws IOException {
        try {
            write("Enter filename\n");

            String file = reader.readLine();
            try {
                contr.importTruck(file);
                write("Truck is imported and active\n");
            } catch (InvalidClassException e) {
                write("Failed to import truck because class version has changed\n");
            } catch (ClassNotFoundException e) {
                write("Failed to import truck because there is no such class\n");
            }
        } catch (FileNotFoundException e) {
            writeln("Failed to find the file to import the truck from");
        }

    }

    private void exportTruck() throws IOException {
        write("Enter filename\n");
        String file = reader.readLine();
        contr.exportTruck(file);
        write("Truck is exported\n");
    }

    public void addCoffee() throws IOException {

        write("Enter type (number)\n" +
                Coffee.ARABICA + " - arabica, " +
                Coffee.CANEPHORA + " - canephora, " +
                Coffee.LIBERICA + " - liberica, " +
                Coffee.DEWEVREI + " - dewevrei\n");
        int type = Integer.parseInt(reader.readLine());
        write("Enter form (number)\n" +
                CoffeeFactory.BEANS + " - beans, " +
                CoffeeFactory.GROUND + " - ground, " +
                CoffeeFactory.INSTANT_CANS + " - instant(cans), " +
                CoffeeFactory.INSTANT_BAGS + " - instant(bags)\n");
        int form = Integer.parseInt(reader.readLine());
        write("Enter quality (number 1-100)\n");
        int quality = Integer.parseInt(reader.readLine());
        write("Enter weight in kilos\n");
        double weight = Double.parseDouble(reader.readLine());
        write("Enter cost in UAH\n");
        double cost = Double.parseDouble(reader.readLine());
        write("Enter volume taken by contained in m^3\n");
        double vol = Double.parseDouble(reader.readLine());
        write("Enter amount of containers to add\n");
        int count = Integer.parseInt(reader.readLine());
        try {
            contr.addCargo(type, form, quality, weight, cost, vol, count);
            write("Successfully put coffee into truck\n");
        } catch (TruckTooFullException e) {
            write("Failed to put all coffee, not enough space in the truck");
        }


    }

    private void selectByQuality() throws IOException {
        write("Enter min quality (number 1-100)\n");
        int min = Integer.parseInt(reader.readLine());
        write("Enter max quality (number 1-100)\n");
        int max = Integer.parseInt(reader.readLine());
        write("Type 'remove' to remove items from the truck\n");
        boolean rmItems;
        String choice = reader.readLine();
        if ("remove".equals(choice))
            rmItems = true;
        else
            rmItems = false;
        ArrayList res = contr.selecByQuality(min, max, rmItems);
        for (int i = 0; i < res.getLen(); i++) {
            write(i + ". " + res.get(i) + "\n");
        }
        writeln("Selected everything");
    }

    private void sortCoffee() throws IOException {
        contr.sortCoffee();
        writeln("Coffee sorted according to its price to weight ratio");
    }

    private void showHelp() throws IOException {
        writeln("q - quits");
        writeln("help - describes commands");
        writeln("create truck - creates a new truck with specified volume and makes it active");
        writeln("import truck - reads a truck from a specified file and makes it active");
        writeln("export truck - saves the active truck into a specified file");
        writeln("show truck - prints information about the active truck and its contents");
        writeln("add coffee - adds specified by user coffee containers to the active truck");
        writeln("sort coffee - sorts contents of the active truck" +
                " in ascending order of their cost to weight ratio");
        writeln("select by quality - selects a subset of the active truck content withing coffee quality range" +
                " with an option to remove the items from the truck");
        writeln("remove coffee - removes a specified coffee container from the truck");


    }

    private void showTruck() throws IOException {
        writeln(contr.describeTruck());
    }

    private void removeCoffee() throws IOException {
        boolean finish = false;
        do {
            showTruck();
            write("Enter item index to remove\n");
            int index = Integer.parseInt(reader.readLine());
            contr.removeCoffee(index);
            write("Type 'y' to keep removing items\n");
            String choice = reader.readLine();
            if (!"y".equals(choice)) {
                finish = true;
            }
        } while (!finish);

    }

    private void write(String msg) throws IOException {
        writer.write(msg);
        writer.flush();
    }

    private void writeln(String msg) throws IOException {
        write(msg + "\n");
    }

    private void writeln() throws IOException {
        writeln("");
    }


    private void execute(String command) throws IOException {
        if ("q".equals(command)) {
            quit = true;
        } else if ("help".equals(command)) {
            showHelp();
        } else if ("create truck".equals(command)) {
            createTruck();
        } else if ("import truck".equals(command)) {
            importTruck();
        } else if ("export truck".equals(command)) {
            exportTruck();
        } else if ("show truck".equals(command)) {
            showTruck();
        } else if ("add coffee".equals(command)) {
            addCoffee();
        } else if ("sort coffee".equals(command)) {
            sortCoffee();
        } else if ("select by quality".equals(command)) {
            selectByQuality();
        } else if ("remove coffee".equals(command)) {
            removeCoffee();
        } else {
            write("Wrong command, try again." +
                    " Use 'q' to quit, 'help' for list of commands.\n");
        }
    }
}
