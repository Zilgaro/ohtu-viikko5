package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukujono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        alusta(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        alusta(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        alusta(kapasiteetti, kasvatuskoko);
    }

    private void alusta(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Joukon parametreina ei saa olla"
                    + " negatiivisia lukuja");
        } else {
            lukujono = new int[kapasiteetti];
            alkioidenLkm = 0;
            this.kasvatuskoko = kasvatuskoko;
        }
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            lisaaTaulukkoon(luku);
            return true;
        }
        return false;
    }

    private void lisaaTaulukkoon(int luku) {
        if (alkioidenLkm == lukujono.length) {
            int[] taulukkoOld = new int[alkioidenLkm + kasvatuskoko];
            System.arraycopy(lukujono, 0, taulukkoOld, 0, lukujono.length);
            lukujono = taulukkoOld;
        }
        lukujono[alkioidenLkm] = luku;
        alkioidenLkm++;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        int kohta = etsiIndeksi(luku);
        if (kohta != -1) {
            vaihdaAlkiot(kohta);
            return true;
        }
        return false;
    }

    private void vaihdaAlkiot(int kohta) {
        for (int j = kohta; j < alkioidenLkm - 1; j++) {
            int apu = lukujono[j];
            lukujono[j] = lukujono[j + 1];
            lukujono[j + 1] = apu;
        }
        alkioidenLkm--;
    }

    private int etsiIndeksi(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                lukujono[i] = 0;
                return i;
            }
        }
        return -1;
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + lukujono[0] + "}";
        } else {
            return tulostaAlkiot();
        }
    }

    private String tulostaAlkiot() {
        String tuotos = "{";
        for (int i = 0; i < alkioidenLkm - 1; i++) {
            tuotos += lukujono[i];
            tuotos += ", ";
        }
        tuotos += lukujono[alkioidenLkm - 1];
        tuotos += "}";
        return tuotos;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        System.arraycopy(lukujono, 0, taulu, 0, taulu.length);
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko joukkoA, IntJoukko joukkoB) {
        int[] aTaulu = joukkoA.toIntArray();
        int[] bTaulu = joukkoB.toIntArray();
        return yhdista(aTaulu, bTaulu);
    }

    private static IntJoukko yhdista(int[] aTaulu, int[] bTaulu) {
        IntJoukko yhdisteJ = new IntJoukko();
        for (int i = 0; i < aTaulu.length; i++) {
            yhdisteJ.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            yhdisteJ.lisaa(bTaulu[i]);
        }
        return yhdisteJ;
    }

    public static IntJoukko leikkaus(IntJoukko joukkoA, IntJoukko joukkoB) {
        int[] aTaulu = joukkoA.toIntArray();
        int[] bTaulu = joukkoB.toIntArray();
        return leikkaa(aTaulu, bTaulu);
    }

    private static IntJoukko leikkaa(int[] aTaulu, int[] bTaulu) {
        IntJoukko leikkausJ = new IntJoukko();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    leikkausJ.lisaa(bTaulu[j]);
                }
            }
        }
        return leikkausJ;
    }

    public static IntJoukko erotus(IntJoukko joukkoA, IntJoukko joukkoB) {
        int[] aTaulu = joukkoA.toIntArray();
        int[] bTaulu = joukkoB.toIntArray();
        return erota(aTaulu, bTaulu);
    }

    private static IntJoukko erota(int[] aTaulu, int[] bTaulu) {
        IntJoukko erotusJ = new IntJoukko();
        for (int i = 0; i < aTaulu.length; i++) {
            erotusJ.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            erotusJ.poista(bTaulu[i]);
        }
        return erotusJ;
    }
}
