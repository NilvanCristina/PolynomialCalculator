public class Monom implements Comparable<Monom> {
    private int degree;
    private double coefficient;
    private boolean added;

    public Monom(int grad, double coeficient) {
        this.degree = grad;
        this.coefficient = coeficient;
        this.added = false;
    }

    public int getDegree() {
        return degree;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public boolean isAdded() {
        return added;
    }

    public void setAdded(boolean added) {
        this.added = added;
    }

    public Monom divideMonomials(Monom monom) {
        int resultDegree = degree - monom.getDegree();
        double resultCoefficient = coefficient / monom.getCoefficient();

        return new Monom(resultDegree, resultCoefficient);
    }

    public int compareTo(Monom object) {
        return object.getDegree() - this.getDegree();
    }
}
