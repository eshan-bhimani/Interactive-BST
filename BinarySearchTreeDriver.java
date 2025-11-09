package cs2720.p3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Driver class for Binary Search tree - handles user interaction and file I/O
public class BinarySearchTreeDriver {

        public static void main(String[] args) {
                // Check if filename is provided as command-line argument
                if (args.length < 1) {
                        System.err.println("Error: Please provide an input file name as a command-line argument.");
                        System.err.println("Usage: java cs2720.p3.BinarySearchTreeDriver <filename>");
                        System.exit(1);
                }

                String filename = args[0];
                Scanner inputScanner = new Scanner(System.in);
                // Get data type from user (int, double, or string)
                System.out.print("Enter list type (i - int, d - double, s - string): ");
                String type = inputScanner.nextLine().trim().toLowerCase();

                // Run appropriate tree based on type
                if (type.equals("i")) {
                        runIntTree(filename, inputScanner);
                } else if (type.equals("d")) {
                        runDoubleTree(filename, inputScanner);
                } else if (type.equals("s")) {
                        runStringTree(filename, inputScanner);
                } else {
                        System.err.println("Error: Invalid type. Please enter 'i', 'd', or 's'.");
                        inputScanner.close();
                        System.exit(1);
                }

                inputScanner.close();
        }
        // Initialize and run tree for Integer type
        private static void runIntTree(String filename, Scanner scanner) {
                BinarySearchTree<Integer> tree = new BinarySearchTree<>();
                // Read integer from file and build initial tree
                try {
                        Scanner fileScanner = new Scanner(new File(filename));
                        while (fileScanner.hasNextLine()) {
                                String line = fileScanner.nextLine().trim();
                                if (!line.isEmpty()) {
                                        try {
                                                int value = Integer.parseInt(line);
                                                try {
                                                        tree.insert(value);
                                                } catch (IllegalArgumentException e) {

                                                }
                                        } catch (NumberFormatException e) {
                                                System.err.println("Warning: Skipping invalid integer: " + line);
                                        }
                                }
                        }
                        fileScanner.close();
                } catch (FileNotFoundException e) {
                        System.err.println("Error: File '" + filename + "' not found.");
                        System.exit(1);
                }

                runMenu(tree, scanner, "int");
        }

