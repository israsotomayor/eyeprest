package controlador;

import dao.DAOFee;
import dominio.Fee;
import dominio.Loan;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import utils.Report;
import vistas.FeePaymentView;

public class UCCFee {

    UCCAccountingEntry uccAEntry = new UCCAccountingEntry();
    UCCLoan uccLoan = new UCCLoan();
    DAOFee df = new DAOFee();

    public List<Fee> getFeePerLoan(Loan loan) {
        //DAOFee df = new DAOFee();
        return df.getFeePerLoan(loan);
    }
    
    public void feePayment(FeePaymentView fpv, Fee fee) {

        BigDecimal feeAmount = new BigDecimal(fpv.paymentDetailFeeAmountTXT.getText());
        BigDecimal feePaymentAmount = new BigDecimal(fpv.PaymentDetailAmountTXT.getText());
        BigDecimal payFee = feePaymentAmount.subtract(feeAmount);

        fee.setFe_state("Pagado");

        if (Objects.equals(feeAmount, feePaymentAmount)) {

            df.updateFee(fee);
            //saveFeeAccountingEntry(fpv, fee);
            System.out.println("PARA SI TIENE DATOS " + fee.getFe_loan().getLn_member().getPn_first_name());
            Report.printInput(fee, fee.getFe_loan().getLn_member());
            
            fpv.dispose();
           // uccLoan.calculateLoanBalance();
            
        } else {
            
            List<Fee> feeList = df.getFeePerLoan(fee.getFe_loan());
            Fee lastFee = feeList.get(feeList.size()-1);

            lastFee.setFe_payment(lastFee.getFe_payment().subtract(payFee));
            df.updateFee(fee);
            df.updateLastFee(lastFee);
            //saveFeeAccountingEntry(fpv, fee);
            fpv.dispose();
        }
    }
}