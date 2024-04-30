package com.Staff.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.Staff.Entities.Staff;
import com.Staff.Repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    public void saveBulkStaff(List<Staff> staffList) {
        List<Staff> staffEntities = staffList.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
        staffRepository.saveAll(staffEntities);
    }

    private Staff convertToEntity(Staff dto) {
        Staff staff = new Staff();
        staff.setName(dto.getName());
        staff.setGender(dto.getGender());
        staff.setMobile(dto.getMobile());
        staff.setDateOfJoining(dto.getDateOfJoining());
        staff.setAddress(dto.getAddress());
        staff.setDob(dto.getDob());
        staff.setDesignation(dto.getDesignation());
        staff.setEmail(dto.getEmail());
        staff.setStaffId(dto.getStaffId());
        staff.setDepartment(dto.getDepartment());
        return staff;
    }
}
