package com.srh_heidelberg;

        import com.srh_heidelberg.model.DateCalculations;
        import com.srh_heidelberg.model.PoolDetails;

        import java.sql.Connection;
        import java.sql.PreparedStatement;
        import java.sql.SQLException;
        import java.util.Date;
        import java.util.Scanner;
public class UpdatePoolDetails {

    private static Scanner scanner = new Scanner(System.in);
    private static Connection connection;
    private static PreparedStatement preparedStatement = null;




    public void updatePoolName() {
        System.out.println("Enter Pool Name to Update: ");
        String poolName = scanner.next();
        System.out.println("Enter Pool ID of the Pool: ");
        int poolID = scanner.nextInt();
        try {
            connection =  new DatabaseConnection().getDatabaseConnection(connection);
            preparedStatement = connection.prepareStatement("UPDATE  pooldetails  SET  PoolName = ? WHERE PoolID= ?");
            preparedStatement.setString(1, poolName);
            preparedStatement.setInt(2, poolID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDuration() {
        System.out.println("Enter Duration to Update: ");
        int duration = scanner.nextInt();
        System.out.println("Enter Pool ID of the Pool: ");
        int poolID = scanner.nextInt();
        try {
            connection =  new DatabaseConnection().getDatabaseConnection(connection);
            preparedStatement = connection.prepareStatement("UPDATE  pooldetails  SET  Duration = ? WHERE PoolID= ?");
            preparedStatement.setInt(1, duration);
            preparedStatement.setInt(2, poolID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateStrength() {
        System.out.println("Enter Strength to Update: ");
        int strength = scanner.nextInt();
        System.out.println("Enter Pool ID of the Pool: ");
        int poolID = scanner.nextInt();
        try {
            connection =  new DatabaseConnection().getDatabaseConnection(connection);
            preparedStatement = connection.prepareStatement("UPDATE  pooldetails  SET  Strength = ? WHERE PoolID= ?");
            preparedStatement.setInt(1, strength);
            preparedStatement.setInt(2, poolID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCurrentCounter() {
        System.out.println("Enter Current Counter to Update: ");
        int currentCounter = scanner.nextInt();
        System.out.println("Enter Pool ID of the Pool: ");
        int poolID = scanner.nextInt();
        try {
            connection =  new DatabaseConnection().getDatabaseConnection(connection);
            preparedStatement = connection.prepareStatement("UPDATE  pooldetails  SET  CurrentCounter = ? WHERE PoolID= ?");
            preparedStatement.setInt(1, currentCounter);
            preparedStatement.setInt(2, poolID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateIndividualShare() {
        System.out.println("Enter Individual Share to Update: ");
        double individualShare = scanner.nextDouble();
        System.out.println("Enter Pool ID of the Pool: ");
        int poolID = scanner.nextInt();
        try {
            connection =  new DatabaseConnection().getDatabaseConnection(connection);
            preparedStatement = connection.prepareStatement("UPDATE  pooldetails  SET  IndividualShare = ? WHERE PoolID= ?");
            preparedStatement.setDouble(1, individualShare);
            preparedStatement.setInt(2, poolID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMonthlyTakeaway() {
        System.out.println("Enter Monthly Takeaway to Update: ");
        double monthlyTakeaway = scanner.nextDouble();
        System.out.println("Enter Pool ID of the Pool: ");
        int poolID = scanner.nextInt();
        try {
            connection =  new DatabaseConnection().getDatabaseConnection(connection);
            preparedStatement = connection.prepareStatement("UPDATE  pooldetails  SET  MonthlyTakeaway = ? WHERE PoolID= ?");
            preparedStatement.setDouble(1, monthlyTakeaway);
            preparedStatement.setInt(2, poolID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateStartDate() {
        System.out.println("Enter Start Date to Update: ");
        String startDate = scanner.next();
        java.util.Date javaStartdate = DateCalculations.stringToDateParse(startDate);
        java.sql.Date sqlStartDate = new java.sql.Date(javaStartdate.getTime());
        System.out.println("Enter Pool ID of the Pool: ");
        int poolID = scanner.nextInt();
        try {
            connection =  new DatabaseConnection().getDatabaseConnection(connection);
            preparedStatement = connection.prepareStatement("UPDATE  pooldetails  SET  StartDate = ? WHERE PoolID= ?");
            preparedStatement.setDate(1, sqlStartDate);
            preparedStatement.setInt(2, poolID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEndDate() {
        System.out.println("Enter End Date to Update: ");
        String endDate = scanner.next();
        java.util.Date javaEnddate = DateCalculations.stringToDateParse(endDate);
        java.sql.Date sqlEndDate = new java.sql.Date(javaEnddate.getTime());
        System.out.println("Enter Pool ID of the Pool: ");
        int poolID = scanner.nextInt();
        try {
            connection =  new DatabaseConnection().getDatabaseConnection(connection);
            preparedStatement = connection.prepareStatement("UPDATE  pooldetails  SET  EndDate = ? WHERE PoolID= ?");
            preparedStatement.setDate(1, sqlEndDate);
            preparedStatement.setInt(2, poolID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMeetupDate() {
        System.out.println("Enter Meetup Date to Update: ");
        int meetUpdate = scanner.nextInt();
        System.out.println("Enter Pool ID of the Pool: ");
        int poolID = scanner.nextInt();
        try {
            connection =  new DatabaseConnection().getDatabaseConnection(connection);
            preparedStatement = connection.prepareStatement("UPDATE  pooldetails  SET  MeetupDate = ? WHERE PoolID= ?");
            preparedStatement.setInt(1, meetUpdate);
            preparedStatement.setInt(2, poolID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateDepositDate() {
        System.out.println("Enter Deposit Date to Update: ");
        int depositDate = scanner.nextInt();
        System.out.println("Enter Pool ID of the Pool: ");
        int poolID = scanner.nextInt();
        try {
            connection =  new DatabaseConnection().getDatabaseConnection(connection);
            preparedStatement = connection.prepareStatement("UPDATE  pooldetails  SET  DepositDate = ? WHERE PoolID= ?");
            preparedStatement.setInt(1, depositDate);
            preparedStatement.setInt(2, poolID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateLateFeeCharge() {
        System.out.println("Enter Late Fee Charge to Update: ");
        int lateFeeCharge = scanner.nextInt();
        System.out.println("Enter Pool ID of the Pool: ");
        int poolID = scanner.nextInt();
        try {
            connection =  new DatabaseConnection().getDatabaseConnection(connection);
            preparedStatement = connection.prepareStatement("UPDATE  pooldetails  SET  LateFeeCharge = ? WHERE PoolID= ?");
            preparedStatement.setInt(1, lateFeeCharge);
            preparedStatement.setInt(2, poolID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

