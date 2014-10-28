package gestion.academica.enumerado;

public enum CatalogoEnum {
	
	/**
     * Tipo de documento.
     */
    TIPO_DOCUMENTO("TIPDO"),
    /**
     * Tipo de proceso.
     */
    TIPO_PROCESO("TIPPR");
    
    private String nemonico;

    /**
     * Constructor de la clase.
     *
     * @param nemonico - Sting
     */
    private CatalogoEnum(String nemonico) {
        this.nemonico = nemonico;
    }

    /**
     * Getter nemonico.
     *
     * @return String
     */
    public String getNemonico() {
        return nemonico;
    }

}
