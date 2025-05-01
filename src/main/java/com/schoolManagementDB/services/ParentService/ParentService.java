package com.schoolManagementDB.services.ParentService;

import com.schoolManagementDB.dtos.AddressDto;
import com.schoolManagementDB.dtos.ParentDto;
import com.schoolManagementDB.entities.Parents;

import java.util.List;

public interface ParentService {

    Parents createParent(ParentDto parentDto, AddressDto addressDto, List<String> studentIds);

    List<ParentDto> getAllParents();

    Parents getParentById(String parentId);

    Parents updateParent(String parentId, ParentDto parentDto);

    void deleteParent(String parentId);

    List<ParentDto> searchParentsByName(String nameKeyword);

    Parents getParentByPhone(String phone);

    List<ParentDto> getParentsWithMoreThanNChildren(int minChildren);
}
