package com.scaler.parking_lot.respositories;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.scaler.parking_lot.models.Slab;
import com.scaler.parking_lot.models.VehicleType;


public class SlabRepositoryImpl implements SlabRepository {

	private Map<Long, Slab> map;

	public SlabRepositoryImpl() {
		map = new HashMap<Long, Slab>();
	}

	public List<Slab> getSortedSlabsByVehicleType(VehicleType vehicleType) {
		List<Slab> slabs = new ArrayList<Slab>();
		for(Slab slab:map.values()) {
			if(slab.getVehicleType().equals(vehicleType)) {
				slabs.add(slab);
			}
		}
		slabs.sort(new Comparator<Slab>() {
			public int compare(Slab s1, Slab s2) {
				return s1.getStartHour()-s2.getStartHour();
			}
		});
		return slabs;
	}

	public Slab save(Slab slab) {
		if(slab.getId()==0) {
			slab.setId(map.size()+1);
		}
		map.put(slab.getId(), slab);
		return slab;
	}
}

