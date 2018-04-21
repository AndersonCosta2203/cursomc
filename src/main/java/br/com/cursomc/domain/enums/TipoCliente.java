package br.com.cursomc.domain.enums;

public enum TipoCliente {

    PESSOAFISICA(1, "Pessoa Jurídica"),
    PESSOAJURIDIA(2, "Pessoa Física");

    private int cod;
    private String descricao;

    TipoCliente(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoCliente toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (TipoCliente x: TipoCliente.values()) {
            if (cod == x.getCod()) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: "+cod);
    }
}
