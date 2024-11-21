package edu.ijse.mvc.finalproject.model;

import edu.ijse.mvc.finalproject.db.DBConnection;
import edu.ijse.mvc.finalproject.dto.MemberDto;
import edu.ijse.mvc.finalproject.dto.PaymentDetailDto;
import edu.ijse.mvc.finalproject.dto.tm.PaymentDetailTM;
import edu.ijse.mvc.finalproject.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HomePageModel{
    public MemberDto searchMember(String id) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from member where member_id = ?", id);

        if (rst.next()){
            MemberDto memberDto = new MemberDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getDate(6),
                    rst.getDouble(7),
                    rst.getDouble(8),
                    rst.getString(9),
                    rst.getString(10),
                    rst.getString(11)
            );
            return memberDto;
        }
        return null;
    }

    public String getPlanId(String txtPlanId) throws SQLException {
        ResultSet rst = CrudUtil.execute("select price from payment_plan where plan_id = ?", txtPlanId);

        if (rst.next()){
            String planId = rst.getString(1);
            return planId;
        }
        return null;
    }

    public ArrayList<PaymentDetailTM> loadTable(String id) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from paymentdetail where member_id = ?", id);

        ArrayList<PaymentDetailTM> paymentDetails = new ArrayList<>();
        while (rst.next()){
            PaymentDetailTM paymentDetailTM = new PaymentDetailTM(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDate(4),
                    rst.getDouble(5),
                    rst.getString(6)
            );
            paymentDetails.add(paymentDetailTM);
        }
        return paymentDetails;
    }

    public String getNewPlanId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select payment_id from payment order by payment_id desc limit 1");

        if (resultSet.next()){
            String lastId = resultSet.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("P%03d", newIdIndex);
        }
        return null;
    }

    public boolean markFee(PaymentDetailDto paymentDetailDto, String adminid) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            boolean isSavedPaymentTable = CrudUtil.execute("insert into payment values (?,?,?)",
                    paymentDetailDto.getPayment_id(),
                    paymentDetailDto.getPayment_date(),
                    adminid
                    );

            if (isSavedPaymentTable){
                System.out.println("Payment Table Saved Done...!");

                boolean isSavedPaymentDetailsTable = CrudUtil.execute("insert into paymentdetail values (?,?,?,?,?,?)",
                        paymentDetailDto.getPayment_id(),
                        paymentDetailDto.getMember_id(),
                        paymentDetailDto.getMember_name(),
                        paymentDetailDto.getPayment_date(),
                        paymentDetailDto.getPrice(),
                        paymentDetailDto.getPayment_method()
                        );

                if (isSavedPaymentDetailsTable){
                    System.out.println("Payment Details Table Saved Done...!");

                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;
        }catch (Exception e){
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
        }
    }
}
