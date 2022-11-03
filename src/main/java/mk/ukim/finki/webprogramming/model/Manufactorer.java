package mk.ukim.finki.webprogramiranje.model;

public class Manufactorer {
    private Long id;
    private String name;
    private String address;

    public Manufactorer(String name, String address) {
        this.id = (long) (Math.random()*1000);
        this.name = name;
        this.address = address;
    }
}
