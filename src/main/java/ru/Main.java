package ru;

import ru.config.SpringComponentScan;
import ru.service.Command;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Set<Command> commands = SpringComponentScan.getInstance().getAllCommands();

        System.out.println("Введите команду : ");

        while (true) {
            String text = sc.nextLine();
            String msg = "Такой команды нет ! \n ";
            for (Command command : commands) {
                if (text.equals(command.getName())) {
                    msg = "";
                    if (!command.execute()) {
                        sc.close();
                        return;
                    }
                    break;
                }
            }
            System.out.print(msg);
        }
    }
}