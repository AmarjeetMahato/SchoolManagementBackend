package com.schoolManagementDB.domain.Parents.controllers;

import com.schoolManagementDB.domain.Parents.dtos.ParentsDto;
import com.schoolManagementDB.domain.Parents.dtos.ParentsResponseDto;
import com.schoolManagementDB.domain.Parents.dtos.ParentsUpdateDto;
import com.schoolManagementDB.domain.Parents.services.IParentsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parents")
@AllArgsConstructor
public class ParentsControllers {


    private final IParentsService parentService;

    @PostMapping("/create")
    public ResponseEntity<ParentsResponseDto> createParent(@RequestBody ParentsDto parentDto) {
        ParentsResponseDto savedParent = parentService.createParent(parentDto);
        return ResponseEntity.ok(savedParent);
    }

    @GetMapping("/get-all-parents")
    public ResponseEntity<List<ParentsResponseDto>> getAllParents() {
        return ResponseEntity.ok(parentService.getAllParents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParentsResponseDto> getParentById(@PathVariable String id) {
        return ResponseEntity.ok(parentService.getParentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParentsResponseDto> updateParent(
            @PathVariable String id, @RequestBody ParentsUpdateDto parentDto) {
        return ResponseEntity.ok(parentService.updateParent(id, parentDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParent(@PathVariable String id) {
        parentService.deleteParent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ParentsResponseDto>> searchByName(@RequestParam("keyword") String keyword) {
        return ResponseEntity.ok(parentService.searchParentsByName(keyword));
    }

    @GetMapping("/by-phone")
    public ResponseEntity<ParentsResponseDto> getParentByPhone(@RequestParam("phone") String phone) {
        return ResponseEntity.ok(parentService.getParentByPhone(phone));
    }

    @GetMapping("/children-over")
    public ResponseEntity<List<ParentsResponseDto>> getParentsWithManyChildren(@RequestParam("count") int minChildren) {
        return ResponseEntity.ok(parentService.getParentsWithMoreThanNChildren(minChildren));
    }
}
