package com.schoolManagementDB.controllers;

import com.schoolManagementDB.dtos.ParentDto;
import com.schoolManagementDB.dtos.ParentRequestDto;
import com.schoolManagementDB.entities.Parents;
import com.schoolManagementDB.services.ParentService.ParentService;
import com.schoolManagementDB.services.TeacherService.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parents")
@AllArgsConstructor
public class ParentController {

    private final ParentService parentService;


    @PostMapping("/create")
    public ResponseEntity<Parents> createParent(@RequestBody ParentRequestDto parentDto) {
        Parents savedParent = parentService.createParent(
                parentDto.getParentDto(),
                parentDto.getAddressDto(),
                parentDto.getStudentIds());
        return ResponseEntity.ok(savedParent);
    }

    @GetMapping("/get-all-parents")
    public ResponseEntity<List<ParentDto>> getAllParents() {
        return ResponseEntity.ok(parentService.getAllParents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parents> getParentById(@PathVariable String id) {
        return ResponseEntity.ok(parentService.getParentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parents> updateParent(@PathVariable String id, @RequestBody ParentDto parentDto) {
        return ResponseEntity.ok(parentService.updateParent(id, parentDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParent(@PathVariable String id) {
        parentService.deleteParent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ParentDto>> searchByName(@RequestParam("keyword") String keyword) {
        return ResponseEntity.ok(parentService.searchParentsByName(keyword));
    }

    @GetMapping("/by-phone")
    public ResponseEntity<Parents> getParentByPhone(@RequestParam("phone") String phone) {
        return ResponseEntity.ok(parentService.getParentByPhone(phone));
    }

    @GetMapping("/children-over")
    public ResponseEntity<List<ParentDto>> getParentsWithManyChildren(@RequestParam("count") int minChildren) {
        return ResponseEntity.ok(parentService.getParentsWithMoreThanNChildren(minChildren));
    }
}