        // Initialize and run tree for Double type
        private static void runDoubleTree(String filename, Scanner scanner) {
                BinarySearchTree<Double> tree = new BinarySearchTree<>();

                try {
                        Scanner fileScanner = new Scanner(new File(filename));
                        while (fileScanner.hasNextLine()) {
                                String line = fileScanner.nextLine().trim();
                                if (!line.isEmpty()) {
                                        try {
                                                double value = Double.parseDouble(line);
                                                try {
                                                        tree.insert(value);
                                                } catch (IllegalArgumentException e) {

                                                }
                                        } catch (NumberFormatException e) {
                                                System.err.println("Warning: Skipping invalid double: " + line);
                                        }
                                }
                        }
                        fileScanner.close();
                } catch (FileNotFoundException e) {
                        System.err.println("Error: File '" + filename + "' not found.");
                        System.exit(1);
                }

                runMenu(tree, scanner, "double");
        }
        // Initialize and run tree for String type
        private static void runStringTree(String filename, Scanner scanner) {
                BinarySearchTree<String> tree = new BinarySearchTree<>();

                try {
                        Scanner fileScanner = new Scanner(new File(filename));
                        while (fileScanner.hasNextLine()) {
                                String line = fileScanner.nextLine().trim();
                                if (!line.isEmpty()) {
                                        try {
                                                tree.insert(line);
                                        } catch (IllegalArgumentException e) {

                                        }
                                }
                        }
                        fileScanner.close();
                } catch (FileNotFoundException e) {
                        System.err.println("Error: File '" + filename + "' not found.");
                        System.exit(1);
                }

                runMenu(tree, scanner, "string");
        }
        // Main interactive menu loop
        private static <T extends Comparable<T>> void runMenu(BinarySearchTree<T> tree, Scanner scanner, String type) {
                printCommands();

                boolean running = true;
                while (running) {
                        System.out.print("\nEnter a command: ");
                        String command = scanner.nextLine().trim().toLowerCase();

                        switch (command) {
                                case "i":
                                        handleInsert(tree, scanner, type);
                                        break;
                                case "d":
                                        handleDelete(tree, scanner, type);
                                        break;
                                case "p":
                                        tree.inOrder();
                                        break;
                                case "r":
                                        handleRetrieve(tree, scanner, type);
                                        break;
                                case "l":
                                        tree.getNumLeafNodes();
                                        break;
                                case "s":
                                        tree.getSingleParent();
                                        break;
                                case "c":
                                        handleCousins(tree, scanner, type);
                                        break;
                                case "o":
                                        tree.inOrder();
                                        if (tree.isProper()) {
                                                System.out.println("This tree is proper.");
                                        } else {
                                                System.out.println("This tree is not proper.");
                                        }
                                        break;
                                case "m":
                                        tree.inOrder();
                                        if (tree.isComplete()) {
                                                System.out.println("This tree is complete.");
                                        } else {
                                                System.out.println("This tree is not complete.");
                                        }
                                        break;
                                case "q":
                                        running = false;
                                        break;
                                default:
                                        System.out.println("Invalid command. Please try again.");
                                        break;
                        }
                }
        }
        // Handle insert command for all data types
        private static <T extends Comparable<T>> void handleInsert(BinarySearchTree<T> tree, Scanner scanner, String type) {
                tree.inOrder();

                if (type.equals("int")) {
                        System.out.print("Enter a number to insert: ");
                        String input = scanner.nextLine().trim();
                        try {
                                int value = Integer.parseInt(input);
                                @SuppressWarnings("unchecked")
                                BinarySearchTree<Integer> intTree = (BinarySearchTree<Integer>) tree;
                                try {
                                        intTree.insert(value);
                                } catch (IllegalArgumentException e) {
                                        System.out.println("The item already exists in the tree.");
                                }
                                tree.inOrder();
                        } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a valid integer.");
                        }
                } else if (type.equals("double")) {
                        System.out.print("Enter a number to insert: ");
                        String input = scanner.nextLine().trim();
                        try {
                                double value = Double.parseDouble(input);
                                @SuppressWarnings("unchecked")
                                BinarySearchTree<Double> doubleTree = (BinarySearchTree<Double>) tree;
                                try {
                                        doubleTree.insert(value);
                                } catch (IllegalArgumentException e) {
                                        System.out.println("The item already exists in the tree.");
                                }
                                tree.inOrder();
                        } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a valid double.");
                        }
                } else if (type.equals("string")) {
                        System.out.print("Enter a string to insert: ");
                        String value = scanner.nextLine().trim();
                        @SuppressWarnings("unchecked")
                        BinarySearchTree<String> stringTree = (BinarySearchTree<String>) tree;
                        try {
                                stringTree.insert(value);
                        } catch (IllegalArgumentException e) {
                                System.out.println("The item already exists in the tree.");
                        }
                        tree.inOrder();
                }
        }
        // Handle delete command for all data types
        private static <T extends Comparable<T>> void handleDelete(BinarySearchTree<T> tree, Scanner scanner, String type) {
                tree.inOrder();

                if (type.equals("int")) {
                        System.out.print("Enter a number to delete: ");
                        String input = scanner.nextLine().trim();
                        try {
                                int value = Integer.parseInt(input);
                                @SuppressWarnings("unchecked")
                                BinarySearchTree<Integer> intTree = (BinarySearchTree<Integer>) tree;
                                try {
                                        intTree.delete(value);
                                } catch (IllegalArgumentException e) {
                                        System.out.println("The number is not present in the tree");
                                }
                                tree.inOrder();
                        } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a valid integer.");
                        }
                } else if (type.equals("double")) {
                        System.out.print("Enter a number to delete: ");
                        String input = scanner.nextLine().trim();
                        try {
                                double value = Double.parseDouble(input);
                                @SuppressWarnings("unchecked")
                                BinarySearchTree<Double> doubleTree = (BinarySearchTree<Double>) tree;
                                try {
                                        doubleTree.delete(value);
                                } catch (IllegalArgumentException e) {
                                        System.out.println("The number is not present in the tree");
                                }
                                tree.inOrder();
                        } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a valid double.");
                        }
                } else if (type.equals("string")) {
                        System.out.print("Enter a string to delete: ");
                        String value = scanner.nextLine().trim();
                        @SuppressWarnings("unchecked")
                        BinarySearchTree<String> stringTree = (BinarySearchTree<String>) tree;
                        try {
                                stringTree.delete(value);
                        } catch (IllegalArgumentException e) {
                                System.out.println("Item is not present in the tree");
                        }
                        tree.inOrder();
                }
        }
        // Handle retrieve/search command for all data types
        private static <T extends Comparable<T>> void handleRetrieve(BinarySearchTree<T> tree, Scanner scanner, String type) {
                tree.inOrder();

                if (type.equals("int")) {
                        System.out.print("Enter a number to search: ");
                        String input = scanner.nextLine().trim();
                        try {
                                int value = Integer.parseInt(input);
                                @SuppressWarnings("unchecked")
                                BinarySearchTree<Integer> intTree = (BinarySearchTree<Integer>) tree;
                                if (intTree.retrieve(value)) {
                                        System.out.println("Item is present in the tree");
                                } else {
                                        System.out.println("Item is not present in the tree");
                                }
                        } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a valid integer.");
                        }
                } else if (type.equals("double")) {
                        System.out.print("Enter a number to search: ");
                        String input = scanner.nextLine().trim();
                        try {
                                double value = Double.parseDouble(input);
                                @SuppressWarnings("unchecked")
                                BinarySearchTree<Double> doubleTree = (BinarySearchTree<Double>) tree;
                                if (doubleTree.retrieve(value)) {
                                        System.out.println("Item is present in the tree");
                                } else {
                                        System.out.println("Item is not present in the tree");
                                }
                        } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a valid double.");
                        }
                } else if (type.equals("string")) {
                        System.out.print("Enter a string to search: ");
                        String value = scanner.nextLine().trim();
                        @SuppressWarnings("unchecked")
                        BinarySearchTree<String> stringTree = (BinarySearchTree<String>) tree;
                        if (stringTree.retrieve(value)) {
                                System.out.println("Item is present in the tree");
                        } else {
                                System.out.println("Item is not present in the tree");
                        }
                }
        }
        // Handle cousins command for all data types
        private static <T extends Comparable<T>> void handleCousins(BinarySearchTree<T> tree, Scanner scanner, String type) {
                tree.inOrder();

                if (type.equals("int")) {
                        System.out.print("Enter a number: ");
                        String input = scanner.nextLine().trim();
                        try {
                                int value = Integer.parseInt(input);
                                @SuppressWarnings("unchecked")
                                BinarySearchTree<Integer> intTree = (BinarySearchTree<Integer>) tree;
                                intTree.getCousins(value);
                        } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a valid integer.");
                        }
                } else if (type.equals("double")) {
                        System.out.print("Enter a number: ");
                        String input = scanner.nextLine().trim();
                        try {
                                double value = Double.parseDouble(input);
                                @SuppressWarnings("unchecked")
                                BinarySearchTree<Double> doubleTree = (BinarySearchTree<Double>) tree;
                                doubleTree.getCousins(value);
                        } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a valid double.");
                        }
                } else if (type.equals("string")) {
                        System.out.print("Enter a string: ");
                        String value = scanner.nextLine().trim();
                        @SuppressWarnings("unchecked")
                        BinarySearchTree<String> stringTree = (BinarySearchTree<String>) tree;
                        stringTree.getCousins(value);
                }
        }
        // Display available commands to user
        private static void printCommands() {
                System.out.println("\nCommands:");
                System.out.println("(i) - Insert Item");
                System.out.println("(d) - Delete Item");
                System.out.println("(p) - Print Tree");
                System.out.println("(r) - Retrieve Item");
                System.out.println("(l) - Count Leaf Nodes");
                System.out.println("(s) - Find Single Parents");
                System.out.println("(c) - Find Cousins");
                System.out.println("(o) - Is Proper");
                System.out.println("(m) - Is Complete");
                System.out.println("(q) - Quit program");
        }
}

~                        
