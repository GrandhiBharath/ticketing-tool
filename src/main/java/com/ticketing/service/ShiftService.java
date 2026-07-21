package com.ticketing.service;

import com.ticketing.model.Shift;
import com.ticketing.repository.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ShiftService {

    @Autowired
    private ShiftRepository shiftRepository;

    public List<Shift> getAllShifts() {
        return shiftRepository.findAll();
    }

    public Shift saveShift(Shift shift) {
        return shiftRepository.save(shift);
    }
}