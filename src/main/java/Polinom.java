import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Polinom {
    private List<Monom> polynomial = new ArrayList<>();

    public List<Monom> getPolynomial() {
        return polynomial;
    }

    public int getPolynomialDegree() {
        int degree = 0;

        for (Monom monom:
                polynomial) {
            if (monom.getCoefficient() != 0) {
                degree = monom.getDegree();
                break;
            }
        }

        return degree;
    }

    public Monom getMonomialMax() {
        return polynomial.get(0);
    }

    public void addMonomial(Monom monom) {
        polynomial.add(monom);
    }

    public void addPolynomial(Polinom newPolynomial) {
        polynomial.addAll(newPolynomial.getPolynomial());
    }

    public String generateStringFromPolynomial() {
        String stringPolynomial = "";

        for (Monom monomial:
                polynomial) {
            if (monomial.getCoefficient() > 0) {
                String auxiliaryString = "+" + monomial.getCoefficient() + "x^" + monomial.getDegree();
                stringPolynomial = stringPolynomial.concat(auxiliaryString);
            }
            else if (monomial.getCoefficient() < 0) {
                String auxiliaryString = monomial.getCoefficient() + "x^" + monomial.getDegree();
                stringPolynomial = stringPolynomial.concat(auxiliaryString);
            } else {
                stringPolynomial = stringPolynomial.concat("");
            }
        }

        return stringPolynomial;
    }

    public void getPolynomialFromString(String polynomialString) {
        if (!polynomialString.equals("")) {
            String replacedPlus = polynomialString.replace("-", "+-");
            String[] splitByPlus = replacedPlus.split("\\+");

            for (String splitElement :
                    splitByPlus) {
                String[] splitByX = splitElement.split("x\\^");
                Monom auxiliaryMonomial = new Monom(Integer.parseInt(splitByX[1]), Double.parseDouble(splitByX[0]));
                this.polynomial.add(auxiliaryMonomial);
            }
        }
    }

    public void sortPolynomial() {
        Collections.sort(polynomial);
    }
}