package org.example.Content;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.example.util.fileUtil;

public class Content {

    public Content(ArrayList<Task> taskList) {
    }

    public void add(Task newTask) throws IOException {

        List<String> lines = fileUtil.readFromFile(Path.of("taskRecord.txt"));
        int maxId = 0;
        for (String line : lines) {
            try {
                Task t = new Task(null, null, null, null, null).toTask(line);
                int id = Integer.parseInt(t.getId());
                if (id > maxId) maxId = id;
            } catch (Exception ignored) {}
        }

        newTask.setId(String.valueOf(maxId + 1));
        newTask.setStatus(taskStatus.STARTED);
        newTask.setDateCreated(new Date());
        newTask.setDateUpdated(new Date());

        fileUtil.writeToFile(newTask.toString());
    }

    public void update (String taskId, int taskOption) throws IOException {

        List<String> lines = fileUtil.readFromFile(Path.of("taskRecord.txt"));
        int indexToUpdate = -1;
        Task task = null;

        for (int i = 0; i < lines.size(); i++) {
            Task tempTask = new Task(null, null, null, null, null).toTask(lines.get(i));
            if (tempTask.getId().equals(taskId)) {
                indexToUpdate = i;
                task = tempTask;
                break;
            }
        }

        if (indexToUpdate == -1) {
            System.out.println("Tarea no encontrada.");
            return;
        }

        task.setDateUpdated(new Date());

        if(taskOption == 1){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the new description:");
            String newDescription = scanner.nextLine();

            task.setDescription(newDescription);
        } else if (taskOption == 2) {
            if(task.getStatus() == taskStatus.STARTED){
                task.setStatus(taskStatus.IN_PROGRESS);
            } else if (task.getStatus() == taskStatus.IN_PROGRESS) {
                task.setStatus(taskStatus.COMPLETED);
            } else {
                System.out.println("Task is already completed.");
            }
        }

        lines.set(indexToUpdate, task.toString());

        fileUtil.saveAll(lines);
    }

    public ArrayList<Task> getTaskList(String taskOption) throws IOException {
        List<String> lines = fileUtil.readFromFile(Path.of("taskRecord.txt"));

        if ("1".equals(taskOption)) {
            for (String line : lines) {
                System.out.println(line);
            }
            return null;
        }

        for(String line : lines){
            Task task = new Task(null, null, null, null, null).toTask(line);
            switch (taskOption) {
                case "2":
                    if (task.getStatus() == taskStatus.COMPLETED) {
                        System.out.println(line);
                    }
                    break;
                case "3":
                    if (task.getStatus() == taskStatus.IN_PROGRESS) {
                        System.out.println(line);
                    }
                    break;
                case "4":
                    if (task.getStatus() == taskStatus.STARTED) {
                        System.out.println(line);
                    }
                    break;
                default:
                    break;
            }
        }

        return null;
    }

    public void delete(String taskId) throws IOException {
        List<String> lines = fileUtil.readFromFile(Path.of("taskRecord.txt"));
        int indexToRemove = -1;

        for (int i = 0; i < lines.size(); i++) {
            Task tempTask = new Task(null, null, null, null, null).toTask(lines.get(i));
            if (tempTask.getId().equals(taskId)) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove != -1) {
            lines.remove(indexToRemove);
            fileUtil.saveAll(lines);
            System.out.println("Task deleted successfully.");
        } else {
            System.out.println("Task not found.");
        }
    }

}
