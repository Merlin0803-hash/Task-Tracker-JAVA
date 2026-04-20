package org.example;

import org.example.Content.Content;
import org.example.Content.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Content content = new Content(new ArrayList<>());

        while (true) {
            System.out.println("""
                    WELCOME TO TASK MANAGER FROM BASH
                    """);

            System.out.println("""
                    MENU - APP
                    1. ADD TASK
                    2. UPGRADE TASK
                    3. SHOW TASKS
                    4. DELETE TASK
                    5. EXIT APP
                    """);


            System.out.print("Enter your option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> {
                    System.out.println("Enter the task description: ");
                    String description = scanner.nextLine();
                    Task newTask = new Task(null, description, null, null, null);
                    content.add(newTask);
                }

                case 2 -> {
                    System.out.println("Enter the task ID to update:");
                    String taskId = scanner.nextLine();
                    System.out.println(
                            """
                            What do you want to update?
                            1. Description
                            2. Status
                            """
                    );
                    int taskOption = scanner.nextInt();
                    scanner.nextLine();

                    content.update(taskId, taskOption);

                }

                case 3 -> {
                    System.out.println(
                            """
                             1. Show all tasks
                             2. Show Done tasks
                             3. Show In Progress tasks
                             4. Show Started tasks       
                            """);
                    String taskOption = scanner.nextLine();

                    content.getTaskList(taskOption);

                }
                case 4 -> {
                    System.out.println("Enter the task ID to delete:");
                    String taskId = scanner.nextLine();
                    content.delete(taskId);
                }

                case 5 -> {
                    System.out.println("Exiting the application...");
                    return;
                }
                default -> System.out.println("Invalid option, try again.");
            }
        }
    }
}