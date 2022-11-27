public enum NumbersRomeSix {
    XC(90), LXXX(80), LXX(70), LX(60), L(50), XL(40), XXX(30), XX(20), X(10);

    private int number;
    NumbersRomeSix(int number) {
        this.number = number;
    }

    public int getNumberArabic() {
        return number;
    }
}
