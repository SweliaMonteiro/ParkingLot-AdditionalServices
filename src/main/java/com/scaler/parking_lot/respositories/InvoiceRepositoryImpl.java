package com.scaler.parking_lot.respositories;

import java.util.HashMap;
import java.util.Map;

import com.scaler.parking_lot.models.Invoice;


public class InvoiceRepositoryImpl implements InvoiceRepository {

	private Map<Long, Invoice> map;

	public InvoiceRepositoryImpl() {
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
