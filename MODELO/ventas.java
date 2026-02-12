package MODELO;

public class ventas {

    private int id;
    private cliente id_cliente;
    private String FECHA;
    private double total;

    public ventas() {

    }

    public ventas(int id, cliente id_cliente, String FECHA, double total) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.FECHA = FECHA;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public cliente getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(cliente id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return """
               *****************************
               Id_venta:    %s
                            %s
               FECHA:       %s
               TOTAL:       %s
               """.formatted(id, id_cliente,FECHA,total);
    }

}