public class Operation {
    private final String[] OPERATION_OPTIONS = new String[] {"Add", "Subtract", "Multiply", "Divide", "Derive First Polynomial",
            "Derive Second Polynomial", "Integrate First Polynomial", "Integrate Second Polynomial"};

    public String[] getOperationOptions() {
        return OPERATION_OPTIONS;
    }

    private Polinom addSameCoefficientMonomials(Polinom firstPolynomial, Polinom secondPolynomial, String operation) {
        Polinom resultPolynomial = new Polinom();

        for (Monom first :
                firstPolynomial.getPolynomial()) {
            for (Monom second :
                    secondPolynomial.getPolynomial()) {
                if (first.getDegree() == second.getDegree()) {
                    double auxiliaryCoefficient = 0;

                    if (operation.equals("+"))
                        auxiliaryCoefficient = first.getCoefficient() + second.getCoefficient();

                    if (operation.equals("-"))
                        auxiliaryCoefficient = first.getCoefficient() - second.getCoefficient();

                    if (auxiliaryCoefficient != 0) {
                        Monom newMonomial = new Monom(first.getDegree(), auxiliaryCoefficient);
                        resultPolynomial.addMonomial(newMonomial);
                    }

                    second.setAdded(true);
                    first.setAdded(true);
                }
            }
        }

        return resultPolynomial;
    }

    private Polinom addRemainingMonomials(Polinom givenPolynomial) {
        Polinom resultPolynomial = new Polinom();

        for (Monom monomial :
                givenPolynomial.getPolynomial())
            if (!monomial.isAdded())
                resultPolynomial.addMonomial(monomial);

        return resultPolynomial;
    }

    public Polinom add(Polinom firstPolynomial, Polinom secondPolynomial) {
        Polinom resultPolynomial = new Polinom();

        resultPolynomial.addPolynomial(addSameCoefficientMonomials(firstPolynomial, secondPolynomial, "+"));
        resultPolynomial.addPolynomial(addRemainingMonomials(firstPolynomial));
        resultPolynomial.addPolynomial(addRemainingMonomials(secondPolynomial));
        resultPolynomial.sortPolynomial();

        return resultPolynomial;
    }

    public Polinom subtract(Polinom firstPolynomial, Polinom secondPolynomial) {
        Polinom resultPolynomial = new Polinom();

        resultPolynomial.addPolynomial(addSameCoefficientMonomials(firstPolynomial, secondPolynomial, "-"));
        resultPolynomial.addPolynomial(addRemainingMonomials(firstPolynomial));

        for (Monom second :
                secondPolynomial.getPolynomial()) {
            boolean found = false;

            for (Monom first :
                    firstPolynomial.getPolynomial()) {
                if (first.getDegree() == second.getDegree()) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                double auxiliaryCoefficient = -(second.getCoefficient());
                Monom newMonomial = new Monom(second.getDegree(), auxiliaryCoefficient);
                resultPolynomial.addMonomial(newMonomial);
            }
        }

        resultPolynomial.sortPolynomial();

        return resultPolynomial;
    }

    private boolean checkForDegree(int degree, Polinom polynomial) {
        boolean found = false;

        for (Monom monomial:
                polynomial.getPolynomial()) {
            if (monomial.getDegree() == degree) {
                found = true;
                break;
            }
        }

        return found;
    }

    private Polinom simplifyPolynomial(Polinom givenPolynomial) {
        Polinom resultPolynomial = new Polinom();

        for (int i = 0; i < givenPolynomial.getPolynomial().size(); i++) {
            boolean found = false;
            double sumOfCoefficients = givenPolynomial.getPolynomial().get(i).getCoefficient();
            int degree = givenPolynomial.getPolynomial().get(i).getDegree();

            if (!checkForDegree(degree, resultPolynomial)) {
                for (int j = i + 1; j < givenPolynomial.getPolynomial().size(); j++) {
                    if (givenPolynomial.getPolynomial().get(i).getDegree() == givenPolynomial.getPolynomial().get(j).getDegree()) {
                        sumOfCoefficients += givenPolynomial.getPolynomial().get(j).getCoefficient();
                        found = true;
                    }
                }

                if (found) {
                    int auxiliaryDegree = givenPolynomial.getPolynomial().get(i).getDegree();
                    Monom newMonomial = new Monom(auxiliaryDegree, sumOfCoefficients);

                    resultPolynomial.addMonomial(newMonomial);
                } else {
                    resultPolynomial.addMonomial(givenPolynomial.getPolynomial().get(i));
                }
            }
        }

        return resultPolynomial;
    }

