package com.iitprojects.acaly.dx;

import com.iitprojects.acaly.dx.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDao {
    
    public static int selectedID;

    public static List<Task> getAllTasks(int cat) throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT title, description FROM Tasks where category=" + cat;

        if (cat == 0) {
            sql = "SELECT title, description FROM Tasks";
        }
        try (Connection connection = DatabaseUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            int i = 0;
            while (resultSet.next()) {
                String name = resultSet.getString("title");
                String desc = resultSet.getString("description");
                for (int j = 0; j < 5; j++) {
                    tasks.add(new Task(j, name, desc));
                }
                i++;
            }

        }
        System.out.println(tasks);
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
}
