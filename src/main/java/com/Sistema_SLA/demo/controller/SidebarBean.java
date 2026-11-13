package com.Sistema_SLA.demo.controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 * Controla o estado (expandida/recolhida) da barra lateral de navegação.
 * SessionScoped: o estado persiste enquanto o usuário navega entre as páginas admin.
 */
@Named
@SessionScoped
public class SidebarBean implements Serializable {

    private boolean recolhida = false;

    /** Alterna entre expandida e recolhida */
    public void toggle() {
        recolhida = !recolhida;
    }

    public boolean isRecolhida() {
        return recolhida;
    }

    public void setRecolhida(boolean recolhida) {
        this.recolhida = recolhida;
    }
}
