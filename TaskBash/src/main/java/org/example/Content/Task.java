package org.example.Content;

import java.util.*;

public class Task {

    String id;
    String description;
    taskStatus status;
    Date dateCreated;
    Date dateUpdated;


    public Task(String id, String description, taskStatus status, Date dateCreated, Date dateUpdated) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    public Task toTask(String line) {
        line = line.replace("{", "").replace("}", "").trim();

        String[] parts = line.split(", ");

        String id = null;
        String description = null;
        taskStatus status = null;
        Date created = null;
        Date updated = null;

        for (String part : parts) {
            part = part.trim();
            if (part.startsWith("ID: ")) {
                id = part.substring(4);
            } else if (part.startsWith("Description: ")) {
                description = part.substring(13);
            } else if (part.startsWith("Status: ")) {
                status = taskStatus.valueOf(part.substring(8));
            } else if (part.startsWith("Created: ")) {
                created = new Date(); 
            } else if (part.startsWith("Updated: ")) {
                updated = new Date();
            }
        }
        return new Task(id, description, status, created, updated);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public taskStatus getStatus() {
        return status;
    }

    public void setStatus(taskStatus status) {
        this.status = status;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    @Override
    public String toString() {
        return "{ ID: " + id + ", Description: " + description + ", Status: " + status + ", Created: " + dateCreated + ", Updated: " + dateUpdated + "}";
    }
}
