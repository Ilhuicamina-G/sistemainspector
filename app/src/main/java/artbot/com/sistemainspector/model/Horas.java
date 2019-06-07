package artbot.com.sistemainspector.model;

public class Horas {

    private String fecha;
    private String desde;
    private String hasta;
    private String desde2;
    private String hasta2;
    private String desde3;
    private String hasta3;

    public Horas(String fecha, String desde, String hasta) {
        this.fecha=fecha;
        this.desde=desde;
        this.hasta=hasta;
    }


    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDesde() {
        return desde;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }

    public String getDesde2() {
        return desde2;
    }

    public void setDesde2(String desde2) {
        this.desde2 = desde2;
    }

    public String getHasta2() {
        return hasta2;
    }

    public void setHasta2(String hasta2) {
        this.hasta2 = hasta2;
    }

    public String getDesde3() {
        return desde3;
    }

    public void setDesde3(String desde3) {
        this.desde3 = desde3;
    }

    public String getHasta3() {
        return hasta3;
    }

    public void setHasta3(String hasta3) {
        this.hasta3 = hasta3;
    }
}
