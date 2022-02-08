package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import sample.util.Const;
import sample.util.DBHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static java.sql.JDBCType.NULL;

public class OrganizationDAO {

    public static ObservableList<Organization> searchOrganizations () throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM " + Const.ORG_TABLE;

        try {
            ResultSet rsOrgs = DBHandler.dbExecuteQuery(selectStmt);
            ObservableList<Organization> orgList = getOrganizationsList(rsOrgs);
            return orgList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }


    private static ObservableList<Organization> getOrganizationsList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<Organization> orgList = FXCollections.observableArrayList();
        while (rs.next()) {
            Organization org = new Organization();
            org.setOrg_id(rs.getInt("id"));
            org.setOrg_name(rs.getString("name_org"));
            org.setActivity(rs.getString("activity"));
            String email = rs.getString("email");
            if(rs.wasNull())
                org.setEmail("-");
            else
                org.setEmail(email);
            String sur = rs.getString("director_sur");
            if(rs.wasNull())
                org.setDir_surname("-");
            else
                org.setDir_surname(sur);
            org.setCountry(rs.getString("country"));
            org.setCity(rs.getString("city"));
            org.setStreet(rs.getString("street_and_house"));
            orgList.add(org);
        }
        return orgList;
    }


    public static ObservableList<Organization> searchNumbers() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT tel_numbers.id,organizations.name_org,tel_numbers.number,tel_numbers.functionality,organizations.country,organizations.city,organizations.street_and_house\n" +
                "FROM tel_numbers INNER JOIN organizations \n" +
                "ON organizations.id = tel_numbers.id\n" +
                "ORDER BY tel_numbers.id";

        try {
            ResultSet rsOrgs = DBHandler.dbExecuteQuery(selectStmt);
            ObservableList<Organization> orgList = getNumbersList(rsOrgs);
            return orgList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }


    private static ObservableList<Organization> getOrganizationsWithNumbersList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<Organization> orgList = FXCollections.observableArrayList();
        while (rs.next()) {
            Organization org = new Organization();
            org.setOrg_id(rs.getInt("id"));
            org.setOrg_name(rs.getString("name_org"));
            org.setPhone_number(rs.getString("number"));
            String func = rs.getString("functionality");
            if(rs.wasNull())
                org.setPhone_functionality("-");
            else
                org.setPhone_functionality(func);
            org.setActivity(rs.getString("activity"));
            org.setCountry(rs.getString("country"));
            org.setCity(rs.getString("city"));
            org.setStreet(rs.getString("street_and_house"));
            orgList.add(org);
        }
        return orgList;
    }


    private static ObservableList<Organization> getNumbersList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<Organization> orgList = FXCollections.observableArrayList();
        while (rs.next()) {
            Organization org = new Organization();
            org.setOrg_id(rs.getInt("id"));
            org.setOrg_name(rs.getString("name_org"));
            org.setPhone_number(rs.getString("number"));
            String func = rs.getString("functionality");
            if(rs.wasNull())
                org.setPhone_functionality("-");
            else
                org.setPhone_functionality(func);
            org.setCountry(rs.getString("country"));
            org.setCity(rs.getString("city"));
            org.setStreet(rs.getString("street_and_house"));
            orgList.add(org);
        }
        return orgList;
    }

    public static ObservableList<Organization> searchOrgByNumber(String number) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT organizations.id, organizations.name_org,tel_numbers.number,tel_numbers.functionality,organizations.activity,organizations.country,organizations.city,organizations.street_and_house\n" +
                "FROM organizations\n" +
                "INNER JOIN tel_numbers\n" +
                "\tON organizations.id = tel_numbers.id\n" +
                "\tWHERE tel_numbers.number = '" + number + "'";

        try {
            ResultSet rsOrgs = DBHandler.dbExecuteQuery(selectStmt);
            ObservableList<Organization> orgList = getOrganizationsWithNumbersList(rsOrgs);
            return orgList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }


    public static ObservableList<Organization> searchNumberByOrg(List<String> list) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT organizations.id, organizations.name_org,tel_numbers.number,tel_numbers.functionality,organizations.activity,organizations.country,organizations.city,organizations.street_and_house\n" +
                "FROM tel_numbers\n" +
                "INNER JOIN organizations\n" +
                "\tON organizations.id = tel_numbers.id";

        if(list.size()>0){
            selectStmt += " WHERE " + String.join(" AND ",list);
        }

        try {
            ResultSet rsOrgs = DBHandler.dbExecuteQuery(selectStmt);
            ObservableList<Organization> orgList = getOrganizationsWithNumbersList(rsOrgs);
            return orgList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    public static ObservableList<Organization> searchBasicInf(List<String> list) throws SQLException, ClassNotFoundException {
        String dropStmt = "DROP TABLE IF EXISTS BasicInf";
        String createStmt = "CREATE TABLE BasicInf (\n" +
                "id INT,\n" +
                "name_org VARCHAR(70),\n" +
                "activity VARCHAR(100),\n" +
                "email VARCHAR(256),\n" +
                "director_sur VARCHAR(54),\n" +
                "country VARCHAR(48),\n" +
                "city VARCHAR(25),\n" +
                "street_and_house VARCHAR(80)\n" +
                ")";
        String insertStmt = "INSERT INTO BasicInf SELECT DISTINCT organizations.id,organizations.name_org,organizations.activity,organizations.email,organizations.director_sur,\n" +
                "organizations.country,organizations.city,organizations.street_and_house\n" +
                "FROM (price_list,time_work)\n" +
                "INNER JOIN organizations \n" +
                "ON organizations.id = price_list.id AND organizations.id = time_work.id";

        if(list.size()>0){
            insertStmt += " WHERE " + String.join(" AND ",list);
        }

        String selectStmt = "SELECT * FROM BasicInf";

        try {
            DBHandler.useStmt(dropStmt);
            DBHandler.useStmt(createStmt);
            DBHandler.useStmt(insertStmt);
            ResultSet rsOrgs = DBHandler.dbExecuteQuery(selectStmt);
            ObservableList<Organization> orgList = getOrganizationsList(rsOrgs);
            return orgList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    public static ObservableList<Organization> searchPhonesWithBasicInf() throws SQLException, ClassNotFoundException {
        String selectStmt = "Select tel_numbers.id, BasicInf.name_org ,tel_numbers.number,tel_numbers.functionality\n" +
                "FROM tel_numbers\n" +
                "INNER JOIN BasicInf\n" +
                "ON BasicInf.id = tel_numbers.id";
        try {
            ResultSet rsOrgs = DBHandler.dbExecuteQuery(selectStmt);
            ObservableList<Organization> phoneList = getNumbersFromBasicInf(rsOrgs);
            return phoneList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    private static ObservableList<Organization> getNumbersFromBasicInf(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<Organization> orgList = FXCollections.observableArrayList();
        while (rs.next()) {
            Organization org = new Organization();
            org.setOrg_id(rs.getInt("id"));
            org.setOrg_name(rs.getString("name_org"));
            org.setPhone_number(rs.getString("number"));
            String func = rs.getString("functionality");
            if(rs.wasNull())
                org.setPhone_functionality("-");
            else
                org.setPhone_functionality(func);
            orgList.add(org);
        }
        return orgList;
    }


    public static ObservableList<Organization> searchServiceAndPriceWithBasicInf() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT price_list.id,BasicInf.name_org,price_list.service,price_list.price\n" +
                "FROM price_list\n" +
                "INNER JOIN BasicInf\n" +
                "ON BasicInf.id = price_list.id";
        try {
            ResultSet rsOrgs = DBHandler.dbExecuteQuery(selectStmt);
            ObservableList<Organization> serviceList = getServiceAndPriceFromBasicInf(rsOrgs);
            return serviceList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    private static ObservableList<Organization> getServiceAndPriceFromBasicInf(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<Organization> orgList = FXCollections.observableArrayList();
        while (rs.next()) {
            Organization org = new Organization();
            org.setOrg_id(rs.getInt("id"));
            org.setOrg_name(rs.getString("name_org"));
            org.setService(rs.getString("service"));
            org.setPrice(rs.getDouble("price"));
            orgList.add(org);
        }
        return orgList;
    }

    public static ObservableList<Organization> searchGraphicWithBasicInf() throws SQLException, ClassNotFoundException {
        String dropStmt = "DROP TABLE IF EXISTS BasicInf";
        String selectStmt = "SELECT time_work.id,BasicInf.name_org,time_work.day_week,time_work.weekend,\n" +
                "time_work.from_time,time_work.up_time\n" +
                "FROM time_work\n" +
                "INNER JOIN BasicInf \n" +
                "ON BasicInf.id = time_work.id";
        try {
            ResultSet rsOrgs = DBHandler.dbExecuteQuery(selectStmt);
            DBHandler.useStmt(dropStmt);
            ObservableList<Organization> graphicList = getGraphicFromBasicInf(rsOrgs);
            return graphicList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    private static ObservableList<Organization> getGraphicFromBasicInf(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<Organization> orgList = FXCollections.observableArrayList();
        while (rs.next()) {
            Organization org = new Organization();
            org.setOrg_id(rs.getInt("id"));
            org.setOrg_name(rs.getString("name_org"));
            org.setDayOfWeek(rs.getString("day_week"));
            boolean weekend = rs.getBoolean("weekend");
            if(!weekend)
                org.setWeekend("-");
            else
                org.setWeekend("Выходной");
            String start = rs.getString("from_time");
            if(weekend)
                org.setFromTime("-");
            else
                org.setFromTime(start);
            String end = rs.getString("up_time");
            if(weekend)
                org.setUpTime("-");
            else
                org.setUpTime(end);
            orgList.add(org);
        }
        return orgList;
    }

    public static ObservableList<Organization> lookHistory() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT num_history.id,organizations.name_org,num_history.old_num,num_history.new_num,num_history.date_add\n" +
                "FROM num_history\n" +
                "INNER JOIN organizations \n" +
                "ON organizations.id = num_history.id \n" +
                "ORDER BY num_history.id, num_history.date_add";

        try {
            ResultSet rsOrgs = DBHandler.dbExecuteQuery(selectStmt);
            ObservableList<Organization> orgList = getHistoryList(rsOrgs);
            return orgList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    private static ObservableList<Organization> getHistoryList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<Organization> orgList = FXCollections.observableArrayList();
        while (rs.next()) {
            Organization org = new Organization();
            org.setOrg_id(rs.getInt("id"));
            org.setOrg_name(rs.getString("name_org"));
            org.setOld_number(rs.getString("old_num"));
            org.setPhone_number(rs.getString("new_num"));
            String date = rs.getString("date_add");
            String date1 = date.replace('T',' ');
            org.setDate_add(date1);
            orgList.add(org);
        }
        return orgList;
    }


    public static ObservableList<Organization> searchOrgByOldNum(String oldNum) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT DISTINCT organizations.id, organizations.name_org,num_history.old_num,organizations.activity,organizations.country,organizations.city,organizations.street_and_house\n" +
                "FROM organizations\n" +
                "INNER JOIN num_history\n" +
                "\tON organizations.id = num_history.id\n" +
                "\tWHERE num_history.old_num = '" + oldNum + "'";

        try {
            ResultSet rsOrgs = DBHandler.dbExecuteQuery(selectStmt);
            ObservableList<Organization> orgList = getOrganizationsWithOldNum(rsOrgs);
            return orgList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    private static ObservableList<Organization> getOrganizationsWithOldNum(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<Organization> orgList = FXCollections.observableArrayList();
        while (rs.next()) {
            Organization org = new Organization();
            org.setOrg_id(rs.getInt("id"));
            org.setOrg_name(rs.getString("name_org"));
            org.setOld_number(rs.getString("old_num"));
            org.setActivity(rs.getString("activity"));
            org.setCountry(rs.getString("country"));
            org.setCity(rs.getString("city"));
            org.setStreet(rs.getString("street_and_house"));
            orgList.add(org);
        }
        return orgList;
    }

    public static void changeNumber(String oldNum, String newNum, Stage stage, Integer id) throws SQLException, ClassNotFoundException {
        String updateStmt = "UPDATE tel_numbers\n" +
                "SET number = '" + newNum +"'\n" +
                "WHERE number = '" + oldNum + "'";

        String insertStmt = "INSERT INTO num_history\n" +
                "VALUES\n" +
                "("+id+",'"+ oldNum +"','"+newNum+"', NOW())";

        try {
            DBHandler.useStmt(updateStmt);
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Данный номер уже есть в телефонной книге!\nПопробуйте еще раз!");
            alert.show();
            throw e;
        }
        try{
            DBHandler.useStmt(insertStmt);
        } catch(SQLException e){
            stage.close();
        }
    }
}
