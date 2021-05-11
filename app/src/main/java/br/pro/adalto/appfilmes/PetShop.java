package br.pro.adalto.appfilmes;

public class PetShop {

    public static final int IDADE_MINIMA = 1;

    public int id;
    public String nomeCao;
    public String endereco;
    public int idadeCao;
    public String nomeDono;


    public String getNomeDono() {
        return nomeDono;
    }

    public void setNomeDono(String nomeDono) {
        this.nomeDono = nomeDono;
    }



    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public PetShop() {

    }

    public PetShop(String nomeCao, String endereco, String nomeDono, int idadeCao) {
        this.nomeCao = nomeCao;
        this.nomeCao = nomeCao;
        this.nomeDono = nomeDono;
        this.endereco = endereco;
        this.idadeCao = idadeCao;
    }

    public PetShop(int id, String nomeCao, String endereco, String nomeDono, int idadeCao) {
        this.id = id;
        this.nomeCao = nomeCao;
        this.nomeDono = nomeDono;
        this.endereco = endereco;
        this.idadeCao = idadeCao;
    }

    public int getIdadeCao() {
        return idadeCao;
    }

    public void setIdadeCao(int idadeCao) {
        if( idadeCao >= IDADE_MINIMA)
            this.idadeCao = idadeCao;
    }

    @Override
    public String toString() {
        return id + " - " + nomeCao + " | "  + nomeDono + " | " + endereco + " | " + idadeCao;
    }
}
