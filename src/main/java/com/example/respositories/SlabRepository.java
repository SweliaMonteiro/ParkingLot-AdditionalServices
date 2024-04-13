package com.example.respositories;

import java.util.*;
import com.example.models.Slab;
import com.example.enums.VehicleType;
import org.springframework.stereotype.Repository;


@Repository
public class SlabRepository {

	private Map<Long, Slab> map;

	public SlabRepository() {
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
