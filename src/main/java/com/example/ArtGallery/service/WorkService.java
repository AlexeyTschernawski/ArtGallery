package com.example.ArtGallery.service;


import com.example.ArtGallery.domain.DTO.WorkDTO;
import com.example.ArtGallery.domain.entity.User;
import com.example.ArtGallery.domain.entity.Work;
import com.example.ArtGallery.repositories.UserRepository;
import com.example.ArtGallery.repositories.WorkRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkService {

    @Autowired
    private WorkRepository workRepository;

    @Autowired
    private UserRepository userRepository;

    public List<WorkDTO> getAllWorks() {
        return workRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public WorkDTO getWorkById(Long id) {
        return workRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public WorkDTO createWork(WorkDTO workDTO) {
        Work work = convertToEntity(workDTO);
        Work savedWork = workRepository.save(work);
        return convertToDTO(savedWork);
    }

    public WorkDTO updateWork(Long id, WorkDTO workDTO) {
        return workRepository.findById(id)
                .map(existingWork -> {
                    existingWork.setTitle(workDTO.getTitle());
                    existingWork.setCreatedAt(workDTO.getCreatedAt());
                    existingWork.setCategoryId(workDTO.getCategoryId());
                    existingWork.setArtStyle(workDTO.getArtStyle());
                    existingWork.setComition(workDTO.isComition());
                    existingWork.setDescription(workDTO.getDescription());
                    existingWork.setImage(workDTO.getImage());

                    User user = userRepository.findById(workDTO.getUserId()).orElse(null);
                    existingWork.setUser(user);

                    Work updatedWork = workRepository.save(existingWork);
                    return convertToDTO(updatedWork);
                })
                .orElse(null);
    }

    @Transactional
    public WorkDTO deleteWork(Long id) {
        return workRepository.findById(id)
                .map(work -> {
                    workRepository.deleteById(id);
                    return convertToDTO(work);
                })
                .orElse(null);
    }

    private WorkDTO convertToDTO(Work work) {
        WorkDTO workDTO = new WorkDTO();
        workDTO.setId(work.getId());
        workDTO.setTitle(work.getTitle());
        workDTO.setCreatedAt(work.getCreatedAt());
        workDTO.setCategoryId(work.getCategoryId());
        workDTO.setArtStyle(work.getArtStyle());
        workDTO.setComition(work.isComition());
        workDTO.setDescription(work.getDescription());
        workDTO.setImage(work.getImage());

        if (work.getUser() != null) {
            workDTO.setUserId(work.getUser().getId());
        }
        return workDTO;
    }

    private Work convertToEntity(WorkDTO workDTO) {
        Work work = new Work();
        work.setId(workDTO.getId());
        work.setTitle(workDTO.getTitle());
        work.setCreatedAt(workDTO.getCreatedAt());
        work.setCategoryId(workDTO.getCategoryId());
        work.setArtStyle(workDTO.getArtStyle());
        work.setComition(workDTO.isComition());
        work.setDescription(workDTO.getDescription());
        work.setImage(workDTO.getImage());

        if (workDTO.getUserId() != null) {
            User user = userRepository.findById(workDTO.getUserId()).orElse(null);
            work.setUser(user);
        }
        return work;
    }
}
