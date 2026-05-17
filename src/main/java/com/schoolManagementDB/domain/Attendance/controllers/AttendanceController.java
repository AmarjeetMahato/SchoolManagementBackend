package com.schoolManagementDB.domain.Attendance.controllers;


import com.schoolManagementDB.domain.Attendance.services.IAttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AttendanceController {

    private final IAttendanceService attendanceService;


}
