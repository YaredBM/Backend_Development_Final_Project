package ui;

import java.awt.GridLayout; // Imports the GridLayout class for layout management.

import javax.swing.*; // Imports all classes from the javax.swing package for creating the GUI.

//This class is to create the menu bar with options for starting a new game, saving the game, opening a saved game, and displaying an about dialog.

public class MenuBar extends JMenuBar { // Creates the MenuBar class that extends JMenuBar.
    
     // Constructor to initialize the menu bar with the specified controller.
     // @param controller The game controller.
    public MenuBar(GameController controller) {
        JMenu menu = new JMenu("File"); // Creates a new menu with the title "File".
        
        JMenuItem newGame = new JMenuItem("New Game"); // Creates a new menu item with the title "New Game".
        newGame.addActionListener(e -> controller.newGame()); // Adds an action listener to start a new game when clicked.
        
        JMenuItem saveGame = new JMenuItem("Save Game"); // Creates a new menu item with the title "Save Game".
        saveGame.addActionListener(e -> controller.saveGame()); // Adds an action listener to save the game when clicked.
        
        JMenuItem openGame = new JMenuItem("Open Game"); // Creates a new menu item with the title "Open Game".
        openGame.addActionListener(e -> controller.openGame()); // Adds an action listener to open a saved game when clicked.
        
        JMenuItem exit = new JMenuItem("Exit"); // Creates a new menu item with the title "Exit".
        exit.addActionListener(e -> System.exit(0)); // Adds an action listener to exit the application when clicked.
        
        JMenuItem about = new JMenuItem("About"); // Creates a new menu item with the title "About".
        about.addActionListener(e -> showAboutDialog()); // Adds an action listener to show the about dialog when clicked.

        menu.add(newGame); // Adds the new game menu item to the menu.
        menu.add(saveGame); // Adds the save game menu item to the menu.
        menu.add(openGame); // Adds the open game menu item to the menu.
        menu.add(exit); // Adds the exit menu item to the menu.
        menu.add(about); // Adds the about menu item to the menu.
        
        add(menu); // Adds the menu to the menu bar.
    }

     // Displays an about dialog with the names of the developers.
    private void showAboutDialog() {
        JDialog aboutDialog = new JDialog(); // Creates a new dialog for the about information.
        aboutDialog.setTitle("About"); // Sets the title of the dialog to "About".
        aboutDialog.setSize(400, 300); // Sets the size of the dialog.
        aboutDialog.setLayout(new GridLayout(6, 1)); // Sets the layout of the dialog to GridLayout with 6 rows and 1 column.

        String[] names = { // Array of developer names.
            "Yared Iesse Bustillo Medina",
            "Jonathan David Concha Matas",
            "Josue David Pavon Maldonado",
            "Julia Correira Bindi",
            "Bruna Perry Pierobon",
            "Sagar Bhusal"
        };

        for (String name : names) { // Iterates over the names array.
            JLabel label = new JLabel(name, SwingConstants.CENTER); // Creates a new label with the name and centers the text.
            aboutDialog.add(label); // Adds the label to the dialog.
        }

        aboutDialog.setVisible(true); // Makes the dialog visible.
    }

    
     // Prints information about the menu bar.
    public void printInfo() {
        System.out.println("Menu Bar Info:"); // Prints a message indicating it's menu bar info.
        for (int i = 0; i < getMenuCount(); i++) { // Iterates over the menus in the menu bar.
            System.out.println("Menu: " + getMenu(i).getText()); // Prints the title of each menu.
        }
    }
}
