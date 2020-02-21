package com.lucida.emembler.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lucida.emembler.entity.PickList;

@Repository
public interface PickListRepository extends JpaRepository<PickList, Integer> {
	
	@Query(value ="select * from pick_list order by order_id", nativeQuery = true)
	public List<PickList> getStagesByOrder();
	
	@Query("select count(pl)>0 from PickList pl where pl.orderId = :orderId")
	public boolean existsIfOrder(@Param("orderId") int orderId);

}
