package com.example.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.Invoice;

@Repository
public interface InvoiceService extends JpaRepository<Invoice, Long>{
	
	@Query("select sum(e.totalPrice) from Invoice e where year(e.paymentDate) = year(current_date) and  month(e.paymentDate) = month(current_date)")
	long getAllOfCurrentMonth();

	@Query("SELECT i from Invoice i where i.customerName like %?1% or i.id like %?1% or i.paymentDate like %?1%")
	List<Invoice> searchInvoice(String searchText);
	
	@Query("select count(e.id) from Invoice e where e.paymentDate = current_date()")
	long getCountTodayInvoices();
	
	@Query("select sum(e.totalPrice) from Invoice e where e.paymentDate = current_date()")
	long getDailySale();
	
	 List<Invoice> findTop10ByOrderByIdDesc();
//	
//	@Query("SELECT i from Invoice i order by id desc limit 10")
//	List<Invoice> findLast10();
}
