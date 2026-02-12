
package MODELO;


public class Celular {
    private int id;
    private marca id_marca;
    private String sistema_operativo;
    private String gama;
    private int precio;
    private int stock;
    private String modelo;
    
    
    public Celular(){
    
    }

    public Celular(int id, marca marca, String sistema_operativo, String gama, int precio, int stock, String modelo) {
        this.id = id;
        this.id_marca = marca;
        this.sistema_operativo = sistema_operativo;
        this.gama = gama;
        this.precio = precio;
        this.stock = stock;
        this.modelo = modelo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public marca getMarca() {
        return id_marca;
    }

    public void setMarca(marca marca) {
        this.id_marca = marca;
    }

    public String getSistema_operativo() {
        return sistema_operativo;
    }

    public void setSistema_operativo(String sistema_operativo) {
        this.sistema_operativo = sistema_operativo;
    }

    public String getGama() {
        return gama;
    }

    public void setGama(String gama) {
        this.gama = gama;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
        @Override
    public String toString() {
        return """
               *****************************
               Id_celular:              %s
                                %s
               SISTEMA OP:      %s
               GAMA:            %s
               PRECIO:          %s 
               STOCK:           %s
               MODELO:          %s
               """.formatted(id, id_marca, sistema_operativo, gama, precio,stock,modelo);
    }