package ba.unsa.etf.rpr;

public class Drzava {
    private int id;
    private String naziv;
    private Grad glavniGrad;
    private Grad najveciGrad;

    public Drzava(int id, String naziv, Grad glavniGrad, Grad najveciGrad) {
        this.id = id;
        this.naziv = naziv;
        this.glavniGrad = glavniGrad;
       this.najveciGrad = najveciGrad;
    }

    public Drzava() {
    }

    public Drzava(int id, String naziv, Grad glavniGrad) {
        this.id = id;
        this.naziv = naziv;
        this.glavniGrad = glavniGrad;
        setNajveciGrad(this.glavniGrad);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Grad getGlavniGrad() {
        return glavniGrad;
    }

    public void setGlavniGrad(Grad glavniGrad) {
        if (najveciGrad == null)
            setNajveciGrad(glavniGrad);
        this.glavniGrad = glavniGrad;
    }

    public Grad getNajveciGrad() {
        return najveciGrad;
    }

    public void setNajveciGrad(Grad najveciGrad) {
        this.najveciGrad = najveciGrad;
    }

    @Override
    public String toString() { return naziv; }
}
