package edu.ifma.dcomp.lbd.model;

public enum TipoProduto {
        PERECIVEL ("Perecível"),
        NAO_PERECIVEL("Não Perecível");

        private String descricao;

        TipoProduto(String descricao) {
                this.descricao = descricao;
        }

        public String getDescricao() {
                return descricao;
        }
}
