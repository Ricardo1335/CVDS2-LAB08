package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

public interface ItemRentadoMapper {
    public List<ItemRentado> consultarItemsRentados(@Param("idcli") long id);

    public List<ItemRentado> TotalRentado();

    public ItemRentado consultarItemRentado( @Param("idItem") int idItem );
    
}
