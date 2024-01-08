package com.recruitflow.api.services;


import com.recruitflow.api.entities.Endereco;
import com.recruitflow.api.entities.ImagemCandidato;
import com.recruitflow.api.entities.PerfilCandidato;
import com.recruitflow.api.handlers.ObjetoNotFoundException;
import com.recruitflow.api.repositories.PerfilCandidatoRepository;
import com.recruitflow.api.requestDTO.PerfilCandidatoRequestDTO;
import com.recruitflow.api.responseDTO.ImagemCandidatoResponseDTO;
import com.recruitflow.api.responseDTO.PerfilCandidatoResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfilCandidatoService {

    private final PerfilCandidatoRepository perfilCandidatoRepository;

    private final ModelMapper modelMapper;

    private final ImagemService imagemService;

    @Transactional
    public PerfilCandidatoResponseDTO salvarPerfilCandidatoComImagemEEndereco(PerfilCandidatoRequestDTO perfilCandidatoRequestDTO) {

        PerfilCandidato perfilCandidato = new PerfilCandidato();
        perfilCandidato.setEndereco(perfilCandidatoRequestDTO.getEndereco());
        perfilCandidato.setLink(perfilCandidatoRequestDTO.getLink());
        perfilCandidato.setNome(perfilCandidatoRequestDTO.getNome());
        perfilCandidato.setCelular(perfilCandidatoRequestDTO.getCelular());
        perfilCandidato.setEmail(perfilCandidatoRequestDTO.getEmail());
        perfilCandidato.setUsuario(perfilCandidatoRequestDTO.getUsuario());
        perfilCandidato.setUsuario(perfilCandidatoRequestDTO.getUsuario());
        perfilCandidato = perfilCandidatoRepository.save(perfilCandidato);

        ImagemCandidato imagem = imagemService.salvarImagemCandidato(perfilCandidato.getId(),perfilCandidatoRequestDTO.getImagem());

        perfilCandidato.setImagemCandidato(imagem);

        perfilCandidato = perfilCandidatoRepository.save(perfilCandidato);

        PerfilCandidatoResponseDTO perfilCandidatoResponseDTO = this.modelMapper.map(perfilCandidato, PerfilCandidatoResponseDTO.class);

        return perfilCandidatoResponseDTO;
    }

    public PerfilCandidatoResponseDTO buscarCandidatoPorIdUsuario(Long idUsuario) {
        return this.perfilCandidatoRepository.findByUsuarioId(idUsuario).map(perfil -> {
            ImagemCandidatoResponseDTO imagemDTO = imagemService.buscarImagemPorIdCandidato(perfil.getId());
            ImagemCandidato img = this.modelMapper.map(imagemDTO, ImagemCandidato.class);
            perfil.setImagemCandidato(img);
            return modelMapper.map(perfil, PerfilCandidatoResponseDTO.class);
        }).orElseThrow(() -> new ObjetoNotFoundException("Perfil não encontrado para o usuário com ID: " + idUsuario));
    }

    public PerfilCandidatoResponseDTO alterarPerfilCandidato(Long id, PerfilCandidatoRequestDTO perfilCandidatoRequestDTO) {
        PerfilCandidato perfil = this.perfilCandidatoRepository.findByUsuarioId(id).get();
        perfil.setEndereco(perfilCandidatoRequestDTO.getEndereco());
        perfil.setLink(perfilCandidatoRequestDTO.getLink());
        perfil.setNome(perfilCandidatoRequestDTO.getNome());
        perfil.setCelular(perfilCandidatoRequestDTO.getCelular());
        perfil.setEmail(perfilCandidatoRequestDTO.getEmail());
        perfil.setUsuario(perfilCandidatoRequestDTO.getUsuario());
        perfil.setUsuario(perfilCandidatoRequestDTO.getUsuario());
        perfil = perfilCandidatoRepository.save(perfil);
        ImagemCandidato imagem = imagemService.salvarImagemCandidato(perfil.getId(),perfilCandidatoRequestDTO.getImagem());

        perfil.setImagemCandidato(imagem);

        perfil = perfilCandidatoRepository.save(perfil);

        perfil = this.modelMapper.map(perfil, PerfilCandidato.class);
        perfil = this.perfilCandidatoRepository.save(perfil);

        return this.modelMapper.map(perfil, PerfilCandidatoResponseDTO.class);

    }

    public PerfilCandidatoResponseDTO buscarPerfilCandidatoPorId(Long idPerfilCandidato) {
        PerfilCandidato candidato = this.perfilCandidatoRepository.findById(idPerfilCandidato).get();
        return this.modelMapper.map(candidato, PerfilCandidatoResponseDTO.class);
    }

}
