public class DivisionPair {
    private Polinom remainder;
    private Polinom quotient;

    public DivisionPair() {
        this.remainder = new Polinom();
        this.quotient = new Polinom();
    }

    public Polinom getRemainder() {
        return remainder;
    }

    public void setRemainder(Polinom remainder) {
        this.remainder = remainder;
    }

    public Polinom getQuotient() {
        return quotient;
    }

    public void setQuotient(Polinom quotient) {
        this.quotient = quotient;
    }
}
