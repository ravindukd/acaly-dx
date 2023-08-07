package com.iitprojects.acaly.dx;

import com.iitprojects.acaly.dx.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDao {

    public static Task selectedTask;

    public static List<Task> getAllTasks(int cat) throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT id, title, description, color FROM Tasks where category = " + cat;

        if (cat == 0) {
            sql = "SELECT id, title, description, color FROM Tasks";
        }
        try {
            Connection connection = DatabaseUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("title");
                String desc = resultSet.getString("description");
                String color = resultSet.getString("color");
                tasks.add(new Task(id, name, desc, color));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return tasks;

    }

    public static List<Category> getAllCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT id, name, description FROM Category order by id";

        try (Connection connection = DatabaseUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            int i = 0;
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String desc = resultSet.getString("description");
                categories.add(new Category(id, name, desc));
                i++;
            }

        }
        return categories;
    }

    public static List<Step> getAllSteps(int id) {
        List<Step> steps = new ArrayList<>();
        String sql = "SELECT id, title, description, type, prompt, instruction FROM Steps where taskId=" + id + ";";
        try {
            Connection connection = DatabaseUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int stepId = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String desc = resultSet.getString("description");
                String type = resultSet.getString("type");
                String prompt = resultSet.getString("prompt");
                String instruction = resultSet.getString("instruction");
                steps.add(new Step(stepId, title, desc, type, prompt, instruction, id));
            }
            return steps;

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
}
