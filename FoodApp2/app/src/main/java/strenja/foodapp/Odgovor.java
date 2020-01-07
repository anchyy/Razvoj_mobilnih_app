package strenja.foodapp;

import java.util.Date;
import java.util.List;

public class Odgovor {

    private String kljuc;
    private Date vrijeme;
    private boolean greska;
    private List<Recept> recepti;

    public String getKljuc() {
        return kljuc;
    }

    public void setKljuc(String kljuc) {
        this.kljuc = kljuc;
    }

    public Date getVrijeme() {
        return vrijeme;
    }

    public void setVrijeme(Date vrijeme) {
        this.vrijeme = vrijeme;
    }

    public boolean isGreska() {
        return greska;
    }

    public void setGreska(boolean greska) {
        this.greska = greska;
    }

    public List<Recept> getRecepti() {
        return recepti;
    }

    public void setRecepti(List<Recept> recepti) {
        this.recepti = recepti;
    }
}
