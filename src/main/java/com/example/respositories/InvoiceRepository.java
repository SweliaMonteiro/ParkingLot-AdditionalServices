package com.example.respositories;

import java.util.*;
import com.example.models.Invoice;
import org.springframework.stereotype.Repository;


@Repository
public class InvoiceRepository {

	private Map<Long, Invoice> map;

	public InvoiceRepository() {
		map = new HashMap<Long, Invoice>();
	}

	public Invoice save(Invoice invoice) {
		if(invoice.getId()==0) {
			invoice.setId(map.size()+1);
		}
		map.put(invoice.getId(), invoice);
		return invoice;
	}
}
