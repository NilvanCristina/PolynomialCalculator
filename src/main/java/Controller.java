import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private View view;
    private Operation operation;

    public Controller(View view) {
        this.view = view;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getCalculateButton()) {
            String viewFirstTextField = view.getFirstTextField();
            String viewSecondTextField = view.getSecondTextField();
            String chosenOperation = String.valueOf(view.getFromComboBox().getSelectedItem());

            Polinom firstPolynomial = new Polinom();
            Polinom secondPolynomial = new Polinom();

            firstPolynomial.getPolynomialFromString(viewFirstTextField);
            secondPolynomial.getPolynomialFromString(viewSecondTextField);

            operation = new Operation();

            if (chosenOperation.equals("Divide")) {
                DivisionPair resultPair = operation.divide(firstPolynomial, secondPolynomial);
                Polinom remainder = resultPair.getRemainder();
                Polinom quotient = resultPair.getQuotient();

                view.setResultTextField(remainder.generateStringFromPolynomial());
                view.setQuotientTextField(quotient.generateStringFromPolynomial());
            } else {
                Polinom resultPolynomial = operation.chooseOperation(chosenOperation, firstPolynomial, secondPolynomial);
                view.setResultTextField(resultPolynomial.generateStringFromPolynomial());
            }
        }

        if (e.getSource() == view.getResetAllButton()) {
            view.resetAll();
        }

        if (e.getSource() == view.getResetResultButton()) {
            view.resetResult();
        }
    }
}
