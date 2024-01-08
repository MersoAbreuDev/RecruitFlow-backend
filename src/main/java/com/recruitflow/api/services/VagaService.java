package com.recruitflow.api.services;

import com.recruitflow.api.configs.modelMapper.ModelMapperConfig;
import com.recruitflow.api.entities.Vaga;
import com.recruitflow.api.repositories.VagaRepository;
import com.recruitflow.api.requestDTO.VagaRequestDTO;
import com.recruitflow.api.responseDTO.VagaResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VagaService {

    private final ModelMapper modelMapper;

    private final VagaRepository vagaRepository;

    public List<VagaResponseDTO> buscarTodos(PageRequest pageRequest) {
        return this.vagaRepository.findAll(pageRequest).stream().map(vaga -> this.modelMapper.map(vaga, VagaResponseDTO.class)).collect(Collectors.toList());
    }

    public VagaResponseDTO salvar(VagaRequestDTO vagaRequestDTO) {
        Vaga vaga = this.modelMapper.map(vagaRequestDTO, Vaga.class);
        vaga = this.vagaRepository.save(vaga);
        return this.modelMapper.map(vaga, VagaResponseDTO.class);
    }

    public VagaResponseDTO buscarVagaPorId(Long id) {
        Vaga vaga = vagaRepository.findById(id).get();
        return this.modelMapper.map(vaga, VagaResponseDTO.class);
    }
}
