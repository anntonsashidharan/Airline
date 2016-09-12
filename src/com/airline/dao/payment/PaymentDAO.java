package com.airline.dao.payment;

import com.airline.domain.payment.Payment;
import com.airline.system.APPStatics;
import com.airline.util.db.DBConnection;

import java.sql.*;
import java.util.Date;

/**
 * Created by JJ on 8/30/16.
 */
public class PaymentDAO {
    private Connection connection;

    public PaymentDAO(Connection connection) {
        this.connection = connection;
    }

    public Payment createPayment(Payment payment) throws Exception {

        String sqlNextPaymentNumber = "SELECT nextval('" + APPStatics.schemaName + ".\"seqPaymentNumberGenerator\"')";
        String sqlInsertIntoTicket = "INSERT INTO " + APPStatics.schemaName + ".\"PAYMENT\" " +
                "(payment_id,paypal_account,payed_amount,payed_date,payed_time) VALUES(?,?,?,?,?)";


        PreparedStatement statement1 = connection.prepareStatement(sqlNextPaymentNumber);
        ResultSet resultSet1 = statement1.executeQuery();
        resultSet1.next();
        int nextPaymentNumber = resultSet1.getInt("nextval");


        PreparedStatement statement2 = connection.prepareStatement(sqlInsertIntoTicket);
        String paymentNumber = APPStatics.PAYMENT_NUMBER_PREFIX + String.format("%06d", nextPaymentNumber);
        payment.setPaymentID(paymentNumber);


        statement2.setString(1, paymentNumber);
        statement2.setString(2, payment.getPaypalUserName());
        statement2.setFloat(3, payment.getAmount());
        Date date = new Date();
        statement2.setDate(4, new java.sql.Date(date.getTime()));
        statement2.setTime(5, new Time(date.getTime()));
        statement2.execute();

        return payment;
    }

}
