package com.foro.foro_alura.services.implementation;

import com.foro.foro_alura.dto.TopicalDTO;
import com.foro.foro_alura.entities.Topical;
import com.foro.foro_alura.exception.exception.UserNotFoundException;
import com.foro.foro_alura.repositories.ITopicalRepository;
import com.foro.foro_alura.services.interfaces.ITopicalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class TopicalServiceImpl implements ITopicalService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ITopicalRepository topicalRepository;

    @Override
    public TopicalDTO saveTopical(TopicalDTO topicalDTO) {
        Optional<Topical> existingEntity = topicalRepository.findByTitleAndMessage(topicalDTO.getTitle(), topicalDTO.getMessage());
        if(existingEntity.isPresent()){
            throw new IllegalArgumentException("Ya existe un registro con el mismo título y mensaje.");
        }
        Topical topical = this.modelMapper.map(topicalDTO, Topical.class);
        Topical topicalSaved = this.topicalRepository.save(topical);
        return this.modelMapper.map(topicalSaved,TopicalDTO.class);
    }

    @Override
    public List<TopicalDTO> findAll() {
        List<Topical> topicals = (List<Topical>) topicalRepository.findAll();
        return topicals.stream()
                .map(topical -> modelMapper.map(topical, TopicalDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TopicalDTO updateTopical(Long id, TopicalDTO topicalDTO) {
        Topical topical = topicalRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuario con id " + id + " no existe."));

        Optional<Topical> existingEntity = topicalRepository.findByTitleAndMessage(topicalDTO.getTitle(), topicalDTO.getMessage());
        if (existingEntity.isPresent() && !existingEntity.get().equals(topical)) {
            throw new IllegalArgumentException("Ya existe un registro con el mismo título y mensaje.");
        }

        topical.setTitle(topicalDTO.getTitle());
        topical.setMessage(topicalDTO.getMessage());
        topical.setDateOfCreation(topicalDTO.getDateOfCreation());
        topical.setTopicalStatus(topicalDTO.isTopicalStatus());
        topical.setAuthor(topicalDTO.getAuthor());
        topical.setCourse(topicalDTO.getCourse());
        Topical topicalUpdate = topicalRepository.save(topical);

        return this.modelMapper.map(topicalUpdate, TopicalDTO.class);
    }

}
