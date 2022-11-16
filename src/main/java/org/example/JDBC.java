package org.example;

import java.sql.*;
import java.util.ArrayList;

public class JDBC {
    public static void main(String[] args)throws SQLException {

        //load JDBC driver
        try{
            Class.forName("org.sqlite.JDBC");

        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:lesson8db.db"); //С помощью метода  getConnection указываем подключение к наей базе данных jdbc:sqlite:lesson8db.db
        Statement statement = connection.createStatement()){ // Обьек выполнения запросов
        }
    }
    //Для хранения скомпилированных запросов
    private static void performPreparedStatement (Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO weather(city,localDate,temperature) VALUES(?,?,?)")) {//запрос с неопределёнными переменными
            for (int i = 1; i < 10; i++) {
                preparedStatement.setString(1, "Moscow" + i); //Вызываем запрос и заменяем прееменные к 1 переменной
                preparedStatement.setInt(2, i); // Обращаемся ко 2 переменной
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();//Вызываем весь update
        }
    }
    //Удаление записей
    private static void performDeleteRows (Statement statement)throws SQLException {
        statement.executeUpdate("DELETE FROM weatherForecast WHERE id>200");
    }
    //Обновление записей
    private static void performUpdateDB (Statement statement) throws SQLException {
        performUpdateWeather("UPDATE weatherForecast SET score = +78 WHERE id == 100", statement);
    }
    private static void performUpdateWeather (String s, Statement statement)throws SQLException {
        statement.executeUpdate(s);
    }
    //Чтобы в таьлице появились записи
    private  static void populateDB(Statement statement, Connection connection) throws SQLException {
        long start = System.currentTimeMillis();
        connection.setAutoCommit(false);//Запрещаем автоматические коммиты.
        for (int i = 1; i < 10_000; i++) {
            statement.executeUpdate(
                    "INSERT INTO weatherForecast(city,localDate,temperature)VALUES ('weather" + i + "'," + i + ");"
            );

        }
        connection.commit();//Вызываем комит чтобы завершить процесс в этом блоуке
        System.out.println(System.currentTimeMillis() - start);
    }

    private static  void performDropTable(Statement statement) throws  SQLException{ // Удаляем все данные из таблицы
        statement.executeUpdate("DROP TABLE IF EXISTS students");
    }

    //Создаем таблицу если она не существует и создаём структуру таблицы
    private static void  performCreateDB(Statement statement)throws SQLException{
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS weatherForecast(id INTEGER PRIMARY KEY AUTOINCREMENT,"+ //Говори что есть id типа INTEGER
                "city STRING,localDate INTEGER,temperature INTEGER NOT NULL);"); // Поле name ТИПа string и поле score типа INTEGER и не может быть нулём NOT NULL
    }

    //Чтение данных
            private static void readStudentsFromDB (Statement statement)trows SQLException {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM weatherForecast");

                ArrayList<MyClass> arrayList = new ArrayList<>();
                while (resultSet.next()) {
                    System.out.println(
                            resultSet.getInt(1) + " - " +
                                    resultSet.getString("city") + " - " +
                                    resultSet.getInt("localDate") + " - "+
                                    resultSet.getInt("temperature") + " - "


                    );
                    //Ручная дессеарилизация список заначений из базы SQL
                    arrayList.add(new MyClass(resultSet.getInt(1), resultSet.getString("city"), resultSet.getInt("localDate"),resultSet.getInt("temperature")));
                }
                System.out.println("");
            }
            public static class MyClass {
                private Integer id;
                private String city;
                private Integer localDate;
                private Integer temperature;


                private

                public MyClass(Integer id, String name, Double score) {
                    this.id = id;
                    this.city = city;
                    this.localDate = localDate;
                    this.temperature=temperature;
                }
            }
        }
//city
//localDate
//temperature