    private Polinom removeZero(Polinom givenPolynomial) {
        Polinom resultPolynomial = new Polinom();

        for (Monom monomial:
             givenPolynomial.getPolynomial()) {
           if (monomial.getCoefficient() != 0)
               resultPolynomial.addMonomial(monomial);
        }

        return resultPolynomial;
    }

    public Polinom multiply(Polinom firstPolynomial, Polinom secondPolynomial) {
        Polinom auxiliaryPolynomial = new Polinom();

        for (Monom first :
                firstPolynomial.getPolynomial()) {
            for (Monom second :
                    secondPolynomial.getPolynomial()) {
                int auxiliaryDegree = first.getDegree() + second.getDegree();
                double auxiliaryCoefficient = first.getCoefficient() * second.getCoefficient();
                Monom newMonomial = new Monom(auxiliaryDegree, auxiliaryCoefficient);
                auxiliaryPolynomial.addMonomial(newMonomial);
            }
        }

        Polinom resultPolynomial = simplifyPolynomial(auxiliaryPolynomial);
        resultPolynomial = removeZero(resultPolynomial);
        resultPolynomial.sortPolynomial();

        return resultPolynomial;
    }

    public Polinom multiplyWithMonomial(Polinom polynomial, Monom monomial) {
        Polinom resultPolynomial = new Polinom();

        for (Monom auxiliaryMonomial:
             polynomial.getPolynomial()) {
            int degree = auxiliaryMonomial.getDegree() + monomial.getDegree();
            double coefficient = auxiliaryMonomial.getCoefficient() * monomial.getCoefficient();

            if (coefficient != 0) {
                Monom newMonomial = new Monom(degree, coefficient);
                resultPolynomial.addMonomial(newMonomial);
            }
        }

        return resultPolynomial;
    }

    public DivisionPair divide(Polinom firstPolynomial, Polinom secondPolynomial) {
        DivisionPair result = new DivisionPair();
        Polinom remainder = new Polinom();

        while (firstPolynomial.getPolynomialDegree() >= secondPolynomial.getPolynomialDegree()) {
            Monom firstPolynomialMax = firstPolynomial.getMonomialMax();
            Monom secondPolynomialMax = secondPolynomial.getMonomialMax();
            Monom newMonomial = firstPolynomialMax.divideMonomials(secondPolynomialMax);

            remainder.addMonomial(newMonomial);

            Operation operation = new Operation();
            Polinom auxiliaryPolynomial = operation.multiplyWithMonomial(secondPolynomial, newMonomial);

            firstPolynomial = operation.subtract(firstPolynomial, auxiliaryPolynomial);
        }

        result.setRemainder(remainder);
        result.setQuotient(firstPolynomial);

        return result;
    }

    public Polinom derive(Polinom polynomial) {
        Polinom resultPolynomial = new Polinom();

        for (Monom monomial:
             polynomial.getPolynomial()) {
            if (monomial.getDegree() > 0) {
                int auxiliaryDegree = monomial.getDegree() - 1;
                double auxiliaryCoefficient = monomial.getDegree() * monomial.getCoefficient();
                Monom newMonomial = new Monom(auxiliaryDegree, auxiliaryCoefficient);
                resultPolynomial.addMonomial(newMonomial);
            }

            if (monomial.getDegree() == 0) {
                Monom newMonomial = new Monom(0, 0);
                resultPolynomial.addMonomial(newMonomial);
            }
        }

        return resultPolynomial;
    }

    public Polinom integrate(Polinom polynomial) {
        Polinom resultPolynomial = new Polinom();

        for (Monom monomial:
                polynomial.getPolynomial()) {
            int auxiliaryGrad = monomial.getDegree() + 1;
            double auxiliaryCoefficient = monomial.getCoefficient() / (monomial.getDegree() + 1);
            Monom newMonomial = new Monom(auxiliaryGrad, auxiliaryCoefficient);
            resultPolynomial.addMonomial(newMonomial);
        }

        return resultPolynomial;
    }

    public Polinom chooseOperation(String option, Polinom firstPolynomial, Polinom secondPolynomial) {
        Polinom resultPolynomial = new Polinom();

        switch (option) {
            case "Add" -> resultPolynomial = add(firstPolynomial, secondPolynomial);
            case "Subtract" -> resultPolynomial = subtract(firstPolynomial, secondPolynomial);
            case "Multiply" -> resultPolynomial = multiply(firstPolynomial, secondPolynomial);
            case "Derive First Polynomial" -> resultPolynomial = derive(firstPolynomial);
            case "Derive Second Polynomial" -> resultPolynomial = derive(secondPolynomial);
            case "Integrate First Polynomial" -> resultPolynomial = integrate(firstPolynomial);
            case "Integrate Second Polynomial" -> resultPolynomial = integrate(secondPolynomial);
            default -> {
            }
        }

        return resultPolynomial;
    }
}
