package com.recruitflow.api.services;

import com.recruitflow.api.entities.ImagemCandidato;
import com.recruitflow.api.handlers.ObjetoNotFoundException;
import com.recruitflow.api.repositories.ImagemRepository;
import com.recruitflow.api.requestDTO.PerfilCandidatoRequestDTO;
import com.recruitflow.api.responseDTO.ImagemCandidatoResponseDTO;
import com.recruitflow.api.responseDTO.PerfilCandidatoResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImagemService {

    private final ImagemRepository imagemRepository;

    private final ModelMapper modelMapper;

    @Transactional
    public ImagemCandidato salvarImagemCandidato(Long id, byte[] imagemBytes) {
        ImagemCandidato imagemCandidato = new ImagemCandidato();
        imagemCandidato.setIdCandidato(id);
        imagemCandidato.setImagem(imagemBytes);
        imagemCandidato = this.imagemRepository.save(imagemCandidato);
        return imagemCandidato;
    }

    public ImagemCandidatoResponseDTO buscarImagemPorIdCandidato(Long id) {
        return this.imagemRepository.findByImagemCandidatoFromIdPerfilCandidato(id).map(imagem -> {
            return modelMapper.map(imagem, ImagemCandidatoResponseDTO.class);
        }).orElseThrow(() -> new ObjetoNotFoundException("Perfil não encontrado."));

    }
}
