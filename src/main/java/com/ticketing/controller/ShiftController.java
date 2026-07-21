package com.ticketing.controller;

import com.ticketing.model.Shift;
import com.ticketing.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/shifts")
@CrossOrigin(origins = "http://localhost:3000")
public class ShiftController {

    @Autowired
    private ShiftService shiftService;

    @GetMapping
    public List<Shift> getAllShifts() {
        return shiftService.getAllShifts();
    }

    @PostMapping
    public Shift saveShift(@RequestBody Shift shift) {
        return shiftService.saveShift(shift);
    }
    @PutMapping("/{id}")
    public Shift updateShift(@PathVariable Long id, @RequestBody Shift shift) {
        shift.setId(id);
        return shiftService.saveShift(shift);
}
}

