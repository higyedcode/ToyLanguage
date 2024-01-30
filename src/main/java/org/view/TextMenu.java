package org.view;

import org.view.Commands.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextMenu {
    private Map<String, Command> commands;
    public TextMenu(){
        commands = new HashMap<>();
    }
    public void addCommand(Command c){
        commands.put(c.getKey(),c);
    }

    public Map<String, Command> getCommands()
    {
        return commands;
    }

    private void printMenu()
    {
        System.out.println("-----------------------------------------------------------------");
        for(Command com: commands.values())
        {
            String line = String.format("%4s : %s", com.getKey(), com.getDescription());
            System.out.println(line);
        }
        System.out.println("-----------------------------------------------------------------");
    }
    public void show()
    {
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            printMenu();
            System.out.println("Input the option: ");
            String key = scanner.nextLine();
            Command com = commands.get(key);
            if (com == null)
            {
                System.out.println("Invalid Option");
                continue;
            }
            com.execute();
        }
    }
}
