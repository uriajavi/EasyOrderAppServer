/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxserverside.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 * Embeddable Id class for ProductoPedido Xref entity.
 * @author javi
 */
@Embeddable
public class ProductoPedidoId implements Serializable {
    private Integer pedidoId;
    private Integer productoId;
    
    public ProductoPedidoId(){     
    }
    
    public ProductoPedidoId(Integer pedidoId,Integer productoId){
        this.pedidoId=pedidoId;
        this.productoId=productoId;
    }

    /**
     * @return the pedidoId
     */
    public Integer getPedidoId() {
        return pedidoId;
    }

    /**
     * @param pedidoId the pedidoId to set
     */
    public void setPedidoId(Integer pedidoId) {
        this.pedidoId = pedidoId;
    }

    /**
     * @return the productoId
     */
    public Integer getProductoId() {
        return productoId;
    }

    /**
     * @param productoId the productoId to set
     */
    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoPedidoId)) {
            return false;
        }
        ProductoPedidoId other = (ProductoPedidoId) object;
        if ((this.pedidoId == null && other.pedidoId != null) || 
            (this.pedidoId != null && !this.pedidoId.equals(other.pedidoId))) {
            return false;
        }
        if ((this.productoId == null && other.productoId != null) || 
            (this.productoId != null && !this.productoId.equals(other.productoId))) {
            return false;
        }
        return true;

    }
 
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productoId != null ? productoId.hashCode() : 0);
        hash += (pedidoId != null ? pedidoId.hashCode() : 0);
        return hash;
    }    
}
