package madstodolist.service;

import madstodolist.dto.UsuarioData;
import madstodolist.model.Usuario;
import madstodolist.repository.UsuarioRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuariosServiceTest {

    private UsuarioService usuarioService;
    private UsuarioRepository usuarioRepository;
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        usuarioRepository = mock(UsuarioRepository.class);
        modelMapper = new ModelMapper();
        usuarioService = new UsuarioService(usuarioRepository, modelMapper);
    }

    @Test
    void testFindAllUsuarios() {
        // Arrange
        Usuario usuario1 = new Usuario();
        usuario1.setId(1L);
        usuario1.setEmail("user1@example.com");
        usuario1.setPassword("password1");

        Usuario usuario2 = new Usuario();
        usuario2.setId(2L);
        usuario2.setEmail("user2@example.com");
        usuario2.setPassword("password2");

        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuario1, usuario2));

        // Act
        List<UsuarioData> result = usuarioService.findAllUsuarios();

        // Assert
        assertEquals(2, result.size());

        assertEquals("user1@example.com", result.get(0).getEmail());
        assertEquals("user2@example.com", result.get(1).getEmail());

        verify(usuarioRepository, times(1)).findAll();
    }
}

