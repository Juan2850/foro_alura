package com.foro.foro_alura.services.interfaces;

import com.foro.foro_alura.dto.TopicalDTO;

import java.util.List;

public interface ITopicalService {
    TopicalDTO saveTopical(TopicalDTO topicalDTO);
    public List<TopicalDTO> findAll();
    public TopicalDTO updateTopical(Long id, TopicalDTO topicalDTO);
}
