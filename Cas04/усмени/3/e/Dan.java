

public enum Dan {
    PON(1), UTO(2), SRE(3), CET(4), PET(5), SUB(6), NED(7);

    int rb;
    Dan(int rb) {
        this.rb = rb;
        System.out.println(this.rb);
    }

    public static void main(String[] args) {
        for (Dan e : Dan.values())
            System.out.println(e.rb + ". " + e);
    }
}
