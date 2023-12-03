package org.example.repository;

import org.example.db.DatabaseUtil;
import org.example.dto.CardDTO;
import org.example.dto.ResponsDTO;
import org.example.dto.TransactionDTO;
import org.example.enums.Status;
import org.example.enums.TransactionType;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class TransactionRepository {
    public ResponsDTO creadTransaction(String cardNumber, String terminalCode, double amount, TransactionType type) {
        Connection connection = DatabaseUtil.getConnection();
        int res = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into transactions(card_number_user,amount,terminal_code,transaction_type) values (?,?,?,?)");

            preparedStatement.setString(1, cardNumber);
            preparedStatement.setDouble(2, amount);
            preparedStatement.setString(3, terminalCode);
            preparedStatement.setString(4, type.name());

            res = preparedStatement.executeUpdate();

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (res != 0) {
            return new ResponsDTO("Transaction completed👌👌👌 ", true);
        }
        return new ResponsDTO("Error occurred while the operation was being processed!!!",false);
    }


    public List<TransactionDTO> getTransactionList(String cardNumber) {
        List<TransactionDTO> transactionDTOList = new LinkedList<>();
        try {
            Connection connection = DatabaseUtil.getConnection();
            Statement statement = connection.createStatement();

            String sql = "select * from transactions where card_number_user = '%s' order by transaction_time desc ";
            sql=String.format(sql, cardNumber);
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                TransactionDTO transactionDTO = new TransactionDTO();
                transactionDTO.setCard_number(resultSet.getString("card_number_user"));
                transactionDTO.setAmount(resultSet.getDouble("amount"));
                transactionDTO.setTerminal_code(resultSet.getString("terminal_code"));
                transactionDTO.setTransactionType(TransactionType.valueOf(resultSet.getString("transaction_type")));
                transactionDTO.setTransactionTime(resultSet.getTimestamp("transaction_time").toLocalDateTime());
                transactionDTOList.add(transactionDTO);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionDTOList;

    }

    public List<TransactionDTO> gettransactionToday() {
        List<TransactionDTO> transactionDTOList = new LinkedList<>();
        try {
            Connection connection = DatabaseUtil.getConnection();
            Statement statement = connection.createStatement();

            String sql = "select * from transactions where transaction_time > now() - interval '24 hours' ";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                TransactionDTO transactionDTO = new TransactionDTO();
                transactionDTO.setCard_number(resultSet.getString("card_number_user"));
                transactionDTO.setAmount(resultSet.getDouble("amount"));
                transactionDTO.setTerminal_code(resultSet.getString("terminal_code"));
                transactionDTO.setTransactionType(TransactionType.valueOf(resultSet.getString("transaction_type")));
                transactionDTO.setTransactionTime(resultSet.getTimestamp("transaction_time").toLocalDateTime());
                transactionDTOList.add(transactionDTO);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionDTOList;
    }

    public List<TransactionDTO> getAllTransactions() {
        List<TransactionDTO> transactionDTOList = new LinkedList<>();
        try {
            Connection connection = DatabaseUtil.getConnection();
            Statement statement = connection.createStatement();

            String sql = "select * from transactions ";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                TransactionDTO transactionDTO = new TransactionDTO();
                transactionDTO.setCard_number(resultSet.getString("card_number_user"));
                transactionDTO.setAmount(resultSet.getDouble("amount"));
                transactionDTO.setTerminal_code(resultSet.getString("terminal_code"));
                transactionDTO.setTransactionType(TransactionType.valueOf(resultSet.getString("transaction_type")));
                transactionDTO.setTransactionTime(resultSet.getTimestamp("transaction_time").toLocalDateTime());
                transactionDTOList.add(transactionDTO);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionDTOList;


    }
}
