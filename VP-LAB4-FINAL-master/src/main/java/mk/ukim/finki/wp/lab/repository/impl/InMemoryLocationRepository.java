package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryLocationRepository {
    public List<Location> findAll(){
        return DataHolder.locations;
    }

    public Optional<Location> findById(Long locationId) {
        return DataHolder.locations.stream().filter(i->i.getId().equals(locationId)).findFirst();
    }
}
