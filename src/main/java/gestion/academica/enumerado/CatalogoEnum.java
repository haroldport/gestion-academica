package gestion.academica.enumerado;

public enum CatalogoEnum {
	
	/**
     * Tipo de documento.
     */
    TIPO_DOCUMENTO("TIPDO"),
    /**
     * Tipo de persona.
     */
    TIPO_PERSONA("TIPPE"),
    /**
     * Ciudades.
     */
    CIUDAD("CIUDA"),
    /**
     * Tipo de proceso.
     */
    TIPO_PROCESO("TIPRO"),
    /**
     * Tipo de persona 2.
     */
    TIPO_PERSONA2("TIPP2"),
    /**
     * Origenes.
     */
    ORIGEN("ORIGE"),
    RESIDENTE_ECUADOR("RESEC"),
    DOMICILIADO_ECUADOR("DOMEC"),
    GENERO("GENER"),
    ESTADO_CIVIL("ESTCI"),
    NIVEL_EDUCACION("NIVED"),
    AREA_ESPECIALIDAD("ARESP"),
    PROVINCIA("PROVI"),
    CANTON("CANTO"),
    PARROQUIA("PARRO"),
    TIPO_TELEFONO("TIPTF"),
    DOCUMENTO_IDENTIFICACION("DOCID"),
    CARGO("CARGO"),
    PRODUCTOS("PRODU"),
    VENTAS_BRUTAS("VENBR"),
    NUMERO_TRABAJADORES("NUMTR"),
    TIPO_COMPRA("TIPCO"),
    PARTIDA_PRESUPUESTARIA("PARPR"),
    SALDO("SALDO"),
    ENCARGADO_PROCESO("ENCPR");
    
    
    
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
