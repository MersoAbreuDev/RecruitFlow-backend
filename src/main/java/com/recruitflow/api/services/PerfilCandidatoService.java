package com.recruitflow.api.services;


import com.recruitflow.api.entities.Endereco;
import com.recruitflow.api.entities.PerfilCandidato;
import com.recruitflow.api.handlers.ObjetoNotFoundException;
import com.recruitflow.api.repositories.PerfilCandidatoRepository;
import com.recruitflow.api.requestDTO.PerfilCandidatoRequestDTO;
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
    public PerfilCandidatoResponseDTO salvarPerfilCandidatoComImagemEEndereco(PerfilCandidatoRequestDTO perfilCandidatoRequestDTO, byte[] imagemBytes) {
        PerfilCandidato perfil = this.modelMapper.map(perfilCandidatoRequestDTO, PerfilCandidato.class);
        Endereco endereco = perfilCandidatoRequestDTO.getEndereco();
        perfil.setEndereco(endereco);
        perfil = this.perfilCandidatoRepository.save(perfil);
        imagemService.salvarImagemCandidato(perfil.getId(), imagemBytes);

        perfil = this.perfilCandidatoRepository.save(perfil);
        return modelMapper.map(perfil, PerfilCandidatoResponseDTO.class);
    }

    public PerfilCandidatoResponseDTO buscarCandidatoPorIdUsuario(Long idUsuario) {
        return this.perfilCandidatoRepository.findByUsuarioId(idUsuario).map(perfil -> {
            return modelMapper.map(perfil, PerfilCandidatoResponseDTO.class);
        }).orElseThrow(() -> new ObjetoNotFoundException("Perfil não encontrado para o usuário com ID: " + idUsuario));
    }

    public PerfilCandidatoResponseDTO alterarPerfilCandidato(Long id, PerfilCandidatoRequestDTO perfilCandidatoRequestDTO) {
        PerfilCandidato perfil = this.perfilCandidatoRepository.findById(id).get();
        perfil.setNome(perfilCandidatoRequestDTO.getNome());
        perfil.setCelular(perfilCandidatoRequestDTO.getCelular());
        perfil.setEmail(perfilCandidatoRequestDTO.getEmail());
        perfilCandidatoRequestDTO.setId(perfil.getId());

        perfil = this.modelMapper.map(perfilCandidatoRequestDTO, PerfilCandidato.class);
        perfil = this.perfilCandidatoRepository.save(perfil);

        return this.modelMapper.map(perfil, PerfilCandidatoResponseDTO.class);
    }
}
